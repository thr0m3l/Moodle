package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.util.Iterator;

public class LoginController {
    private Main main;
    // ager Welcome to moodle lekhar color #457824
    @FXML
    private JFXTextField userText;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signUpButton;

    @FXML
    private AnchorPane loginAnchorPane;



    @FXML
    void loginAction() throws Exception{
//        ObservableList<User> userObservableList = Main.getUserData().getData();
//        Iterator<User> iter = userObservableList.iterator();
//        boolean success = false;
//        while(iter.hasNext()){
//            User temp = iter.next();
//            if(temp.getUserName().equals(userText.getText()) && temp.getPassword().equals(passwordText.getText())){
//                main.showHomePage(temp);
//                success = true;
//            }
//        }
//
//            if(!success) {
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Incorrect Credentials");
//                alert.setHeaderText("Incorrect Credentials");
//                alert.setContentText("The username and password you provided is not correct.");
//                alert.showAndWait();
//            }
        if(userText.getText().equals("admin") && passwordText.getText().equals("admin")){
            Message message = new Message();
            message.setMessageType(MessageType.ADMINLOGIN);
            Main.getClient().send(message);
        }
        else {
            Message message = new Message();
            message.setMessageType(MessageType.LOGIN);
            message.setUser(new User(userText.getText(),passwordText.getText()));
            Main.getClient().send(message);
        }


    }

    @FXML
    void resetAction() {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    @FXML
    void signUp() throws Exception{
        main.showSignUpPage();
    }
    @FXML
    public void forgotPassword(){

    }
    @FXML
    public void initialize(){
        userText.setText("Saadman");
        passwordText.setText("11711109");
    }
}