package Moodle;


import Moodle.Client.Client;
import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;


public class StorageController {

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Label userNameLabel;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnMessage;

    @FXML
    private Button btnFriends;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnStorage;

    @FXML
    private Button btnSettings;

    @FXML
    private Button PostBtn;

    @FXML
    private Button SiteNews;

    @FXML
    private Button btnSignout;


    @FXML
    private JFXListView<File> fileList;

    @FXML
    private Button addFileButton;

    private User currentUser;

    private Main main;

    private ObservableList<File> files = FXCollections.observableArrayList(Main.getCurrentUser().getFiles());

    @FXML
    void showNewFileDialog(ActionEvent event) {

    }
    FileChooser fileChooser = new FileChooser();

    @FXML
    public void initialize(){

        fileList.setItems(files);
        btnSignout.setOnAction(event -> {
            try{
                main.showLoginPage();
                Main.setCurrentUser(null);
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

        btnOverview.setOnAction(event -> {
            try{
                main.showHomePage(Main.getCurrentUser());
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

        btnMessage.setOnAction(event -> {
            try{
                main.showChatScreen();
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }
        });

        addFileButton.setOnAction( event -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Select a text file");
            java.io.File file = fileChooser.showOpenDialog(homeAnchorPane.getScene().getWindow());


            System.out.println(file.getPath());

            File newFile = new File(file);
            newFile.setOwner(Main.getCurrentUser().getUserName());
//            fileList.getItems().add(newFile);

            Message fileMsg = new Message();
            fileMsg.setUser(Main.getCurrentUser());
            fileMsg.setFile(newFile);
            fileMsg.setMessageType(MessageType.FILE);


            Main.getClient().send(fileMsg);
        });

        fileList.setCellFactory(new Callback<ListView<File>,  ListCell<File>>() {
            @Override
            public ListCell<File> call(final ListView<File> lv) {
                ListCell<File> cell = new ListCell<File>(){
                    int height = 20;
                    @Override
                    protected void updateItem(final File item, boolean empty) {
                        super.updateItem(item, empty);
                        final HBox hbox = new HBox();
                        setGraphic(hbox);
                        if (item != null && getIndex() > -1) {
                            final Label labelHeader = new Label(item.getName());
                            labelHeader.setStyle("-fx-text-fill: #161616");
//                            labelHeader.setGraphic(createArrowPath(height, false));
                            labelHeader.setGraphicTextGap(20);
                            labelHeader.setId("tableview-columnheader-default-bg");
//                            labelHeader.setPrefWidth(fileList.getWidth() - 10);
                            labelHeader.setPrefHeight(height);

                            Button btn = new Button("Download");
//                            Label owner = new Label("Owner : " +  item.getOwner());
                            btn.setOnAction(event -> {
                                item.byteToFile();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle(item.getName() + " is downloaded successfully");
                                alert.showAndWait();
                            });
                            Button btn1 = new Button("Properties");
                            btn1.setOnAction( event -> {

                            });

                            labelHeader.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent me) {
//                                        labelHeader.setGraphic(createArrowPath(height, false));
//                                        labelHeader.setGraphic(createArrowPath(height, true));
                                    hbox.setSpacing(25);
                                    item.setHidden(item.getHidden() ? false : true);
                                    if(item.getHidden()){
                                        hbox.getChildren().removeAll(btn,btn1);
                                    }
                                    else {
                                        hbox.getChildren().addAll(btn,btn1);
                                    }

                                }
                            });

                            hbox.getChildren().add(labelHeader);
                        }
                    }

                };
                return cell;
            }
        });






    }
    public void addToFileList(File file){
        files.add(file);
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void PostBtnAction(ActionEvent event) {

    }

    @FXML
    void SiteNewsAction(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;



}
