package com.example.homeworktwoanswers.Lecture11;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class MainScreen extends Stage {
    private final TextArea textArea;
    private final Slider sliderFontSize;
    private File selectedFile;

    public MainScreen() {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemClose = new MenuItem("Close");
        MenuItem menuItemSave = new MenuItem("Save");
        MenuItem menuItemExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuItemOpen, menuItemClose, menuItemSave, menuItemExit);

        Menu menuColor = new Menu("Color");
        MenuItem menuItemGold = new MenuItem("Gold");
        MenuItem menuItemCyan = new MenuItem("Cyan");
        MenuItem menuItemRed = new MenuItem("Red");
        MenuItem menuItemReset = new MenuItem("Reset");
        menuColor.getItems().addAll(menuItemGold, menuItemCyan, menuItemRed, menuItemReset);

        Menu menuHelp = new Menu("Help");
        MenuItem menuItemAbout = new MenuItem("About");
        menuHelp.getItems().add(menuItemAbout);

        menuBar.getMenus().addAll(menuFile, menuColor, menuHelp);

        textArea = new TextArea("");
        textArea.setMaxHeight(300);
        textArea.setMaxWidth(500);
        sliderFontSize = new Slider(5, 35, 12);
        sliderFontSize.setMajorTickUnit(5);
        sliderFontSize.setMinorTickCount(4);
        sliderFontSize.setShowTickLabels(true);
        sliderFontSize.setShowTickMarks(true);
        sliderFontSize.setSnapToTicks(true);
        ComboBox<String> comboBoxLinks = new ComboBox<>();
        comboBoxLinks.getItems().addAll("Instructor GitHub", "My GitHub", "JavaFX Guide");
        comboBoxLinks.getSelectionModel().select(1);

        WebView webView = new WebView();

        HBox hBox1 = new HBox(15, comboBoxLinks, webView);

        VBox vBox1 = new VBox(15, textArea, sliderFontSize, hBox1);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(vBox1);

        Scene scene = new Scene(borderPane, 1000, 700);
        this.setScene(scene);
        this.setTitle("Advanced JavaFX Screen");
        this.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("code.png"))));

        sliderFontSize.valueProperty().addListener(event ->
                textArea.setStyle("-fx-font-size: " + sliderFontSize.getValue() + "pt"));
        comboBoxLinks.setOnAction(event -> {
            switch (comboBoxLinks.getSelectionModel().getSelectedIndex()){
                case 0 -> webView.getEngine().load("https://github.com/aashgar");
                case 1 -> webView.getEngine().load("https://github.com/ZakiKurdya");
                case 2 -> webView.getEngine().load("https://openjfx.io/#");
            }
        });

        menuItemOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("src/"));
            selectedFile = fileChooser.showOpenDialog(this);
            try {
                Scanner s = new Scanner(selectedFile);
                while (s.hasNextLine()) {
                    textArea.appendText(s.nextLine() + "\n");
                }
                s.close();
            } catch (FileNotFoundException ignored) {}
        });

        menuItemClose.setOnAction(event -> {
            textArea.setText("");
            this.selectedFile = new File("");
        });

        menuItemSave.setOnAction(event -> {
            try {
                FileWriter fileWriter = new FileWriter(selectedFile);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (IOException ignored) {}
        });

        menuItemExit.setOnAction(event -> System.exit(0));

        menuItemGold.setOnAction(event -> textArea.lookup(".content").setStyle("-fx-background-color: Gold"));
        menuItemCyan.setOnAction(event -> textArea.lookup(".content").setStyle("-fx-background-color: Cyan"));
        menuItemRed.setOnAction(event -> textArea.lookup(".content").setStyle("-fx-background-color: Red"));
        menuItemReset.setOnAction(event -> textArea.lookup(".content").setStyle("-fx-background-color: none"));

        menuItemAbout.setOnAction(event -> new About().show());
    }
}
// Zaki Kurdya