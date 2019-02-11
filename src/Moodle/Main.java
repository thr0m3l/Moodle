package Moodle;

import Moodle.Client.ChatController;
import Moodle.Client.Client;
import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    private javafx.stage.Screen Screen;
    private static Client client = new Client("localhost",8818);
    private static User currentUser = null;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
//        showHomePage(new User("r0m3l","1705069","Romel","12","Student"));
    }
    public void showLoginPage () throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "Login.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        LoginController controller = loader.getController();
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();

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
    public void showSignUpPage () throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "Registration.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        RegistrationController controller = loader.getController();
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("REgistration");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void showHomePage (User user) throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "Home.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 1820;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        HomeController controller = loader.getController();
        controller.setCurrentUser(user);

        controller.setMain(this);
        client.setHomeController(controller);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
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

    public void showCoursePage (User user, Course course) throws Exception{


        FXMLLoader loader = new FXMLLoader( getClass().getResource( "CoursePage.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        CourseController controller = loader.getController();
        controller.setMain(this);
        controller.setCurrentCourse(course);
        controller.setCurrentUser(user);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }


    public void showCoursePage2 (User user, Course course) throws Exception{
        getUpdate();
        System.out.println("showCoursePage2: " + course.getTitle());
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "CoursePage2.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        CoursePage2Controller controller = loader.getController();
        controller.setMain(this);
        controller.setCurrentCourse(course);
        controller.setCurrentUser(user);

        client.setCoursePage2Controller(controller);
        // Set the primary stage
        stage.setTitle("Moodle");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }


    public void showSiteNews(User user)throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "SiteNews.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        SiteNewsController controller = loader.getController();
        controller.setMain(this);
        controller.setCurrentUser(user);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }

    public void showPostingNotice (User user) throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "PostingNotice.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        PostingNoticeController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }


    @Override
    public void init() throws Exception {

        Thread t = new Thread(client);
        t.start();
        client.setMain(this);
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        Main.client = client;
    }

    @Override
    public void stop() throws Exception {
//            try{
//                UserData.getUserData().saveUserData();
//            }catch(IOException e){
//                System.out.println(e.getMessage());
//            }
    }
    public void showFriends (User user) throws Exception{

        getUpdate();
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "ShowingFriends.fxml" ) );
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
//        ShowingFriendsController controller = loader.getController();
//        controller.setCurrentUser(user);
//        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }
    public void showNewCourseDialog(User user)throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "newCourseDialog.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        newCourseDialogController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }
    public void showMyProfile(User user)throws Exception{

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "UpdateProfile.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }

    public void showCoursePostEdit (User user, Course course) throws Exception {

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "CoursePostEdit.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        //stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        CoursePostEditController controller = loader.getController();
        controller.setMain(this);
        controller.setCurrentCourse(course);
        controller.setCurrentUser(user);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }
    public static void getUpdate(){
        Message updateMsg = new Message();
        updateMsg.setUser(currentUser);
        updateMsg.setMessageType(MessageType.GETUPDATE);
        Main.client.send(updateMsg);
    }

    public void showConversation (User user, Course course,Post post) throws Exception{
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "Conversation.fxml" ) );
        Region contentRootRegion = loader.load();
        //Set a default "standard" or "100%" resolution
        double origW = 600;
        double origH = 400;
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
        stage.setTitle( "My Slide" );
        //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Scene scene=new Scene(contentRootRegion,1200,700);
        ConversationController controller = loader.getController();
        controller.setMain(this);
        controller.setCurrentCourse(course);
        controller.setCurrentUser(user);
        controller.setPost(post);
        // Set the primary stage
        stage.setTitle("Conversation");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }
}
