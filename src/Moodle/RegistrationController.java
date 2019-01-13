package Moodle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RegistrationController {
    private Main main;
    @FXML
    private ToggleGroup SF;
    @FXML
    private RadioButton faculty;
    @FXML
    private RadioButton student;
    @FXML
    private Button confirm;
    @FXML
    private Button returnLogin;
    @FXML
    private TextField fullName;
    @FXML
    private TextField userName;
    @FXML
    private TextField eMail;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password1;



    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void initialize(){ }
    @FXML
    public void signUpComplete() throws Exception {
        boolean success = true;
        RadioButton selectedRadioButton = (RadioButton) SF.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        if(!password.getText().equals(password1.getText()) || password.getText().length() <8){
            success = false;
        }
        if(!success) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("Please make sure that both passwords match and the password is at least 8 digits long");
            alert.showAndWait();
        }
        else {
            User user = new User(userName.getText(), password.getText(), fullName.getText(), eMail.getText(), toogleGroupValue);
            System.out.println(user);
            UserData.getUserData().getUsers().add(user);
            UserData.getUserData().saveUserData();
            System.out.println("Successful");
            main.showHomePage(user);
        }


    }
    @FXML
    public void returnToLogin() throws Exception{
        main.showLoginPage();
    }
}
