package Reiziger;

import java.util.List;

public interface ReizigerDAO {

    List<Reiziger> findAll();

    List<Reiziger> findByGbDatum(String gbDatum);

    List<Reiziger> findByName(String gbDatum);

    Reiziger findById(int id);

    Reiziger save(Reiziger reiziger);

    Reiziger update(Reiziger reiziger, int id);

    Boolean delete(Reiziger reiziger);

    void closeConnection();
}
