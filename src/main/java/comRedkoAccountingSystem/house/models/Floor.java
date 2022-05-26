package comRedkoAccountingSystem.house.models;

import java.util.List;
import java.util.Objects;

public class Floor {
    private int numOfFloor;
    private List<Apartment> apartments;

    public Floor(){}

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public void setNumOfFloor(int numOfFloor) {
        this.numOfFloor = numOfFloor;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public int getNumOfFloor() {
        return numOfFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Floor)) return false;
        Floor floor = (Floor) o;
        return Objects.equals(apartments, floor.apartments);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Floor №" + numOfFloor + '\n');
        for (Apartment apartment : apartments)
            str.append(apartment.toString()).append('\n');
        if (!apartments.isEmpty())
            str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    public static final class FloorBuilder {
        private int numOfFloor;
        private List<Apartment> apartments;

        private FloorBuilder(){}

        public static FloorBuilder aFloor(){
            return new FloorBuilder();
        }

        public FloorBuilder withNumOfFloor(int numOfFloor){
            this.numOfFloor = numOfFloor;
            return this;
        }

        public FloorBuilder withApartments(List<Apartment> apartments){
            this.apartments = apartments;
            return this;
        }

        public Floor build(){
            Floor floor = new Floor();
            floor.setApartments(apartments);
            floor.setNumOfFloor(numOfFloor);
            return floor;
        }

    }

}
