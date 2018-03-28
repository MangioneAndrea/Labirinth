/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author andre
 */
public class FXMLDocumentController implements Initializable {
    private static FXMLDocumentController instance;
    private GameThread gameThread;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    public Label actualPosition;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance=this;
        grid.minWidthProperty().bind(grid.heightProperty());
        grid.minHeightProperty().bind(grid.widthProperty());
    }

    @FXML
    private void start(ActionEvent event) {
        if(gameThread instanceof GameThread)gameThread.clean();
        gameThread=new GameThread(grid,getInstance());
        gameThread.start();
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void info(ActionEvent event) {
        Dialog<?> dialog = new Dialog<>();
        dialog.setTitle("Infos");
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ok);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        Label txt=new Label("Use the arrow keys to move onto the maze.\n"
                + "With the spacebar you can save your actual \nposition and "
                + "pressing it again you can restore it.");
        txt.setWrapText(true);
        gridPane.add(txt, 1, 0);

        dialog.getDialogPane().setContent(gridPane);


        dialog.showAndWait();
    }
    
    
    public static FXMLDocumentController getInstance(){
        return instance;
    }
    
    static public void showEnd() {
                Dialog<?> dialog = new Dialog<>();
                dialog.setTitle("Infos");
                ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(ok);

                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.setPadding(new Insets(20, 150, 10, 10));

                Label txt=new Label("You Won!!");
                txt.setWrapText(true);
                gridPane.add(txt, 1, 0);

                dialog.getDialogPane().setContent(gridPane);
                

                dialog.show();
            }
}
