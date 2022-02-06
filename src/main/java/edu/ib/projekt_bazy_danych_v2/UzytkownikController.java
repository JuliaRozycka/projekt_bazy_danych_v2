package edu.ib.projekt_bazy_danych_v2;

import entity.Uzytkownicy;
import entity.WidokUzytkownika;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

public class UzytkownikController {

    private ObservableList<Object> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, String> tcNazwa;

    @FXML
    private TableColumn<Object, String> tcProducent;

    @FXML
    private TableColumn<Object, String> tcData;

    @FXML
    private TableColumn<Object, String> tcGodzina;

    @FXML
    private TableColumn<Object, String> tcStatus;

    @FXML
    void initialize() {
        tcNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwaSzczepienia"));
        tcProducent.setCellValueFactory(new PropertyValueFactory<>("producent"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(list);
        FetchDataFromTableUzyt fetchDataFromTableUzyt = new FetchDataFromTableUzyt();
        fetchDataFromTableUzyt.addData();
        list.addAll(WidokUzytkownika.getWidok(fetchDataFromTableUzyt.idList,fetchDataFromTableUzyt.nazwaSzczepieniaList,fetchDataFromTableUzyt.producentList,fetchDataFromTableUzyt.dataList,fetchDataFromTableUzyt.godzinaList,fetchDataFromTableUzyt.statusList));
    }

/*    public static void initData(Uzytkownicy uzytkownik) {
        Label labelUser = new Label();
        labelUser.setText(String.valueOf(uzytkownik.getId()));
    }*/
}
