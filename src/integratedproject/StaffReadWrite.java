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
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zuby
 */
public class StaffReadWrite {

    // Checks if username exists by trying to open the file
    // if it opens returns true
    // if it can't open it will error, and return false
    public static boolean doesUsernameExist(String username) throws FileNotFoundException {
        try {
            Scanner user = new Scanner(new File("src/StaffData/" + username + ".txt"));
            user.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    // Reads the staff data and returns their username, password, and speciality
    public static List<String> readStaffData(String username) throws FileNotFoundException {
        List<String> UserPass = new ArrayList<>();

        Scanner input = new Scanner(new File("src/StaffData/" + username + ".txt"));
        int counter = 0;
        while (input.hasNextLine() && counter < 3) {
            UserPass.add((input.nextLine()));
            counter++;
        }
        input.close();
        return UserPass;

    }

    // Reads in all appointments and adds it to a list inside a list
    // and returns that.
    public static List<List<String>> readAllFiles() throws FileNotFoundException, IOException {
        File directory = new File("src/UserAppointments");
        File files[] = directory.listFiles();
        System.out.println();
        int linenumber = 0;

        for (File f : files) {

            FileReader fr = new FileReader(f);
            LineNumberReader lnr = new LineNumberReader(fr);
            while (lnr.readLine() != null) {
                linenumber++;
            }
            lnr.close();
        }
        List<List<String>> records = new ArrayList<>();
        for (File file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            br.close();
        }
        return records;
    }

    // returns a single list of appointments from the big list of lists
    // returns the position you want via counter variable
    public static List<String> singularAppointment(int counter) throws IOException {
        List<List<String>> records = readAllFiles();

        try {
            return records.get(counter);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("testtest");
        }
        return null;
    }

    // creates a new appointment inside existing user's appointmnets
    // this method adds the status.
    public static void createAppointmentFile(String appID, String appType, String userID, String Date, String Time, String Status) throws IOException {

        FileWriter fw = new FileWriter("src/UserAppointments/" + userID + ".txt", true);

        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(appID + "," + appType + "," + userID + "," + Date + "," + Time + "," + Status);

        pw.flush();
        pw.close();

    }

    // deletes the line you want (oldAppointment) and does this by finding the
    // old users appointments file. Opens original and creates a new copy called 
    // userIDTemp, once done it deletes original and renames new copy to original
    public static Boolean appointmentDelete(String oldAppointment, String userID) throws IOException {
        File inputFile = new File("src/UserAppointments/" + userID + ".txt");
        File tempFile = new File("src/UserAppointments/" + userID + "Temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = oldAppointment;
        String currentLine;

        try {
            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine;
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            inputFile.delete();
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
        System.out.println("done");
        return true;
    }

}
