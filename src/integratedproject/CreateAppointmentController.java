package integratedproject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
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
public class CreateAppointmentController implements Initializable {

    // Variables declared to be used by the code - these are linked to the
    // according element in the FXML
    @FXML
    private TextField userUserID;

    @FXML
    private DatePicker dateAppointment;

    @FXML
    private ComboBox comboBox;

    @FXML
    private ComboBox timeAppointment;

    @FXML
    private Label lblAppointment;

    @FXML
    private Label userIDLabel;

    @FXML
    private Label appointmentLabel;

    @FXML
    private Label comboLabel;

    //Opens User Home and closes current window
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

    // Method to make a random time between 9am and 11:59am
    private String TimeBeforeNoon() {
        Random rand = new Random();
        String appointment;
        int maxHour = 11;
        int minHour = 9;
        int randomHour = rand.nextInt((maxHour - minHour) + 1) + minHour;

        int maxMin = 59;
        int minMin = 1;
        int randomMin = rand.nextInt((maxMin - minMin) + 1) + minMin;

        if (randomMin < 10) {
            String randomMinString = ("0" + Integer.toString(randomMin));
            appointment = (randomHour + ":" + randomMinString + "am");
            return appointment;
        }
        return appointment = (randomHour + ":" + randomMin + "am");
    }

    // Method to make a random time between 1pm and 4:50pm
    private String TimeAfterNoon() {
        Random rand = new Random();
        String appointment;

        int maxHour = 5;
        int minHour = 1;
        int randomHour = rand.nextInt((maxHour - minHour) + 1) + minHour;

        int maxMin = 59;
        int minMin = 1;
        int randomMin = rand.nextInt((maxMin - minMin) + 1) + minMin;

        if (randomMin < 10) {
            String randomMinString = ("0" + Integer.toString(randomMin));
            appointment = (randomHour + ":" + randomMinString + "am");
            return appointment;
        }

        return (randomHour + ":" + randomMin + "pm");
    }

    @FXML
    public void createAppointment(ActionEvent event) throws IOException {

        String comboSelection = "";
        String appointmentID = "";

        // Get user's selection of appointment and create a unique appointment ID
        try {
            comboSelection = comboBox.getSelectionModel().getSelectedItem().toString();

            int max = 1000;
            int min = 1;
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;

            String appointmentPrefix = comboSelection.substring(0, 3);

            appointmentID = appointmentPrefix + randomNum;
        } catch (Exception e) {
            comboLabel.setText("Select type of appointment.");
        }

        String appointmentString = "";

        //Get user's preferred time
        try {
            appointmentString = timeAppointment.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            lblAppointment.setText("Select time of appointment.");
        }

        // If selection is before noon then call that function
        String appointmentTime = "";
        if (appointmentString.equals("Before Noon")) {

            appointmentTime = TimeBeforeNoon();

            // If selection is after noon then call that function
        } else if (appointmentString.equals("After Noon")) {

            appointmentTime = TimeAfterNoon();

        } else if (appointmentString.isEmpty()) {
            lblAppointment.setText("Select appointment time.");
        }

        if (!appointmentString.isEmpty()) {
            lblAppointment.setText("");
        }

        //Validation
        String userID = userUserID.getText();
        LocalDate appointmentDate = dateAppointment.getValue();
        LocalDate dateNow = LocalDate.now();

        if (userID.length() == 0) {
            userIDLabel.setText("Enter user ID.");
        }
        if (appointmentDate == null) {
            appointmentLabel.setText("Pick day for appointment.");
        }

        if (userID.length() != 0) {
            if (!Patient.userID.equals(userID)) {
                userIDLabel.setText("Incorrect user ID.");
            } else {
                userIDLabel.setText("");
            }
        }

        if (comboSelection.length() > 0) {
            comboLabel.setText("");
        }

        if (appointmentDate != null) {
            appointmentLabel.setText("");
        }

        try {
            if (appointmentDate.compareTo(dateNow) < 0) {
                appointmentLabel.setText("Appointment cannot be in the past.");
            }
        } catch (NullPointerException e) {
            appointmentLabel.setText("Appointment cannot be in the past.");
        }
        if (appointmentDate.compareTo(dateNow) > 0) {
            appointmentLabel.setText("");
        }

        // Creates appointment and opens User Home
        if (appointmentDate != null && userID.length() > 0 && appointmentDate.compareTo(dateNow) > 0 && Patient.userID.equals(userID) && !appointmentTime.isEmpty() && !appointmentTime.equals("-1")) {

            ReadWrite.createAppointmentFile(appointmentID, comboSelection, userID, appointmentDate, appointmentTime);
            Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));

            Scene scene = new Scene(root);
            Stage reg = new Stage(StageStyle.DECORATED);
            reg.setTitle("User Home");
            reg.setScene(scene);

            reg.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Physiotherapy", "Acupuncture", "Sports Massage", "Hairdressing", "Spa");

        timeAppointment.getItems().clear();
        timeAppointment.getItems().addAll("Before Noon", "After Noon");
    }

}
