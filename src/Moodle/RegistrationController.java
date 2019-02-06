package Moodle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.nio.Buffer;

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
    @FXML
    private Label inform;
    private final String filenameTemp="New Waiting Users.txt";


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
        for(User user : UserData.getUserData().getUsers()){
            if(userName.getText() == user.getUserName()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username already exists");
                alert.setHeaderText("The username you is already registered");
                alert.setContentText("Please try another username");
                alert.showAndWait();
            }
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
            NewWaitingUser newuser=new NewWaitingUser(fullName.getText(),userName.getText(),eMail.getText(),password.getText(),toogleGroupValue);
            inform.setText("Your request has been sent to Admin.");
            //writing these to new file
            try{
                File file =new File(filenameTemp);
                //FileOutputStream fos=new FileOutputStream(file,true);
                Object obj=newuser;
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file,true));
                BufferedReader reader=new BufferedReader(new FileReader(file));

                oos.writeObject(obj);
                System.out.println(reader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }
    @FXML
    public void returnToLogin() throws Exception{
        main.showLoginPage();
    }
}
