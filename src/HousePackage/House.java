package HousePackage;

import comRedkoAccountingSystem.Service;


public class House {
    private static final int minNumFloor = 1;
    private int floorID = minNumFloor;
    private final Floor[] floors;
    private final int number;

    public House() {
        number = Service.getRandInt(1, 50);
        int numFloors = Service.getRandInt(1, 5);
        floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; ++i)
            floors[i] = new Floor(floorID++);
    }

    public House(int number, int numFloors) {
        this.number = number;
        floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; ++i) {
            System.out.println("\nFloor №" + (i + minNumFloor));
            System.out.println("Number of apartments");
            int numApartments = Service.inputInt();
            while (numApartments <= 0) {
                System.out.println("Number of apartments should be >= 0");
                System.out.println("Number of apartments");
                numApartments = Service.inputInt();
            }
            floors[i] = new Floor(floorID++, numApartments);
        }
    }

    public double getArea() {
        double area = 0;
        for (int i = 0; i < floors.length; ++i)
            area += floors[i].getArea();
        return area;
    }

    public int getNumPeoples() {
        int peoples = 0;
        for (int i = 0; i < floors.length; ++i)
            peoples += floors[i].getNumPeoples();
        return peoples;
    }

    public int getNumber() {
        return number;
    }

    public int getNumFloors() {
        return floors.length;
    }

    public void getAllInfo() {
        System.out.println("House №" + number);
        for (int i = 0; i < floors.length; ++i)
            floors[i].getAllInfo();
    }

    public void getInfo() {
        System.out.printf("\nHouse №%d\nNumber of floors: %d\nArea: %.2f, Peoples: %d\n",
                this.getNumber(), this.getNumFloors(), this.getArea(), this.getNumPeoples());
    }

    public static void compare(House x1, House x2) {
        x1.getInfo();
        x2.getInfo();
        System.out.println();

        if (x1.getNumFloors() > x2.getNumFloors())
            System.out.println("The height of the house №" + x1.number + " is greater than the height of the house №" + x2.number);
        else if (x1.getNumFloors() < x2.getNumFloors())
            System.out.println("The height of the house №" + x2.number + " is greater than the height of the house №" + x1.number);
        else
            System.out.println("The height of the house №" + x2.number + " is equal to the height of the house №" + x1.number);

        if (x1.getArea() > x2.getArea())
            System.out.println("The area of the house №" + x1.number + " is larger than the area of the house №" + x2.number);
        else if (x1.getArea() < x2.getArea())
            System.out.println("The area of the house №" + x2.number + " is larger than the area of the house №" + x1.number);
        else
            System.out.println("The area of the house №" + x1.number + " is equal to the area of the house №" + x2.number);


        if (x1.getNumPeoples() > x2.getNumPeoples())
            System.out.println("The number of people in the house №" + x1.number + " is more than in the house №" + x2.number);
        else if (x1.getNumPeoples() < x2.getNumPeoples())
            System.out.println("The number of people in the house №" + x2.number + " is more than in the house №" + x1.number);
        else
            System.out.println("The number of people in the house №" + x2.number + " is equal to number of people in the house №" + x1.number);
    }

    public boolean isApartment(int numApartment) {
        if (numApartment <= Floor.minNumApartment)
            return false;
        int floor = numApartment / Floor.minNumApartment - minNumFloor;
        int index = numApartment % 10 - 1;
        if (floor >= floors.length || index >= floors[floor].apartments.length)
            return false;
        return true;
    }

    public static void compare(House x1, int numApartment_1, House x2, int numApartment_2) {
        if (!x1.isApartment(numApartment_1)) {
            System.out.println("\nApartment №" + numApartment_1 + " in house №" + x1.getNumber() +
                      " isn't found");
            return;
        }
        int index1 = numApartment_1 % 10 - 1;;
        int floor1 = numApartment_1 / Floor.minNumApartment - minNumFloor;


        if (!x2.isApartment(numApartment_2)) {
            System.out.println("\nApartment №" + numApartment_2 + " in house №" + x2.getNumber() +
                     " isn't found");
            return;
        }
        int index2 = numApartment_2 % 10 - 1;
        int floor2 = numApartment_2 / Floor.minNumApartment - minNumFloor;

        System.out.println();
        System.out.print("House №" + x1.number + " ");
        x1.floors[floor1].apartments[index1].getAllInfo();
        System.out.print("House №" + x2.number + " ");
        x2.floors[floor2].apartments[index2].getAllInfo();
        System.out.println();

        if (x1.floors[floor1].apartments[index1].getArea() > x2.floors[floor2].apartments[index2].getArea())
            System.out.println("The area of the apartment №" + numApartment_1 + " (" + x1.number + ')' +
                    " is larger than the area of the apartment №" + numApartment_2 + " (" + x2.number + ')');
        else if (x1.floors[floor1].apartments[index1].getArea() < x2.floors[floor1].apartments[index2].getArea())
            System.out.println("The area of the apartment №" + numApartment_2 + " (" + x2.number + ')' +
                    " is larger than the area of the apartment №" + numApartment_1 + " (" + x1.number + ')');
        else
            System.out.println("The area of the apartment №" + numApartment_1 + " (" + x1.number + ')' +
                    " is equal to the area of the apartment №" + numApartment_2 + " (" + x2.number + ')');

        if (x1.floors[floor1].apartments[index1].getNumPeoples() > x2.floors[floor2].apartments[index2].getNumPeoples())
            System.out.println("The number of people in the apartment №" + numApartment_1 + " (" + x1.number + ')' +
                    " is larger than number of people in №" + numApartment_2 + " (" + x2.number + ')');
        else if (x1.floors[floor1].apartments[index1].getNumPeoples() < x2.floors[floor1].apartments[index2].getNumPeoples())
            System.out.println("The number of people in the apartment №" + numApartment_2 + " (" + x2.number + ')' +
                    " is larger than the number of people in №" + numApartment_1 + " (" + x1.number + ')');
        else
            System.out.println("The number of people in the apartment №" + numApartment_1 + " (" + x1.number + ')' +
                    " is equal to the number of people in №" + numApartment_2 + " (" + x2.number + ')');
    }
    public void setPeople(int numApartment){
        if (!isApartment(numApartment)) {
            System.out.println("\nApartment №" + numApartment + " in house №" + getNumber() +
                    " isn't found");
            return;
        }
        int index = numApartment % 10 - 1;;
        int floor = numApartment / Floor.minNumApartment - minNumFloor;

        System.out.println("Number of people");
        int people = Service.inputInt();
        while (people < 0) {
            System.out.println("ERROR. Try again");
            System.out.println("Number of people");
            people = Service.inputInt();
        }

        floors[floor].apartments[index].setPeoples(people);
    }
}

