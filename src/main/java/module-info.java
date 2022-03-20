module com.example.homeworktwoanswers {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.logging;

    exports com.example.homeworktwoanswers.Lecture9;
    opens com.example.homeworktwoanswers.Lecture9 to javafx.fxml;
    exports com.example.homeworktwoanswers.Lecture11;
    opens com.example.homeworktwoanswers.Lecture11 to javafx.fxml;
    exports com.example.homeworktwoanswers.Lecture12;
    opens com.example.homeworktwoanswers.Lecture12 to javafx.fxml;
}