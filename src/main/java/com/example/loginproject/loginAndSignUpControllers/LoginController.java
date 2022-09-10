package com.example.loginproject.loginAndSignUpControllers;

import com.example.loginproject.startClass;
import com.example.loginproject.InputVerification;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField userNameField,passwordField,emailField,contactNoField;
    @FXML
    private Label warningMessage;

    //SIGN UP BUTTON UNA BASILDIGINDA SIGN UP PAGE INE GECILMEYI SAGLAR
    @FXML
    private void toGoSignUp(){
        startClass.toLoadFXML("loginAndSignUp/signUpPage");
    }

    @FXML
    private void loginButtonReleased(){

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

        //DATABASE CONTROL GERCEKLESECEK
        if(!startClass.getDatabaseConnection().checkUser(userNameField.getText(),passwordField.getText(),emailField.getText(),contactNoField.getText())){
            warningMessage.setText("THERE IS NO SUCH USER.PLEASE SIGN UP!");
            return;
        }

        //ASIL PAGE IN YUKLENMESI GERCEKLESECEK
        startClass.toLoadFXML("mainParts/mainPage");

    }

}

