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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
class SGBDUtils {
    
   static int iduser_connecte;

    static boolean verifieMdp(String login, String passwd) {
        int i = 0;
        boolean tmp = false;
        try {
            //
            String myDriver = "org.postgresql.Driver";
            String myUrl = "jdbc:postgresql://localhost/projetjava";
            Class.forName(myDriver);

            /* Connexion à la base de données */
            Connection conn = DriverManager.getConnection(myUrl, "postgres", "rayane");
            
            /* Construction requete */
            String query = "select * from users where identifiant = '"+login+"' and password= '"+passwd+"'";
            
            /* ? */
            Statement st=conn.createStatement();
            
            /* Envoi de la requete */
            ResultSet result=st.executeQuery(query);
            
            ResultSetMetaData resultMeta = result.getMetaData();
            
            while(result.next()){
                i++;
                SGBDUtils.iduser_connecte = result.getInt(1);
                System.out.println("Id user = "+SGBDUtils.iduser_connecte);                       
            }
            
            if (i == 1){
                tmp = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
            tmp = false;
        }
        return tmp;
    }

    static  recupSalon(int iduser_connecte) {
        String query = "select nomsalon from salon,autorise where iduser = "+iduser_connecte+" and salon.idsalon = autorise.idsalon";
        
    }

}
