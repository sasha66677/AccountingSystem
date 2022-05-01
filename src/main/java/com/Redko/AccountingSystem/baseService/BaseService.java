package com.Redko.AccountingSystem.baseService;

import com.Redko.AccountingSystem.dao.HouseDao;
import com.Redko.AccountingSystem.Storage;
import com.Redko.AccountingSystem.dao.StorageDao;
import com.Redko.AccountingSystem.models.House;

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
