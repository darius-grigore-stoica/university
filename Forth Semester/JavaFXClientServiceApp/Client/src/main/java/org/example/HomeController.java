package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
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

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    void enroll(MouseEvent event) {
        if(nameTF.getText().isEmpty() || ageTF.getText().isEmpty() || probaComboBoxEnroll.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enrollment failed");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
            return;
        }
        try {
            service.enroll(new Child(nameTF.getText(), Integer.parseInt(ageTF.getText())), probaComboBoxEnroll.getValue());
            enrollmentsListView.setItems(FXCollections.observableArrayList(service.getAllEnrollments()));
        } catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enrollment failed");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enrollment failed");
            alert.setContentText("An error occurred while trying to enroll the child");
            alert.showAndWait();
        }
    }

    @FXML
    void logout(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/LoginView.fxml"));
            Parent root = fxmlLoader.load();
            LoginController loginController = fxmlLoader.getController();
            loginController.setService(service);

            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void search(MouseEvent event) {
        ArrayList<Child> kids = (ArrayList<Child>) service.search(probaComboBoxSearch.getValue(), grupaComboBox.getValue());
        ObservableList<Child> observableKids = FXCollections.observableArrayList(kids);
        if (observableKids.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No children found");
            alert.setContentText("No children found for the selected competition and age group");
            alert.showAndWait();
        } else
            searchChildListView.setItems(observableKids);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initList() {
        ArrayList<Competition> competitions = (ArrayList<Competition>) service.getAllCompetitions();
        ObservableList<Competition> observableCompetitions = FXCollections.observableArrayList(competitions);

        ArrayList<Enrollment> enrollments = (ArrayList<Enrollment>) service.getAllEnrollments();
        ObservableList<Enrollment> observableEnrollments = FXCollections.observableArrayList(enrollments);

        availableCompetitionsListView.getItems().addAll(observableCompetitions);
        probaComboBoxEnroll.getItems().addAll(observableCompetitions);
        probaComboBoxSearch.getItems().addAll(observableCompetitions);
        enrollmentsListView.getItems().addAll(observableEnrollments);
        grupaComboBox.getItems().addAll(AgeGroup.values());
    }
}
