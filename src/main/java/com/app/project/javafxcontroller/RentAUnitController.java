package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.repository.PropertiesRepository;
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

public class RentAUnitController implements Initializable {
    private static UUID ppid;
    @FXML
    private Button closebutton;
    @FXML
    private Button yesbtn;
    @FXML
    private Button nobtn;
    @FXML
    private Button submitbtn;
    @FXML
    private TextField userID;
    private PropertyServices propertyServices;
    @FXML
    private TextField propertyID;

    public static UUID getProperty() {
        return ppid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertyID.setText("");
        userID.setText("");
        ppid = null;
        propertyServices = new PropertyServices(PropertiesRepository.getInstance());
    }

    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }

    public void next() throws Exception {
//        propertyServices.moveTenantToProperty();
        nobtn.setVisible(false);
        userID.setVisible(true);
        submitbtn.setVisible(true);
    }

    public void moveTenants() throws Exception {
        UUID pID = UUID.fromString(propertyID.getText());
        ppid = pID;
        UUID uid = UUID.fromString(userID.getText());
        propertyServices.moveTenantToProperty(pID, uid);
    }

    public void addTenant() throws IOException {
        UUID pid = UUID.fromString(propertyID.getText());
        ppid = pid;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddTenantToProperty.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add a Tenant View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }
}
