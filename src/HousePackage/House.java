package HousePackage;

public class House {
    private Floor[] floors;
    private int number;
    public House() {
        number = (int) (Math.random() * 50);
        int numFloors = (int) (Math.random() * 5 + 1);
        floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; ++i)
            floors[i] = new Floor();
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
        return floors.length + 1;
    }

    public void getAllInfo() {
        System.out.println("House №" + number);
        for (int i = 0; i < floors.length; ++i)
            floors[i].getAllInfo();
    }
public void getInfo(){
    String str = String.format("%.2f", this.getArea());
    System.out.println("\nNumber : " + this.getNumber() + "\nNumber of floors: " + this.getNumFloors() +
            "\nArea: " + str + ", Peoples: " + this.getNumPeoples());
}
    static public void compare(House x1, House x2) {
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
}
