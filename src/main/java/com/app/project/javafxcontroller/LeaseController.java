package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Lease;
import com.app.project.service.LeaseServices;
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

public class LeaseController implements Initializable {
    private LeaseServices leaseServices = new LeaseServices();
    @FXML
    private ListView<Lease> leaseListView;
    private ObservableList<Lease> leaseObservableList;
    @FXML
    private Button closebtn;

    public void initialize() {
        leaseObservableList = FXCollections.observableArrayList();
        leaseListView.setItems(leaseObservableList);
        displayAllLeases();

    }

    private void displayAllLeases() {
        // Retrieve all items from the model and add them to the list
        ArrayList<Lease> leases = leaseServices.getAllLeases();
        leaseObservableList.addAll(leases);
        leaseListView = new ListView<>(leaseObservableList);
        leaseListView.setCellFactory(param -> new ListCell<Lease>() {
            @Override
            protected void updateItem(Lease item, boolean empty) {
                super.updateItem(item, empty);
                setText(item.toString());
            }
        });
        App.navigate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaseObservableList = FXCollections.observableArrayList();
        leaseListView.setItems(leaseObservableList);
        displayAllLeases();
    }

    //    @FXML
//    private void handleAddNewTenant(ActionEvent ae) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/AddTenant.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Add tenant View");
//        stage.setScene(new Scene(root));
//        stage.show();
//        App.stage.close();
//    }
    @FXML
    private void back() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
        App.navigate();
    }
}
