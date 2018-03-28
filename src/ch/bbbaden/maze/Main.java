/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author andre
 */
public class Main extends Application {
    
    public static Image[] images={
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0000.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0001.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0010.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0011.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0100.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0101.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0110.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/0111.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1000.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1001.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1010.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1011.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1100.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1101.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1110.png")),
        new Image(Main.class.getResourceAsStream("/ch/bbbaden/maze/resource/1111.png")),
    };
    static public Scene scene;
    
    /**
     * create Stage and bind the view
     * Set ratio 1:1
     * @param stage standard stage of javaFX
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.minWidthProperty().bind(scene.heightProperty());
        stage.minHeightProperty().bind(scene.widthProperty().add(30));
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
