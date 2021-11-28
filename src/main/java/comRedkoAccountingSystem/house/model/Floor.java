package comRedkoAccountingSystem.house.model;

import comRedkoAccountingSystem.house.service.*;

import java.util.List;

public class Floor {
    private int numOfFloor;
    private List<Apartment> apartments;

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
    public void setNumOfFloor(int numOfFloor) {
        this.numOfFloor = numOfFloor;
    }
    public List<Apartment> getApartments() {
        return apartments;
    }

    public double getArea() {
        return FloorService.countArea(this);
    }

    public int getNumPeoples() {
        return FloorService.countNumPeoples(this);
    }

    public int getNumApartments() {
        return apartments.size();
    }

    public int getNumOfFloor() {
        return numOfFloor;
    }

    public void getAllInfo() {
        System.out.println("Floor " + numOfFloor);
        for (int i = 0; i < apartments.size(); ++i)
            apartments.get(i).getAllInfo();
    }

    public void setPeoplesApartment(int indexApartment, int numPeoples) {
        if (indexApartment >= apartments.size() || indexApartment < 0)
            return;
        apartments.get(indexApartment).setNumOfPeoples(numPeoples);
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
