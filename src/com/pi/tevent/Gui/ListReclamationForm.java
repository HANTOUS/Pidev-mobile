/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Reclamation;
import com.pi.tevent.Services.ReclamationServices;
import java.util.ArrayList;

/**
 *
 * @author Salim
 */
public class ListReclamationForm extends BaseForm {

    Form current;
    public Resources theme;
    public ArrayList<Reclamation> list;
    
    
    public ListReclamationForm(Resources res) {
        
        super( "Liste Reclamations",BoxLayout.y());
        setTitle("Liste Reclamation");
        this.theme=theme;
        
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            this.add(createDLineSeparator());
            this.add(createDLineSeparator());
            this.add(createLineSeparator(000000));
            
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setTitle("Liste Reclamation");
        getTitleArea().setUIID("Container");
        setTitle("List Reclamation");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
              //Image img = theme.getImage("claim.jpg");
             // ScaleImageLabel sl = new ScaleImageLabel(img);
              //sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
              
        
    
        
        list = ReclamationServices.getInstance().getAllReclamation();
        
        current = this;
        for (Reclamation r : list){
            this.add(item(r));
        }
    }
    
    public Container item(Reclamation r){
        Button supprimer = new Button(FontImage.MATERIAL_DELETE);

        
        Container holderParent = new Container(BoxLayout.y());
        Container holder = new Container(BoxLayout.x());
        Container holder1 = new Container(BoxLayout.y());
        Container holderID = new Container(BoxLayout.x());
        Container holderSU = new Container(BoxLayout.x());
        Container holderCO = new Container(BoxLayout.x());
        Container holderET = new Container(BoxLayout.x());
        
        Label TID = new Label("ID reclamation :");
        Label TSUJET = new Label("Suujet :");
        Label TCONTENU = new Label("Contenu :");
        Label TETAT = new Label("Etat : ");
        
        Label ID = new Label (""+ r.getId());
        Label SUJET = new Label (""+ r.getSujet());
        Label CONTENU = new Label (""+ r.getContenu());
        Label ETAT = new Label (""+ r.getEtat());

        holderID.add(TID).add(ID);
        holderSU.add(TSUJET).add(SUJET);
        holderCO.add(TCONTENU).add(CONTENU);
        holderET.add(TETAT).add(ETAT);

         supprimer.addActionListener(evt ->{
             
            new ReclamationServices().deleteReclamation(r);
            new ListReclamationForm(theme).show();
             refreshTheme();
         });
         
         holder1.add(holderID).add(holderSU).add(holderCO).add(holderET);
         holder.add(holder1).add(supprimer);
         holderParent.add(holder).add(createLineSeparator(000000));

        
        return holderParent;
        
    }
    
  /* public void addGUIs(){
        
       /* Container holderLabel = new Container(BoxLayout.x());
        
        Label TID = new Label ("ID :");
        Label TSUJET = new Label ("Sujet :");
        Label TCONTENU = new Label ("Contenu :");
        Label TETAT = new Label ("Etat :");
        holderLabel.add(TID).add(TSUJET).add(TCONTENU).add(TETAT);
        
        for ( Reclamation r : list){
            this.add(item(r));
        }
    }    */
}
