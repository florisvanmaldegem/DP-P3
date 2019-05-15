package OVchipkaart;

import Reiziger.Reiziger;
import Reiziger.ReizigerOracleDaoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVchipkaartOracleDaoImpl extends Application.OracleBaseDAO implements OVchipkaartDAO {

    public OVchipkaartOracleDaoImpl() throws SQLException{

    }

    @Override
    public OVchipkaart findByKaartnummer(int number){
        try {
            String query;
            query = "SELECT * FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";

            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, number);

            ResultSet resultC = preparedStatement.executeQuery();

            resultC.next();

            OVchipkaart chipkaart = new OVchipkaart();
            chipkaart.setKaartNummer(resultC.getInt("KAARTNUMMER"));
            chipkaart.setGeldigTot(resultC.getDate("GELDIGTOT"));
            chipkaart.setKlasse(resultC.getInt("KLASSE"));
            chipkaart.setSaldo(resultC.getFloat("SALDO"));

            query = "SELECT * FROM REIZIGER WHERE REIZIGERID = ?";
            PreparedStatement preparedStatementR = this.getConnection().prepareStatement(query);
            preparedStatementR.setInt(1, resultC.getInt("REIZIGERID"));
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
                chipkaart.setEigenaar(r);
            }
            return chipkaart;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OVchipkaart save(OVchipkaart oVchipkaart) {
        try {
            String query;
            query = "INSERT INTO OVCHIPKAART(KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID)" +
                    "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, oVchipkaart.getKaartNummer());
            preparedStatement.setDate(2, oVchipkaart.getGeldigTot());
            preparedStatement.setInt(3, oVchipkaart.getKlasse());
            preparedStatement.setFloat(4, oVchipkaart.getSaldo());
            preparedStatement.setInt(5, oVchipkaart.getEigenaar().getReizigerID());

            preparedStatement.execute();
            this.getConnection().commit();
            return oVchipkaart;

        }catch(SQLException e){
            e.printStackTrace();
            return oVchipkaart;
        }
    }
    @Override
    public OVchipkaart update(OVchipkaart oVchipkaart) {
        try {
            String query;
            query = "UPDATE OV_CHIPKAART" +
                    "SET GELDIGTOT=?" +
                    "KLASSE=?" +
                    "SALDO=?" +
                    "REIZIGERID=?";

            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setDate(1, oVchipkaart.getGeldigTot());
            preparedStatement.setInt(2, oVchipkaart.getKlasse());
            preparedStatement.setFloat(3, oVchipkaart.getSaldo());
            preparedStatement.setInt(4, oVchipkaart.getEigenaar().getReizigerID());

            preparedStatement.execute();
            this.getConnection().commit();
            return oVchipkaart;

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return oVchipkaart;
        }
    }

    @Override
    public boolean delete(OVchipkaart oVchipkaart) {
        try {
            String query;
            query = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            pstmt.setInt(1, oVchipkaart.getKaartNummer());

            boolean succes = pstmt.execute();
            this.getConnection().commit();
            return succes;
            
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OVchipkaart> findByReiziger(Reiziger reiziger) {
        try {
            ArrayList<OVchipkaart> kaarten = new ArrayList<>();
            String query;
            query = "SELECT * FROM OV_CHIPKAART WHERE REIZIGERID = ?";
            PreparedStatement pstmt = this.getConnection().prepareStatement(query);
            pstmt.setInt(1, reiziger.getReizigerID());

            ResultSet resultC = pstmt.executeQuery();
            while(resultC.next()){
                OVchipkaart chipkaart = new OVchipkaart();
                chipkaart.setEigenaar(reiziger);
                chipkaart.setKaartNummer(resultC.getInt("KAARTNUMMER"));
                chipkaart.setGeldigTot(resultC.getDate("GELDIGTOT"));
                chipkaart.setKlasse(resultC.getInt("KLASSE"));
                chipkaart.setSaldo(resultC.getFloat("SALDO"));
                kaarten.add(chipkaart);
            }
            return kaarten;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OVchipkaart> findAll() {
        try {
            ArrayList<OVchipkaart> kaarten = new ArrayList<>();

            String query;
            query = "SELECT * FROM OV_CHIPKAART";
            Statement statement = this.getConnection().createStatement();

            ResultSet resultC = statement.executeQuery(query);

            while(resultC.next()){
                OVchipkaart chipkaart = new OVchipkaart();
                chipkaart.setKaartNummer(resultC.getInt("KAARTNUMMER"));
                chipkaart.setGeldigTot(resultC.getDate("GELDIGTOT"));
                chipkaart.setKlasse(resultC.getInt("KLASSE"));
                chipkaart.setSaldo(resultC.getFloat("SALDO"));

                query = "SELECT * FROM REIZIGER WHERE REIZIGERID = ?";
                PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, resultC.getInt("REIZIGERID"));
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
                    chipkaart.setEigenaar(r);
                }

                kaarten.add(chipkaart);
            }
            return kaarten;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
