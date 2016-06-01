/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;

/**
 *
 * @author Stephanie
 */
public class Message {

    /**
     * ATTRIBUTS
     */
    /* ID du message */   
    int ID;
    /* Expediteur du message */
    User expediteur;
    /* Recepteur du message */
    User recepteur;
    /* Corps du message */ 
    String contenu;

    
    /**
     * CONSTRUCTEUR
     * @param exp
     * @param recep
     * @param contenu 
     */
    public Message(User exp, User recep, String contenu) {
        this.expediteur = exp;
        this.recepteur = recep;
        this.contenu = contenu;
    }
    
    /**
     * GETTER / SETTER 
     * @return 
     */
    
    /* Sur l'ID */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /* Sur l'expediteur */
    public User getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(User expediteur) {
        this.expediteur = expediteur;
    }

    /* Sur le recepteur */
    public User getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(User recepteur) {
        this.recepteur = recepteur;
    }

    /* Sur le contenu */
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
        
}
