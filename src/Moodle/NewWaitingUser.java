package Moodle;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//new user jara admin er permission er opekkhay
public class NewWaitingUser implements Serializable {
    private String fullname,username,email,password,userType;
    private Map<String,Integer> UserState=new HashMap<String,Integer>();

    public NewWaitingUser(String fullname, String username, String email, String password,String usertype) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType=usertype;
        //null = not approved yet
        UserState.put(fullname,null);
    }

    //admin approve korle iha call korar plan ache
    public void SetState(){
        UserState.put(fullname,1);
        User newUser=new User(username,password,fullname,email,userType);
//        try {
//            UserData.getUserData().getUsers().add(newUser);
//            UserData.getUserData().saveUserData();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public String toString() {
        return "NewWaitingUser{" +
                "fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", UserState=" + UserState +
                '}';
    }
}
