package com.student.controller.impl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * @author Tomas Kozakas
 */
public interface Table extends Initializable {
    @FXML
    void addRow();

    @FXML
    void editRow();

    @FXML
    void removeRow();
}
