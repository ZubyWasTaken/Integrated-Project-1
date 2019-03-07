/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
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

    /*
     This methoid below writes all the user data to a text file, with the userID
     as the file name.
     */
    public void writeToFile(String forename, String surname, String userID, String password, LocalDate dateOfBirth) throws IOException {

        try (PrintWriter writer = new PrintWriter("UserData/" + userID + ".txt", "UTF-8")) {
            writer.println(userID);
            writer.println(password);
            writer.println(forename);
            writer.println(surname);
            writer.println(dateOfBirth);
            writer.close();
        }

    }

    /*
     These variables below link the FXML labels and text fields
     with the code, allowing the code to manipulate them.
     */
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
    private Label forenameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField usernameUser;

    @FXML
    private TextField passwordUser;

    @FXML
    private Label usernameUserLabel;

    @FXML
    private Label passwordUserLabel;

    /*
     This code is executed when the 'Register' button is clicked in
     Register.fxml
     */
    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException {

        /*
         Setting the data in the textfields and date picker to variables
         to be used by the code.
         */
        String forename = forename1.getText();
        String surname = surname2.getText();
        String passwrd = password.getText();
        String confirmPasswrd = confirmPassword.getText();
        LocalDate dateOfBirth = DoB.getValue();

        /*
         This is to affix a random number to the user's forename and surname
         to create a unique userID.
         */
        int max = 1000;
        int min = 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        String userID = forename + surname + randomNum;

        /*
         Checks if passwords are equal.
         */
        boolean match = passwrd.equals(confirmPasswrd);
        if (match == true) {
            System.out.println("Your passwords match.");

        } else {
            System.out.println("Your passwords do not match.");
            confirmPasswordLabel.setText("Your passwords do not match.");
        }

        /*
         This section checks if the textfields and datepicker are empty
         and if they are empty to change the text in the labels to prompt the
         user to enter the data.
         */
        if (forename.length() == 0) {
            System.out.println("Fill in forename");
            forenameLabel.setText("Please enter your forename.");
        }
        if (surname.length() == 0) {
            System.out.println("Fill in surname");
            surnameLabel.setText("Please enter your surname.");
        }
        if (passwrd.length() == 0) {
            System.out.println("Fill in password");
            passwordLabel.setText("Please enter your password.");
        }
        if (confirmPasswrd.length() == 0) {
            System.out.println("Fill in password");
            confirmPasswordLabel.setText("Please confirm your password.");
        }
        if (dateOfBirth == null) {
            System.out.println("No valid date");
            dateLabel.setText("Please confirm your Date of Birth.");
        }

        /*
         If the password entered is not between 8 and 18 chars it prompts
         the user that their password must be between those two limits.
         */
        if ((passwrd.length() < 8 || passwrd.length() > 18)) {

            passwordLabel.setText("Password must be 8 to 18 characters.");
        }

        /*
         If the forename and username fields and datepicker are not empty then it clears the 
         labels.
         */
        if (forename.length() != 0) {
            forenameLabel.setText("");
        }
        if (surname.length() != 0) {
            surnameLabel.setText("");
        }
        if (dateOfBirth != null) {
            dateLabel.setText("");
        }

        /*
         If the password is between the limit then it clears the label telling
         you that your password is not within limit.
         */
        if ((passwrd.length() > 7 && passwrd.length() < 19)) {
            passwordLabel.setText("");
        }

        /*
         Checks if your password matches, and if the field has any data in it,
         if so then it clears the label that tells you to confirm your password.
         */
        if (confirmPasswrd.length() != 0 && (match == true)) {
            confirmPasswordLabel.setText("");
        }

        /*
        If the forename, surname, datepicker, and both password fields are NOT
        empty, AND if the password lengths are within limit, THEN it clears the
        remaining labels, and calls passes the variables into the method to
        write the data to an individual text file.
         */
        if (forename.length() != 0 && surname.length() != 0 && dateOfBirth != null && passwrd.length() != 0 && confirmPasswrd.length() != 0) {
            if ((passwrd.length() > 7 && passwrd.length() < 19)) {
                forenameLabel.setText("");
                surnameLabel.setText("");
                dateLabel.setText("");

                writeToFile(forename, surname, userID, passwrd, dateOfBirth);

                // Closes that window
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            }
        }
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
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    /*
    When the Home button in Register.fxml is clicked, it closes the window,
    and re-opens Home.fxml
     */
    @FXML
    public void homeButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Home.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Register");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    public void userLoginButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserLogin.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Register");
        reg.setScene(scene);
        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public ArrayList readTextFile(String username) throws FileNotFoundException {
        ArrayList<String> UserPass = new ArrayList<>();
        Scanner input = new Scanner(new File("UserData/" + username + ".txt"));
        int counter = 0;
        while (input.hasNextLine() && counter < 2) {
            UserPass.add((input.nextLine()));
            counter++;
        }
        return UserPass;
    }

    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        String username = usernameUser.getText();
        String passwrd = passwordUser.getText();
        
        ArrayList UserPass = readTextFile(username);
        System.out.println(UserPass);

    }

    @FXML
    public void exitButtonAction(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
