package com.app.project.javafxcontroller;

import com.app.project.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WelcomeController {

    @FXML
    private Label detailsLabel;

    @FXML
    private Button addProperty;
    @FXML
    private Button addTenant;
    @FXML
    private Button rentUnit;
    @FXML
    private Button displayProperties;
    @FXML
    private Button displayTenants;
    @FXML
    private Button displayVacantUnits;
    @FXML
    private Button displayRentedUnits;
    @FXML
    private Button displayLeases;
    @FXML
    private Button payRent;
    @FXML
    private Button vacateUnit;
    @FXML
    private Button displayPendingRent;



    public void addProperty(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddProperty.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add Property View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void addTenant(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddTenant.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add a Tenant View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void rentUnit(ActionEvent actionEvent) {
//        Parent root = FXMLLoader.load(getClass().getResource("/AddProperty.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Add property View");
//        stage.setScene(new Scene(root));
//        stage.show();
//        App.stage.close();
    }

    public void displayProperties(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayProperties.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Properties View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void displayTenants(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayTenants.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Tenants View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void displayVacantUnits(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DisplayVacantUnits.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Vacant Units View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void displayRentedUnits(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayRentedUnits.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Diplay Rented Units View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void displayLeases(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayLeases.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display All Leases View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void vacateUnit(ActionEvent actionEvent) {
//        Parent root = FXMLLoader.load(getClass().getResource("/AddProperty.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Add property View");
//        stage.setScene(new Scene(root));
//        stage.show();
//        App.stage.close();
    }

    public void payRent(ActionEvent actionEvent) {
//        Parent root = FXMLLoader.load(getClass().getResource("/AddProperty.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Add property View");
//        stage.setScene(new Scene(root));
//        stage.show();
//        App.stage.close();
    }

    public void displayPendingRent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayPendingRentProperties.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Rented Properties View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

//    public void cancel() {
//        Stage stage = (Stage) closebutton.getScene().getWindow();
//        stage.close();
//        App.navigate();
//        //navigate to main screen
//    }
}