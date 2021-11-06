package Main;

import HousePackage.House;

public class Main{
    public static void main(String[] args) {
        var x = new House();

        x.getInfo();
        System.out.println("\n" + x.getNumber());
        System.out.print(x.getArea() + "   " + x.getNumPeoples());
    }

}
