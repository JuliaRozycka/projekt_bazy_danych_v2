package edu.ib.projekt_bazy_danych_v2;

import entity.Archiwum;
import entity.Terminy;
import entity.WidokUzytkownika;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class ZapisyAdminController {

    private ObservableList<Object> list = FXCollections.observableArrayList();

    @FXML
    private TextField odH;

    @FXML
    private TextField odM;

    @FXML
    private TextField doH;

    @FXML
    private TextField doM;

    @FXML
    private DatePicker datePC;

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, String> tcData;

    @FXML
    private TableColumn<Object, String> tcGodzina;


    public void btnDodajClicked(ActionEvent actionEvent) {
        int valueOdH = Integer.parseInt(odH.getText());
        int valueOdM = Integer.parseInt(odM.getText());
        int valueDoH = Integer.parseInt(doH.getText());
        int valueDoM = Integer.parseInt(doM.getText());
        String valueDate = String.valueOf(datePC.getValue());
        TimeSlotsGenerator generator = new TimeSlotsGenerator();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;
            List list = generator.generateTimeSlots(LocalTime.of(valueOdH,valueOdM), LocalTime.of(valueDoH,valueDoM));
            String forSql = "";
            for (int i = 0; i < list.size()-1;i++){
                forSql += "('"+valueDate+"','"+list.get(i)+"'), ";
            }
            forSql = forSql + "('"+valueDate+"','"+list.get(list.size()-1)+"')";
            String sql = "INSERT INTO terminy(data,godzina) VALUES "+ forSql;
            System.out.println(sql);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }
        table.getItems().clear();
        FetchDataFromTableZapisyAdmin fetchDataFromTable = new FetchDataFromTableZapisyAdmin();
        fetchDataFromTable.addData();
        list.addAll(Terminy.getTerms(fetchDataFromTable.idList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    public void btnUsunClicked(ActionEvent actionEvent) {
        int valueOdH = Integer.parseInt(odH.getText());
        int valueOdM = Integer.parseInt(odM.getText());
        int valueDoH = Integer.parseInt(doH.getText());
        int valueDoM = Integer.parseInt(doM.getText());
        String valueDate = String.valueOf(datePC.getValue());
        TimeSlotsGenerator generator = new TimeSlotsGenerator();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;
            List list = generator.generateTimeSlots(LocalTime.of(valueOdH,valueOdM), LocalTime.of(valueDoH,valueDoM));
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            Statement st = connection.createStatement();
            for (int i = 0; i < list.size();i++){
                String sql = "DELETE FROM terminy WHERE data='"+valueDate+"' AND godzina='"+list.get(i)+"';";
                System.out.println(sql);
                st.executeUpdate(sql);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        table.getItems().clear();
        FetchDataFromTableZapisyAdmin fetchDataFromTable = new FetchDataFromTableZapisyAdmin();
        fetchDataFromTable.addData();
        list.addAll(Terminy.getTerms(fetchDataFromTable.idList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    public void btnPowrotClicked(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("panel_admin.fxml")));
        Scene scene = new Scene(root, 700, 500);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void initialize() {
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        table.setItems(list);
        FetchDataFromTableZapisyAdmin fetchDataFromTable = new FetchDataFromTableZapisyAdmin();
        fetchDataFromTable.addData();
        list.addAll(Terminy.getTerms(fetchDataFromTable.idList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }
}