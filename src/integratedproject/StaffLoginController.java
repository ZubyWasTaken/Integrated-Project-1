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
public class StaffLoginController implements Initializable {

    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
    @FXML
    private TextField usernameStaff;

    @FXML
    private PasswordField passwordStaff;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblPassword;

    Patient patient = new Patient();

    /*
     When the Home button in Register.fxml is clicked, it closes the window,
     and re-opens Home.fxml
     */
    @FXML
    public void homeButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Home.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

  
    @FXML
    public void staffLogin(ActionEvent event) throws IOException {
        
        String username = usernameStaff.getText();
        String passwrd = passwordStaff.getText();

        lblUsername.setText("");
        lblPassword.setText("");

        if (username.length() == 0) {
            lblUsername.setText("Please enter a username.");
        }
        if (passwrd.length() == 0) {
            lblPassword.setText("Please enter a password.");
        }

        if (username.length() != 0) {
            if (StaffReadWrite.doesUsernameExist(username) == true) {              
                Patient.userID = username;
                List<String> testArray = StaffReadWrite.readStaffData(username);
                
                

                if (passwrd.equals(testArray.get(1))) {
                    Staff.staffID = testArray.get(0);
                    Staff.speciality = testArray.get(2);
                    Parent root = FXMLLoader.load(getClass().getResource("FXML/StaffHome.fxml"));

                    Scene scene = new Scene(root);
                    Stage reg = new Stage(StageStyle.DECORATED);
                    reg.setTitle("User Home");
                    reg.setScene(scene);

                    reg.show();
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                }
                if (!passwrd.equals(testArray.get(1))) {
                    lblUsername.setText("Username/Password is incorrect");
                    lblPassword.setText("Username/Password is incorrect");
                }
            } else {
                lblUsername.setText("Username does not exist.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
