package edu.ib.projekt_bazy_danych_v2;

import entity.Terminy;
import entity.Uzytkownicy;
import entity.WidokUzytkownika;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class UzytkownikController {

    private final ObjectProperty contractBox = new SimpleObjectProperty(this, "contractBox");

    private ObservableList<Object> list = FXCollections.observableArrayList();
    private ObservableList<Object> list2 = FXCollections.observableArrayList();
    private ObservableList<Object> list3 = FXCollections.observableArrayList();
    private ObservableList<Object> list4 = FXCollections.observableArrayList();
    private ObservableList<Object> emptyList = FXCollections.observableArrayList();

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
    private ComboBox cbTermin;

    @FXML
    private ComboBox cbNazwa;

    @FXML
    private ComboBox cbProducent;



    @FXML
    void initialize() {
        cbTermin.setItems(list2);
        FetchDataFromTableZapisyAdmin fetchDataFromTable2 = new FetchDataFromTableZapisyAdmin();
        fetchDataFromTable2.addData();
        ArrayList listTerminy = new ArrayList<>();
        for(int i = 0; i<fetchDataFromTable2.dataList.size();i++){
            listTerminy.add(fetchDataFromTable2.dataList.get(i)+" "+fetchDataFromTable2.godzinaList.get(i));
        }
        list2.addAll(listTerminy);

        cbNazwa.setItems(list3);
        ArrayList listNazwy = new ArrayList<>();
        Szczepionki szczepionki = new Szczepionki();
        szczepionki.getHashmap();
        listNazwy = szczepionki.getKeys();
        list3.addAll(listNazwy);

        cbProducent.setDisable(true);


        cbNazwa.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            cbProducent.setDisable(false);
            cbProducent.setItems(emptyList);
            cbProducent.setItems(list4);
            ArrayList listProducenci;
            ArrayList emptyList;
            listProducenci = szczepionki.getValuesForKey(String.valueOf(newValue));
            emptyList = szczepionki.getValuesForKey(String.valueOf(oldValue));
            if (emptyList != null){
                list4.removeAll(emptyList);
            }
            list4.addAll(listProducenci);
                });

        /*cbProducent.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)->{
            if(newValue == null){
                System.out.println("nnic");
            }
        });*/







        /*System.out.println(property);

        cbNazwa.setOnAction(event ->{
            Object selected = cbNazwa.getSelectionModel().getSelectedItem();
            person.contractBoxProperty().set(selected);
        });*/

        /*cbProducent.setItems(list4);
        ArrayList listProducenci = new ArrayList<>();
        listProducenci = szczepionki.getValuesForKey(selectedValue);
        list4.addAll(listProducenci);*/

        tcNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwaSzczepienia"));
        tcProducent.setCellValueFactory(new PropertyValueFactory<>("producent"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(list);
        FetchDataFromTableUzyt fetchDataFromTableUzyt = new FetchDataFromTableUzyt();
        fetchDataFromTableUzyt.addData(StartController.getUzytkownikId());
        list.addAll(WidokUzytkownika.getWidok(fetchDataFromTableUzyt.idList,fetchDataFromTableUzyt.nazwaSzczepieniaList,fetchDataFromTableUzyt.producentList,fetchDataFromTableUzyt.dataList,fetchDataFromTableUzyt.godzinaList,fetchDataFromTableUzyt.statusList));
    }
}
