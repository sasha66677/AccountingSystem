package HousePackage;

class Apartment {
    private int number;
    private double area;
    private int peoples;

    Apartment(int number, double area, int peoples){
        this.peoples = peoples;
        this.area = area;
        this.number = number;
    }

    double getArea() {
        return area;
    }

    int getNumPeoples() {
        return peoples;
    }

    int getNumber(){
        return  number;
    }

    void getAllInfo() {
        String str = String.format("%.2f", area);
        System.out.println("Number " + number + " Area " + str + " Peoples " + peoples);
    }
}
