package Application;

import Reiziger.Reiziger;
import Reiziger.ReizigerOracleDaoImpl;

import OVchipkaart.OVchipkaart;
import OVchipkaart.OVchipkaartOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {


    public static void main(String[] args) throws SQLException {
        ReizigerOracleDaoImpl reizigerOracleDao = new ReizigerOracleDaoImpl();
        OVchipkaartOracleDaoImpl oVchipkaartOracleDao = new OVchipkaartOracleDaoImpl();

        List<Reiziger> test = reizigerOracleDao.findByGbDatum(java.sql.Date.valueOf("2002-12-03"));
        for(Reiziger r : test){
            System.out.println(r);
        }
    }
}
