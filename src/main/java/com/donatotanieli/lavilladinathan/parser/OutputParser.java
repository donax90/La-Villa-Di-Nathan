/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.parser;

import com.donatotanieli.lavilladinathan.entity.Command;
import com.donatotanieli.lavilladinathan.entity.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe che costruisce un oggetto contenente le informazioni, interpretate dal Parser, che sono scritte dall'utente.
 * @author donatotanieli
 */
public class OutputParser {
    
    private Command command;    //Comando che l'utente ha scritto es. nord, sud, prendi ecc...
    private List<GameObject> objList;   //La lista di oggetti con i quali l'utente vuole interagire
    private int wordsNumber;    //Il numero di parole contenute nella frase input dell'utente che servirà a capire quali azioni intrapendere a seconda del numeero

    //COSTRUTTORI
    
    public OutputParser() {
        command = null;
        objList = new ArrayList<>();
    }

    public OutputParser(Command command, List<GameObject> objList, int wordsNumber) {
        this.command = command;
        this.objList = objList;
        this.wordsNumber = wordsNumber;
    }

    //GETTER E SETTER
    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public List<GameObject> getObjList() {
        return objList;
    }

    public void setObjList(List<GameObject> objList) {
        this.objList = objList;
    }

    public int getWordsNumber() {
        return wordsNumber;
    }

    public void setWordsNumber(int wordsNumber) {
        this.wordsNumber = wordsNumber;
    }
    
    //ALTRI METODI
    
    /**
     * Metodo che setta a null tutte le variabili di istanza
     */
    public void setNull() {
        this.command = null;
        this.objList = null;
        this.wordsNumber = 0;
    }
    
    /**
     * Metodo che setta il numero di parole prendendolo dal parametro in ingresso e imposta il resto a null
     * @param wordsNumber 
     */
    public void setNull(int wordsNumber){
        this.command = null;
        this.objList = null;
        this.wordsNumber = wordsNumber;
    }
    
    /**
     * Metodo che aggiunge un oggetto alla lista
     * @param go 
     */
    public void addGameObject(GameObject go){
        this.objList.add(go);
    }
    
    
    
    
}
