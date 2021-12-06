package comRedkoAccountingSystem.house.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("House class")
class HouseTest {

    private int numOfPeopleHouse;
    private int numOfFloors;
    private List<Floor> floors;
    private int numOfHouse;
    private double areaOfHouse;
    private final House house = createHouse();

    private House createHouse() {
        numOfHouse = (int) (Math.random() * 100) + 1;
        numOfFloors = (int) (Math.random() * 5) + 5;
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
        int people = (int) (Math.random() * 5) + 5;
        numOfPeopleHouse += people;
        areaOfHouse += areaOfApartment;
        return Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * 100 + apartmentID).
                withNumOfPeoples(people).
                withArea(areaOfApartment).
                build();
    }

    @Test
    @DisplayName("test creating house")
    void testCreateHouse(){
        assertNotNull(house, "Your object is NULL");
    }

    @Test
    @DisplayName("test getFloors method")
    void testGetFloors() {
        assertEquals(floors, house.getFloors());
    }

    @Test
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals((int) (areaOfHouse * 1000), (int) (house.getArea() * 1000));
    }

    @Test
    @DisplayName("test getNumPeoples method")
    void testGetNumPeoples() {
        assertEquals(numOfPeopleHouse, house.getNumPeoples());
    }

    @Test
    @DisplayName("test getNumOfHouse method")
    void testGetNumOfHouse() {
        assertEquals(numOfHouse, house.getNumOfHouse());
    }

    @Test
    @DisplayName("test getNumFloors method")
    void testGetNumFloors() {
        assertEquals(numOfFloors, house.getNumFloors());
    }

    @RepeatedTest(3)
    @DisplayName("test setFloors method")
    void testSetFloors() {
        double areaOfApartment = Math.random() * 100 + 50;
        int numOfFloors = (int) (Math.random() * 5) + 5;
        int numOfApartments = (int) (Math.random() * 10) + 5;
        List<Floor> newFloors = new ArrayList<>(numOfFloors);
        for (int i = 1; i <= numOfFloors; ++i) {
            Floor floor = createFloor(i, areaOfApartment, numOfApartments);
            newFloors.add(i - 1, floor);
        }
        house.setFloors(newFloors);
        assertNotEquals(floors, house.getFloors(), "There are old floors");
        assertEquals(newFloors, house.getFloors(), "New floors not set");
        house.setFloors(floors);
    }

    @RepeatedTest(3)
    @DisplayName("test setNumOfHouse method")
    void testSetNumOfHouse() {
        numOfHouse = (int) (Math.random() * 100) + 1;
        house.setNumOfHouse(numOfHouse);
        assertEquals(numOfHouse, house.getNumOfHouse());
    }

    @RepeatedTest(3)
    @DisplayName("test isApartment method")
    void testIsApartment() {
        List<Apartment> apartments = floors.get(0).getApartments();
        int maxNumOfApartments = apartments.get(apartments.size() - 1).getNumOfApartment();
        for (int i = apartments.get(0).getNumOfApartment(); i <= maxNumOfApartments; ++i)
            assertTrue(house.isApartment(i), "There is apartment with №" + i + " but return false");

        apartments = floors.get(floors.size() - 1).getApartments();
        maxNumOfApartments = apartments.get(apartments.size() - 1).getNumOfApartment();
        for (int i = apartments.get(0).getNumOfApartment(); i <= maxNumOfApartments; ++i)
            assertTrue(house.isApartment(i), "There is apartment with №" + i + " but return false");

        for (int i = 1; i < 10; ++i)
            assertFalse(house.isApartment(maxNumOfApartments + i), "There is no apartment with №" + (maxNumOfApartments + i) + " but return true");
    }

    @RepeatedTest(3)
    @DisplayName("test setPeople method")
    void testSetPeople() {
        List<Apartment> apartments = floors.get(0).getApartments();
        for (Apartment apartment : apartments) {
            int numOfPeople = (int) (Math.random() * 100) + 20;
            int number = apartment.getNumOfApartment();
            house.setPeople(number, numOfPeople);
            assertEquals(numOfPeople, apartment.getNumOfPeoples());
        }
    }
}