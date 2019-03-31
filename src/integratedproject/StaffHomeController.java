package integratedproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffHomeController implements Initializable {

    @FXML
    private Label lblSpeciality;

    @FXML
    private TextField appointmentList;

    @FXML
    public void logoutButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Home.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    public void updateAppointment(ActionEvent event) throws IOException {

    }

    @FXML
    public void cancelAppointment(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

        lblSpeciality.setText(Staff.speciality + " Appointments");
        
        try {
            List<List<String>> allAppointments = StaffReadWrite.readAllFiles();
            System.out.println(allAppointments);
             int amountOfAppointments = allAppointments.size();
             
             System.out.println(amountOfAppointments);
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("This error is here");
        } catch (IOException ex) {
            System.out.println("This other error is here");
        }


    }

}
