package edu.ib.projekt_bazy_danych_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnZaloguj;

    @FXML
    private Button btnZarejestruj;

    @FXML
    private Button btnLogowanie;

    @FXML
    private Button btnRejestracja;

    @FXML
    private TextField tfImie;

    @FXML
    private TextField tfNazwisko;

    @FXML
    private TextField tfPesel;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfHaslo;

    @FXML
    private TextField tfHaslo2;

    @FXML
    private TextField tfLog;

    @FXML
    private TextField tfHas;

    private ConnectionUtil dbUtil;

    @FXML
    void btnZalogujClicked(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logowanie.fxml")));
        Scene scene = new Scene(root, 400, 300);
        Stage primaryStage2 = new Stage();
        primaryStage2.setScene(scene);
        primaryStage2.show();
    }

    @FXML
    void btnZarejestrujClicked(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("rejestracja.fxml")));
        Scene scene = new Scene(root, 400, 300);
        Stage primaryStage3 = new Stage();
        primaryStage3.setScene(scene);
        primaryStage3.show();
    }

    public void btnRejestracjaClicked(ActionEvent event) throws IOException {
        if (Objects.equals(tfImie.getText(), "") || Objects.equals(tfNazwisko.getText(), "") ||
                Objects.equals(tfPesel.getText(), "") || Objects.equals(tfLogin.getText(), "") ||
                Objects.equals(tfHaslo.getText(), "") || Objects.equals(tfHaslo2.getText(), "")) {
            JOptionPane.showMessageDialog(null, "Prosze sie poprawic i wypelnienie naprawic");
        } else if (!Objects.equals(tfHaslo.getText(), tfHaslo2.getText())) {
            JOptionPane.showMessageDialog(null, "Zjebales gosciu");
        } else {
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logowanie.fxml")));
            Scene scene = new Scene(root, 400, 300);
            Stage primaryStage2 = new Stage();
            primaryStage2.setScene(scene);
            primaryStage2.show();
        }
    }

    public void btnLogowanieClicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (Objects.equals(tfHas.getText(), "") || Objects.equals(tfLog.getText(), "")) {
            JOptionPane.showMessageDialog(null, "Pusty masz leb jak te pola cwelu");
        } else {
            //((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            dbUtil = new ConnectionUtil(tfLog.getText(), tfHas.getText());
            dbUtil.dbConnect();


            //rozroznienie uzytkownika i admina
            //wywalanie okna poprzedniego
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("panel_uzytkownika.fxml")));
            Scene scene = new Scene(root, 400, 300);
            Stage primaryStage4 = new Stage();
            primaryStage4.setScene(scene);
            primaryStage4.show();
        }
    }


/*    public void login(ActionEvent event) throws SQLException, ClassNotFoundException{
        dbUtil = new ConnectionUtil(peselField.getText(), passwordField.getText(), consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + peselField.getText() + "\"." + "\n");
        loginButton.setDisable(true);
    }*/



}
