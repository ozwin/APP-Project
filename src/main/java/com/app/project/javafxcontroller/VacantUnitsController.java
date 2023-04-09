package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Property;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.PropertyServices;
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

public class VacantUnitsController implements Initializable{
    private PropertyServices propertyServices=new PropertyServices(PropertiesRepository.getInstance());
    @FXML private ListView<Property> vacantUnitsListView;
    private ObservableList<Property> vacantUnitsObservableList;
    @FXML
    private Button closebtn;
    public void initialize() {
        vacantUnitsObservableList = FXCollections.observableArrayList();
        vacantUnitsListView.setItems(vacantUnitsObservableList);
        displayAllVacantUnits();

    }
    private void displayAllVacantUnits() {
        // Retrieve all items from the model and add them to the list
        ArrayList<Property> vacantUnits = (ArrayList<Property>)(ArrayList<?>)propertyServices.findVacant();
        vacantUnitsObservableList.addAll(vacantUnits);
        vacantUnitsListView=new ListView<>(vacantUnitsObservableList);
        vacantUnitsListView.setCellFactory(param -> new ListCell<Property>() {
            @Override
            protected void updateItem(Property item, boolean empty) {
                super.updateItem(item, empty);
                setText(item.toString());
            }
        });
        App.navigate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vacantUnitsObservableList = FXCollections.observableArrayList();
        vacantUnitsListView.setItems(vacantUnitsObservableList);
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
private void back(){
    Stage stage = (Stage) closebtn.getScene().getWindow();
    stage.close();
    App.navigate();
}
}
