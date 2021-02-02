/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.game;

import com.donatotanieli.lavilladinathan.gamefile.GameFile;
import com.donatotanieli.lavilladinathan.parser.Parser;

/**
 *Classe che fa da tramite tra l'utente e il gioco.Attraverso l'input dell'utente restituisce la stringa di risposta del gioco
 * @author donatotanieli
 */
public class GameCommunicator {
    
    private Parser parser; //parser per codificare l'input dell'utente
    private GameManager gameManager; //istanza che contiene la logica del gioco
    private GameFile gameFile; //istanza che contiene le risorse del gioco

    
    //COSTRUTTORE
    public GameCommunicator() {
        gameFile = new GameFile();
        gameManager = new GameManager(gameFile.getGame());
    }

    //GET
    public GameManager getGameManager() {
        return gameManager;
    }

    //ALTRI METODI
    
    /**
     * Metodo che si occupa della risposta del gioco in base all'input dell'utente
     * @param input stringa input dell'utente
     * @return stringa di risposta
     */
    public String gameResponse(String input){
        
        parser = new Parser();
        return gameManager.executeCommand(parser.parseInput(input, gameFile.getGameObjectList()));
    }
    
    
    
    
    
}
