package com.student;

import com.student.handler.impl.ViewHandlerImpl;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        new ViewHandlerImpl(primaryStage).launchStudentWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}