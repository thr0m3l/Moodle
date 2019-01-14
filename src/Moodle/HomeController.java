package Moodle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class HomeController {

    private Main main;
    @FXML
    private Button PostaNotice;

    @FXML
    void PostNoticeAction()throws Exception{
        main.showPostNotice();
    }



    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}

