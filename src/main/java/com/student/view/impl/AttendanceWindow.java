package com.student.view.impl;

import com.student.controller.impl.AttendanceController;
import com.student.view.AbstractWindow;

/**
 * @author Tomas Kozakas
 */
public class AttendanceWindow extends AbstractWindow {
    public AttendanceWindow(AttendanceController controller) {
        super(controller);
    }

    @Override
    protected String getFxmlPath() {
        return "attendance.fxml";
    }
}
