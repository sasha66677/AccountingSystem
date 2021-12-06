package comRedkoAccountingSystem.house.service;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;
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

    private Floor createFloor() {
        numOfApartments = (int) (Math.random() * 10) + 2;
        int numOfFloor = (int) (Math.random() * 10) + 2;
        areaOfApartment = (Math.random() * 100) + 20;
        List<Apartment> apartments = new ArrayList<>(numOfApartments);
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
    @DisplayName("test countArea method")
    void testCountArea() {
        assertEquals((int) (areaOfApartment * numOfApartments * 1000), (int) (FloorService.countArea(floor) * 1000));
    }

    @Test
    @DisplayName("test countNumPeople method")
    void testCountNumPeople() {
        assertEquals(numOfPeopleFloor, FloorService.countNumPeoples(floor));
    }
}