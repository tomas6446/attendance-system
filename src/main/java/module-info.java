module com.attendancesystem {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.student;
    opens com.student to javafx.fxml;
    exports com.student.model;
    opens com.student.model to javafx.fxml;
    exports com.student.controller;
    opens com.student.controller to javafx.fxml;
    exports com.student.controller.impl;
    opens com.student.controller.impl to javafx.fxml;
    exports com.student.handler;
    opens com.student.handler to javafx.fxml;
    exports com.student.handler.impl;
    opens com.student.handler.impl to javafx.fxml;
    exports com.student.view;
    opens com.student.view to javafx.fxml;
    exports com.student.view.impl;
    opens com.student.view.impl to javafx.fxml;
}