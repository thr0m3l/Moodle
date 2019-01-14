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

        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 1200, 700));
        stage.show();
    }
    public void showSignUpPage () throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Registration.fxml"));
        Parent root = loader.load();

        RegistrationController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Sign Up");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public void showPostNotice () throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PostingNotice.fxml"));
        Parent root = loader.load();

        PostingNoticeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Edit post");
        stage.setScene(new Scene(root, 600, 700));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showHomePage (User user) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Moodle HomePage");
        stage.setScene(new Scene(root, 1070, 650));
        stage.show();
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
