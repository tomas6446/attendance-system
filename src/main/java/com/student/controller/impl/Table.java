package com.student.controller.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

/**
 * @author Tomas Kozakas
 */
public interface Table {
    @FXML
    EventHandler<ActionEvent> addRow();

    @FXML
    EventHandler<ActionEvent> editRow();

    @FXML
    EventHandler<ActionEvent> removeRow();

    void exportTable();

    void importTable();
}
