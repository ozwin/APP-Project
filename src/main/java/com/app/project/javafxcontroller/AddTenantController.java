package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Contact;
import com.app.project.entity.Property;
import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import com.app.project.util.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
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

    private String propertyID;
    @FXML
    private Button closebutton;
    @FXML
    private ComboBox<Helper.Item> comboBox = new ComboBox<>();
    private PropertyServices propertyServices;
    private TenantServices tenantServices = new TenantServices(TenantRepository.getInstance(), new PropertyServices(PropertiesRepository.getInstance()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        fax.setText("");
        phone.setText("");
        propertyServices = new PropertyServices(PropertiesRepository.getInstance());
        List<Helper.Item> properties = ((List<Property>) (List<?>) propertyServices.getAll()).stream().map(x -> new Helper.Item(x.getAddress().toString(), x.getPropertyId().toString())).toList();
        comboBox.getItems().addAll(properties);
        comboBox.setCellFactory(listView -> new ListCell<Helper.Item>() {
            @Override
            protected void updateItem(Helper.Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDisplayText());
                }
            }
        });
        comboBox.setButtonCell(new ListCell<Helper.Item>() {
            @Override
            protected void updateItem(Helper.Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDisplayText());
                }
            }
        });
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                propertyID = newValue.getValue();
            }
        });
        if(properties.size()>0){
            comboBox.setValue(properties.get(0));
            propertyID = properties.get(0).getValue();
        }
    }

    public void addTenant(ActionEvent ae) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String emailText = email.getText();
        String faxText = fax.getText();
        String phoneText = phone.getText();
        Tenant tenant = new Tenant(firstNameText, lastNameText, new Contact(emailText, faxText, phoneText), UUID.fromString(propertyID));
        tenantServices.addTenant(tenant);
//        navigate();
        cancel();
    }

    public void navigate() {
//        let's make this utility function or something
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayTenants.fxml")));
            Scene scene = new Scene(root);
            App.stage.setScene(scene);
            App.stage.setTitle("Display Tenants");
            Image icon = new Image("/icons/logo.png");
            App.stage.getIcons().add(icon);
            App.stage.show();
        } catch (Exception ex) {

        }
    }

    public void cancel() {
        closeStage();
        App.navigate();
        //navigate to main screen
    }
    private void closeStage(){
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }

}