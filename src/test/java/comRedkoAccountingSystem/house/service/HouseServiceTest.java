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
    private House house = createHouse();

    private House createHouse() {
        areaOfHouse = 0;
        numOfPeopleHouse = 0;
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
    @DisplayName("test getArea method")
    void testGetArea() {
        assertEquals((int) (areaOfHouse * 1000), (int) (HouseService.getArea(house) * 1000));
    }

    @Test
    @DisplayName("test getNumOfPeople method")
    void testGetNumOfPeople() {
        assertEquals(numOfPeopleHouse, HouseService.getNumOfPeople(house));
    }

    @Test
    @DisplayName("test getApartment method")
    void testGetApartment() {
        List<Apartment> apartments = floors.get(0).getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), HouseService.getApartment(house, numOfApartment));

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), HouseService.getApartment(house, numOfApartment));

        index = (int) (Math.random() * (apartments.size() - 1));
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), HouseService.getApartment(house, numOfApartment));

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), HouseService.getApartment(house, numOfApartment));

        index = 0;
        numOfApartment = apartments.get(index).getNumOfApartment() - 1;
        assertNull(HouseService.getApartment(house, numOfApartment));


        apartments = floors.get(floors.size() - 1).getApartments();
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), HouseService.getApartment(house, numOfApartment));

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        assertEquals(apartments.get(index), HouseService.getApartment(house, numOfApartment));

        numOfApartment += 100;
        assertNull(HouseService.getApartment(house, numOfApartment));

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

    @Test
    @DisplayName("test setPeople method")
    void testSetPeople() {
        List<Apartment> apartments = floors.get(0).getApartments();
        int index = 0;
        int numOfApartment = apartments.get(index).getNumOfApartment();
        int numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeople());

        index = apartments.size() / 2;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeople());

        index = (int) (Math.random() * (apartments.size() - 1));
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeople());

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeople());


        apartments = floors.get(floors.size() - 1).getApartments();
        index = 0;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeople());

        index = apartments.size() - 1;
        numOfApartment = apartments.get(index).getNumOfApartment();
        numOfPeople = (int) (Math.random() * 100 + 20);
        HouseService.setPeople(house, numOfApartment, numOfPeople);
        assertEquals(numOfPeople, apartments.get(index).getNumOfPeople());
    }

    @Test
    @DisplayName("test getNumOfFloors method")
    void testGetNumOfFloors() {
        assertEquals(floors.size(), HouseService.getNumOfFloors(house));
    }

    @RepeatedTest(3)
    @DisplayName("test compareArea for house method")
    void testCompareArea() {
        House house1 = house;
        assertEquals(0, HouseService.compareArea(house, house1));
        double area1 = HouseService.getArea(house1);
        house = createHouse();
        assertEquals(Double.compare(areaOfHouse, area1), HouseService.compareArea(house, house1));
    }

    @RepeatedTest(3)
    @DisplayName("test compareNumOfPeople for house method")
    void testCompareNumOfPeople() {
        House house1 = house;
        assertEquals(0, HouseService.compareNumOfPeople(house, house1));
        int numOfPeople1 = HouseService.getNumOfPeople(house1);
        house = createHouse();
        assertEquals(Integer.compare(numOfPeopleHouse, numOfPeople1), HouseService.compareNumOfPeople(house, house1));
    }

    @RepeatedTest(3)
    @DisplayName("test compareHeight for house method")
    void testCompareHeight() {
        House house1 = house;
        assertEquals(0, HouseService.compareHeight(house, house1));
        int numOfFloors1 = HouseService.getNumOfFloors(house1);
        house = createHouse();
        assertEquals(Integer.compare(floors.size(), numOfFloors1), HouseService.compareHeight(house, house1));
    }

    @RepeatedTest(3)
    @DisplayName("test compareArea for apartment method")
    void testCompareAreaApartment() {
        var apartments = floors.get(0).getApartments();

        House house1 = house;
        var floors1 = house1.getFloors();
        var apartments1 = floors1.get(0).getApartments();

        int num = apartments.get(0).getNumOfApartment();
        int num1 = apartments1.get(0).getNumOfApartment();
        assertEquals(0, HouseService.compareArea(house, num, house1, num1));

        num = apartments.get(apartments.size() - 1).getNumOfApartment();
        num1 = apartments1.get(apartments1.size() - 1).getNumOfApartment();
        assertEquals(0, HouseService.compareArea(house, num, house1, num1));

        apartments = floors.get(floors.size() - 1).getApartments();
        apartments1 = house1.getFloors().get(floors1.size() - 1).getApartments();
        num = apartments.get(apartments.size() - 1).getNumOfApartment();
        num1 = apartments1.get(apartments1.size() - 1).getNumOfApartment();
        assertEquals(0, HouseService.compareArea(house, num, house1, num1));

        house = createHouse();

        apartments = floors.get(floors.size() / 2).getApartments();
        apartments1 = floors1.get(floors1.size() / 2).getApartments();

        double area = apartments.get(apartments.size() / 2).getArea();
        double area1 = apartments1.get(apartments1.size() / 2).getArea();
        num = apartments.get(apartments.size() / 2).getNumOfApartment();
        num1 = apartments1.get(apartments1.size() / 2).getNumOfApartment();
        assertEquals(Double.compare(area, area1), HouseService.compareArea(house, num, house1, num1));
    }

    @RepeatedTest(3)
    @DisplayName("test compareNumOfPeople for apartment method")
    void testCompareNumOfPeopleApartment() {
        var apartments = floors.get(0).getApartments();

        House house1 = house;
        var floors1 = house1.getFloors();
        var apartments1 = floors1.get(0).getApartments();

        int num = apartments.get(0).getNumOfApartment();
        int num1 = apartments1.get(0).getNumOfApartment();
        assertEquals(0, HouseService.compareNumOfPeople(house, num, house1, num1));

        num = apartments.get(apartments.size() - 1).getNumOfApartment();
        num1 = apartments1.get(apartments1.size() - 1).getNumOfApartment();
        assertEquals(0, HouseService.compareNumOfPeople(house, num, house1, num1));

        apartments = floors.get(floors.size() - 1).getApartments();
        apartments1 = house1.getFloors().get(floors1.size() - 1).getApartments();
        num = apartments.get(apartments.size() - 1).getNumOfApartment();
        num1 = apartments1.get(apartments1.size() - 1).getNumOfApartment();
        assertEquals(0, HouseService.compareNumOfPeople(house, num, house1, num1));

        house = createHouse();

        apartments = floors.get(floors.size() / 2).getApartments();
        apartments1 = floors1.get(floors1.size() / 2).getApartments();

        int numOfPeople = apartments.get(apartments.size() / 2).getNumOfPeople();
        int numOfPeople1 = apartments1.get(apartments1.size() / 2).getNumOfPeople();
        num = apartments.get(apartments.size() / 2).getNumOfApartment();
        num1 = apartments1.get(apartments1.size() / 2).getNumOfApartment();
        assertEquals(Double.compare(numOfPeople, numOfPeople1), HouseService.compareNumOfPeople(house, num, house1, num1));
    }

}