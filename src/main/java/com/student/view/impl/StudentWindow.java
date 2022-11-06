package com.student.view.impl;

import com.student.controller.impl.StudentController;
import com.student.view.AbstractWindow;

/**
 * @author Tomas Kozakas
 */
public class StudentWindow extends AbstractWindow {
    public StudentWindow(StudentController controller) {
        super(controller);
    }

    @Override
    protected String getFxmlPath() {
        return "list.fxml";
    }
}
