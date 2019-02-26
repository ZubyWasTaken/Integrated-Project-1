import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Caledonian Sports Clinic");

        VBox leftMenu = new VBox();
        Button userLogin = new Button("User Login");
        Button userRegister = new Button("User Register");
        Button staffLogin = new Button("Staff Login");
        Button exitProgram = new Button("Exit");
        
        userLogin.setPrefSize(90, 20);
        userRegister.setPrefSize(90, 20);
        staffLogin.setPrefSize(90, 20);
        exitProgram.setPrefSize(90, 20);
        
        leftMenu.getChildren().addAll(userLogin, userRegister, staffLogin, exitProgram);
        userLogin.setOnAction(e -> UserLogin.display("User Login", "Please log in below."));
        
        
//        buttonD.setOnAction(e -> ConfirmBox.display("Title", "Do you want to exit?"));

        BorderPane borderPane = new BorderPane();
        leftMenu.setAlignment(Pos.CENTER);
        borderPane.setLeft(leftMenu);

        
        Scene scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.show();
    }

}