package Moodle.Server;

import Moodle.UserData;

import java.io.IOException;

public class ServerMain {
    private Server dataServer;
    private Server chatServer;
    private Server fileServer;
    private static int port = 8818;

    public static void main(String[] args) {
        Server ds = new Server();
        Thread dataServer = new Thread(ds);
        dataServer.start();

        try{
            UserData.getUserData().loadUserData();
        } catch (IOException e){
            e.printStackTrace();
        }

    }




}
