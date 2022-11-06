package com.student.handler.impl;

import com.student.handler.ViewHandler;
import com.student.view.AbstractWindow;
import com.student.view.WindowFactory;
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
    public void launchAttendanceWindow() throws IOException {
        showScene(primaryStage, WindowFactory.ATTENDANCE.createWindow(this));
    }

    @Override
    public void launchStudentWindow() throws IOException {
        showScene(new Stage(), WindowFactory.STUDENT.createWindow(this));
    }

    private void showScene(Stage stage, AbstractWindow window) throws IOException {
        stage.setTitle("Application");
        stage.setScene(new Scene(window.root()));
        stage.show();
    }
}
