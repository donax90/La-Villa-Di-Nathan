/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.parser;

import com.donatotanieli.lavilladinathan.entity.Command;
import com.donatotanieli.lavilladinathan.entity.GameObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *Classe che analizza l'input dell'utente e ne codifica le informazioni salvandole in un oggetto OutputParser
 * @author donatotanieli
 */
public class Parser {
    
    private OutputParser output;

    //COSTRUTTORE
    public Parser() {
        output = new OutputParser();
    }
    
    //METODI
    
    /**
     * Metodo che riceve in ingresso la lista degli oggetti del gioco e la stringa che l'utente ha digitato
     * @param input
     * @param objList
     * @return output di tipo OutputParser che contiene il comando e la lista degli oggetti
     */
    public OutputParser parseInput(String input, ArrayList<GameObject> objList){
        ArrayList<String> wordlist = wordListInput(input); //Construisco l'array di parole scritte dall'utente
        parse(wordlist, objList); //invoco il metodo che consentirà di interpretare le parole dell'utente
        return output;
    }
    
    /**
	 * Metodo che permette di scomporre la frase input digitata dall'utente.
	 * @param input
	 * @return wordlist, ossia la lista di parole digitata dall'utente
	 */
    public ArrayList<String> wordListInput(String input) {
        ArrayList<String> wordList = new ArrayList<>();
        String delims = " \t,.:;?!\"'";
        StringTokenizer stkz = new StringTokenizer(input, delims);
        while(stkz.hasMoreElements()) {
                wordList.add(stkz.nextToken());
        }
        return wordList;
    }
     
    /**
     * Metodo che controlla la dimensione dell'arrayList per invocare il metodo opportuno in base al numero di parole
     * @param wordlist
     * @param objList 
     */
    public void parse(ArrayList<String> wordlist, ArrayList<GameObject> objList) {

        //Se arraylist è vuoto, ovvero l'utente non ha scritto nulla...
        if(wordlist.isEmpty()) {
                output.setNull();
        }
        //Se l'utente ha scritto più di 4 parole oppure 3...in questo gioco l'unica combinazione ammessa con più di 2 parole è 4
        else if(wordlist.size() > 4 || wordlist.size() == 3){
            output.setNull(5); //setto numero di parole a 5 per dire che ne ha scritte più di 4
        }
        //Altrimenti...
        else {
                //Se la lunghezza dell'array è 1, allora probabilmente si tratta di un movimento o di un verbo che indica una singola azione
                if(wordlist.size() == 1) {
                    parseOneWord(wordlist.get(0));
                }
                //Se è 2 allora probabilmente sarà la combinazione di un'azione con un oggetto
                else if(wordlist.size() == 2) {
                    parseTwoWords(wordlist.get(0), wordlist.get(1), objList);
                }
                //Se è 4 allora probabilmente c'è una combinazione con 2 oggetti ( es. accendi candela con fiammiferi)
                else if(wordlist.size() == 4){
                    parseThreeWords(wordlist, objList);
                }
        }
    }   

    /**
     * Metodo che consente di codificare il comando scritto dall'utente. Invocato quando l'utente scrive una sola parola
     * @param w0 
     */
    private void parseOneWord(String w0) {
        //Essendo di dimensione 1 non si tratta di un oggetto
		output.setObjList(null);

		//Setto command
		switch(w0) {
			case "n" :
			case "nord" :
				output.setCommand(Command.N);
				break;
				
			case "s" :
			case "sud" :
				output.setCommand(Command.S);
				break;
				
			case "e" :
			case "est" :
				output.setCommand(Command.E);
				break;
				
			case "o" :
			case "ovest" :
				output.setCommand(Command.W);
				break;
				
			case "i" : 
			case "inventario" :
				output.setCommand(Command.I);
				break;
				
			case "info" :
			case "istruzioni" :
				output.setCommand(Command.HELP);
				break;
				
			case "osserva" :
			case "guarda" :
				output.setCommand(Command.SHOW);
				break;
				
                        case "esci" :
                        case "abbandona" :
                        case "arrenditi" : 
                                output.setCommand(Command.EXIT);
                                break;
                        
                        case "salva" :
                                output.setCommand(Command.SAVE);
                                break;
                                
                        default:
                                output.setNull(1);
                                break;
                }
    }

    /**
     * Metodo che consente di codificare il comando scritto dall'utente e l'oggetto. Invocato quando l'utente scrive due parole parole
     * perciò si presume che l'utente voglia interagire con un oggetto
     * @param w0
     * @param w1
     * @param objList 
     */
    private void parseTwoWords(String w0, String w1, ArrayList<GameObject> objList) {
        
        //Essendo 2 parole probabilmente è un'azione + oggetto
        
        //Controllo prima l'azione
        switch(w0){
            case "apri" :
                output.setCommand(Command.OPEN);
                break;
                
            case "prendi" :
            case "raccogli" :
                output.setCommand(Command.TAKE);
                break;
                
            case "esamina" :
            case "osserva" :
            case "guarda" :
                output.setCommand(Command.EXAMINE);
                break;
                
            case "leggi" :
                output.setCommand(Command.READ);
                break;
                
            case "sposta" :
               output.setCommand(Command.MOVE);
               break;
               
            case "usa" : 
                output.setCommand(Command.USE);
                break;
                
            default :
                output.setNull(2);
        }
        
        //CONTROLLO L'OGGETTO se il settaggio del comando è andato a buon fine, altrimenti è già un comando errato
        if(output.getCommand() != null){
            //Scorro la lista di oggetti
            for( GameObject go : objList){
                //Verifico se l'oggetto scritto in w1 è un oggetto della lista
                if(go.isGameObject(w1)){

                    //Se lo è allora memorizzo in output il tipo di oggetto
                    output.addGameObject(go);
                    break;
                }
            }
        }
        
        
        
        
        
    }

    /**
     * Metodo invocato quando le parole sono 4, ma che per il controllo effettivo sono 3c. 
     * In questo caso l'interazione è con due oggetti come ad es. ACCENDI LANTERNA CON ACCENDINO
     * Le parole sono 4, viene semplicemente verificata la preposizione se è "con" e le altre parole codficate
     * @param words
     * @param objList 
     */
    private void parseThreeWords(ArrayList<String> words, ArrayList<GameObject> objList) {
        //Essendo 4 parole potrebbe essere la combinazione di azione + oggetto + preposizione + oggetto
        //Deve essere scritto in quest'ordine preciso altrimenti il comando non viene riconosciuto
        
        switch(words.get(0)){
            case "accendi" :
                output.setCommand(Command.TURN_ON);
                break;
                
            case "apri" :
                output.setCommand(Command.OPEN);
                break;
                
            default :
                output.setNull(4);
                break;
        }
        
        //CONTROLLO la preposizione così se non è scritta giusta il comando non viene riconosciuto (questo gioco implementa solo "con")...
        if(output.getCommand() != null){ //se command è null è inutile effettuare gli altri controlli
            
            if(words.get(2).equals("con")){
                
                //CONTROLLO GLI OGGETTI 
                if(output.getCommand() != null){

                    //Verifico il primo oggetto

                    //scorro la lista
                    for( GameObject go : objList){
                        //controllo se la parola in words.get(1) è un oggetto del gioco
                        if(go.isGameObject(words.get(1))){
                            output.addGameObject(go);
                            break;
                        }
                    }
                    
                    //verifico il secondo solo se il primo è andato a buon fine
                    if(!output.getObjList().isEmpty()){
                        
                        //scorro la lista
                        for( GameObject go : objList){
                            //controllo se la parola in words.get(3) è un oggetto del gioco
                            if(go.isGameObject(words.get(3))){
                                output.addGameObject(go);
                                break;
                            }
                        }
                    }
                    else{
                        output.setNull(4);
                    }
                }
            }else{
                output.setNull(4);
            }
            
        }
        
        
        
        
    }
}
