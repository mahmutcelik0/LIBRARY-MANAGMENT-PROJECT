package com.example.loginproject.leftSideControllers;

import com.example.loginproject.DatabaseConnection;
import com.example.loginproject.InputVerification;
import com.example.loginproject.Student;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class DeleteStudentController {
    @FXML
    private TextField studentName,studentSurname,studentMail,studentDepartment;
    @FXML
    private DatePicker studentBirthdayDate;
    @FXML
    private ComboBox studentGender;
    @FXML
    private Label warningMessage;

    @FXML
    public void deleteStudentFromDatabase() {
        try {
            Student studentToDelete = new Student(-1, studentName.getText(), studentSurname.getText(), studentDepartment.getText(), studentBirthdayDate.getValue().toString(), String.valueOf(studentGender.getValue()), studentMail.getText());

            HashMap<Boolean, String> verificationResult = new InputVerification().makeControlsForStudents(studentToDelete);
            Boolean boolResult = true;
            String strResult = "";
            for (Boolean key : verificationResult.keySet()) {
                boolResult = key;
                strResult = verificationResult.get(key);
            }

            if (!boolResult) {
                warningMessage.setText(strResult);
                return;
            }

            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.makeConnectionBeginning();

            if (!databaseConnection.deleteStudentFromDatabase(studentToDelete)) {
                warningMessage.setText("THIS STUDENT \nDOESN'T EXIST");
                return;
            }

        }catch (NullPointerException e) {
            warningMessage.setText("FILL ALL FIELDS");
            e.printStackTrace();
            return;
        }catch (Exception e) {
            warningMessage.setText("ENTER DATE USING DOT(.) EX: 19.10.2001");
            return;
        }
        warningMessage.setText("STUDENT DELETED \nSUCCESSFULLY!");
    }


}
