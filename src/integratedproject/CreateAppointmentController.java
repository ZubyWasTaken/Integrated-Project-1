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

    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
    @FXML
    private TextField userUserID;

    @FXML
    private DatePicker dateAppointment;

    @FXML
    private ComboBox comboBox;

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

        String comboSelection = "";
        try {
            comboSelection = comboBox.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            comboLabel.setText("Select type of appointment.");
        }
        
        String userID = userUserID.getText();
        LocalDate appointmentDate = dateAppointment.getValue();
        LocalDate dateNow = LocalDate.now();

        int max = 1000;
        int min = 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        
        String appointmentPrefix = comboSelection.substring(0, 3);
        
        String appointmentID = appointmentPrefix + randomNum;
        
        System.out.println(appointmentID);
        
        
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

        if (appointmentDate.compareTo(dateNow) > 0) {
            appointmentLabel.setText("Appointment cannot be in the future.");
        }

        if (appointmentDate != null && userID.length() > 0 && appointmentDate.compareTo(dateNow) < 0 && Patient.userID.equals(userID)) {

            ReadWrite.createAppointmentFile(appointmentID ,comboSelection, userID, appointmentDate);     
            Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));

            Scene scene = new Scene(root);
            Stage reg = new Stage(StageStyle.DECORATED);
            reg.setTitle("Home");
            reg.setScene(scene);

            reg.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }

    }

    @FXML
    public void viewAppointment(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Physiotherapy", "Acupuncture", "Sports Massage", "Hairdressing", "Spa");
    }

}
