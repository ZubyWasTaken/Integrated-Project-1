/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject1;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Zuby
 */
public class IntegratedProject1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Creates new button object called btn
        Button btn_login = new Button();
        Button btn_register = new Button();
        Button btn_stafflogin = new Button();

        //Create User Login Button
        btn_login.setText("User Login");
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            //Event Handler to click button
            public void handle(ActionEvent event) {
                //Outputs to console "Switching to User Login Screen!"
                System.out.println("Switching to User Login Screen");
            }
        });

        //Create User Registr Button
        btn_register.setText("User Register");
        btn_login.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            //Event Handler to click button
            public void handle(ActionEvent event){
                System.out.println("Switching to User Register Screen");

            }
        });

        //Create staff login button

        //Create event handler to click button



        
        //New Stackpane created called 'root'.
        // This layers its children back to front
        StackPane root = new StackPane();
        
        //Adding buttons to screen
        root.getChildren().add(btn_login);
        root.getChildren().add(btn_register);
        //add staff login button

        //Moving Buttons
        btn_login.setTranslateY(-60);
        btn_register.setTranslateY(-20);
        //transform staff login button

       
        
        
        //A new scene called scene is created, with the root as the main scene, and the height and width of the scene set.
        Scene scene = new Scene(root, 600, 400);
        
        //Sets title to "Hello World!"
        primaryStage.setTitle("Hello World!");
        //Specify's what scene to be used here
        primaryStage.setScene(scene);
        //Displays that scene
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}