package com.Redko.AccountingSystem.services;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.Floor;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FloorService class")
class FloorServiceTest {

    private Floor floor = createFloor((int) (Math.random() * 10) + 2);

    @Test
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals((int) (countArea(floor) * 1000), (int) (FloorService.getArea(floor) * 1000));
    }

    double countArea(Floor floor) {
        var apartments = floor.getApartments();
        return apartments.stream().
                mapToDouble(Apartment::getArea).
                sum();
    }

    @Test
    @DisplayName("test getNumPeople method")
    void testGetNumPeople() {
        assertEquals(countPeople(floor), FloorService.getNumPeople(floor));
    }

    int countPeople(Floor floor) {
        var apartments = floor.getApartments();
        return apartments.stream().
                mapToInt(Apartment::getNumOfPeople).
                sum();
    }

    @Test
    @DisplayName("test getNumApartments method")
    void testGetNumApartments() {
        assertEquals(countApartments(floor), FloorService.getNumApartments(floor));
    }

    int countApartments(Floor floor) {
        var apartments = floor.getApartments();
        return apartments.size();
    }

    @Test
    @DisplayName("test isApartment method")
    void testIsApartment() {
        var apartments = floor.getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(FloorService.isApartment(floor, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(FloorService.isApartment(floor, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertTrue(FloorService.isApartment(floor, numOfApartment), "There is apartment with №" + numOfApartment + " but return false");

        numOfApartment += 100;
        assertFalse(FloorService.isApartment(floor, numOfApartment), "There is no apartment with №" + numOfApartment + " but return true");
    }

    @Test
    @DisplayName("test getApartment method")
    void testGetApartment() {
        var apartments = floor.getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), FloorService.getApartment(floor, numOfApartment));

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), FloorService.getApartment(floor, numOfApartment));

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), FloorService.getApartment(floor, numOfApartment));

        numOfApartment += 100;
        assertNull(FloorService.getApartment(floor, numOfApartment));


        index = 0;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfApartment -= 100;
        assertNull(FloorService.getApartment(floor, numOfApartment));

    }

    private Floor createFloor(int floorID) {
        int numOfApartments = (int) (Math.random() * 10) + 2;
        List<Apartment> apartments = new ArrayList<>(numOfApartments);
        for (int i = 0; i < numOfApartments; ++i) {
            Apartment apartment = createApartment(floorID, i + 1);
            apartments.add(i, apartment);
        }
        return Floor.FloorBuilder.aFloor().
                withNumOfFloor(floorID).
                withApartments(apartments).
                build();

    }

    private Apartment createApartment(int floorID, int apartmentID) {
        int people = (int) (Math.random() * 5 + 5);
        double areaOfApartment = (Math.random() * 100) + 20;
        return Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * 100 + apartmentID).
                withNumOfPeople(people).
                withArea(areaOfApartment).
                build();

    }
}