package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.implementations.ChildDBRepository;
import org.example.implementations.CompetitionDBRepository;
import org.example.implementations.EnrollmentDBRepository;
import org.example.implementations.UserDBRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
            Service service = new Service(new UserDBRepository(props), new ChildDBRepository(props), new CompetitionDBRepository(props), new EnrollmentDBRepository(props));

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/LoginView.fxml"));
            Parent root = fxmlLoader.load();
            LoginController loginController = fxmlLoader.getController();
            loginController.setService(service);

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        } catch (
                Exception e) {
            System.out.println("Error starting JavaFX application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}