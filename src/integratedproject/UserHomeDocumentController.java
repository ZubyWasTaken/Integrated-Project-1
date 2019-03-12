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
public class UserHomeDocumentController implements Initializable {

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
    public void logoutButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Home.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    public void createAppointment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/CreateAppointment.fxml"));
        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Create Appointment");
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
        // TODO
    }

}
