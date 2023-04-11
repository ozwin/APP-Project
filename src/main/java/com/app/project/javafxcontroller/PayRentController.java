package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Property;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;
import com.app.project.util.Helper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class PayRentController implements Initializable {

    @FXML
    private Text question;
    @FXML
    private Text question1;
    @FXML
    private Button closebutton;
    @FXML
    private Button submit;
    @FXML
    private ComboBox<Helper.Item> comboBox = new ComboBox<>();
    private String propertyId;
    @FXML
    private TextField rent;
    private LeaseServices leaseServices;
    private  PropertyServices propertyServices;
    double amount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rent.setText("");
        Helper.setNumericInputFilter(rent);
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

    public void submit() throws Exception {
        amount = Double.parseDouble(rent.getText());
        leaseServices.recordPayment(UUID.fromString(propertyId), amount);
        App.navigate();
    }

    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
    }
}
