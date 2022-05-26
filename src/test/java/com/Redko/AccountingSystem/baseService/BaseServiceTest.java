package com.Redko.AccountingSystem.baseService;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.House;
import com.Redko.AccountingSystem.Storage;
import com.Redko.AccountingSystem.models.Floor;
import com.Redko.AccountingSystem.services.HouseService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BaseService class")
class BaseServiceTest {
    private static Storage storage;

    @BeforeAll
    private static void setUp() {
        storage = new Storage();
        var houses = storage.getHouses();
        int numOfHouses = (int) (Math.random() * 10) + 20;
        for (int i = 0; i < numOfHouses; ++i) {
            House house = createHouse();
            houses.put(house.getNumOfHouse(), house);
        }
        storage.setHouses(houses);
    }

    @Test
    @DisplayName("test save method")
    void save() {
        BaseService base = BaseService.getBaseService();
        var houses = storage.getHouses();
        for (Map.Entry<Integer, House> item : houses.entrySet())
            base.save(item.getValue());
    }

    @Test
    @DisplayName("test update method")
    void update() {
        BaseService base = BaseService.getBaseService();
        var house = createHouse();
        var house1 = createHouse();

        base.save(house);
        house1.setNumOfHouse(house.getNumOfHouse());
        base.update(house1);

        var houseInBase = base.findById(house1.getNumOfHouse());
        assertEquals(house1.getNumOfHouse(), houseInBase.getNumOfHouse());
        assertEquals(0, HouseService.compareArea(house1, houseInBase));
        assertEquals(0, HouseService.compareHeight(house1, houseInBase));
        assertEquals(0, HouseService.compareNumOfPeople(house1, houseInBase));

    }

    @Test
    @DisplayName("test delete method")
    void delete() {
        BaseService base = BaseService.getBaseService();
        var houses = storage.getHouses();

        var house = createHouse();
        while (houses.containsKey(house.getNumOfHouse()))
            house = createHouse();

        base.save(house);
        base.delete(house);
        house = base.findById(house.getNumOfHouse());
        assertNull(house);
    }

    @Test
    @DisplayName("test findById method")
    void findById() {
        BaseService base = BaseService.getBaseService();

        var house = createHouse();
        base.save(house);

        var houseInBase = base.findById(house.getNumOfHouse());
        assertEquals(house.getNumOfHouse(), houseInBase.getNumOfHouse());
        assertEquals(0, HouseService.compareArea(house, houseInBase));
        assertEquals(0, HouseService.compareHeight(house, houseInBase));
        assertEquals(0, HouseService.compareNumOfPeople(house, houseInBase));

    }

    private static House createHouse() {
        List<Floor> floors;
        int numOfHouse = (int) (Math.random() * 10000) + 1;
        Map<Integer, House> houses = storage.getHouses();
        while (houses.containsKey(numOfHouse))
            numOfHouse = (int) (Math.random() * 10000) + 1;

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