package comRedkoAccountingSystem.house.service;

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

}
