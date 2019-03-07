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
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Zuby
 */
public class ReadWrite {

    public static List<String> readTextFile(String username) throws FileNotFoundException {
        List<String> UserPass = new ArrayList<>();

        Scanner input = new Scanner(new File("UserData/" + username + ".txt"));
        int counter = 0;
        while (input.hasNextLine() && counter < 2) {
            UserPass.add((input.nextLine()));
            counter++;
        }
        return UserPass;

    }

    public static boolean doesUsernameExist(String username) throws FileNotFoundException {
        try {
            Scanner user = new Scanner(new File("UserData/" + username + ".txt"));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

      
    }

    /*
     This methoid below writes all the user data to a text file, with the userID
     as the file name.
     */
    public static void writeToFile(String forename, String surname, String userID, String password, LocalDate dateOfBirth) throws IOException {

        try (PrintWriter writer = new PrintWriter("UserData/" + userID + ".txt", "UTF-8")) {
            writer.println(userID);
            writer.println(password);
            writer.println(forename);
            writer.println(surname);
            writer.println(dateOfBirth);
            writer.close();
        }

    }

}
