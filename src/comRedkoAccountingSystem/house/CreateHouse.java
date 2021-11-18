package comRedkoAccountingSystem.house;

import comRedkoAccountingSystem.Service;
import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;
import comRedkoAccountingSystem.house.model.House;

import java.util.ArrayList;
import java.util.List;

public class CreateHouse {
    private static int apartmentID;//to control number of apartment
    private static final int minNumOfApartment = 100;
    private static int floorID;


    private  static int numOfApartments;
    private  static double areaOfApartment;
    private static int numOfPeople;

    public static House rand(){
        floorID = 1;
        int numOfHouse = Service.getRandInt(1, 50);
        int numFloors = Service.getRandInt(1, 5);
        numOfApartments = Service.getRandInt(3, 10);
        areaOfApartment = Service.getRandDouble(50, 100);

        List<Floor> floors = new ArrayList<>();


        for (int i = 0; i < numFloors; ++i) {
            Floor floor = createFloor();
            floors.add(i, floor);
        }
        return House.HouseBuilder.aHouse().
                withNumOfHouse(numOfHouse).
                withFloors(floors).
                build();
    }

    public static House manual(){
        floorID = 1;
        System.out.println("Input number of house: ");
        int numOfHouse = Service.inputInt();

        System.out.println("Input number of floors: ");
        int numOfFloors = Service.inputInt();
        while (numOfFloors < 0) {
            System.out.println("Number of floors should be >= 0");
            System.out.println("\nNumber of floors");
            numOfFloors = Service.inputInt();
        }

        System.out.println("Input number of apartments on the floor: ");
        numOfApartments = Service.inputInt();
        while (numOfApartments < 0) {
            System.out.println("Number of apartments should be >= 0");
            System.out.println("\nNumber of apartments");
            numOfApartments = Service.inputInt();
        }

        System.out.println("Input area of the apartment: ");
        areaOfApartment = Service.inputDouble();
        while (areaOfApartment < 0) {
            System.out.println("Number of floors should be >= 0");
            System.out.println("\nNumber of floors");
            areaOfApartment = Service.inputInt();
        }

        List<Floor> floors = new ArrayList<>();

        for (int i = 0; i < numOfFloors; ++i) {
            Floor floor = createFloor();
            floors.add(i, floor);
        }
        return House.HouseBuilder.aHouse().
                withNumOfHouse(numOfHouse).
                withFloors(floors).
                build();
    }


    private static Floor createFloor(){
        List<Apartment> apartments = new ArrayList<>(numOfApartments);
        apartmentID = 1;
        for (int i = 0; i < numOfApartments; ++i) {
            Apartment apartment = createApartment();
            apartments.add(i, apartment);
        }
        return Floor.FloorBuilder.aFloor().
                withNumOfFloor(floorID++).
                withApartments(apartments).
                build();
    }

    private static Apartment createApartment(){
        numOfPeople = Service.getRandInt(0, 10);
        return  Apartment.ApartmentBuilder.aFlat().
                withNumOfApartment(floorID * minNumOfApartment + apartmentID++).
                withNumOfPeoples(numOfPeople).
                withArea(areaOfApartment).
                build();
    }


}
