package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Property;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.PropertyServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PropertyController  implements Initializable{
    private PropertyServices propertyServices=new PropertyServices(PropertiesRepository.getInstance());
    @FXML private ListView<Property> propertyListView;
    private ObservableList<Property> propertyObservableList;

    public void initialize() {
        propertyObservableList = FXCollections.observableArrayList();
        propertyListView.setItems(propertyObservableList);
        displayAllProperties();

    }
    private void displayAllProperties() {
        // Retrieve all items from the model and add them to the list
        ArrayList<Property> properties = (ArrayList<Property>)(ArrayList<?>)propertyServices.getAll();
        propertyObservableList.addAll(properties);
        propertyListView=new ListView<>(propertyObservableList);
        propertyListView.setCellFactory(param -> new ListCell<Property>() {
            @Override
            protected void updateItem(Property item, boolean empty) {
                super.updateItem(item, empty);
                setText(item.getAddress().toString());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertyObservableList = FXCollections.observableArrayList();
        propertyListView.setItems(propertyObservableList);
        displayAllProperties();
    }
    @FXML
    private void handleAddNewProperty(ActionEvent ae) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AddProperty.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add property View");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }
}
