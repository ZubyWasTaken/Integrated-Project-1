package integratedproject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class createAppointmentDocumentController implements Initializable {

    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
    @FXML
    private TextField userForename;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userUserID;

    @FXML
    private DatePicker dateAppointment;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Label forenameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label userIDLabel;

    @FXML
    private Label appointmentLabel;

    @FXML
    private Label comboLabel;

    @FXML
    public void exitButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void createAppointment(ActionEvent event) throws IOException {
        Patient patient = new Patient();
        
        String comboSelection = "";
        try {
            comboSelection = comboBox.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            comboLabel.setText("Select type of appointment.");
        }
        String forename = userForename.getText();
        String surname = userSurname.getText();
        String userID = userUserID.getText();
        LocalDate appointmentDate = dateAppointment.getValue();
        LocalDate dateNow = LocalDate.now();

        if (forename.length() == 0) {
            forenameLabel.setText("Enter forename.");
        }
        if (surname.length() == 0) {
            surnameLabel.setText("Enter surname.");
        }
        if (userID.length() == 0) {
            userIDLabel.setText("Enter user ID.");
        }
        if (appointmentDate == null) {
            appointmentLabel.setText("Pick day for appointment.");
        }

        if (forename.length() != 0) {
            forenameLabel.setText("");
        }
        if (surname.length() != 0) {
            surnameLabel.setText("");
        }
        if (userID.length() != 0) {
            userIDLabel.setText("");
        }
        if (comboSelection.length() > 0) {
            comboLabel.setText("");
        }
        if (appointmentDate != null) {
            appointmentLabel.setText("");

        }
        if (appointmentDate.compareTo(dateNow) > 0) {
            appointmentLabel.setText("Appointment cannot be in the future.");
        }

        if (forename.length() > 0 && surname.length() > 0 && appointmentDate != null && userID.length() > 0 && appointmentDate.compareTo(dateNow) < 0) {
//            ReadWrite.createAppointmentFile(comboSelection, forename, surname, userID, appointmentDate);
            System.out.println("pyah");
            
            String patientID = patient.getuserID();
            System.out.println(patientID);

//            Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));
//
//            Scene scene = new Scene(root);
//            Stage reg = new Stage(StageStyle.DECORATED);
//            reg.setTitle("Home");
//            reg.setScene(scene);
//
//            reg.show();
//            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }

//        ReadWrite.createAppointmentFile(comboSelection, forename, surname, userID, appointmentDate);
    }

    @FXML
    public void viewAppointment(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Physiotherapy", "Acupuncture", "Sports Massage", "Hairdressing", "Spa");
//        comboBox.getSelectionModel().select("Physiotherapy");
    }

}
