package com.Redko.AccountingSystem.models;

import java.util.List;
import java.util.Objects;


public class House {
    private List<Floor> floors;
    private int numOfHouse;

    public House() {}

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void setNumOfHouse(int numOfHouse) {
        this.numOfHouse = numOfHouse;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public int getNumOfHouse() {
        return numOfHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        return numOfHouse == ((House) o).numOfHouse;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("House №" + numOfHouse + '\n');
        for (Floor floor : floors)
            str.append(floor.toString()).append('\n');
        if (!floors.isEmpty())
            str.deleteCharAt(str.length() - 1);
        return str.toString();
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

