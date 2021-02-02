/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.game;

import com.donatotanieli.lavilladinathan.entity.Command;
import com.donatotanieli.lavilladinathan.entity.GameObject;
import com.donatotanieli.lavilladinathan.entity.LightRoom;
import com.donatotanieli.lavilladinathan.entity.ObjectType;
import com.donatotanieli.lavilladinathan.entity.Room;
import com.donatotanieli.lavilladinathan.gamefile.SaverLoaderClass;
import com.donatotanieli.lavilladinathan.parser.OutputParser;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *Classe che gestisce la logica del gioca e implementa il metodo astratto della classe astratta GameVN
 * @author donatotanieli
 */
public class GameManager extends GameVN{
    
    public static boolean zombieFlag = false; //flag che si attiva quando ci si trova all cospetto dello zombie, l'interazione cambia
    
    //COSTRUTTORE
    public GameManager(Game g){
        super(g);
    }
    
    /**
     * Implementazione del metodo astratto. Il metodo contiene la logica del gioco e in base all'azione restituisce la stringa di risposta
     * @param output parser che contiene il comando
     * @return stringa da comunicare all'utente
     */
    @Override
    public String executeCommand(OutputParser output){
        
        String outString = "";
        
        Command command = output.getCommand();
        
        //verifico command se è null, in questo caso il parser non ha potuto associare un comando in quanto l'utente ha digitato 
        //una parola che non è stata interpretata
        if(command == null){
            int size = output.getWordsNumber();
            switch(size){
                case 0 :
                    //Se l'utente non ha scritto nulla
                    outString = "Sei troppo occupato per suggerirmi cosa fare?";
                    break;
                    
                case 1 :
                    //Se l'utente ha scritto una parola che non corrisponde ad un comando
                    outString = "Cosa? Che significa?";
                    break;
                    
                case 2 :
                    //Se l'utente ha scritto 2 parole che non corrispondono ad un comando
                    outString = "WTF??...";
                    break;
                    
                case 3 :
                    //Se l'utente ha scritto 3 parole che non corrispondono ad un comando
                    outString = "Mi prendi in giro? Riesci a dire cose sensate?";
                    break;
                    
                case 4 :
                    //Se l'utente ha scritto 4 parole che non corrispondono ad un comando
                    outString = "Una cosa alla volta. Non ti seguo.";
                    break;
                    
                default :
                    //Se ne scrive più di 4
                    outString = "Devi avere qualche problema. E io dovrei seguire uno come te?";
                    break;
            }
        }else{
            
            //L'utente ha inserito un comando corretto
            Room r = getGame().getCurrentRoom();    //Ottengo il riferimento della stanza in cui si trova il giocatore
            
            //In questo if si entra solo quando ci si trova davanti lo zombie nella catacomba
            //Il giocatore ha solo un tentativo per ucciderlo (usare la spada) altrimenti si muore
            if(zombieFlag){
                switch(command){
                    case USE :
                        if(getGame().getPlayer().getInventory().contains(output.getObjList().get(0))
                                && output.getObjList().get(0).getObjetcType() == ObjectType.SWORD){
                            //Ho ucciso lo zombie e setto la lightRoom della catacomba
                            setLightRoom(r);
                            getGame().getPlayer().incrementScore(15);
                            zombieFlag = false;
                        }
                        break;
                        
                    case HELP :
                        outString = showInstructions();
                        break;
                        
                    case I :
                        outString = getGame().getPlayer().getInventory().show();
                        break;
                    default :
                        outString = "Purtroppo non era il comando corretto e sei morto! Lo zombie ti ha squartato!!";
                        getGame().getPlayer().decrementScore();
                        death();
                        break;
                }
            }else{
                //il gioco prosegue normalmente
                switch(command){

                    //Comandi di movimento

                    case N :
                        //verifico che la stanza nord esiste

                        if(getGame().getCurrentRoom().getN() == null){
                            outString = "Non posso andare in questa direzione!";
                        }else{
                            //verifico che la stanza è accessibile
                            if(getGame().getCurrentRoom().getN().isOpened()){

                                //Se la stanza a nord è la catacomba e non sono in possesso di una candela e dei fiammiferi, lo zombie mi uccide
                                if(getGame().getCurrentRoom().getN().getName().equals("CATACOMBA")){
                                    //verifico se ho gli oggetti in questione servendomi di due variabili booleane come appoggio
                                    boolean candle = false;
                                    boolean matches = false;
                                    //cerco la candela
                                    for(GameObject go : getGame().getPlayer().getInventory().getInventoryList()){
                                        if(go.getObjetcType() == ObjectType.CANDLE){
                                            candle = true;
                                            break;
                                        }
                                    }
                                    //cerco i fiammiferi
                                    for(GameObject go : getGame().getPlayer().getInventory().getInventoryList()){
                                        if(go.getObjetcType() == ObjectType.MATCHES){
                                            matches = true;
                                            break;
                                        }
                                    }

                                    if(matches && candle){
                                        //ho soddisfatto i requisiti, aggiorno lo stato del gioco
                                        outString = updateRoom(getGame().getCurrentRoom().getN());
                                    }
                                    else{
                                        //lo zombie mi uccide
                                        outString = "La stanza è completamente al buio. Non vedi un accidente.\n"
                                                + "Improvvisamente senti un rumore. Non fai in tempo a capire da dove proviene che vieni ucciso dallo zombie.";
                                        death();
                                    }
                                }

                                //Se la stanza a nord è il secondo piano allora muori
                                else if(getGame().getCurrentRoom().getN().getName().equals("SECONDO PIANO")){
                                    outString = "Cominci a salire le scale cercando di stare quanto più attento possibile visto che sono malridotte."
                                            + "\nPurtroppo questo non basta, il pavimento ai tuoi piedi cede e tu vieni schiacchiato dai detriti non riuscendo a sopravvivere.";
                                    getGame().getPlayer().decrementScore();
                                    death();
                                }
                                else{
                                    //aggiorno lo stato del gioco
                                    outString = updateRoom(getGame().getCurrentRoom().getN());
                                }

                            }else{

                                //Se mi trovo all'ingresso della villa, l'accesso a nord è bloccato da rovi
                                if(getGame().getCurrentRoom().getName().equals("INGRESSO VILLA")){
                                    outString = "Il cancello è bloccato da rovi molto spessi. Ho bisogno di qualcosa per tagliarli!";
                                }
                                //Se mi trovo nel soggiorno la stanza a nord è bloccata dalla biblioteca
                                else if(getGame().getCurrentRoom().getName().equals("SOGGIORNO")){
                                    outString = "Sembra esserci qualcosa in questa direzione. Peccato ci sia la libreria!";
                                }
                                else{
                                    outString = "L'ingresso è bloccato. Hai bisogno di qualcosa per aprirlo!";
                                }
                            }
                        }
                        break;

                    case S :

                        //verifico che la stanza sud esiste
                        if(getGame().getCurrentRoom().getS() == null){
                            outString = "Non posso andare in questa direzione!";
                        }else{

                            //Verifico se la stanza è accessibile
                            if(getGame().getCurrentRoom().getS().isOpened()){

                                //aggiorno lo stato del gioco
                                outString = updateRoom(getGame().getCurrentRoom().getS());
                            }else{
                                outString = "L'ingresso è bloccato. Hai bisogno di qualcosa per aprirlo!";
                            }
                        }
                        break;

                    case E :

                        //verifico che la stanza est esiste
                        if(getGame().getCurrentRoom().getE() == null){
                            outString = "Non posso andare in questa direzione!";
                        }else{

                            //verifico se è accessibile
                            if(getGame().getCurrentRoom().getE().isOpened()){

                                outString = updateRoom(getGame().getCurrentRoom().getE());

                            }else{

                                //Non è accessibile

                                //se mi trovo in zona est esterno villa...
                                if(getGame().getCurrentRoom().getName().equals("ZONA EST ESTERNO VILLA")){
                                    outString = "Le mura sono troppo alte. Serve qualcosa per scalarle!";
                                }else{
                                    outString = "L'ingresso è bloccato. Hai bisogno di qualcosa per aprirlo!";
                                }

                            }
                        }
                        break;

                    case W :

                        //verifico che la stanza ovest esiste
                        if(getGame().getCurrentRoom().getW() == null){
                            outString = "Non posso andare in questa direzione!";
                        }else{

                            //verifico se è accessibile
                            if(getGame().getCurrentRoom().getW().isOpened()){

                                outString = updateRoom(getGame().getCurrentRoom().getW());
                            }else{

                                //Non è accessibile
                                if(getGame().getCurrentRoom().getName().equals("GIARDINO VILLA")){
                                    outString = "L'ingresso alla grotta è bloccato da un grosso masso. Dovrei spaccarlo con qualcosa per entrare!";
                                }else{
                                    outString = "L'ingresso è bloccato. Hai bisogno di qualcosa per aprirlo!";
                                }
                            }

                        }
                        break;


                    //Comandi di azione

                    case I:
                        outString = getGame().getPlayer().getInventory().show();
                        break;

                    case HELP :
                        outString = showInstructions();
                        break;

                    case OPEN :

                        //con il comando OPEN si può accedere o se gli oggetti sono 2 (Apri porta con chiave) o se è solo uno (Apri armadio)
                        //verifico quindi la dimensione dell'arraylist di outputparser
                        switch(output.getObjList().size()){

                            case 1 :

                                //controllo se la stanza è illuminata
                                if( r instanceof LightRoom){
                                    //controllo se l'oggetto in pos 0 si trova nella stanza
                                    if(verifyObjectsInRoom(output.getObjList().get(0))){
                                        //controllo se è apribile
                                        if(output.getObjList().get(0).isIsOpenable()){                                       
                                            //Prendo il riferimento all'oggetto nella stanza
                                            GameObject gameObj = ((LightRoom)r).getGameObjectFromList(output.getObjList().get(0).getObjetcType());
                                            //controllo se è già aperto
                                            if(gameObj.isIsOpened()){
                                                outString = "L'oggetto " + gameObj.getName() + " è già aperto!";
                                            }else{
                                                //setto a true l'istanza isOpened dell'oggetto
                                                gameObj.setIsOpened(true);
                                                getGame().getPlayer().incrementScore(gameObj.getScore());
                                                if(gameObj.getObjetcType() == ObjectType.WARDROBE){
                                                    outString = "Aperto! Nell' armadio c'è ";
                                                    gameObj.setLook("Un armadio un pò mal andato. E' aperto!");
                                                }else{
                                                    outString = "Aperta! Nella tomba c'è ";
                                                }
                                                //scorro gli oggetti contenuti in gameObj
                                                for( int i = 0; i < gameObj.getGameObjList().size(); i ++){
                                                    outString += gameObj.getGameObjList().get(i).getDescription();
                                                    //setto la visibilità degli oggetti contenuti
                                                    gameObj.getGameObjList().get(i).setVisible(true);
                                                    if( i < gameObj.getGameObjList().size()-1)
                                                        outString += ", ";
                                                }
                                            }
                                        }else{
                                            outString = "Non ho il potere per aprire questo oggetto!";
                                        }
                                    }else{
                                        //l'oggetto non si trova nella stanza
                                        outString = "Mi prendi in giro?Tu vedi quell'oggetto?";
                                    }
                                }else{
                                    //Se la stanza è al buio
                                    outString = "Peccato che non riesco a vedere nulla!!";
                                }
                                break;

                            case 2 :

                                //controllo se l'oggetto in pos 0 è una porta in quanto è stata inserita come oggetto fittizio
                                //per capire se l'utente vuole aprire una porta con un oggetto
                                if(output.getObjList().get(0).getObjetcType() == ObjectType.DOOR){

                                    //verifico che l'oggetto che si sta usando per aprire la porta è presente nell'inventario
                                    if(verifyObjectInInventory(output.getObjList().get(1))){
                                        //Ottengo il riferimento
                                        GameObject obj = getGame().getPlayer().getInventory().getInventoryList().get(getGame().getPlayer().getInventory().indexOfGame(output.getObjList().get(1)));
                                        //Controllo se mi trovo in una stanza adatta a compiere questa azione
                                        outString = "Non posso usare " + obj.getName() + " per aprire questa porta";
                                        if(getGame().getCurrentRoom().getName().equals("ZONA OVEST ESTERNO VILLA")){
                                            if(obj.getObjetcType() == ObjectType.KEY ){
                                                outString = unlockedDoor(getGame().getCurrentRoom().getW());
                                                getGame().getPlayer().incrementScore(2);
                                                //Rimuovo l'oggetto dall'inventario
                                                getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                            }
                                        }else if(getGame().getCurrentRoom().getName().equals("INGRESSO VILLA")){
                                            if(obj.getObjetcType() == ObjectType.MACHETE){
                                                outString = unlockedDoor(getGame().getCurrentRoom().getN());
                                                getGame().getPlayer().incrementScore(2);
                                                //Rimuovo l'oggetto dall'inventario
                                                getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                            }
                                        }else if(getGame().getCurrentRoom().getName().equals("GIARDINO VILLA")){
                                            if(obj.getObjetcType() == ObjectType.PICKLOCK){
                                                outString = unlockedDoor(getGame().getCurrentRoom().getN());
                                                getGame().getPlayer().incrementScore(2);
                                                //Rimuovo l'oggetto dall'inventario
                                                getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                            }else if(obj.getObjetcType() == ObjectType.HUMMER){
                                                outString = unlockedDoor(getGame().getCurrentRoom().getW());
                                                getGame().getPlayer().incrementScore(3);
                                                //Rimuovo l'oggetto dall'inventario
                                                getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                            }
                                        }else if(getGame().getCurrentRoom().getName().equals("ATRIO SUD")){
                                            if(obj.getObjetcType() == ObjectType.CAVACHIODI){
                                                outString = unlockedDoor(getGame().getCurrentRoom().getW());
                                                getGame().getPlayer().incrementScore(3);
                                                //Rimuovo l'oggetto dall'inventario
                                                getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                            }
                                        }else if(getGame().getCurrentRoom().getName().equals("INGRESSO CATACOMBA")){
                                            if(obj.getObjetcType() == ObjectType.BONE){
                                                outString = unlockedDoor(getGame().getCurrentRoom().getN());
                                                getGame().getPlayer().incrementScore(8);
                                                //Rimuovo l'oggetto dall'inventario
                                                getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                            }else{
                                                outString = "Provo ad inserire " + obj.getName() + " nel foro ma non succede nulla.\n"
                                                        + "Una botola sotto di te si apre facendoti precipitare. Finisci infilzato dalle lame e muori!";
                                                getGame().getPlayer().decrementScore();
                                                death();
                                            }
                                        }else{
                                            outString = "Qui non c'è nessuna porta da aprire!";
                                        }
                                    }else{
                                        //Non è presente nell'inventario
                                        outString = "Non possiedo quell'oggetto";
                                    }
                                }else{
                                    //L'utente può anche aver scritto apri albero con machete
                                    outString = "Non posso fare quello che hai scritto!!";
                                }
                                break;

                            default :
                                outString = "Non c'è nulla da aprire!";

                        }

                        break;

                    case TAKE :
                        //Verifica se la lista di oggetti in output parser è vuota
                        if(output.getObjList().isEmpty()){
                            outString = "Prendilo tu!";
                        }else{
                            //Verifico se la stanza è illuminata
                            if(r instanceof LightRoom){
                                //Verifico se l'oggetto è presente nella stanza
                                if(verifyObjectsInRoom(output.getObjList().get(0))){
                                    //Ottengo il riferimento all'oggetto della stanza
                                    GameObject gameObj = ((LightRoom)r).getGameObjectFromList(output.getObjList().get(0).getObjetcType());
                                    //Verifico se l'oggetto si può prendere
                                    if(!gameObj.isIsTakeable()){
                                        outString = "Non posso portare l'oggetto " + gameObj.getName() + " con me.";
                                    }else{
                                        //Verifico che non sia il portafogli altrimenti si muore
                                        if(gameObj.getObjetcType() == ObjectType.WALLET){
                                            outString = "La tua curiosità ti ha giocato un brutto scherzo! Mentre ti chini a prenderlo, una folata di vento ti fa perdere l'equilibrio"
                                                    + " e cadi giu dalle mura facendo un volo di 10 metri! Non sopravvivi all'impatto";
                                            getGame().getPlayer().decrementScore();
                                            death();
                                        }else if(gameObj.getObjetcType() == ObjectType.RING){
                                            //Se è l'anello allora il gioco termina e hai vinto
                                            Game.winner = true;
                                        }else{
                                            //Aggiungo l'oggetto nell'inventario
                                            getGame().getPlayer().getInventory().addGameObject(gameObj);
                                            //Aumento il punteggio del giocatore
                                            getGame().getPlayer().incrementScore(gameObj.getScore());
                                            //Rimuovo l'oggetto dalla stanza
                                            ((LightRoom) r).removeGameObject(gameObj);
                                            outString = "Fatto.";
                                        }
                                    }  
                                }
                                //Verifico se l'oggetto è presente in un oggetto della stanza
                                else{
                                    boolean founded = false; //booleano che sarà true se l'oggetto è stato trovato
                                    //Scorro gli oggetti della stanza e verifico se l'oggetto analizzato è un oggetto contenitore
                                    for(GameObject gObj : ((LightRoom)r).getGameObjList()){
                                        if(gObj.isIsGameObjectContainer()){
                                            //Se lo è verifico se l'oggetto è aperto
                                            if(gObj.isIsOpened()){
                                                //Se lo è scorro gli oggetti contenuti in gObj e li confronto con l'oggetto
                                                for( GameObject g : gObj.getGameObjList()){
                                                    if(g.getObjetcType() == output.getObjList().get(0).getObjetcType()){
                                                        //L'ho trovato
                                                        founded = true;
                                                        //Verifico se è prendibile
                                                        if(g.isIsTakeable()){                                               
                                                            //Aggiungo l'oggetto nell'inventario
                                                            getGame().getPlayer().getInventory().addGameObject(g);
                                                            //Aumento il punteggio del giocatore
                                                            getGame().getPlayer().incrementScore(g.getScore());
                                                            //Rimuovo l'oggetto dall'oggetto contenitore
                                                            gObj.removeGameObject(g);
                                                            outString = "Fatto.";
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if(!founded){
                                        outString = "Non vedo niente di tutto ciò!";
                                    }      
                                }
                            }else{
                                //la stanza non è illuminata
                                outString = "Come faccio a vedere se ci sta " + output.getObjList().get(0) + " se non ci vedo nulla!!!";
                            }
                        }
                        break;

                    case SHOW :
                        outString = r.getLook();
                        break;

                    case EXAMINE :
                        //verifico se la lista di oggetti nel parser è vuota o meno
                        if(output.getObjList().isEmpty()){
                            outString = "Non credo proprio di poterlo fare!";
                        }else{
                            //verifico se l'oggetto da esaminare è presente nell'inventario
                            if(verifyObjectInInventory(output.getObjList().get(0))){
                                outString = output.getObjList().get(0).getLook();
                            }
                            //Altrimenti verifico se l'oggetto da esaminare si trova nella stanza
                            else{
                                //verifico se la stanza è illuminata
                                if(r instanceof LightRoom){
                                    if(verifyObjectsInRoom(output.getObjList().get(0))){
                                        //Controllo se l'oggetto da esaminare è un oggetto la cui azione causa delle modifiche
                                        switch(output.getObjList().get(0).getObjetcType()){
                                            case STREAM :
                                                outString = "Ti lasci quasi incantare dal rumore dell'acqua che scorre, come se fosse una melodia ipnotica.\n"
                                                        + "La sensazione è troppo bella, tanto che ti getti. Purtroppo però, la corrente è troppo forte, muori per annegamento.";
                                                getGame().getPlayer().decrementScore();
                                                death();
                                                break;

                                            case WELL : 
                                                outString = "Il pozzo è mal ridotto, non c'è nulla esternamente.\n"
                                                        + "Così provi a vedere se almeno all'interno puoi scorgere qualcosa, ma il mattoncino sul quale ti eri\n"
                                                        + "appoggiato per sporgerti cede facendoti cadere dentro. Non sopravvivi alla caduta!";
                                                getGame().getPlayer().decrementScore();
                                                death();
                                                break;

                                            case MIRROR : 
                                                outString = "Sei proprio tu, con quella faccia piena di ferite per la caduta di prima.\n"
                                                        + "\"Appena tutto sarà finito mi darò una sistemata!\" pensi. Improvvisamente vedi una figura alle tue spalle,\n"
                                                        + "non riesci ad urlare poiché vieni ucciso all'istante.";
                                                getGame().getPlayer().decrementScore();
                                                death();
                                                break;

                                            case TREE :
                                                //Verifico se sia la prima volta che l'utente osserva l'albero (chiave si trova nell'albero)
                                                GameObject tree = ((LightRoom)r).getGameObjectFromList(ObjectType.TREE);
                                                if(tree.getGameObjList() == null){
                                                    //La chiave è stata tolta dall'albero
                                                    outString = tree.getLook();
                                                }else{
                                                    //Si sta osservando per la prima volta e la chiave si trova in albero
                                                    outString = tree.getLook();
                                                    //Rivelo la visibilità della chiave
                                                    tree.getGameObjList().get(0).setVisible(true);
                                                    //Sposto la chiave nella stanza
                                                    ((LightRoom)r).getGameObjList().add(tree.getGameObjList().get(0));
                                                    //Incremento il punteggio del giocatore
                                                    getGame().getPlayer().incrementScore(2);
                                                    //Rimuovo la chiave dall'albero
                                                    tree.setGameObjList(null);
                                                    //Setto il nuovo look dell'albero nel caso in cui l'utente decida di osservarlo nuovamente
                                                    tree.setLook("Ha un tronco enorme e la chioma molto folta.");
                                                }
                                                break;


                                            case CANDLESTICK : 
                                                //Il candelabro contiene 2 candele, ma ne viene rivelata solo una se lo si osserva
                                                //Verifico quante candele ci sono
                                                GameObject candlestick = ((LightRoom)r).getGameObjectFromList(ObjectType.CANDLESTICK);//Riferimento all'oggetto candelabro
                                                if(candlestick.getGameObjList() != null){
                                                    if(candlestick.getGameObjList().size() == 1){
                                                        //La candela è una
                                                        outString = candlestick.getLook();//C'è una candela su questo candelabro.
                                                        //Rivelo la candela
                                                        candlestick.getGameObjList().get(0).setVisible(true);
                                                        //Sposto la candela nella stanza
                                                        ((LightRoom)r).getGameObjList().add(candlestick.getGameObjList().get(0));
                                                        //Incremento il punteggio del giocatore
                                                        getGame().getPlayer().incrementScore(4);
                                                        //Rimuovo l'ultima candela dal candelabro
                                                        candlestick.setGameObjList(null);
                                                        //Setto il nuovo look del candelabro
                                                        candlestick.setLook("Non ci sono più candele su questo candelabro.");

                                                    }else{
                                                        //Le candele sono 2
                                                        outString = candlestick.getLook();//Ci sono delle candele su questo candelabro.
                                                        //Rivelo una delle due candele
                                                        candlestick.getGameObjList().get(0).setVisible(true);
                                                        //Sposto la candela nella stanza
                                                        ((LightRoom)r).getGameObjList().add(candlestick.getGameObjList().get(0));
                                                        //Incremento il punteggio del giocatore
                                                        getGame().getPlayer().incrementScore();
                                                        //Rimuovo la candela dal candelabro
                                                        candlestick.getGameObjList().remove(candlestick.getGameObjList().get(0));
                                                        //Setto il nuovo look del candelabro
                                                        candlestick.setLook("C'è una candela su questo candelabro.");
                                                    }
                                                }else{
                                                    //Se è null vuol dire che è stato svuotato di tutte le candele
                                                    outString = candlestick.getLook();//Non ci sono più candele su questo candelabro.
                                                }
                                                break;

                                            case TABLE :
                                                //Verifico se sia la prima volta che l'utente osserva il tavolo (fiammiferi si trovano sul tavolo)
                                                GameObject table = ((LightRoom)r).getGameObjectFromList(ObjectType.TABLE);
                                                if(table.getGameObjList() == null){
                                                    //I fiammiferi sono stati già raccolti 
                                                    outString = table.getLook();
                                                }else{
                                                    //Si sta osservando per la prima volta e i fiammiferi si trovano sul tavolo
                                                    outString = table.getLook();
                                                    //Rivelo la visibilità dei fiammiferi
                                                    table.getGameObjList().get(0).setVisible(true);
                                                    //Sposto i fiammiferi nella stanza
                                                    ((LightRoom)r).getGameObjList().add(table.getGameObjList().get(0));
                                                    //Incremento il punteggio del giocatore
                                                    getGame().getPlayer().incrementScore(2);
                                                    //Rimuovo i fiammiferi dal tavolo
                                                    table.setGameObjList(null);
                                                    //Setto il nuovo look del tavolo nel caso in cui l'utente decida di osservarlo nuovamente
                                                    table.setLook("Il tavolo è pieno di polvere.");
                                                }
                                                break;

                                            default:
                                                //Scorro gli oggetti della stanza e li confronto con l'oggetto in outputparser
                                                for(GameObject gameObj : ((LightRoom)r).getGameObjList()){
                                                    if(gameObj.getObjetcType() == output.getObjList().get(0).getObjetcType()){
                                                        outString = gameObj.getLook();
                                                        break;
                                                    }
                                                }
                                                break;        
                                        }
                                    }else{
                                        //Se l'oggetto non è presente nella stanza verifico se nel caso è presente in un oggetto della stanza
                                        boolean founded = false; //booleano che sarà true se l'oggetto è stato trovato
                                        //Scorro gli oggetti della stanza e verifico se l'oggetto analizzato è un oggetto contenitore
                                        for(GameObject gObj : ((LightRoom)r).getGameObjList()){
                                            if(gObj.isIsGameObjectContainer()){
                                                //Se lo è verifico se l'oggetto è aperto
                                                if(gObj.isIsOpened()){
                                                    //Se lo è scorro gli oggetti contenuti in gObj e li confronto con l'oggetto
                                                    for( GameObject g : gObj.getGameObjList()){
                                                        if(g.getObjetcType() == output.getObjList().get(0).getObjetcType()){
                                                            //L'ho trovato
                                                            founded = true;
                                                            if(g.getObjetcType() == ObjectType.SKULL){
                                                                //Verifico se sia la prima volta che l'utente osserva lo scheletro (osso si trova nello scheletro)
                                                                if(g.getGameObjList() == null){
                                                                    //L'osso è stato tolto dallo scheletro
                                                                    outString = g.getLook();
                                                                }else{
                                                                    //Si sta osservando per la prima volta e l'osso si trova nello scheletro
                                                                    outString = g.getLook();
                                                                    //Rivelo la visibilità dell'osso
                                                                    g.getGameObjList().get(0).setVisible(true);
                                                                    //Incremento il punteggio del giocatore
                                                                    getGame().getPlayer().incrementScore(2);
                                                                    //Sposto l'osso nell'armadio
                                                                    gObj.getGameObjList().add(g.getGameObjList().get(0));
                                                                    //Rimuovo l'osso dallo scheletro
                                                                    g.setGameObjList(null);
                                                                    //Setto il nuovo look dello scheletro nel caso in cui l'utente decida di osservarlo nuovamente
                                                                    g.setLook("E' sempre lo stesso mucchio di ossa.");
                                                                    break;
                                                                }
                                                            }else{   
                                                                outString = g.getLook();
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        if(!founded){
                                            outString = "Non vedo " + output.getObjList().get(0).getName() + " da nessuna parte.";
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case TURN_ON :
                        //Gli oggetti da esaminare sono 2 e devono essere presenti nell'inventario
                        if(getGame().getPlayer().getInventory().contains(output.getObjList().get(0)) && 
                                getGame().getPlayer().getInventory().contains(output.getObjList().get(1))){
                            //Riferimento dell'oggetto 1
                            GameObject obj1 = getGame().getPlayer().getInventory().getInventoryList().get(getGame().getPlayer().getInventory().indexOfGame(output.getObjList().get(0)));
                            //Riferimento dell'oggetto 2
                            GameObject obj2 = getGame().getPlayer().getInventory().getInventoryList().get(getGame().getPlayer().getInventory().indexOfGame(output.getObjList().get(1)));
                            //verifico se l'utente esegue nel modo corretto il comando
                            if(obj1.getObjetcType() == ObjectType.CANDLE && obj2.getObjetcType() == ObjectType.MATCHES){
                                //verifico se la stanza corrente è una LightRoom (illuminata)
                                if( r instanceof LightRoom){
                                    //Se lo è illuminarla non è servito a nulla
                                    outString = "Fatto, ma non credo sia servito. Ci vedevo già benissimo prima!";
                                }else{
                                    //In questo gioco se mi trovo nella catacomba spunta lo zombie e ho un tentativo per ucciderlo
                                    if(r.getName().equals("CATACOMBA")){
                                        outString = "La scena è inquietante, vedo la bara dritta davanti a me. \"Oh, merda!\" La bara si apre "
                                                + "e spunta fuori Nathan in persona! Anzi lo zombie di Nathan! Hai un tentativo per salvarti prima "
                                                + "che il mostro ti uccida!Quale oggetto vuoi usare? Scrivi il comando correttamente.";
                                        zombieFlag = true;
                                        //Incremento il punteggio del giocatore
                                        getGame().getPlayer().incrementScore(2);
                                    }else{
                                        //Se non è illuminata setto la LightRoom
                                        setLightRoom(r);
                                        //Incremento il punteggio del giocatore
                                        getGame().getPlayer().incrementScore();
                                        outString = "Fatto. Adesso riesco a vedere qualcosa";
                                    }
                                }
                                //rimuovo la candela perché non più utilizzabile, l'eventuale stanza illuminata mantiene questo stato fino alla fine del gioco
                                getGame().getPlayer().getInventory().removeGameObject(obj1);
                                outString += "\nL'oggetto candela è stato rimosso dall'inventario perché non più utilizzabile!"; 
                            }
                            else if(obj1.getObjetcType() == ObjectType.LANTERN && obj2.getObjetcType() == ObjectType.LIGHTER){
                                //verifico se la stanza corrente è una LightRoom (illuminata)
                                if( r instanceof LightRoom){
                                    //Se lo è illuminarla non è servito a nulla
                                    outString = "Fatto, ma non credo sia servito. Ci vedevo già benissimo prima!";
                                }else{
                                    //Se non è illuminata setto la LightRoom
                                    setLightRoom(r);
                                    //Incremento il punteggio del giocatore
                                    getGame().getPlayer().incrementScore();
                                }
                                //rimuovo la lanterna e l'accendino perché non più utilizzabili
                                getGame().getPlayer().getInventory().removeGameObject(obj1);
                                getGame().getPlayer().getInventory().removeGameObject(obj2);
                                outString += "\nL'oggetto lanterna e accendino sono stati rimossi dall'inventario perché non più utilizzabili";
                            }
                        }
                        else{
                            outString = "Non possiedo questi oggetti.";
                        }

                        break;

                    case MOVE :
                        //Controllo se c'è almeno un oggetto nel outputparser
                        if(output.getObjList().isEmpty()){
                            outString = "Non trovo quell'oggetto!";
                        }else{
                            //Controllo se l'oggetto è nella stanza
                            if(verifyObjectsInRoom(output.getObjList().get(0))){
                                GameObject obj = ((LightRoom)r).getGameObjectFromList(output.getObjList().get(0).getObjetcType());//Riferimento all'oggetto
                                //Controllo se è spostabile
                                if(obj.isMoveable()){
                                    //In questo gioco gli unici oggetti spostabili sono il letto e la biblioteca, verifico quale di questi si tratta
                                    if(obj.getObjetcType() == ObjectType.BED){
                                        //Rivelo la spada
                                        obj.getGameObjList().get(0).setVisible(true);
                                        //Aggiungo la spada nella stanza
                                        ((LightRoom)r).getGameObjList().add(obj.getGameObjList().get(0));
                                        //Incremento il punteggio del giocatore
                                        getGame().getPlayer().incrementScore(2);
                                        //Rimuovo la spada dal letto
                                        obj.getGameObjList().remove(obj.getGameObjList().get(0));
                                        outString = "Da sotto al letto compare una spada.";
                                    }else if(obj.getObjetcType() == ObjectType.LIBRARY){
                                        //Rivelo la porta a nord
                                        r.getN().setUnlockedBy(null);
                                        //Incremento il punteggio del giocatore
                                        getGame().getPlayer().incrementScore(2);
                                        outString = "Ottima intuizione! C'era proprio un ingresso dietro questa libreria!!!";                               
                                    }
                                }else{
                                    outString = "Ritenta, sarai più fortunato!";
                                }
                            }else{
                                outString = "Ma dove ce li hai gli occhi!";
                            }
                        }
                        break;

                    case USE : 
                        //Verifico la lista di outputparser che non sia vuota
                        if(output.getObjList().isEmpty()){
                            outString = "Io non uso quella cosa!";
                        }else{
                            //Verifico se l'oggetto è nella stanza
                            if(verifyObjectsInRoom(output.getObjList().get(0))){
                                //In questo gioco l'unico oggetto usabile nella stanza è l'auto. Tra l'altro se si usa si muore
                                if(((LightRoom)r).getGameObjectFromList(output.getObjList().get(0).getObjetcType()).getObjetcType() == ObjectType.CAR){
                                    outString = "Con totale sicurezza metti in moto l'auto e cominci ad andartene.\n"
                                            + "Percorri qualche centinaia di metri fuori dalla villa e incontri un'ostacolo che ti costringe a premere\n"
                                            + " il freno. La macchina esplode e tu non sopravvivi all'incidente";
                                    getGame().getPlayer().decrementScore();
                                    death();
                                }else{
                                    outString = "Non posso usare questo oggetto!";
                                }
                            }else if(verifyObjectInInventory(output.getObjList().get(0))){
                                //In questo gioco gli oggetti usabili dall'inventario sono il rampino e la spada, 
                                //devi trovarti nella zona est esterno villa per il rampino e nella catacomba per la spada
                                GameObject obj = getGame().getPlayer().getInventory().getInventoryList().get(getGame().getPlayer().getInventory().indexOfGame(output.getObjList().get(0)));
                                if(obj.getObjetcType() == ObjectType.HOOK){
                                    if(r.getName().equals("ZONA EST ESTERNO VILLA")){
                                        //Sblocco le mura
                                        r.getE().setUnlockedBy(null);
                                        //Rimuovo il rampino dall'inventario
                                        getGame().getPlayer().getInventory().getInventoryList().remove(obj);
                                        getGame().getPlayer().incrementScore(3);
                                        outString = "Sembra che regga. Posso accedere ora alle mura ad est!";
                                    }else{
                                        outString = "Tu vedi qualcosa da scalare?Non posso certamente usarlo qui!";
                                    }
                                }else{
                                    outString = "Non posso usare questo oggetto!";
                                }

                            }else{
                                outString = "Non trovo niente di quello che hai scritto qui!";
                            }
                        }
                        break;

                    case EXIT :
                        int result = JOptionPane.showConfirmDialog(null, "Non puoi abbandonarmi proprio ora!!!Resterò qui per sempre!!Vuoi davvero andartene?", null, JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION){
                            death();
                        }else{
                            outString = "Mi hai fatto prendere un accidenti!!!";
                        }
                        
                        break;

                    case READ :
                        //Verifico se c'è almeno un oggetto nell'outputparser
                        if(output.getObjList().isEmpty()){
                            outString = "Non c'è nulla da leggere!";
                        }else{
                            //verifico se l'oggetto è nella stanza
                            if(verifyObjectsInRoom(output.getObjList().get(0))){
                                GameObject gameRead = ((LightRoom)r).getGameObjectFromList(output.getObjList().get(0).getObjetcType());
                                //verifico quale cartello è
                                if(gameRead.getObjetcType() == ObjectType.SIGNBOARD){
                                    outString = "Benvenuti nella villa del conte Nathan!\nSe come tanti altri siete alla ricerca del suo tesoro allora solo un consiglio.\nAndatevene subito! La villa è piena di trabocchetti!";
                                }else if(gameRead.getObjetcType() == ObjectType.SIGN){
                                    outString = "Qui è dove è stato sepolto segretamente il conte Nathan.";
                                }else if(gameRead.getObjetcType() == ObjectType.MISSIVE){
                                    outString = "A te che leggi questo biglietto,\nse come me sei venuto qui alla ricerca del tesoro di Nathan\n"
                                            + "allora ti consiglio di andartene! In questa villa si aggira uno zombie, sono stato colpito!\n"
                                            + "Non sono riuscito ad ucciderlo. Sono in fin di vita, non credo di sopravvivere!\n"
                                            + "Scappa finché puoi!";
                                }else{
                                    outString = "Questo oggetto non si può leggere!";
                                }
                            }//verifico se l'oggetto è nell'inventario( l'unico è solo la lettera)
                            else if(verifyObjectInInventory(output.getObjList().get(0))){
                                if(output.getObjList().get(0).getObjetcType() == ObjectType.MISSIVE){
                                    outString = "A te che leggi questo biglietto,\nse come me sei venuto qui alla ricerca del tesoro di Nathan\n"
                                                + "allora ti consiglio di andartene! In questa villa si aggira uno zombie, sono stato colpito!\n"
                                                + "Non sono riuscito ad ucciderlo. Sono in fin di vita, non credo di sopravvivere!\n"
                                                + "Scappa finché puoi!";
                                }else{
                                    outString = "Non posso leggere l'oggetto " + output.getObjList().get(0).getName();
                                }
                            }
                            else{
                                //Verifico se l'oggetto è contenuto in un altro oggetto
                                boolean founded = false; //booleano che sarà true se l'oggetto è stato trovato
                                //Scorro gli oggetti della stanza e verifico se l'oggetto analizzato è un oggetto contenitore
                                for(GameObject gObj : ((LightRoom)r).getGameObjList()){
                                    if(gObj.isIsGameObjectContainer()){
                                        //Se lo è verifico se l'oggetto è aperto
                                        if(gObj.isIsOpened()){
                                            //Se lo è scorro gli oggetti contenuti in gObj e li confronto con l'oggetto
                                            for( GameObject g : gObj.getGameObjList()){
                                                if(g.getObjetcType() == output.getObjList().get(0).getObjetcType()){
                                                    //L'ho trovato
                                                    founded = true;
                                                    //Verifico se è la lettera
                                                    if(g.getObjetcType() == ObjectType.MISSIVE){                                               
                                                        outString = "A te che leggi questo biglietto,\nse come me sei venuto qui alla ricerca del tesoro di Nathan\n"
                                                        + "allora ti consiglio di andartene! In questa villa si aggira uno zombie, sono stato colpito!\n"
                                                        + "Non sono riuscito ad ucciderlo. Sono in fin di vita, non credo di sopravvivere!\n"
                                                        + "Scappa finché puoi!";                                                
                                                        break;
                                                    }else{
                                                        outString = "L'oggetto non si può leggere!";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                if(!founded){
                                    outString = "Non vedo niente di tutto ciò!";
                                }      
                            }
                        }
                        break;

                    case SAVE :
                        JFileChooser fc = new JFileChooser();
                        fc.setCurrentDirectory(new File(".//SaveFolder")); // viene visualizzata la cartella inserita nel percorso
                            try {
                                    if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                                        String path = fc.getSelectedFile().getPath(); //Stringa che contiene il persorso del file
                                        //Invoco il metodo saveFile per salvare il gioco passando il percorso e l'oggetto game
                                        SaverLoaderClass sc = new SaverLoaderClass();
                                        sc.saveFile(getGame(), path);
                                        outString = "Gioco salvato!";
                                    }else{
                                        outString = "Non è stato possibile salvare il gioco!";
                                    }
                                } catch (HeadlessException | IOException e) {
                                    JOptionPane.showMessageDialog(null, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
                                }
                                
                        break;


                    default : 
                        break;
                }
            }
            
        }
        
        return outString;
    }
    
    /**
     * Metodo che consente di settare il player nella stanza r
     * @param r stanza in cui il player si troverà
     * @return dopo aver settato il player viene ritornata la stringa contentente il nome della stanza e la descrizione
     */
    private String updateRoom(Room r){
        getGame().setCurrentRoom(r);
        return getGame().getCurrentRoom().getName() + "\n\n" + getGame().getCurrentRoom().getDescription();
    }
    
    /**
     * Metodo che setta a null il campo unlockedby della stanza: Vuol dire che un accesso che prima era bloccato ora è accessibile
     * @param r stanza da sbloccare
     * @return Stringa di risposta
     */
    private String unlockedDoor(Room r){
        r.setUnlockedBy(null);
        return("Fatto. Ora si può accedere!");
    }
    
    /**
     * Metodo che crea una stringa per mostrare le istruzioni del gioco
     * @return stringa contenente le istruzioni
     */
    public String showInstructions(){
        return("Usa i seguenti comandi per giocare:\n" +
                "DIREZIONI:\n" +
                "-\" n, s, e, o \" oppure \"nord, sud, est, ovest\" per muoverti nella direzione specificata.\n" +
                "AZIONI:\n" +
                "-\"prendi + oggetto\" per raccogliere un oggetto;\n" +
                "-\"usa + oggetto\" per usare un oggetto in tuo possesso;\n" +
                "-\"osserva o guarda\" per osservare la stanza o il luogo alla ricerca di più dettagli;\n" +
                "-\"osserva o guarda o esamina + oggetto\" per osservare l'oggetto;\n" +
                "-\"apri + oggetto\" per aprire un oggetto;\n" +
                "-\"apri porta con + oggetto\" per aprire una porta tramite un oggetto;\n" +
                "-\"leggi + oggetto\" per leggere;\n" +
                "-\"sposta + oggetto\" per spostare un oggetto;\n" +
                "-\"accendi + oggetto con + oggetto\" per accendere un oggetto;\n"+
                "-\"salva\" per salvare la partita;\n" +
                "-\"i o inventario\" per aprire l'inventario degli oggetti in tuo possesso;\n"+
                "-\"esci o abbandona\" per uscire dal gioco senza salvare. Corrisponde ad arrendersi;\n" + 
                "-\"info\" per aprire le istruzioni da riga di comando.");
    }
    
    /**
     * Metodo che consente di sostituire la stanza buia con quella illuminata
     * @param r stanza da illuminare
     */
    private void setLightRoom(Room r){
        
        ArrayList<LightRoom> lightrooms = getGame().getLightrooms();
        for(LightRoom lRoom : lightrooms){
            if(r.getName().equals(lRoom.getName())){
                //In questo gioco le lightroom sono 3. Grotta, Cantina, Catacomba
                //Se sono nella grotta esiste solo la direzione est
                //Se sono nella cantina esiste solo la direzione ovest
                //Se sono nella catacomba esiste solo la direzione sud
               if(r.getE()!= null){
                   lRoom.setE(r.getE());
                   r.getE().setW(lRoom);
               }else if(r.getW() != null){
                   lRoom.setW(r.getW());
                   r.getW().setE(lRoom);
               }else if(r.getS() != null){
                   lRoom.setS(r.getS());
                   r.getS().setN(lRoom);
               }
               getGame().setCurrentRoom(lRoom);
               break;
            }      
        }
    }
    
    /**
     * Questo metodo permette di verificare se l'oggetto è presente o meno nella stanza
     * @param gameObject oggetto da verificare
     * @return false se non è presente, true se è nella stanza
     */
    private boolean verifyObjectsInRoom(GameObject gameObject){
        boolean flag = false;
        //Affinché il controllo vada a buon fine gameObject non deve essere null
        if(gameObject != null){
            //CONTROLLO LA STANZA PRIMA
            if(getGame().getCurrentRoom() instanceof LightRoom){
                //Scorro gli oggetti della stanza e li confronto con gameObject
                for( GameObject g : ((LightRoom)getGame().getCurrentRoom()).getGameObjList()){
                    if(gameObject.getObjetcType() == g.getObjetcType()){
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    
    /**
     * Questo metodo permette di verificare se l'oggetto è presente o meno nell'inventario
     * @param gameObject oggetto da verificare
     * @return true se l'oggetto è presente, false altrimenti
     */
    private boolean verifyObjectInInventory(GameObject gameObject){
        boolean flag = false;
        //Affinché il controllo vada a buon fine gameObject non deve essere null
        if(gameObject != null){
            //CONTROLLO L'INVENTARIO
            //Scorro gli oggetti dell'inventario e li confronto con gameObject
            for(GameObject g : getGame().getPlayer().getInventory().getInventoryList()){
                if(gameObject.getObjetcType() == g.getObjetcType()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }  
    
    /**
     * Metodo che setta la variabile statica alive a false. Sta ad indicare che il giocatore è morto
     */
    private void death(){
        Game.alive = false;   
    }
    
}
