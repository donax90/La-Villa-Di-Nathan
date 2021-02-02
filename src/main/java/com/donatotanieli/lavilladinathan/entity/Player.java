/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;

/**
 *Classe che rappresenta il giocatore
 * @author donatotanieli
 */
public class Player implements Serializable{
    
    private String name; //nome
    private Inventory inventory; //inventario
    private int score; //punteggio

    //COSTRUTTORI
    public Player() {
        this.inventory = new Inventory();
        this.score = 0;
    }

    public Player(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
        this.score = 0;
    }

    //GET E SET
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    //ALTRI METODI
    
    /**
     * Incrementa il punteggio di 1
     */
    public void incrementScore(){
        score++;
    }
    
    /**
     * Incrementa il punteggio in base al numero passato
     * @param num valore che indica di quanto incrementare il punteggio
     */
    public void incrementScore(int num){
        score += num;
    }
    
    /**
     * Metodo che sottrae punti al giocatore
     */
    public void decrementScore(){
        switch(score){
            case 0 :
                break; //rimane 0
                
            case 1 :
                score = 0;
                break;
                
            default :
                score -= 2;
                break;
        }
    }
    
    
}
