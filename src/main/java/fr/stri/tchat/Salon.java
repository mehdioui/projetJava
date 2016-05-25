/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;

import java.util.ArrayList;

/**
 *
 * @author thomas
 */
public class Salon {
    
    /* ***** ATTRIBUTS ***** */
    /* ID du salon */
    int ID;
    /* Nom du salon */ 
    String nom;
    /* Liste messages */
    ArrayList <Message> liste_messages = new ArrayList<Message>();
    /* Liste users */
    ArrayList <User> liste_users = new ArrayList<User>();

    /* ***** CONSTRUCTEUR ***** */ 
    public Salon(String nomSalon) {
        this.nom = nomSalon;
    }
    
    /* ***** GETTER / SETTER ***** */
    public int getID(){
        return ID;
    }

    public void setID(int id){
        this.ID = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Message> getListe_messages() {
        return liste_messages;
    }

    public void setListe_messages(ArrayList<Message> liste_messages) {
        this.liste_messages = liste_messages;
    }

    public ArrayList<User> getListe_users() {
        return liste_users;
    }

    public void setListe_users(ArrayList<User> liste_users) {
        this.liste_users = liste_users;
    }

}
