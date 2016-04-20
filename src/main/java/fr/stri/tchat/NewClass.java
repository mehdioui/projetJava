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
public class NewClass {

    public static void main(String[] args) {
        Salon salon = new Salon();
        Collaborateur collaborateur = new Collaborateur();
        Message message = new Message(collaborateur, salon, "titi");
        Message message1 = new Message(collaborateur, collaborateur, "tata");
    }
}
