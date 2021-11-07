package InterfaceOfSystem;

import HousePackage.House;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountingSystem {
    private static Scanner in;
    private ArrayList<House> houses;

    public static void open() {
        in = new Scanner(System.in);
        System.out.println("You are welcomed by the apartment accounting system");
        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1. Check houses");
            System.out.println("2. Add houses");
            System.out.println("3. Notifications of house");

            int i = in.nextInt();
            switch (i) {
                case 1:
                    checkHouses();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("ERROR, Try again");
            }
        }
    }

    private static void checkHouses(){

    }

}
