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

    //<editor-fold defaultstate="collapsed" desc="Variables">
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
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Button Methods">
    /*
     When the Home button in Register.fxml is clicked, it closes the window,
     and re-opens Home.fxml
     */
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
        String comboSelection = comboBox.getSelectionModel().getSelectedItem().toString();
        String forename = userForename.getText();
        String surname = userSurname.getText();
        String userID = userUserID.getText();
        LocalDate appointmentDate = dateAppointment.getValue();
        LocalDate dateNow = LocalDate.now();

////        if (comboSelection.isEmpty()) {
////            System.out.println("Fill in forename");
////            comboLabel.setText("Please enter your forename.");
////        }
////        if (forename.length() == 0) {
////            System.out.println("Fill in forename");
////            forenameLabel.setText("Please enter your forename.");
////        }
////        if (surname.length() == 0) {
////            System.out.println("Fill in surname");
////            surnameLabel.setText("Please enter your surname.");
////        }
////        if (userID.length() == 0) {
////            System.out.println("Fill in password");
////            userIDLabel.setText("Please enter your password.");
////        }
////        if (appointmentDate == null) {
////            System.out.println("No valid date");
////            appointmentLabel.setText("Please confirm your Date of Birth.");
////        }
//
////        ReadWrite.createAppointmentFile(comboSelection, forename, surname, userID, appointmentDate);
//        

        
            Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));

            Scene scene = new Scene(root);
            Stage reg = new Stage(StageStyle.DECORATED);
            reg.setTitle("Home");
            reg.setScene(scene);

            reg.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        
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
