package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class MainScreenController {

    // Pola pasujące do fx:id
    @FXML
    private BorderPane rootPane;

    @FXML
    private HBox bottomHBox;



    // Pomocnicza funkcja tworząca dolne przyciski
    // TODO dodać funkcjonalność do przycisku i przekazywanie funkcji jako parametr
    @FXML
    private void createButton(String buttonName) {
        Button newButton = new Button(buttonName);
        newButton.getStyleClass().add("bottom-button"); // Ustawienie stylu CSS dla przycisku

        HBox.setHgrow(newButton, Priority.ALWAYS); // Automatyczne dostosowywanie rozmiaru
        newButton.setMaxHeight(Double.MAX_VALUE);

        bottomHBox.getChildren().add(newButton);
    }



    @FXML
    public void initialize() {

        // Utworzenie HBoxa na dole okna (kontener na przyciski)
        bottomHBox.prefHeightProperty().bind(rootPane.heightProperty().divide(5)); // Przycisk zajmuje 1/5 wysokości ekranu
        bottomHBox.setSpacing(5);
        bottomHBox.setPadding(new Insets(5));

        // Utworzenie przycisków
        createButton("Uczniowie");
        createButton("Przedmioty");
        createButton("Klasy");
        createButton("Nauczyciele");

        // Listener pozwalający na dobranie rozmiaru czcionki w przyciskach
        rootPane.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            double newFontSize = newWidth.doubleValue() / 50.0; // Czcionka jest ustawiana na 1/50 szerokości okna
            if (newFontSize < 10) newFontSize = 10; // Minimalnie 10px
            if (newFontSize > 30) newFontSize = 50; // Maksymalnie 50px

            String dynamicStyle = String.format(
                    "-fx-font-size: %.2fpx; " +
                            "-fx-max-width: infinity; " +
                            "-fx-pref-width: 0;",
                    newFontSize
            );

            for (Node node : bottomHBox.getChildren()) { // Ustawienie stylu CSS dla przycisków
                if (node instanceof Button) {
                    node.setStyle(dynamicStyle);
                }
            }
        });
    }
}