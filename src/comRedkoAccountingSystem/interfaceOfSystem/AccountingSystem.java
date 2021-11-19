package comRedkoAccountingSystem.interfaceOfSystem;

import comRedkoAccountingSystem.house.Storage;
import comRedkoAccountingSystem.house.model.House;
import comRedkoAccountingSystem.Service;


public class AccountingSystem {

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

        if (!Storage.isHouse(number)) {
            System.out.println("There is no house with this number");
            return inputNumberHouse();
        }
        return number;
    }

    private static int inputNumberApartment(int numOfHouse) {
        House house = Storage.getHouse(numOfHouse);
        System.out.print("\nWrite a number of apartment in house №" + house.getNumOfHouse() + ": ");
        int numberApartment = Service.inputInt();

        if (numberApartment == -1)
            return -1;

        if (!house.isApartment(numberApartment)) {
            System.out.println("There is no apartment with this number");
            return inputNumberApartment(numOfHouse);
        }
        return numberApartment;

    }

    private static void checkHouses() {
        System.out.println("--Check houses--");
        Storage.getInfo();
    }

    private static void compareHouses() {
        if (Storage.getNumOfHouses() == 0) {
            System.out.println("There is no houses");
            return;
        }

        System.out.print("write -1 to go menu\nHouses: ");
        Storage.outputNumOfHouses();

        System.out.println();
        System.out.print("First house: ");
        int num1 = inputNumberHouse();
        if (num1 == -1)
            return;

        System.out.println();
        System.out.print("Second house: ");
        int num2 = inputNumberHouse();
        if (num2 == -1)
            return;

        Storage.compareHouses(num1, num2);

    }

    private static void compareApartments() {
        if (Storage.getNumOfHouses() == 0) {
            System.out.println("There is no houses");
            return;
        }

        System.out.print("write -1 to go menu\nHouses: ");
        Storage.outputNumOfHouses();

        System.out.println();
        System.out.print("First house: ");
        int num1 = inputNumberHouse();
        if (num1 == -1)
            return;

        int apart1 = inputNumberApartment(num1);
        if (apart1 == -1)
            return;

        System.out.println();
        System.out.print("Second house: ");
        int num2 = inputNumberHouse();
        if (num2 == -1)
            return;

        int apart2 = inputNumberApartment(num2);
        if (apart2 == -1)
            return;

        Storage.compareApartments(num1, apart1, num2, apart2);

    }


    private static void addHouse() {
        System.out.println("write -1 to go menu");
        System.out.println("---Add a house---");
        System.out.println("1. By random");
        System.out.println("2. By manual input");


        switch (Service.inputInt()) {
            case 1: {
                Storage.addHouseRand().getInfo();
            }
            break;
            case 2: {
                Storage.addHouseManual().getInfo();
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
        if (Storage.getNumOfHouses()==0) {
            System.out.println("There is no houses");
            return;
        }

        System.out.print("write -1 to go menu\nHouses: ");
        Storage.outputNumOfHouses();

        int numOfHouse = inputNumberHouse();
        if (numOfHouse == -1)
            return;
        Storage.getHouse(numOfHouse).getAllInfo();


        while (true) {
            System.out.println("\n1. Set people in apartment");
            System.out.println("2. Delete");

            switch (Service.inputInt()) {
                case 1: {
                    int numOfApartment = inputNumberApartment(numOfHouse);
                    if (numOfApartment == -1)
                        return;
                    System.out.println("Input number of people: ");
                    int numOfPeople = Service.inputInt();
                    while(numOfPeople < 0 && numOfPeople != -1){
                        System.out.println("Number of people should be >= 0");
                        System.out.println("\nNumber of people");
                        numOfPeople = Service.inputInt();
                    }
                    if (numOfPeople == -1)
                        return;

                    Storage.setPeople(numOfHouse, numOfApartment, numOfPeople);
                }
                break;
                case 2:
                    Storage.remove(numOfHouse);
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

