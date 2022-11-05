package com.student.tables;

import com.student.model.Attendance;
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
public class AttendanceController extends TableController {
    @FXML
    private TableView<Attendance> tvAttendanceTable;
    @FXML
    private TableColumn<Attendance, String> dateCol;
    @FXML
    private TableColumn<Attendance, String> presentCol;
    @FXML
    private TableColumn<Attendance, String> subjectCol;
    private ObservableList<Attendance> attendance = FXCollections.observableArrayList();
    private Attendance chosenAttendance;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField tfSubject;
    @FXML
    private CheckBox chbPresent;

    public void removeEvent() {
        attendance.remove(chosenAttendance);
        tvAttendanceTable.setItems(attendance);
    }

    public void addEvent() {
        if (attendance.stream().noneMatch(attend -> attend.getDate().equals(dpDate.getValue()) && attend.getSubject().equals(tfSubject.getText()))) {
            attendance.add(new Attendance(dpDate.getValue(), chbPresent.isSelected(), tfSubject.getText()));
        }
        tvAttendanceTable.setItems(attendance);
    }

    public void editEvent() {
        if (!attendance.isEmpty() && chosenAttendance != null) {
            attendance.remove(chosenAttendance);
            chosenAttendance = new Attendance(dpDate.getValue(), chbPresent.isSelected(), tfSubject.getText());
            attendance.add(chosenAttendance);
            tvAttendanceTable.setItems(attendance);
        }
    }

    @FXML
    private void backEvent() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/list.fxml"));
        Parent homeParent = loader.load();
        Scene home = new Scene(homeParent);
        Stage window = (Stage) chbPresent.getScene().getWindow();
        window.setTitle("Attendance System");
        window.setScene(home);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        presentCol.setCellValueFactory(new PropertyValueFactory<>("present"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));

        tvAttendanceTable.setRowFactory(tv -> {
            TableRow<Attendance> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    chosenAttendance = row.getItem();
                    dpDate.setValue(chosenAttendance.getDate());
                    tfSubject.setText(chosenAttendance.getSubject());
                    chbPresent.setSelected(chosenAttendance.getPresent());
                }
            });
            return row;
        });
    }
}
