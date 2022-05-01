package com.Redko.AccountingSystem.baseService;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.House;
import com.Redko.AccountingSystem.Storage;
import com.Redko.AccountingSystem.models.Floor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BaseService class")
class BaseServiceTest {
    private static Storage storage;

    @BeforeAll
    private static void setUp() {
        storage = new Storage();
        Map<Integer, House> houses;
        int numOfHouses = (int) (Math.random() * 10) + 3;
        houses = storage.getHouses();
        for (int i = 0; i < numOfHouses; ++i) {
            House house = createHouse();
            while (houses.containsKey(house.getNumOfHouse()))
                house = createHouse();

            houses.put(house.getNumOfHouse(), house);
        }

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

        var houses = storage.getHouses();
        BaseService base = BaseService.getBaseService();
        Map<Integer, House> houses1 = new HashMap<>();
        for (Map.Entry<Integer, House> item : houses.entrySet()) {
            int numOfHouse = item.getKey();
            var house = createHouse();
            house.setNumOfHouse(numOfHouse);
            houses1.put(numOfHouse, house);
            base.update(house);
        }

        storage = base.findAll();
        houses = storage.getHouses();
        for (Map.Entry<Integer, House> item : houses1.entrySet())
            assertTrue(houses.containsKey(item.getKey()));
    }

    @RepeatedTest(3)
    @DisplayName("test delete method")
    void delete() {
        BaseService base = BaseService.getBaseService();
        var houses = storage.getHouses();

        var house = createHouse();
        while (houses.containsKey(house.getNumOfHouse()))
            house = createHouse();

        base.save(house);
        base.delete(house);
        storage = base.findAll();
        houses = storage.getHouses();
        assertFalse(houses.containsKey(house.getNumOfHouse()));
    }

    @Test
    @DisplayName("test findById method")
    void findById() {
        BaseService base = BaseService.getBaseService();

        var houses = storage.getHouses();
        var house = createHouse();
        while (houses.containsKey(house.getNumOfHouse()))
            house = createHouse();

        base.save(house);
        storage = base.findAll();
        houses = storage.getHouses();
        assertTrue(houses.containsKey(house.getNumOfHouse()));
    }

    private static House createHouse() {
        List<Floor> floors;
        int numOfHouse = (int) (Math.random() * 10000) + 1;
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