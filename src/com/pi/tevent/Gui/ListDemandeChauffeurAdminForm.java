/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeChauffeur;
import com.pi.tevent.Services.DemandeChauffeurServices;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class ListDemandeChauffeurAdminForm extends BaseForm{
     public Resources theme ; 
        ArrayList<DemandeChauffeur> list;
        
Form current;
    public ListDemandeChauffeurAdminForm(Resources theme){
        super("List Demande Chauffeur",BoxLayout.y());
        current=this;
        Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        this.add(createDLineSeparator());
this.add(createDLineSeparator());
add(createLineSeparator(000000));
getTitleArea().setUIID("Container");
        setTitle("List Demandes Chauffeur");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        this.theme = theme ; 
                list = DemandeChauffeurServices.getInstance().getAllDemandeChauffeur();

        addGUIs();

    }
    int id;
    int numpermis ;
    Date dateExp;
    Date datePermis;
    String Etat;
    
            
    public Container item(DemandeChauffeur dc){
                            Button supprimer = new Button("Supprimer");

        Container holder = new Container(BoxLayout.x());
        Container holder1 = new Container(BoxLayout.x());
        Container holder2 = new Container(BoxLayout.x());
        Container holder3 = new Container(BoxLayout.y());
        Label lID = new Label(""+dc.getId()) ;
        Label lEtat = new Label(dc.getEtat());
        Label lNumpermis = new Label(""+dc.getNumPermis()) ;
                lNumpermis.setUIID("NewsTopLine");

        Label lDatePermis= new Label(dc.getDatePermis().toString()) ;
                lDatePermis.setUIID("NewsTopLine");

        Label lDateExp= new Label(dc.getDateExpiration().toString()) ;

         supprimer.addActionListener(evt ->{
            new DemandeChauffeurServices().deleteDemandeChauffeur(dc);
            new ListDemandeChauffeurAdminForm(theme).show();
                    });
         holder1.add(lNumpermis);
         holder2.add(lDatePermis);
         holder3.add(holder1).add(holder2);
       //holder2.add(lDatePermis).add(lDateExp);
//        holder.add(holder1).add(holder2).add("--------------------------------------------------------------");
        holder.add(holder3).add(supprimer).add(createLineSeparator(000000));
         lNumpermis.addPointerPressedListener(evt ->{
         new DetailsDemandeChauffeurAdminForm(theme,dc).show();
        });
       holder3.setLeadComponent(lNumpermis); // pour tout les composant de holder aient le meme comportement que lNb_particip
       
        return holder;
    }
    
    public void addGUIs(){
                Container holderLabel = new Container(BoxLayout.x());
           
        Label titreID = new Label("ID") ;
        Label titreNumPermis = new Label("Num Permis") ;
        Label titreDatePermis= new Label("Date Permis") ;
        Label titreDateExp= new Label("Date Exp") ;
         holderLabel.add(titreID).add(titreNumPermis).add(titreDatePermis).add(titreDateExp);
        
        
         for (DemandeChauffeur db : list) {
            this.add(item(db));
        }
       
    }
    
    public void addActions(){
        
    }
}
