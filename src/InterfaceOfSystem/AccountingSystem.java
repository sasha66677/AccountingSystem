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
                    break;
                case 3:

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

    private static void addHouse() {
        System.out.println("---Add a house---");
        System.out.println("1. By random");
        System.out.println("2. By manual input");
        int inVal = 0;
        try {
            Scanner in = new Scanner(System.in);
            inVal = in.nextInt();
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