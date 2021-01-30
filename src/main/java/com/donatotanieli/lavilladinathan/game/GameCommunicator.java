/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.game;

import com.donatotanieli.lavilladinathan.gamefile.GameFile;
import com.donatotanieli.lavilladinathan.parser.OutputParser;
import com.donatotanieli.lavilladinathan.parser.Parser;

/**
 *
 * @author donatotanieli
 */
public class GameCommunicator {
    
    private Parser parser;
    private GameManager gameManager;
    private GameFile gameFile;

    public GameCommunicator() {
        gameFile = new GameFile();
        gameManager = new GameManager(gameFile.getGame());
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Metodo che si occupa della risposta del gioco in base all'input dell'utente
     * @param input
     * @return 
     */
    public String gameResponse(String input){
        
        parser = new Parser();
        return gameManager.executeCommand(parser.parseInput(input, gameFile.getGameObjectList()));
    }
    
    
    
    
    
}
