package ru.ac.uniyar.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ac.uniyar.utils.DataHandler;

import java.io.IOException;

public class LabApplication extends Application {
    public Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LabApplication.class.getResource("main-page.fxml"));
        scene = new Scene(fxmlLoader.load(), 1300, 800);
        stage.setTitle("Machines Lab");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataHandler.init();
        launch();
    }
}