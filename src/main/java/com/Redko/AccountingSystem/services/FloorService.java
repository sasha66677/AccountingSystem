package com.Redko.AccountingSystem.services;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.Floor;

import java.util.List;

public class FloorService {

    public static double getArea(Floor floor) {
        List<Apartment> apartments = floor.getApartments();
        return apartments.stream().
                mapToDouble(Apartment::getArea).
                sum();
    }

    public static int getNumPeople(Floor floor) {
        List<Apartment> apartments = floor.getApartments();
        return apartments.stream().
                mapToInt(Apartment::getNumOfPeople).
                sum();
    }

    public static int getNumApartments(Floor floor) {
        List<Apartment> apartments = floor.getApartments();
        return apartments.size();
    }

    public static boolean isApartment(Floor floor, int numOfApartment) {
        List<Apartment> apartments = floor.getApartments();
        return apartments.stream().
                mapToInt(Apartment::getNumOfApartment).
                anyMatch(num -> num == numOfApartment);
    }

    public static Apartment getApartment(Floor floor, int numOfApartment) {
        List<Apartment> apartments = floor.getApartments();
        return apartments.stream().
                filter(apartment -> apartment.getNumOfApartment() == numOfApartment).
                findFirst().
                orElse(null);
    }

}
