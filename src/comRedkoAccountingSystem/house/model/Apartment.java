package comRedkoAccountingSystem.house.model;

public class Apartment {
    private int numOfApartment;
    private double area;
    private int numOfPeoples;

    public double getArea() {
        return area;
    }

    public int getNumOfPeoples() {
        return numOfPeoples;
    }

    public int getNumOfApartment(){
        return numOfApartment;
    }

    public void setNumOfPeoples(int numOfPeoples){
        this.numOfPeoples = numOfPeoples;
    }

    public void setNumOfApartment(int numOfApartment) {
        this.numOfApartment = numOfApartment;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void getAllInfo() {
        System.out.printf("Apartment %d Area %.2f Peoples %d\n", numOfApartment,area, numOfPeoples);
    }

    public static final class ApartmentBuilder {
        private int numOfApartment;
        private double area;
        private int numOfPeoples;

        private ApartmentBuilder() {
        }

        public static ApartmentBuilder aFlat() {
            return new ApartmentBuilder();
        }

        public ApartmentBuilder withNumOfApartment(int numOfApartment){
            this.numOfApartment = numOfApartment;
            return this;
        }

        public ApartmentBuilder withArea(double area) {
            this.area = area;
            return this;
        }

        public ApartmentBuilder withNumOfPeoples(int numberOfPeoples) {
            this.numOfPeoples = numberOfPeoples;
            return this;
        }

        public Apartment build() {
            Apartment apartment = new Apartment();
            apartment.setNumOfApartment(numOfApartment);
            apartment.setArea(area);
            apartment.setNumOfPeoples(numOfPeoples);
            return apartment;
        }
    }
}

