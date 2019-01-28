package Moodle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.*;
import java.io.File;
import java.io.*;

public class SiteNewsController {

    private Main main;
    private final String filename="SiteNews.data";
    private final String filename2="SiteNewsData2.txt";


    private User currentUser;
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    @FXML
    private AnchorPane Root;
    @FXML
    private Button btn;
    @FXML
    private Button Back;
    @FXML
    TextField textField[];
    @FXML
    private TextArea Holder;
    @FXML
    private ListView Holder2;
    @FXML
    public void ShowPost()throws Exception{
        //trying to show the posts from data file
        Holder.setWrapText(true);
        FileInputStream fis = new FileInputStream(filename);
        ArrayList<Object> objectsList = new ArrayList<Object>();
        StringBuilder sb=new StringBuilder();
        boolean cont = true;
        try{
            ObjectInputStream input = new ObjectInputStream(fis);
            while(cont){
                Object obj = input.readObject();
                sb.append("New Post:\n"+obj.toString());
                sb.append("\n");
                //Holder.setText(obj.toString());
                if(obj != null)
                    objectsList.add(obj);
                else
                    cont = false;
            }
        }catch(Exception e){
            //System.out.println(e.printStackTrace());
        }
        Holder.setText(sb.toString());

        /*int size=SiteNews.getNumber();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<size;i++){
            System.out.println("aise  22222222222222222222");
            sb.append(SiteNews.showPosts(i));
            sb.append("\n\n");
        }
        Holder.setWrapText(true);
        Holder.setText(sb.toString());*/


        //trying to show the posts from text file
        try{
            File file=new File(filename2);
            //FileInputStream fin=new FileInputStream(file);
            BufferedReader reader=new BufferedReader(new FileReader(file));
            StringBuilder builder=new StringBuilder();
            String line;
            while( (line=reader.readLine())!=null      ){
                builder.append(line);
            }
            String post=builder.toString();
            StringBuilder builder2=new StringBuilder();
            StringTokenizer stringTokenizer=new StringTokenizer(post,"###");
            while(stringTokenizer.hasMoreTokens()){
                String post2=stringTokenizer.nextToken();
                StringTokenizer stringTokenizer2=new StringTokenizer(post2,"#");
                //builder2.append("\n\n\n");
                while(stringTokenizer2.hasMoreTokens()){
                    String holderText=stringTokenizer2.nextToken()+"\n";
                    builder2.append(holderText);
                }
            }

            Holder.setText(builder2.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void BackAction()throws Exception{
        main.showHomePage(currentUser);
    }

}