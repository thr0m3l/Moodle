package Moodle;

import Moodle.Client.ChatController;
import Moodle.Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
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
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("Home.fxml"));
//        Parent root = loader.load();

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "Home.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 1280;
        double origH = 720;
        //If the Region containing the GUI does not already have a preferred width and height, set it.
        //But, if it does, we can use that setting as the "standard" resolution.
        if ( contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE )
            contentRootRegion.setPrefWidth( origW );
        else
            origW = contentRootRegion.getPrefWidth();
        if ( contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE )
            contentRootRegion.setPrefHeight( origH );
        else
            origH = contentRootRegion.getPrefHeight();
        //Wrap the resizable content in a non-resizable container (Group)
        Group group = new Group( contentRootRegion );
        //Place the Group in a StackPane, which will keep it centered
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(group);
        stage.setTitle( "Moodle HomePage" );
        //Create the scene initially at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();




        // Loading the controller
        HomeController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Moodle HomePage");
//        stage.setScene(new Scene(root, 1280, 720));
//        stage.show();


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

    public void showStorage() throws IOException {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "FXML/Storage.fxml" ) );
        Region contentRootRegion = loader.load();
        double origW = 1280;
        double origH = 720;


        if ( contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE )
            contentRootRegion.setPrefWidth( origW );
        else
            origW = contentRootRegion.getPrefWidth();
        if ( contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE )
            contentRootRegion.setPrefHeight( origH );
        else
            origH = contentRootRegion.getPrefHeight();
        Group group = new Group( contentRootRegion );

        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(group);
        stage.setTitle( "Moodle Storage" );
        Scene scene = new Scene( rootPane, origW, origH );
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();




        // Loading the controller
        StorageController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        //controller.init(userName);
        controller.setMain(this);
        client.setStorageController(controller);

    }
}
