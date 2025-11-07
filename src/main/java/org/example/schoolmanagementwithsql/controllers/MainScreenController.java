package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;


public class MainScreenController {

    // Pola pasujące do fx:id
    @FXML
    private StackPane rootPane;

    @FXML
    private HBox bottomHBox;

    /**
     * Funkcja pomocnicza, która resetuje wszystkie przyciski do stanu "unclicked".
     * Usuwa klasę 'clicked' i dodaje 'unclicked'.
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


    @FXML
    private void createButton(String buttonName) {
        Button newButton = new Button(buttonName);


        newButton.getStyleClass().addAll("bottom-button-base", "bottom-button-unclicked");

        newButton.setOnMouseClicked(event -> {
            unclickButtons();

            newButton.getStyleClass().remove("bottom-button-unclicked");
            newButton.getStyleClass().add("bottom-button-clicked");
        });

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

            // Zmieniłem '50' na '30', zakładając, że to była literówka.
            if (newFontSize > 50) newFontSize = 50; // Maksymalnie 50px

            String dynamicStyle = String.format(
                    "-fx-font-size: %.2fpx; " +
                            "-fx-max-width: infinity; " +
                            "-fx-pref-width: 0;"+
                            "-fx-effect: dropshadow( gaussian , gray , 0 , 1.0 , 0 , 0 );"
                    ,
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