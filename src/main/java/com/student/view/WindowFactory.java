package com.student.view;

import com.student.controller.impl.AttendanceController;
import com.student.controller.impl.StudentController;
import com.student.handler.ViewHandler;
import com.student.view.impl.AttendanceWindow;
import com.student.view.impl.StudentWindow;

/**
 * @author Tomas Kozakas
 */
public enum WindowFactory {
    STUDENT {
        @Override
        public AbstractWindow createWindow(ViewHandler viewHandler) {
            return new StudentWindow(new StudentController(viewHandler));
        }
    },

    ATTENDANCE {
        @Override
        public AbstractWindow createWindow(ViewHandler viewHandler) {
            return new AttendanceWindow(new AttendanceController(viewHandler));
        }
    };

    public abstract AbstractWindow createWindow(ViewHandler viewHandler);
}
