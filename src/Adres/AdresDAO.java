package Adres;

import Reiziger.Reiziger;
import java.util.List;

public interface AdresDAO {
    Adres save(Adres adres);

    Adres update(Adres adres);

    boolean delete(Adres adres);

    Adres findByReiziger(Reiziger reiziger);

    List<Adres> findAll();

    Adres findById(int number);
}
