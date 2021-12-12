package comRedkoAccountingSystem.house.service;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;

import java.util.List;

public class FloorService {

    public static double getArea(Floor floor){
        List<Apartment> apartments = floor.getApartments();
        double area = 0;
        for (Apartment apartment : apartments) area += apartment.getArea();
        return area;
    }

    public static int getNumPeople(Floor floor) {
        List<Apartment> apartments = floor.getApartments();
        int numPeople = 0;
        for (Apartment apartment : apartments) numPeople += apartment.getNumOfPeople();
        return numPeople;
    }

    public static int getNumApartments(Floor floor) {
        List<Apartment> apartments = floor.getApartments();
        return apartments.size();
    }

}
