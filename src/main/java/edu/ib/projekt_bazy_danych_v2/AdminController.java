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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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


    public void btnArchiwumClicked(ActionEvent event) {
        table.getItems().clear();
        FetchDataFromTable fetchDataFromTable = new FetchDataFromTable();
        fetchDataFromTable.addData("Archiwum");
        list.addAll(Archiwum.getArchs(fetchDataFromTable.peselList, fetchDataFromTable.imieList,fetchDataFromTable.nazwiskoList,fetchDataFromTable.nazwaSzczepieniaList,fetchDataFromTable.producentList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    public void btnOczekujaceClicked(ActionEvent event) {
        table.getItems().clear();
        FetchDataFromTable fetchDataFromTable = new FetchDataFromTable();
        fetchDataFromTable.addData("oczekujace_szczepienia");
        list.addAll(OczekujaceSzczepienia.getOczek(fetchDataFromTable.peselList, fetchDataFromTable.imieList,fetchDataFromTable.nazwiskoList,fetchDataFromTable.nazwaSzczepieniaList,fetchDataFromTable.producentList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    public void btnNiezrealizowaneClicked(ActionEvent event) {
        table.getItems().clear();
        FetchDataFromTable fetchDataFromTable = new FetchDataFromTable();
        fetchDataFromTable.addData("niezrealizowane_szczepienia");
        list.addAll(NiezrealizowaneSzczepienia.getNiezreal(fetchDataFromTable.peselList, fetchDataFromTable.imieList,fetchDataFromTable.nazwiskoList,fetchDataFromTable.nazwaSzczepieniaList,fetchDataFromTable.producentList,fetchDataFromTable.dataList,fetchDataFromTable.godzinaList));
    }

    @FXML
    void initialize() {
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
}
