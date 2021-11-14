package comRedkoAccountingSystem.house;

import comRedkoAccountingSystem.Service ;

import java.util.ArrayList;
import java.util.List;

class Floor {
    private int apartmentID;//to control number of apartment
    static final int minNumApartment = 100;
    private final int numberFloor;

   private List <Apartment> apartments;

    Floor(int numberFloor) {
        int numApartments = Service.getRandInt(1, 10);
        this.numberFloor = numberFloor;
        apartmentID = numberFloor*minNumApartment + 1;
        apartments = new ArrayList<>();

        for (int i = 0; i < numApartments; ++i){
            int people = Service.getRandInt(0, 10);
            double area = Service.getRandDouble(50, 100);
            var x = new Apartment(apartmentID++, area, people);
            apartments.add(i, x);
        }
    }

    Floor(int numberFloor, int numApartments) {
        this.numberFloor = numberFloor;
        apartmentID = numberFloor*minNumApartment + 1;
        apartments = new ArrayList<>();

        for (int i = 0; i < numApartments; ++i){
            System.out.println("\nApartment №" + apartmentID);

            System.out.println("Number of people");
            int people = Service.inputInt();
            while (people < 0){
                System.out.println("ERROR. Try again");
                System.out.println("Number of people");
                people = Service.inputInt();
            }

            System.out.println("Area");
            double area = Service.inputDouble();
            while (area < 0){
                System.out.println("ERROR. Try again");
                System.out.println("Area");
                area = Service.inputDouble();
            }


            var x = new Apartment(apartmentID++, area, people);
            apartments.add(i, x);
        }
    }

    double getArea() {
        double area = 0;
        for (int i = 0; i < apartments.size(); ++i)
            area += apartments.get(i).getArea();
        return area;
    }

    double getArea(int numApartment) {
        int index = numApartment % 10 - 1;
        return apartments.get(index).getArea();
    }

    int getNumPeoples() {
        int peoples = 0;
        for (int i = 0; i < apartments.size(); ++i)
            peoples += apartments.get(i).getNumPeoples();
        return peoples;
    }

    int getNumPeoples(int numApartment){
        int index = numApartment % 10 - 1;
        return apartments.get(index).getNumPeoples();
    }

    int getNumApartments(){
        return apartments.size();
    }

    int getNumberFloor(){
        return  numberFloor;
    }

    void getAllInfo(){
        System.out.println ("Floor " + numberFloor);
        for (int i = 0; i < apartments.size(); ++i)
            apartments.get(i).getAllInfo();
    }
    void getAllInfo(int numApartment){
        System.out.println ("Floor " + numberFloor);
        int index = numApartment % 10 - 1;
        apartments.get(index).getAllInfo();
    }

    void setPeoples(int numApartment, int numPeoples){
        int index = numApartment % 10 - 1;
        apartments.get(index).setPeoples(numPeoples);
    }
}
