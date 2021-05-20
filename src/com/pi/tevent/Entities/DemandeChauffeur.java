/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Entities;
import java.util.Date;

import java.util.Objects;

/**
 *
 * @author hanto
 */
public class DemandeChauffeur {
    private int id ;
    private int utilisateur ;
    private int numPermis ;
    private Date datePermis ; 
    private Date dateExpiration ;
    private String etat;

    
    public DemandeChauffeur() {
    }

    //constructeur pour ajouter dans la base de données sans id 

    public DemandeChauffeur(int utilisateur, int numPermis, Date datePermis, Date dateExpiration, String etat) {
        this.utilisateur = utilisateur;
        this.numPermis = numPermis;
        this.datePermis = datePermis;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public DemandeChauffeur(int id, int utilisateur, int numPermis, Date datePermis, Date dateExpiration, String etat) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.numPermis = numPermis;
        this.datePermis = datePermis;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getNumPermis() {
        return numPermis;
    }

    public void setNumPermis(int numPermis) {
        this.numPermis = numPermis;
    }

    public Date getDatePermis() {
        return datePermis;
    }

    public void setDatePermis(Date datePermis) {
        this.datePermis = datePermis;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "DemandeChauffeur{" + "id=" + id + ", utilisateur=" + utilisateur + ", numPermis=" + numPermis + ", datePermis=" + datePermis + ", dateExpiration=" + dateExpiration + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DemandeChauffeur other = (DemandeChauffeur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.utilisateur != other.utilisateur) {
            return false;
        }
        if (this.numPermis != other.numPermis) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.datePermis, other.datePermis)) {
            return false;
        }
        if (!Objects.equals(this.dateExpiration, other.dateExpiration)) {
            return false;
        }
        return true;
    }

   
    

}
