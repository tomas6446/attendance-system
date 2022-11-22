package com.student.controller.impl;

import com.student.controller.AbstractTableController;
import com.student.model.Student;
import com.student.view.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Tomas Kozakas
 */
public class StudentTableController extends AbstractTableController<Student> implements Table {
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
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    public StudentTableController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    @Override
    public EventHandler<ActionEvent> addRow() {
        return e -> {
            if ((tfName.getText() != null && tfSurname.getText() != null && cbGroup.getValue() != null) && (!tfName.getText().isEmpty() && !tfSurname.getText().isEmpty() && !cbGroup.getValue().isEmpty())) {
                if (list.stream().noneMatch(student -> student.getName().equals(tfName.getText()) && student.getSurname().equals(tfSurname.getText()) && student.getGroup().equals(cbGroup.getValue()))) { // if student doesn't exist in the list
                    list.add(new Student(String.valueOf(list.size() + 1), tfName.getText(), tfSurname.getText(), cbGroup.getValue()));
                }
                table.setItems(list);
            }
            exportTable();
        };
    }

    @Override
    public EventHandler<ActionEvent> removeRow() {
        return e -> {
            list.remove(chosenObject);
            table.setItems(list);
            exportTable();
            deleteFile(Integer.parseInt(chosenObject.getNumber()) - 1);
        };
    }

    @Override
    public void exportTable() {
        try {
            try (FileWriter fileWriter = new FileWriter("students.csv")) {

                fileWriter.append("No,Name,Surname,Group\n");
                for (Student student : list) {
                    fileWriter.append(String.valueOf(student.getNumber()))
                            .append(String.valueOf(','))
                            .append(student.getName())
                            .append(String.valueOf(','))
                            .append(student.getSurname())
                            .append(String.valueOf(','))
                            .append(student.getGroup())
                            .append("\n");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void importTable() throws RuntimeException {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader("students.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                String number = fields[0];
                String name = fields[1];
                String surname = fields[2];
                String group = fields[3];

                Student student = new Student(number, name, surname, group);
                students.add(student);
            }
            list = students;
            table.setItems(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EventHandler<ActionEvent> editRow() {
        return e -> {
            if (!list.isEmpty() && chosenObject != null) {
                list.remove(chosenObject);
                chosenObject = new Student(chosenObject.getNumber(), tfName.getText(), tfSurname.getText(), cbGroup.getValue());
                list.add(chosenObject);
                table.setItems(list);
            }
        };
    }

    private void addNewFile() {
        File newAttendanceFile = new File("students.csv");
        try {
            if (newAttendanceFile.createNewFile()) {
                System.out.println("File created: " + newAttendanceFile.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(int studentID) {
        File attendanceFile = new File("attendance/" + studentID + "attendance.csv");
        if (attendanceFile.delete()) {
            System.out.println("File deleted: " + attendanceFile.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        addNewFile();
        importTable();

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
                            viewHandler.launchAttendanceWindow(row.getIndex());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            return row;
        });

        btnAdd.setOnAction(addRow());
        btnRemove.setOnAction(removeRow());
        btnEdit.setOnAction(editRow());
        cbGroup.getItems().addAll("1", "2", "3", "4");
        cbGroup.setValue(cbGroup.getValue());

        table.setItems(list);
    }
}
