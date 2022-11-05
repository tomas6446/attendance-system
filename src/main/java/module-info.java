module com.attendancesystem {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.student;
    opens com.student to javafx.fxml;
    exports com.student.model;
    opens com.student.model to javafx.fxml;
    exports com.student.tables;
    opens com.student.tables to javafx.fxml;
}