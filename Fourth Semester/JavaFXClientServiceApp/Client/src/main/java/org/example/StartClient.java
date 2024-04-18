package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.protocol.ServicesProxy;

import java.io.IOException;
import java.util.Properties;

public class StartClient extends Application {

    private static int defaultPort = 55555;
    private static String defaultServer = "localhost";
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Properties props=new Properties();
        try {
            props.load(StartClient.class.getResourceAsStream("/client.properties"));
            System.out.println("Client properties set.");
            props.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find teledonclient.properties " + e);
        }
        String serverIP = props.getProperty("server.host", defaultServer);
        int serverPort = defaultPort;
        try{
            serverPort = Integer.parseInt(props.getProperty("server.port"));
        }catch (NumberFormatException e){
            System.err.println("Wrong port number " + e.getMessage());
            System.out.println("Using default port: " + defaultPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

        IServices server = new ServicesProxy(serverIP, serverPort);
        FXMLLoader loader = new FXMLLoader(StartClient.class.getResource("/view/LoginView.fxml"));
        AnchorPane root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setServer(server);

        FXMLLoader hloader = new FXMLLoader(StartClient.class.getResource("/view/HomeView.fxml"));
        AnchorPane hroot = hloader.load();
        HomeController homeController = hloader.getController();
        homeController.setServer(server);

        loginController.setController(homeController);
        loginController.setMainParent(hroot);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}