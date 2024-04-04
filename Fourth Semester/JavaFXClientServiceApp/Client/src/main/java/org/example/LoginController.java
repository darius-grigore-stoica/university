package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    private Service service;
    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameTF;


    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    void login(MouseEvent event) {
        boolean login = service.login(usernameTF.getText(), passwordTF.getText());
        if (login) {
            System.out.println("Login successful");
            switchToHomePage();
        } else {
            System.out.println("Login failed");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login failed");
            alert.setContentText("Username or password is incorrect");
            alert.showAndWait();
        }
    }

    private void switchToHomePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/HomeView.fxml"));
            Parent root = fxmlLoader.load();

            HomeController homeController = fxmlLoader.getController();

            homeController.setService(this.service);

            homeController.setService(service);
            homeController.initList();

            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void signUp(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Sign up");
        alert.setContentText("Please contact the administrator to create an account");
        alert.showAndWait();
    }

}
