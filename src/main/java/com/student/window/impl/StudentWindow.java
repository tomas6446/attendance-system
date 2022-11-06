package com.student.window.impl;

import com.student.controller.impl.StudentTableController;
import com.student.window.AbstractWindow;

/**
 * @author Tomas Kozakas
 */
public class StudentWindow extends AbstractWindow {
    public StudentWindow(StudentTableController controller) {
        super(controller);
    }

    @Override
    protected String getFxmlPath() {
        return "list.fxml";
    }

    @Override
    public String getTitle() {
        return "Students";
    }
}
