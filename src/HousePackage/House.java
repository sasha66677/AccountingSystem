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

    public void getInfo() {
        System.out.println("House №" + number);
        for (int i = 0; i < floors.length; ++i)
            floors[i].getInfo();
    }


}
