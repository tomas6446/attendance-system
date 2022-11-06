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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Kozakas
 */
public class ViewHandlerImpl implements ViewHandler {
    private final Stage primaryStage;
    private final StudentWindow studentWindow;
    private final List<AttendanceWindow> attendanceWindow;

    public ViewHandlerImpl(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.studentWindow = new StudentWindow(new StudentController(this));
        this.attendanceWindow = new ArrayList<>();
    }

    @Override
    public void launchAttendanceWindow(int index) throws IOException {
        if (index >= attendanceWindow.size()) {
            attendanceWindow.add(new AttendanceWindow(new AttendanceController(this)));
        }
        showWindow(attendanceWindow.get(index));
    }

    @Override
    public void launchStudentWindow() throws IOException {
        showWindow(studentWindow);
    }

    private void showWindow(AbstractWindow window) throws IOException {
        primaryStage.setScene(new Scene(window.root()));
        primaryStage.show();
    }
}
