package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class StudentManagementController {

    // --- KLUCZOWE POLE: Komunikacja z aplikacją główną ---
    private MainScreenController parentController;

    // --- POPRAWKA A i B: Wszystkie pola muszą mieć @FXML i pasować do FXML ---

    @FXML // Odpowiada głównemu BorderPane w student_management_view.fxml
    private BorderPane studentManagementRoot;

    @FXML // Odpowiada Label w sekcji <top> (jeśli dodasz jej fx:id="titleLabel")
    private Label titleLabel;

    @FXML // Odpowiada ListView w sekcji <center>
    private ListView<String> studentListView;

    @FXML // Odpowiada ButtonBar w sekcji <bottom>
    private ButtonBar actionButtonBar;

    // --- Komunikacja ---

    /**
     * Ustawia referencję do kontrolera głównego (MainScreenController).
     * Wywoływane jest z MainScreenController.loadView().
     */
    public void setParentController(MainScreenController controller) {
        this.parentController = controller;
    }

    // --- Inicjalizacja ---

    /**
     * Metoda wywoływana automatycznie przez FXMLLoader po załadowaniu FXML.
     */
    @FXML // POPRAWKA C: Metoda initialize musi mieć @FXML
    private void initialize() {
        // Logika inicjalizacji np. ładowanie danych do ListView

        // Przykładowa logika: Wyślij dane do ListView
        studentListView.getItems().addAll("Anna Kowalska", "Bartosz Nowak", "Celina Wójcik");
    }
}