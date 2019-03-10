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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zuby
 */
public class ReadWrite {

    /*
     This method checks if the username exists. How it does this is by checking
     if the UserData directory contains a text file with the userID that has been
     passed into the method. If it doesn't exist, the try/catch block catches the
     error and instead of throwing an error, it returns false. If it has been
     found then it returns true.
     */
    public static boolean doesUsernameExist(String username) throws FileNotFoundException {
        try {
            Scanner user = new Scanner(new File("UserData/" + username + ".txt"));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /*
     This method gets the username passed in, and scans the UserData directory
     to find a matching username in that directory, and if it has been found
     it returns the first two items in the text file and appends it to a
     List<String> and returns that.
     */
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