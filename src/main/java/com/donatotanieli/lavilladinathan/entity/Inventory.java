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
 *
 * @author donatotanieli
 */
public class Inventory implements Serializable{
    
    private List<GameObject> inventoryList;

    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    public List<GameObject> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<GameObject> inventoryList) {
        this.inventoryList = inventoryList;
    }
    
    public void addGameObject(GameObject go){
        inventoryList.add(go);
    }
    
    public void removeGameObject(GameObject go){
        inventoryList.remove(go);
    }
    
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
