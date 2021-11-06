package Main;

import HousePackage.House;

public class Main{
    public static void main(String[] args) {
        var x = new House();
        x.getAllInfo();

        System.out.println();
        var x1 = new House();
        x1.getAllInfo();
        House.compare(x,101, x1, 102);
        //x1.getAllInfo();
        //House.compare(x, x1);

    }

}
