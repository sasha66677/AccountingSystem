package comRedkoAccountingSystem.house.dao;

import comRedkoAccountingSystem.house.Storage;
import comRedkoAccountingSystem.house.models.House;

import java.io.File;


public class StorageDao implements Dao<House, Storage> {
    private static volatile StorageDao storageDao;
    private final HouseDao houseDao;

    private StorageDao() {
        houseDao = HouseDao.getHouseDao();
    }

    public static StorageDao getStorageDao() {
        if (storageDao == null)
            synchronized (HouseDao.class) {
                if (storageDao == null)
                    storageDao = new StorageDao();
            }
        return storageDao;
    }

    @Override
    public void save(House house) {
        Storage storage = findAll();
        if (!storage.isHouse(house.getNumOfHouse()))
            houseDao.save(house);
    }

    @Override
    public void update(House house) {
        Storage storage = findAll();
        if (storage.isHouse(house.getNumOfHouse()))
            houseDao.update(house);
    }

    @Override
    public void delete(House house) {
        houseDao.delete(house);
    }

    @Override
    public House findById(int numOfHouse) {
        Storage storage = findAll();
        return storage.getHouse(numOfHouse);
    }

    @Override
    public Storage findAll() {
        Storage storage = new Storage();
        var houses = houseDao.findAll();
        storage.setHouses(houses);
        return storage;
    }

}
