package edu.ib.projekt_bazy_danych_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="connectButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField;

    @FXML // fx:id="userTextField"
    private TextField peselField; // Value injected by FXMLLoader

    private ConnectionUtil dbUtil;



    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert peselField != null : "fx:id=\"peselField\" was not injected: check your FXML file 'dbFX.fxml'.";
    }


    public void login(ActionEvent event) throws SQLException, ClassNotFoundException{
        dbUtil = new ConnectionUtil(peselField.getText(), passwordField.getText(), consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + peselField.getText() + "\"." + "\n");
        loginButton.setDisable(true);
    }
}
