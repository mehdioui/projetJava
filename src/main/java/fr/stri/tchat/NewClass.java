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
public class NewClass{

    public static void main(String[] args) {
        Salon salon = new Salon("Projet 1");
        User collaborateur = new User(1, "User1", "pass1");
        Message message1 = new Message(collaborateur, collaborateur, "tata");
        WindowAccueil fenetreAccueil = new WindowAccueil();
        
        /* Affichage de la fenetre d'accueil */
        fenetreAccueil.setVisible(true);
        
        /* Recupération du login et mot de passe lorsqu'on clique sur Connexion */
        
    }
}
