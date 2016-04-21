/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;

/**
 *
 * @author thomas
 */
public class Message {

    Collaborateur expediteur;
    Recepteur recipi;
    
    String contenue;

    public Message(Collaborateur expediteur, Recepteur recipi, String contenue) {
        this.expediteur = expediteur;
        this.recipi = recipi;
        this.contenue = contenue;
    }
    
}
