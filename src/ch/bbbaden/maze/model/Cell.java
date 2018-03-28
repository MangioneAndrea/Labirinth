/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze.model;

import ch.bbbaden.maze.Main;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 *
 * @author andre
 */
public class Cell extends Label{
    private int type;
    private Image img;
    private final boolean proofed[]=new boolean[4];
    private final boolean open[]=new boolean[4];
    private final  int x,y;
    private double opacityLevel=1;
    private final double minOpacityLevel=0;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /**
     * Method for the creation of the maze and the walls around the cell.
     * If a cell has been proofed, it means that either there is already
     * a cell in that direction.
     * @param dir Direction to set proofed
     */
    public void setProofed(Direction dir){
        proofed[dir.getValue()]=true;
    }
    /**
     * Method for the creation of the maze used to open a way for the cell.
     * If a cell is open, it does mean that it's possible to move on that way
     * @param dir Direction to open
     */
    public void setOpen(Direction dir){
        open[dir.getValue()]=true;
        type+=Math.pow(2,dir.getValue());
        updateGraphic();
    }
    /**
     * After proofing the adjacent cells it proofs, whether there are free space
     * around it or not.
     * @return Direction.error if there's no free space. A random direction if 
     * it has
     */
    public Direction getRandomFreeDirection(){
        ArrayList<Direction> list=new ArrayList<>();
        for(int i=0;i<4;i++){
            if(!proofed[i])list.add(Direction.getFromNumber(i));
        }
        if(list.isEmpty())return Direction.error;
        return list.get((int)(Math.random()*list.size()));
    }
    public boolean isOpen(Direction dir){
        return open[dir.getValue()];
    }
    /**
     * Updates the image of the Cell with the actual number of walls and way in the maze
     */
    private void updateGraphic(){
        this.img = Main.images[type];
        setBackground(new Background(
                new BackgroundImage(
                        img,BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                800,800,
                                false,false,true,true
                        )
                )
        ));
    }
    /**
     * setOpen's overwrite used to set the start of the labyrinth
     */
    public void setStart(){
        setOpen(Direction.up);
        updateGraphic();
    }
    /**
     * setOpen's overwrite used to set the end of the labyrinth
     */
    public void setEnd(){
        setOpen(Direction.down);
        updateGraphic();
        
    }
    /**
     * put the opacity of the cell down. It does scale it to 1.6% at time
     */
    public void setTransparent(){
        if(opacityLevel>=minOpacityLevel)opacityLevel-=0.016;
        this.setOpacity(opacityLevel);
    }
    /**
     * put the opacity of the cell to 100% preventing it to become invisible
     */
    public void resetOpacity(){
        opacityLevel=1;
        this.setOpacity(opacityLevel);
    }
}
