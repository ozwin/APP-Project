package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Property;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.PropertyServices;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class PropertyController implements Initializable {
    @FXML
    private Button closebtn;
    private PropertyServices propertyServices = new PropertyServices(PropertiesRepository.getInstance());

    @FXML
    private TableView<Property> tableView;
    @FXML
    private TableColumn<Property, String> address;
    @FXML
    private TableColumn<Property, String> propertyId;
    @FXML
    private TableColumn<Property, String> rented;
    private ObservableList<Property> propertyObservableList;

    private void displayAllProperties() {
        // Retrieve all items from the model and add them to the list
        System.out.println("Running a new Thread");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Property> properties = (ArrayList<Property>) (ArrayList<?>) propertyServices.getAll();
                propertyObservableList.addAll(properties);
                Platform.runLater(() -> {
                    address.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getAddress().toString()));
                    propertyId.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getID().toString()));
                    rented.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().isVacant()?"Available":"Not Available"));
                    tableView.setItems(propertyObservableList);
//                    App.navigate();
                });
            }
        });
        thread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertyObservableList = FXCollections.observableArrayList();
        displayAllProperties();
    }

    @FXML
    private void handleAddNewProperty(ActionEvent ae) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddProperty.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add property");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    @FXML
    private void back() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
        App.navigate();
    }
}
