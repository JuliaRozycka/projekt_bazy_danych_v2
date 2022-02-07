package edu.ib.projekt_bazy_danych_v2;

import java.sql.*;
import java.util.ArrayList;

public class FetchDataFromTableZapisyAdmin {

    protected ArrayList<Integer> idList = new ArrayList<>();
    protected ArrayList<Date> dataList = new ArrayList<>();
    protected ArrayList<Time> godzinaList = new ArrayList<>();

    public void addData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;
            String sql = "SELECT * FROM terminy ORDER BY data";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                idList.add(id);
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
        return "FetchDataFromTableZapisyAdmin{" +
                "idList=" + idList +
                ", dataList=" + dataList +
                ", godzinaList=" + godzinaList +
                '}';
    }
}
