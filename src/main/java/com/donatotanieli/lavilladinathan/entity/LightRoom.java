/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;
import java.util.List;

/**
 *Classe che rappresenta la stanza illuminata. Una stanza illuminata può contenere degli oggetti
 * @author donatotanieli
 */
public class LightRoom extends Room implements Serializable{
    
    private List<GameObject> gameObjList; //lista di oggetti della stanza

    
    //COSTRUTTORI
    public LightRoom() {
    }

  
    public LightRoom(List<GameObject> gameObjList, Room r) {
        super(r.getN(), r.getS(), r.getE(), r.getW(), r.getUnlockedBy(), r.getName(), r.getDescription(), r.getLook());
        this.gameObjList = gameObjList;
    }

    
    //SET E GET
    public List<GameObject> getGameObjList() {
        return gameObjList;
    }

    public void setGameObjList(List<GameObject> gameObjList) {
        this.gameObjList = gameObjList;
    }
    
    
    //ALTRI METODI
    
    /**
     * Metodo che restituisce l'oggetto dalla lista cercandolo in base al tipo
     * @param type il tipo di oggetto da cercare
     * @return null se l'oggetto non c'è, GameObject altrimenti
     */
    public GameObject getGameObjectFromList(ObjectType type){
        int index = -1;
        if(gameObjList != null){
            for( int i = 0; i < gameObjList.size(); i ++){
                if(gameObjList.get(i).getObjetcType() == type){
                    index = i;
                    break;
                }
            }
        }
        if(index != -1){
            return gameObjList.get(index);
        }else{
            return null;
        }
    }
    
    /**
     * Metodo che rimuove l'oggetto gameObj dalla lista
     * @param gameObj oggetto da rimuovere
     */
    public void removeGameObject(GameObject gameObj){
        gameObjList.remove(gameObj);
    }
    
}
