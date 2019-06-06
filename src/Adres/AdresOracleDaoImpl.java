package Adres;

import Application.OracleBaseDAO;
import OVchipkaart.OVchipkaart;
import OVchipkaart.OVchipkaartOracleDaoImpl;
import Reiziger.Reiziger;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresOracleDaoImpl extends OracleBaseDAO implements AdresDAO {

    public AdresOracleDaoImpl() throws SQLException{

    }

    public Adres save(Adres adres){

        try {
            String query = "INSERT INTO adres(adresid, postcode, huisnummer, straat, woonplaats, reizigerid) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, adres.getAdresID());
            preparedStatement.setString(2, adres.getPostcode());
            preparedStatement.setString(3, adres.getHuisnummer());
            preparedStatement.setString(4, adres.getStraat());
            preparedStatement.setString(5, adres.getWoonplaats());
            preparedStatement.setInt(6, adres.getReiziger().getReizigerID());

            preparedStatement.execute();
            this.getConnection().commit();
            return adres;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Adres update(Adres adres){
        try {
            String query = "UPDATE adres " +
                    "SET postcode = ?," +
                    "huisnummer = ?," +
                    "straat = ?," +
                    "woonplaats = ?," +
                    "reizigerid = ? " +
                    "WHERE adresid = ?";

            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);

            preparedStatement.setInt(6, adres.getAdresID());
            preparedStatement.setString(1, adres.getPostcode());
            preparedStatement.setString(2, adres.getHuisnummer());
            preparedStatement.setString(3, adres.getStraat());
            preparedStatement.setString(4, adres.getWoonplaats());
            preparedStatement.setInt(5, adres.getReiziger().getReizigerID());

            preparedStatement.execute();
            this.getConnection().commit();
            return adres;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM adres WHERE adresid=?";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, adres.getAdresID());
            if(preparedStatement.execute()){
                this.getConnection().commit();
                return true;
            }else{
                return false;
            }

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            String query = "SELECT * FROM ADRES WHERE REIZIGERID = ?";
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            pstmt.setInt(1, reiziger.getReizigerID());

            ResultSet result = pstmt.executeQuery();
            Adres adres = new Adres();
            while(result.next()){
                adres = new Adres(result.getInt("ADRESID"),
                        result.getString("POSTCODE"),
                        result.getString("HUISNUMMER"),
                        result.getString("STRAAT"),
                        result.getString("WOONPLAATS"),
                        reiziger);
            }

            return adres;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try {
            String query = "SELECT * FROM ADRES";
            Statement statement = this.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);

            ArrayList<Adres> adressen = new ArrayList<>();

            while(result.next()){
                Adres adres = new Adres(result.getInt("ADRESID"),
                        result.getString("POSTCODE"),
                        result.getString("HUISNUMMER"),
                        result.getString("STRAAT"),
                        result.getString("WOONPLAATS"));

                String queryR = "SELECT * FROM reiziger WHERE reizigerid = ?";
                PreparedStatement preparedStatement = this.getConnection().prepareStatement(queryR);
                preparedStatement.setInt(1, result.getInt("REIZIGERID"));
                ResultSet resultR = preparedStatement.executeQuery();
                while(resultR.next()){
                    Reiziger r = new Reiziger();
                    r.setReizigerID(resultR.getInt("REIZIGERID"));
                    r.setVoorletters(resultR.getString("VOORLETTERS"));
                    r.setTussenvoegsel(resultR.getString("TUSSENVOEGSEL"));
                    r.setAchternaam(resultR.getString("ACHTERNAAM"));
                    r.setGeboortedatum(resultR.getDate("GEBORTEDATUM"));
                    for(OVchipkaart c : new OVchipkaartOracleDaoImpl().findByReiziger(r)){
                        r.voegOVChipkaartToe(c);
                    }
                    adres.setReiziger(r);
                }

                adressen.add(adres);
            }

            return adressen;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Adres findById(int number) {
        try {
            String query = "SELECT * FROM adres WHERE adresid = ?";

            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, number);

            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Adres adres = new Adres(result.getInt("ADRESID"),
                        result.getString("POSTCODE"),
                        result.getString("HUISNUMMER"),
                        result.getString("STRAAT"),
                        result.getString("WOONPLAATS"));

                String queryR = "SELECT * FROM reiziger WHERE reizigerid = ?";
                PreparedStatement preparedStatementR = this.getConnection().prepareStatement(queryR);
                preparedStatementR.setInt(1, result.getInt("REIZIGERID"));
                ResultSet resultR = preparedStatementR.executeQuery();
                while(resultR.next()){
                    Reiziger r = new Reiziger();
                    r.setReizigerID(resultR.getInt("REIZIGERID"));
                    r.setVoorletters(resultR.getString("VOORLETTERS"));
                    r.setTussenvoegsel(resultR.getString("TUSSENVOEGSEL"));
                    r.setAchternaam(resultR.getString("ACHTERNAAM"));
                    r.setGeboortedatum(resultR.getDate("GEBORTEDATUM"));
                    for(OVchipkaart c : new OVchipkaartOracleDaoImpl().findByReiziger(r)){
                        r.voegOVChipkaartToe(c);
                    }
                    adres.setReiziger(r);
                    return adres;
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
