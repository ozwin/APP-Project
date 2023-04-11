package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Property;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;
import com.app.project.util.Helper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

public class VacateController implements Initializable {

    String propertyId;
    @FXML
    private ComboBox<Helper.Item> comboBox = new ComboBox<>();
    @FXML
    Button submit;
    @FXML
    Button backbutton;
    private PropertyServices propertyServices;
    private LeaseServices leaseServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertyServices = new PropertyServices();
        leaseServices = new LeaseServices();
        propertyServices = new PropertyServices(PropertiesRepository.getInstance());
        List<Helper.Item> properties = ((List<Property>) (List<?>) propertyServices.findRented()).stream().map(x -> new Helper.Item(x.getAddress().toString(), x.getPropertyId().toString())).toList();
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
                propertyId = newValue.getValue();
            }
        });
        if(properties.size()>0){
            comboBox.setValue(properties.get(0));
            propertyId = properties.get(0).getValue();
        }
    }

    public void removeTenants() throws IOException {
        UUID propertyID = UUID.fromString(propertyId);
        propertyServices.removeTenants(propertyID);
        leaseServices.removeLease(propertyID);
        Stage old = (Stage) backbutton.getScene().getWindow();
        old.close();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomePage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("OSM Real Estate");
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
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
