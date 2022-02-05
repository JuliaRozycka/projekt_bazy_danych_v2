package edu.ib.projekt_bazy_danych_v2;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.sql.*;


public class ConnectionUtil {
    private String userName;
    private String userPassword;
    private static final String SELECT_QUERY = "SELECT * FROM uzytkownicy WHERE login = ? and haslo = ?";

    private Connection conn = null;

    public ConnectionUtil(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A");
            //consoleTextArea.appendText("No MySQL JDBC Driver found." + "\n");
            e.printStackTrace();
            throw e;
        }
        try {
            conn = DriverManager.getConnection(createURL());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Podano nieporpawny login lub hasło.");
            e.printStackTrace();
            throw e;
        }
    }

/*    public void dbDisconnect() throws SQLException {

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                JOptionPane.showMessageDialog(null, "A");
                //consoleTextArea.appendText("Connection closed. Bye!" + "\n");
            }
        } catch (Exception e) {
            throw e;
        }
    }*/

    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("punkt_szczepien?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET");

        return urlSB.toString();
    }

/*    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {
            dbConnect();
            stmt = conn.prepareStatement(queryStmt);
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetWrapper();
            crs.populate(resultSet);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "A");
            //consoleTextArea.appendText("Problem occurred at executeQuery operation. \n");
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }*/

/*    public void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "A");
            //consoleTextArea.appendText("Problem occurred at executeUpdate operation. \n");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }*/
}
