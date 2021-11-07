package HousePackage;

import java.util.InputMismatchException;
import java.util.Scanner;

class Floor {
    private int apartmentID;//to control number of apartment
    static final int minNumApartment = 100;
    private int number;
    Apartment[] apartments;

    Floor(int number) {
        int numApartments = (int) (Math.random()*10+1);
        this.number = number;
        apartmentID = number*minNumApartment + 1;
        apartments = new Apartment[numApartments];

        for (int i = 0; i < numApartments; ++i){
            int people = (int) (Math.random()*10);
            double area = Math.random()*100.0 + 50;
            var x = new Apartment(apartmentID++, area, people);
            apartments[i] = x;
        }
    }

    Floor(int number, int numApartments) {
        this.number = number;
        apartmentID = number*minNumApartment + 1;
        apartments = new Apartment[numApartments];

        for (int i = 0; i < numApartments; ++i){
            System.out.println("\nApartment №" + apartmentID);
            int people = 0;
            while (true) {
                try {
                    System.out.println("Number of people");
                    Scanner in = new Scanner(System.in);
                    people = in.nextInt();
                    if(people < 0){
                        System.out.println("ERROR. Try again");
                        continue;
                    }
                } catch (InputMismatchException x1) {
                    System.out.println("ERROR. Try again");
                    continue;
                }
                break;
            }


            double area = 0;
            while (true) {
                try {
                    System.out.println("Area");
                    Scanner in = new Scanner(System.in);
                    area = in.nextInt();
                    if(area < 0){
                        System.out.println("ERROR. Try again");
                        continue;
                    }
                } catch (InputMismatchException x1) {
                    System.out.println("ERROR. Try again");
                    continue;
                }
                break;
            }


            var x = new Apartment(apartmentID++, area, people);
            apartments[i] = x;
        }
    }

    double getArea() {
        double area = 0;
        for (int i = 0; i < apartments.length; ++i)
            area += apartments[i].getArea();
        return area;
    }

    int getNumPeoples() {
        int peoples = 0;
        for (int i = 0; i < apartments.length; ++i)
            peoples += apartments[i].getNumPeoples();
        return peoples;
    }

    int getNumApartments(){
        return apartments.length;
    }

    int getNumber(){
        return  number;
    }

    void getAllInfo(){
        System.out.println ("Floor " + number);
        for (int i = 0; i < apartments.length; ++i)
            apartments[i].getAllInfo();
    }
}
