package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.EnrollmentDTO;

import java.io.IOException;
import java.net.URL;
import java.rmi.ServerException;
import java.util.ResourceBundle;


public class HomeController implements Initializable, IObserver {
    @FXML
    private TextField nameTF;

    @FXML
    private TextField ageTF;

    @FXML
    private ListView<Competition> availableCompetitionsListView;

    @FXML
    private ListView<Enrollment> enrollmentsListView;

    @FXML
    private ListView<Child> searchChildListView;

    @FXML
    private ComboBox<AgeGroup> grupaComboBox;

    @FXML
    private ComboBox<Competition> probaComboBoxEnroll;

    @FXML
    private ComboBox<Competition> probaComboBoxSearch;

    @FXML
    private Button logoutBtn;

    private User crtUser;

    private IServices server;

    private LoginController controller;


    public void setUser(User u) {
        this.crtUser = u;
    }

    public HomeController() {
    }

    public HomeController(IServices server) {
        this.server = server;
        initializeFields();
    }

    public void setServer(IServices server) {
        this.server = server;
    }


    public void OnLogoutClick(MouseEvent event)  {
        try {
            server.logout(crtUser, this);
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Logout failed!");
            alert.showAndWait();
        }
        FXMLLoader loader = new FXMLLoader(StartClient.class.getResource("/view/LoginView.fxml"));
        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();
        loginController.setServer(server);

        FXMLLoader hloader = new FXMLLoader(StartClient.class.getResource("/view/HomeView.fxml"));
        AnchorPane hroot = null;
        try {
            hroot = hloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomeController homeController = hloader.getController();
        homeController.setServer(server);

        loginController.setController(homeController);
        loginController.setMainParent(hroot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initializeFields() {
        grupaComboBox.getItems().setAll(AgeGroup.values());

        ObservableList<Competition> competitions = null;
        try {
            competitions = FXCollections.observableArrayList(server.getAllCompetitions(crtUser, this));
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Error getting competitions!");
            alert.showAndWait();
        }
        probaComboBoxEnroll.getItems().setAll(competitions);
        probaComboBoxSearch.getItems().setAll(competitions);
        availableCompetitionsListView.getItems().setAll(competitions);

        ObservableList<Enrollment> enrollments = null;
        try {
            enrollments = FXCollections.observableArrayList(server.getAllEnrollments(crtUser, this));
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Error getting enrollments!");
            alert.showAndWait();
        }
        enrollmentsListView.getItems().setAll(enrollments);
    }

    public void enroll(MouseEvent event) {
        Child c = new Child(nameTF.getText(), Integer.parseInt(ageTF.getText()));
        Competition comp = probaComboBoxEnroll.getSelectionModel().getSelectedItem();

        try {
            server.enroll(c, comp, this);
            updateEnrollmentsNotify(new Enrollment(c.getEntityID(), comp.getEntityID()));
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Enrollment failed!");
            alert.showAndWait();
        }
    }

    public void search(MouseEvent event) {
        AgeGroup grupa = grupaComboBox.getSelectionModel().getSelectedItem();
        Competition comp = probaComboBoxSearch.getSelectionModel().getSelectedItem();

        Child[] children = new Child[0];
        try {
            children = server.search(comp, grupa, this);
            searchChildListView.getItems().addAll(children);

        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Search failed!");
            alert.showAndWait();
        }

    }

    @Override
    public void updateEnrollmentsNotify(Enrollment e) throws ServiceException {
        Platform.runLater(() -> {
            try {
                enrollmentsListView.getItems().setAll(server.getAllEnrollments(crtUser, this));
            } catch (ServiceException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Error getting enrollments!");
                alert.showAndWait();
            }
        });
    }
}
