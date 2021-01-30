/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.game;

import com.donatotanieli.lavilladinathan.entity.LightRoom;
import com.donatotanieli.lavilladinathan.entity.Player;
import com.donatotanieli.lavilladinathan.entity.Room;
import java.util.ArrayList;


/**
 *
 * @author donatotanieli
 */
public class Game {
    
    private Player player;
    private Room currentRoom;
    private ArrayList<LightRoom> lightrooms;
    public static boolean alive = true;
    public static boolean winner = false;
    
    public Game(Player player, Room currentRoom, ArrayList<LightRoom> lightrooms){
        this.player = player;
        this.currentRoom = currentRoom;
        this.lightrooms = lightrooms;
    }
    
    public Game(){
        player = new Player();
        currentRoom = new Room();
        lightrooms = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<LightRoom> getLightrooms() {
        return lightrooms;
    }

    public void setLightrooms(ArrayList<LightRoom> lightrooms) {
        this.lightrooms = lightrooms;
    }
        
}
