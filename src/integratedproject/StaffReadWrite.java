/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

}
