package com.app.project.javafxcontroller;

import com.app.project.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.app.project.util.Helper.setAPPIcon;

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
        stage.setTitle("Add Property");
        stage.setScene(new Scene(root));
        stage.show();
        setAPPIcon(stage,"/icons/house.png");
        App.stage.close();
    }

    public void addTenant(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddTenant.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add a Tenant");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void rentUnit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RentAUnit.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Rent a Unit");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void displayProperties(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayProperties.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Properties");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void displayTenants(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayTenants.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Tenants");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void displayVacantUnits(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayVacantUnits.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Vacant Units");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void displayRentedUnits(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayRentedUnits.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Diplay Rented Units");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void displayLeases(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayLeases.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display All Leases");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void vacateUnit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vacate.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Vacate a Unit");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void payRent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/PayRent.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Pay Rent");
        stage.setScene(new Scene(root));
        stage.show();
        setAPPIcon(stage,"/icons/salary.png");
        App.stage.close();
    }

    public void displayPendingRent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayPendingRentProperties.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Pending Rent Properties");
        stage.setScene(new Scene(root));
        stage.show();
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

//    public void cancel() {
//        Stage stage = (Stage) closebutton.getScene().getWindow();
//        stage.close();
//        App.navigate();
//        //navigate to main screen
//    }
}