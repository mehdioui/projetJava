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
    	int idmessage=0;//id du message envoyé
        int iddest=0;//id du destinataire
        /*recuperation de la date */
        String format = "dd/MM/yy H:mm:ss";
        int a;
    	java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
    	java.util.Date date = new java.util.Date();

    	String dat=formater.format( date );
   	// insertion du message dans la bdd 
    	String query = "INSERT INTO public.message( contenu, datereception)VALUES ('";
    	query+=contenu+"',";
    	query+="'"+dat+"')";
        System.out.println(query);
        
        try{
        	/* Envoi de la requete */
            Statement stmt=conn.createStatement();
            //isertion du message dans la table user
           stmt.executeQuery(query);
         
        }catch(SQLException e){
            
        }
        
        //recuperation de l'iduser du destinataire
        
        
        try{
        String req="SELECT iduser from users where identifiant='"+destinataire+"'";
        System.out.println(req);
        
            Statement st1=conn.createStatement();
            ResultSet result1 = st1.executeQuery(req);
            while(result1.next()){
             
                iddest = result1.getInt(1);
                System.out.println(iddest+" lid du destinataire");
         }
        }catch(SQLException e){
          System.out.println("execution requette select pour recuperer iddest echoué");
        }
       //recuperation de l'id du message
        try{
            query="select * from message";
        	/* Envoi de la requete */
        
        Statement stm=conn.createStatement();
        
          ResultSet res= stm.executeQuery(query);
          
          while(res.next()){
             idmessage++;
          }
          System.out.println("nbre message yen a :"+idmessage);
          
        }catch(SQLException e){
            System.out.println("execution requette select nbligne des messages echoué");
        }
        
        //insertion dans la table envoie iduserconnecte,iduserdestinataier,idmessage
        int iduser_conn=SGBDUtils.iduser_connecte;
        
       try{
            query="INSERT INTO public.envoie(iduser2, iduser, idmessage)VALUES ("+iduser_conn+","+iddest+","+ idmessage +")";
            System.out.println(query);
        	/* Envoi de la requete */
       Statement s=conn.createStatement();
       
          
                 s.executeQuery(query);
                 
       
        }catch(SQLException e){
            System.out.println("execution requette insrtion envoie");
        } 
     
    }
    
   
   
}
    
    

