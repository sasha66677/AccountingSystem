package HousePackage;

class Floor {
    private static int floorID = 1;
    private int apartmentID;//to control number of apartment
    private int number;
    private Apartment[] apartments;

    Floor() {
        int numApartments = (int) (Math.random()*10+1);
        number = floorID++;
        apartmentID = number*1000 + 1;
        apartments = new Apartment[numApartments];

        for (int i = 0; i < numApartments; ++i){
            int people = (int) (Math.random()*10);
            double area = Math.random()*100.0 + 50;
            var x = new Apartment(apartmentID++, area, people);
            apartments[i] = x;
        }
    }

    double getArea() {
        double area = 0;
        for (int i = 0; i < apartments.length; ++i)
            area += apartments[i].getArea();
        return area;
    }

    int getNumPeoples() {
        int peoples = 0;
        for (int i = 0; i < apartments.length; ++i)
            peoples += apartments[i].getNumPeoples();
        return peoples;
    }

    int getNumApartments(){
        return apartments.length;
    }

    int getNumber(){
        return  number;
    }

    void getAllInfo(){
        System.out.println ("Floor " + number);
        for (int i = 0; i < apartments.length; ++i)
            apartments[i].getAllInfo();
    }
}
