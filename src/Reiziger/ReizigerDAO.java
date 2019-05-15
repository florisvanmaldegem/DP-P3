package Reiziger;

import java.util.List;

public interface ReizigerDAO {

    List<Reiziger> findAll();

    List<Reiziger> findByGbDatum(java.sql.Date gbDatum);

    Reiziger save(Reiziger reiziger);

    Reiziger update(Reiziger reiziger);

    Boolean delete(Reiziger reiziger);

    void closeConnection();
}
