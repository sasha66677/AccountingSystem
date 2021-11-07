package InterfaceOfSystem;

import HousePackage.House;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountingSystem {
    private static ArrayList<House> houses = new ArrayList<House>();

    public static void open() {
        System.out.println("You are welcomed by the apartment accounting system");

        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1. Check houses");
            System.out.println("2. Compare houses");
            System.out.println("3. Compare apartments");
            System.out.println("4. Add houses");
            System.out.println("5. Notifications of house");

            int inVal = 0;
            try {
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();
            } catch (InputMismatchException x) {
                inVal = -1;
            }
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

                    break;
                default:
                    System.out.println("ERROR. Try again");

            }
        }
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

    private static int findHouse(int number) {
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

        int inVal = 0;
        while (true) {
            try {
                System.out.print("\nWrite a number of first house: ");
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();

                if (inVal == -1)
                    return;

                if (findHouse(inVal) == -1)
                    throw new MyException("There is no house with this number\n");
            } catch (InputMismatchException x1) {
                System.out.println("ERROR. Try again");
                continue;
            } catch (MyException e) {
                System.out.println(e.getException());
                continue;
            }
            break;
        }
        int num1 = findHouse(inVal);

        inVal = 0;
        while (true) {
            try {
                System.out.print("\nWrite a number of second house: ");
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();

                if (inVal == -1)
                    return;

                if (findHouse(inVal) == -1)
                    throw new MyException("There is no house with this number\n");
            } catch (InputMismatchException x1) {
                System.out.println("ERROR. Try again");
                continue;
            } catch (MyException e) {
                System.out.println(e.getException());
                continue;
            }
            break;
        }
        int num2 = findHouse(inVal);

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

        int inVal;
        while (true) {
            try {
                System.out.print("\nWrite a number of first house: ");
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();

                if (inVal == -1)
                    return;

                if (findHouse(inVal) == -1)
                    throw new MyException("There is no house with this number\n");
            } catch (InputMismatchException x1) {
                System.out.println("ERROR. Try again");
                continue;
            } catch (MyException e) {
                System.out.println(e.getException());
                continue;
            }
            break;
        }
        int num1 = findHouse(inVal);


        while (true) {
            try {
                System.out.print("\nWrite a number of apartment: ");
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();

                if (inVal == -1)
                    return;

                if (!houses.get(num1).isApartment(inVal))
                    throw new MyException("There is no apartment with this number\n");
            } catch (InputMismatchException x1) {
                System.out.println("ERROR. Try again");
                continue;
            } catch (MyException e) {
                System.out.println(e.getException());
                continue;
            }
            break;
        }
        int apart1 = inVal;



        while (true) {
            try {
                System.out.print("\nWrite a number of second house: ");
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();

                if (inVal == -1)
                    return;

                if (findHouse(inVal) == -1)
                    throw new MyException("There is no house with this number\n");
            } catch (InputMismatchException x1) {
                System.out.println("ERROR. Try again");
                continue;
            } catch (MyException e) {
                System.out.println(e.getException());
                continue;
            }
            break;
        }
        int num2 = findHouse(inVal);


        while (true) {
            try {
                System.out.print("\nWrite a number of apartment: ");
                Scanner in = new Scanner(System.in);
                inVal = in.nextInt();

                if (inVal == -1)
                    return;

                if (!houses.get(num2).isApartment(inVal))
                    throw new MyException("There is no apartment with this number\n");
            } catch (InputMismatchException x1) {
                System.out.println("ERROR. Try again");
                continue;
            } catch (MyException e) {
                System.out.println(e.getException());
                continue;
            }
            break;
        }
        int apart2 = inVal;

        House.compare(houses.get(num1), apart1, houses.get(num2), apart2);

    }


    private static void addHouse() {
        System.out.println("write -1 to go menu");
        System.out.println("---Add a house---");
        System.out.println("1. By random");
        System.out.println("2. By manual input");
        int inVal = 0;
        try {
            Scanner in = new Scanner(System.in);
            inVal = in.nextInt();
            if (inVal == -1)
                return;
        } catch (InputMismatchException x) {
            inVal = -1;
        }

        switch (inVal) {
            case 1: {
                House x = new House();
                houses.add(x);
                x.getInfo();
            }
            break;
            case 2: {
                int numberHouse = 0;
                while (true) {
                    try {
                        System.out.println("Number of house");
                        Scanner in = new Scanner(System.in);
                        numberHouse = in.nextInt();
                        if (findHouse(numberHouse) != -1)
                            throw new MyException("There is already a house with this number\n");
                    } catch (InputMismatchException x1) {
                        System.out.println("ERROR. Try again");
                        continue;
                    } catch (MyException e) {
                        System.out.println(e.getException());
                        continue;
                    }
                    break;
                }

                int numFloors = 0;
                while (true) {
                    try {
                        System.out.println("Number of floors");
                        Scanner in = new Scanner(System.in);
                        numFloors = in.nextInt();
                    } catch (InputMismatchException x1) {
                        System.out.println("ERROR. Try again");
                        continue;
                    }
                    break;
                }

                House x = new House(numberHouse, numFloors);
                houses.add(x);
                x.getInfo();
            }
            break;
            default:
                System.out.println("ERROR. Try again");
                addHouse();
                break;
        }
    }


}



class MyException extends Exception{
    String exception;
    MyException(String exception){
        this.exception = exception;
    }
    String getException (){
        return exception;
    }

}