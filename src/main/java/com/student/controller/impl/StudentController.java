package com.student.controller.impl;

import com.student.controller.AbstractController;
import com.student.handler.ViewHandler;
import com.student.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Tomas Kozakas
 */
public class StudentController extends AbstractController<Student> implements Table {
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> surnameCol;
    @FXML
    private TableColumn<Student, String> groupCol;
    @FXML
    private TableColumn<Student, String> numberCol;
    @FXML
    private ChoiceBox<String> cbGroup;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;

    public StudentController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    @Override
    public void addRow() {
        if ((tfName.getText() != null && tfSurname.getText() != null && cbGroup.getValue() != null) && (!tfName.getText().isEmpty() && !tfSurname.getText().isEmpty() && !cbGroup.getValue().isEmpty())) {
            if (list.stream().noneMatch(student -> student.getName().equals(tfName.getText()) && student.getSurname().equals(tfSurname.getText()) && student.getGroup().equals(cbGroup.getValue()))) { // if student doesn't exist in the list
                list.add(new Student(list.size() + 1 + ".", tfName.getText(), tfSurname.getText(), cbGroup.getValue()));
            }
            table.setItems(list);
        }
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
            chosenObject = new Student(chosenObject.getNumber(), tfName.getText(), tfSurname.getText(), cbGroup.getValue());
            list.add(chosenObject);
            table.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));

        table.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                chosenObject = row.getItem();
                if (!row.isEmpty()) {
                    tfName.setText(chosenObject.getName());
                    tfSurname.setText(chosenObject.getSurname());
                    cbGroup.setValue(chosenObject.getGroup());
                    if (event.getClickCount() == 2) {
                        try {
                            viewHandler.launchAttendanceWindow();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            return row;
        });

        cbGroup.getItems().addAll("1", "2", "3", "4");
        cbGroup.setValue(cbGroup.getValue());
    }
}
