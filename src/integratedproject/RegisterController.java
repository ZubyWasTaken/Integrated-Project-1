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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class RegisterController implements Initializable {

    // Variables declared to be used by the code - these are linked to the
    // according element in the FXML
    @FXML
    private Label userIDLabel;

    @FXML
    private TextField forename1;

    @FXML
    private TextField surname2;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private DatePicker DoB;

    @FXML
    private Label forenameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button registerButton;

    // Registers the user
    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException {

        // Data gotten from the textfields and stored into variables
        String forename = forename1.getText();
        String surname = surname2.getText();
        String passwrd = password.getText();
        String confirmPasswrd = confirmPassword.getText();
        LocalDate dateOfBirth = DoB.getValue();
        LocalDate date = LocalDate.now();

        // Create a random number between 1 and 1000
        int max = 1000;
        int min = 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        // Checks if passwords match
        boolean match = passwrd.equals(confirmPasswrd);
        if (match == true) {
            passwordLabel.setText("Your passwords match.");

        } else {
            confirmPasswordLabel.setText("Your passwords do not match.");
        }

        // Input validation
        if (forename.length() == 0) {
            forenameLabel.setText("Please enter your forename.");
        }
        if (surname.length() == 0) {
            surnameLabel.setText("Please enter your surname.");
        }
        if (passwrd.length() == 0) {
            passwordLabel.setText("Please enter your password.");
        }
        if (confirmPasswrd.length() == 0) {
            confirmPasswordLabel.setText("Please confirm your password.");
        }
        if (dateOfBirth == null) {
            dateLabel.setText("Please confirm your Date of Birth.");
        }

        if ((passwrd.length() < 8 || passwrd.length() > 18)) {

            passwordLabel.setText("Password must be 8 to 18 characters.");
        }


        if (forename.length() != 0) {
            forenameLabel.setText("");

        }
        if (surname.length() != 0) {
            surnameLabel.setText("");

        }
        if (dateOfBirth != null) {
            dateLabel.setText("");
            if (dateOfBirth.compareTo(date) > 0) {
                dateLabel.setText("Date of birth cannot be in the future.");
            }
        }
        if ((passwrd.length() > 7 && passwrd.length() < 19)) {
            passwordLabel.setText("");
        }

        if (confirmPasswrd.length() != 0 && (match == true)) {
            confirmPasswordLabel.setText("Your passwords match.");
        }

        if (forename.length() > 0 && !forename.matches("[a-zA-Z ,]+")) {
            forenameLabel.setText("Invalid charachter(s).");
        }
        if (surname.length() > 0 && !surname.matches("[a-zA-Z ,]+")) {
            surnameLabel.setText("Invalid charachter(s).");
        }

        // If validation is all correct, registers user and disables register button
        if (forename.length() != 0 && surname.length() != 0 && dateOfBirth != null && passwrd.length() != 0 && confirmPasswrd.length() != 0 && dateOfBirth.compareTo(date) < 0 && forename.matches("[a-zA-Z ,]+") && surname.matches("[a-zA-Z ,]+")) {
            if ((passwrd.length() > 7 && passwrd.length() < 19)) {
                forenameLabel.setText("");
                surnameLabel.setText("");
                dateLabel.setText("");

                String temp1 = forename.substring(0, 2);
                String temp2 = surname.substring(0, 2);
                String userID = temp1 + temp2 + randomNum;

                // Calls writeToFile method in ReadWrite class.
                ReadWrite.writeToFile(forename, surname, userID, passwrd, dateOfBirth);

                // Displays the users ID to them.
                userIDLabel.setText("Your user ID is: " + userID);

                registerButton.setDisable(true);

            }
        }
    }

    /*
     When the Home button in Register.fxml is clicked, it closes the window,
     and re-opens Home.fxml
     */
    @FXML
    public void homeButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Home.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
