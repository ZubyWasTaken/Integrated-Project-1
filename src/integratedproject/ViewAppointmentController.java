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

    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
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

    @FXML
    public void nextAppointment(ActionEvent event) throws IOException {
        boolean found = ReadWrite.doesAppointmentExist(Patient.userID);
        try {
            if (found) {
                lblDisplay.setText("");
                List<List<String>> appointments = ReadWrite.returnAppointment();
                int maxCounter = appointments.size();
                try {
//                    Patient.counter = 0;
                    Patient.counter++;
                    if (Patient.counter == maxCounter) {
                        Patient.counter = 0;
                    }
                    List<String> currentSelection = ReadWrite.displayAppointment();

                    String appointmentID = currentSelection.get(0);
                    String appointmentType = currentSelection.get(1);
                    String userID = currentSelection.get(2);
                    String date = currentSelection.get(3);
                    String timeAppointment = currentSelection.get(4);
                    String status = currentSelection.get(5);

                    if (status.contains(" ")) {
                        lblStatus.setText("Appointment not done.");
                    } else {
                        lblStatus.setText(status);
                    }

                    List<String> tempArray1 = ReadWrite.userForenameSurname(Patient.userID);

                    String forename = tempArray1.get(2);
                    String surname = tempArray1.get(3);

                    lblForename.setText(forename);
                    lblSurname.setText(surname);

                    lblUserID.setText(userID);
                    lblAppointmentID.setText(appointmentID);
                    lblAppointment.setText(appointmentType);
                    lblTime.setText(timeAppointment);
                    lblDate.setText(date);

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("error");
                }
            } else {
                lblDisplay.setText("No appointments exist for this user.");
            }
        } catch (IndexOutOfBoundsException e) {
            lblDisplay.setText("No appointments exist for this user.");
        }
    }

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
    public void cancelAppointment(ActionEvent event) throws IOException, FileNotFoundException {
        try{
        List<String> currentSelection = ReadWrite.displayAppointment();

        String listString = String.join(",", currentSelection);
        System.out.println(listString);


        boolean found = ReadWrite.doesAppointmentExist(Patient.userID);

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
            boolean deleted = ReadWrite.appointmentDelete(listString);
            if (deleted) {
                lblUserID.setText("");
                lblAppointment.setText("");
                lblForename.setText("");
                lblSurname.setText("");
                lblDate.setText("");
                lblAppointmentID.setText("");
                lblTime.setText("");
                lblStatus.setText("");

                lblDisplay.setText("Appointment successfully cancelled.");
                btnCancel.setDisable(true);
            } else {
                lblDisplay.setText("Appointment failed to cancel.");
            }
        } else {
            lblDisplay.setText("You have no appointment to cancel.");
        }

        }catch(IndexOutOfBoundsException e){
            lblDisplay.setText("You have no appointment to cancel.");
        }catch(FileNotFoundException e){
            lblDisplay.setText("You have no appointment to cancel.");
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
