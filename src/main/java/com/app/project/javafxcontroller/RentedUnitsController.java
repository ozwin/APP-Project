package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Property;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.PropertyServices;
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

public class RentedUnitsController implements Initializable {
    private PropertyServices propertyServices = new PropertyServices(PropertiesRepository.getInstance());
    @FXML
    private ListView<Property> rentedUnitsListView;
    private ObservableList<Property> rentedUnitsObservableList;
    @FXML
    private Button closebtn;

    public void initialize() {
        rentedUnitsObservableList = FXCollections.observableArrayList();
        rentedUnitsListView.setItems(rentedUnitsObservableList);
        displayAllVacantUnits();

    }

    private void displayAllVacantUnits() {
        // Retrieve all items from the model and add them to the list
        ArrayList<Property> vacantUnits = (ArrayList<Property>) (ArrayList<?>) propertyServices.findRented();
        rentedUnitsObservableList.addAll(vacantUnits);
        Thread thread = new Thread(()->{
            Platform.runLater(()->{
                rentedUnitsListView = new ListView<>(rentedUnitsObservableList);
                rentedUnitsListView.setCellFactory(param -> new ListCell<Property>() {
                    @Override
                    protected void updateItem(Property item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item.toString());
                    }
                });
            });
        },"Display Rented unit thread");
        App.navigate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rentedUnitsObservableList = FXCollections.observableArrayList();
        rentedUnitsListView.setItems(rentedUnitsObservableList);
        displayAllVacantUnits();
    }

    //    @FXML
//    private void handleAddNewProperty(ActionEvent ae) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/AddProperty.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Add property View");
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
