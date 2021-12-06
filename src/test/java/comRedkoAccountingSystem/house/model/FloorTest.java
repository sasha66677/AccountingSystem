package comRedkoAccountingSystem.house.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Floor class")
class FloorTest {
    private int numOfApartments;
    private double areaOfApartment;
    private int numOfPeopleFloor;
    private List<Apartment> apartments;
    private int numOfFloor;
    private final Floor floor = createFloor();

    private Floor createFloor() {
        numOfApartments = (int) (Math.random() * 10) + 2;
        numOfFloor = (int) (Math.random() * 10) + 2;
        areaOfApartment = (Math.random() * 100) + 20;
        apartments = new ArrayList<>(numOfApartments);
        int apartmentID = 1;
        for (int i = 0; i < numOfApartments; ++i) {
            Apartment apartment = createApartment(numOfFloor, apartmentID++);
            apartments.add(i, apartment);
        }
        return Floor.FloorBuilder.aFloor().
                withNumOfFloor(numOfFloor).
                withApartments(apartments).
                build();
    }

    private Apartment createApartment(int floorID, int apartmentID) {
        int people = (int) (Math.random() * 5 + 5);
        numOfPeopleFloor += people;
        return Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * 100 + apartmentID).
                withNumOfPeoples(people).
                withArea(areaOfApartment).
                build();
    }

    @Test
    @DisplayName("test creating floor")
    void testCreateApartment(){
        assertNotNull(floor, "Your object is null");
    }

    @Test
    @DisplayName("test getApartments method")
    void testGetApartments() {
        assertEquals(apartments, floor.getApartments());
    }

    @Test
    @DisplayName("test getNumOfFloor method")
    void testGetNumOfFloor() {
        assertEquals(numOfFloor, floor.getNumOfFloor());
    }

    @Test
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals((int) (areaOfApartment * numOfApartments * 1000), (int) (floor.getArea() * 1000));
    }

    @Test
    @DisplayName("test getNumPeoples method")
    void testGetNumPeoples() {
        assertEquals(numOfPeopleFloor, floor.getNumPeoples());
    }

    @Test
    @DisplayName("test getNumApartments method")
    void testGetNumApartments() {
        assertEquals(numOfApartments, floor.getNumApartments());
    }

    @RepeatedTest(3)
    @DisplayName("test setApartments method")
    void testSetApartments() {
        List<Apartment> apartments = new ArrayList<>(numOfApartments);
        numOfApartments = (int) (Math.random() * 10) + 2;
        areaOfApartment = (Math.random() * 100) + 20;
        for (int i = 0; i < numOfApartments; ++i) {
            Apartment apartment = createApartment(numOfFloor, i+1);
            apartments.add(i, apartment);
        }
        floor.setApartments(apartments);
        assertNotEquals(this.apartments, floor.getApartments(), "There are old apartments");
        assertEquals(apartments, floor.getApartments(), "New apartments not set");
        this.apartments = apartments;
    }

    @RepeatedTest(3)
    @DisplayName("test setNumOfFloor method")
    void testSetNumberFloor() {
        int numOfFloor = (int) (Math.random() * 3000) + 1;
        Floor floor = Floor.FloorBuilder.aFloor().build();
        floor.setNumOfFloor(numOfFloor);
        assertEquals(numOfFloor, floor.getNumOfFloor());
    }

    @RepeatedTest(3)
    @DisplayName("test setPeopleApartment method")
    void testSetPeoplesApartment() {
        int numOfPeople = (int) (Math.random() * 10) + 2;
        int index = 0;
        floor.setPeoplesApartment(index, numOfPeople);
        apartments = floor.getApartments();
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        numOfPeople = (int) (Math.random() * 10) + 2;
        index = numOfApartments / 2;
        floor.setPeoplesApartment(index, numOfPeople);
        apartments = floor.getApartments();
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        numOfPeople = (int) (Math.random() * 10) + 2;
        index = (int) (Math.random() * (apartments.size() - 1));
        floor.setPeoplesApartment(index, numOfPeople);
        apartments = floor.getApartments();
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());

        numOfPeople = (int) (Math.random() * 10) + 2;
        index = numOfApartments - 1;
        floor.setPeoplesApartment(index, numOfPeople);
        apartments = floor.getApartments();
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeoples());
    }
}