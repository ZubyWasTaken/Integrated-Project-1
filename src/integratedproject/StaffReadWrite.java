/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zuby
 */
public class StaffReadWrite {

    public static boolean doesUsernameExist(String username) throws FileNotFoundException {
        try {
            Scanner user = new Scanner(new File("src/StaffData/" + username + ".txt"));
            user.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

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

    public static List<String> readUsernames() throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        Scanner input = new Scanner(new File("src/UserData/AllUsernames.txt"));
        while (input.hasNextLine()) {
            list.add((input.nextLine()));
        }
        input.close();

        return list;
    }

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
        }
        return records;
    }

    
    public static List<String> singularAppointment(int counter) throws IOException {
        List<List<String>> records = readAllFiles();

        try {
            return records.get(counter);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("testtest");
        }
        return null;
    }
}
