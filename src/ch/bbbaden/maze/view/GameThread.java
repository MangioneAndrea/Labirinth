/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze.view;

import ch.bbbaden.maze.Main;
import ch.bbbaden.maze.model.Cell;
import ch.bbbaden.maze.model.Direction;
import ch.bbbaden.maze.model.Maze;
import ch.bbbaden.maze.model.Memento;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author andre
 */
public class GameThread extends Thread{
    private final GridPane grid;
    private KeyCode actualCode=KeyCode.ACCEPT;
    private boolean mementoExists=false;
    private final Maze maze;
    private Memento memento;
    private boolean isRunning=true;
    public GameThread(GridPane grid, FXMLDocumentController controller) {
        this.setDaemon(true);
        this.grid = grid;
        maze=new Maze();
        Main.scene.addEventHandler(KeyEvent.KEY_RELEASED, (evt) -> {
            switch(evt.getCode()){
                case LEFT:
                case UP:
                case RIGHT:
                case DOWN:
                case SPACE:
                    actualCode=evt.getCode();
                default:;
            }
        });
    }
    

    
    
    @Override
    public void run(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                maze.setToGrid(grid);

            }
        });
        while(isRunning){
            try{
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        setTransparent();
                        if(!actualCode.equals(KeyCode.ACCEPT)){
                            if(actualCode.equals(KeyCode.SPACE)){
                                memento();
                            }else{
                                move(actualCode);
                            }
                            actualCode=KeyCode.ACCEPT;
                        }
                    }
                });
                Thread.sleep(16);
            }catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    private void move(KeyCode direction){
        try{
            Direction dir=Direction.getFromKeyCode(direction);
            //System.out.println(maze.getActualPosition().isOpen(dir));
            if(!maze.getActualPosition().isOpen(dir))return;
            maze.updatePosition(dir);
        }catch(ArrayIndexOutOfBoundsException outOfMap){
                if(maze.isTheEnd()){
                    end();
                }
        }
    }
    private void memento(){
        if(mementoExists){
            maze.restoreMemento(memento);
        }else{
            memento=maze.saveMemento();
        }
        mementoExists=!mementoExists;
    }
    private void setTransparent(){
        for(Cell[] arrCell: maze.getCells()){
            for(Cell cell: arrCell){
                if(cell!=maze.getActualPosition())cell.setTransparent();
                if(mementoExists)memento.getActualPosition().resetOpacity();
            }
        }
    }
    public void clean(){
        grid.getChildren().clear();
        isRunning=false;
    }
    private void end(){
        for(Cell[] arrCell: maze.getCells()){
            for(Cell cell: arrCell){
                cell.resetOpacity();
            }
        }
        FXMLDocumentController.showEnd();
        isRunning=false;
    }
}
