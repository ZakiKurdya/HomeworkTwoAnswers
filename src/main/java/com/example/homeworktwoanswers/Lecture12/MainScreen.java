package com.example.homeworktwoanswers.Lecture12;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainScreen extends Stage{
    public MainScreen() {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Scene scene;

        try {
            scene = new Scene(fXMLLoader.load());
            this.setScene(scene);
            this.setTitle("Main Screen with Scene Builder");
            this.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("code.png"))));
        } catch (IOException ignored) {}
    }
}
// Zaki Kurdya