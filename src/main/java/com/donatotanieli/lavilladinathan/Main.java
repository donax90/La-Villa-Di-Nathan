/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan;

import com.donatotanieli.lavilladinathan.gui.MenuGUI;
import java.sql.SQLException;

/**
 *Classe Main che fa partire l'interfaccia del menù di gioco
 * @author donatotanieli
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        //Viene caricata e visualizzata l'interfaccia del gioco
        MenuGUI menu = new MenuGUI();
        menu.setVisible(true);
    }
}
