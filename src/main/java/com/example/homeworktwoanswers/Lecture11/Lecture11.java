package com.example.homeworktwoanswers.Lecture11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Lecture11 extends Application {
    private Label labelError;
    private TextField textFieldLoginName;
    private PasswordField passwordField;
    private Button buttonSubmit, buttonCancel;
    private VBox vBox2;
    private final HashMap<String, String> users = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            Scanner in = new Scanner(new File("passwords.txt"));
            while (in.hasNextLine()) {
                String[] data = in.nextLine().split("~");
                users.put(data[0], data[1]);
            }
            in.close();
        } catch (FileNotFoundException ignored) {}

        Label labelTitle = new Label("Login Information");
        labelTitle.setId("label-title");

        textFieldLoginName = new TextField();
        textFieldLoginName.setPromptText("Login Name");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        labelError = new Label();
        labelError.setId("label-error");

        VBox vBox1 = new VBox(10, labelTitle, textFieldLoginName, passwordField, labelError);
        vBox1.setAlignment(Pos.CENTER);

        buttonSubmit = new Button("Submit");
        buttonCancel = new Button("Cancel");
        buttonSubmit.setOnAction(new MyEventHandler());
        buttonCancel.setOnAction(new MyEventHandler());

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(buttonSubmit, buttonCancel);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);

        vBox2 = new VBox(10, vBox1, hBox1);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setPadding(new Insets(10, 20, 10, 20));
        vBox2.getStyleClass().add("vbox");

        FlowPane flowPane = new FlowPane(vBox2);
        flowPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flowPane, 380, 300);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Lecture11.class.getResourceAsStream("code.png"))));
        primaryStage.setTitle("Login Screen");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MyEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == buttonSubmit) {
                try {
                    if (users.get(textFieldLoginName.getText()).equals(passwordField.getText())) {
                        labelError.setText("Valid User");
                        labelError.setTextFill(Color.DARKGREEN);
                        new MainScreen().show();
                        textFieldLoginName.setText("");
                        passwordField.setText("");
                        vBox2.setStyle("-fx-border-color: none;");
                    } else {
                        labelError.setText("Invalid Data");
                        labelError.setTextFill(Color.DARKRED);
                        vBox2.setStyle("-fx-border-color: DarkRed;");
                    }
                }catch (Exception ignored){
                    labelError.setText("Invalid Data");
                    labelError.setTextFill(Color.DARKRED);
                    vBox2.setStyle("-fx-border-color: DarkRed;");
                }
            } else if (event.getSource() == buttonCancel) {
                textFieldLoginName.setText("");
                passwordField.setText("");
                labelError.setText("");
                vBox2.setStyle("-fx-border-color: none;");
            }
        }
    }
}
// Zaki Kurdya