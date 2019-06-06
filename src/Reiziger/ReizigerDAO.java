package Reiziger;

import java.util.List;

public interface ReizigerDAO {

    List<Reiziger> findAll();

    List<Reiziger> findByGbDatum(java.sql.Date gbDatum);

    Reiziger findById(int id);

    Reiziger save(Reiziger reiziger);

    Reiziger update(Reiziger reiziger);

    Boolean delete(Reiziger reiziger);

    void closeConnection();
}
