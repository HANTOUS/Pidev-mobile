/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Services.CampingServices;
import com.pi.tevent.Services.RandonneeServices;
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
import com.pi.tevent.Entities.Camping;
import com.pi.tevent.Services.CampingServices;
import java.util.ArrayList;

/**
 *
 * @author maale
 */
public class ListCamping extends BaseForm{
  /*   public Resources theme ; 
       Form current;
    public ListCamping(Resources theme,Form previous){
        /*super("List Randonnées ",BoxLayout.y());
        current = this;
        this.theme = theme ; 
        addGUIs();*/
       /* setTitle("List des camping");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(CampingServices.getInstance().getAllTasks().toString());
        add(sp);
                this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }*/
    Form current;
    public Resources theme;
    public ArrayList<Camping> list;
    
    
    public ListCamping (Resources res) {
        
        super( "Liste Campings",BoxLayout.y());
        setTitle("Liste Camping");
        this.theme=theme;
        
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            this.add(createDLineSeparator());
            this.add(createDLineSeparator());
            this.add(createLineSeparator(000000));
            
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setTitle("Liste Camping");
        getTitleArea().setUIID("Container");
        setTitle("List Camping");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
              //Image img = theme.getImage("claim.jpg");
             // ScaleImageLabel sl = new ScaleImageLabel(img);
              //sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
              
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ReclamationServices.getInstance().getAllReclamation().toString());
        add(sp);*/
        
        list = CampingServices.getInstance().getAllTasks();
        
        current = this;
        for (Camping r : list){
            this.add(item(r,CampingServices.getInstance()));
            //System.out.println(r);
        }
       // addGUIs();
    }
    
    public Container item(Camping r,CampingServices cs){

        
        Container holderParent = new Container(BoxLayout.y());
        Container holder = new Container(BoxLayout.x());
        Container holder1 = new Container(BoxLayout.y());
        Container holderID = new Container(BoxLayout.x());
        Container holderSU = new Container(BoxLayout.x());
        Container holderCO = new Container(BoxLayout.x());
        Container holderET = new Container(BoxLayout.x());
        Container holdernb = new Container(BoxLayout.x());
        Container holderloc = new Container(BoxLayout.x());

        Label TID = new Label("Nom Camping :");
        Label TSUJET = new Label("heure de début :");
        Label TCONTENU = new Label("heure de fin :");
        Label TETAT = new Label("lieu : ");
        Label Tnbr = new Label("nonbre de participants : ");
        Label loc = new Label("localisation : ");

        
        Label nom= new Label (""+ r.getNomevent());
        Label SUJET = new Label (""+ r.getHeuredebut());
        Label CONTENU = new Label (""+ r.getHeurefin());
        Label ETAT = new Label (""+ r.getLieu());
        Label nbrpart = new Label (""+ r.getNbmaxparticipant());
        Label localisation = new Label (""+ r.getLocalisation());

        
        

        holderID.add(TID).add(nom);
        holderSU.add(TSUJET).add(SUJET);
        holderCO.add(TCONTENU).add(CONTENU);
        holderET.add(TETAT).add(ETAT);
        holdernb.add(Tnbr).add(nbrpart);
        holderloc.add(loc).add(localisation);


        
        /* supprimer.addActionListener(evt ->{
             
            new CampingServices().deleteComm(TOP);
            new ListCamping(theme).show();
             refreshTheme();
         });*/
         Button supprimer = new Button(FontImage.MATERIAL_DELETE);
         supprimer.addActionListener(evt ->{
             
            cs.deleteComm(r.getId());
            new ListCamping(theme).show();
             refreshTheme();
         });
         holder1.add(holderID).add(holderSU).add(holderCO).add(holderET).add(holdernb).add(holderloc);
         holder.add(holder1).add(supprimer);
         holderParent.add(holder).add(createLineSeparator(000000));

        
        return holderParent;
        
    }
    
  /*  public void addGUIs(){
        
        Container holderLabel = new Container(BoxLayout.x());
        
        Label TID = new Label ("nom :");
        Label TSUJET = new Label ("Sujet :");
        Label TCONTENU = new Label ("Contenu :");
        Label TETAT = new Label ("Etat :");
        Label Tnbr = new Label("nonbre de participants : ");
                Label loc = new Label("localisation : ");


        holderLabel.add(TID).add(TSUJET).add(TCONTENU).add(TETAT).add(Tnbr).add(loc);
        
        for ( Camping r : list){
            this.add(item(r));
        }
    }    */
}
