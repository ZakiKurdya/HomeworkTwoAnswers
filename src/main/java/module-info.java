module com.example.homeworktwoanswers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homeworktwoanswers to javafx.fxml;
    exports com.example.homeworktwoanswers;
}