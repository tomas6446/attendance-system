package com.student.controller;

import com.student.handler.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * @author Tomas Kozakas
 */
public abstract class AbstractController<T> {
    @FXML
    protected TableView<T> table;
    protected final ObservableList<T> list = FXCollections.observableArrayList();
    protected T chosenObject;
    protected final ViewHandler viewHandler;

    protected AbstractController(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
}
