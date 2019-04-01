package integratedproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class ViewAppointmentController implements Initializable {

    // Variables declared to be used by the code - these are linked to the
    // according element in the FXML
    @FXML
    private Label lblUserID;

    @FXML
    private Label lblAppointment;

    @FXML
    private Label lblAppointmentID;

    @FXML
    private Label lblForename;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDisplay;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnCancel;

    // Cycles through appointments
    @FXML
    public void nextAppointment(ActionEvent event) throws IOException {
        boolean found = ReadWrite.doesAppointmentExist(Patient.userID);
        try {
            if (found) {
                lblDisplay.setText("");
                List<List<String>> appointments = ReadWrite.returnAppointment();

                // used to cycle through appointments
                int maxCounter = appointments.size();
                try {
                    Patient.counter++;
                    if (Patient.counter >= maxCounter) {
                        Patient.counter = 0;
                    }
                    
                    // assigns variables from the data in the current appointment list
                    List<String> currentSelection = appointments.get(Patient.counter);
                    String appointmentID = currentSelection.get(0);
                    String appointmentType = currentSelection.get(1);
                    String userID = currentSelection.get(2);
                    String date = currentSelection.get(3);
                    String timeAppointment = currentSelection.get(4);
                    String status = currentSelection.get(5);
                    
                    // gets users forename and surname
                    List<String> tempArray1 = ReadWrite.userForenameSurname(Patient.userID);

                    String forename = tempArray1.get(2);
                    String surname = tempArray1.get(3);

                    // displays the data onto the labels
                    lblForename.setText(forename);
                    lblSurname.setText(surname);

                    lblUserID.setText(userID);
                    lblAppointmentID.setText(appointmentID);
                    lblAppointment.setText(appointmentType);
                    lblTime.setText(timeAppointment);
                    lblDate.setText(date);

                    // validation
                    if (status.equals(" Pending")) {
                        lblStatus.setText(status);
                    } else if (status.equals(" In-progress")) {
                        lblStatus.setText(status);
                    } else if (status.equals(" Not-Complete")) {
                        lblStatus.setText(status);
                    } else if (status.equals(" Complete")) {
                        lblStatus.setText(status);
                    } else {
                        lblStatus.setText("Not Started.");
                    }

                } catch (ArrayIndexOutOfBoundsException e) {

                }
            } else {
                lblDisplay.setText("No appointments exist for this user.");
            }
        } catch (Exception e) {
            Patient.counter = 0;
        }
    }

    // closes current window and goes to user home window
    @FXML
    public void exitButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("User Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    // cancel appointment button
    @FXML
    public void cancelAppointment(ActionEvent event) throws IOException, FileNotFoundException {
        try {
            // currentSelection is the appointment that is currently displayed
            List<String> currentSelection = ReadWrite.displayAppointment();

            // makes that list to a string
            String listString = String.join(",", currentSelection);
            System.out.println(listString);

            // string is passed in to check if appoiintment infact exists
            boolean found = ReadWrite.doesAppointmentExist(Patient.userID);

            // if it exists it clears labels
            if (found) {
                Patient.counter = 0;
                lblUserID.setText("");
                lblAppointment.setText("");
                lblForename.setText("");
                lblSurname.setText("");
                lblDate.setText("");
                lblAppointmentID.setText("");
                lblTime.setText("");
                lblStatus.setText("");
                
                //string is passed to method to delete it from users appointment files
                boolean deleted = ReadWrite.appointmentDelete(listString);
                if (deleted) {
                    //once deleted it clears labels again
                    lblUserID.setText("");
                    lblAppointment.setText("");
                    lblForename.setText("");
                    lblSurname.setText("");
                    lblDate.setText("");
                    lblAppointmentID.setText("");
                    lblTime.setText("");
                    lblStatus.setText("");

                    // success message, disables cancel button
                    lblDisplay.setText("Appointment successfully cancelled.");
                    btnCancel.setDisable(true);
                } else {
                    lblDisplay.setText("Appointment failed to cancel.");
                }
            } else {
                lblDisplay.setText("You have no appointment to cancel.");
            }

        } catch (IndexOutOfBoundsException e) {
            lblDisplay.setText("You have no appointment to cancel.");
        } catch (FileNotFoundException e) {
            lblDisplay.setText("You have no appointment to cancel.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
