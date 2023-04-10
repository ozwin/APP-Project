package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;
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

public class VacateController implements Initializable {
    @FXML
    TextField property;
    @FXML
    Button submit;
    @FXML
    Button backbutton;
    private PropertyServices propertyServices;
    private LeaseServices leaseServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        property.setText("");
        propertyServices = new PropertyServices();
        leaseServices = new LeaseServices();
    }

    public void removeTenants() throws IOException {
        UUID propertyID = UUID.fromString(property.getText());
        propertyServices.removeTenants(propertyID);
        leaseServices.removeLease(propertyID);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomePage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }


    public void cancel() {
        Stage stage = (Stage) backbutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }
}
