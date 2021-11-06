package HousePackage;

class Floor {
    private static int floorID = 1;
    private int apartmentID;
    private int number;
    private Apartment[] apartments;

    Floor() {
        int numApartments = (int) (Math.random()*30+1);
        number = floorID++;
        apartmentID = number*1000 + 1;
        apartments = new Apartment[numApartments];

        for (int i = 0; i < numApartments; ++i){
            int people = (int) (Math.random()*10);
            double area = (double) (Math.random()*1000/10.0 + 50);
            var x = new Apartment(apartmentID++, area, people);
            apartments[i] = x;
        }
    }
    void getInfo(){
        System.out.println ("Floor " + number);
        for (int i = 0; i < apartments.length; ++i)
            apartments[i].getInfo();
    }
}
