package OVchipkaart;

import Reiziger.Reiziger;

import java.util.List;

public interface OVchipkaartDAO {
    OVchipkaart save(OVchipkaart oVchipkaart);

    OVchipkaart update(OVchipkaart oVchipkaart);

    boolean delete(OVchipkaart oVchipkaart);

    List<OVchipkaart> findByReiziger(Reiziger reiziger);

    List<OVchipkaart> findAll();

    OVchipkaart findByKaartnummer(int number);
}
