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

public class UserRegister {

    public static void display(String title, String message) {

        Stage window = new Stage();
        
        window.setTitle(title);
        Label loginLabel = new Label();
        loginLabel.setText(message);
        
        TextField userLogin = new TextField ();
        PasswordField userPassword = new PasswordField();
        Button login = new Button("Login");
        
        userLogin.prefWidth(10);
        userPassword.minWidth(100);
        
        loginLabel.setTranslateX(-40);
        login.setTranslateY(40);
        login.setTranslateX(-40);
        
        
        userLogin.setTranslateY(-110);
        userLogin.setTranslateX(5);
        
        userPassword.setTranslateY(-62);
        userPassword.setTranslateX(5);
       
        
        Label userLabel = new Label("User ID:");
        Label passwordLabel = new Label("Password:");
        
        userLabel.setTranslateY(6);
        passwordLabel.setTranslateY(26);
        
        userLogin.setPromptText("Plese enter your user ID");
        userPassword.setPromptText("Plese enter your password");
        
        StackPane layout = new StackPane();
        layout.getChildren().addAll(loginLabel, userLogin, userPassword, login);
//        layout.setAlignment(Pos.CENTER);
        
        
        VBox labels = new VBox();
        labels.getChildren().addAll(userLabel, passwordLabel);
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
