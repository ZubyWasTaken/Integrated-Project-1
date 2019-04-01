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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffHomeController implements Initializable {

    @FXML
    private Label lblSpeciality;

    @FXML
    private TextArea appointmentList;

    @FXML
    private TextField txtAppID;

    @FXML
    private TextField txtAppType;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtStatus;

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
    public void nextApp(ActionEvent event) throws IOException {

        try {

            String[] appValues = Staff.singleApp.get(Staff.counter).split(",");

            int maxSize = Staff.singleApp.size();
            System.out.println(maxSize);
            Staff.counter++;
            if (Staff.counter == maxSize) {
                Staff.counter = 0;
            }

            String appointmentID = appValues[0];
            String appointmentType = appValues[1];
            String userID = appValues[2];
            String Date = appValues[3];
            String Time = appValues[4];
            String Status = appValues[5];

            System.out.println(appointmentID);
            System.out.println(appointmentType);
            System.out.println(userID);
            System.out.println(Date);
            System.out.println(Time);
            System.out.println(Status);

            txtAppID.setText(appValues[0]);
            txtAppType.setText(appointmentType);
            txtUserID.setText(userID);
            txtDate.setText(Date);
            txtTime.setText(Time);

            if (Status.contains(" ")) {
                txtStatus.setText("In Progress");
            } else {
                txtStatus.setText(Status);
            }

        } catch (Exception e) {
            appointmentList.setText("No appointments to show.");
        }

    }

    @FXML
    public void updateAppointment(ActionEvent event) throws IOException {

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
            appointmentList.setText("No appointments to show.");
        } catch (IOException ex) {
            appointmentList.setText("No appointments to show.");
        }

    }

}
