package com.student.tables;

import com.student.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Tomas Kozakas
 */
public class ListController extends TableController {
    @FXML
    private TableView<Student> tvStudentTable;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> surnameCol;
    @FXML
    private TableColumn<Student, String> groupCol;
    @FXML
    private TableColumn<Student, String> numberCol;
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private Student chosenStudent;
    @FXML
    private ChoiceBox<String> cbGroup;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;

    public void addEvent() {
        if ((tfName.getText() != null && tfSurname.getText() != null && cbGroup.getValue() != null)
                && (!tfName.getText().isEmpty() && !tfSurname.getText().isEmpty() && !cbGroup.getValue().isEmpty())) {
            if (students.stream().noneMatch(student -> student.getName().equals(tfName.getText())
                    && student.getSurname().equals(tfSurname.getText())
                    && student.getGroup().equals(cbGroup.getValue()))) { // if student doesn't exist in the list
                students.add(new Student(students.size() + 1 + ".", tfName.getText(), tfSurname.getText(), cbGroup.getValue()));
            }
            tvStudentTable.setItems(students);
        }
    }


    public void removeEvent() {
        students.remove(chosenStudent);
        tvStudentTable.setItems(students);
    }

    public void editEvent() {
        if (!students.isEmpty() && chosenStudent != null) {
            students.remove(chosenStudent);
            chosenStudent = new Student(chosenStudent.getNumber(), tfName.getText(), tfSurname.getText(), cbGroup.getValue());
            students.add(chosenStudent);
            tvStudentTable.setItems(students);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));

        tvStudentTable.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                chosenStudent = row.getItem();
                if (!row.isEmpty()) {
                    tfName.setText(chosenStudent.getName());
                    tfSurname.setText(chosenStudent.getSurname());
                    cbGroup.setValue(chosenStudent.getGroup());
                    if (event.getClickCount() == 2) {
                        try {
                            showAttendanceList(row);
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

    private void showAttendanceList(TableRow<Student> row) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/attendance.fxml"));
        Parent homeParent = loader.load();
        Scene home = new Scene(homeParent);
        //This line gets the Stage information
        Stage window = (Stage) row.getScene().getWindow();
        window.setTitle("Attendance System");
        window.setScene(home);
        window.show();
    }
}
