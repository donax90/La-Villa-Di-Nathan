/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.gamefile;

import com.donatotanieli.lavilladinathan.entity.GameObject;
import com.donatotanieli.lavilladinathan.entity.LightRoom;
import com.donatotanieli.lavilladinathan.entity.ObjectType;
import com.donatotanieli.lavilladinathan.entity.Room;
import com.donatotanieli.lavilladinathan.game.Game;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *Classe che contiene le risorse del gioco: gli oggetti e le stanze
 * @author donatotanieli
 */
public class GameFile {
    
    private Game g;
    
    //COSTRUTTORE
    
    public GameFile(){
        g = new Game();
        this.setResources();
    }
    
    //GET
    public Game getGame(){
        return this.g;
    }
    
    //ALTRI METODI
    
    /**
     * Metodo che carica tutti gli oggetti del gioco in una lista che servirà per il parser per la codifica dell'input dell'utente
     * @return 
     */
    public ArrayList<GameObject> getGameObjectList(){
        
        ArrayList<GameObject> lista = new ArrayList<>();
        //---------------------OGGETTI
     
        //chiave 0
        GameObject key = new GameObject(
                ObjectType.KEY,
                true,
                false,
                false,
                false,
                false,
                false,
                2,
                new ArrayList<>(Arrays.asList(new String[]{ })),
                null,
                "chiave", 
                "una chiave ai piedi dell'albero",
                "Questa chiave serve ad aprire sicuramente qualche porta.");
        lista.add(key);
        
        //albero 1
        GameObject tree = new GameObject(
                ObjectType.TREE, 
                false, 
                false, 
                false, 
                true, 
                false, 
                true, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"pianta"})),
                new ArrayList<>(Arrays.asList(new GameObject[]{key})), 
                "albero", "un albero folto", 
                "Ha un tronco enorme e la chioma molto folta. Intravedo una chiave ai suoi piedi!");
        lista.add(tree);
        
        //insegna 2
        GameObject signboard = new GameObject(
                ObjectType.SIGNBOARD, 
                false, 
                false, 
                false, 
                false, 
                false, 
                true, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{})), 
                null, 
                "insegna", 
                "un'insegna",
                "C'è scritto qualcosa.");
        lista.add(signboard);
        
        //ruscello 3
        GameObject stream = new GameObject(
                ObjectType.STREAM, 
                false, 
                false, 
                false, 
                false, 
                false, 
                true, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"fiume", "rio", "rivolo", "rigagnolo"})), 
                null, 
                "ruscello", 
                "un ruscello", 
                "");
        lista.add(stream);
        
        //machete 4
        GameObject machete = new GameObject(
                ObjectType.MACHETE, 
                true, 
                false, 
                false, 
                false, 
                false, 
                true, 
                1, 
                new ArrayList<>(Arrays.asList(new String[]{"roncola"})), 
                null, 
                "machete", 
                "un vecchio machete", 
                "E' un machete arrugginito");
        lista.add(machete);
        
        //lanterna 5
        GameObject lantern = new GameObject(
                ObjectType.LANTERN, 
                true, 
                false, 
                false, 
                false, 
                false, 
                true, 
                1, 
                new ArrayList<>(Arrays.asList(new String[]{"lume", "lucerna", "lampione", "lampada"})), 
                null, 
                "lanterna", 
                "una lanterna spenta", 
                "Una lanterna molto vecchia coperta da un vetro allungato.");
        lista.add(lantern);
        
        //pozzo 6
        GameObject well = new GameObject(
                ObjectType.WELL, 
                false, 
                false, 
                false, 
                false, 
                false, 
                true, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"buca"})), 
                null, 
                "pozzo", 
                "un pozzo", 
                "");
        lista.add(well);
        
        //portafogli 7
        GameObject wallet = new GameObject(
                ObjectType.WALLET, 
                true, 
                true, 
                false, 
                false, 
                false, 
                true, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"borsellino", "portamonete"})), 
                null, 
                "portafogli", 
                "un portafogli", 
                "");
        lista.add(wallet);
        
        //piede di porco 8
        GameObject cavachiodi = new GameObject(
                ObjectType.CAVACHIODI, 
                true, 
                false, 
                false, 
                false, 
                false, 
                true, 
                1, 
                new ArrayList<>(Arrays.asList(new String[]{})), 
                null, 
                "cavachiodi", 
                "un cavachiodi", 
                "E' il classico piede di porco!");
        lista.add(cavachiodi);
        
        //auto 9
        GameObject car = new GameObject(
                ObjectType.CAR, 
                false, 
                false, 
                false, 
                false, 
                false, 
                true, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"macchina", "automobile"})), 
                null, 
                "auto", 
                "un auto abbandonata", 
                "L'auto è di un rosso fiammeggiante, sembra in ottimo stato e funzionante!");
        lista.add(car);
        
        //rampino 10
        GameObject hook = new GameObject(
                ObjectType.HOOK, 
                true, 
                false, 
                false, 
                false, 
                false, 
                true, 
                3, 
                new ArrayList<>(Arrays.asList(new String[]{"raffio", "gancio", "grappino"})), 
                null, 
                "rampino", 
                "un rampino", 
                "Un rampino col gancio formato da tre uncini e una corda. Sembra resistente!");
        lista.add(hook);
        
        //grimaldello 11
        GameObject picklock = new GameObject(
                ObjectType.PICKLOCK, 
                true, 
                false, 
                false, 
                false, 
                false, 
                true, 
                3, 
                new ArrayList<>(Arrays.asList(new String[]{})), 
                null, 
                "grimaldello", 
                "un grimaldello", 
                "Con questo potrò aprire qualcosa!");
        lista.add(picklock);
        
        //candela 12
        GameObject candle1 = new GameObject(
                ObjectType.CANDLE, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false, 
                2, 
                new ArrayList<>(Arrays.asList(new String[]{"cero", "lume", "fiaccola", "torcia", "candele"})), 
                null, 
                "candela", 
                "una candela", 
                "Questa candela non ha nulla di interessante!");
        lista.add(candle1);
        
        //candela 13
        GameObject candle2 = new GameObject(
                ObjectType.CANDLE, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false, 
                2, 
                new ArrayList<>(Arrays.asList(new String[]{"cero", "lume", "fiaccola", "torcia", "candele"})), 
                null, 
                "candela", 
                "una candela", 
                "Questa candela non ha nulla di interessante!");
        lista.add(candle2);
        
        //candelabro 14
        GameObject candlestick = new GameObject(
                ObjectType.CANDLESTICK, 
                false, 
                false, 
                false, 
                true, 
                false, 
                true, 
                1, 
                new ArrayList<>(Arrays.asList(new String[]{"candeliere"})), 
                new ArrayList<>(Arrays.asList(new GameObject[]{candle1, candle2})), 
                "candelabro", 
                "un vecchio candelabro", 
                "Ci sono delle candele su questo candelabro");
        lista.add(candlestick);
        
        //fiammiferi 15
        GameObject matches = new GameObject(
                ObjectType.MATCHES, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false, 
                2, 
                new ArrayList<>(Arrays.asList(new String[]{"fiammifero"})), 
                null, 
                "fiammiferi", 
                "dei fiammiferi sul tavolo", 
                "Sembrano funzionare ancora");
        lista.add(matches);
        
        //tavolo 16
        GameObject table = new GameObject(
                ObjectType.TABLE, 
                false, 
                false, 
                false, 
                true, 
                false, 
                true, 
                1, 
                new ArrayList<>(Arrays.asList(new String[]{"banco", "tavola"})), 
                new ArrayList<>(Arrays.asList(new GameObject[]{matches})), 
                "tavolo", 
                "un tavolo", 
                "Il tavolo è pieno di polvere. Posso notare dei fiammiferi poggiati!");
        lista.add(table);
        
        //spada 17
        GameObject sword = new GameObject(
                ObjectType.SWORD, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false, 
                5, 
                new ArrayList<>(Arrays.asList(new String[]{"lama", "arma"})), 
                null, 
                "spada", 
                "una spada", 
                "La lama ha un bagliore accecante. Che sia questo il tesoro?");
        lista.add(sword);
        
        //letto 18
        GameObject bed = new GameObject(
                ObjectType.BED, 
                false, 
                false, 
                false, 
                true, 
                true, 
                true, 
                3, 
                new ArrayList<>(Arrays.asList(new String[]{"branda"})), 
                new ArrayList<>(Arrays.asList(new GameObject[]{sword})), 
                "letto", 
                "un letto disfatto", 
                "E' un letto disfatto con le lenzuola sporche e piene di sangue. Che scena orribile!");
        lista.add(bed);
        
        //osso 19
        GameObject bone = new GameObject(
                ObjectType.BONE, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false, 
                3,
                new ArrayList<>(Arrays.asList(new String[]{"spoglie", "ossa"})), 
                null, 
                "osso", 
                "un mucchio di ossa", 
                "Non so dirti a quale ossa del corpo umano appartenga!");
        lista.add(bone);
        
        //scheletro 20
        GameObject skull = new GameObject(
                ObjectType.SKULL, 
                false, 
                false, 
                false, 
                true, 
                false, 
                false, 
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"teschio", "carcassa"})), 
                new ArrayList<>(Arrays.asList(new GameObject[]{bone})), 
                "scheletro", 
                "uno scheletro", 
                "Chissà chi era!Posso notare un mucchio di ossa!");
        lista.add(skull);
        
        //lettera 21
        GameObject missive = new GameObject(
                ObjectType.MISSIVE, 
                true, 
                false, 
                false, 
                false, 
                false, 
                false, 
                1,
                new ArrayList<>(Arrays.asList(new String[]{"missiva", "messaggio"})),
                null,
                "lettera",
                "una lettera",
                "Forse la devo leggere...");
        lista.add(missive);
        
        //armadio 22
        GameObject wardrobe = new  GameObject(
                ObjectType.WARDROBE, 
                false,
                true, 
                false,
                true,
                false,
                true,
                1,
                new ArrayList<>(Arrays.asList(new String[]{"guardaroba"})), 
                new ArrayList<>(Arrays.asList(new GameObject[]{missive, skull})), 
                "armadio", 
                "un vecchio armadio", 
                "Un armadio un pò mal andato. Un anta è semiaperta.");
        lista.add(wardrobe);
        
        //specchio 23
        GameObject mirror = new GameObject(
                ObjectType.MIRROR, 
                false, 
                false, 
                false, 
                false, 
                false, 
                true,
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"specchietto"})),
                null, 
                "specchio", 
                "uno specchio sinistro",
                "");
        lista.add(mirror);
        
        //libreria 24
        GameObject library = new GameObject(
                ObjectType.LIBRARY, 
                false,
                false,
                false,
                false,
                true,
                true,
                6, 
                new ArrayList<>(Arrays.asList(new String[]{"biblioteca"})),
                null,
                "libreria", 
                "una libreria piena zeppa di libri",
                "Si intravede qualcosa dietro!");
        lista.add(library);
        
        //cartello 25
        GameObject sign = new GameObject(
                ObjectType.SIGN, 
                false,
                false,
                false,
                false,
                false,
                true,
                1,
                new ArrayList<>(Arrays.asList(new String[]{})), 
                null,
                "cartello", 
                "un cartello", 
                "C'è scritto qualcosa.");
        lista.add(sign);
        
        //anello 26
        GameObject ring = new GameObject(
                ObjectType.RING, 
                true,
                false,
                false,
                false, 
                false,
                false, 
                20,
                new ArrayList<>(Arrays.asList(new String[]{"tesoro"})), 
                null, "anello", 
                "l'anello di Nathan", 
                "Il tesoro di Nathan!!!L'aveva portato con sé e nessuno l'ha mai trovato!");
        lista.add(ring);
        
        //tomba 27
        GameObject grave = new GameObject(
                ObjectType.GRAVE, 
                false,
                true,
                false,
                true,
                false,
                true,
                8,
                new ArrayList<>(Arrays.asList(new String[]{"loculo", "urna"})), 
                new ArrayList<>(Arrays.asList(new GameObject[]{ring})), 
                "tomba", 
                "la tomba di Nathan", 
                "Nathan Stuart\nNascita: 26 - 7 -1783\nMorte: 3 - 3 - 1869");
        lista.add(grave);
        
        //porta 28(oggetto fittizio per l'interazione con le porte bloccate
        GameObject door = new GameObject(
                ObjectType.DOOR, 
                false,
                false,
                false, 
                false,
                false,
                false,
                0, 
                new ArrayList<>(Arrays.asList(new String[]{"ingresso", "cancello", "accesso"})),
                null, 
                "porta", 
                "porta", 
                "porta");
        lista.add(door);
        
        //accendino 29
        GameObject lighter = new GameObject(
                ObjectType.LIGHTER, 
                false,
                false,
                false,
                false,
                false,
                true, 
                0,
                new ArrayList<>(Arrays.asList(new String[]{})), 
                null, 
                "accendino", 
                "un accendino", 
                "L'accendino che avevo in tasca!");
        lista.add(lighter);
        
        //martello 30
        GameObject hummer = new GameObject(
                ObjectType.HUMMER,
                true,
                false,
                false,
                false,
                false,
                true,
                1,
                new ArrayList<>(Arrays.asList(new String[]{"mazza", "maglia", "battente"})),
                null,
                "martello",
                "un martello",
                "Il martello è bello pesante. Sarà utile a spaccare qualcosa!");
        lista.add(hummer);
        
        return lista;
    }
    
    /**
     * Metodo che carica le stanze e setta l'istanza g
     */
    private void setResources(){
        
        
        //--------------------STANZE
        
        //foresta iniziale
        LightRoom foresta = new LightRoom();
        foresta.setName("FORESTA");
        foresta.setDescription("Mi trovo in una foresta molto fitta, vedo alberi ovunque. Intravedo però a nord la villa.");
        foresta.setLook("Non c'è nulla di interessante.");
        foresta.setUnlockedBy(null);
        foresta.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{})));
        
        //foresta1
        LightRoom foresta1 = new LightRoom();
        foresta1.setName("FORESTA");
        foresta1.setDescription("E' sempre la stessa foresta, la villa è molto più vicina.");
        foresta1.setLook("Non c'è nulla di interessante.");
        foresta1.setUnlockedBy(null);
        foresta1.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{})));
        
        //foresta2
        LightRoom foresta2 = new LightRoom();
        foresta2.setName("FORESTA");
        foresta2.setDescription("Un altro piccolo sforzo e sono giunto alla villa.");
        foresta2.setLook("Non c'è nulla di interessante.");
        foresta2.setUnlockedBy(null);
        foresta2.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(1)})));
        
        //ingresso villa
        LightRoom ingressoVilla = new LightRoom();
        ingressoVilla.setName("INGRESSO VILLA");
        ingressoVilla.setDescription("Mi trovo all'esterno dell'ingresso alla villa. E' circondata da mura molto alte e l'unica via per entrare e' un cancello situato a nord.");
        ingressoVilla.setLook("Non vedo nessun altro oggetto che possa essermi utile.");
        ingressoVilla.setUnlockedBy(null);
        ingressoVilla.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(2)})));
        
        //zona ovest esterno villa
        LightRoom zonaOvestEsternoVilla = new LightRoom();
        zonaOvestEsternoVilla.setName("ZONA OVEST ESTERNO VILLA");
        zonaOvestEsternoVilla.setDescription("In questa zona la natura ha preso il sopravvento. "
                + "Alberi e cespugli ovunque. Un ruscello attraversa l'intero paesaggio, in direzione ovest, oltre esso, posso vedere una piccola casa!");
        zonaOvestEsternoVilla.setLook("Sento gli uccelli cantare, il suono del ruscello è molto rilassante. Potrei starci delle ore a contemplare il paesaggio!");
        zonaOvestEsternoVilla.setUnlockedBy(null);
        zonaOvestEsternoVilla.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(3)})));
        
        //casa degli attrezzi
        LightRoom casaAttrezzi = new LightRoom();
        casaAttrezzi.setName("CASA DEGLI ATTREZZI");
        casaAttrezzi.setDescription("Questa stanza pare servisse a fabbricare degli oggetti. E' piena di cose e macchinari di ogni tipo ormai distrutti.");
        casaAttrezzi.setLook("Ci sono oggetti che sicuramente mi sarebbero stati utili, ma ahimè sono andati distrutti!");
        casaAttrezzi.setUnlockedBy(ObjectType.KEY);
        casaAttrezzi.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(4), getGameObjectList().get(5)})));
        
        //zona est esterno villa
        LightRoom zonaEstEsternoVilla = new LightRoom();
        zonaEstEsternoVilla.setName("ZONA EST ESTERNO VILLA");
        zonaEstEsternoVilla.setDescription("Costeggiando la zona est della villa, mi trovo davanti a un pozzo. Continuando ad est invece ci sono le mura della villa!");
        zonaEstEsternoVilla.setLook("Ci sta un pozzo antico, qualche pietra è andata giù, tuttavia è ancora in piedi. Ha il tettuccio tutto regolato, ma è sprovvisto di carrucola.");
        zonaEstEsternoVilla.setUnlockedBy(null);
        zonaEstEsternoVilla.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(6)})));
        
        //mura 
        LightRoom mura = new LightRoom();
        mura.setName("MURA");
        mura.setDescription("Non ci sono altre direzioni oltre quella per tornare indietro. Le mura sono interamente ricoperte da rovi.");
        mura.setLook("Da qua posso intravedere il punto in cui mi sono trovato all'inizio quando sono caduto. Tira molto vento quassù!");
        mura.setUnlockedBy(ObjectType.HOOK);
        mura.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(7), getGameObjectList().get(8)})));
        
        //giardino villa
        LightRoom giardinoVilla = new LightRoom();
        giardinoVilla.setName("GIARDINO VILLA");
        giardinoVilla.setDescription("Il giardino è molto grande, a nord vi è l'ingresso della villa, ad est il giardino prosegue e ad ovest vedo una grotta!");
        giardinoVilla.setLook("La villa è molto imponente. L'auto, a differenza della villa che ha un suo vissuto, sembra non essere molto vecchia!");
        giardinoVilla.setUnlockedBy(ObjectType.MACHETE);
        giardinoVilla.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(9)})));
        
        //grotta non illuminata
        Room grottaBuia = new Room();
        grottaBuia.setName("GROTTA");
        grottaBuia.setDescription("Non riesco a vedere nulla. E' troppo buia!");
        grottaBuia.setLook("Spero tu riesca a vedere qualcosa che io proprio non ci vedo!");
        grottaBuia.setUnlockedBy(ObjectType.HUMMER);
        
        //cantina non illuminata
        Room cantinaBuia = new Room();
        cantinaBuia.setName("CANTINA");
        cantinaBuia.setDescription("La cantina non è illuminata. Non intravedo nulla di interessante.");
        cantinaBuia.setLook("Non ho la vista di un gatto!");;
        cantinaBuia.setUnlockedBy(null);
        
        //atrio sud
        LightRoom atrioSud = new LightRoom();
        atrioSud.setName("ATRIO SUD");
        atrioSud.setDescription("L'atrio e' molto ampio, il soffitto a cassettoni in legno color oro e bordeaux è pieno di lampadari antichi con struttura a gabbia a dieci luci disposte su bracci curvi. Vicino le pareti qualche sedia ormai mal ridotta");
        atrioSud.setLook("Il pavimento è molto carino: un mosaico di figure geometriche tra triangoli ed esagoni. Non posso fare a meno di guardare quel candelabro!");
        atrioSud.setUnlockedBy(ObjectType.PICKLOCK);
        atrioSud.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(14)})));
        
        //cucina
        LightRoom cucina = new LightRoom();
        cucina.setName("CUCINA");
        cucina.setDescription("La cucina non è molto grande, il soffitto è ad archi. Un tavolino al centro, dove sicuramente Nathan consumava i suoi pasti.");
        cucina.setLook("Posso osservare anche una credenza, dove vi sono raccolti tutti i piatti, bicchieri e posate, ma non credo mi possano servire a qualcosa!");
        cucina.setUnlockedBy(null);
        cucina.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(16)})));
        
        //camera da letto
        LightRoom cameraLetto = new LightRoom();
        cameraLetto.setName("CAMERA DA LETTO");
        cameraLetto.setDescription("Le quattro mura della stanza sono piene di quadri di Nathan. Era molto egocentrico! Ci sta il letto con tracce di sangue e un comodino accanto ma di esso nulla di interessante!Di fronte al letto c'è anche un armadio!");
        cameraLetto.setLook("Nient altro di interessante.");
        cameraLetto.setUnlockedBy(ObjectType.CAVACHIODI);
        cameraLetto.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(18), getGameObjectList().get(22)})));
        
        //atrio nord
        LightRoom atrioNord = new LightRoom();
        atrioNord.setName("ATRIO NORD");
        atrioNord.setDescription("E' la continuazione dell'atrio sud, stesso soffitto, stessi lampadari. A nord una scala che porta ai piani superiori, molto instabile con qualche gradino crollato giù. Ad est e ovest ci sono altre stanze.");
        atrioNord.setLook("Non c'è nient altro di interessante!");
        atrioNord.setUnlockedBy(null);
        atrioNord.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{})));
        
        //bagno
        LightRoom bagno = new LightRoom();
        bagno.setName("BAGNO");
        bagno.setDescription("Classico bagno con i sanitari, un lavandino e uno specchio che ha l'aria di avere qualcosa che non va.");
        bagno.setLook("Nulla di interessante.");
        bagno.setUnlockedBy(null);
        bagno.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(23)})));
        
        //soggiorno
        LightRoom soggiorno = new LightRoom();
        soggiorno.setName("SOGGIORNO");
        soggiorno.setDescription("Un mucchio di mobili riempiono la stanza tutti coperti da un telo per proteggerli dalla polvere! Sul lato nord vi è una libreria.");
        soggiorno.setLook("Non vedo nulla di interessante!");
        soggiorno.setUnlockedBy(null);
        soggiorno.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(24)})));
        
        //ingresso catacomba
        LightRoom ingressoCatacomba = new LightRoom();
        ingressoCatacomba.setName("INGRESSO CATACOMBA");
        ingressoCatacomba.setDescription("Uno stretto corridoio, a nord una porta con un cartello accanto!");
        ingressoCatacomba.setUnlockedBy(ObjectType.LIBRARY);
        ingressoCatacomba.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(25)})));
        
        //catacomba non illuminata
        Room catacombaBuia = new Room();
        catacombaBuia.setName("CATACOMBA");
        catacombaBuia.setDescription("Come mi aspettavo non si vede nulla!");
        catacombaBuia.setLook("Non ci vedo!");
        catacombaBuia.setUnlockedBy(ObjectType.BONE);
        
        //secondo piano
        Room secondoPiano = new Room();
        secondoPiano.setName("SECONDO PIANO");
        secondoPiano.setDescription("");
        secondoPiano.setLook("");
        secondoPiano.setUnlockedBy(null);
        
        //-------------CORRISPETTIVE STANZE ILLUMINATE DI QUELLE BUIE
        
        //grotta illuminata
        LightRoom grotta = new LightRoom();
        grotta.setName("GROTTA");
        grotta.setDescription("La grotta è molto fredda. Dal soffitto pieno di stalattiti, gocciola acqua.");
        grotta.setLook("Non vedo altro di interessante!");
        grotta.setUnlockedBy(null);
        grotta.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(10)})));
        
        //cantina illuminata
        LightRoom cantina = new LightRoom();
        cantina.setName("CANTINA");
        cantina.setDescription("Pensavo di trovarci chissà cosa e invece ci sono tanti scaffali ma sono tutti vuoti!");
        cantina.setLook("Gli scaffali sono vuoti!");
        cantina.setUnlockedBy(null);
        cantina.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(11), getGameObjectList().get(30)})));
        
        //catacomba illuminata
        LightRoom catacomba = new LightRoom();
        catacomba.setName("CATACOMBA");
        catacomba.setDescription("Non ci credo!!! Ho ucciso lo Zombie di Nathan!....Comunque....L'atmosfera lugubre mi fa rabbrividire, mi trovo davanti alla tomba del conte Nathan!");
        catacomba.setLook("C'è solo la sua tomba e il cadavere dello zombie che ho ucciso!");
        catacomba.setUnlockedBy(null);
        catacomba.setGameObjList(new ArrayList<>(Arrays.asList(new GameObject[]{getGameObjectList().get(27)})));
        
        //----------SETTO I VARI COLLEGAMENTI TRA LE CAMERE
        
        foresta.setN(foresta1);
        foresta.setS(null);
        foresta.setE(null);
        foresta.setW(null);
        
        foresta1.setN(foresta2);
        foresta1.setS(foresta);
        foresta1.setE(null);
        foresta1.setW(null);
        
        foresta2.setN(ingressoVilla);
        foresta2.setS(foresta1);
        foresta2.setE(null);
        foresta2.setW(null);
        
        ingressoVilla.setN(giardinoVilla);
        ingressoVilla.setS(foresta2);
        ingressoVilla.setE(zonaEstEsternoVilla);
        ingressoVilla.setW(zonaOvestEsternoVilla);
        
        zonaOvestEsternoVilla.setN(null);
        zonaOvestEsternoVilla.setS(null);
        zonaOvestEsternoVilla.setE(ingressoVilla);
        zonaOvestEsternoVilla.setW(casaAttrezzi);
        
        casaAttrezzi.setN(null);
        casaAttrezzi.setS(null);
        casaAttrezzi.setE(zonaOvestEsternoVilla);
        casaAttrezzi.setW(null);
        
        zonaEstEsternoVilla.setN(null);
        zonaEstEsternoVilla.setS(null);
        zonaEstEsternoVilla.setE(mura);
        zonaEstEsternoVilla.setW(ingressoVilla);
        
        mura.setN(null);
        mura.setS(null);
        mura.setE(null);
        mura.setW(zonaEstEsternoVilla);
        
        giardinoVilla.setN(atrioSud);
        giardinoVilla.setS(ingressoVilla);
        giardinoVilla.setE(cantinaBuia);
        giardinoVilla.setW(grottaBuia);
        
        grottaBuia.setN(null);
        grottaBuia.setS(null);
        grottaBuia.setE(giardinoVilla);
        grottaBuia.setW(null);
        
        grotta.setN(null);
        grotta.setS(null);
        grotta.setE(giardinoVilla);
        grotta.setW(null);
        
        cantinaBuia.setN(null);
        cantinaBuia.setS(null);
        cantinaBuia.setE(null);
        cantinaBuia.setW(giardinoVilla);
        
        cantina.setN(null);
        cantina.setS(null);
        cantina.setE(null);
        cantina.setW(giardinoVilla);
        
        atrioSud.setN(atrioNord);
        atrioSud.setS(giardinoVilla);
        atrioSud.setE(cucina);
        atrioSud.setW(cameraLetto);
        
        cameraLetto.setN(null);
        cameraLetto.setS(null);
        cameraLetto.setE(atrioSud);
        cameraLetto.setW(null);
        
        cucina.setN(null);
        cucina.setS(null);
        cucina.setE(null);
        cucina.setW(atrioSud);
        
        atrioNord.setN(secondoPiano);
        atrioNord.setS(atrioSud);
        atrioNord.setE(bagno);
        atrioNord.setW(soggiorno);
        
        bagno.setN(null);
        bagno.setS(null);
        bagno.setE(null);
        bagno.setW(atrioNord);
        
        soggiorno.setN(ingressoCatacomba);
        soggiorno.setS(null);
        soggiorno.setE(atrioNord);
        soggiorno.setW(null);
        
        ingressoCatacomba.setN(catacombaBuia);
        ingressoCatacomba.setS(soggiorno);
        ingressoCatacomba.setE(null);
        ingressoCatacomba.setW(null);
        
        catacombaBuia.setN(null);
        catacombaBuia.setS(ingressoCatacomba);
        catacombaBuia.setE(null);
        catacombaBuia.setW(null);
        
        catacomba.setN(null);
        catacomba.setS(ingressoCatacomba);
        catacomba.setE(null);
        catacomba.setW(null);
        
        //Raggruppo le lights room delle stanze al buio
        ArrayList<LightRoom> listaLight = new ArrayList<>();
        listaLight.add(grotta);
        listaLight.add(cantina);
        listaLight.add(catacomba);
        
        //Setto il game con tutti i dati
        g.setCurrentRoom(foresta);
        g.setLightrooms(listaLight); 
        g.getPlayer().getInventory().getInventoryList().add(getGameObjectList().get(29));
        
    }
    
}
