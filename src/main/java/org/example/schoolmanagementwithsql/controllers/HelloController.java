package org.example.schoolmanagementwithsql.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {




        welcomeText.setText("Do widzenia");
    }
}
