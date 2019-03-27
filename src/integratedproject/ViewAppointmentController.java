package integratedproject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
    private Label lblForename;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDisplay;

    @FXML
    private Button btnCancel;

    @FXML
    public void viewAppointment(ActionEvent event) throws IOException, ParseException {
        String userID = Patient.userID;
        System.out.println(Patient.userID);

        if (ReadWrite.doesAppointmentExist(userID) == true) {

            List<String> tempArray = ReadWrite.readAppointment(Patient.userID);
            String appointmentType = tempArray.get(1);       
            String date = tempArray.get(2);
            
            List<String> tempArray1 = ReadWrite.userForenameSurname(Patient.userID);
            
            String forename = tempArray1.get(2);
            String surname = tempArray1.get(3);
            
            
            lblForename.setText(forename);
            lblSurname.setText(surname);
            
            lblUserID.setText(userID);
            lblAppointment.setText(appointmentType);
            lblDate.setText(date);
        } else {
            lblDisplay.setText("You have no appointments.");
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
    public void cancelAppointment(ActionEvent event) throws IOException {
        String userID = Patient.userID;

        if (ReadWrite.doesAppointmentExist(userID) == true) {
            lblUserID.setText("");
            lblAppointment.setText("");
            lblForename.setText("");
            lblSurname.setText("");
            lblDate.setText("");

            boolean deleted = ReadWrite.deleteAppointment(userID);
            if (deleted) {
                lblUserID.setText("");
                lblAppointment.setText("");
                lblForename.setText("");
                lblSurname.setText("");
                lblDate.setText("");
                
                lblDisplay.setText("Appointment successfully cancelled.");
                btnCancel.setDisable(true);
            } else{
                lblDisplay.setText("Appointment failed to cancel.");
            }

        } else {
            lblDisplay.setText("You have no appointment to cancel.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

    }

}
