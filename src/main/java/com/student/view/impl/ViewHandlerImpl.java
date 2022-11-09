package com.student.view.impl;

import com.student.controller.impl.AttendanceTableController;
import com.student.controller.impl.StudentTableController;
import com.student.view.ViewHandler;
import com.student.window.AbstractWindow;
import com.student.window.impl.AttendanceWindow;
import com.student.window.impl.StudentWindow;
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
        this.studentWindow = new StudentWindow(new StudentTableController(this));
        this.attendanceWindow = new ArrayList<>();
    }

    @Override
    public void launchAttendanceWindow(int index) throws IOException {
        if (index >= attendanceWindow.size()) {
            attendanceWindow.add(new AttendanceWindow(new AttendanceTableController(this, index)));
        }
        showWindow(attendanceWindow.get(index));
    }

    @Override
    public void launchStudentWindow() throws IOException {
        showWindow(studentWindow);
    }

    private void showWindow(AbstractWindow window) throws IOException {
        primaryStage.setTitle(window.getTitle());
        primaryStage.setScene(new Scene(window.root()));
        primaryStage.show();
    }
}
