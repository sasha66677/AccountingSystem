package comRedkoAccountingSystem.house.service;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;

import java.util.List;

public class FloorService {
    public static double countArea(Floor floor){
        List<Apartment> apartments = floor.getApartments();
        double area = 0;
        for (int i = 0; i < apartments.size(); ++i)
            area += apartments.get(i).getArea();
        return area;
    }
    public static int countNumPeoples(Floor floor) {
        List<Apartment> apartments = floor.getApartments();
        int numPeoples = 0;
        for (int i = 0; i < apartments.size(); ++i)
            numPeoples += apartments.get(i).getNumOfPeoples();
        return numPeoples;
    }
}
