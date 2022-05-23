package com.Redko.AccountingSystem;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.Floor;
import com.Redko.AccountingSystem.models.House;
import com.Redko.AccountingSystem.services.HouseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Storage class")
class StorageTest {
    private static Map<Integer, House> houses;
    private static Storage storage;

    @BeforeAll
    private static void setUp() {
        storage = new Storage();
        int numOfHouses = (int) (Math.random() * 10) + 3;
        houses = storage.getHouses();
        for (int i = 0; i < numOfHouses; ++i) {
            House house = createHouse();
            while (houses.containsKey(house.getNumOfHouse()))
                house = createHouse();

            houses.put(house.getNumOfHouse(), house);
        }
        storage.setHouses(houses);
    }

   @RepeatedTest(3)
    @DisplayName("test addHouse method")
    void testAddHouse() {
        House house = createHouse();
        storage.addHouse(house);
        houses = storage.getHouses();
        assertTrue(houses.containsKey(house.getNumOfHouse()) && houses.containsValue(house));

        house = createHouse();
        assertFalse(houses.containsValue(house));
    }

    @Test
    @DisplayName("test getHouse method")
    void testGetHouse() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = item.getValue();
            assertEquals(house, storage.getHouse(numOfHouse));

            numOfHouse = (int) (Math.random() * 1000) + 1000;
            assertNull(storage.getHouse(numOfHouse));
        }
    }

    @Test
    @DisplayName("test getNumOfHouses method")
    void testGetNumOfHouses() {
        assertEquals(houses.size(), storage.getNumOfHouses());
    }

    @Test
    @DisplayName("test isHouse method")
    void testIsHouse() {
        int numOfHouse = (int) (Math.random() * 1000) + 1;
        while (houses.containsKey(numOfHouse)) {
            numOfHouse = (int) (Math.random() * 1000) + 1;
        }
        assertFalse(storage.isHouse(numOfHouse));

        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            assertTrue(storage.isHouse(item.getKey()));
        }
    }

    @RepeatedTest(2)
    @DisplayName("test setPeople method")
    void testSetPeople() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            var house = item.getValue();
            var apartments = house.getFloors().get(0).getApartments();

            int indexOfApartment = (int) ((apartments.size() - 1) * Math.random());
            int numOfApartment = apartments.get(indexOfApartment).getNumOfApartment();

            int numOfPeople = (int) (Math.random() * 10 + 5);
            storage.setPeople(house.getNumOfHouse(), numOfApartment, numOfPeople);

            houses = storage.getHouses();
            var apartment = house.getFloors().get(0).getApartments().get(indexOfApartment);
            assertEquals(numOfPeople, apartment.getNumOfPeople());
        }
        houses = storage.getHouses();
    }

    @Test
    @DisplayName("test isEmpty method")
    void testIsEmpty() {
        assertEquals(houses.isEmpty(), storage.isEmpty());
    }

    @Test
    @DisplayName("test compareArea for house method")
    void testCompareArea() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = item.getValue();
            double area = HouseService.getArea(house);

            for (Map.Entry<Integer, House> item1 : houses.entrySet()) {
                int numOfHouse1 = item1.getKey();
                var house1 = item1.getValue();
                double area1 = HouseService.getArea(house1);

                assertEquals(Double.compare(area, area1), storage.compareArea(numOfHouse, numOfHouse1));
            }
        }
    }

    @Test
    @DisplayName("test compareHeight for house method")
    void testCompareHeight() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = item.getValue();
            int height = HouseService.getNumOfFloors(house);

            for (Map.Entry<Integer, House> item1 : houses.entrySet()) {
                int numOfHouse1 = item1.getKey();
                var house1 = item1.getValue();
                int height1 = HouseService.getNumOfFloors(house1);

                assertEquals(Double.compare(height, height1), storage.compareHeight(numOfHouse, numOfHouse1));
            }
        }
    }

    @Test
    @DisplayName("test compareNumOfPeople for house method")
    void testCompareNumOfPeople() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = item.getValue();
            int numOfPeople = HouseService.getNumOfPeople(house);

            for (Map.Entry<Integer, House> item1 : houses.entrySet()) {
                int numOfHouse1 = item1.getKey();
                var house1 = item1.getValue();
                int numOfPeople1 = HouseService.getNumOfPeople(house1);

                assertEquals(Double.compare(numOfPeople, numOfPeople1), storage.compareNumOfPeople(numOfHouse, numOfHouse1));
            }
        }
    }

    @Test
    @DisplayName("test compareArea for apartment method")
    void testCompareAreaA() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = item.getValue();

            var apartments = house.getFloors().get(0).getApartments();

            int indexOfApartment = (int) ((apartments.size() - 1) * Math.random());
            int numOfApartment = apartments.get(indexOfApartment).getNumOfApartment();
            double area = apartments.get(indexOfApartment).getArea();

            for (Map.Entry<Integer, House> item1 : houses.entrySet()) {
                int numOfHouse1 = item1.getKey();
                var house1 = item1.getValue();
                var apartments1 = house1.getFloors().get(0).getApartments();

                int indexOfApartment1 = (int) ((apartments1.size() - 1) * Math.random());
                int numOfApartment1 = apartments1.get(indexOfApartment1).getNumOfApartment();
                double area1 = apartments1.get(indexOfApartment1).getArea();

                assertEquals(Double.compare(area, area1), storage.compareArea(numOfHouse, numOfApartment, numOfHouse1, numOfApartment1));
            }
        }
    }

    @Test
    @DisplayName("test compareNumOfPeople for apartment method")
    void testCompareNumOfPeopleA() {
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = item.getValue();

            var apartments = house.getFloors().get(0).getApartments();

            int indexOfApartment = (int) ((apartments.size() - 1) * Math.random());
            int numOfApartment = apartments.get(indexOfApartment).getNumOfApartment();
            int numOfPeople = apartments.get(indexOfApartment).getNumOfPeople();

            for (Map.Entry<Integer, House> item1 : houses.entrySet()) {
                int numOfHouse1 = item1.getKey();
                var house1 = item1.getValue();
                var apartments1 = house1.getFloors().get(0).getApartments();

                int indexOfApartment1 = (int) ((apartments1.size() - 1) * Math.random());
                int numOfApartment1 = apartments1.get(indexOfApartment1).getNumOfApartment();
                int numOfPeople1 = apartments1.get(indexOfApartment1).getNumOfPeople();

                assertEquals(Double.compare(numOfPeople, numOfPeople1), storage.compareNumOfPeople(numOfHouse, numOfApartment, numOfHouse1, numOfApartment1));
            }
        }
    }

    private static House createHouse() {
        List<Floor> floors;
        int numOfHouse = (int) (Math.random() * 1000) + 1;
        int numOfFloors = (int) (Math.random() * 5) + 5;
        int numOfApartments = (int) (Math.random() * 10) + 5;
        double areaOfApartment = Math.random() * 100 + 50;

        floors = new ArrayList<>();

        for (int i = 1; i <= numOfFloors; ++i) {
            Floor floor = createFloor(i, areaOfApartment, numOfApartments);
            floors.add(i - 1, floor);
        }

        return House.HouseBuilder.aHouse().
                withNumOfHouse(numOfHouse).
                withFloors(floors).
                build();
    }

    private static Floor createFloor(int numFloor, double areaOfApartment, int numOfApartments) {
        List<Apartment> apartments = new ArrayList<>(numOfApartments);
        int apartmentID = 1;
        for (int i = 0; i < numOfApartments; ++i) {
            Apartment apartment = createApartment(numFloor, apartmentID++, areaOfApartment);
            apartments.add(i, apartment);
        }
        return Floor.FloorBuilder.aFloor().
                withNumOfFloor(numFloor).
                withApartments(apartments).
                build();
    }

    private static Apartment createApartment(int floorID, int apartmentID, double areaOfApartment) {
        int people = (int) (Math.random() * 5 + 5);
        return Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * 100 + apartmentID).
                withNumOfPeople(people).
                withArea(areaOfApartment).
                build();
    }

}