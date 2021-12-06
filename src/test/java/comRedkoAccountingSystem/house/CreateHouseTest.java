package comRedkoAccountingSystem.house;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;
import comRedkoAccountingSystem.house.model.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateHouse class")
class CreateHouseTest {

    @Test
    @DisplayName("test rand method")
    void testRand() {
        House house1 = CreateHouse.rand();
        assertNotNull(house1, "Your object is NULL");


        House house2 = CreateHouse.rand();
        assertNotEquals(house1, house2, "Two the same random houses");


        List <Floor> floors = house1.getFloors();
        assertFalse(floors.isEmpty(), "There are no floors");
        while (!floors.isEmpty()) {
            Floor fl = floors.get(0);
            floors.remove(0);
            assertFalse(floors.contains(fl), "There are two the same floors");

            int numOfFloor = fl.getNumOfFloor();
            for (Floor x : floors)
                assertNotEquals(numOfFloor, x.getNumOfFloor(), "There are two floors with the same numbers");
        }

        floors = house1.getFloors();
        for (Floor fl : floors){
            List <Apartment> apartments = fl.getApartments();
            assertFalse(apartments.isEmpty(), "There are no apartments on the floor");
            while (!apartments.isEmpty()){
                Apartment apart = apartments.get(0);
                apartments.remove(0);
                assertFalse(apartments.contains(apart), "There are two the same apartments on the floor");

                int numOfApartment = apart.getNumOfApartment();
                for (Apartment x:apartments)
                    assertNotEquals(numOfApartment, x.getNumOfApartment(), "There are two apartments with the same numbers");
            }
        }
    }
}