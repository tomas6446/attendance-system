package com.student.view;

import java.io.IOException;

/**
 * @author Tomas Kozakas
 */
public interface ViewHandler {
    void launchAttendanceWindow(int index) throws IOException;

    void launchStudentWindow() throws IOException;
}
