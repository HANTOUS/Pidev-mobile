/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author DELL
 */
public class DemandesAdminForm extends BaseForm {
    Button AListBus ;
    Button AListMateriel ;
    Button AListChauff ;
    Resources theme ;
    public DemandesAdminForm(Resources theme){
        super("Gestion demandes",BoxLayout.y());
        this.theme=theme ;
        
        AListBus = new Button("Gérer demande bus");
        AListMateriel = new Button("Gérer demande matériel");
        AListChauff = new Button("Gérer demande chauffeur");
        
        AListChauff.addActionListener(evt -> new ListDemandeChauffeurAdminForm(theme).show() );
        AListBus.addActionListener(evt -> new ListDemandeBusAdminForm(theme).show() );
        AListMateriel.addActionListener(evt -> new ListDemandeMaterielAdminForm(theme).show() );
        
        this.addAll(AListChauff,AListBus,AListMateriel);
        
    }
}
