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

public class StudentManagementController {

    private MainScreenController parentController;

    @FXML
    private BorderPane studentManagementRoot;

    @FXML
    private Label titleLabel;

    @FXML
    private ListView<String> studentListView;

    @FXML
    private ButtonBar actionButtonBar;

    public void setParentController(MainScreenController controller) {
        this.parentController = controller;
    }

    @FXML
    private void initialize() {
        loadStudentsFromDB();
    }

    private void loadStudentsFromDB() {
        studentListView.getItems().clear();

        String sql = "SELECT IMIE, NAZWISKO FROM UCZEN ORDER BY ID_UCZNIA";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String imie = rs.getString("IMIE");
                String nazwisko = rs.getString("NAZWISKO");
                studentListView.getItems().add(imie + " " + nazwisko);
            }

        } catch (Exception e) {
            e.printStackTrace();
            studentListView.getItems().add("Błąd: nie udało się pobrać uczniów z bazy.");
        }
    }
}
