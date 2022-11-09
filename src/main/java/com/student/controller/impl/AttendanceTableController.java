package com.student.controller.impl;

import com.student.controller.AbstractTableController;
import com.student.model.Attendance;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Tomas Kozakas
 */
public class AttendanceTableController extends AbstractTableController<Attendance> implements Table {
    private Integer studentID;
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
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;

    public AttendanceTableController(ViewHandler viewHandler, int index) {
        super(viewHandler);
        studentID = index;
    }

    @Override
    public EventHandler<ActionEvent> addRow() {
        return e -> {
            if (list.stream().noneMatch(attend -> attend.getDate().equals(dpDate.getValue()) && attend.getSubject().equals(tfSubject.getText()))) {
                list.add(new Attendance(dpDate.getValue(), chbPresent.isSelected(), tfSubject.getText()));
            }
            table.setItems(list);
            exportTable();
        };
    }

    @Override
    public EventHandler<ActionEvent> removeRow() {
        return e -> {
            list.remove(chosenObject);
            table.setItems(list);
        };
    }

    @Override
    public void exportTable() {
        try {
            try (FileWriter fileWriter = new FileWriter("attendance/" + studentID + "attendance.csv")) {
                fileWriter.append("Date,Present,Subject\n");
                for (Attendance attendance : list) {
                    fileWriter.append(attendance.getDate().toString() + ',' + attendance.getPresent() + ',' + attendance.getSubject() + "\n");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void importTable() throws RuntimeException {
        ObservableList<Attendance> attendances = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader("attendance/" + studentID + "attendance.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                LocalDate date = LocalDate.parse(fields[0]);
                boolean present = Boolean.parseBoolean(fields[1]);
                String subject = fields[2];

                Attendance attendance = new Attendance(date, present, subject);
                attendances.add(attendance);
            }
            list = attendances;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EventHandler<ActionEvent> editRow() {
        return e -> {
            if (!list.isEmpty() && chosenObject != null) {
                list.remove(chosenObject);
                chosenObject = new Attendance(dpDate.getValue(), chbPresent.isSelected(), tfSubject.getText());
                list.add(chosenObject);
                table.setItems(list);
            }
        };
    }

    private void createNewAttendanceFile() {
        File newAttendanceFile = new File("attendance/" + studentID + "attendance.csv");
        try {
            if (newAttendanceFile.createNewFile()) {
                System.out.println("File created: " + newAttendanceFile.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        createNewAttendanceFile();
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
        importTable();

        btnAdd.setOnAction(addRow());
        btnRemove.setOnAction(removeRow());
        btnEdit.setOnAction(editRow());

        btnBack.setOnAction(event -> {
            try {
                viewHandler.launchStudentWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        table.setItems(list);
    }
}
