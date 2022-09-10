package com.example.loginproject.leftSideControllers;

import com.example.loginproject.Book;
import com.example.loginproject.DatabaseConnection;
import com.example.loginproject.Student;
import com.example.loginproject.leftSideControllers.MODEL.LISTSforStudentandBook;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowBookController implements Initializable {
    @FXML
    private TextField studentIDField,bookIDField;
    @FXML
    private Button borrowBookButton;
    @FXML
    private Label warningMessage;

    @FXML
    private Label studentIDLabel,studentName,studentSurname,studentDepartment,studentBirthDate,studentGender,studentMail,studentIssuedBook;
    @FXML
    private Label bookIDLabel,bookName,bookAuthor,bookType,bookYear,borrowerName,borrowerID;

    Student studentFromDatabase = null;
    Book bookFromDatabase =null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borrowBookButton.setDisable(true);
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
        warningMessage.setText("CLICK BORROW BUTTON TO GIVE BOOK");
        borrowBookButton.setDisable(false);

    }

    @FXML
    private void borrowMethod(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.makeConnectionBeginning();

        databaseConnection.updateStudentFromDatabase(bookFromDatabase.getNameOfBook(), studentFromDatabase.getID());
        databaseConnection.updateBookFromDatabase(studentFromDatabase.getNameOfStudent(), bookFromDatabase.getID(),studentFromDatabase.getID());

        setLabels(studentFromDatabase.getID(),bookFromDatabase.getID());

        warningMessage.setText("BOOK GIVEN SUCCESSFULLY");
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
            borrowBookButton.setDisable(true);
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
            borrowBookButton.setDisable(true);
            return false;
        }
        else{
            bookIDLabel.setText(Integer.toString(bookFromDatabase.getID()));
            bookName.setText(bookFromDatabase.getNameOfBook());
            bookAuthor.setText(bookFromDatabase.getAuthor());
            bookType.setText(bookFromDatabase.getType());
            bookYear.setText(Integer.toString(bookFromDatabase.getYear()));
            borrowerName.setText(bookFromDatabase.getNameOfIssuedFrom());
            borrowerID.setText(Integer.toString(bookFromDatabase.getIDofIssuedFrom()));
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
        bookName.setText("");
        bookAuthor.setText("");
        bookType.setText("");
        bookYear.setText("");
        borrowerName.setText("");
        borrowerID.setText("");
    }
}
