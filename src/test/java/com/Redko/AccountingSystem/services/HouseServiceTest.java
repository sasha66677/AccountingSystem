package com.Redko.AccountingSystem.services;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.Floor;
import com.Redko.AccountingSystem.models.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HouseService class")
class HouseServiceTest {

    private House house = createHouse();

    @Test
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals((int) (countArea(house) * 1000), (int) (HouseService.getArea(house) * 1000));
    }

    double countArea(House house) {
        var floors = house.getFloors();
        return floors.stream().
                mapToDouble(this::countArea).
                sum();
    }

    double countArea(Floor floor) {
        var apartments = floor.getApartments();
        return apartments.stream().
                mapToDouble(Apartment::getArea).
                sum();
    }

    @Test
    @DisplayName("test getNumOfPeople method")
    void testGetNumOfPeople() {
        assertEquals(countPeople(house), HouseService.getNumOfPeople(house));
    }

    int countPeople(House house) {
        var floors = house.getFloors();
        return floors.stream().
                mapToInt(this::countPeople).
                sum();
    }

    int countPeople(Floor floor) {
        var apartments = floor.getApartments();
        return apartments.stream().
                mapToInt(Apartment::getNumOfPeople).
                sum();
    }

    @Test
    @DisplayName("test getApartment method")
    void testGetApartment() {
        var floors = house.getFloors();

        var apartments = floors.get(0).getApartments();
        int numOfApartment = apartments.get(0).getNumOfApartment() - 100;
        assertNull(HouseService.getApartment(house, numOfApartment));

        for (Floor floor : floors) {
            apartments = floor.getApartments();
            for (Apartment apartment : apartments) {
                numOfApartment = apartment.getNumOfApartment();
                assertEquals(apartment, HouseService.getApartment(house, numOfApartment));
            }
        }

    }

    @Test
    @DisplayName("test isApartment method")
    void testIsApartment() {
        var floors = house.getFloors();

        var apartments = floors.get(0).getApartments();
        int numOfApartment = apartments.get(0).getNumOfApartment() - 100;
        assertFalse(HouseService.isApartment(house, numOfApartment),
                "There is no apartment with №" + numOfApartment + " but return true");

        for (Floor floor : floors) {
            apartments = floor.getApartments();
            for (Apartment apartment : apartments) {
                numOfApartment = apartment.getNumOfApartment();
                assertTrue(HouseService.isApartment(house, numOfApartment),
                        "There is apartment with №" + numOfApartment + " but return false");
            }
        }

    }

    @RepeatedTest(5)
    @DisplayName("test setPeople method")
    void testSetPeople() {
        var floors = house.getFloors();
        int indexFloor = (int) (Math.random() * (floors.size() - 1));
        var apartments = floors.get(indexFloor).getApartments();
        int indexApartment = (int) (Math.random() * (apartments.size() - 1));
        int numOfApartment = apartments.get(indexApartment).getNumOfApartment();

        int numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        apartments = house.getFloors().get(indexFloor).getApartments();
        assertEquals(numOfPeople, apartments.get(indexApartment).getNumOfPeople());

    }

    @Test
    @DisplayName("test getNumOfFloors method")
    void testGetNumOfFloors() {
        var floors = house.getFloors();
        assertEquals(floors.size(), HouseService.getNumOfFloors(house));

    }

    @RepeatedTest(5)
    @DisplayName("test compareArea for house method")
    void testCompareArea() {
        House house1 = house;
        assertEquals(0, HouseService.compareArea(house, house1));
        double area = countArea(house);
        house1 = createHouse();
        double area1 = countArea(house1);
        assertEquals(Double.compare(area, area1), HouseService.compareArea(house, house1));

    }

    @RepeatedTest(5)
    @DisplayName("test compareNumOfPeople for house method")
    void testCompareNumOfPeople() {
        House house1 = house;
        assertEquals(0, HouseService.compareNumOfPeople(house, house1));
        int numOfPeople = countPeople(house);
        house1 = createHouse();
        int numOfPeople1 = countPeople(house1);
        assertEquals(Integer.compare(numOfPeople, numOfPeople1), HouseService.compareNumOfPeople(house, house1));

    }

    @RepeatedTest(5)
    @DisplayName("test compareHeight for house method")
    void testCompareHeight() {
        House house1 = house;
        assertEquals(0, HouseService.compareNumOfPeople(house, house1));

        house1 = createHouse();
        int numOfFloors = HouseService.getNumOfFloors(house);
        int numOfFloors1 = HouseService.getNumOfFloors(house1);
        assertEquals(Integer.compare(numOfFloors, numOfFloors1), HouseService.compareHeight(house, house1));

    }

    @RepeatedTest(5)
    @DisplayName("test compareArea for apartment method")
    void testCompareAreaApartment() {
        var floors = house.getFloors();
        var apartments = floors.get((int) (Math.random() * (floors.size() - 1))).getApartments();
        var apart = apartments.get((int) (Math.random() * (apartments.size() - 1)));
        int numOfApartment = apart.getNumOfApartment();

        assertEquals(0, HouseService.compareArea(house, numOfApartment, house, numOfApartment));

        House house1 = createHouse();
        var floors1 = house1.getFloors();
        var apartments1 = floors1.get((int) (Math.random() * (floors1.size() - 1))).getApartments();
        var apart1 = apartments1.get((int) (Math.random() * (apartments1.size() - 1)));
        int numOfApartment1 = apart1.getNumOfApartment();

        double area = apart.getArea();
        double area1 = apart1.getArea();
        assertEquals(Double.compare(area, area1), HouseService.compareArea(house, numOfApartment, house1, numOfApartment1));

    }

    @RepeatedTest(5)
    @DisplayName("test compareNumOfPeople for apartment method")
    void testCompareNumOfPeopleApartment() {
        var floors = house.getFloors();
        var apartments = floors.get((int) (Math.random() * (floors.size() - 1))).getApartments();
        var apart = apartments.get((int) (Math.random() * (apartments.size() - 1)));
        int numOfApartment = apart.getNumOfApartment();

        assertEquals(0, HouseService.compareNumOfPeople(house, numOfApartment, house, numOfApartment));

        House house1 = createHouse();
        var floors1 = house1.getFloors();
        var apartments1 = floors1.get((int) (Math.random() * (floors1.size() - 1))).getApartments();
        var apart1 = apartments1.get((int) (Math.random() * (apartments1.size() - 1)));
        int numOfApartment1 = apart1.getNumOfApartment();

        int numOfPeople = apart.getNumOfPeople();
        int numOfPeople1 = apart1.getNumOfPeople();
        assertEquals(Integer.compare(numOfPeople, numOfPeople1), HouseService.compareNumOfPeople(house, numOfApartment, house1, numOfApartment1));

    }

    private House createHouse() {
        int numOfHouse = (int) (Math.random() * 100) + 1;
        int numOfFloors = (int) (Math.random() * 5) + 5;

        List<Floor> floors = new ArrayList<>(numOfFloors);
        for (int i = 0; i < numOfFloors; ++i) {
            Floor floor = createFloor(i + 1);
            floors.add(i, floor);
        }

        return House.HouseBuilder.aHouse().
                withNumOfHouse(numOfHouse).
                withFloors(floors).
                build();
    }

    private Floor createFloor(int numFloor) {
        int numOfApartments = (int) (Math.random() * 10) + 5;
        List<Apartment> apartments = new ArrayList<>(numOfApartments);
        int apartmentID = 1;
        for (int i = 0; i < numOfApartments; ++i) {
            Apartment apartment = createApartment(numFloor, apartmentID++);
            apartments.add(i, apartment);
        }
        return Floor.FloorBuilder.aFloor().
                withNumOfFloor(numFloor).
                withApartments(apartments).
                build();
    }

    private Apartment createApartment(int floorID, int apartmentID) {
        double areaOfApartment = Math.random() * 100 + 50;
        int people = (int) (Math.random() * 5 + 5);
        return Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * 100 + apartmentID).
                withNumOfPeople(people).
                withArea(areaOfApartment).
                build();
    }

}