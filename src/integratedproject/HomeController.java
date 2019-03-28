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
     Method to close the current window, with the mouseclick passed in as an
     argument .
     */
    private void closeWindow(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    // Method to load the Register.fxml file.
    private void loadRegister() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Register.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Register");
        reg.setScene(scene);
        reg.show();
    }

    // Method to load UserLogin.fxml file.
    private void loadUserLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserLogin.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("User Login");
        reg.setScene(scene);
        reg.show();
    }
    
    private void loadStaffLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/StaffLogin.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Staff Login");
        reg.setScene(scene);
        reg.show();
    }

    /* Finds 'User Register' button and calls loadRegister() method and then
     calls closeWindow() method, and passes the event (mouseclick) into it.*/
    @FXML
    private void regButtonAction(ActionEvent event) throws IOException {
        loadRegister();
        closeWindow(event);
    }

    /* Finds 'User Login' button and calls loadUserLogin() method and then
     calls closeWindow() method, and passes the event (mouseclick) into it.*/
    @FXML
    private void userLoginButton(ActionEvent event) throws IOException {
        loadUserLogin();
        closeWindow(event);

    }

    @FXML
    private void staffLoginButton(ActionEvent event) throws IOException {
        loadStaffLogin();
        closeWindow(event);

    }
    
    /* Finds 'Exit' button andcalls closeWindow() method, and passes the
     event (mouseclick) into it.*/
    @FXML
    private void exitButtonAction(ActionEvent event) throws IOException {
//        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
