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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPropertyController implements Initializable {
    @FXML
    private Label bedroomslbl;
    @FXML
    private Label bathroomslbl;
    @FXML
    private Label streetNumberlbl;
    @FXML
    private Label unitNumberlbl;

    @FXML
    private RadioButton type1RadioButton;

    @FXML
    private RadioButton type2RadioButton;
    @FXML
    private RadioButton type3RadioButton;

    private ToggleGroup toggleGroup;
    @FXML
    private Label squareftLabel;
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
        bedroomslbl.setVisible(false);
        bathrooms.setVisible(false);
        bathroomslbl.setVisible(false);
        squarefoot.setVisible(false);
        unitNumber.setVisible(false);
        unitNumberlbl.setVisible(false);
        streetNumber.setVisible(false);
        streetNumberlbl.setVisible(false);
        squareftLabel.setVisible(false);
        switch (type) {
            case "Apartment" -> {
                bedrooms.setVisible(true);
                bedroomslbl.setVisible(true);
                bathrooms.setVisible(true);
                bathroomslbl.setVisible(true);
                squarefoot.setVisible(true);
                squareftLabel.setVisible(true);
            }
            case "Condo" -> {
                unitNumber.setVisible(true);
                unitNumberlbl.setVisible(true);
                streetNumber.setVisible(true);
                streetNumberlbl.setVisible(true);
            }
            case "Private House" -> {
                streetNumber.setVisible(true);
                streetNumberlbl.setVisible(true);
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

    public void addProperty(ActionEvent ae) throws IOException {
        try{
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
            closeStage();
            App.navigate();
        }
        catch (Exception e)
        {
            App.errorPage();
        }
    }
    private void closeStage(){
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }

    public void cancel() {
        closeStage();
        App.navigate();
        //navigate to main screen
    }
}