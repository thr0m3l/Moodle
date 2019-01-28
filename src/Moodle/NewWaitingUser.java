package Moodle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewWaitingUser {
    private String fullname,username,email,password,userType;
    private Map<String,Integer> UserState=new HashMap<String,Integer>();

    public NewWaitingUser(String fullname, String username, String email, String password,String usertype) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType=usertype;
        UserState.put(fullname,null);
    }
    public void SetState(){
        UserState.put(fullname,1);
        User newUser=new User(username,password,fullname,email,userType);
        try {
            UserData.getUserData().getUsers().add(newUser);
            UserData.getUserData().saveUserData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
