/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
/**
 *
 * @author maale
 */
public class HomeFormEvent extends BaseForm {
        Form current;
      //  Resources theme;
    public HomeFormEvent(Resources theme) {
        current = this;
        this.add(createDLineSeparator());
        this.add(createDLineSeparator());
        setTitle("Acceuil Event");
        
        setLayout(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        super.addSideMenu(theme);
        
        tb.addSearchCommand(e -> {});
        //add(new Label("choisir une option"));
        Button btnAjouterRandonnee = new Button("Ajouter Randonnée");
        
        Button btnAjouterCamping = new Button("Ajouter Camping");
        
                Button btnAfficherRandonnee = new Button("Afficher Randonnée");
                                Button btnAfficherCamping = new Button("Afficher camping");
                   Button btnSupprimerRandonnee = new Button("supprimer randonnee par id");
                   Button btnSupprimerRandonnee2 = new Button("supprimer randonnee par nom");
                   Button btnSupprimerCamping = new Button("supprimer camping par id");



        btnAjouterRandonnee.addActionListener(e -> new AjouterRandonnee(current).show());
        btnAjouterCamping.addActionListener(e -> new AjouterCamping(current).show());
        btnAfficherRandonnee.addActionListener(e -> new ListRandonnee(theme).show());
                btnAfficherCamping.addActionListener(e -> new ListCamping(theme).show());
                btnSupprimerRandonnee.addActionListener(e -> new SuppressionRandonnee().show());
                //btnSupprimerRandonnee2.addActionListener(e -> new SuppressionRandByNom().show());
                btnSupprimerCamping.addActionListener(e -> new SuppressionCamping().show());

        addAll(btnAjouterRandonnee,btnAjouterCamping,btnAfficherRandonnee,btnAfficherCamping,btnSupprimerRandonnee/*,btnSupprimerRandonnee2*/); 

        

        
    }

    
    
}
