package Moodle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public class LoginController {
    private Main main;

    @FXML
    private TextField userText; ///emnei comment

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginAnchorPane;



    @FXML
    void loginAction() throws Exception{
        ObservableList<User> userObservableList = UserData.getUserData().getUsers();
        Iterator<User> iter = userObservableList.iterator();
        boolean success = false;
        while(iter.hasNext()){
            User temp = iter.next();
            if(temp.userName.equals(userText.getText()) && temp.password.equals(passwordText.getText())){

                main.showHomePage(temp);
                success = true;
            }
        }
            // failed login
            if(!success) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Incorrect Credentials");
                alert.setContentText("The username and password you provided is not correct.");
                alert.showAndWait();
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

    void showSignUpDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(loginAnchorPane.getScene().getWindow());
        dialog.setTitle("Sign up");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get()== ButtonType.OK) {
//            DialogController controller = fxmlLoader.getController();
//            TodoItem newItem = controller.processResults();
////            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
//            todoListView.getSelectionModel().select(newItem);
        }
    }
}

