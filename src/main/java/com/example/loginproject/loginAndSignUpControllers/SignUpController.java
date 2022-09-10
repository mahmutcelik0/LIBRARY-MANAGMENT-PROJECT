package com.example.loginproject.loginAndSignUpControllers;

import com.example.loginproject.startClass;
import com.example.loginproject.InputVerification;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private Label warningMessage;
    @FXML
    private TextField userNameField, passwordField,emailField,contactNoField;

    @FXML
    private void signUpButtonReleased(){

        //FIELD KONTROLU
        InputVerification controlSystem = new InputVerification();

        //FILLITY OF FIELDS CONTROL
        if(controlSystem.controlOffFillity(userNameField.getText(),passwordField.getText(),emailField.getText(),contactNoField.getText())){
            warningMessage.setText("TEXT FIELDS CAN'T BE EMPTY");
            return;
        }

        //USERNAME CONTROL
        if(!controlSystem.usernameVerification(userNameField.getText())){
            warningMessage.setText("USERNAME CAN'T CONTAIN SPACE");
            return;
        }

        //PASSWORD CONTROL
        if(!controlSystem.passwordVerification(passwordField.getText())){
            warningMessage.setText("PASSWORD MUST BE -> LEAST 8 CHARACHTER");
            return;
        }

        //MAIL CONTROL
        if(!controlSystem.mailVerification(emailField.getText())){
            warningMessage.setText("INVALID MAIL ADDRESS (MUST HAVE @)");
            return;
        }

        //CONTACT NO CONTROL
        if(!controlSystem.contactNoVerification(contactNoField.getText())){
            warningMessage.setText("CONTACT NO MUST BE 11 DIGIT");
            return;
        }


        //DATABASE CONTROL GERCEKLESECEK (MEVCUT ISE MEVCUT KULLANICI - DEGILSE ALTTAKI + KAYIT)
        if(!startClass.getDatabaseConnection().signUpMethod(userNameField.getText(),passwordField.getText(),emailField.getText(),contactNoField.getText())){
            warningMessage.setText("THIS USER ALREADY EXIST!");
            return;
        }

        //KAYIT BASARILI YAZISI GERCEKLESECEK
        warningMessage.setText("Registration Successful. NOW YOU CAN LOGIN!");





    }

}
