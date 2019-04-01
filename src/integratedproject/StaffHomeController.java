package integratedproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffHomeController implements Initializable {

    @FXML
    private Label lblSpeciality;

    @FXML
    private TextArea appointmentList;
    
    @FXML
    private Label lblAppoimtmentID;
    
    @FXML
    private Label lblAppointmentType;
    
    @FXML
    private Label lblUserID;
    
    @FXML
    private Label lblDate;
    
    @FXML
    private Label lblTime;
    
    @FXML
    private Label lblStatus;

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
    public void initialize(URL url, ResourceBundle rb) {

        lblSpeciality.setText(Staff.speciality + " Appointments");

        try {
            List<List<String>> allAppointments = StaffReadWrite.readAllFiles();
            int counter = 0;
            Staff.singleApp = new ArrayList<>();

            for (int i = 0; i < allAppointments.size(); i++) {
                List<String> tempArray = StaffReadWrite.singularAppointment(counter);
                if (Staff.speciality.equals(tempArray.get(1))) {

                    String apps = String.join(",", tempArray);
                    Staff.singleApp.add(apps);
                }

                counter++;
            }
            String newLine = System.getProperty("line.separator");
            for (String f : Staff.singleApp) {
                appointmentList.appendText(f + newLine);
            }

        } catch (FileNotFoundException ex) {
            lblSpeciality.setText("This error should not happen.");
        } catch (IOException ex) {
            System.out.println("This error should also not happen.");
        }

    }

}
