package Moodle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;
import java.io.File;
import java.io.*;

import static javafx.scene.paint.Color.BLACK;

public class SiteNewsController implements Initializable {
    @FXML
    private VBox box;

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
    public void BackAction()throws Exception{
        main.showHomePage(currentUser);
    }

    @FXML
    public void PostBtnAction()throws  Exception{
        main.showPostingNotice(currentUser);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ScrollPane sp=new ScrollPane();
        VBox.setVgrow(sp, Priority.ALWAYS);
        sp.setContent(box);
        sp.setVmax(440);
        sp.setPrefSize(1000, 730);
        sp.setLayoutX(250);
        sp.setLayoutY(0);
        box.setStyle("-fx-border-color:khaki; -fx-background-color: khaki;");
        TextArea[] ta=new TextArea[50];
        Text name,date;
        int counter=0;
        try{
            File file=new File(filename);
            FileInputStream fin =new FileInputStream(file);
            ObjectInputStream oin=new ObjectInputStream(fin);
            boolean cond=true;
            while(cond){
                counter++;
                Object obj=null;
                try{
                    obj=oin.readObject();
                    SiteNews posts=(SiteNews) obj;
                    ta[counter]=new TextArea();

                    ta[counter].setLayoutX(320);
                    ta[counter].setWrapText(true);
                    ta[counter].setMaxSize(1000,300);
                    ta[counter].setFont(new Font("Arial",25));
                    ta[counter].setText(((SiteNews) obj).getDetails());

                     name=new Text("Posted BY:"+((SiteNews) obj).getUser());
                    name.setFont(Font.font("Italic",20));
                    name.setFill(BLACK);
                     date=new Text("Last Modified at:"+((SiteNews) obj).getDate());
                    date.setFont(Font.font("Italic",20));
                    date.setFill(BLACK);
                    box.getChildren().addAll(name,date,ta[counter]);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                if(obj==null){
                    cond=false;
                    fin.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}