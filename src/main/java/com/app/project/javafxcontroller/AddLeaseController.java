package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Lease;
import com.app.project.service.LeaseServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddLeaseController implements Initializable {

    @FXML
    private Button closebutton;
    @FXML
    private Button submit;
    @FXML
    private TextField duration;
    @FXML
    private TextField rent;
    private LeaseServices leaseServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        duration.setText("");
        rent.setText("");
        leaseServices = new LeaseServices();
    }

    public void submit() throws IOException {
        try{
            UUID propertyID = RentAUnitController.getProperty();
            Lease lease = new Lease(leaseServices.getTenants(propertyID), propertyID);
            lease.setAgreedMonthlyRent(Double.parseDouble(rent.getText()));
            lease.setLeaseDuration(Integer.parseInt(duration.getText()));
            leaseServices.addLease(lease);
            displayNext();
        }
        catch(Exception e)
        {
            App.errorPage();
        }

    }

    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }

    public void displayNext() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayLeases.fxml")));
        Scene scene = new Scene(root);
        App.stage.setScene(scene);
        App.stage.setTitle("Display Leases");
        App.stage.show();
    }

}
