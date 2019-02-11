package Moodle;


import Moodle.Client.Client;
import Moodle.Client.DialogController;
import Moodle.Client.Group;
import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
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

import java.io.IOException;
import java.util.Optional;


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

        Button btn2 = new Button("Share");
        btn2.setOnAction( event -> {
            try{
                showShareDialog(event);
            } catch (java.lang.Exception e){
                e.printStackTrace();
            }

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
                            labelHeader.setStyle("-fx-text-fill: #f7f9fc");
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
                                alert.setTitle("FILE DOWNLOAD SUCCESSFUL");
                                alert.setHeaderText(item.getName() + " is downloaded successfully");
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
                                        hbox.getChildren().removeAll(btn,btn1,btn2);
                                    }
                                    else {
                                        hbox.getChildren().addAll(btn,btn1,btn2);
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

    public void showShareDialog (ActionEvent event) throws Exception{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(homeAnchorPane.getScene().getWindow());
        dialog.setTitle("Share File");
        dialog.setHeaderText("To add multiple users, put ';' between users ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newFileDialog.fxml"));
        NewFileDialogController controller = fxmlLoader.getController();
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

//                                    Group newGroup = controller.processResults();
//                                    newGroup.getUsers().add(Main.getCurrentUser().getUserName());
////            groupObservableList.add(newGroup);
//                                    groupList.getSelectionModel().select(newGroup);
//                                    groupList.refresh();
        }
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
