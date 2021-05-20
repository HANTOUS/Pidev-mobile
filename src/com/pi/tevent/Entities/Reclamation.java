/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Entities;

/**
 *
 * @author Salim
 */
public class Reclamation {
     private int id;
    private int user_id;
    private String sujet;
    private String contenu;
    private String etat="En cours";

    public Reclamation(int id, int user_id, String sujet, String contenu) {
        this.id = id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public Reclamation(int user_id, String sujet, String contenu) {
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public Reclamation() {
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public String getEtat() {
        return etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", sujet=" + sujet + ", contenu=" + contenu + ", etat=" + etat + '}';
    }
    
    
    
}
