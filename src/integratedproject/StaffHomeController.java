package integratedproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffHomeController implements Initializable {

    @FXML
    private Label lblSpeciality;

    @FXML
    private TextField appointmentList;

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
    public void updateAppointment(ActionEvent event) throws IOException {

    }

    @FXML
    public void cancelAppointment(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

        lblSpeciality.setText(Staff.speciality + " Appointments");

        try {
            List<String> list = StaffReadWrite.readUsernames();
            int listSize = list.size();

            String[] usernames = new String[listSize];

            List<List<String>> appointments;

            List<String> currentAppointment;

            for (int i = 0; i < listSize; i++) {
                usernames[i] = list.get(i);
            }
            for (int x = 0; x < listSize; x++) {
                appointments = ReadWrite.returnAppointmentStaff(usernames[x]);
                System.out.println(appointments);

                for (int y = 0; y < listSize; y++) {
                    currentAppointment = ReadWrite.displayAppointmentStaff(appointments, y);
                    System.out.println(currentAppointment);
                    if (Staff.speciality.equals(currentAppointment.get(1))) {
                        String appointment = currentAppointment.toString();
                        appointmentList.setText(appointment);
                    }
                }
//                
            }

        } catch (FileNotFoundException e) {
            System.out.println("Files not found.");
        } catch (IOException ex) {
            System.out.println("File not found.");
        }

    }

}
