package comRedkoAccountingSystem.house.service;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;
import comRedkoAccountingSystem.house.model.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HouseService class")
class HouseServiceTest {
    private int numOfPeopleHouse;
    private List<Floor> floors;
    private double areaOfHouse;
    private final House house = createHouse();

    private House createHouse() {
        int numOfHouse = (int) (Math.random() * 100) + 1;
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

    private Floor createFloor(int numFloor, double areaOfApartment, int numOfApartments) {
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

    private Apartment createApartment(int floorID, int apartmentID, double areaOfApartment) {
        int people = (int) (Math.random() * 5 + 5);
        numOfPeopleHouse += people;
        areaOfHouse += areaOfApartment;
        return Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * 100 + apartmentID).
                withNumOfPeoples(people).
                withArea(areaOfApartment).
                build();
    }

    @Test
    @DisplayName("test CountArea method")
    void testCountArea() {
        assertEquals((int) (areaOfHouse * 1000), (int) (HouseService.countAreaHouse(house) * 1000));
    }

    @Test
    @DisplayName("test countPeople method")
    void testCountPeople() {
        assertEquals(numOfPeopleHouse, HouseService.countPeople(house));
    }

    @RepeatedTest(3)
    @DisplayName("test getIndexApartment method")
    void testGetIndexApartment() {
        List<Apartment> apartments = floors.get(0).getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(index, HouseService.getIndexApartment(house, numOfApartment));

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(index, HouseService.getIndexApartment(house, numOfApartment));

        index = (int) (Math.random() * (apartments.size() - 1));
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(index, HouseService.getIndexApartment(house, numOfApartment));

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(index, HouseService.getIndexApartment(house, numOfApartment));

        index = 0;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(-1, HouseService.getIndexApartment(house, numOfApartment - 1), "There is no apartment with №" + (numOfApartment - 1)+ " should return -1");


        apartments = floors.get(floors.size() - 1).getApartments();
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(index, HouseService.getIndexApartment(house, numOfApartment));

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(index, HouseService.getIndexApartment(house, numOfApartment));

        assertEquals(-1, HouseService.getIndexApartment(house, numOfApartment + 1), "There is no apartment with №" + (numOfApartment + 1)+ " should return -1");
    }

    @RepeatedTest(3)
    @DisplayName("test isApartment method")
    void testIsApartment() {
        List<Apartment> apartments = floors.get(0).getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(HouseService.isApartment(house, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(HouseService.isApartment(house, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = (int) (Math.random() * (apartments.size() - 1));
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(HouseService.isApartment(house, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(HouseService.isApartment(house, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = 0;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertFalse(HouseService.isApartment(house, numOfApartment - 1), "There is no apartment with №" + numOfApartment + " but return true");


        apartments = floors.get(floors.size() - 1).getApartments();
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(HouseService.isApartment(house, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(HouseService.isApartment(house, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        assertFalse(HouseService.isApartment(house, numOfApartment + 1), "There is no apartment with №" + numOfApartment + " but return true");
    }

    @RepeatedTest(3)
    @DisplayName("test setPeople method")
    void testSetPeople() {
        List<Apartment> apartments = floors.get(0).getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        int numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        index = (int) (Math.random() * (apartments.size() - 1));
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());


        apartments = floors.get(floors.size() - 1).getApartments();
        index = 0;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());
    }
}