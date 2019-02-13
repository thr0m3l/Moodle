package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubmissionsController implements Initializable {
    private Main main;
    private User currentUser;
    private Course currentCourse;
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    @FXML private ListView<File> fileListView;
    @FXML
    private ObservableList<File> files = FXCollections.observableArrayList(Main.getCurrentUser().getFiles());
    @FXML
    public void setCurrentCourse(Course currentCourse) {

        this.currentCourse = currentCourse;

    }
    public void initialize(URL location, ResourceBundle resources) {


        fileListView.setCellFactory(new Callback<ListView<File>,  ListCell<File>>() {
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
