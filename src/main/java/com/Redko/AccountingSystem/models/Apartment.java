package com.Redko.AccountingSystem.models;

import java.util.Objects;

public class Apartment {
    private int numOfApartment;
    private double area;
    private int numOfPeople;

    public Apartment() {
    }

    public double getArea() {
        return area;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public int getNumOfApartment(){
        return numOfApartment;
    }

    public void setNumOfPeople(int numOfPeople){
        this.numOfPeople = numOfPeople;
    }

    public void setNumOfApartment(int numOfApartment) {
        this.numOfApartment = numOfApartment;
    }

    public void setArea(double area) {
        this.area = area;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apartment)) return false;
        Apartment apartment = (Apartment) o;
        return numOfApartment == apartment.numOfApartment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfApartment, area, numOfPeople);
    }

    @Override
    public String toString() {
        return "Apartment " +
                "№" + numOfApartment +
                ", area=" + area +
                ", numOfPeople=" + numOfPeople;
    }

    public static final class ApartmentBuilder {
        private int numOfApartment;
        private double area;
        private int numOfPeople;

        private ApartmentBuilder() {}

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

        public ApartmentBuilder withNumOfPeople(int numberOfPeople) {
            this.numOfPeople = numberOfPeople;
            return this;
        }

        public Apartment build() {
            Apartment apartment = new Apartment();
            apartment.setNumOfApartment(numOfApartment);
            apartment.setArea(area);
            apartment.setNumOfPeople(numOfPeople);
            return apartment;
        }
    }
}

