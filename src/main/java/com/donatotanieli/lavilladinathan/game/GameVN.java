/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.game;

import com.donatotanieli.lavilladinathan.parser.OutputParser;

/**
 *Classe astratta che definisce la gestione del gioco
 * @author donatotanieli
 */
public abstract class GameVN {
    
    private Game game; 
    
    /**
     * Metodo astratto che gestisce l'esecuzione del comando che l'utente ha scritto
     * @param output 
     * @return stringa di risposta dopo aver eseguito il comando
     */
    protected abstract String executeCommand(OutputParser output);
    
    //COSTRUTTORE
    public GameVN(Game g){
        this.game = g;
    }

    //GET E SET
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    
    
}
