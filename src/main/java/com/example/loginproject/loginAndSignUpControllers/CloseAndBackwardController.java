package com.example.loginproject.loginAndSignUpControllers;

import com.example.loginproject.startClass;
import javafx.fxml.FXML;

public class CloseAndBackwardController {

    @FXML
    private void closeProgram(){
        System.exit(0);
    }

    @FXML
    private void toGoBackward(){
        startClass.toLoadFXML("loginAndSignUp/loginPage");
    }
}
