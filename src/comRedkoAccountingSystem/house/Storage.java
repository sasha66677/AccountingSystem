package comRedkoAccountingSystem.house;

import comRedkoAccountingSystem.house.model.Apartment;
import comRedkoAccountingSystem.house.model.Floor;
import comRedkoAccountingSystem.house.model.House;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<House> houses = new ArrayList<House>();

    public static House addHouseRand() {
        House x = CreateHouse.rand();
        houses.add(x);
        return x;
    }

    public static House addHouseManual() {
        House x = CreateHouse.manual();
        houses.add(x);
        return x;
    }

    public static House getHouse(int numOfHouse){
        return houses.get(getIndexHouse(numOfHouse));
    }

    public static int getNumOfHouses(){
        return houses.size();
    }

    public static void getInfo(){
        if (Storage.getNumOfHouses() == 0)
            System.out.println("There is no houses");
        else
            for (int i = 0; i < houses.size(); ++i) {
                houses.get(i).getInfo();
                System.out.println();
            }
    }

    public static void outputNumOfHouses(){
        for (int i = 0; i < houses.size(); ++i)
            System.out.print("№" + houses.get(i).getNumOfHouse() + " ");
    }

    public static boolean isHouse(int numOfHouse) {
        for (int i = 0; i < houses.size(); ++i)
            if (houses.get(i).getNumOfHouse() == numOfHouse)
                return true;
        return false;
    }

    public static int getIndexHouse(int numOfHouse) {
        int index = 0;
        for (; index < houses.size(); ++index)
            if (houses.get(index).getNumOfHouse() == numOfHouse)
                break;
        if (index == houses.size())
            return -1;
        return index;
    }

    public static int getIndexApartment(int numOfHouse, int numApartment) {
        if (!Storage.getHouse(numOfHouse).isApartment(numApartment))
            return -1;
        return numApartment % 10 - 1;
    }

    public static void compareHouses(int numOfHouse1, int numOfHouse2) {
        House x1 = getHouse(numOfHouse1);
        House x2 = getHouse(numOfHouse2);
        x1.getInfo();
        x2.getInfo();
        System.out.println();

        if (x1.getNumFloors() > x2.getNumFloors())
            System.out.println("The height of the house №" + numOfHouse1 + " is greater than the height of the house №" + numOfHouse2);
        else if (x1.getNumFloors() < x2.getNumFloors())
            System.out.println("The height of the house №" + numOfHouse2 + " is greater than the height of the house №" + numOfHouse1);
        else
            System.out.println("The height of the house №" + numOfHouse1 + " is equal to the height of the house №" + numOfHouse2);

        if (x1.getArea() > x2.getArea())
            System.out.println("The area of the house №" + numOfHouse1 + " is larger than the area of the house №" + numOfHouse2);
        else if (x1.getArea() < x2.getArea())
            System.out.println("The area of the house №" + numOfHouse2 + " is larger than the area of the house №" + numOfHouse1);
        else
            System.out.println("The area of the house №" + numOfHouse1 + " is equal to the area of the house №" + numOfHouse2);


        if (x1.getNumPeoples() > x2.getNumPeoples())
            System.out.println("The number of people in the house №" + numOfHouse1 + " is more than in the house №" + numOfHouse2);
        else if (x1.getNumPeoples() < x2.getNumPeoples())
            System.out.println("The number of people in the house №" + numOfHouse2 + " is more than in the house №" + numOfHouse1);
        else
            System.out.println("The number of people in the house №" + numOfHouse1 + " is equal to number of people in the house №" + numOfHouse2);

    }

    public static void compareApartments(int numOfHouse1, int numOfApartment1, int numOfHouse2, int numOfApartment2) {
        House house1 = houses.get(getIndexHouse(numOfHouse1));
        House house2 = houses.get(getIndexHouse(numOfHouse2));

        int floor = numOfApartment1 / 100 - 1;
        int index = getIndexApartment(numOfHouse1, numOfApartment1);
        List<Floor> floors1 = house1.getFloors();
        List <Apartment> apartments1 = floors1.get(floor).getApartments();

        Apartment apartment1 = apartments1.get(index);

        index = getIndexApartment(numOfHouse2, numOfApartment2);
        floor = numOfApartment2 / 100 - 1;
        List<Floor> floors2 = house2.getFloors();
        List <Apartment> apartments2 = floors2.get(floor).getApartments();

        Apartment apartment2 = apartments2.get(index);

        System.out.println();
        System.out.print("House №" + numOfHouse1 + " ");
        apartment1.getAllInfo();
        System.out.print("House №" + numOfHouse2 + " ");
        apartment2.getAllInfo();
        System.out.println();

        if (apartment1.getArea() > apartment2.getArea())
            System.out.println("The area of the apartment №" + numOfApartment1 + " (" + numOfHouse1 + ')' +
                    " is larger than the area of the apartment №" + numOfApartment2 + " (" + numOfHouse2 + ')');
        else if (apartment1.getArea() < apartment2.getArea())
            System.out.println("The area of the apartment №" + numOfApartment2 + " (" + numOfHouse2 + ')' +
                    " is larger than the area of the apartment №" + numOfApartment1 + " (" + numOfHouse1 + ')');
        else
            System.out.println("The area of the apartment №" + numOfApartment1 + " (" + numOfHouse1 + ')' +
                    " is equal to the area of the apartment №" + numOfApartment2 + " (" + numOfHouse2 + ')');

        if (apartment1.getNumOfPeoples() > apartment2.getNumOfPeoples())
            System.out.println("The number of people in the apartment №" + numOfApartment1 + " (" + numOfHouse1 + ')' +
                    " is larger than number of people in №" + numOfApartment2 + " (" + numOfHouse2 + ')');
        else if (apartment1.getNumOfPeoples() < apartment2.getNumOfPeoples())
            System.out.println("The number of people in the apartment №" + numOfApartment2 + " (" + numOfHouse2 + ')' +
                    " is larger than the number of people in №" + numOfApartment1 + " (" + numOfHouse1 + ')');
        else
            System.out.println("The number of people in the apartment №" + numOfApartment1 + " (" + numOfHouse1 + ')' +
                    " is equal to the number of people in №" + numOfApartment2 + " (" + numOfHouse2 + ')');

    }

    public static void setPeople(int numOfHouse, int numOfApartment, int numOfPeople){
        getHouse(numOfHouse).setPeople(numOfApartment, numOfPeople);
    }

    public static void remove(int numOfHouse){
        int index = getIndexHouse(numOfHouse);
        houses.remove(index);
    }
}
