/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.gamefile;

import com.donatotanieli.lavilladinathan.entity.LightRoom;
import com.donatotanieli.lavilladinathan.entity.Player;
import com.donatotanieli.lavilladinathan.entity.Room;
import com.donatotanieli.lavilladinathan.game.Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *Classe che si occupa della gestione del salvataggio e del caricamento della partita
 * @author donatotanieli
 */
public class SaverLoaderClass {
    
    /**
     * Metodo che consente di salvare la partita serializzando l'oggetto Game
     * @param g oggetto da serializzare
     * @param path percorso su cui verrà salvato il file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveFile(Game g, String path) throws FileNotFoundException, IOException{
        
        FileOutputStream fout = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        
        oos.writeObject(g.getPlayer());
        oos.writeObject(g.getCurrentRoom());
        oos.writeObject(g.getLightrooms());
        
        fout.close();
        oos.close();
        
    }
    
    /**
     * Metodo che consente di caricare la partita deserializzando l'oggetto Game
     * @param g oggetto da deserializzare
     * @param path percorso del file selezionato
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void loadFile(Game g, String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fin);
        
        g.setPlayer((Player)in.readObject());
        g.setCurrentRoom((Room)in.readObject());
        g.setLightrooms((ArrayList<LightRoom>)in.readObject());
        
        fin.close();
        in.close();
 
    }
    
}
