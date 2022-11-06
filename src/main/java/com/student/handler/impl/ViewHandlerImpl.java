package com.student.handler.impl;

import com.student.controller.impl.AttendanceController;
import com.student.controller.impl.StudentController;
import com.student.handler.ViewHandler;
import com.student.view.AbstractWindow;
import com.student.view.impl.AttendanceWindow;
import com.student.view.impl.StudentWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Tomas Kozakas
 */
public class ViewHandlerImpl implements ViewHandler {
    private final Stage primaryStage;
    private final StudentWindow studentWindow;
    private final AbstractWindow attendanceWindow;

    public ViewHandlerImpl(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.attendanceWindow = new AttendanceWindow(new AttendanceController(this));
        this.studentWindow = new StudentWindow(new StudentController(this));
    }

    @Override
    public void launchAttendanceWindow() throws IOException {
        primaryStage.setScene(new Scene(attendanceWindow.root()));
        primaryStage.show();
    }

    @Override
    public void launchStudentWindow() throws IOException {
        primaryStage.setScene(new Scene(studentWindow.root()));
        primaryStage.show();
    }
}
