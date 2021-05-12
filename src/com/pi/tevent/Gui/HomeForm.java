/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author DELL
 */
public class HomeForm extends BaseForm{
    Resources theme;
    Button ListBus ;
    Button ListChauffeur ;
    Button ListMateriel ;
    Button passerdb ;
    Button passerdc ;
    Button passerdm ;
    Button AListBus ;
    Button AListMateriel ;
    Button AListChauff ;
    public HomeForm(Resources theme){
        super("PAGE D'ACCEUIL", BoxLayout.y());
        this.theme = theme ;
        addGUIs();
        addActions();
        
    }

    public void addGUIs() {
        ListBus = new Button("LIST BUS");
        ListChauffeur = new Button("LIST Chauffeur");
        ListMateriel = new Button("LIST Materiel");
        passerdb = new Button("Passer demande bus");
        passerdc = new Button("Passer demande chauffeur");
        passerdm = new Button("Passer demande Materiel");
        AListBus = new Button("Admin Bus");
        AListMateriel = new Button("Admin Materiel");
        AListChauff = new Button("Admin Chauffeur");
        
        
       this.addAll(ListBus,ListChauffeur,ListMateriel,AListChauff,AListBus,AListMateriel,passerdc,passerdm,passerdb);
//       this.add(passerdc,passerdm).add(passerdb);
    }
    public void addActions( ){
        ListBus.addActionListener(evt -> new ListDemandeBusForm(theme).show() );
        ListChauffeur.addActionListener(evt -> new ListDemandeChauffeurForm(theme).show() );
        ListMateriel.addActionListener(evt -> new ListDemandeMaterielForm(theme).show() );
        AListChauff.addActionListener(evt -> new ListDemandeChauffeurAdminForm(theme).show() );
        AListBus.addActionListener(evt -> new ListDemandeBusAdminForm(theme).show() );
        AListMateriel.addActionListener(evt -> new ListDemandeMaterielAdminForm(theme).show() );
        passerdc.addActionListener(evt -> new AddDemandeChauffeurForm(theme).show() );
        passerdb.addActionListener(evt -> new AddDemandeBusForm(theme).show() );
        passerdm.addActionListener(evt -> new AddDemandeMaterielForm(theme).show() );

    }
}
