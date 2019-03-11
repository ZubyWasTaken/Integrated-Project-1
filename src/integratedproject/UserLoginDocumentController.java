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
public class UserLoginDocumentController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
    @FXML
    private TextField usernameUser;

    @FXML
    private PasswordField passwordUser;

    @FXML
    private Label usernameUserLabel;

    @FXML
    private Label passwordUserLabel;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Button Methods">
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

    /*
     This code is executed when the 'Register' button is clicked in
     Register.fxml
     */
    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        /*
         Setting the data in the textfields and date picker to variables
         to be used by the code.
         */
        String username = usernameUser.getText();
        String passwrd = passwordUser.getText();

        usernameUserLabel.setText("");
        passwordUserLabel.setText("");

        /*
         Checks if the username and password fields are empty, and if so it
         tells you to enter a username and/or a password.
         */
        if (username.length() == 0) {
            usernameUserLabel.setText("Please enter a username.");
        }
        if (passwrd.length() == 0) {
            passwordUserLabel.setText("Please enter a password.");
        }

        /*
         If the username field is not empty, it calls the doesUsernameExist
         in ReadWrite class to see if the username exists, if it does
         it calls readTextFile in the same class and passes in the userID to
         return the userID and password in an array.
        
         If the username does exist then it matches the variable passwrd, which
         is the user's inputted password, against the 2nd position in the array
         that got passed back with that specific userID's password and checks
         if they match. If it matches it goes to UserMenu.fxml, if not it tells
         you that the Username/Password is incorrect.
        
         If the username is not found
         it prompts you that the username does not exist.
         */
        if (username.length() != 0) {
            if (ReadWrite.doesUsernameExist(username) == true) {
                System.out.println("Username Found");
                List<String> testArray = ReadWrite.readTextFile(username);

                if (passwrd.equals(testArray.get(1))) {
                    System.out.println("Password matches username");
                }
                if (!passwrd.equals(testArray.get(1))) {
                    System.out.println("Password does not match!");
                    usernameUserLabel.setText("Username/Password is incorrect");
                    passwordUserLabel.setText("Username/Password is incorrect");
                }
            } else {
                System.out.println("Username Not Found");
                usernameUserLabel.setText("Username does not exist.");
            }
        }
    }
//</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
