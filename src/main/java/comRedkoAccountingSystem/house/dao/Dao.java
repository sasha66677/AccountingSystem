package comRedkoAccountingSystem.house.dao;

public interface Dao <T, H> {

    void save(T obj);

    void update(T obj);

    void delete(T obj);

    T findById(int id);

    H findAll();

}
