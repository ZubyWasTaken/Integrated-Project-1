/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject1;

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
        Button btn = new Button();

        //Sets the visable text on the button object to display "Say 'Hello World'"'
        btn.setText("Say 'Hello World'");

        //When the button object recieves an action, a new event handler is created to handle that action
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            //This handles that event
            public void handle(ActionEvent event) {
                //Outputs to console "Hello Guys!"
                System.out.println("Hello Guys!");
            }
        });
        
        //New Stackpane created called 'root'.
        // This layers its children back to front
        StackPane root = new StackPane();
        //The root object gets it's children as a list and adds button as another child
        root.getChildren().add(btn);
        
        //A new scene called scene is created, with the root as the main scene, and the height and width of the scene set.
        Scene scene = new Scene(root, 300, 250);
        
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
