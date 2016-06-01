/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;

import com.sun.org.apache.xerces.internal.impl.dv.xs.StringDV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stephanie & Nadjim
 */
class SGBDUtils {

    static int iduser_connecte;
    static int droit;
    static String statut;
    static String myDriver = "org.postgresql.Driver";
    static String myUrl = "jdbc:postgresql://localhost/projetjava";
    static Connection conn;
    static Statement st;
    static String mdp_connexion = "123456";

    static void setStatut(String statut){
        SGBDUtils.statut = statut;
    }
    
    static String getStatut(){
        return SGBDUtils.statut;
    }
    
    static void alterStatut(String s){
        setStatut(s);
    }
    
    /**
     * Vérifier l'authentification d'un utilsateur
     * @param login
     * @param passwd
     * @return 
     */
    static boolean verifieMdp(String login, String passwd) {
        int i = 0;
        boolean tmp = false;
        try {

            Class.forName(myDriver);

            /* Connexion à la base de données */
            conn = DriverManager.getConnection(myUrl, "postgres", mdp_connexion);

            /* Construction requete */
            String query = "select * from public.users where identifiant = '" + login + "' and password= '" + passwd + "'";

            /* ? */
            st = conn.createStatement();

            /* Envoi de la requete */
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                i++;
                SGBDUtils.iduser_connecte = result.getInt(1);
                System.out.println("Id user = " + SGBDUtils.iduser_connecte);
            }
            
            statut = "connecte";
            String req_connexion = "update public.users set statut = 'connecte' where iduser = "+iduser_connecte;
            /* Execution de la requete */
            int res_update = st.executeUpdate(req_connexion);
            
            if (i == 1) {
                tmp = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
            tmp = false;
        }
        return tmp;
    }

    /**
     * Recupérer la liste des salons sur lesquels un user est autorisé
     * @param iduser_connecte
     * @return 
     */
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

    /**
     * Recuperer la liste des personnes d'un salon donné
     * @param id_salon id du salon pour lequel on souhaite récupérer les
     * users
     * @return une liste des noms des personnes du salon
     */
    public static List<String> recupUserSalon(String nom_salon) {
        List<String> listeUser = new ArrayList<>();

        String query = "select distinct identifiant from autorise,users,salon where autorise.iduser = users.iduser and autorise.idsalon = salon.idsalon and salon.nomsalon = '" + nom_salon + "'";

        int i = 0;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            /* On parcourt le resultat de la requete pour construire la chaine de retour */
            while (result.next()) {
                System.out.println(result.getString(1));
                listeUser.add(result.getString(1));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeUser;
    }
    
    
    /**
     * Recuperer la liste des personnes connectées pour un salon donné
     * @param id_salon id du salon pour lequel on souhaite récupérer les
     * personnes connectées
     * @return une liste des noms des personnes connectées
     */
    public static List<String> recupUserConnecte(String nom_salon) {
        List<String> listeConnect = new ArrayList<String>();

        String query = "select distinct identifiant from autorise,users,salon where autorise.iduser = users.iduser and autorise.idsalon = salon.idsalon and salon.nomsalon = '" + nom_salon + "' and users.statut = 'connecte'";

        int i = 0;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            /* On parcourt le resultat de la requete pour construire la chaine de retour */
            while (result.next()) {
                System.out.println(result.getString(1));
                listeConnect.add(result.getString(1));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeConnect;
    }
    
    /**
     * Verifier si une personne qui se connecte est un collaborateur ou un administateur
     * @param login
     * @return 
     */
    static int verifierDroits(String login){
        String droit = "";
        /* Construction de la requete */
        String query = "select droit from public.users where identifiant ='"+login+"'";
        /* Envoi de la requete */
        int i = 0;
        int ret = 0;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            /* On parcourt le readdUserSalonsultat de la requete pour construire la chaine de retour */
            while (result.next()) {
                droit = result.getString(1);
                System.out.println("Droit"+droit);
                i++;
            }
            
            if ("collaborateur".compareTo(droit) == -37){ /* Problème de retour du compareTo */
                ret = 0;
            } else {
                ret = 1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return ret;
    }
    
    /**
     * Obtenir un User à partir de son ID
     * @param id
     * @return 
     */
    static User getUserId(int id){
        User tmp = null;
        
        try {

            Class.forName(myDriver);

            /* Connexion à la base de données */
            conn = DriverManager.getConnection(myUrl, "postgres", mdp_connexion);

            /* Construction requete */
            String query = "select * from users where iduser = '" + id + "'";

            /* ? */
            st = conn.createStatement();

            /* Envoi de la requete */
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                SGBDUtils.iduser_connecte = result.getInt(1);
                tmp = new User(result.getInt("iduser"), result.getString("identifiant"), result.getString("password"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
            tmp = null;
        }
        return tmp;
    }

    
    /**
     * Obtenir un User à partir de son identifiant
     * @param login Identifiant du User que l'on veut récupérer
     * @return 
     */
    static User getUser(String login) {
        int i = 0;
        User tmp = null;
        try {

            Class.forName(myDriver);

            /* Connexion à la base de données */
            conn = DriverManager.getConnection(myUrl, "postgres", mdp_connexion);

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
     * Obtenir les users qui ne sont pas sur le salon
     * @param id_salon ID du salon pour lequel on recupère les users qui ne sont pas présents
     * @return Liste des users qui ne sont pas sur le salon
     */
    static List<String> getUserNonPresents(int id_salon){
        List<String> listeUser = new ArrayList<>();
        
        String query = "select identifiant from users where identifiant not in (select identifiant from users as u, autorise as a where u.iduser = a.iduser and a.idsalon = "+id_salon+")";
       
        int i = 0;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            /* On parcourt le resultat de la requete pour construire la chaine de retour */
            while (result.next()) {
                System.out.println(result.getString(1));
                listeUser.add(result.getString(1));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        return listeUser;         
    }
    
    /**
     * Obtenir la liste de tous les users autorises sur le salon
     * @param id_salon ID du salon pour lequel on récupère les users présents
     * @return Liste des users présents sur le salon
     */
    static List<String> getUsersSalonPresents(int id_salon){
        List<String> listeUser = new ArrayList<>();
        
        String query = "select identifiant from users as u, autorise as a where u.iduser = a.iduser and a.idsalon = "+id_salon;
       
        int i = 0;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            /* On parcourt le resultat de la requete pour construire la chaine de retour */
            while (result.next()) {
                System.out.println(result.getString(1));
                listeUser.add(result.getString(1));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        return listeUser; 
    }    
    
    /**
     * Obtenir un salon à partir de son nom
     * @param nomSalon
     * @return Salon
     */
    static Salon getSalon(String nomSalon) {
        Salon tmpS = new Salon(nomSalon);
        int id_salon;
        try {

            Class.forName(myDriver);

            /* Connexion à la base de données */
            conn = DriverManager.getConnection(myUrl, "postgres", mdp_connexion);

            /* Construction requete */
            String query = "select * from salon where nomsalon = '" + nomSalon + "'";

            st = conn.createStatement();

            /* Envoi de la requete */
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                id_salon = result.getInt(1);
                tmpS.setID(id_salon);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
            tmpS = null;
        }
        return tmpS;
    }

    /**
     * Inserer le message d'une conversation dans la base de données Inserer le
     * message dans la table message Inserer le message dans la table tchate
     * @param message
     * @param date
     * @param nomSalon
     * @throws SQLException 
     */
    public static void insererMessageConversation(String message, String date, String nomSalon) throws SQLException {
        int ID_message = 0;
        int ID_salon = 0;
        int res_insert_message = 0;
        int i = 1;

        /* Insertion du message dans la table message */
        String insert_message = "INSERT INTO public.message(contenu, datereception) VALUES ('" + message + "','" + date + "')";
        System.out.println(insert_message);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(insert_message);

        /* Récupération de l'ID du message */
        String req_ID_message = "SELECT idmessage FROM message ORDER BY idmessage DESC LIMIT 1";
        ResultSet res_message = st.executeQuery(req_ID_message);
        while (i < 2) {
            res_message.next();
            ID_message = res_message.getInt(i);
            i++;
        }
        System.out.println("ID Message = " + ID_message);

        /* Recupération de l'ID du salon */
        String req_ID_salon = "SELECT idsalon FROM salon WHERE nomsalon = '" + nomSalon + "'";
        ResultSet res_salon = st.executeQuery(req_ID_salon);
        while (res_salon.next()) {
            ID_salon = res_salon.getInt(1);
        }
        System.out.println("ID salon " + ID_salon);

        /* Insertion dans la table tchate */
        String req_tchate = "INSERT INTO public.tchate(idsalon, idmessage, iduser) VALUES (" + ID_salon + ", " + ID_message + ", " + SGBDUtils.iduser_connecte + ")";
        int res_insert_tchate = st.executeUpdate(req_tchate);
    }

    /**
     * Envoyer un message privé
     * @param destinataire
     * @param contenu
     * @throws SQLException 
     */
    static void sendPrivatemessage(String destinataire, String contenu) throws SQLException {
        int idmessage = 0;//id du message envoyé
        int iddest = 0;//id du destinataire
        /*recuperation de la date */
        String format = "dd/MM/yy H:mm:ss";
        int a;
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();

        String dat = formater.format(date);
        // insertion du message dans la bdd 
        String query = "INSERT INTO public.message(contenu, datereception)VALUES ('";
        query += contenu + "',";
        query += "'" + dat + "')";
        System.out.println(query);

        try {

            Statement stmt = conn.createStatement();
            //isertion du message dans la table user
            stmt.executeQuery(query);

        } catch (SQLException e) {

        }  //recuperation de l'iduser du destinataire

        try {
            String req = "SELECT iduser from users where identifiant='" + destinataire + "'";
            System.out.println(req);

            Statement st1 = conn.createStatement();
            ResultSet result1 = st1.executeQuery(req);
            while (result1.next()) {

                iddest = result1.getInt(1);
                System.out.println(iddest + " lid du destinataire");
            }
        } catch (SQLException e) {
            System.out.println("execution requete select pour recuperer iddest echoué");
        }
        //recuperation de l'id du message
        try {
            String req_ID_message = "SELECT idmessage FROM message ORDER BY idmessage DESC LIMIT 1";
            ResultSet res_message = st.executeQuery(req_ID_message);
            int i = 1;
            while (i < 2) {
                res_message.next();
                idmessage = res_message.getInt(i);
                i++;
            }
            System.out.println("ID_message :" + idmessage);

        } catch (SQLException e) {
            System.out.println("execution requete select nbligne des messages echoués");
        }

        //insertion dans la table envoie iduserconnecte,iduserdestinataier,idmessage
        int iduser_conn = SGBDUtils.iduser_connecte;

        try {
            query = "INSERT INTO public.envoie(iduser2, iduser, idmessage)VALUES (" + iduser_conn + "," + iddest + "," + idmessage + ")";
            System.out.println(query);
            /* Envoi de la requete */
            Statement s = conn.createStatement();

            s.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("execution requette insrtion envoie");
        }

    }

    /**
     * Récuperation de la liste des messages appartenant à un salon
     * @param id_salon ID du salon pour lequel on souhaite récupérer les
     * messages
     * @return Retourne la liste des messages du salon
     */
    public static List<String> recupMessageSalon(int id_salon) {
        List<String> listeMessage = new ArrayList<>();
        String message="";
        /* Construction de la requête */
        String query = "select distinct datereception, identifiant, contenu from users as u,tchate as t,message as m where t.idmessage=m.idmessage and t.iduser=u.iduser and t.idsalon="+id_salon;
        try {
            /* Envoi de la requête à la base de données */
            ResultSet result = st.executeQuery(query);

            /* On parcourt le resultat de la requete pour construire la chaine de retour */
            while (result.next()) {
                message = result.getString(1).trim();
                message += ", "+result.getString(2).trim();
                message += " a dit : "+result.getString(3).trim();
                System.out.println(message);
                listeMessage.add(message);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeMessage;
    }

    /**
     * Insérer un salon dans la base de données
     * @param nomSalon 
     */
    static void insererSalon(String nomSalon) {
        try {
            /* Creation de la requete */
            String reqInsererSalon = "INSERT INTO salon (nomsalon, iduser) VALUES('"+nomSalon+"', "+iduser_connecte+")";
            /* Execution de la requete */
            int res_insert_salon = st.executeUpdate(reqInsererSalon);
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtenir la liste des salons des salons administrés par un user
     * @param iduser_connecte
     * @return 
     */
    static List<String> getSalonAdministre(int iduser_connecte){
            List<String> listeSalonAdmin = new ArrayList<>();
            String nom ="";
            
            String query = "select distinct nomsalon from public.salon as s where iduser = "+SGBDUtils.iduser_connecte;
        try{    
            /* Envoi de la requete */
            ResultSet result = st.executeQuery(query);
            
            while (result.next()) {
                nom = result.getString(1);
                listeSalonAdmin.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return listeSalonAdmin;
    }

    /**
     * Insérer un user autorisé sur un salon
     * @param nom Nom du user à ajouter au salon
     * @param salon Nom du salon dans lequel on ajoute l'utilisateur
     */
    static void addUserSalon(String nom, String salon, int droit){
        try {
            /* Creation de la requete */
            int id_user_autorise = SGBDUtils.getUser(nom).getID();
            int idsalon = SGBDUtils.getSalon(salon).getID();
            String reqAutoriseUserSalon = "INSERT INTO autorise (iddroit, idsalon, iduser) VALUES("+droit+", "+idsalon+" , "+id_user_autorise+")";
            /* Execution de la requete */
            int res_insert_salon = st.executeUpdate(reqAutoriseUserSalon);
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mettre à jour la base de données => Statut passe à hors ligne
     * @param id_user 
     */
    static void deconnexion(int id_user){
        String query = "update public.users set statut = 'hors ligne' where iduser = "+id_user;
        /* Execution de la requete */
        try{
            int res_update = st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * Passer le statut du user à connecté
     */
    static void enligne(){
        String query = "update public.users set statut = 'connecte' where iduser = "+SGBDUtils.iduser_connecte;
        /* Execution de la requete */
        try{
            int res_update = st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    /**
     * Supprimer un user d'un salon
     * @param nom
     * @param salon 
     */
    static void delUserSalon(String nom, String salon){
        try {
            /* Creation de la requete */
            int id_user_del = SGBDUtils.getUser(nom).getID();
            int id_salon_del = SGBDUtils.getSalon(salon).getID();
            String reqDelUserSalon = "delete from public.autorise where iduser = "+id_user_del+"and idsalon = "+id_salon_del;
            /* Execution de la requete */
            int res_insert_salon = st.executeUpdate(reqDelUserSalon);
        } catch (SQLException ex) {
            Logger.getLogger(SGBDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
