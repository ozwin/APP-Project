package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.entity.Lease;
import com.app.project.entity.Property;
import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import com.app.project.util.Helper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RentAUnitController implements Initializable {
    private static UUID ppid;
    @FXML
    private Button closebutton;
    @FXML
    private Button yesbtn;
    @FXML
    private Button nobtn;
    @FXML
    private Button submitbtn;
    private String userID;
    @FXML
    private TextField duration;
    @FXML
    private Label userLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label rentLabel;
    @FXML
    private ComboBox<Item> comboBox = new ComboBox<>();
    @FXML
    private ComboBox<Item> tenantCombobox = new ComboBox<>();
    private ObservableList<String> propertyObservableList;
    @FXML
    private TextField monthlyRent;
    private PropertyServices propertyServices;
    private TenantServices tenantServices;
    private LeaseServices leaseServices;
    private String propertyID;

    public static UUID getProperty() {
        return ppid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        duration.setText("0");
        monthlyRent.setText("0");
        ppid = null;
        propertyObservableList = FXCollections.observableArrayList();
        propertyServices = new PropertyServices(PropertiesRepository.getInstance());
        tenantServices = new TenantServices(TenantRepository.getInstance(), propertyServices);
        leaseServices = new LeaseServices();
        Helper.setNumericInputFilter(duration);
        Helper.setNumericInputFilter(monthlyRent);
        List<Item> properties = ((List<Property>) (List<?>) propertyServices.findVacant()).stream().map(x -> new Item(x.getAddress().toString(), x.getPropertyId().toString())).toList();
        comboBox.getItems().addAll(properties);
        comboBox.setCellFactory(listView -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDisplayText());
                }
            }
        });
        tenantCombobox.setCellFactory(listView -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDisplayText());
                }
            }
        });

        // Set the custom item factory for the ComboBox
        comboBox.setButtonCell(new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDisplayText());
                }
            }
        });
        tenantCombobox.setButtonCell(new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDisplayText());
                }
            }
        });

        // Add a listener to the selected item property
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                propertyID = newValue.getValue();
                setUserListForProperty(propertyID);
            }
        });

        tenantCombobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                userID = newValue.getValue();

            }
        });
        if(properties.size()>0){
            comboBox.setValue(properties.get(0));
            propertyID = properties.get(0).value;
        }

    }

    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }

    public void next() throws Exception {
//        propertyServices.moveTenantToProperty();
        nobtn.setVisible(false);
        submitbtn.setVisible(true);
        duration.setVisible(true);
        monthlyRent.setVisible(true);
        userLabel.setVisible(true);
        rentLabel.setVisible(true);
        durationLabel.setVisible(true);
        tenantCombobox.setVisible(true);
        if (propertyID.trim().length() > 0)
            setUserListForProperty(propertyID);
    }

    public void moveTenants() throws Exception {
        UUID propertyId = UUID.fromString(propertyID);
        ppid = propertyId;
        UUID userUUID = UUID.fromString(userID);
        propertyServices.moveTenantToProperty(propertyId, userUUID);
        Tenant tenant = tenantServices.getTenant(userUUID);
        Lease lease = new Lease(new ArrayList<UUID>() {{
            add(userUUID);
        }}, propertyId);
        lease.setLeaseDuration(Integer.parseInt(duration.getText().trim()));
        lease.setAgreedMonthlyRent(Integer.parseInt(monthlyRent.getText().trim()));
        lease.setTenantNames(new ArrayList<String>() {{
            add(tenant.fullName());
        }});
        leaseServices.addLease(lease);
        App.navigate();
    }

    public void addTenant() throws IOException {
        UUID pid = UUID.fromString(propertyID);
        ppid = pid;
        Stage stage1 = (Stage) nobtn.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddTenantToProperty.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add a Tenant");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    static class Item {
        private String displayText;
        private String value;

        public Item(String displayText, String value) {
            this.displayText = displayText;
            this.value = value;
        }

        public String getDisplayText() {
            return displayText;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return displayText;
        }
    }

    private void setUserListForProperty(String propertyID) {
        Thread thread = new Thread(() -> {
            List<Tenant> waitingListUsers = tenantServices.findMany(((Property) propertyServices.getByKey(UUID.fromString(propertyID))).getWaitingList());
            Platform.runLater(() ->{
                List<Item> userList = waitingListUsers.stream().map(x -> new Item(x.fullName(), x.getID().toString())).toList();
                tenantCombobox.getItems().clear();
                tenantCombobox.getItems().addAll(userList);
                if (userList.size() > 0) userID = userList.get(0).value;
            });
        });
        thread.start();
    }



}
