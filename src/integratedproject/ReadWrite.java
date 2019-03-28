/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
            Scanner user = new Scanner(new File("src/UserData/" + username + ".txt"));
            user.close();
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

        Scanner input = new Scanner(new File("src/UserData/" + username + ".txt"));
        int counter = 0;
        while (input.hasNextLine() && counter < 2) {
            UserPass.add((input.nextLine()));
            counter++;
        }
        input.close();
        return UserPass;

    }

    public static List<String> userForenameSurname(String userID) throws FileNotFoundException {
        List<String> UserPass = new ArrayList<>();

        Scanner input = new Scanner(new File("src/UserData/" + userID + ".txt"));
        int counter = 0;
        while (input.hasNextLine() && counter < 4) {
            UserPass.add((input.nextLine()));
            counter++;
        }
        input.close();
        return UserPass;

    }

    /*
     This methoid below writes all the user data to a text file, with the userID
     as the file name.
     */
    public static void writeToFile(String forename, String surname, String userID, String password, LocalDate dateOfBirth) throws IOException {

        try (PrintWriter writer = new PrintWriter("src/UserData/" + userID + ".txt", "UTF-8")) {
            writer.println(userID);
            writer.println(password);
            writer.println(forename);
            writer.println(surname);
            writer.println(dateOfBirth);
            writer.close();
        }

    }

    public static void createAppointmentFile(String appointmentID, String comboSelection, String userID, LocalDate appointmentDate, String appointmentTime) throws IOException {

        FileWriter fw = new FileWriter("src/UserAppointments/" + userID + ".txt", true);

        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(appointmentID + "," + comboSelection + "," + userID + "," + appointmentDate + "," + appointmentTime + "," + " ");

        pw.flush();
        pw.close();

    }

    public static List<String> readAppointment(String username) throws FileNotFoundException {
        List<String> appointment = new ArrayList<>(5);

        Scanner input = new Scanner(new File("src/UserAppointments/" + username + ".txt"));
        int counter = 0;
        while (input.hasNextLine() && counter < 4) {
            appointment.add((input.nextLine()));
            counter++;
        }
        input.close();
        return appointment;

    }

    public static boolean doesAppointmentExist(String username) {
        try {
            Scanner user = new Scanner(new File("src/UserAppointments/" + username + ".txt"));
            user.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }

    public static Boolean deleteAppointment(String username) throws IOException {

        File file = new File("src/UserAppointments/" + username + ".txt");

        try {
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Array[] getDetails(List<String> currentSelections) {
        
        
        
        return null;

    }

    public static List<List<String>> returnAppointment() throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/UserAppointments/" + Patient.userID + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public static List<String> displayAppointment() throws IOException {
        List<List<String>> records = returnAppointment();
        int counter = Patient.counter;

        try {
            return records.get(counter);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("testtest");
        }
        return null;
    }

}
