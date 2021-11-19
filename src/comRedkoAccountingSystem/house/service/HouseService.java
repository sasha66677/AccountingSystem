package comRedkoAccountingSystem.house.service;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;
import comRedkoAccountingSystem.house.model.House;

import java.util.List;

public class HouseService {
    public static double countAreaHouse(House house) {
        List<Floor> floors = house.getFloors();
        double area = 0;
        for (int i = 0; i < floors.size(); ++i)
            area += floors.get(i).getArea();
        return area;
    }

    public static int countPeoples(House house) {
        List<Floor> floors = house.getFloors();
        int peoples = 0;
        for (int i = 0; i < floors.size(); ++i)
            peoples += floors.get(i).getNumPeoples();
        return peoples;
    }

    public static int getIndexApartment(House house,int numOfApartment) {
        if (numOfApartment <= 100)
            return -1;
        List<Floor> floors = house.getFloors();
        int numOfFloor = numOfApartment / 100 - 1;
        List<Apartment> apartments = floors.get(numOfFloor).getApartments();
        for (int i = 0; i < apartments.size(); ++i)
            if (apartments.get(i).getNumOfApartment() == numOfApartment)
                return i;
        return -1;
    }

    public static boolean isApartment(House house, int numOfApartment) {
        if (numOfApartment <= 100)
            return false;
        List<Floor> floors = house.getFloors();
        int numOfFloor = numOfApartment / 100 - 1;
        if (numOfFloor >= floors.size())
            return false;

        List<Apartment> apartments = floors.get(numOfFloor).getApartments();
        for (int i = 0; i < apartments.size(); ++i)
            if (apartments.get(i).getNumOfApartment() == numOfApartment)
                return true;
        return false;
    }

    public static void setPeople(House house, int numOfApartment, int numOfPeople) {
        if (!isApartment(house, numOfApartment)) {
            System.out.println("\nApartment №" + numOfApartment + " in house №" + house.getNumOfHouse() +
                    " isn't found");
            return;
        }

        int floor = numOfApartment / 100 - 1;
        int index = HouseService.getIndexApartment(house, numOfApartment);
        List<Floor> floors = house.getFloors();
        floors.get(floor).setPeoplesApartment(index, numOfPeople);
    }
}
