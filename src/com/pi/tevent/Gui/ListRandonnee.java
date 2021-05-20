/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Camping;
import com.pi.tevent.Entities.Randonnee;
import com.pi.tevent.Services.CampingServices;
import com.pi.tevent.Services.RandonneeServices;
import java.util.ArrayList;

/**
 *
 * @author maale
 */
public class ListRandonnee extends BaseForm{
      
       Form current;
    public Resources theme;
    public ArrayList<Randonnee> list;
    
    
    public ListRandonnee (Resources res) {
        
        super( "Liste randonnee",BoxLayout.y());
        setTitle("Liste randonnee");
        this.theme=theme;
        
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            this.add(createDLineSeparator());
            this.add(createDLineSeparator());
            this.add(createLineSeparator(000000));
            
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setTitle("Liste randonnee");
        getTitleArea().setUIID("Container");
        setTitle("List randonnee");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
              //Image img = theme.getImage("claim.jpg");
             // ScaleImageLabel sl = new ScaleImageLabel(img);
              //sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
              
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ReclamationServices.getInstance().getAllReclamation().toString());
        add(sp);*/
        
        list = RandonneeServices.getInstance().getAllTasks();
        
        current = this;
        for (Randonnee r : list){
            this.add(item(r));
            //System.out.println(r);
        }
        addGUIs();
    }
    
    public Container item(Randonnee r){
        Button supprimer = new Button(FontImage.MATERIAL_DELETE);

        
        Container holderParent = new Container(BoxLayout.y());
        Container holder = new Container(BoxLayout.x());
        Container holder1 = new Container(BoxLayout.y());
        Container holderID = new Container(BoxLayout.x());
        Container holderSU = new Container(BoxLayout.x());
        Container holderCO = new Container(BoxLayout.x());
        Container holderET = new Container(BoxLayout.x());
        Container holdernb = new Container(BoxLayout.x());
        Container holderloc = new Container(BoxLayout.x());

        Label TID = new Label("Nom Randonnee :");
        Label TSUJET = new Label("heure de début :");
        Label TCONTENU = new Label("heure de fin :");
        Label TETAT = new Label("lieu : ");
        Label Tnbr = new Label("nonbre de participants : ");
        Label loc = new Label("type de randonnéé : ");

        
        Label nom= new Label (""+ r.getNomevent());
        Label SUJET = new Label (""+ r.getHeuredebut());
        Label CONTENU = new Label (""+ r.getHeurefin());
        Label ETAT = new Label (""+ r.getLieu());
        Label nbrpart = new Label (""+ r.getNbmaxparticipant());
        Label localisation = new Label (""+ r.getTyperand());

        
        

        holderID.add(TID).add(nom);
        holderSU.add(TSUJET).add(SUJET);
        holderCO.add(TCONTENU).add(CONTENU);
        holderET.add(TETAT).add(ETAT);
        holdernb.add(Tnbr).add(nbrpart);
        holderloc.add(loc).add(localisation);


        
        /* supprimer.addActionListener(evt ->{
             
            new RandonneeServices().deleteComm(TOP);
            new ListRandonnee(theme).show();
             refreshTheme();
         });*/
         
         holder1.add(holderID).add(holderSU).add(holderCO).add(holderET).add(holdernb).add(holderloc);
         holder.add(holder1)/*.add(supprimer)*/;
         holderParent.add(holder).add(createLineSeparator(000000));

        
        return holderParent;
        
    }
    
    public void addGUIs(){
        
        Container holderLabel = new Container(BoxLayout.x());
        
        Label TID = new Label ("nom :");
        Label TSUJET = new Label ("Sujet :");
        Label TCONTENU = new Label ("Contenu :");
        Label TETAT = new Label ("Etat :");
        Label Tnbr = new Label("nonbre de participants : ");
                Label loc = new Label("localisation : ");


        holderLabel.add(TID).add(TSUJET).add(TCONTENU).add(TETAT).add(Tnbr).add(loc);
        
        for ( Randonnee r : list){
            this.add(item(r));
        }
    }    
   /* public ListRandonnee(Resources theme,Form previous){
        /*super("List Randonnées ",BoxLayout.y());
        current = this;
        this.theme = theme ; 
        addGUIs();*/
  /*      setTitle("List des Randonnées");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(RandonneeServices.getInstance().getAllTasks().toString());
        add(sp);
              //  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }*/
       
    
  /*  public Container item(Randonnee db){
        Container holder = new Container(BoxLayout.x());
        
        Label lNomevent = new Label(""+db.getNomevent()) ;
        Label ltyperand= new Label(db.getTyperand()) ;
      //  Label lVille_arrivee= new Label(db.getVille_arrivee()) ;
        //Label lJour_location = new Label(db.getJour_location().toString());
        
       holder.add(lNomevent).add(ltyperand);
       lNomevent.addPointerPressedListener(evt ->{
                 new DetailsRandonneeForm(theme,db,current ).show();
       });
       holder.setLeadComponent(lNomevent); // pour tout les composant de holder aient le meme comportement que lNb_particip
       
        return holder;
    }*/
    /*
    public void addGUIs(){
                Container holderLabel = new Container(BoxLayout.x());
           
      
        Label titreNb = new Label("Nom de randonnée") ;
        Label typerand= new Label("type de randonnée") ;
       // Label titreVille_arrivee= new Label("V.arrivée") ;
        //Label titreJour_location = new Label("J.location");
         holderLabel.add(titreNb).add(typerand);
        //this.add(item("14","Tunis","Sousse","14/05/2020","14:00","19:00","encours"));
        //this.add(item("120","Gabes","Sfax","14/05/2025","13:00","19:00","accepter"));
        this.add(holderLabel);
        
        this.add(item(new Randonnee("à velo","houssem randonnée")));
    }
    
    public void addActions(){
        
    }*/
}
