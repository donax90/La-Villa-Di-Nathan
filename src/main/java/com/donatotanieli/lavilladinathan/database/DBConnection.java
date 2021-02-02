/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatotanieli.lavilladinathan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *Classe che gestisce la connessione al db per tenere traccia dei punteggi del giocatore
 * @author donatotanieli
 */
public class DBConnection {
    
    private Connection con;
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS scores (name VARCHAR(25), score INTEGER)";
    
    /**
     * Connessione al db locale
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {

        con = DriverManager.getConnection("jdbc:h2:./resources/db/scores");
        Statement stm = con.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();
        
    }
    
    /**
     * Metodo che chiude la connessione del db
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException{
        con.close();
    }
    
    /**
     * Metodo che aggiunge il nome del giocatore con il punteggio sul db
     * @param name_player nome del giocatore
     * @param score punteggio del giocatore
     * @throws SQLException 
     */
    public void addScore(String name_player, int score) throws SQLException{
        
        PreparedStatement pstm = con.prepareStatement("INSERT INTO scores VALUES (?,?)");
        pstm.setString(1, name_player);
        pstm.setInt(2, score);
        pstm.executeUpdate();
        pstm.close();  
        
    }
    
    /**
     * Metodo che crea la stringa contenente i punteggi salvati ordinati da quello più alto a quello più basso
     * @return stringa
     * @throws SQLException 
     */
    public String getScores() throws SQLException{
        
        String result = "";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT name, score FROM scores ORDER BY score DESC");
        while(rs.next()){
            result += "-" + rs.getString(1) + " : ";
            result += rs.getInt(2) + "\n";
        }
        rs.close();
        st.close();
        return result;
    }
    
    
}
