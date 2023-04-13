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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddTenantToProperty implements Initializable {
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
    private Button closebutton;
    @FXML
    private Button savebtn;
    private TenantServices tenantServices = new TenantServices(TenantRepository.getInstance(), new PropertyServices(PropertiesRepository.getInstance()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        fax.setText("");
        phone.setText("");
    }

    public void addTenant(ActionEvent ae) throws Exception {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String emailText = email.getText();
        String faxText = fax.getText();
        String phoneText = phone.getText();
        Tenant tenant = new Tenant(firstNameText, lastNameText, new Contact(emailText, faxText, phoneText), RentAUnitController.getProperty());
        tenantServices.addAndRent(tenant);
        navigate();
    }

    public void navigate() throws IOException {
//        let's make this utility function or something
        Stage stage = (Stage) savebtn.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/LeaseData.fxml")));
        Scene scene = new Scene(root);
        App.stage.setScene(scene);
        App.stage.setTitle("Lease Data");
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.show();
    }

    public void cancel() {
        closeStage();
        App.navigate();
        //navigate to main screen
    }

    private void closeStage() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }
}
