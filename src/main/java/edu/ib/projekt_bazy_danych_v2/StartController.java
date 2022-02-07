package edu.ib.projekt_bazy_danych_v2;

import entity.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController {
    private static int uzytkownikId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfImie;

    @FXML
    private TextField tfNazwisko;

    @FXML
    private TextField tfPesel;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfNumer;

    @FXML
    private TextField tfHaslo;

    @FXML
    private TextField tfHaslo2;

    @FXML
    private TextField tfLog;

    @FXML
    private TextField tfHas;

    private ConnectionUtil dbUtil;

    public static int getUzytkownikId() {
        return uzytkownikId;
    }

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

    public void btnRejestracjaClicked(ActionEvent event) throws IOException, SQLException {
        if (Objects.equals(tfImie.getText(), "") || Objects.equals(tfNazwisko.getText(), "") ||
                Objects.equals(tfPesel.getText(), "") || Objects.equals(tfNumer.getText(), "") ||
                Objects.equals(tfLogin.getText(), "") || Objects.equals(tfHaslo.getText(), "")) {
            JOptionPane.showMessageDialog(null, "Prosze sie poprawic i wypelnienie naprawic");
        } else if (!Objects.equals(tfHaslo.getText(), tfHaslo2.getText())) {
            JOptionPane.showMessageDialog(null, "Zjebales gosciu");
        } else {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            String query = "CALL addPacjent(?,?,?,?,?,?)";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1,tfLogin.getText());
            stmt.setString(2,tfHaslo.getText());
            stmt.setString(3,tfNumer.getText());
            stmt.setString(4,tfPesel.getText());
            stmt.setString(5,tfImie.getText());
            stmt.setString(6,tfNazwisko.getText());

            stmt.executeQuery();

            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            JOptionPane.showMessageDialog(null, "Rejestracja przebiegła pomyślnie. Teraz się zaloguj i miłej zabawy XD");
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
            dbUtil = new ConnectionUtil(tfLog.getText(), tfHas.getText());
            dbUtil.dbConnect();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/punkt_szczepien?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET");
            String sql = "SELECT * FROM Pracownicy WHERE login='" + tfLog.getText() + "'";
            String sql2 = "SELECT id FROM Uzytkownicy WHERE login='" + tfLog.getText() + "'";
            Statement stmt = connection.prepareStatement(sql);
            Statement stmt2 = connection.prepareStatement(sql2);

            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs2 = stmt2.executeQuery(sql2);

            while (rs2.next()){
                uzytkownikId = Integer.parseInt(rs2.getString(1));
                System.out.println(rs2.getString(1));
            }



            if (rs.next()) {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                Parent root = FXMLLoader.load((getClass().getResource("panel_admin.fxml")));
                Scene scene = new Scene(root, 700, 500);
                Stage primaryStage4 = new Stage();
                primaryStage4.setScene(scene);
                primaryStage4.show();
            } else {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                Parent root = FXMLLoader.load((getClass().getResource("panel_uzytkownika.fxml")));
                Scene scene = new Scene(root, 700, 500);

                Stage primaryStage4 = new Stage();
                primaryStage4.setScene(scene);
                primaryStage4.show();
            }
        }
    }

}