package com.student.controller.impl;

import com.student.controller.AbstractController;
import com.student.handler.ViewHandler;
import com.student.model.Attendance;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Tomas Kozakas
 */
public class AttendanceController extends AbstractController<Attendance> implements Table {
    @FXML
    private TableColumn<Attendance, String> dateCol;
    @FXML
    private TableColumn<Attendance, String> presentCol;
    @FXML
    private TableColumn<Attendance, String> subjectCol;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField tfSubject;
    @FXML
    private CheckBox chbPresent;
    @FXML
    private Button btnBack;

    public AttendanceController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    @Override
    public void addRow() {
        if (list.stream().noneMatch(attend -> attend.getDate().equals(dpDate.getValue()) && attend.getSubject().equals(tfSubject.getText()))) {
            list.add(new Attendance(dpDate.getValue(), chbPresent.isSelected(), tfSubject.getText()));
        }
        table.setItems(list);
    }

    @Override
    public void removeRow() {
        list.remove(chosenObject);
        table.setItems(list);
    }

    @Override
    public void editRow() {
        if (!list.isEmpty() && chosenObject != null) {
            list.remove(chosenObject);
            chosenObject = new Attendance(dpDate.getValue(), chbPresent.isSelected(), tfSubject.getText());
            list.add(chosenObject);
            table.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        presentCol.setCellValueFactory(new PropertyValueFactory<>("present"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));

        table.setRowFactory(tv -> {
            TableRow<Attendance> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    chosenObject = row.getItem();
                    dpDate.setValue(chosenObject.getDate());
                    tfSubject.setText(chosenObject.getSubject());
                    chbPresent.setSelected(chosenObject.getPresent());
                }
            });
            return row;
        });

        btnBack.setOnAction(event -> {
            try {
                viewHandler.launchStudentWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
