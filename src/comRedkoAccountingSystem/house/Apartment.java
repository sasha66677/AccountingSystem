package comRedkoAccountingSystem.house;

class Apartment {
    private final int number;
    private final double area;
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
        System.out.printf("Apartment %d Area %.2f Peoples %d\n",number,area, peoples);
    }
    void setPeoples(int numPeoples){
        peoples = numPeoples;
    }
}

