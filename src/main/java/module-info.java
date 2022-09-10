module com.example.loginproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    

    opens com.example.loginproject to javafx.fxml;
    exports com.example.loginproject;
    exports com.example.loginproject.loginAndSignUpControllers;
    opens com.example.loginproject.loginAndSignUpControllers to javafx.fxml;
    exports com.example.loginproject.leftSideControllers;
    opens com.example.loginproject.leftSideControllers to javafx.fxml;
}