package integratedproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    // Checks if username exists by trying to open file with the users ID
    // if it can open file it returns true
    // if it cannot open file, it errors and returns false
    public static boolean doesUsernameExist(String username) throws FileNotFoundException {
        try {
            Scanner user = new Scanner(new File("src/UserData/" + username + ".txt"));
            user.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    // Reads text file that is the same as the users ID
    // reads in the first two lines and returns them
    // first two lines are username and password
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

    // Opens the users data file and reads first four lines
    // same as method above, but is used to get the forename and surname of user
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

    // Writes the following variables each to a new line
    // file name is same as user ID
    public static void writeToFile(String forename, String surname, String userID, String password, LocalDate dateOfBirth) throws IOException {

        FileWriter fw = new FileWriter("src/UserData/" + userID + ".txt", true);

        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(userID);
        pw.println(password);
        pw.println(forename);
        pw.println(surname);
        pw.println(dateOfBirth);

        pw.flush();
        pw.close();

    }

    // Writes the following variables all to the same line to make an appointment
    // file name is same as user ID
    public static void createAppointmentFile(String appointmentID, String comboSelection, String userID, LocalDate appointmentDate, String appointmentTime) throws IOException {

        FileWriter fw = new FileWriter("src/UserAppointments/" + userID + ".txt", true);

        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(appointmentID + "," + comboSelection + "," + userID + "," + appointmentDate + "," + appointmentTime + "," + " ");

        pw.flush();
        pw.close();

    }

    // Checks if appointment exists by trying to open file with the users ID
    // if it can open file it returns true
    // if it cannot open file, it errors and returns false
    public static boolean doesAppointmentExist(String username) {
        try {
            Scanner user = new Scanner(new File("src/UserAppointments/" + username + ".txt"));
            user.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }

    // deletes the appointment by copying the original file and renaming it
    // to originalnameTemp and then once done with the new file it deletes
    // the old fire and renames the new file to the original files name
    // original files name is the same as the user's ID
    public static Boolean appointmentDelete(String listString) throws IOException {
        File inputFile = new File("src/UserAppointments/" + Patient.userID + ".txt");
        File tempFile = new File("src/UserAppointments/" + Patient.userID + "Temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = listString;
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

    // Returns a list containing a list of all of the appointments that the
    // user has
    public static List<List<String>> returnAppointment() throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/UserAppointments/" + Patient.userID + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            br.close();
        }
        return records;
    }

    // Looks into the array, and the counter variable is used to return
    // the appointment in that position of the list.
    /*
    Strucure:
    EntireList{
        Appointment1{
            data
        }
        Appointment2{
            data
        }
        .. etc
    }
    
    */
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
