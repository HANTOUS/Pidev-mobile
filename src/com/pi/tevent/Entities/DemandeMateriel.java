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
public class DemandeMateriel {
      private int id ;
    private int utilisateur ;
    private int Materiel ;
    private String qte ; 
    private String etat; 
    private Date dateDebut ;
    private Date dateFin ;

    public DemandeMateriel() {
    }

    public DemandeMateriel(int utilisateur, int Materiel, String qte, String etat, Date dateDebut, Date dateFin) {
        this.utilisateur = utilisateur;
        this.Materiel = Materiel;
        this.qte = qte;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public DemandeMateriel(int id, int utilisateur, int Materiel, String qte, String etat, Date dateDebut, Date dateFin) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.Materiel = Materiel;
        this.qte = qte;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public int getMateriel() {
        return Materiel;
    }

    public void setMateriel(int Materiel) {
        this.Materiel = Materiel;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "DemandeMateriel{" + "id=" + id + ", utilisateur=" + utilisateur + ", Materiel=" + Materiel + ", qte=" + qte + ", etat=" + etat + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
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
        final DemandeMateriel other = (DemandeMateriel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.utilisateur != other.utilisateur) {
            return false;
        }
        if (this.Materiel != other.Materiel) {
            return false;
        }
        if (!Objects.equals(this.qte, other.qte)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return true;
    }
    
    
}
