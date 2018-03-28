/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze.model;

import javafx.scene.layout.GridPane;

/**
 *
 * @author andre
 */
public final class Maze{
    private final Cell grid[][];
    private Cell actualPosition;
    private final int dimension=11;
    public Maze() {
        this.grid = new Cell[dimension][dimension];
        grid[3][0]=new Cell(3,0);
        recursiveMethod(3,0);
        grid[3][0].setStart();
        grid[6][dimension-1].setEnd();
        actualPosition=grid[3][0];
    }
    public void recursiveMethod(int x,int y){
        Direction dir;
        while((dir=grid[x][y].getRandomFreeDirection())!=Direction.error){
            try{
                if(grid[x+dir.getX()][y+dir.getY()]==null){
                    grid[x+dir.getX()][y+dir.getY()]=new Cell(x+dir.getX(),y+dir.getY());
                    grid[x][y].setOpen(dir);
                    grid[x+dir.getX()][y+dir.getY()].setOpen(dir.getInverted());
                    recursiveMethod(x+dir.getX(),y+dir.getY());
                }
            }catch(ArrayIndexOutOfBoundsException outOfGrid){
                
            }
            grid[x][y].setProofed(dir);
        }
    }

    public void setActualPosition(Cell actualPosition) {
        this.actualPosition = actualPosition;
    }

    public Cell[][] getCells() {
        return grid;
    }

    public Cell getActualPosition() {
        return actualPosition;
    }
    
    public GridPane setToGrid(GridPane base){
        
         for(int i=0;i<grid[0].length;i++){//column -->x
            for(int j=0;j<grid.length;j++){//row -->y
                base.add(grid[i][j], i, j);
                grid[i][j].setPrefSize(900, 900);
            }
        }
        return base;
    }
    public boolean isTheEnd(){
        return actualPosition==grid[6][dimension-1];
    }
    public void updatePosition(Direction dir){
            setActualPosition(grid[actualPosition.getX()+dir.getX()][actualPosition.getY()+dir.getY()]);
            getActualPosition().resetOpacity();
    }
    public void restoreMemento(Memento memento){
        setActualPosition(grid[memento.getActualPosition().getX()][memento.getActualPosition().getY()]);
        getActualPosition().resetOpacity();
    }
    public Memento saveMemento(){
        return new Memento(actualPosition);
    }
}
