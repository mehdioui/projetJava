/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
class SGBDUtils {

    static int iduser_connecte;

    static String myDriver = "org.postgresql.Driver";
    static String myUrl = "jdbc:postgresql://localhost/projetjava";
    static Connection conn;
    static Statement st;

    static boolean verifieMdp(String login, String passwd) {
        int i = 0;
        boolean tmp = false;
        try {

            Class.forName(myDriver);

            /* Connexion à la base de données */
            conn = DriverManager.getConnection(myUrl, "postgres", "rayane");

            /* Construction requete */
            String query = "select * from users where identifiant = '" + login + "' and password= '" + passwd + "'";

            /* ? */
            st = conn.createStatement();

            /* Envoi de la requete */
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                i++;
                SGBDUtils.iduser_connecte = result.getInt(1);
                System.out.println("Id user = " + SGBDUtils.iduser_connecte);
            }

            if (i == 1) {
                tmp = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
            tmp = false;
        }
        return tmp;
    }

    public static List<String> recupSalon(int iduser_connecte) {
        List<String> listeSalon = new ArrayList<String>();
        String query = "select nomsalon from salon, autorise where autorise.iduser = " + iduser_connecte + " and salon.idsalon = autorise.idsalon";
        int i = 0;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                System.out.println(result.getString(1));
                listeSalon.add(result.getString(1));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeSalon;

    }

    static User getUser(String login) {
        int i = 0;
        User tmp = null;
        try {

            Class.forName(myDriver);

            /* Connexion à la base de données */
            conn = DriverManager.getConnection(myUrl, "postgres", "rayane");

            /* Construction requete */
            String query = "select * from users where identifiant = '" + login + "'";

            /* ? */
            st = conn.createStatement();

            /* Envoi de la requete */
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                i++;
                SGBDUtils.iduser_connecte = result.getInt(1);
                System.out.println("Id user = " + SGBDUtils.iduser_connecte);
                tmp = new User(result.getInt("iduser"), result.getString("identifiant"), result.getString("password"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
            tmp = null;
        }
        return tmp;
    }
    
    
    /**
     * Inserer le message d'une conversation dans la base de données
     */
    public static void insererMessageConversation(String message, String date) throws SQLException{
        String insert_message = "INSERT INTO public.message(contenu, datereception) VALUES ('"+message+"', '"+date+"')";
        ResultSet res = st.executeQuery(insert_message);
        /* Récupération de l'ID du message */
        //String recup_ID = "SELECT idmessage FROM message WHERE contenu = 'message'";
        
    }

    
    static void sendPrivatemessage(String destinataire,String contenu) throws SQLException{
    	String format = "dd/MM/yy H:mm:ss";

    	java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
    	java.util.Date date = new java.util.Date();

    	String dat=formater.format( date );
   	 
    	String query = "INSERT INTO public.message( contenu, datereception)VALUES ('";
    	query+=contenu+"',";
    	query+="'"+dat+"')";
   
        /* Connexion à la base de données */
     	 
        /* ? */
        	st = conn.createStatement();

        	/* Envoi de la requete */
        	ResultSet result = st.executeQuery(query);

   	 
    	System.out.println(query);

	}

}
