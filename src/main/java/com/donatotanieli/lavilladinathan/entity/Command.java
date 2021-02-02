/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.entity;

import java.io.Serializable;

/**
 *Enumerazione che contiene i possibili valori che il comando può assumere
 * @author donatotanieli
 */
public enum Command implements Serializable{
    N, //nord
    S, //sud
    E, //est
    W, //ovest
    I, //inventario
    OPEN, //apri
    TAKE, //prendi
    SHOW, //osserva
    EXAMINE, //esamina
    HELP, //info
    EXIT, //esci o abbandona
    SAVE, //salva
    READ, //leggi
    TURN_ON, //accendi
    MOVE, //sposta
    USE //usa
    
}
