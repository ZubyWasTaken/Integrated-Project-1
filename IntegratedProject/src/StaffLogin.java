import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StaffLogin {

    public static void display(String title, String message) {

        Stage window = new Stage();
        
        window.setTitle(title);
        Label loginLabel = new Label();
        loginLabel.setText(message);

        
        
        TextField staffLogin = new TextField ();
        PasswordField staffPassword = new PasswordField();
        Button login = new Button("Login");

        loginLabel.setTranslateX(-40);
        login.setTranslateY(40);
        login.setTranslateX(-40);
        
        staffLogin.prefWidth(10);
        staffPassword.minWidth(100);
        
        
        
        staffLogin.setTranslateY(-110);
        staffLogin.setTranslateX(5);
        
        staffPassword.setTranslateY(-62);
        staffPassword.setTranslateX(5);
       
        
        Label staffLabel = new Label("Staff ID:");
        Label passwordLabel = new Label("Password:");
        
        staffLabel.setTranslateY(6);
        passwordLabel.setTranslateY(26);
        
        staffLogin.setPromptText("Plese enter your Staff ID");
        staffPassword.setPromptText("Plese enter your password");
        
        StackPane layout = new StackPane();
        layout.getChildren().addAll(loginLabel, staffLogin, staffPassword, login);
//        layout.setAlignment(Pos.CENTER);
        
        
        VBox labels = new VBox();
        labels.getChildren().addAll(staffLabel, passwordLabel);
        labels.setSpacing(10.0);
//        labels.setAlignment(Pos.CENTER);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(labels);
        borderPane.setCenter(layout);
        
        
        Scene scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.show();
    }

}
