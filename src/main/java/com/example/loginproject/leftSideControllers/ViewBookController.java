package com.example.loginproject.leftSideControllers;

import com.example.loginproject.Book;
import com.example.loginproject.leftSideControllers.MODEL.LISTSforStudentandBook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewBookController implements Initializable {
    @FXML
    private TableView<Book> bookView;
    @FXML
    private TableColumn<Book,Integer> bookID,bookYear,bookBorrowerID;
    @FXML
    private TableColumn<Book,String> bookName,bookAuthor,bookType,bookBorrowerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookID.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ID"));
        bookName.setCellValueFactory(new PropertyValueFactory<Book,String>("nameOfBook"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        bookType.setCellValueFactory(new PropertyValueFactory<Book,String>("type"));
        bookYear.setCellValueFactory(new PropertyValueFactory<Book,Integer>("year"));
        bookBorrowerID.setCellValueFactory(new PropertyValueFactory<Book,Integer>(  "IDofIssuedFrom"));
        bookBorrowerName.setCellValueFactory(new PropertyValueFactory<Book,String>("nameOfIssuedFrom"));

        bookView.setItems(new LISTSforStudentandBook().getBooksListFromDatabase());
    }
}
