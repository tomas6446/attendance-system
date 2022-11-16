package com.student;


import com.student.view.impl.ViewHandlerImpl;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        new ViewHandlerImpl(primaryStage).launchStudentWindow();
    }
}