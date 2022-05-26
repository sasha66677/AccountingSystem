package comRedkoAccountingSystem.house.baseService;

import comRedkoAccountingSystem.house.Storage;
import comRedkoAccountingSystem.house.dao.HouseDao;
import comRedkoAccountingSystem.house.dao.StorageDao;
import comRedkoAccountingSystem.house.models.House;

import java.util.Map;

public class BaseService {
    private final StorageDao dao;
    private static BaseService baseService;

    private BaseService() {
        dao = StorageDao.getStorageDao();
    }

    public static BaseService getBaseService() {
        if (baseService == null)
            synchronized (HouseDao.class) {
                if (baseService == null)
                    baseService = new BaseService();
            }
        return baseService;
    }


    void save(House house) {
        dao.save(house);
    }

    void update(House house) {
        dao.update(house);
    }

    void delete(House house) {
        dao.delete(house);
    }

    House findById(int numOfHouse) {
        return dao.findById(numOfHouse);
    }

    Storage findAll() {
        return dao.findAll();
    }

}
