package com.example.homeworktwoanswers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class Lecture9 extends Application {
    private ListView<String> listViewSource, listViewDest;
    private TextField textFieldName;
    private CheckBox checkBoxAll;

    @Override
    public void start(Stage primaryStage) {
        listViewSource = new ListView<>();
        listViewSource.setPrefSize(100, 150);
        listViewSource.getSelectionModel().selectedItemProperty().addListener(
                event ->
                        textFieldName.setText(listViewSource.getSelectionModel().getSelectedItem())
        );

        listViewDest = new ListView<>();
        listViewDest.setPrefSize(100, 150);

        HBox hBox1 = new HBox(10, listViewSource, listViewDest);
        hBox1.setAlignment(Pos.CENTER);

        textFieldName = new TextField();

        checkBoxAll = new CheckBox("Select All");
        checkBoxAll.setOnAction(event -> {
            if(!checkBoxAll.isSelected())
                listViewSource.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            else
                listViewSource.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        });

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton radioButtonGold = new RadioButton("Gold");
        RadioButton radioButtonCyan = new RadioButton("Cyan");
        radioButtonGold.setToggleGroup(toggleGroup);
        radioButtonCyan.setToggleGroup(toggleGroup);

        HBox hBox2 = new HBox(20, radioButtonGold, radioButtonCyan);
        hBox2.setAlignment(Pos.CENTER);

        Button buttonAdd = new Button("Add");
        Button buttonDel = new Button("Delete");
        Button buttonUpdate = new Button("Update");
        Button buttonCopy = new Button("Copy");
        Button buttonClear = new Button("Clear");

        MyEventHandler myEventHandler = new MyEventHandler();
        buttonAdd.setOnAction(myEventHandler);
        buttonDel.setOnAction(myEventHandler);
        buttonUpdate.setOnAction(myEventHandler);
        buttonCopy.setOnAction(myEventHandler);
        buttonClear.setOnAction(myEventHandler);

        HBox hBox3 = new HBox(10, buttonAdd, buttonDel, buttonUpdate, buttonCopy, buttonClear);
        hBox3.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(10, hBox1, textFieldName, checkBoxAll, hBox2, hBox3);
        vBox1.setPadding(new Insets(20));
        vBox1.setAlignment(Pos.CENTER);

        radioButtonGold.setOnAction(e->
                vBox1.setStyle("-fx-background-color:gold")
        );
        radioButtonCyan.setOnAction(e->
                vBox1.setStyle("-fx-background-color:cyan")
        );


        FlowPane flowPane = new FlowPane(vBox1);
        flowPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flowPane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lecture9 App");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Lecture9.class.getResourceAsStream("code.png"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MyEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String buttonName = ((Button) event.getSource()).getText();

            switch (buttonName) {
                case "Add" -> {
                    if (!textFieldName.getText().equals("")){
                        listViewSource.getItems().add(textFieldName.getText());
                        textFieldName.setText("");
                    }
                }
                case "Delete" -> listViewSource.getItems().remove(listViewSource.getSelectionModel().getSelectedItem());
                case "Update" -> {
                    int selectedIndex = listViewSource.getSelectionModel().getSelectedIndex();
                    String editedText = textFieldName.getText();
                    if (!editedText.equals(""))
                        listViewSource.getItems().set(selectedIndex, editedText);
                }
                case "Copy" -> {
                    if (!listViewSource.getItems().isEmpty() && !listViewSource.getSelectionModel().isEmpty()){
                        if(!checkBoxAll.isSelected())
                            listViewDest.getItems().add(listViewSource.getSelectionModel().getSelectedItem());
                        else
                            listViewDest.getItems().addAll(listViewSource.getSelectionModel().getSelectedItems());
                    }
                }
                case "Clear" -> {
                    listViewSource.getItems().clear();
                    listViewDest.getItems().clear();
                    textFieldName.setText("");
                    checkBoxAll.setSelected(false);
                }
            }
        }
    }
}
// Zaki Kurdya