package HousePackage;

class Apartment {
    private int number;
    private double area;
    private int peoples;

    public Apartment(int number, double area, int peoples){
        this.peoples = peoples;
        this.area = area;
        this.number = number;
    }

    protected void getInfo() {
        String str = String.format("%.2f", area);
        System.out.println("Number " + number + " Area " + str + " Peoples " + peoples);
    }
}
