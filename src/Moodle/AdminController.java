package Moodle;

import Moodle.Messages.Message;
import Moodle.Messages.MessageType;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

    @FXML
    private ListView<User> userList;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXButton saveButton;


    private ObservableList<User> users = null;

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        this.users = users;
        userList.setItems(users);
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){


        saveButton.setOnAction( event -> {
            try {
                main.showLoginPage();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        userList.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(final ListView<User> lv) {
                ListCell<User> cell = new ListCell<User>(){
                    int height = 80;
                    @Override
                    protected void updateItem(final User item, boolean empty) {
                        super.updateItem(item, empty);
                        final VBox vbox = new VBox();
                        vbox.setSpacing(10);
                        setGraphic(vbox);

                        if (item != null && getIndex() > -1) {
                            final Label labelHeader = new Label(item.getUserName());
                            labelHeader.setStyle("-fx-text-fill: #e7e5e5");
                            labelHeader.setGraphic(createArrowPath(height, false));
                            labelHeader.setGraphicTextGap(10);
                            labelHeader.setId("tableview-columnheader-default-bg");
                            labelHeader.setPrefWidth(userList.getWidth() - 10);
                            labelHeader.setPrefHeight(height);
                            Label userType = new Label(item.getUserType());
                            Label status = new Label("Approved : " + (item.isApproved() ? "true" : "false"));
                            Button btn = new Button("Approve");
                            btn.setVisible(false);
                            btn.setOnAction( event -> {
                                item.setApproved(true);
                                Message message = new Message();
                                message.setMessageType(MessageType.PROFILEUPDATE);
                                message.setUser(item);
                                Main.getClient().send(message);
                            });
                            labelHeader.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent me) {
                                    item.setHidden(item.isHidden() ? false : true);
                                    if (item.isHidden()) {
                                        labelHeader.setGraphic(createArrowPath(height, false));
                                        vbox.getChildren().removeAll(btn,userType,status);
                                    }
                                    else {
                                        labelHeader.setGraphic(createArrowPath(height, true));
                                        vbox.getChildren().addAll(btn,userType,status);
                                        btn.setVisible(true);
                                    }
                                }
                            });
                            vbox.getChildren().add(labelHeader);
                        }
                    }

                };
                return cell;
            }
        });



    }

    private SVGPath createArrowPath(int height, boolean up) {
        SVGPath svg = new SVGPath();
        int width = height / 4;

        if (up)
            svg.setContent("M" + width + " 0 L" + (width * 2) + " " + width + " L0 " + width + " Z");
        else
            svg.setContent("M0 0 L" + (width * 2) + " 0 L" + width + " " + width + " Z");

        return svg;
    }
}
