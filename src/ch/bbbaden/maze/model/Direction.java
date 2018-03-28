/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze.model;

import javafx.scene.input.KeyCode;

/**
 *
 * @author andre
 */
public enum Direction {
    
    left(3),up(2),right(1),down(0),error(-1);
    private final int value;
    Direction(int value){
        this.value=value;
    }
    static public Direction getFromNumber(int i){
        switch(i){
            case 0:
            return down;
            case 1:
            return right;
            case 2:
            return up;
            case 3:
            return left;
        }
        return error;
    }
    public int getValue() {
        return value;
    }
    public int getX(){
        return value%2==1?(value==1?1:-1):0;
    }
    public int getY(){
        return value%2==0?(value==0?1:-1):0;
    }
    public Direction getInverted(){
        switch(this){
            case left:return right;
            case up:return down;
            case right:return left;
            case down:return up;
            default: return this;
        }
    }
    static public Direction getFromKeyCode(KeyCode i){
        if(i==KeyCode.DOWN)return down;
        if(i==KeyCode.RIGHT)return right;
        if(i==KeyCode.UP)return up;
        if(i==KeyCode.LEFT)return left;    
        return error;
    }
}
