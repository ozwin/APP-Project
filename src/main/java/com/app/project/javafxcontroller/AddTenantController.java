package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Contact;
import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddTenantController implements Initializable {

    @FXML
    private Label detailsLabel;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField fax;
    @FXML
    private TextField phone;
    @FXML
    private TextField propertyID;
    @FXML
    private Button closebutton;
    private TenantServices tenantServices = new TenantServices(TenantRepository.getInstance(), new PropertyServices(PropertiesRepository.getInstance()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        fax.setText("");
        phone.setText("");
        propertyID.setText("");
    }

    public void addTenant(ActionEvent ae) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String emailText = email.getText();
        String faxText = fax.getText();
        String phoneText = phone.getText();
        String propertyIDText = propertyID.getText();
        Tenant tenant = new Tenant(firstNameText, lastNameText, new Contact(emailText, faxText, phoneText), UUID.fromString(propertyIDText));
        tenantServices.add(tenant);
//        navigate();
        cancel();
    }
    public void navigate(){
//        let's make this utility function or something
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/DisplayTenants.fxml"));
            Scene scene = new Scene(root);
            App.stage.setScene(scene);
            App.stage.setTitle("Display Tenants");
            App.stage.show();
        }catch (Exception ex){

        }
    }
    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }
}