package comRedkoAccountingSystem.house.dao;

import comRedkoAccountingSystem.house.models.Apartment;
import comRedkoAccountingSystem.house.models.Floor;
import comRedkoAccountingSystem.house.models.House;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseDao implements Dao<House, Map<Integer, House>> {
    private final File file;
    private static BufferedWriter writer;
    private static BufferedReader reader;

    private static volatile HouseDao houseDao;

    private HouseDao() {
        String str = "DB.txt";
        file = new File(str);
    }

    public static HouseDao getHouseDao() {
        if (houseDao == null)
            synchronized (HouseDao.class) {
                if (houseDao == null)
                    houseDao = new HouseDao();
            }
        return houseDao;
    }

    private void save(Map<Integer, House> houses) throws IOException {
        for (Map.Entry<Integer, House> item : houses.entrySet())
            save(item.getValue());
    }

    @Override
    public void save(House house) {
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(String.valueOf(house.getNumOfHouse()));
            writer.newLine();

            var floors = house.getFloors();
            for (var item : floors)
                save(item);
            writer.newLine();

            writer.close();
        } catch (IOException ignored) {
        }
    }

    private void save(Floor floor) throws IOException {
        writer.write(String.valueOf(floor.getNumOfFloor()));
        writer.newLine();

        var apartments = floor.getApartments();
        for (var item : apartments)
            save(item);
        writer.newLine();
    }

    private void save(Apartment apartment) throws IOException {
        writer.write(String.valueOf(apartment.getNumOfApartment()));
        writer.newLine();
        writer.write(String.valueOf(apartment.getArea()));
        writer.newLine();
        writer.write(String.valueOf(apartment.getNumOfPeople()));
        writer.newLine();
    }

    @Override
    public void update(House house) {
        try {
            var houses = findAll();
            houses.replace(house.getNumOfHouse(), house);
            file.delete();
            save(houses);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void delete(House house) {
        try {
            var houses = findAll();
            houses.remove(house.getNumOfHouse());
            file.delete();
            save(houses);
        } catch (IOException ignored) {

        }
    }

    @Override
    public House findById(int numOfHouse) {
        var houses = findAll();
        return houses.get(numOfHouse);
    }

    @Override
    public Map<Integer, House> findAll() {
        try {
            Map<Integer, House> houses = new HashMap<>();
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                List<Floor> floors = new ArrayList<>();

                int numOfHouse = Integer.parseInt(line);

                line = reader.readLine();
                while (!line.isEmpty()) {

                    int numOfFloor = Integer.parseInt(line);

                    List<Apartment> apartments = new ArrayList<>();

                    line = reader.readLine();
                    while (!line.isEmpty()) {
                        int numOfApartment = Integer.parseInt(line);
                        line = reader.readLine();
                        double area = Double.parseDouble(line);
                        line = reader.readLine();
                        int numOfPeople = Integer.parseInt(line);

                        line = reader.readLine();

                        var apart = Apartment.ApartmentBuilder.aFlat().
                                withNumOfPeople(numOfPeople).
                                withArea(area).
                                withNumOfApartment(numOfApartment).build();
                        apartments.add(apart);
                    }
                    line = reader.readLine();
                    floors.add(Floor.FloorBuilder.aFloor().withApartments(apartments).withNumOfFloor(numOfFloor).build());
                }

                houses.put(numOfHouse, House.HouseBuilder.aHouse().withNumOfHouse(numOfHouse).withFloors(floors).build());
            }
            reader.close();

            return houses;
        } catch (IOException ignored) {
            return new HashMap<>();
        }
    }

}
