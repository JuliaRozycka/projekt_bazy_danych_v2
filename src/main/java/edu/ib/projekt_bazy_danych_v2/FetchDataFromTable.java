package edu.ib.projekt_bazy_danych_v2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;

public class FetchDataFromTable {

    protected ArrayList<String> peselList = new ArrayList<>();
    protected ArrayList<String> imieList = new ArrayList<>();
    protected ArrayList<String> nazwiskoList = new ArrayList<>();
    protected ArrayList<String> nazwaSzczepieniaList = new ArrayList<>();
    protected ArrayList<String> producentList = new ArrayList<>();
    protected ArrayList<Date> dataList = new ArrayList<>();
    protected ArrayList<Time> godzinaList = new ArrayList<>();

    @FXML
    public TextField tfPesel;

    public void addData(String klasa) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;
            String filter;
            if (tfPesel == null) {
                filter = "";
            }
            else {
                filter = tfPesel.getText();
            }
            String sql = "SELECT * FROM ".concat(klasa).concat(" WHERE pesel LIKE '%").concat(filter).concat("%' ORDER BY data DESC");
            System.out.println(sql);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String pesel = rs.getString("pesel");
                peselList.add(pesel);
                String imie = rs.getString("imie");
                imieList.add(imie);
                String nazwisko = rs.getString("nazwisko");
                nazwiskoList.add(nazwisko);
                String nazwaSzczepienia = rs.getString("nazwa_szczepienia");
                nazwaSzczepieniaList.add(nazwaSzczepienia);
                String producent = rs.getString("producent");
                producentList.add(producent);
                Date data = rs.getDate("data");
                dataList.add(data);
                Time godzina = rs.getTime("godzina");
                godzinaList.add(godzina);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FetchDataFromTable{" +
                "peselList=" + peselList +
                ", imieList=" + imieList +
                ", nazwiskoList=" + nazwiskoList +
                ", nazwaSzczepieniaList=" + nazwaSzczepieniaList +
                ", producentList=" + producentList +
                ", dataList=" + dataList +
                ", godzinaList=" + godzinaList +
                '}';
    }
}