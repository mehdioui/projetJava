/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;
import java.sql.*;
/**
 *
 * @author thomas
 */
public class NewClass{

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      
      // create our mysql database connection
      /*String myDriver = "org.postgresql.Driver";
      String myUrl = "jdbc:postgresql://localhost/projetjava";
      Class.forName(myDriver);
           
      Connection conn = DriverManager.getConnection(myUrl, "postgres", "rayane");
     
      String query="select * from users";
    
      Statement st=conn.createStatement();
      
      ResultSet result=st.executeQuery(query);
      
      ResultSetMetaData resultMeta = result.getMetaData();
      // ceci est un test 
      System.out.println("- Il y a " + resultMeta.getColumnCount() + " colonnes dans cette table");
      for(int i = 1; i <= resultMeta.getColumnCount(); i++)
       System.out.println("\t *" + resultMeta.getColumnName(i)); */
      
      Salon salon = new Salon("Projet 1");
      User collaborateur = new User(1, "User1", "pass1");
      Message message1 = new Message(collaborateur, collaborateur, "tata");
      WindowAccueil fenetreAccueil = new WindowAccueil(); 
        
        /* Affichage de la fenetre d'accueil */
        fenetreAccueil.setVisible(true);
       
        
    }
}
