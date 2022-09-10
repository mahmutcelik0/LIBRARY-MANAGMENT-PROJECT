package com.example.loginproject.leftSideControllers.MODEL;

import com.example.loginproject.Book;
import com.example.loginproject.DatabaseConnection;
import com.example.loginproject.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class LISTSforStudentandBook {

    private ObservableList<Student> studentsFromDatabase = FXCollections.observableArrayList();
    private ObservableList<Book> booksFromDatabase = FXCollections.observableArrayList();


    public void updateStudentsUsingDatabase(){
        DatabaseConnection databaseConnection = new DatabaseConnection();

        studentsFromDatabase.clear();
        studentsFromDatabase.addAll(databaseConnection.getStudentFromDatabase());
    }

    public void updateBooksUsingDatabase(){
        DatabaseConnection databaseConnection = new DatabaseConnection();

        booksFromDatabase.clear();
        booksFromDatabase.addAll(databaseConnection.getBookFromDatabase());
    }

    public int getSizeOfStudents(){
        updateStudentsUsingDatabase();
        return studentsFromDatabase.size();
    }

    //GUNCELLER VE DATABASEDEN GELENLERI DONDURUR
    public ObservableList<Student> getStudentsListFromDatabase(){
        updateStudentsUsingDatabase();
        return studentsFromDatabase;
    }


    public int getSizeOfBooks(){
        updateBooksUsingDatabase();
        return booksFromDatabase.size();
    }

    public ObservableList<Book> getBooksListFromDatabase(){
        updateBooksUsingDatabase();
        return booksFromDatabase;
    }

    public Student checkAndReturnStudentFromList(Integer studentID){
        updateStudentsUsingDatabase();

        List<Student> studentList =  studentsFromDatabase.stream().filter(student -> student.getID() == studentID ).toList();

        if(studentList.isEmpty()) return null;
        return studentList.get(0);
    }

    public Book checkAndReturnBookFromList(Integer bookID){
        updateBooksUsingDatabase();

        List<Book> bookList = booksFromDatabase.stream().filter(book -> book.getID() == bookID).toList();

        if(bookList.isEmpty()) return null;
        return bookList.get(0);
    }

}
