package com.student.window.impl;

import com.student.controller.impl.AttendanceTableController;
import com.student.window.AbstractWindow;

/**
 * @author Tomas Kozakas
 */
public class AttendanceWindow extends AbstractWindow {
    public AttendanceWindow(AttendanceTableController controller) {
        super(controller);
    }

    @Override
    protected String getFxmlPath() {
        return "attendance.fxml";
    }

    @Override
    public String getTitle() {
        return "Attendance";
    }
}
