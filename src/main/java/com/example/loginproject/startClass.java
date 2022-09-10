package com.example.loginproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class startClass extends Application {
    private static Stage myStage = null;
    public String element = "";

    private static DatabaseConnection databaseProcess = null;

//https://stackoverflow.com/questions/13032257/combo-box-javafx-with-fxml
    //COMMUNICATION BETWEEN CONTROLLERS IS IMPORTANT FOR THIS PROJECT. TAKE IT INTO NOTES
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(startClass.class.getResource("loginAndSignUp/loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 600);
        stage.setTitle("Hello!");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();


        myStage = stage;

        databaseProcess = new DatabaseConnection();
        databaseProcess.makeConnectionBeginning();
//        System.out.println(databaseProcess.signUpMethod("mahmutt","celik","mahmut.382@hotmail.com","12345678912"));
    }

    public static void toLoadFXML(String fxmlToLoad){
        FXMLLoader fxml = new FXMLLoader(startClass.class.getResource(fxmlToLoad+".fxml"));

        try {
            Scene scene2 = new Scene(fxml.load() , 1024,600);
            myStage.setScene(scene2);
            myStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static DatabaseConnection getDatabaseConnection(){
        return databaseProcess;
    }
}