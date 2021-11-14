package HousePackage;

import comRedkoAccountingSystem.Service ;

class Floor {
    private int apartmentID;//to control number of apartment
    static final int minNumApartment = 100;
    private final int number;
    Apartment[] apartments;

    Floor(int number) {
        int numApartments = Service.getRandInt(1, 10);
        this.number = number;
        apartmentID = number*minNumApartment + 1;
        apartments = new Apartment[numApartments];

        for (int i = 0; i < numApartments; ++i){
            int people = Service.getRandInt(0, 10);
            double area = Service.getRandDouble(50, 100);
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
