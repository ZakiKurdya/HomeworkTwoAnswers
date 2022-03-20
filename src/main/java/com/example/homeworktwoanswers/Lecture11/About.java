package com.example.homeworktwoanswers.Lecture11;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class About extends Stage {
    public About() {
        Label info = new Label("""
                Hello there!
                I'm Zaki Kurdya, and my ID is 120200706.
                I am enrolled in the Programming 3 Course at IUG...""");
        info.setPadding(new Insets(5));

        Button ok = new Button("OK");
        ok.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-background-color: #3232c2; -fx-text-fill: white; -fx-pref-width: 100");
        ok.setOnAction(event -> this.close());

        VBox vBox = new VBox(12, info, ok);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-font-size: 12pt");

        Scene scene = new Scene(vBox, 400, 150);
        this.setScene(scene);
        this.setTitle("About");
        this.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("code.png"))));
        this.setResizable(false);
        this.show();
    }
}
// Zaki Kurdya