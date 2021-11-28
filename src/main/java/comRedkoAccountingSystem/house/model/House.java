package comRedkoAccountingSystem.house.model;

import comRedkoAccountingSystem.house.service.*;

import java.util.List;


public class House {
    private List<Floor> floors;
    private int numOfHouse;

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void setNumOfHouse(int numOfHouse) {
        this.numOfHouse = numOfHouse;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public double getArea() {
        return HouseService.countAreaHouse(this);
    }

    public int getNumPeoples() {
        return HouseService.countPeoples(this);
    }

    public int getNumOfHouse() {
        return numOfHouse;
    }

    public int getNumFloors() {
        return floors.size();
    }

    public void getAllInfo() {
        System.out.println("House №" + numOfHouse);
        for (int i = 0; i < floors.size(); ++i)
            floors.get(i).getAllInfo();
    }

    public void getInfo() {
        System.out.printf("\nHouse №%d\nNumber of floors: %d\nArea: %.2f, Peoples: %d\n",
                this.getNumOfHouse(), this.getNumFloors(), this.getArea(), this.getNumPeoples());
    }


    public boolean isApartment(int numApartment) {
        return HouseService.isApartment(this, numApartment);
    }


    public void setPeople(int numOfApartment, int numOfPeople) {
        HouseService.setPeople(this, numOfApartment, numOfPeople);
    }



    public static final class HouseBuilder {
        private List<Floor> floors;
        private int numOfHouse;

        private HouseBuilder(){}

        public static HouseBuilder aHouse(){
            return new House.HouseBuilder();
        }
        public HouseBuilder withFloors(List<Floor> floors){
            this.floors = floors;
            return this;
        }

        public HouseBuilder withNumOfHouse(int numOfHouse){
            this.numOfHouse = numOfHouse;
            return this;
        }

        public House build(){
            House house = new House();
            house.setNumOfHouse(numOfHouse);
            house.setFloors(floors);
            return house;
        }
    }
}

