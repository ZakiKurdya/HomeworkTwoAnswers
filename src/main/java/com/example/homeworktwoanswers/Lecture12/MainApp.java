package com.example.homeworktwoanswers.Lecture12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));

        Scene scene = new Scene(fXMLLoader.load());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("code.png"))));
        primaryStage.setTitle("Login Screen");
        primaryStage.show();
    }
}
// Zaki Kurdya