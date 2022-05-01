package com.Redko.AccountingSystem;

import com.Redko.AccountingSystem.models.House;
import com.Redko.AccountingSystem.services.HouseService;

import java.util.*;

public class Storage {
    private Map<Integer, House> houses;

    public Storage() {
        houses = new HashMap<>();
    }

    public void setHouses(Map<Integer, House> houses) {
        this.houses = houses;
    }

    public Map<Integer, House> getHouses() {
        return houses;
    }

    public void addHouse(House house) {
        if (!isHouse(house.getNumOfHouse())){
            houses.put(house.getNumOfHouse(), house);
        }
    }

    public House getHouse(int numOfHouse){
        return houses.get(numOfHouse);
    }

    public int getNumOfHouses(){
        return houses.size();
    }

    public boolean isHouse(int numOfHouse) {
        return houses.containsKey(numOfHouse);
    }

    public void setPeople(int numOfHouse, int numOfApartment, int numOfPeople) {
        if (houses.containsKey(numOfHouse)){
            HouseService.setPeople(houses.get(numOfHouse), numOfApartment, numOfPeople);
        }
    }

    public void remove(int numOfHouse){
        houses.remove(numOfHouse);
    }

    public boolean isEmpty(){
        return houses.isEmpty();
    }

    public int compareArea(int numOfHouse1, int numOfHouse2) {
        House x1 = getHouse(numOfHouse1);
        House x2 = getHouse(numOfHouse2);
        return HouseService.compareArea(x1, x2);
    }

    public int compareHeight(int numOfHouse1, int numOfHouse2) {
        House x1 = getHouse(numOfHouse1);
        House x2 = getHouse(numOfHouse2);
        return HouseService.compareHeight(x1, x2);
    }

    public int compareNumOfPeople(int numOfHouse1, int numOfHouse2) {
        House x1 = getHouse(numOfHouse1);
        House x2 = getHouse(numOfHouse2);
        return HouseService.compareNumOfPeople(x1, x2);
    }

    public int compareArea(int numOfHouse1, int numOfApartment1, int numOfHouse2, int numOfApartment2) {
        House x1 = getHouse(numOfHouse1);
        House x2 = getHouse(numOfHouse2);
        return HouseService.compareArea(x1, numOfApartment1, x2, numOfApartment2);
    }

    public int compareNumOfPeople(int numOfHouse1, int numOfApartment1, int numOfHouse2, int numOfApartment2) {
        House x1 = getHouse(numOfHouse1);
        House x2 = getHouse(numOfHouse2);
        return HouseService.compareNumOfPeople(x1, numOfApartment1, x2, numOfApartment2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;
        Storage storage = (Storage) o;
        return Objects.equals(houses, storage.houses);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Storage" + '\n');
        for (Map.Entry<Integer, House> item : houses.entrySet())
            str.append(item.getValue().toString()).append("\n\n");
        if (!isEmpty())
            str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

}
