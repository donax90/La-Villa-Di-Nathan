/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.gui;

import com.donatotanieli.lavilladinathan.database.DBConnection;
import com.donatotanieli.lavilladinathan.entity.GameObject;
import com.donatotanieli.lavilladinathan.entity.LightRoom;
import com.donatotanieli.lavilladinathan.entity.Room;
import com.donatotanieli.lavilladinathan.game.Game;
import java.awt.event.KeyEvent;
import com.donatotanieli.lavilladinathan.game.GameCommunicator;
import com.donatotanieli.lavilladinathan.gamefile.SaverLoaderClass;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *Interfaccia principale del gioco
 * @author donatotanieli
 */
public class GameGUI extends javax.swing.JFrame {
    
    private GameCommunicator gameC; //Oggetto che contiene i progressi del gioco e il Parser per la codifica del messaggio in input dell'utente
    private Room current; //Questo oggetto mi servirà per confrontarlo con la stanza in cui si trova il giocatore. Se cambia stanza pulisco il pannello
    SaverLoaderClass sc = new SaverLoaderClass();
    DBConnection dbConnection = new DBConnection();
    
    /**
     * Creates new form GameGUI
     * @throws javax.swing.text.BadLocationException
     */
    public GameGUI() throws BadLocationException {
        initComponents();
        newGame();
    }
    
    /**
     * Costruttore con ingresso il parametro Game, invocato quando si deve caricare una partita
     * @param g
     * @throws BadLocationException 
     */
    public GameGUI(Game g) throws BadLocationException{
        initComponents();
        loadGame(g);
    }
    
    /**
     * Metodo che crea una nuova partita
     * @throws BadLocationException 
     */
    private void newGame() throws BadLocationException{
        
        gameC = new GameCommunicator();
        current = gameC.getGameManager().getGame().getCurrentRoom();
        
        //JOptionPane per scrivere il nome del giocatore
        JLabel optionText = new JLabel("Come ti chiami?");
        optionText.setFont(new java.awt.Font("Papyrus", 0, 18));        
        String playerName = JOptionPane.showInputDialog(null, optionText, "NUOVA PARTITA", JOptionPane.INFORMATION_MESSAGE);        
        gameC.getGameManager().getGame().getPlayer().setName(playerName);
        lblPlayer.setText(playerName);
        
        String string = "BENVENUTO " + playerName + "\n\nIn questo gioco vestirai i panni di Albert, un famoso cacciatore di tesori.\n"
				+ "Molti dei suoi ritrovamenti sono esposti nei musei di tutto il mondo.\n"
				+ "Da giorni Albert si era imbattuto su un tesore non ancora trovato da nessuno, raccontato solo sui libri.\n"
				+ "Si narra che nella villa di Nathan ci sia nascosto un tesoro che il conte trovò in uno dei suoi viaggi in America.\n"
				+ "Così senza perdere altro tempo sali in macchina con tutta la tua attrezzatura da esploratore e ti metti alla guida"
				+ " per raggiungere la villa.\nTi accorgi che le ruote dell'auto hanno forato, ma data la forte velocità e l'asfalto "
				+ "umido perdi il controllo e vieni sbalzato fuori.\nRotoli giù ai piedi della valle e cadi in un piccolo burrone perdendo i sensi.\n"
				+ "Ti svegli in un piccolo bosco, ma tornare alla macchina è praticamente impossibile.\n"
				+ "\n\n ";
        pannelloTesto.setText(string);
        setColorText(gameC.getGameManager().getGame().getCurrentRoom().getName(), Color.red);
        appendText("\n" + gameC.getGameManager().getGame().getCurrentRoom().getDescription() + "\nNon vedo nessun oggetto.\n");
    }
    
    /**
     * Questo metodo consente di caricare una partita salvata
     * @param g
     * @throws BadLocationException 
     */
    private void loadGame(Game g) throws BadLocationException{
        gameC = new GameCommunicator();
        gameC.getGameManager().setGame(g);
        current = gameC.getGameManager().getGame().getCurrentRoom();
        updateGame("Bentornato " + gameC.getGameManager().getGame().getPlayer().getName() + "\n\n");
        labScore.setText(String.valueOf(gameC.getGameManager().getGame().getPlayer().getScore()));
        lblPlayer.setText(gameC.getGameManager().getGame().getPlayer().getName());
    }
    
    /**
     * Metodo che viene invocato quando il giocatore muore. Viene mostrato il punteggio e si ritorna al menù principale
     */
    public void gameOver() throws SQLException{
        JOptionPane.showMessageDialog(null, "Sei morto!\nIl tuo punteggio è : " + gameC.getGameManager().getGame().getPlayer().getScore(), "OPS", JOptionPane.INFORMATION_MESSAGE);
        goToMenu();
        saveOnDB();
    }
    
    /**
     * Metodo che viene invocato quanto il giocatore completa il gioco. Viene mostrato il punteggio e si ritorna al menù principale
     */
    public void winner() throws SQLException{
        JOptionPane.showMessageDialog(null, "Congratulazioni!\nSei riuscito a completare il gioco!\n"
                + "Il tuo punteggio è : " + gameC.getGameManager().getGame().getPlayer().getScore(), "VITTORIA!!!!!!!", JOptionPane.INFORMATION_MESSAGE);
        goToMenu();
        saveOnDB();
    }
    
    /**
     * Metodo che consente il salvataggio dei dati della partita
     */
    public void save(){
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(".//SaveFolder")); // viene visualizzata la cartella inserita nel percorso
            try {
                    if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                        String path = fc.getSelectedFile().getPath(); //Stringa che contiene il persorso del file
                        //Invoco il metodo saveFile per salvare il gioco passando il percorso e l'oggetto game
                        sc.saveFile(gameC.getGameManager().getGame(), path);                         
                    }
                } catch (HeadlessException | IOException e) {
                    JOptionPane.showMessageDialog(null, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
    }
    
    
    /**
     * Metodo che permette di ritornare al menù principale
     */
    public void goToMenu(){
        MenuGUI menu = new MenuGUI();
        menu.setVisible(true);
        dispose();
    }

    /**
     * Metodo che mostra un JOptionPane che cambia stato in base al titolo (NUOVA PARTITA, CARICA PARTITA, TORNA AL MENU' PRINCIPALE)
     * @param message messaggio che si vuole mostrare
     * @param title titolo del frame
     * @throws BadLocationException 
     */
    public void showJOptionPaneType(String message, String title) throws BadLocationException{
        
        Object[] object = new Object[]{"Salva", "Non Salvare", "Annulla"};
        int result = JOptionPane.showOptionDialog(
               null, 
               message,
               title,
               JOptionPane.YES_NO_CANCEL_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               object,
               object[0]);
        if(result == JOptionPane.YES_OPTION){
            save();
        }else if(result == JOptionPane.NO_OPTION){
            switch(title){
                case "NUOVA PARTITA" :
                    newGame();
                    break;
                    
                case "CARICA PARTITA" :
                    JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new File(".//SaveFolder")); 
                    try {

                        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                            String path = fc.getSelectedFile().getPath();
                            Game game = new Game();
                            sc.loadFile(game, path);
                            loadGame(game);                       
                        }
                    } catch (HeadlessException | IOException | ClassNotFoundException | BadLocationException e) {
                        JOptionPane.showMessageDialog(null, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "TORNA AL MENU' PRINCIPALE" :
                    goToMenu();
                    break;
            }
        }        
    }
    
    /**
     * Metodo che salba sul db il punteggio del giocatore
     * @throws SQLException 
     */
    private void saveOnDB() throws SQLException{
        dbConnection.connect();
        dbConnection.addScore(gameC.getGameManager().getGame().getPlayer().getName(), gameC.getGameManager().getGame().getPlayer().getScore());
        dbConnection.closeConnection();
    }
    
    /**
     * Metodo che esegue il comando di input dell'utente, invocato nell'ActionListener del pulsante invio
     */
    private void enterCommand() throws SQLException{
        
        try {
            String input = textComando.getText();       //stringa che contiene il testo scritto dall'utente
            
            setColorText("--->" + input + "\n", Color.GREEN);       //Mando in stampa ciò che ha scritto l'utente
            
            String output = gameC.gameResponse(input) + "\n";       //stringa che contiene la risposta del gioco
            
            //Se la stanza in cui si trova il giocatore è ancora la stessa aggiungo la risposta al testo
            //altrimenti pulisco il pannello e stampo la nuova stanza. In questo modo visualizzo nel pannello solo le interazioni con la stanza corrente
            if(!(current.getName().equals(gameC.getGameManager().getGame().getCurrentRoom().getName()) && 
                   current.getDescription().equals(gameC.getGameManager().getGame().getCurrentRoom().getDescription()))){
                updateGame("");
                current = gameC.getGameManager().getGame().getCurrentRoom();
            }else{
                appendText(output);
            }               
            } catch (BadLocationException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
            textComando.setText("");        //pulisco la textField dopo ogni comando
            
            labScore.setText(String.valueOf(gameC.getGameManager().getGame().getPlayer().getScore()));      //Aggiorno il punteggio
            
            //Se lo stato di gioco è cambiato e il giocatore è morto invoco il metodo gameOver()
            if(!Game.alive){
                gameOver();
            }
            
            //Se lo stato del gioco è cambiato e il giocatore ha vinto invoco il metodo winner()
            if(Game.winner){
                winner();
            }
    }
    
    /**
     * Metodo che riceve in input la stringa da stampare sulla TextPane, permette di aggiungere il testo a quello già presente
     * @param stringa
     * @throws BadLocationException 
     */
    public void appendText(String stringa) throws BadLocationException {
            StyledDocument document = (StyledDocument) pannelloTesto.getDocument();
	    document.insertString(document.getLength(), stringa, null);
	}
	
    /**
     * Metodo che aggiorna le informazioni della stanza, viene invocato 
     * quando il giocatore accede ad una nuova stanza sovrascrivendo il testo presente nella TextPane
     * @param stringa
     * @throws BadLocationException 
     */
    public void updateGame(String stringa) throws BadLocationException {
            pannelloTesto.setText(stringa);
            setColorText("===========" + (gameC.getGameManager().getGame().getCurrentRoom().getName()) + "===========\n", Color.red);
            appendText((gameC.getGameManager().getGame().getCurrentRoom().getDescription()) + "\n");
            //Se la stanza non è illuminata allora lookpattern non può essere invocato
            if(gameC.getGameManager().getGame().getCurrentRoom() instanceof LightRoom){
                    appendText(lookPattern(((LightRoom)gameC.getGameManager().getGame().getCurrentRoom()).getGameObjList()));
            }
    }
    
    /**
     * Metodo che aggiunge al testo già presente la stringa di colore color passata come parametro 
     * @param stringa
     * @param color
     * @throws BadLocationException 
     */
    public void setColorText(String stringa, Color color) throws BadLocationException{
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setForeground(set, color);
        StyledDocument document = (StyledDocument) pannelloTesto.getDocument();
        document.insertString(document.getLength(), stringa, set);
    }
	
    /**
     * Metodo che riceve costruisce la stringa degli oggetti visibili nella stanza partendo dalla lista di oggetti in input
     * @param goList
     * @return stringa costruita
     */
    private String lookPattern(List<GameObject> goList) {
            String str = "";
            if(goList.isEmpty()){
                str = "Non vedo nessun oggetto.\n";
            }else{
                str = "Vedo:\n";
                for(int i = 0 ; i < goList.size(); i++) {
                    if(goList.get(i).isVisible()){
                        str = str + "-" + goList.get(i).getDescription();
                        if(goList.get(i).isIsGameObjectContainer() && goList.get(i).isIsOpened()){
                            
                            //verifico attraverso un primo ciclo quanti oggetti visibili ci sono nell'oggetto per poter capire quante virgole stampare
                            int numbObjVisible = 0;
                            for(int j = 0; j < goList.get(i).getGameObjList().size(); j ++){
                                if(goList.get(i).getGameObjList().get(j).isVisible()){
                                    numbObjVisible++;  
                                }
                            }
                            //Se gli oggetti visibili all'interno di un oggetto contenitore sono più di 0 allora continuo a costruire la stringa
                            if(numbObjVisible > 0){
                                str += " con dentro: ";
                                for(int j = 0; j < goList.get(i).getGameObjList().size(); j ++){
                                    if(goList.get(i).getGameObjList().get(j).isVisible()){
                                        str += goList.get(i).getGameObjList().get(j).getDescription();
                                        if((numbObjVisible - 1) > 0){
                                            str += ", ";
                                            numbObjVisible--;
                                        }
                                    }
                                }
                                str += "\n";
                            }
                        }else{
                            //Se l'oggetto non è un container allora vado a capo e continuo a scorrere la lista
                            str += "\n";
                        }
                    }
                    
                }
            }
            
            return str;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textComando = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pannelloTesto = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        lblPlayer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labScore = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuNewGame = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuLoad = new javax.swing.JMenuItem();
        menuMenu = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        Istruzioni = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textComando.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        textComando.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                invio(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pannelloTesto.setEditable(false);
        pannelloTesto.setBackground(new java.awt.Color(0, 0, 0));
        pannelloTesto.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        pannelloTesto.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(pannelloTesto);

        jLabel1.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        jLabel1.setText("Premi invio per confermare ");

        lblPlayer.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        lblPlayer.setText("xxxxxxx");

        jLabel4.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        jLabel4.setText("Giocatore: ");

        jLabel5.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        jLabel5.setText("Punteggio:");

        labScore.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        labScore.setText("0");

        menuFile.setText("File");
        menuFile.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N

        menuNewGame.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        menuNewGame.setText("Nuova Partita");
        menuNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewGameActionPerformed(evt);
            }
        });
        menuFile.add(menuNewGame);

        menuSave.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        menuSave.setText("Salva");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuSave);

        menuLoad.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        menuLoad.setText("Carica");
        menuLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadActionPerformed(evt);
            }
        });
        menuFile.add(menuLoad);

        menuMenu.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        menuMenu.setText("Torna al menu principale");
        menuMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMenuActionPerformed(evt);
            }
        });
        menuFile.add(menuMenu);

        jMenuBar1.add(menuFile);

        jMenu1.setText("Supporto");
        jMenu1.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N

        Istruzioni.setFont(new java.awt.Font("Papyrus", 0, 18)); // NOI18N
        Istruzioni.setText("Istruzioni");
        Istruzioni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IstruzioniActionPerformed(evt);
            }
        });
        jMenu1.add(Istruzioni);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textComando, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel1)))
                        .addContainerGap(87, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPlayer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labScore, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(labScore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textComando, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * KeyListener della textField
     * @param evt 
     */
    private void invio(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invio
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                enterCommand();
            } catch (SQLException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }//GEN-LAST:event_invio

    /**
     * ActionListener della voce nuova partita del menù File
     * @param evt 
     */
    private void menuNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewGameActionPerformed
       try {
            showJOptionPaneType("Cominciando una nuova partita perderai i progressi che hai ottenuto fino ad ora!\nVuoi salvare prima di continuare?",
                    "NUOVA PARTITA");
        } catch (BadLocationException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuNewGameActionPerformed

    /**
     * ActionListener della voce torna al menù principale del menù File
     * @param evt 
     */
    private void menuMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMenuActionPerformed
        try {
            showJOptionPaneType("Uscendo perderai i progressi che hai ottenuto fino ad ora!\nVuoi davvero continuare?", "TORNA AL MENU' PRINCIPALE");
        } catch (BadLocationException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuMenuActionPerformed

    /**
     * ActionListener della voce salva del menù File
     * @param evt 
     */
    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        save();
    }//GEN-LAST:event_menuSaveActionPerformed

    /**
     * ActionListener della voce carica del menù File
     * @param evt 
     */
    private void menuLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadActionPerformed
        try {
            showJOptionPaneType("Caricando un'altra partita perderai i progressi che hai ottenuto fino ad ora!\nVuoi salvare prima di continuare?", "CARICA PARTITA");
        } catch (BadLocationException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuLoadActionPerformed

    /**
     * ActionListener della voce istruzioni del menù Supporto
     * @param evt 
     */
    private void IstruzioniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IstruzioniActionPerformed
        JOptionPane.showMessageDialog(null, gameC.getGameManager().showInstructions(), "ISTRUZIONI PER GIOCARE", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_IstruzioniActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Istruzioni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labScore;
    private javax.swing.JLabel lblPlayer;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuLoad;
    private javax.swing.JMenuItem menuMenu;
    private javax.swing.JMenuItem menuNewGame;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JTextPane pannelloTesto;
    private javax.swing.JTextField textComando;
    // End of variables declaration//GEN-END:variables
}
