package comRedkoAccountingSystem.interfaceOfSystem;

import comRedkoAccountingSystem.house.House;
import comRedkoAccountingSystem.Service;

import java.util.ArrayList;
import java.util.List;


public class AccountingSystem {
    private static List<House> houses = new ArrayList<House>();


    public static void open() {
        System.out.println("You are welcomed by the apartment accounting system");

        while (true) {
            System.out.println("\n\n-----MENU-----");
            System.out.println("1. Check houses");
            System.out.println("2. Compare houses");
            System.out.println("3. Compare apartments");
            System.out.println("4. Add houses");
            System.out.println("5. Notifications of house");

            int inVal = Service.inputInt();
            switch (inVal) {
                case 1:
                    checkHouses();
                    break;
                case 2:
                    compareHouses();
                    break;
                case 3:
                    compareApartments();
                    break;
                case 4:
                    addHouse();
                    break;
                case 5:
                    customizeHouse();
                    break;
                default:
                    System.out.println("ERROR. Try again");

            }
        }
    }


    private static int inputNumberHouse() {
        System.out.println("\nWrite a number of house: ");
        int number = Service.inputInt();
        if (number == -1)
            return -1;

        if (getIndexHouse(number) == -1) {
            System.out.println("There is no house with this number");
            return inputNumberHouse();
        }
        return number;
    }

    private static int inputNumberApartment(House house) {
        System.out.print("\nWrite a number of apartment in house №" + house.getNumber() + ": ");
        int numberApartment = Service.inputInt();

        if (numberApartment == -1)
            return -1;

        if (!house.isApartment(numberApartment)) {
            System.out.println("There is no apartment with this number");
            return inputNumberApartment(house);
        }
        return numberApartment;

    }

    private static void checkHouses() {
        System.out.println("--Check houses--");
        if (houses.size() == 0)
            System.out.println("There is no houses");
        else
            for (int i = 0; i < houses.size(); ++i) {
                houses.get(i).getInfo();
                System.out.println();
            }

    }

    private static int getIndexHouse(int number) {
        int index = 0;
        for (; index < houses.size(); ++index)
            if (houses.get(index).getNumber() == number)
                break;
        if (index == houses.size())
            return -1;
        return index;
    }

    private static void compareHouses() {
        if (houses.isEmpty()) {
            System.out.println("There is no houses");
            return;
        }

        System.out.print("write -1 to go menu\nHouses: ");
        for (int i = 0; i < houses.size(); ++i)
            System.out.print("№" + houses.get(i).getNumber() + " ");

        System.out.println();
        System.out.print("First house: ");
        int num1 = getIndexHouse(inputNumberHouse());
        if (num1 == -1)
            return;

        System.out.println();
        System.out.print("Second house: ");
        int num2 = getIndexHouse(inputNumberHouse());
        if (num2 == -1)
            return;

        House.compare(houses.get(num1), houses.get(num2));

    }

    private static void compareApartments() {
        if (houses.isEmpty()) {
            System.out.println("There is no houses");
            return;
        }

        System.out.print("write -1 to go menu\nHouses: ");
        for (int i = 0; i < houses.size(); ++i)
            System.out.print("№" + houses.get(i).getNumber() + " ");

        System.out.println();
        System.out.print("First house: ");
        int num1 = getIndexHouse(inputNumberHouse());
        if (num1 == -1)
            return;

        int apart1 = inputNumberApartment(houses.get(num1));
        if (apart1 == -1)
            return;

        System.out.println();
        System.out.print("Second house: ");
        int num2 = getIndexHouse(inputNumberHouse());
        if (num2 == -1)
            return;

        int apart2 = inputNumberApartment(houses.get(num2));
        if (apart2 == -1)
            return;

        House.compare(houses.get(num1), apart1, houses.get(num2), apart2);

    }


    private static void addHouse() {
        System.out.println("write -1 to go menu");
        System.out.println("---Add a house---");
        System.out.println("1. By random");
        System.out.println("2. By manual input");

        switch (Service.inputInt()) {
            case 1: {
                House x = new House();
                houses.add(x);
                x.getInfo();
            }
            break;
            case 2: {
                System.out.println("Number of house");
                int numberHouse = Service.inputInt();

                while (getIndexHouse(numberHouse) != -1) {
                    System.out.println("There is already a house with this number");
                    System.out.println("Number of house");
                    numberHouse = Service.inputInt();
                }

                System.out.println("Number of floors");
                int numFloors = Service.inputInt();
                while (numFloors < 0) {
                    System.out.println("Number of floors should be >= 0");
                    System.out.println("\nNumber of floors");
                    numFloors = Service.inputInt();
                }

                House x = new House(numberHouse, numFloors);
                houses.add(x);
                x.getInfo();
            }
            break;
            case -1:
                return;
            default:
                System.out.println("ERROR. Try again");
                addHouse();
                return;
        }
    }

    private static void customizeHouse() {
        if (houses.isEmpty()) {
            System.out.println("There is no houses");
            return;
        }

        System.out.print("write -1 to go menu\nHouses: ");
        for (int i = 0; i < houses.size(); ++i)
            System.out.print("№" + houses.get(i).getNumber() + " ");

        int index = inputNumberHouse();
        if (index == -1)
            return;
        index = getIndexHouse(index);
        houses.get(index).getAllInfo();


        while (true) {
            System.out.println("\n1. Set people in apartment");
            System.out.println("2. Delete");

            switch (Service.inputInt()) {
                case 1: {
                    int apartment = inputNumberApartment(houses.get(index));
                    houses.get(index).setPeople(apartment);
                }
                break;
                case 2:
                    houses.remove(index);
                    break;
                case -1:
                    customizeHouse();
                    return;
                default:
                    System.out.println("ERROR. Try again");
                    continue;
            }

            break;
        }


    }
}

