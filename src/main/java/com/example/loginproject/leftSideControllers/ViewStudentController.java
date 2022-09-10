package com.example.loginproject.leftSideControllers;

import com.example.loginproject.Student;
import com.example.loginproject.leftSideControllers.MODEL.LISTSforStudentandBook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewStudentController implements Initializable {
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student,Integer> studentID;
    @FXML
    private TableColumn<Student,String> studentName,studentSurname,studentDepartment,studentBirthdayDate,studentGender,studentMail,studentIssuedBook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentID.setCellValueFactory(new PropertyValueFactory<Student,Integer>("ID"));
        studentName.setCellValueFactory(new PropertyValueFactory<Student,String>("nameOfStudent"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<Student,String>("surnameOfStudent"));
        studentDepartment.setCellValueFactory(new PropertyValueFactory<Student,String>("department"));
        studentBirthdayDate.setCellValueFactory(new PropertyValueFactory<Student,String>("dateOfBirth"));
        studentGender.setCellValueFactory(new PropertyValueFactory<Student,String>("gender"));
        studentMail.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        studentIssuedBook.setCellValueFactory(new PropertyValueFactory<Student,String>("issuedBook"));

        studentTableView.setItems(new LISTSforStudentandBook().getStudentsListFromDatabase());

    }
}
