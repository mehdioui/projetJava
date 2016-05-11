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
public class User {

    /* ***** ATTRIBUTS ***** */
 /* ID du user */
    int ID;
    /**
     * Login du user *
     */
    String login;
    /* Mot de passe du user */
    String passwd;

    /**
     * CONSTRUCTEUR *
     * @param ID id utilisateur
     * @param login
     * @param passwd
     */
    public User(int ID, String login, String passwd) {
        this.ID = ID;
        this.login = login;
        this.passwd = passwd;
    }

    /* ***** GETTER / SETTER ***** */
 /* Sur l'ID */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /* Sur le login */
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /* Sur le mot de passe */
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
