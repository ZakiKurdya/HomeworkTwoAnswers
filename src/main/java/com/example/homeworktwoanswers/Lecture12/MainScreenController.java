package com.example.homeworktwoanswers.Lecture12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxLinks;
    @FXML
    private Slider sliderFontSize;
    @FXML
    private TextArea textArea;
    @FXML
    private WebView webView;
    @FXML
    private MenuItem menuItemOpen, menuItemClose, menuItemSave, menuItemExit, menuItemGold, menuItemCyan, menuItemRed, menuItemReset, menuItemAbout;

    private File selectedFile;

    Stage aboutStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxLinks.getItems().addAll("Instructor GitHub", "My GitHub", "JavaFX Guide");
        comboBoxLinks.getSelectionModel().select(1);
    }

    @FXML
    private void sliderFontSizeHandle() {
        textArea.setStyle("-fx-font-size: " + sliderFontSize.getValue() + "pt");
    }

    @FXML
    private void comboBoxLinksHandle() {
        switch (comboBoxLinks.getSelectionModel().getSelectedIndex()) {
            case 0 -> webView.getEngine().load("https://github.com/aashgar");
            case 1 -> webView.getEngine().load("https://github.com/ZakiKurdya");
            case 2 -> webView.getEngine().load("https://openjfx.io/#");
        }
    }

    @FXML
    private void menuItemsHandle(ActionEvent event) {
        if (event.getSource() == menuItemOpen) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("src/"));
            selectedFile = fileChooser.showOpenDialog(new Stage());
            try {
                Scanner s = new Scanner(selectedFile);
                while (s.hasNextLine()) {
                    textArea.appendText(s.nextLine() + "\n");
                }
                s.close();
            } catch (FileNotFoundException ignored) {
            }
        } else if (event.getSource() == menuItemClose) {
            textArea.setText("");
            this.selectedFile = new File("");
        } else if (event.getSource() == menuItemSave) {
            try {
                FileWriter fileWriter = new FileWriter(selectedFile);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (IOException ignored) {
            }
        } else if (event.getSource() == menuItemExit) {
            System.exit(0);
        } else if (event.getSource() == menuItemGold) {
            textArea.lookup(".content").setStyle("-fx-background-color: Gold");
        } else if (event.getSource() == menuItemCyan) {
            textArea.lookup(".content").setStyle("-fx-background-color: Cyan");
        } else if (event.getSource() == menuItemRed) {
            textArea.lookup(".content").setStyle("-fx-background-color: Red");
        } else if (event.getSource() == menuItemReset) {
            textArea.lookup(".content").setStyle("-fx-background-color: none");
        } else if (event.getSource() == menuItemAbout) {
            aboutStage = new AboutScreen();
            aboutStage.show();
        }
    }
}
// Zaki Kurdya