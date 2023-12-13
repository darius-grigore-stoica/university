package GUI;

import domain.Pacient;
import domain.Programare;
import exceptions.DuplicateElementException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.IRepository;
import repository.SQLPacientRepository;
import repository.SQLProgramareRepository;
import service.ServicePacient;
import service.ServiceProgramare;

public class JavaFXMain extends Application {
    @Override
    public void start(Stage primaryStage) {

        IRepository<Pacient> pacientIRepository = new SQLPacientRepository("myDB.sqlite");
        ServicePacient servicePacient = new ServicePacient(pacientIRepository);

        IRepository<Programare> programareIRepository = new SQLProgramareRepository("myDB.sqlite");
        ServiceProgramare serviceProgramare = new ServiceProgramare(programareIRepository);

        HBox root = new HBox();

        VBox pacientsVBox = new VBox();
        VBox appointmentsVBox = new VBox();

        pacientsVBox.setPrefWidth(520);
        appointmentsVBox.setPrefWidth(520);
        pacientsVBox.setPadding(new javafx.geometry.Insets(10));
        appointmentsVBox.setPadding(new javafx.geometry.Insets(10));

        root.getChildren().add(pacientsVBox);
        root.getChildren().add(appointmentsVBox);

        Scene scene = new Scene(root, 1080, 720);

        ListView<Pacient> listView = new ListView<>();
        ObservableList<Pacient> items = FXCollections.observableArrayList(servicePacient.getAll());
        listView.setItems(items);

        GridPane pacientGridPane = new GridPane();
        Label idLabel = new Label("ID");
        TextField pacientIDTextField = new TextField();

        Label nameLabel = new Label("Name");
        TextField pacientNameTextField = new TextField();

        Label surnameLabel = new Label("Surname");
        TextField pacientSurnameTextField = new TextField();

        Label varstaLabel = new Label("Varsta");
        TextField pacientVarstaTextField = new TextField();


        idLabel.setPadding(new javafx.geometry.Insets(10));
        nameLabel.setPadding(new javafx.geometry.Insets(10));
        surnameLabel.setPadding(new javafx.geometry.Insets(10));
        varstaLabel.setPadding(new javafx.geometry.Insets(10));
        pacientGridPane.add(idLabel, 0, 0);
        pacientGridPane.add(pacientIDTextField, 1, 0);
        pacientGridPane.add(nameLabel, 0, 1);
        pacientGridPane.add(pacientNameTextField, 1, 1);
        pacientGridPane.add(surnameLabel, 0, 2);
        pacientGridPane.add(pacientSurnameTextField, 1, 2);
        pacientGridPane.add(varstaLabel, 0, 3);
        pacientGridPane.add(pacientVarstaTextField, 1, 3);

        pacientsVBox.setPadding(new javafx.geometry.Insets(10));

        HBox hbox = new HBox();
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");
        hbox.getChildren().add(addButton);
        hbox.getChildren().add(updateButton);
        hbox.getChildren().add(deleteButton);
        hbox.getChildren().add(clearButton);

        addButton.setOnMouseClicked(mouseEvent -> {
            try {
                servicePacient.addPacient(Integer.parseInt(pacientIDTextField.getText()), pacientNameTextField.getText(), pacientSurnameTextField.getText(), Integer.parseInt(pacientVarstaTextField.getText()));
                items.setAll(servicePacient.getAll());
                pacientIDTextField.setText("");
                pacientNameTextField.setText("");
                pacientSurnameTextField.setText("");
                pacientVarstaTextField.setText("");
            } catch (DuplicateElementException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Duplicate element");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            Pacient pacient = listView.getSelectionModel().getSelectedItem();
            pacientIDTextField.setText(String.valueOf(pacient.getId()));
            pacientNameTextField.setText(pacient.getNume());
            pacientSurnameTextField.setText(pacient.getPrenume());
            pacientVarstaTextField.setText(String.valueOf(pacient.getVarsta()));
        });

        updateButton.setOnMouseClicked(mouseEvent -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            servicePacient.updatePacient(selectedIndex, pacientNameTextField.getText(), pacientSurnameTextField.getText(), Integer.parseInt(pacientVarstaTextField.getText()));
            items.setAll(servicePacient.getAll());
            pacientIDTextField.setText("");
            pacientNameTextField.setText("");
            pacientSurnameTextField.setText("");
            pacientVarstaTextField.setText("");

        });

        deleteButton.setOnMouseClicked(mouseEvent -> {
            servicePacient.deletePacient(Integer.parseInt(pacientIDTextField.getText()));
            items.setAll(servicePacient.getAll());
            pacientIDTextField.setText("");
            pacientNameTextField.setText("");
            pacientSurnameTextField.setText("");
            pacientVarstaTextField.setText("");
        });

        clearButton.setOnMouseClicked(mouseEvent -> {
            pacientIDTextField.setText("");
            pacientNameTextField.setText("");
            pacientSurnameTextField.setText("");
            pacientVarstaTextField.setText("");
        });

        ListView<Programare> listView2 = new ListView<>();
        ObservableList<Programare> items2 = FXCollections.observableArrayList(serviceProgramare.getAll());
        listView2.setItems(items2);

        GridPane appointmentGridPane = new GridPane();
        Label idLabel2 = new Label("ID");
        TextField appointmentIDTextField = new TextField();
        Label pacientLabel = new Label("Pacient Name");
        TextField appointmentPacientTextField = new TextField();
        Label oraLabel = new Label("Hour");
        TextField appointmentOraTextField = new TextField();
        Label dataLabel = new Label("Date (dd/mm/yyyy)");
        TextField appointmentDataTextField = new TextField();
        Label porpouseLabel = new Label("Porpouse");
        TextField appointmentScopTextField = new TextField();

        idLabel2.setPadding(new javafx.geometry.Insets(10));
        pacientLabel.setPadding(new javafx.geometry.Insets(10));
        oraLabel.setPadding(new javafx.geometry.Insets(10));
        dataLabel.setPadding(new javafx.geometry.Insets(10));
        porpouseLabel.setPadding(new javafx.geometry.Insets(10));
        appointmentGridPane.add(idLabel2, 0, 0);
        appointmentGridPane.add(appointmentIDTextField, 1, 0);
        appointmentGridPane.add(pacientLabel, 0, 1);
        appointmentGridPane.add(appointmentPacientTextField, 1, 1);
        appointmentGridPane.add(oraLabel, 0, 2);
        appointmentGridPane.add(appointmentOraTextField, 1, 2);
        appointmentGridPane.add(dataLabel, 0, 3);
        appointmentGridPane.add(appointmentDataTextField, 1, 3);
        appointmentGridPane.add(porpouseLabel, 0, 4);
        appointmentGridPane.add(appointmentScopTextField, 1, 4);


        HBox hbox2 = new HBox();
        Button addButton2 = new Button("Add");
        Button updateButton2 = new Button("Update");
        Button deleteButton2 = new Button("Delete");
        Button clearButton2 = new Button("Clear");
        hbox2.getChildren().add(addButton2);
        hbox2.getChildren().add(updateButton2);
        hbox2.getChildren().add(deleteButton2);
        hbox2.getChildren().add(clearButton2);


        listView2.setOnMouseClicked(mouseEvent -> {
            Programare programare = listView2.getSelectionModel().getSelectedItem();
            appointmentIDTextField.setText(String.valueOf(programare.getId()));
            appointmentPacientTextField.setText(programare.getNumePacient());
            appointmentOraTextField.setText(programare.getOra());
            appointmentDataTextField.setText(programare.getData());
            appointmentScopTextField.setText(programare.getScop_programre());
        });

        addButton2.setOnMouseClicked(mouseEvent -> {
            try {
                serviceProgramare.addProgramare(Integer.parseInt(appointmentIDTextField.getText()), appointmentPacientTextField.getText(), appointmentOraTextField.getText(), appointmentDataTextField.getText(), appointmentScopTextField.getText());
                items2.setAll(serviceProgramare.getAll());
                appointmentIDTextField.setText("");
                appointmentPacientTextField.setText("");
                appointmentOraTextField.setText("");
                appointmentDataTextField.setText("");
                appointmentScopTextField.setText("");

            } catch (DuplicateElementException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Duplicate element");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

        updateButton2.setOnMouseClicked(mouseEvent -> {
            int selectedIndex = listView2.getSelectionModel().getSelectedIndex();
            serviceProgramare.updateProgramare(selectedIndex, appointmentPacientTextField.getText(), appointmentOraTextField.getText(), appointmentDataTextField.getText(), appointmentScopTextField.getText());
            items2.setAll(serviceProgramare.getAll());
            appointmentIDTextField.setText("");
            appointmentPacientTextField.setText("");
            appointmentOraTextField.setText("");
            appointmentDataTextField.setText("");
            appointmentScopTextField.setText("");
        });

        deleteButton2.setOnMouseClicked(mouseEvent -> {
            serviceProgramare.deleteProgramare(Integer.parseInt(appointmentIDTextField.getText()));
            items2.setAll(serviceProgramare.getAll());
            appointmentIDTextField.setText("");
            appointmentPacientTextField.setText("");
            appointmentOraTextField.setText("");
            appointmentDataTextField.setText("");
            appointmentScopTextField.setText("");
        });

        clearButton2.setOnMouseClicked(mouseEvent -> {
            appointmentIDTextField.setText("");
            appointmentPacientTextField.setText("");
            appointmentOraTextField.setText("");
            appointmentDataTextField.setText("");
            appointmentScopTextField.setText("");
        });

        pacientsVBox.getChildren().add(listView);
        pacientsVBox.getChildren().add(pacientGridPane);
        pacientsVBox.getChildren().add(hbox);
        appointmentsVBox.getChildren().add(listView2);
        appointmentsVBox.getChildren().add(appointmentGridPane);
        appointmentsVBox.getChildren().add(hbox2);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
