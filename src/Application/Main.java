package Application;

import Reiziger.Reiziger;
import Reiziger.ReizigerOracleDaoImpl;

import OVchipkaart.OVchipkaart;
import OVchipkaart.OVchipkaartOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {


    public static void main(String[] args) throws SQLException {
        ReizigerOracleDaoImpl reizigerOracleDao = new ReizigerOracleDaoImpl();
        OVchipkaartOracleDaoImpl oVchipkaartOracleDao = new OVchipkaartOracleDaoImpl();

        /*
        AUTOCOMMIT STAAT AL AAN IN DE DATABASE
         */

        /*
        ----- TEST 1 -----
        vind alle reizigers
         */
        System.out.println("Test 1");

        for(Reiziger r : reizigerOracleDao.findAll()){
            System.out.println(r);
        }
        //-----------------------------------------------
        System.out.println("---------------");


        /*
        ----- TEST 2 -----
        voeg een nieuwe reiziger toe aan de database
         */
        System.out.println("Test 2");

        Reiziger test = new Reiziger();
        test.setReizigerID(new Random().nextInt(5000)+5000);
        test.setVoorletters("F J");
        test.setTussenvoegsel("van");
        test.setAchternaam("Maldegem");
        test.setGeboortedatum(java.sql.Date.valueOf("1998-05-14"));
        System.out.println("Reiziger ID van " + test.toString() + " is " + test.getReizigerID());
        reizigerOracleDao.save(test);


        System.out.println("Alle reizigers in DB");
        for(Reiziger r : reizigerOracleDao.findAll()){
            System.out.println(r);
        }

        //-----------------------
        System.out.println("---------------");

        /*
        ----- TEST 3 -----
        voeg een OV chipkaart toe
         */
        System.out.println("Test 3");

        OVchipkaart testkaart = new OVchipkaart();
        testkaart.setKaartNummer(20000);
        testkaart.setEigenaar(test);
        testkaart.setSaldo(0);
        testkaart.setKlasse(2);
        testkaart.setGeldigTot(java.sql.Date.valueOf("2024-02-27"));
        oVchipkaartOracleDao.save(testkaart);

        System.out.println("Alle reizigers in DB");
        for(Reiziger r : reizigerOracleDao.findAll()){
            System.out.println(r);
        }

        //---------------------------------
        System.out.println("---------------");

        /*
        ----- TEST 4 -----
        verander saldo van een kaart
         */
        System.out.println("Test 4");

        testkaart.setSaldo(25.00f);
        oVchipkaartOracleDao.update(testkaart);

        System.out.println("Alle reizigers in DB");
        for(Reiziger r : reizigerOracleDao.findAll()){
            System.out.println(r);
        }

        //-----------------------
        System.out.println("---------------");


        /*
        ----- TEST 5 -----
        verwijder een reiziger uit de database met zijn kaarten
         */
        System.out.println("Test 5");

        for(OVchipkaart c : oVchipkaartOracleDao.findByReiziger(test)){
            oVchipkaartOracleDao.delete(c);
        }
        reizigerOracleDao.delete(test);

        System.out.println("Alle reizigers in DB");
        for(Reiziger r : reizigerOracleDao.findAll()){
            System.out.println(r);
        }

        //-----------------------
        System.out.println("---------------");


    }
}
