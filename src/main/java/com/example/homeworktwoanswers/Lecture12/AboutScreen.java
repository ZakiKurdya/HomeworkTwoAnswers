package com.example.homeworktwoanswers.Lecture12;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AboutScreen extends Stage {
    public AboutScreen() {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Scene scene;

        try {
            scene = new Scene(fXMLLoader.load());
            this.setScene(scene);
            this.setTitle("About");
            this.setResizable(false);
            this.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("code.png"))));
        } catch (IOException ignored) {
        }
    }
}
// Zaki Kurdya