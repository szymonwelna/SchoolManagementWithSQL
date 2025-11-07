package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;


public class MainScreenController {

    // Pola pasujące do fx:id
    @FXML
    private StackPane rootPane;

    @FXML
    private BorderPane mainContentPane; // Kontener na ładowane widoki

    @FXML
    private HBox bottomHBox;

    /**
     * Metoda ładująca widok z pliku FXML.
     */
    private void loadView(String fxmlFileName) {
        try {
            String fullPath = "/" + getClass().getPackage().getName().replace('.', '/') + "/" + fxmlFileName;

            URL fileUrl = getClass().getResource("/org/example/schoolmanagementwithsql/" + fxmlFileName);

            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("Nie można znaleźć pliku FXML! Sprawdzana ścieżka: " + fullPath);
            }

            Node view = FXMLLoader.load(fileUrl);
            mainContentPane.setCenter(view);

        } catch (IOException e) {
            System.err.println("Błąd podczas ładowania FXML: " + fxmlFileName);
            e.printStackTrace();
        }
    }

    /**
     * Funkcja pomocnicza, która resetuje wszystkie przyciski do stanu "unclicked".
     */
    private void unclickButtons() {
        for (Node node : bottomHBox.getChildren()) {
            if (node instanceof Button) {
                node.getStyleClass().remove("bottom-button-clicked");

                if (!node.getStyleClass().contains("bottom-button-unclicked")) {
                    node.getStyleClass().add("bottom-button-unclicked");
                }
            }
        }
    }

    /**
     * Funkcja dodająca nowy przycisk do HBoxa na dole okna.
     */
    @FXML
    private void createButton(String buttonName, String fxmlViewName) {
        Button newButton = new Button(buttonName);
        newButton.getStyleClass().addAll("bottom-button-base", "bottom-button-unclicked");

        newButton.setOnMouseClicked(event -> {
            unclickButtons();
            newButton.getStyleClass().remove("bottom-button-unclicked");
            newButton.getStyleClass().add("bottom-button-clicked");

            // Załadowanie okna
            loadView(fxmlViewName);

        });

        HBox.setHgrow(newButton, Priority.ALWAYS); // Automatyczne dostosowywanie rozmiaru
        newButton.setMaxHeight(Double.MAX_VALUE);
        bottomHBox.getChildren().add(newButton);
    }

    @FXML
    public void initialize() {

        // Utworzenie HBoxa na dole okna (kontener na przyciski)
        bottomHBox.prefHeightProperty().bind(rootPane.heightProperty().divide(5));
        bottomHBox.setSpacing(5);
        bottomHBox.setPadding(new Insets(5));

        // Utworzenie przycisków
        createButton("Uczniowie", "student_management_view.fxml");
        createButton("Przedmioty", "student_management_view.fxml");
        createButton("Klasy", "student_management_view.fxml");
        createButton("Nauczyciele", "student_management_view.fxml"); // Tymczasowo podane tylko jedno istniejące okno

        // Listener pozwalający na dobranie rozmiaru czcionki w przyciskach
        rootPane.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            double fontSize = newWidth.doubleValue() / 50.0;
            if (fontSize < 10) fontSize = 10;

            if (fontSize > 50) fontSize = 50; // Maksymalnie 50px

            // StylCSS pozwalający na dynamiczne ustawienie rozmiaru czcionki
            String dynamicStyle = String.format(
                    "-fx-font-size: %.2fpx; " +
                            "-fx-max-width: infinity; " +
                            "-fx-pref-width: 0;"+
                            "-fx-effect: dropshadow( gaussian , gray , 0 , 1.0 , 0 , 0 );"
                    ,
                    fontSize
            );

            for (Node node : bottomHBox.getChildren()) {
                if (node instanceof Button) {
                    node.setStyle(dynamicStyle);
                }
            }
        });
    }
}