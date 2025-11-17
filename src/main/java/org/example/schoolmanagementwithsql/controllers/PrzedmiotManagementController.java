package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import org.example.schoolmanagementwithsql.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrzedmiotManagementController {

    private MainScreenController parentController;

    @FXML
    private BorderPane przedmiotManagementRoot;

    @FXML
    private Label titleLabel;

    @FXML
    private ListView<String> przedmiotListView;

    @FXML
    private ButtonBar actionButtonBar;

    public void setParentController(MainScreenController controller) {
        this.parentController = controller;
    }

    @FXML
    private void initialize() {
        loadPrzedmiotyFromDB();
    }

    private void loadPrzedmiotyFromDB() {
        przedmiotListView.getItems().clear();

        String sql = "SELECT NAZWA, OPIS FROM PRZEDMIOT ORDER BY ID_PRZEDMIOTU";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nazwa = rs.getString("NAZWA");
                String opis = rs.getString("OPIS");
                przedmiotListView.getItems().add(
                        opis == null || opis.isBlank()
                                ? nazwa
                                : nazwa + " — " + opis
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            przedmiotListView.getItems().add("Błąd: nie udało się pobrać przedmiotów z bazy.");
        }
    }
}
