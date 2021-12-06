package comRedkoAccountingSystem.house.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Apartment class")
class ApartmentTest {
    private double area = Math.random() * (3000 - 1) + 1;
    private int numOfPeoples = (int) (Math.random() * 3000) + 1;
    private int numOfApartment = (int) (Math.random() * 3000) + 1;
    private final Apartment apartment = Apartment.ApartmentBuilder.aFlat().
            withArea(area).
            withNumOfPeoples(numOfPeoples).
            withNumOfApartment(numOfApartment).
            build();

    @Test
    @DisplayName("test creating apartment")
    void testCreateApartment(){
        assertNotNull(apartment, "Your object is null");
    }

    @Test
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals(area, apartment.getArea());
    }

    @Test
    @DisplayName("test getNumOfPeoples method")
    void testGetNumOfPeoples() {
        assertEquals(numOfPeoples, apartment.getNumOfPeoples());
    }

    @Test
    @DisplayName("test getNumOfApartment method")
    void testGetNumOfApartment() {
        assertEquals(numOfApartment, apartment.getNumOfApartment());
    }

    @RepeatedTest(3)
    @DisplayName("test setNumOfPeoples method")
    void testSetNumOfPeoples() {
        numOfPeoples = (int) (Math.random() * 3000) + 1;
        apartment.setNumOfPeoples(numOfPeoples);
        assertEquals(numOfPeoples, apartment.getNumOfPeoples());
    }

    @RepeatedTest(3)
    @DisplayName("test setNumOfApartment method")
    void testSetNumOfApartment() {
        numOfApartment = (int) (Math.random() * 3000) + 1;
        apartment.setNumOfApartment(numOfApartment);
        assertEquals(numOfApartment, apartment.getNumOfApartment());
    }

    @RepeatedTest(3)
    @DisplayName("test setAreaApartment method")
    void testSetAreaApartment() {
        area = Math.random() * 3000 + 1;
        apartment.setArea(area);
        assertEquals(area, apartment.getArea());
    }

}