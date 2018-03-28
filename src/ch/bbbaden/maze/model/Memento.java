/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.maze.model;

/**
 *
 * @author andre
 */
public class Memento {
    
    private final Cell actualPosition;

    public Memento(Cell actualPosition) {
        this.actualPosition = actualPosition;
    }

    public Cell getActualPosition() {
        return actualPosition;
    }
    
}
