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
public class GameObject extends Entity implements Serializable{
    
    private ObjectType objetcType;
    private boolean isTakeable;
    private boolean isOpenable;
    private boolean isOpened;
    private boolean isGameObjectContainer;
    private boolean moveable;
    private boolean visible;
    private int score;
    private List<String> alias;
    private List<GameObject> gameObjList;

    public GameObject() {
        super(null, null, null);
    }

    public GameObject(ObjectType objetcType, boolean isTakeable, boolean isOpenable, boolean isOpened, boolean isGameObjectContainer, boolean moveable, boolean visible, int score, List<String> alias, List<GameObject> gameObjList, String name, String description, String look) {
        super(name, description, look);
        this.objetcType = objetcType;
        this.isTakeable = isTakeable;
        this.isOpenable = isOpenable;
        this.isOpened = isOpened;
        this.isGameObjectContainer = isGameObjectContainer;
        this.moveable = moveable;
        this.visible = visible;
        this.score = score;
        this.alias = alias;
        this.gameObjList = gameObjList;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isIsGameObjectContainer() {
        return isGameObjectContainer;
    }

    public void setIsGameObjectContainer(boolean isGameObjectContainer) {
        this.isGameObjectContainer = isGameObjectContainer;
    }

    public ObjectType getObjetcType() {
        return objetcType;
    }

    public void setObjetcType(ObjectType objetcType) {
        this.objetcType = objetcType;
    }

    public boolean isIsTakeable() {
        return isTakeable;
    }

    public void setIsTakeable(boolean isTakeable) {
        this.isTakeable = isTakeable;
    }

    public boolean isIsOpenable() {
        return isOpenable;
    }

    public void setIsOpenable(boolean isOpenable) {
        this.isOpenable = isOpenable;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public boolean isIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<GameObject> getGameObjList() {
        return gameObjList;
    }

    public void setGameObjList(List<GameObject> gameObjList) {
        this.gameObjList = gameObjList;
    }
    
    
    
    /**
     * Metodo che mi consente di verificare che la parola che l'utente digita corrisponda ad un oggetto del gioco
     * @param obj 
     * @return type che è null se la parola non è un oggetto, un ObjectType altrimenti
     */
    public ObjectType checkObject(String obj){
        ObjectType type = null;
        if(this.getName().equals(obj) || alias.contains(obj)){
            type = this.objetcType;
        }
        return type;
    }
    
    public boolean isGameObject(String obj){
        boolean isGameObj = false;
        if(this.getName().equals(obj) || alias.contains(obj)){
            isGameObj = true;
        }
        return isGameObj;
    }
    
    public boolean isInTheGameObject(ObjectType type){
        boolean isInGameObj = false;
        for(int i = 0; i < gameObjList.size(); i ++){
            if(gameObjList.get(i).getObjetcType() == type){
                isInGameObj = true;
                break;
            }
                
        }
        return isInGameObj;
    }
    
    public void removeGameObject(GameObject go){
        gameObjList.remove(go);
    }

    @Override
    public String toString() {
        return getName(); 
    }
    
    
    
}
