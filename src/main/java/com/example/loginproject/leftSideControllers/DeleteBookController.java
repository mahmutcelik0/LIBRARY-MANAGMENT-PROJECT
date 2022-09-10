package com.example.loginproject.leftSideControllers;

import com.example.loginproject.Book;
import com.example.loginproject.DatabaseConnection;
import com.example.loginproject.InputVerification;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class DeleteBookController {
    @FXML
    private TextField bookName,bookAuthor,bookYear,bookType;
    @FXML
    private Label warningMessage;

    @FXML
    private void deleteBookFromDatabase(){
        try {
            Book bookToDelete = new Book(-1,bookName.getText(),bookAuthor.getText(),bookType.getText(),Integer.parseInt(bookYear.getText()));
            HashMap<Boolean, String> verificationResult = new InputVerification().makeControlsForBooks(bookToDelete);

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

            if (!databaseConnection.deleteBookFromDatabase(bookToDelete)) {
                warningMessage.setText("THIS BOOK \nDOESN'T EXIST");
                return;
            }
        }catch (NullPointerException e) {
            warningMessage.setText("FILL ALL FIELDS");
            e.printStackTrace();
            return;
        }catch (NumberFormatException e){
            warningMessage.setText("IN YEAR PART \nENTER ONLY DIGITS");

            return;
        }
        warningMessage.setText("BOOK DELETED \nSUCCESSFULLY!");
    }
}
