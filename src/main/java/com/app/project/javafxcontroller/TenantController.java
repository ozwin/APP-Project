package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TenantController implements Initializable {
    @FXML
    private Button closebtn;
    private TenantServices tenantServices = new TenantServices(TenantRepository.getInstance(), new PropertyServices(PropertiesRepository.getInstance()));
    @FXML
    private ListView<Tenant> tenantListView;
    private ObservableList<Tenant> tenantObservableList;

    public void initialize() {
        tenantObservableList = FXCollections.observableArrayList();
        tenantListView.setItems(tenantObservableList);
        displayAllTenants();

    }

    private void displayAllTenants() {
        // Retrieve all items from the model and add them to the list
        ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantServices.getAll();
        tenantObservableList.addAll(tenants);
        tenantListView = new ListView<>(tenantObservableList);
        Thread thread = new Thread(()->{
            Platform.runLater(()->{
                tenantListView.setCellFactory(param -> new ListCell<Tenant>() {
                    @Override
                    protected void updateItem(Tenant item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item.toString());
                    }
                });
            });
        }, "Display Tenant Thread");
        App.navigate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tenantObservableList = FXCollections.observableArrayList();
        tenantListView.setItems(tenantObservableList);
        displayAllTenants();
    }

    @FXML
    private void handleAddNewTenant(ActionEvent ae) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AddTenant.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add a Tenant");
        stage.setScene(new Scene(root));
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
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
