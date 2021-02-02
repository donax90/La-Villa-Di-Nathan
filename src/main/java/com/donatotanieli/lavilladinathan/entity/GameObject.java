/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;
import java.util.List;


/**
 *Classe che rappresenta l'oggetto del gioco
 * @author donatotanieli
 */
public class GameObject extends Entity implements Serializable{
    
    private ObjectType objetcType; //Il tipo di oggetto
    private boolean isTakeable; //true se l'oggetto è prendibile
    private boolean isOpenable; //true se l'oggetto è apribile
    private boolean isOpened; //true se l'oggetto è aperto
    private boolean isGameObjectContainer; //true se l'oggetto è un contenitore di altri oggetti
    private boolean moveable; //true se l'oggetto si può spostare
    private boolean visible; //true se l'oggetto è visibile
    private int score; //valore dell'oggetto come punteggio
    private List<String> alias; //lista di alias dell'oggetto 
    private List<GameObject> gameObjList; //lista di eventuali oggetti se l'oggetto in questione è un contenitore 

    //COSTRUTTORI 
    
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

    //GET E SET
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
    
    
    //ALTRI METODI
    
    
    /**
     * Metodo che mi consente di verificare che la parola che l'utente digita corrisponda ad un oggetto del gioco e mi restituisce il tipo
     * @param obj parola da verificare
     * @return type che è null se la parola non è un oggetto, un ObjectType altrimenti
     */
    public ObjectType checkObject(String obj){
        ObjectType type = null;
        if(this.getName().equals(obj) || alias.contains(obj)){
            type = this.objetcType;
        }
        return type;
    }
    
    /**
     * Metodo che mi consente di verificare che la parola che l'utente digita corrisponda ad un oggetto del gioco
     * @param obj parola da verificare
     * @return true se l'ggetto è un GameObject, false altrimenti
     */
    public boolean isGameObject(String obj){
        boolean isGameObj = false;
        if(this.getName().equals(obj) || alias.contains(obj)){
            isGameObj = true;
        }
        return isGameObj;
    }
    
    /**
     * Metodo che rimuove l'oggetto go dalla lista
     * @param go oggetto da rimuovere
     */
    public void removeGameObject(GameObject go){
        gameObjList.remove(go);
    }

    /**
     * toString del nome
     * @return 
     */
    @Override
    public String toString() {
        return getName(); 
    }
    
    
    
}
