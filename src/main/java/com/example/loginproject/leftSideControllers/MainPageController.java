package com.example.loginproject.leftSideControllers;


import com.example.loginproject.startClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Pane paneForFXML;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            Pane newPane =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/loginproject/mainParts/leftParts/homePage.fxml")));
            paneForFXML.getChildren().setAll(newPane);



        }catch (IOException m){
            System.out.println("LOAD PROBLEM");

        }
    }

    @FXML
    private void logoutMethod(){
        startClass.toLoadFXML("loginAndSignUp/loginPage");
    }

    @FXML
    private void closeProgramMethod(){
        System.exit(0);
    }

    //SAG TARAFA GELMESI GEREKEN FXML I ID OLARAK ALIYOR
    @FXML
    private void changeRightPane(ActionEvent e){
        Button button = (Button) e.getSource();

        try {
            //https://stackoverflow.com/questions/26142760/get-file-from-another-package-on-different-directory-level#comment40978530_26142760 EXPLANATION
            //I HAVE TO USER / BEGINNING OF PATH (getClass().getResource() or MainPageController.class.getResource() doesn't matter)
            Pane newPane =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/loginproject/mainParts/leftParts/"+button.getId()+".fxml")));
            paneForFXML.getChildren().setAll(newPane);



        }catch (IOException m){
            System.out.println("LOAD PROBLEM");

        }
    }
}
