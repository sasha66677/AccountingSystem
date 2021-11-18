package comRedkoAccountingSystem.house.model;

import comRedkoAccountingSystem.Service;
import comRedkoAccountingSystem.house.service.*;

import java.util.List;


public class House {
    private List<Floor> floors;
    private int numOfHouse;

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void setNumOfHouse(int numOfHouse) {
        this.numOfHouse = numOfHouse;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public double getArea() {
        return HouseService.countAreaHouse(this);
    }

    public int getNumPeoples() {
        return HouseService.countPeoples(this);
    }

    public int getNumOfHouse() {
        return numOfHouse;
    }

    public int getNumFloors() {
        return floors.size();
    }

    public void getAllInfo() {
        System.out.println("House №" + numOfHouse);
        for (int i = 0; i < floors.size(); ++i)
            floors.get(i).getAllInfo();
    }

    public void getInfo() {
        System.out.printf("\nHouse №%d\nNumber of floors: %d\nArea: %.2f, Peoples: %d\n",
                this.getNumOfHouse(), this.getNumFloors(), this.getArea(), this.getNumPeoples());
    }

    public static void compare(House x1, House x2) {
        x1.getInfo();
        x2.getInfo();
        System.out.println();

        if (x1.getNumFloors() > x2.getNumFloors())
            System.out.println("The height of the house №" + x1.numOfHouse + " is greater than the height of the house №" + x2.numOfHouse);
        else if (x1.getNumFloors() < x2.getNumFloors())
            System.out.println("The height of the house №" + x2.numOfHouse + " is greater than the height of the house №" + x1.numOfHouse);
        else
            System.out.println("The height of the house №" + x2.numOfHouse + " is equal to the height of the house №" + x1.numOfHouse);

        if (x1.getArea() > x2.getArea())
            System.out.println("The area of the house №" + x1.numOfHouse + " is larger than the area of the house №" + x2.numOfHouse);
        else if (x1.getArea() < x2.getArea())
            System.out.println("The area of the house №" + x2.numOfHouse + " is larger than the area of the house №" + x1.numOfHouse);
        else
            System.out.println("The area of the house №" + x1.numOfHouse + " is equal to the area of the house №" + x2.numOfHouse);


        if (x1.getNumPeoples() > x2.getNumPeoples())
            System.out.println("The number of people in the house №" + x1.numOfHouse + " is more than in the house №" + x2.numOfHouse);
        else if (x1.getNumPeoples() < x2.getNumPeoples())
            System.out.println("The number of people in the house №" + x2.numOfHouse + " is more than in the house №" + x1.numOfHouse);
        else
            System.out.println("The number of people in the house №" + x2.numOfHouse + " is equal to number of people in the house №" + x1.numOfHouse);
    }

    public boolean isApartment(int numApartment) {
        if (numApartment <= 100)/////////////////////////////////////////////////////////////
            return false;
        int floor = numApartment / 100 - 1;//////////////////////////////////////////////////////////Floor.minNumOfApartment - 1
        int index = numApartment % 10 - 1;
        if (floor >= floors.size() || index >= floors.get(floor).getNumApartments())
            return false;
        return true;
    }

    int getIndexApartment(int numApartment) {
        if (!isApartment(numApartment))
            return -1;
        return numApartment % 10 - 1;
    }

    public static void compare(House house1, int numApartment1, House house2, int numApartment2) {
        if (!house1.isApartment(numApartment1)) {
            System.out.println("\nApartment №" + numApartment1 + " in house №" + house1.getNumOfHouse() +
                    " isn't found");
            return;
        }

        if (!house2.isApartment(numApartment2)) {
            System.out.println("\nApartment №" + numApartment2 + " in house №" + house2.getNumOfHouse() +
                    " isn't found");
            return;
        }

        int floor = numApartment1 / 100 - 1;//////////////////////////////////////////////////////////Floor.minNumOfApartment - 1
        int index = house1.getIndexApartment(numApartment1);
        List<Floor> floors1 = house1.getFloors();
        List <Apartment> apartments1 = floors1.get(floor).getApartments();

        Apartment apartment1 = apartments1.get(index);

        index = house2.getIndexApartment(numApartment2);
        floor = numApartment2 / 100 - 1;//////////////////////////////////////////////////////////Floor.minNumOfApartment - 1
        List<Floor> floors2 = house2.getFloors();
        List <Apartment> apartments2 = floors2.get(floor).getApartments();

        Apartment apartment2 = apartments2.get(index);

        System.out.println();
        System.out.print("House №" + house1.numOfHouse + " ");
        apartment1.getAllInfo();
        System.out.print("House №" + house2.numOfHouse + " ");
        apartment2.getAllInfo();
        System.out.println();

        if (apartment1.getArea() > apartment2.getArea())
            System.out.println("The area of the apartment №" + numApartment1 + " (" + house1.numOfHouse + ')' +
                    " is larger than the area of the apartment №" + numApartment2 + " (" + house2.numOfHouse + ')');
        else if (apartment1.getArea() < apartment2.getArea())
            System.out.println("The area of the apartment №" + numApartment2 + " (" + house2.numOfHouse + ')' +
                    " is larger than the area of the apartment №" + numApartment1 + " (" + house1.numOfHouse + ')');
        else
            System.out.println("The area of the apartment №" + numApartment1 + " (" + house1.numOfHouse + ')' +
                    " is equal to the area of the apartment №" + numApartment2 + " (" + house2.numOfHouse + ')');

        if (apartment1.getNumOfPeoples() > apartment2.getNumOfPeoples())
            System.out.println("The number of people in the apartment №" + numApartment1 + " (" + house1.numOfHouse + ')' +
                    " is larger than number of people in №" + numApartment2 + " (" + house2.numOfHouse + ')');
        else if (apartment1.getNumOfPeoples() < apartment2.getNumOfPeoples())
            System.out.println("The number of people in the apartment №" + numApartment2 + " (" + house2.numOfHouse + ')' +
                    " is larger than the number of people in №" + numApartment1 + " (" + house1.numOfHouse + ')');
        else
            System.out.println("The number of people in the apartment №" + numApartment1 + " (" + house1.numOfHouse + ')' +
                    " is equal to the number of people in №" + numApartment2 + " (" + house2.numOfHouse + ')');
    }


    public void setPeople(int numApartment) {
        if (!isApartment(numApartment)) {
            System.out.println("\nApartment №" + numApartment + " in house №" + getNumOfHouse() +
                    " isn't found");
            return;
        }

        int floor = numApartment / 100 - 1;//////////////////////////////////////////////////////////Floor.minNumOfApartment - 1

        System.out.println("Number of people");
        int people = Service.inputInt();
        while (people < 0) {
            System.out.println("ERROR. Try again");
            System.out.println("Number of people");
            people = Service.inputInt();
        }

        int index = getIndexApartment(numApartment);
        floors.get(floor).setPeoplesApartment(index, people);
    }



    public static final class HouseBuilder {
        private List<Floor> floors;
        private int numOfHouse;

        private HouseBuilder(){}

        public static HouseBuilder aHouse(){
            return new House.HouseBuilder();
        }
        public HouseBuilder withFloors(List<Floor> floors){
            this.floors = floors;
            return this;
        }

        public HouseBuilder withNumOfHouse(int numOfHouse){
            this.numOfHouse = numOfHouse;
            return this;
        }

        public House build(){
            House house = new House();
            house.setNumOfHouse(numOfHouse);
            house.setFloors(floors);
            return house;
        }
    }
}

