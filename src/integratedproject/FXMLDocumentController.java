/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Zuby
 */
public class FXMLDocumentController implements Initializable {

    public void writeToFile(String forename, String surname, String userID, String password, LocalDate dateOfBirth) throws IOException {

        try (PrintWriter writer = new PrintWriter("UserData/" + userID + ".txt", "UTF-8")) {
            writer.println(userID);
            writer.println(forename);
            writer.println(surname);
            writer.println(password);
            writer.println(dateOfBirth);
            writer.close();
        }

    }

    @FXML
    private TextField forename1;

    @FXML
    private TextField surname2;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private DatePicker DoB;

    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException {
        String forename = forename1.getText();
        String surname = surname2.getText();
        String passwrd = password.getText();
        String confirmPasswrd = confirmPassword.getText();
        LocalDate dateOfBirth = DoB.getValue();

        boolean match = passwrd.equals(confirmPasswrd);
        if (match == true) {
            System.out.println("Your passwords match.");
        } else {
            System.out.println("Your passwords do not match.");;
        }
        if (passwrd.length() == 0) {
            System.out.println("Fill in password");
        }
        if (forename.length() == 0) {
            System.out.println("Fill in forename");
        }
        if (surname.length() == 0) {
            System.out.println("Fill in surname");
        }
        if (dateOfBirth == null) {
            System.out.println("No valid date");
        }
        if (passwrd.length() < 8 || passwrd.length() > 18) {
            System.out.println("Password must be 8 to 18 characters in length.");

        }

        int max = 1000;
        int min = 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        String userID = forename + surname + randomNum;

        writeToFile(forename, surname, userID, passwrd, dateOfBirth);

    }


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
    }

    @FXML
    public void homeButtonAction(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
