package integratedproject;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class UpdateAppointmentController implements Initializable {

    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
    @FXML
    private TextField txtAppID;

    @FXML
    private Label lblError;

    @FXML
    private ComboBox statusType;

    @FXML
    public void exitButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/StaffHome.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void updateApp(ActionEvent event) throws IOException {
        try {
            String statusSelection = statusType.getSelectionModel().getSelectedItem().toString();

            String ID = txtAppID.getText();

            List<String> allAppointmentID = new ArrayList<>();
            List<String> allUserID = new ArrayList<>();
            int position = 0;
            for (String f : Staff.singleApp) {
                String appID = f.split(",")[0];
                String userID = f.split(",")[2];
                allUserID.add(userID);
                allAppointmentID.add(appID);
            }

            boolean idMatch = false;
            String selectedID = "";

            int counter = 0;
            for (String s : allAppointmentID) {
                counter++;
                if (s.equals(ID)) {
                    position = counter;
                    idMatch = true;
                    selectedID = s;
                }
            }

            String oldAppointment = "";
            boolean appFound = false;
            for (String s : Staff.singleApp) {
                if (s.contains(selectedID)) {
                    appFound = true;
                    oldAppointment = s;
                }
            }

            String idToDelete = allUserID.get(position - 1);
            String newAppointmentString = oldAppointment + statusSelection;
            String appID = newAppointmentString.split(",")[0];
            String appType = newAppointmentString.split(",")[1];
            String userID = newAppointmentString.split(",")[2];
            String Date = newAppointmentString.split(",")[3];
            String Time = newAppointmentString.split(",")[4];
            String Status = newAppointmentString.split(",")[5];

            boolean deleted = StaffReadWrite.appointmentDelete(oldAppointment, userID);
            if (deleted) {
                StaffReadWrite.createAppointmentFile(appID, appType, userID, Date, Time, Status);
                System.out.println("Written.");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            lblError.setText("Cannot update appointment.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        statusType.getItems().clear();
        statusType.getItems().addAll("Pending", "In-progress", "Not-Complete", "Complete");
        statusType.getSelectionModel().select("Choose status");

    }
}
