/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author donatotanieli
 */
public class LightRoom extends Room implements Serializable{
    
    private List<GameObject> gameObjList;

    public LightRoom() {
    }

  
    public LightRoom(List<GameObject> gameObjList, Room r) {
        super(r.getN(), r.getS(), r.getE(), r.getW(), r.getUnlockedBy(), r.getName(), r.getDescription(), r.getLook());
        this.gameObjList = gameObjList;
    }

    public List<GameObject> getGameObjList() {
        return gameObjList;
    }

    public void setGameObjList(List<GameObject> gameObjList) {
        this.gameObjList = gameObjList;
    }
    
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
    
    public boolean isInTheRoom(ObjectType type){
        boolean isInRoom = false;
        if(gameObjList != null){
            for(int i = 0; i < gameObjList.size(); i++){
                if(gameObjList.get(i).isIsGameObjectContainer()){
                    for(int j = 0; j < gameObjList.get(i).getGameObjList().size(); j++){
                        if(gameObjList.get(i).getGameObjList().get(j).isIsGameObjectContainer()){
                            if(gameObjList.get(i).getGameObjList().get(j).getGameObjList().get(0).getObjetcType() == type){
                                isInRoom = true;
                            }
                        }
                    }
                }else{
                    if(gameObjList.get(i).getObjetcType() == type){
                        isInRoom = true;
                        break;
                    }
                }
            }
        } 
        return isInRoom;
    }
    
    public void removeGameObject(GameObject gameObj){
        gameObjList.remove(gameObj);
    }
    
}
