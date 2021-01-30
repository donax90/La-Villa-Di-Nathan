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
 *
 * @author donatotanieli
 */
public class OutputParser {
    
    private Command command;
    private List<GameObject> objList;
    private int wordsNumber;

    public OutputParser() {
        command = null;
        objList = new ArrayList<>();
    }

    public OutputParser(Command command, List<GameObject> objList, int wordsNumber) {
        this.command = command;
        this.objList = objList;
        this.wordsNumber = wordsNumber;
    }

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
    
    public void setNull() {
        this.command = null;
        this.objList = null;
        this.wordsNumber = 0;
    }
    
    public void setNull(int wordsNumber){
        this.command = null;
        this.objList = null;
        this.wordsNumber = wordsNumber;
    }
    
    public void addGameObject(GameObject go){
        this.objList.add(go);
    }
    
    
    
    
}
