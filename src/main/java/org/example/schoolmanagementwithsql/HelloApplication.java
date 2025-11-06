package org.example.schoolmanagementwithsql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main_screen_view.fxml"));

        // Pobranie wymiarów ekranu
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        // Domyślne ustawienie zmaksymalizowanego okna
        stage.setMaximized(true);
        // Ustawienie nazwy okna
        stage.setTitle("School Management");


        // Utworzenie Sceny, ustawienie wymiarów okna (domyślnie 1/2 ekranu)
        Scene scene = new Scene(fxmlLoader.load(), screenSize.getWidth()/2, screenSize.getHeight()/2);
        stage.setScene(scene);
        stage.show();
    }
}
