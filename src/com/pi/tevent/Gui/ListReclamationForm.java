/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Reclamation;
import com.pi.tevent.Services.ReclamationServices;

/**
 *
 * @author Salim
 */
public class ListReclamationForm extends BaseForm {

    public ListReclamationForm(Resources res) {
        setTitle("Liste Reclamation");
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ReclamationServices.getInstance().getAllReclamation().toString());
        add(sp);
    }
    
    public Container item(Reclamation r){
        Container holder = new Container(BoxLayout.x());
        
        Label lsujet = new Label(""+r.getSujet()) ;
        Label lcontenu= new Label(r.getContenu()) ;
        Label letat= new Label(r.getEtat()) ;
      //  Label lVille_arrivee= new Label(db.getVille_arrivee()) ;
        //Label lJour_location = new Label(db.getJour_location().toString());
        
       holder.add(lsujet).add(lcontenu).add(letat);
       /*lsujet.addPointerPressedListener(evt ->{
                 new ListReclamationForm(res).show();
       });*/
       holder.setLeadComponent(lsujet); // pour tout les composant de holder aient le meme comportement que lsujet
       
        return holder;
    }
    
}
