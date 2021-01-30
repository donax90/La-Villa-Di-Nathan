/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.game;

import com.donatotanieli.lavilladinathan.parser.OutputParser;

/**
 *
 * @author donatotanieli
 */
public abstract class GameVN {
    
    private Game game;
    
    protected abstract String executeCommand(OutputParser output);
    
    public GameVN(Game g){
        this.game = g;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    
    
}
