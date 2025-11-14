package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class StudentManagementController {

    // Komunikacja z aplikacją główną
    private MainScreenController parentController;

    @FXML // Odpowiada głównemu BorderPane w student_management_view.fxml
    private BorderPane studentManagementRoot;

    @FXML // Odpowiada Label w sekcji <top>
    private Label titleLabel;

    @FXML // Odpowiada ListView w sekcji <center>
    private ListView<String> studentListView;

    @FXML // Odpowiada ButtonBar w sekcji <bottom>
    private ButtonBar actionButtonBar;

    /**
     * Ustawia referencję do kontrolera głównego (MainScreenController).
     * Wywoływane jest z MainScreenController.loadView().
     */
    public void setParentController(MainScreenController controller) {
        this.parentController = controller;
    }

    /**
     * Metoda wywoływana automatycznie przez FXMLLoader po załadowaniu FXML.
     */
    @FXML
    private void initialize() {
        /* TODO tutaj dodaj wczytywanie listy uczniów z bazy danych,
                możesz ewentualnie napisać do tego osobną funkcję,
                żeby nie było tu za dużo nawalone
        */
        studentListView.getItems().addAll("Anna Kowalska", "Bartosz Nowak", "Celina Wójcik");
    }
}