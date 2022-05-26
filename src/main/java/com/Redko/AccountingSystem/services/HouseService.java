package com.Redko.AccountingSystem.services;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.House;
import com.Redko.AccountingSystem.models.Floor;

import java.util.List;

public class HouseService {
    public static double getArea(House house) {
        List<Floor> floors = house.getFloors();
        return floors.stream().
                mapToDouble(FloorService::getArea).
                sum();
    }

    public static int getNumOfPeople(House house) {
        List<Floor> floors = house.getFloors();
        return floors.stream().
                mapToInt(FloorService::getNumPeople).
                sum();
    }

    public static boolean isApartment(House house, int numOfApartment) {
        List<Floor> floors = house.getFloors();
        return floors.stream().
                anyMatch(floor -> FloorService.isApartment(floor, numOfApartment));
    }

    public static Apartment getApartment(House house, int numOfApartment) {
        List<Floor> floors = house.getFloors();
        Apartment apartment = null;
        for (Floor item : floors) {
            apartment = FloorService.getApartment(item, numOfApartment);
            if (apartment != null)
                break;
        }
        return apartment;
    }

    public static void setPeople(House house, int numOfApartment, int numOfPeople) {
        Apartment apartment = getApartment(house, numOfApartment);
        assert apartment != null;
        apartment.setNumOfPeople(numOfPeople);
    }

    public static int getNumOfFloors(House house) {
        List<Floor> floors = house.getFloors();
        return floors.size();
    }

    public static int compareArea(House house1, House house2) {
        double area1 = getArea(house1);
        double area2 = getArea(house2);
        return Double.compare(area1, area2);
    }

    public static int compareNumOfPeople(House house1, House house2) {
        int numOfPeople1 = getNumOfPeople(house1);
        int numOfPeople2 = getNumOfPeople(house2);
        return Integer.compare(numOfPeople1, numOfPeople2);
    }

    public static int compareHeight(House house1, House house2) {
        int numOfFloors1 = getNumOfFloors(house1);
        int numOfFloors2 = getNumOfFloors(house2);
        return Integer.compare(numOfFloors1, numOfFloors2);
    }

    public static int compareArea(House house1, int numOfApartment1, House house2, int numOfApartment2) {
        Apartment apartment1 = getApartment(house1, numOfApartment1);
        Apartment apartment2 = getApartment(house2, numOfApartment2);
        if (apartment1 == null || apartment2 == null) {
            if (apartment1 == null && apartment2 == null) {
                return 0;
            }
            if (apartment1 != null) {
                return 1;
            }
            return -1;
        }

        double area1 = apartment1.getArea();
        double area2 = apartment2.getArea();
        return Double.compare(area1, area2);
    }

    public static int compareNumOfPeople(House house1, int numOfApartment1, House house2, int numOfApartment2) {
        Apartment apartment1 = getApartment(house1, numOfApartment1);
        Apartment apartment2 = getApartment(house2, numOfApartment2);
        if (apartment1 == null || apartment2 == null) {
            if (apartment1 == null && apartment2 == null) {
                return 0;
            }
            if (apartment1 != null) {
                return 1;
            }
            return -1;
        }

        int numOfPeople1 = apartment1.getNumOfPeople();
        int numOfPeople2 = apartment2.getNumOfPeople();
        return Integer.compare(numOfPeople1, numOfPeople2);
    }

}
