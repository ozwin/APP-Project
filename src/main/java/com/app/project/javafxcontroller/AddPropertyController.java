package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Address;
import com.app.project.entity.Apartment;
import com.app.project.entity.Condo;
import com.app.project.entity.PrivateHouse;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.PropertyServices;
import com.app.project.util.Helper;
import com.app.project.util.RentalPropertyFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPropertyController implements Initializable {

    @FXML
    private RadioButton type1RadioButton;

    @FXML
    private RadioButton type2RadioButton;
    @FXML
    private RadioButton type3RadioButton;

    private ToggleGroup toggleGroup;
    @FXML
    private Label detailsLabel;
    @FXML
    private TextField streetName;
    @FXML
    private TextField city;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField bedrooms;
    @FXML
    private TextField bathrooms;
    @FXML
    private TextField squarefoot;
    @FXML
    private TextField streetNumber;
    @FXML
    private TextField unitNumber;
    private String type = "Apartment";
    @FXML
    private Button closebutton;
    private PropertyServices propertyServices = new PropertyServices(PropertiesRepository.getInstance());


    // Method to set the type property
    public void setType(String type) {
        this.type = type;
        bedrooms.setVisible(false);
        bathrooms.setVisible(false);
        squarefoot.setVisible(false);
        unitNumber.setVisible(false);
        streetNumber.setVisible(false);

        switch (type) {
            case "Apartment" -> {
                bedrooms.setVisible(true);
                bathrooms.setVisible(true);
                squarefoot.setVisible(true);

            }
            case "Condo" -> {
                unitNumber.setVisible(true);
                streetNumber.setVisible(true);
            }
            case "Private House" -> {
                streetNumber.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        type1RadioButton.setToggleGroup(toggleGroup);
        type2RadioButton.setToggleGroup(toggleGroup);
        type3RadioButton.setToggleGroup(toggleGroup);
        streetName.setText("");
        streetNumber.setText("");
        city.setText("");
        postalCode.setText("");
        bedrooms.setText("");
        bathrooms.setText("");
        unitNumber.setText("");
        squarefoot.setText("");
        Helper.setNumericInputFilter(bedrooms);
        Helper.setNumericInputFilter(bathrooms);
        Helper.setNumericInputFilter(squarefoot);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                String selectedType = selectedRadioButton.getText();
                setType(selectedType);
            }
        });
    }

    public void addProperty(ActionEvent ae) {
        String streetNameText = streetName.getText();
        String cityText = city.getText();
        String postalCodeText = postalCode.getText();

        String streetNumberText = streetNumber.getText();
        String unitNumberText = unitNumber.getText();

        String bedroomsText = bedrooms.getText();
        String bathroomsText = bathrooms.getText();
        String squarefootText = squarefoot.getText();

        IProperty property = RentalPropertyFactory.getPropertyObject(type.trim().toUpperCase(), new Address(streetNameText, cityText, postalCodeText));
        switch (type) {
            case "Apartment" -> {
                ((Apartment) property).setNumberOfBathrooms(Integer.parseInt(bathroomsText));
                ((Apartment) property).setNumberOfBedrooms(Integer.parseInt(bedroomsText));
                ((Apartment) property).setSquareFoot(Float.parseFloat(squarefootText));
            }
            case "Condo" -> {
                ((Condo) property).setUnitNumber(unitNumberText);
                ((Condo) property).setStreetNumber(streetNumberText);

            }
            case "Private House" -> {
                ((PrivateHouse) property).setStreetNumber(streetNumberText);
            }
        }
        propertyServices.add(property);
        App.navigate();
    }

    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }
}