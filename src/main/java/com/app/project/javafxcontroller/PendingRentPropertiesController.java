package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.interfaces.IProperty;
import com.app.project.service.LeaseServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PendingRentPropertiesController implements Initializable {
    public Button closebtn;
    private LeaseServices leaseServices = new LeaseServices();
    @FXML
    private ListView<IProperty> pendingRentListView;
    private ObservableList<IProperty> pendingRentObservableList;

    public void initialize() {
        pendingRentObservableList = FXCollections.observableArrayList();
        pendingRentListView.setItems(pendingRentObservableList);
        displayAllPendingRentProperties();

    }

    private void displayAllPendingRentProperties() {
        // Retrieve all items from the model and add them to the list
        ArrayList<IProperty> pendingRent = (ArrayList<IProperty>) leaseServices.getPropertiesWithPendingRent();
        pendingRentObservableList.addAll(pendingRent);
        pendingRentListView = new ListView<>(pendingRentObservableList);
        Thread thread = new Thread(()->{
            Platform.runLater(()->{
                pendingRentListView.setCellFactory(param -> new ListCell<IProperty>() {
                    @Override
                    protected void updateItem(IProperty item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item.toString());
                    }
                });
            });
        }, "Pending unit threads");
        App.navigate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pendingRentObservableList = FXCollections.observableArrayList();
        pendingRentListView.setItems(pendingRentObservableList);
        displayAllPendingRentProperties();
    }

    @FXML
    private void back() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
        App.navigate();
    }
}
