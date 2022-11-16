package com.student.controller;

import com.student.view.ViewHandler;
import com.student.controller.AbstractController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * @author Tomas Kozakas
 */
public abstract class AbstractTableController<T> extends AbstractController {
    protected ObservableList<T> list = FXCollections.observableArrayList();
    @FXML
    protected TableView<T> table;
    protected T chosenObject;

    protected AbstractTableController(ViewHandler viewHandler) {
        super(viewHandler);
    }
}
