package Moodle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    Stage stage;
    private javafx.stage.Screen Screen;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
//        showHomePage(new User("r0m3l","1705069","Romel","12","Student"));
    }
    public void showLoginPage () throws Exception{
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        Scene scene=new Scene(root,1200,700);
        LoginController controller = loader.getController();
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();*/




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
    public void showSignUpPage () throws Exception{
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Registration.fxml"));
        Parent root = loader.load();

        RegistrationController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Sign Up");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/


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
        /*FXMLLoader loader = new FXMLLoader();
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
        stage.show();*/

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "Home.fxml" ) );
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
        HomeController controller = loader.getController();
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
    public void showCoursePage (User user, Course course) throws Exception{
       /* FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        Parent root = loader.load();
        // Loading the controller
        CourseController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setCurrentCourse(course);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle(course.getNumber());
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/

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
       /* FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        Parent root = loader.load();
        // Loading the controller
        CourseController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setCurrentCourse(course);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle(course.getNumber());
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/

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
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //Set the scene to the window (stage) and show it
        stage.setScene( scene );
        stage.show();
    }


    public void showSiteNews(User user)throws Exception{
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SiteNews.fxml"));

       Parent root = loader.load();
        // Loading the controller
        SiteNewsController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setMain(this);
        //Button btn=new Button("Set text");
        //root.getChildrenUnmodifiable().add(btn);
        // Set the primary stage*/

        /*stage.setTitle("Site News");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "SiteNews.fxml" ) );
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
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PostingNotice.fxml"));
        Parent root=loader.load();
        // Loading the controller

        PostingNoticeController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Post Page");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/

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
        try{
            UserData.getUserData().loadUserData();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }try{
            SiteNewsData.getSiteNewsData().loadSiteNewsData();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            CourseData.getCourseData().loadCourseData();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            PostData.getPostData().loadPostData();
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println(UserData.getUserData().getUsers());
        System.out.println(SiteNewsData.getSiteNewsData().getSiteNews());
    }
    @Override
    public void stop() throws Exception {
        try{
            UserData.getUserData().saveUserData();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void showFriends (User user) throws Exception{
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowingFriends.fxml"));
        Parent root = loader.load();
        // Loading the controller
        ShowingFriendsController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Friend Page");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "ShowingFriends.fxml" ) );
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
        ShowingFriendsController controller = loader.getController();
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
    public void showNewCourseDialog(User user)throws Exception{
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("newCourseDialog.fxml"));
        Parent root = loader.load();
        // Loading the controller
        newCourseDialogController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Add a new course");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/
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
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateProfile.fxml"));
        Parent root = loader.load();
        // Loading the controller
        UpdateProfileController controller = loader.getController();
        controller.setCurrentUser(user);
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Update your profile");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/

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
        //Scene scene=new Scene(contentRootRegion,1200,700);
        UpdateProfileController controller = loader.getController();
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

    public void showCoursePostEdit (User user, Course course) throws Exception {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePostEdit.fxml"));
        Parent root = loader.load();
        // Loading the controller
        CoursePostEditController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setCurrentCourse(course);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle(course.getNumber());
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/
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

    public void showConversation (User user, Course course,Post post) throws Exception{
       /* FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        Parent root = loader.load();
        // Loading the controller
        CourseController controller = loader.getController();
        controller.setCurrentUser(user);
        controller.setCurrentCourse(course);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle(course.getNumber());
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/

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
