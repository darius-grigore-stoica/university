package org.example;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.rmi.ServerException;
import java.security.AllPermission;


public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameTF;

    private IServices server;
    private User crtUser;
    private HomeController controller;
    Parent mainParent;

    public void setController(HomeController controller) {
        this.controller = controller;
    }

    public void setServer(IServices server) {
        this.server = server;
    }
    public void setMainParent(Parent mainParent) {
        this.mainParent = mainParent;
    }

    public void OnLoginClick(MouseEvent event) throws IOException, ServerException, ServiceException {
        String username = usernameTF.getText();
        if(username.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter the username!");
            return;
        }

        String password = passwordTF.getText();
        if(password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter the password!");
            return;
        }

        try {
            server.login(new User(username, password), controller);
            crtUser = new User(username, password);
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Authentication failed!");
            alert.showAndWait();
            return;
        }

        controller.setServer(server);
        controller.setUser(crtUser);
        controller.initializeFields();


        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainParent);
        stage.setScene(scene);
        stage.show();
    }

    public void OnSignUpClick(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up");
        alert.setHeaderText("Sign Up");
        alert.setContentText("Please contact the administrator to create an account");
        alert.showAndWait();
    }

    public User getUser() {
        return crtUser;
    }

    public void setUser(User crtUser) {
        this.crtUser = crtUser;
    }
}
