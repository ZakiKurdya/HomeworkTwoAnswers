package com.example.homeworktwoanswers.Lecture12;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController implements Initializable {

    @FXML
    private TextField textFieldLoginName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label labelError;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonSubmit;

    private final HashMap<String, String> users = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Scanner in = new Scanner(new File("passwords.txt"));
            while (in.hasNextLine()) {
                String[] data = in.nextLine().split("~");
                users.put(data[0], data[1]);
            }
            in.close();
        } catch (FileNotFoundException ignored) {
        }
    }

    @FXML
    private void buttonSubmitHandle() {
        try {
            if (!textFieldLoginName.getText().isEmpty() && !passwordField.getText().isEmpty() && users.get(textFieldLoginName.getText()).equals(passwordField.getText())) {
                labelError.setText("You are welcome ...");
                new MainScreen().show();
                textFieldLoginName.setText("");
                passwordField.setText("");
            } else
                labelError.setText("Invalid username or password");
        } catch (RuntimeException ignored) {
        }
    }

    @FXML
    private void buttonClearHandle() {
        textFieldLoginName.setText("");
        passwordField.setText("");
        labelError.setText("");
    }
}
// Zaki Kurdya