package integratedproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label lblUserID;

    @FXML
    private Label lblAppointment;

    @FXML
    private Label lblForename;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDisplay;

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnCancel;

    private static String[] ReturnAppointmentsFiles() throws FileNotFoundException {
        File path = new File("src/UserAppointments");
        Collection<String> files = new ArrayList<>();

        if (path.isDirectory()) {
            File[] listFiles = path.listFiles();

            for (File file : listFiles) {
                if (file.isFile()) {
                    files.add(file.getName());
                }
            }
        }

        return files.toArray(new String[]{});

    }

    private static String removeTXT() throws FileNotFoundException {
        String[] files = ReturnAppointmentsFiles();

        String[] newFiles = new String[files.length];
        String toDelete = ".txt";
        for (int i = 0; i < newFiles.length; i++) {
            newFiles[i] = files[i].replace(toDelete, "");
        }

        return Arrays.toString(newFiles);
    }

    private static List<String> returnFile(String[] files) {
        String parentFolderPath = "src/UserAppointments/";
        String fileName = "Hai61.txt";
        File parent = new File(parentFolderPath);
        for (File subFolder : parent.listFiles()) {
            if (subFolder.isDirectory()) {
                File f = new File(subFolder, fileName);
                if (f.exists()) {
                    System.out.println("Found");
                }
            }
        }

        return null;
    }

    @FXML
    public void viewAppointment(ActionEvent event) throws IOException, ParseException {
        String userID = Patient.userID;
        System.out.println(Patient.userID);

//        String[] files = ReturnAppointmentsFiles();
        String files = removeTXT();
        System.out.println(files);

//        if (ReadWrite.doesAppointmentExist(userID)
//                == true) {
//
//            List<String> tempArray = ReadWrite.readAppointment(Patient.userID);
//            String appointmentType = tempArray.get(1);
//            String date = tempArray.get(2);
//            String status = tempArray.get(3);
//
//            if (status.isEmpty()) {
//                lblStatus.setText("Appointment not done.");
//            } else {
//                lblStatus.setText(status);
//            }
//
//            List<String> tempArray1 = ReadWrite.userForenameSurname(Patient.userID);
//
//            String forename = tempArray1.get(2);
//            String surname = tempArray1.get(3);
//
//            lblForename.setText(forename);
//            lblSurname.setText(surname);
//
//            lblUserID.setText(userID);
//            lblAppointment.setText(appointmentType);
//            lblDate.setText(date);
//        } else {
//            lblDisplay.setText("You have no appointments.");
//        }
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
        String userID = Patient.userID;

        if (ReadWrite.doesAppointmentExist(userID) == true) {
            lblUserID.setText("");
            lblAppointment.setText("");
            lblForename.setText("");
            lblSurname.setText("");
            lblDate.setText("");

            boolean deleted = ReadWrite.deleteAppointment(userID);
            if (deleted) {
                lblUserID.setText("");
                lblAppointment.setText("");
                lblForename.setText("");
                lblSurname.setText("");
                lblDate.setText("");

                lblDisplay.setText("Appointment successfully cancelled.");
                btnCancel.setDisable(true);
            } else {
                lblDisplay.setText("Appointment failed to cancel.");
            }

        } else {
            lblDisplay.setText("You have no appointment to cancel.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

    }

}
