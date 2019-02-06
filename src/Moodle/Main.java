package Moodle;

import Moodle.Client.ChatController;
import Moodle.Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Client client = new Client("localhost",8818);
    private Stage stage;
    private javafx.stage.Screen Screen;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }



    public static Client getClient() {
        return client;
    }

    public static void setClient(Client cl) {
        client = cl;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
//        showHomePage(new User("r0m3l","1705069","Romel","12","Student"));
    }
    public void showLoginPage () throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        /*int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

        root.setStyle("-fx-background-color:rgb(186,153,122); -fx-background-radius:30;");
        int sceneWidth = 0;
        int sceneHeight = 0;
        if (screenWidth <= 800 && screenHeight <= 600) {
            sceneWidth = 600;
            sceneHeight = 350;
        } else if (screenWidth <= 1280 && screenHeight <= 768) {
            sceneWidth = 800;
            sceneHeight = 450;
        } else if (screenWidth <= 1920 && screenHeight <= 1080) {
            sceneWidth = 1000;
            sceneHeight = 650;
        }*/


        Scene scene=new Scene(root,1200,700);
        LoginController controller = loader.getController();
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
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


    public static void main(String[] args) {
        launch(args);
    }

    public void showHomePage (User user) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        // Loading the controller
        HomeController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Moodle HomePage");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    public void showCoursePage (User user, Course course) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        Parent root = loader.load();
        // Loading the controller
        CourseController controller = loader.getController();
//        controller.setCurrentUser(user);
//        controller.setMain(this);

        // Set the primary stage
        stage.setTitle(course.getNumber());
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public void showSiteNews(User user)throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SiteNews.fxml"));
        Parent root = loader.load();
        // Loading the controller
        SiteNewsController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Site News");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public void showPostingNotice (User user) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PostingNotice.fxml"));
        Parent root = loader.load();
        // Loading the controller
        PostingNoticeController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Post Page");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }


    @Override
    public void init() throws Exception {
            Thread thread = new Thread(client);
            thread.start();
            client.setMain(this);




    }
    @Override
    public void stop() throws Exception {
//        try{
//            cluserData.saveData();
//            courseData.saveData();
//        }catch(IOException e){
//            System.out.println(e.getMessage());
//        }

    }
    public void showChatScreen () throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/chatScreen.fxml"));
        Parent root = loader.load();
        // Loading the controller
        ChatController controller = loader.getController();
//        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        client.setChatController(controller);

        // Set the primary stage
        stage.setTitle("Chat Screen");
        stage.setScene(new Scene(root, 1280, 720));
        stage.setResizable(true);
        stage.show();
    }

}
