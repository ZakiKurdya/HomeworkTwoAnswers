module com.example.homeworktwoanswers {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.logging;


    opens com.example.homeworktwoanswers to javafx.fxml;
    exports com.example.homeworktwoanswers;
}