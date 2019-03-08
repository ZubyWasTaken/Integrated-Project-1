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
public class HomeDocumentController implements Initializable {

    /* This button finds the 'User Register' button on the Home.fxml, and once it
     has found the button it calls the button action and the following code
     changes the scene and opens the Register.fxml */
    @FXML
    private void regButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Register.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Register");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    /* This button finds the 'User Login' button on the Home.fxml, and once it
     has found the button it calls the button action and the following code
     changes the scene and opens the UserLogin.fxml */
    @FXML
    private void userLoginButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserLogin.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("User Login");
        reg.setScene(scene);
        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    /* This button finds the 'Exit' button on the Home.fxml, and once it
     has found the button it calls the button action and the following code
     exits the program.*/
    @FXML
    private void exitButtonAction(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
