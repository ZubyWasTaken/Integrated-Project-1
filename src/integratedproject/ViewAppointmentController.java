package integratedproject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
public class ViewAppointmentController implements Initializable {

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

    @FXML
    public void viewAppointment(ActionEvent event) throws IOException, ParseException {

        List<String> tempArray = ReadWrite.readAppointment(Patient.userID);

        String userID = tempArray.get(0);
        String appointmentType = tempArray.get(1);
        String forename = tempArray.get(2);
        String surname = tempArray.get(3);

        String tempdate = tempArray.get(4);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(tempdate);
       

        System.out.println(userID);
        System.out.println(appointmentType);
        System.out.println(forename);
        System.out.println(surname);
        System.out.println(date);

    }

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
    public void cancelAppointment(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

    }

}
