/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *Classe che rappresenta l'inventario del giocatore
 * @author donatotanieli
 */
public class Inventory implements Serializable{
    
    private List<GameObject> inventoryList; //lista di oggetti dell'inventario

    //COSTRUTTORE
    
    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    //GET E SET
    
    public List<GameObject> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<GameObject> inventoryList) {
        this.inventoryList = inventoryList;
    }
    
    //ALTRI METODI
    
    /**
     * Metodo per aggiungere l'oggetto go all'inventario
     * @param go 
     */
    public void addGameObject(GameObject go){
        inventoryList.add(go);
    }
    
    /**
     * Metodo per rimuovere l'oggetto go dall'inventario
     * @param go 
     */
    public void removeGameObject(GameObject go){
        inventoryList.remove(go);
    }
    
    /**
     * Metodo che ritorna la stringa contente la lista di oggetti presenti nell'inventario
     * @return stringa di oggetti
     */
    public String show(){
   
        String str = "";
        if(inventoryList.isEmpty()){
            str = "Non  nulla con me.";
        }else{
            str = "Ho con me:\n";
            ListIterator<GameObject> lit = inventoryList.listIterator();
            while(lit.hasNext()){
                str += "-" + lit.next() + "\n";
            }
        }
        return str;
    }
    
    /**
     * Metodo che verifica se l'oggetto go è presente nell'inventario
     * @param go oggetto da verificare
     * @return true se l'oggetto c'è, false alltrimenti
     */
    public boolean contains(GameObject go){
        boolean flag = false;
        for( GameObject obj : inventoryList){
            if(obj.getObjetcType() == go.getObjetcType()){
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    /**
     * Metodo che restituisce la posizione dell'oggetto go nell'inventario
     * @param go oggetto da cercare
     * @return -1 se non è presente, la posizione altrimenti
     */
    public int indexOfGame(GameObject go){
        int index = -1;
        for(int i = 0; i < inventoryList.size(); i ++){
            if(inventoryList.get(i).getObjetcType() == go.getObjetcType()){
                index = i;
                break;
            }
        }
        return index;
    }
}
