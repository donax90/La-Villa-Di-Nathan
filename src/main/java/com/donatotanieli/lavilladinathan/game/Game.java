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
 *Classe che rappresenta la struttura del gioco
 * @author donatotanieli
 */
public class Game {
    
    private Player player; //il giocatore
    private Room currentRoom; //la stanza in cui si trova il giocatore
    private ArrayList<LightRoom> lightrooms; // la lista delle stanze illuminate che sostituiranno quelle al buio
    public static boolean alive = true; //flag che rappresenta la sopravvivenza del giocatore
    public static boolean winner = false; //flag che diventa true quando il giocatore ha completato il gioco
    
    
    //COSTRUTTORI
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

    //GET E SET
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
