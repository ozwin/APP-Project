package com.app.project.javafxcontroller;

import com.app.project.App;
import com.app.project.service.LeaseServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
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
    private TextField id;
    @FXML
    private TextField rent;
    private LeaseServices leaseServices;
    UUID propertyId;
    double amount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText("");
        rent.setText("");
        leaseServices = new LeaseServices();
    }

    public void submit() throws Exception {
        propertyId = UUID.fromString(id.getText());
        amount = Double.parseDouble(rent.getText());
        leaseServices.recordPayment(propertyId, amount);
    }

    public void cancel() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        App.navigate();
        //navigate to main screen
    }


}
