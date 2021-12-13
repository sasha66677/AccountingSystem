package comRedkoAccountingSystem.house.services;

import comRedkoAccountingSystem.house.models.Apartment;
import comRedkoAccountingSystem.house.models.Floor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FloorService class")
class FloorServiceTest {

    private int numOfApartments;
    private double areaOfApartment;
    private int numOfPeopleFloor;
    private final Floor floor = createFloor();
    private List<Apartment> apartments;

    private Floor createFloor() {
        numOfPeopleFloor = 0;
        numOfApartments = (int) (Math.random() * 10) + 2;
        int numOfFloor = (int) (Math.random() * 10) + 2;
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
                withNumOfPeople(people).
                withArea(areaOfApartment).
                build();
    }

    @Test
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals((int) (areaOfApartment * numOfApartments * 1000), (int) (FloorService.getArea(floor) * 1000));
    }

    @Test
    @DisplayName("test getNumPeople method")
    void testGetNumPeople() {
        assertEquals(numOfPeopleFloor, FloorService.getNumPeople(floor));
    }

    @Test
    @DisplayName("test getNumApartments method")
    void testGetNumApartments() {
        assertEquals(apartments.size(), FloorService.getNumApartments(floor));
    }

}