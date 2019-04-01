package integratedproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class HomeController implements Initializable {

    /*
     Closes current window
     */
    private void closeWindow(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    // Loads user register window
    private void loadRegister() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Register.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Register");
        reg.setScene(scene);
        reg.show();
    }

    // Loads user login window
    private void loadUserLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserLogin.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("User Login");
        reg.setScene(scene);
        reg.show();
    }

    // Loads staff login window
    private void loadStaffLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/StaffLogin.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Staff Login");
        reg.setScene(scene);
        reg.show();
    }

    // To open user register
    @FXML
    private void regButtonAction(ActionEvent event) throws IOException {
        loadRegister();
        closeWindow(event);
    }

   // To open user login
    @FXML
    private void userLoginButton(ActionEvent event) throws IOException {
        loadUserLogin();
        closeWindow(event);

    }

    // To open staff login
    @FXML
    private void staffLoginButton(ActionEvent event) throws IOException {
        loadStaffLogin();
        closeWindow(event);

    }

    // Exits program
    @FXML
    private void exitButtonAction(ActionEvent event) throws IOException {
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
