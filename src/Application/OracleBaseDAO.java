package Application;

import java.sql.*;

public abstract class OracleBaseDAO {

    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//fluuurp.tk:1521/xe";
    private static final String DB_USER = "FLORIS_P2";
    private static final String DB_PASS = "siev1b";
    protected static Connection conn;

    public OracleBaseDAO() throws SQLException{
        try {
            Class.forName(DB_DRIV).newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        // Leg de connectie met de database
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        conn.setAutoCommit(false);
    }

    protected Connection getConnection(){
        return conn;
    }

    public void closeConection() throws SQLException{
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
