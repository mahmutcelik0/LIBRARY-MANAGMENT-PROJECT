package com.example.loginproject.leftSideControllers;

import com.example.loginproject.Book;
import com.example.loginproject.DatabaseConnection;
import com.example.loginproject.Student;
import com.example.loginproject.leftSideControllers.MODEL.LISTSforStudentandBook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBookController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Label studentIDLabel,studentName,studentSurname,studentDepartment,studentBirthDate,studentGender,studentMail,studentIssuedBook;
    @FXML
    private Label bookIDLabel,nameOfBook,bookAuthor,bookType,bookYear,nameOfBorrower,idOfBorrower;
    @FXML
    private Label warningMessage;
    @FXML
    private TextField studentIDField,bookIDField;

    Student studentFromDatabase = null;
    Book bookFromDatabase =null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setDisable(true);
    }

    @FXML
    private void checkMethod(){
        int studentID = Integer.MIN_VALUE;
        int bookID = Integer.MIN_VALUE;

        if (studentIDField.getText().isEmpty() || bookIDField.getText().isEmpty()){
            warningMessage.setText("FILL ALL FIELDS");
            return;
        }

        try {
            studentID = Integer.parseInt(studentIDField.getText());
            bookID = Integer.parseInt(bookIDField.getText());
        }catch (NumberFormatException e){
            warningMessage.setText("USE ONLY DIGITS");
            return;
        }


        if(!setLabels(studentID,bookID)) return;
        warningMessage.setText("");

        if(studentFromDatabase.getID() != bookFromDatabase.getIDofIssuedFrom()){
            warningMessage.setText("DIFFERENT BOOK AND USER.TRY AGAIN!");
            return;
        }

        warningMessage.setText("CLICK RETURN BUTTON TO RETRIEVE BOOK");
        returnButton.setDisable(false);

    }

    @FXML
    private void retrieveMethod(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.makeConnectionBeginning();

        databaseConnection.updateBookFromDatabase("", bookFromDatabase.getID(), -1);
        databaseConnection.updateStudentFromDatabase("",studentFromDatabase.getID());

        setLabels(studentFromDatabase.getID(),bookFromDatabase.getID());

        warningMessage.setText("BOOK RETRIEVED SUCCESSFULLY");
    }


    private boolean setLabels(Integer studentID,Integer bookID){
        boolean firstResult = setStudent(studentID);
        boolean secondResult = setBook(bookID);

        return firstResult && secondResult;
    }

    private boolean setStudent(Integer studentID){
        studentFromDatabase = null;
        studentFromDatabase = new LISTSforStudentandBook().checkAndReturnStudentFromList(studentID);
        if(studentFromDatabase == null){
            warningMessage.setText("THIS STUDENT IS NOT REGISTERED");
            clearStudentPart();
            returnButton.setDisable(true);
            return false;
        }
        else{
            studentIDLabel.setText(Integer.toString(studentFromDatabase.getID()));
            studentName.setText(studentFromDatabase.getNameOfStudent());
            studentSurname.setText(studentFromDatabase.getSurnameOfStudent());
            studentDepartment.setText(studentFromDatabase.getDepartment());
            studentBirthDate.setText(studentFromDatabase.getDateOfBirth());
            studentGender.setText(studentFromDatabase.getGender());
            studentMail.setText(studentFromDatabase.getEmail());
            studentIssuedBook.setText(studentFromDatabase.getIssuedBook());
        }


        return true;
    }

    private boolean setBook(Integer bookID){
        bookFromDatabase = null;
        bookFromDatabase = new LISTSforStudentandBook().checkAndReturnBookFromList(bookID);
        if(bookFromDatabase == null){
            warningMessage.setText("THIS BOOK DOESN'T EXIST");
            clearBookPart();
            returnButton.setDisable(true);
            return false;
        }
        else{
            bookIDLabel.setText(Integer.toString(bookFromDatabase.getID()));
            nameOfBook.setText(bookFromDatabase.getNameOfBook());
            bookAuthor.setText(bookFromDatabase.getAuthor());
            bookType.setText(bookFromDatabase.getType());
            bookYear.setText(Integer.toString(bookFromDatabase.getYear()));
            nameOfBook.setText(bookFromDatabase.getNameOfIssuedFrom());
            idOfBorrower.setText(Integer.toString(bookFromDatabase.getIDofIssuedFrom()));
        }

        return true;
    }

    private void clearStudentPart(){
        studentMail.setText("");
        studentIDLabel.setText("");
        studentName.setText("");
        studentSurname.setText("");
        studentDepartment.setText("");
        studentBirthDate.setText("");
        studentGender.setText("");
        studentMail.setText("");
        studentIssuedBook.setText("");
    }

    private void clearBookPart(){
        bookIDLabel.setText("");
        nameOfBook.setText("");
        bookAuthor.setText("");
        bookType.setText("");
        bookYear.setText("");
        nameOfBorrower.setText("");
        idOfBorrower.setText("");
    }
}
