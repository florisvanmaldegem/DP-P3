package Reiziger;

import OVchipkaart.OVchipkaartOracleDaoImpl;
import OVchipkaart.OVchipkaart;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends Application.OracleBaseDAO implements ReizigerDAO{

    public ReizigerOracleDaoImpl() throws SQLException {
        super();
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            // Create statement
            Statement statement = this.getConnection().createStatement();

            // Make query
            String query = "SELECT * FROM REIZIGER";

            // Get results
            ResultSet resultR = statement.executeQuery(query);

            //Create list and loop over results
            ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();

            while(resultR.next()){
                // Maak nieuwe reiziger
                Reiziger reiziger = new Reiziger();

                // Set het ID
                reiziger.setReizigerID(resultR.getInt("REIZIGERID"));

                // Set naam
                reiziger.setVoorletters(resultR.getString("VOORLETTERS"));
                reiziger.setTussenvoegsel(resultR.getString("TUSSENVOEGSEL"));
                reiziger.setAchternaam(resultR.getString("ACHTERNAAM"));

                // Set geboortedatum
                reiziger.setGeboortedatum(resultR.getDate("GEBORTEDATUM"));

                //Haal OV chipkaarts op bij de huidge reiziger
                for(OVchipkaart c : new OVchipkaartOracleDaoImpl().findByReiziger(reiziger)){
                    reiziger.voegOVChipkaartToe(c);
                }

                // Voeg reiziger toe aan de result list
                reizigers.add(reiziger);
            }

            return reizigers;
        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Reiziger> findByGbDatum(java.sql.Date gbDatum) {
        try {

            // Make query
            String query = "SELECT * FROM REIZIGER WHERE GEBORTEDATUM LIKE ?";

            // Create statement
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setDate(1, gbDatum);

            // Get results
            ResultSet resultR = statement.executeQuery();

            //Create list and loop over results
            ArrayList<Reiziger> reizigers = new ArrayList<>();

            while(resultR.next()){
                // Maak nieuwe reiziger
                Reiziger reiziger = new Reiziger();

                // Set het ID
                reiziger.setReizigerID(resultR.getInt("REIZIGERID"));

                // Set naam
                reiziger.setVoorletters(resultR.getString("VOORLETTERS"));
                reiziger.setTussenvoegsel(resultR.getString("TUSSENVOEGSEL"));
                reiziger.setAchternaam(resultR.getString("ACHTERNAAM"));

                // Set geboortedatum
                reiziger.setGeboortedatum(resultR.getDate("GEBORTEDATUM"));

                //Haal OV chipkaarts op bij de huidge reiziger
                for(OVchipkaart c : new OVchipkaartOracleDaoImpl().findByReiziger(reiziger)){
                    reiziger.voegOVChipkaartToe(c);
                }

                // Voeg reiziger toe aan de result list
                reizigers.add(reiziger);
            }

            return reizigers;
        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Reiziger save(Reiziger reiziger) {
        try {
            String query;
            query = "INSERT INTO REIZIGER(REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBORTEDATUM)" +
                    "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, reiziger.getReizigerID());
            preparedStatement.setString(2, reiziger.getVoorletters());
            preparedStatement.setString(3, reiziger.getTussenvoegsel());
            preparedStatement.setString(4, reiziger.getAchternaam());
            preparedStatement.setDate(5, reiziger.getGeboortedatum());

            preparedStatement.execute();
            this.getConnection().commit();
            return reiziger;
            
        } catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return reiziger;
        }
    }

    @Override
    public Boolean delete(Reiziger reiziger) {
        try {
            String query;
            query = "DELETE FROM REIZIGER WHERE REIZIGERID = ?";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, reiziger.getReizigerID());

            boolean succes = preparedStatement.execute();
            this.getConnection().commit();
            return succes;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger update(Reiziger reiziger){
        try {
            String query;
            query = "UPDATE REIZIGER" +
                    "SET VOORLETTERS=?" +
                    "TUSSENVOEGSEL=?" +
                    "ACHTERNAAM=?" +
                    "GEBORTEDATUM=?";

            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setString(1, reiziger.getVoorletters());
            preparedStatement.setString(2, reiziger.getTussenvoegsel());
            preparedStatement.setString(3, reiziger.getAchternaam());
            preparedStatement.setDate(4, reiziger.getGeboortedatum());

            boolean succes = preparedStatement.execute();
            this.getConnection().commit();
            return reiziger;

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return reiziger;
        }
    }

    @Override
    public void closeConnection() {
        try {
            this.getConnection().close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
