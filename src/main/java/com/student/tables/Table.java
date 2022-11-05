package com.student.tables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Tomas Kozakas
 */
public interface Table extends Initializable {
    @FXML
    void addEvent();
    @FXML
    void editEvent();
    @FXML
    void removeEvent();

    void initialize(URL url, ResourceBundle resourceBundle);
}
