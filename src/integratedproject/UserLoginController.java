package integratedproject;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class UserLoginController implements Initializable {

    // Variables declared to be used by the code - these are linked to the
    // according element in the FXML
    @FXML
    private TextField usernameUser;

    @FXML
    private PasswordField passwordUser;

    @FXML
    private Label usernameUserLabel;

    @FXML
    private Label passwordUserLabel;

    // closes current window and opens home window
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

    // user login button
    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        // assigns the variables gotten from the textfields
        String username = usernameUser.getText();
        String passwrd = passwordUser.getText();

        usernameUserLabel.setText("");
        passwordUserLabel.setText("");

        // validation
        if (username.length() == 0) {
            usernameUserLabel.setText("Please enter a username.");
        }
        if (passwrd.length() == 0) {
            passwordUserLabel.setText("Please enter a password.");
        }

        // checks if username exists, and matches the password entered with
        // the password of that username. if all is good it logs you in by opening
        // user home window
        if (username.length() != 0) {
            if (ReadWrite.doesUsernameExist(username) == true) {
                Patient.userID = username;
                List<String> testArray = ReadWrite.readTextFile(username);

                if (passwrd.equals(testArray.get(1))) {
                    Parent root = FXMLLoader.load(getClass().getResource("FXML/UserHome.fxml"));

                    Scene scene = new Scene(root);
                    Stage reg = new Stage(StageStyle.DECORATED);
                    reg.setTitle("User Home");
                    reg.setScene(scene);

                    reg.show();
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                }
                if (!passwrd.equals(testArray.get(1))) {
                    usernameUserLabel.setText("Username/Password is incorrect");
                    passwordUserLabel.setText("Username/Password is incorrect");
                }
            } else {
                usernameUserLabel.setText("Username does not exist.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
