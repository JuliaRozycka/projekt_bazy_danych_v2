package edu.ib.projekt_bazy_danych_v2;

import entity.Pacjenci;
import entity.Uzytkownicy;
import entity.WidokUzytkownika;
import javafx.fxml.FXMLLoader;

import java.sql.*;
import java.util.ArrayList;

public class FetchDataFromTableUzyt {
    protected ArrayList<Integer> idList = new ArrayList<Integer>();
    protected ArrayList<String> nazwaSzczepieniaList = new ArrayList<>();
    protected ArrayList<String> producentList = new ArrayList<>();
    protected ArrayList<Date> dataList = new ArrayList<>();
    protected ArrayList<Time> godzinaList = new ArrayList<>();
    protected ArrayList<Object> statusList = new ArrayList<>();



    public void addData(int id_l) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;
            String sql = "SELECT * FROM widok_uzytkownika WHERE id_login = ".concat(String.valueOf(id_l)).concat(" ORDER BY data DESC");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            PreparedStatement ps = connection.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Integer id = Integer.valueOf(rs.getString("id_login"));
                idList.add(id);
                String nazwaSzczepienia = rs.getString("nazwa_szczepienia");
                nazwaSzczepieniaList.add(nazwaSzczepienia);
                String producent = rs.getString("producent");
                producentList.add(producent);
                Date data = rs.getDate("data");
                dataList.add(data);
                Time godzina = rs.getTime("godzina");
                godzinaList.add(godzina);
                Object status = rs.getObject("status");
                statusList.add(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FetchDataFromTableUzyt{" +
                "idList=" + idList +
                ", nazwaSzczepieniaList=" + nazwaSzczepieniaList +
                ", producentList=" + producentList +
                ", dataList=" + dataList +
                ", godzinaList=" + godzinaList +
                ", statusList=" + statusList +
                '}';
    }

}