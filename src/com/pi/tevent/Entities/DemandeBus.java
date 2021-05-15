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
public class DemandeBus {

    private int id;
    private int utilisateur;
    private int nbParticipant;
    private String villeDepart;
    private String villeArrivee;
    private String heureDepart ;
    private String heureArrivee;
    private String etat;
    private Date jourLocation;

    public DemandeBus() {
    }

    public DemandeBus(int id, int utilisateur, int nbParticipant, String villeDepart, String villeArrivee, String heureDepart, String heureArrivee, String etat, Date jourLocation) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.nbParticipant = nbParticipant;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.etat = etat;
        this.jourLocation = jourLocation;
    }

    public DemandeBus(int utilisateur, int nbParticipant, String villeDepart, String villeArrivee, String heureDepart, String heureArrivee, String etat, Date jourLocation) {
        this.utilisateur = utilisateur;
        this.nbParticipant = nbParticipant;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.etat = etat;
        this.jourLocation = jourLocation;
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

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getJourLocation() {
        return jourLocation;
    }

    public void setJourLocation(Date jourLocation) {
        this.jourLocation = jourLocation;
    }

    @Override
    public String toString() {
        return "DemandeBus{" + "id=" + id + ", utilisateur=" + utilisateur + ", nbParticipant=" + nbParticipant + ", villeDepart=" + villeDepart + ", villeArrivee=" + villeArrivee + ", heureDepart=" + heureDepart + ", heureArrivee=" + heureArrivee + ", etat=" + etat + ", jourLocation=" + jourLocation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final DemandeBus other = (DemandeBus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.utilisateur != other.utilisateur) {
            return false;
        }
        if (this.nbParticipant != other.nbParticipant) {
            return false;
        }
        if (!Objects.equals(this.villeDepart, other.villeDepart)) {
            return false;
        }
        if (!Objects.equals(this.villeArrivee, other.villeArrivee)) {
            return false;
        }
        if (!Objects.equals(this.heureDepart, other.heureDepart)) {
            return false;
        }
        if (!Objects.equals(this.heureArrivee, other.heureArrivee)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.jourLocation, other.jourLocation)) {
            return false;
        }
        return true;
    }

   

}
