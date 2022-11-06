package com.student.handler;

import java.io.IOException;

/**
 * @author Tomas Kozakas
 */
public interface ViewHandler {
    void launchAttendanceWindow(int index) throws IOException;

    void launchStudentWindow() throws IOException;
}
