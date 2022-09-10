package com.example.loginproject.leftSideControllers;

import com.example.loginproject.Book;
import com.example.loginproject.DatabaseConnection;
import com.example.loginproject.Student;
import com.example.loginproject.leftSideControllers.MODEL.LISTSforStudentandBook;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Label numberOfBooks,numberOfFreeBooks,numberOfBorrowedBooks;
    @FXML
    private Label numberOfStudents,boughtBooks,notBoughtBooks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LISTSforStudentandBook getList = new LISTSforStudentandBook();
        setBookPart(getList);
        setStudentPart(getList);

    }

    private void setBookPart(LISTSforStudentandBook getList){
        ObservableList<Book> books = getList.getBooksListFromDatabase();

        numberOfBooks.setText(Integer.toString(books.size()));

        long borrowedBook = books.stream().filter(book -> book.getIDofIssuedFrom() != -1).count();

        numberOfBorrowedBooks.setText(Long.toString(borrowedBook));

        numberOfFreeBooks.setText(Long.toString(books.size()-borrowedBook));
    }

    private void setStudentPart(LISTSforStudentandBook getList){
        ObservableList<Student> students = getList.getStudentsListFromDatabase();

        numberOfStudents.setText(Integer.toString(students.size()));

        long studentThatBorrowedBook = students.stream().filter(student -> !student.getIssuedBook().equals("-")).count();

        boughtBooks.setText(Long.toString(studentThatBorrowedBook));

        notBoughtBooks.setText(Long.toString(students.size() - studentThatBorrowedBook));

    }
}
