package Moodle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
    }
    public void showLoginPage () throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Loading the controller and aro onek kisu
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showHomePage (User user) throws Exception{

    }
    @Override
    public void init() throws Exception {
        try{
            UserData.getUserData().loadUserData();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void stop() throws Exception {
        try{
            UserData.getUserData().saveUserData();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
