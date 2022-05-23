package com.Redko.AccountingSystem.dao;

import com.Redko.AccountingSystem.models.Apartment;
import com.Redko.AccountingSystem.models.Floor;
import com.Redko.AccountingSystem.models.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class HouseDao implements Dao<House, Map<Integer, House>> {
    Connection connection = null;

    private static volatile HouseDao houseDao;

    private HouseDao() {
    }

    private void openConnection() {
        final String username = "root";
        final String password = "root";
        final String url = "jdbc:mysql://localhost:3306/mysql";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HouseDao getHouseDao() {
        if (houseDao == null)
            synchronized (HouseDao.class) {
                if (houseDao == null)
                    houseDao = new HouseDao();
            }
        return houseDao;

    }


    @Override
    public void save(House house) {
        openConnection();
        try {
            PreparedStatement insertHouse = connection.prepareStatement("insert into sys.houses (numOfHouse) values (?)");
            insertHouse.setInt(1, house.getNumOfHouse());
            insertHouse.execute();
            var floors = house.getFloors();
            for (Floor floor : floors)
                save(floor, house.getNumOfHouse());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

    }

    private void save(Floor floor, int numOfHouse) {
        try {
            PreparedStatement insertFloor = connection.prepareStatement("insert into sys.floors (numOfHouse, numOfFloor) values (?, ?)");
            insertFloor.setInt(1, numOfHouse);
            insertFloor.setInt(2, floor.getNumOfFloor());
            insertFloor.execute();

            var apartments = floor.getApartments();
            for (Apartment apartment : apartments)
                save(apartment, numOfHouse, floor.getNumOfFloor());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void save(Apartment apartment, int numOfHouse, int numOfFloor) {
        try {
            PreparedStatement insertApartment = connection.prepareStatement("insert into sys.apartments (numOfHouse, numOfFloor, numOfApartment, numOfPeople, area) values (?, ?, ?, ?, ?)");
            insertApartment.setInt(1, numOfHouse);
            insertApartment.setInt(2, numOfFloor);
            insertApartment.setInt(3, apartment.getNumOfApartment());
            insertApartment.setInt(4, apartment.getNumOfPeople());
            insertApartment.setDouble(5, apartment.getArea());

            insertApartment.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(House house) {
        openConnection();
        delete(house);
        save(house);
        closeConnection();

    }

    @Override
    public void delete(House house) {
        openConnection();
        try {
            int numOfHouse = house.getNumOfHouse();
            PreparedStatement deleteApartment =
                    connection.prepareStatement("delete from sys.apartments where ?");
            PreparedStatement deleteFloor =
                    connection.prepareStatement("delete from sys.floors where ?");
            PreparedStatement deleteHouse =
                    connection.prepareStatement("delete from sys.houses where ?");

            deleteApartment.setInt(1, numOfHouse);
            deleteFloor.setInt(1, numOfHouse);
            deleteHouse.setInt(1, numOfHouse);

            deleteApartment.execute();
            deleteFloor.execute();
            deleteHouse.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

    }

    @Override
    public House findById(int numOfHouse) {
        openConnection();
        var houses = findAll();
        closeConnection();
        return houses.get(numOfHouse);

    }

    @Override
    public Map<Integer, House> findAll() {
        openConnection();
        Map<Integer, House> houses = new HashMap<>();
        try {
            PreparedStatement selectHouse =
                    connection.prepareStatement("select * from sys.houses");
            ResultSet resHouse = selectHouse.executeQuery();

            House house;
            while (resHouse.next()) {
                int numOfHouse = resHouse.getInt("numOfHouse");
                var floors = findFloors(numOfHouse);
                house = House.HouseBuilder.aHouse().
                        withNumOfHouse(numOfHouse).
                        withFloors(floors).
                        build();
                houses.put(numOfHouse, house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return houses;

    }

    private List<Floor> findFloors(int numOfHouse) {
        List<Floor> floors = new ArrayList<Floor>();
        Floor fl;
        try {
            PreparedStatement selectFloor =
                    connection.prepareStatement("select * from sys.floors where numOfHouse = ?");
            selectFloor.setInt(1, numOfHouse);
            ResultSet resFloor = selectFloor.executeQuery();
            while (resFloor.next()) {
                int numOfFloor = resFloor.getInt("numOfFloor");
                var apartments = findApartments(numOfHouse, numOfFloor);
                fl = Floor.FloorBuilder.aFloor().
                        withNumOfFloor(numOfFloor).
                        withApartments(apartments).
                        build();
                floors.add(fl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return floors;

    }

    private List<Apartment> findApartments(int numOfHouse, int numOfFloor) {
        List<Apartment> apartments = new ArrayList<>();
        Apartment apart;
        try {
            PreparedStatement selectApartment =
                    connection.prepareStatement("select * from sys.apartments where numOfHouse = ? AND numOfFloor = ?");
            selectApartment.setInt(1, numOfHouse);
            selectApartment.setInt(2, numOfFloor);
            ResultSet resApartment = selectApartment.executeQuery();

            while (resApartment.next()) {
                apart = Apartment.ApartmentBuilder.aFlat().
                        withNumOfApartment(resApartment.getInt("numOfApartment")).
                        withArea(resApartment.getDouble("area")).
                        withNumOfPeople(resApartment.getInt("numOfPeople")).
                        build();
                apartments.add(apart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;

    }

}
