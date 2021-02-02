/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;

/**
 *Claase che rappresenta un'entità generica del gioco che può essere un oggetto o una stanza. Contiene le informazioni principali
 * @author donatotanieli
 */
public class Entity implements Serializable{
    
    private String name;    //nome 
    private String description; //la descrizione dell'entità
    private String look;    //stringa che conterrà la frase quando l'utente vorrà osservare l'entità

    //COSTRUTTORE
    public Entity(String name, String description, String look) {
        this.name = name;
        this.description = description;
        this.look = look;
    }

    //GET E SET
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }
    
    
}
