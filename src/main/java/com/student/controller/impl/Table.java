package com.student.controller.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * @author Tomas Kozakas
 */
public interface Table extends Initializable {
    @FXML
    EventHandler<ActionEvent> addRow();

    @FXML
    EventHandler<ActionEvent> editRow();

    @FXML
    EventHandler<ActionEvent> removeRow();
}
