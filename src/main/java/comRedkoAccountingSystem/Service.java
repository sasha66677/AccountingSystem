package comRedkoAccountingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Service {
    public static int inputInt() {
        int inVal;
        try {
            Scanner in = new Scanner(System.in);
            inVal = in.nextInt();
        } catch (InputMismatchException x) {
            System.out.println("ERROR. Try again");
            return inputInt();
        }
        return inVal;
    }
    public static double inputDouble() {
        double inVal;
        try {
            Scanner in = new Scanner(System.in);
            inVal = in.nextDouble();
        } catch (InputMismatchException x) {
            System.out.println("ERROR. Try again");
            return inputDouble();
        }
        return inVal;
    }
    public static int getRandInt(int minVal, int maxVal){
        return (int) (Math.random() * (maxVal - minVal + 1) + minVal);
    }
    public static double getRandDouble(double minVal, double maxVal){
        return Math.random() * (maxVal - minVal) + minVal;
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
