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

/**
 * @author Tomas Kozakas
 */
public class ViewHandlerImpl implements ViewHandler {
    private final Stage primaryStage;

    public ViewHandlerImpl(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void launchAttendanceWindow(int index) throws IOException {
        showWindow(new AttendanceWindow(new AttendanceTableController(this, index)));
    }

    @Override
    public void launchStudentWindow() throws IOException {
        showWindow(new StudentWindow(new StudentTableController(this)));
    }

    private void showWindow(AbstractWindow window) throws IOException {
        primaryStage.setTitle(window.getTitle());
        primaryStage.setScene(new Scene(window.root()));
        primaryStage.show();
    }
}
