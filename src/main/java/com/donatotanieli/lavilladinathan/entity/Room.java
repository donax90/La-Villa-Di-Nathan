/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;

/**
 *
 * @author donatotanieli
 */
public class Room extends Entity implements Serializable{
    private Room n;
    private Room s;
    private Room e;
    private Room w;
    private ObjectType unlockedBy;

    public Room(Room n, Room s, Room e, Room w, ObjectType unlockedBy, String name, String description, String look) {
        super(name, description, look);
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
        this.unlockedBy = unlockedBy;
    } 

    public Room() {
        super(null, null, null);
    }

    public Room getN() {
        return n;
    }

    public void setN(Room n) {
        this.n = n;
    }

    public Room getS() {
        return s;
    }

    public void setS(Room s) {
        this.s = s;
    }

    public Room getE() {
        return e;
    }

    public void setE(Room e) {
        this.e = e;
    }

    public Room getW() {
        return w;
    }

    public void setW(Room w) {
        this.w = w;
    }

    public ObjectType getUnlockedBy() {
        return unlockedBy;
    }

    public void setUnlockedBy(ObjectType unlockedBy) {
        this.unlockedBy = unlockedBy;
    }
    
    public boolean isOpened(){
        return unlockedBy == null;
    }
    
}
