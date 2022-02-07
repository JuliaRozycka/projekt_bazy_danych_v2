package edu.ib.projekt_bazy_danych_v2;

import entity.Archiwum;
import entity.NiezrealizowaneSzczepienia;
import entity.OczekujaceSzczepienia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class AdminController {



    private ObservableList<Object> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, String> tcPesel;

    @FXML
    private TableColumn<Object, String> tcImie;

    @FXML
    private TableColumn<Object, String> tcNazwisko;

    @FXML
    private TableColumn<Object, String> tcNazwa;

    @FXML
    private TableColumn<Object, String> tcProducent;

    @FXML
    private TableColumn<Object, String> tcData;

    @FXML
    private TableColumn<Object, String> tcGodzina;

    @FXML
    private TextField tfPesel;

    @FXML
    private DatePicker dpData;

    @FXML
    private Button btnOznaczNie;

    @FXML
    private Button btnOznaczTak;

    public void btnArchiwumClicked(ActionEvent event) {
        btnOznaczTak.setDisable(true);
        btnOznaczNie.setDisable(true);
        table.getItems().clear();
        FetchDataFromTable fetchDataFromTable = new FetchDataFromTable();
        fetchDataFromTable.addData("Archiwum",tfPesel.getText(), String.valueOf(dpData.getValue()));
        list.addAll(Archiwum.getArchs(fetchDataFromTable.peselList, fetchDataFromTable.imieList,fetchDataFromTable.nazwiskoList,fetchDataFromTable.nazwaSzczepieniaList,fetchDataFromTable.producentList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    public void btnOczekujaceClicked(ActionEvent event) {
        btnOznaczTak.setDisable(false);
        btnOznaczNie.setDisable(false);
        table.getItems().clear();
        FetchDataFromTable fetchDataFromTable = new FetchDataFromTable();
        fetchDataFromTable.addData("oczekujace_szczepienia",tfPesel.getText(), String.valueOf(dpData.getValue()));
        list.addAll(OczekujaceSzczepienia.getOczek(fetchDataFromTable.peselList, fetchDataFromTable.imieList,fetchDataFromTable.nazwiskoList,fetchDataFromTable.nazwaSzczepieniaList,fetchDataFromTable.producentList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    public void btnNiezrealizowaneClicked(ActionEvent event) {
        btnOznaczTak.setDisable(true);
        btnOznaczNie.setDisable(true);
        table.getItems().clear();
        FetchDataFromTable fetchDataFromTable = new FetchDataFromTable();
        fetchDataFromTable.addData("niezrealizowane_szczepienia",tfPesel.getText(), String.valueOf(dpData.getValue()));
        list.addAll(NiezrealizowaneSzczepienia.getNiezreal(fetchDataFromTable.peselList, fetchDataFromTable.imieList,fetchDataFromTable.nazwiskoList,fetchDataFromTable.nazwaSzczepieniaList,fetchDataFromTable.producentList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    @FXML
    void initialize() {
        btnOznaczTak.setDisable(true);
        btnOznaczNie.setDisable(true);
        tcPesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        tcImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        tcNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        tcNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwaSzczepienia"));
        tcProducent.setCellValueFactory(new PropertyValueFactory<>("producent"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        /*FilteredList<Object> filteredData = new FilteredList<>(list, p->true);
        tfPesel.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                if (person.getPesel().contains(newValue)) {
                    return true;
                }
                return false;
            });
        }));
        SortedList<Object> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());*/
        table.setItems(list);
    }

    public void btnZapisyClicked(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("zapisy_admin.fxml")));
        Scene scene = new Scene(root, 700, 500);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void btnOznaczTakClicked(ActionEvent event) {

        OczekujaceSzczepienia oczekujaceSzczepienia = (OczekujaceSzczepienia) table.getSelectionModel().getSelectedItem();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;

            String sql = ("UPDATE wizyty w JOIN pacjenci p ON p.pesel = w.pesel_pacjenci " +
                    "JOIN szczepienia s ON s.id=w.id_szczepienia SET s.status='zrealizowane' WHERE p.pesel='")
                            .concat(oczekujaceSzczepienia.getPesel()+"' AND s.nazwa_szczepienia = '")
                            .concat(oczekujaceSzczepienia.getNazwaSzczepienia()+"' AND s.producent = '")
                            .concat(oczekujaceSzczepienia.getProducent()+"' AND w.data = '")
                            .concat(oczekujaceSzczepienia.getData()+"' AND w.godzina = '")
                            .concat(oczekujaceSzczepienia.getGodzina()+"';");
            System.out.println(sql);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }

        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());






        System.out.println(table.getSelectionModel().getSelectedItem());

    }

    public void btnOznaczNieClicked(ActionEvent event) {

        OczekujaceSzczepienia oczekujaceSzczepienia = (OczekujaceSzczepienia) table.getSelectionModel().getSelectedItem();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;

            String sql = ("UPDATE wizyty w JOIN pacjenci p ON p.pesel = w.pesel_pacjenci " +
                    "JOIN szczepienia s ON s.id=w.id_szczepienia SET s.status='niezrealizowane' WHERE p.pesel='")
                    .concat(oczekujaceSzczepienia.getPesel()+"' AND s.nazwa_szczepienia = '")
                    .concat(oczekujaceSzczepienia.getNazwaSzczepienia()+"' AND s.producent = '")
                    .concat(oczekujaceSzczepienia.getProducent()+"' AND w.data = '")
                    .concat(oczekujaceSzczepienia.getData()+"' AND w.godzina = '")
                    .concat(oczekujaceSzczepienia.getGodzina()+"';");
            System.out.println(sql);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }

        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());

    }
}
