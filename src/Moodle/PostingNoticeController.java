package Moodle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PostingNoticeController {
    private Main main;
    @FXML
    private Button Post_to_Forum;
    @FXML
    private TextField TakeNotice;
    @FXML
    private Button Back;
    @FXML
    void PostToForumAction()throws Exception{
        String filename="ForumNotices.txt";
        String Notice=TakeNotice.getText();
        //File file=new File(filename);
        try{
            FileOutputStream fos=new FileOutputStream(filename);
            fos.write(Notice.getBytes());
            fos.flush();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void ButtonBackAction()throws Exception{
        //main.showHomePage();
    }


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
