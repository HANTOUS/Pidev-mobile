/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.Services.DemandeBusServices;
import com.pi.tevent.utils.SessionUser;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class DetailsDemandeBusForm extends BaseForm{
    private ImageViewer img ;
                 String str="" ;
        Button updateBtn ;
        private TextField lNb_participants  ;
        private TextField lVille_depart ;
        private TextField lVille_arrivee ;
        private TextField lHeure_depart ;
        private TextField lHeure_arrivee ;
        private TextField lJour_location ;
        private TextField lEtat ;
        private Resources theme;
        Utilisateur user ;
        
      
    public DetailsDemandeBusForm(Resources theme ,DemandeBus db){
        super("Detaille Du demande",new BorderLayout());
                            user =  SessionUser.getUser();
Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getTitleArea().setUIID("Container");
        setTitle("Detaille Demande Bus");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
                super.addSideMenu(theme);

        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("destination7.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
         add(BorderLayout.NORTH,LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            new Label(),
//                            FlowLayout.encloseCenter(
//                                new Label(theme.getImage("pdp.jpg"), "PictureWhiteBackground")),
                           new Label()
                    )
                )
        ));
        this.theme=theme;
        //addGUIs(db);
        addActions();
        
          TextField nb_participant = new TextField(""+db.getNbParticipant(),"Nombre de participants",20,TextField.NUMERIC);
        nb_participant.setUIID("TextFieldBlack");
       // addStringValue("Nom", nom);
        
        TextField ville_depart = new TextField(""+db.getVilleDepart(),"Ville Depart");
        ville_depart.setUIID("TextFieldBlack");
        //addStringValue("Prenom", prenom);

        TextField ville_arrivee = new TextField(""+db.getVilleArrivee(), "Ville Arrivée", 20, TextField.ANY);
        ville_arrivee.setUIID("TextFieldBlack");
        //addStringValue("Email", email);
        
        TextField heure_depart = new TextField(""+db.getHeureDepart(), "Heure Depart", 20, TextField.ANY);
        heure_depart.setUIID("TextFieldBlack");
        //addStringValue("CIN", cin);
        
        TextField heure_arrivee = new TextField(""+db.getHeureArrivee(), "Heure Arrivée", 20, TextField.ANY);
        heure_arrivee.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        TextField jour_loc = new TextField(""+db.getJourLocation(), "Jour de Location", 20, TextField.ANY);
        jour_loc.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        TextField etat = new TextField(db.getEtat(), "Etat", 20, TextField.ANY);
        etat.setUIID("TextFieldBlack");
        etat.setEditable(false);
//addStringValue("Date Naissance", DateN);
        
        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenter(new Label("Nombre de participants", "PaddedLabel"), nb_participant),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Ville Depart", "PaddedLabel"), ville_depart),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Ville Arrivée", "PaddedLabel"), ville_arrivee),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Heure Depart", "PaddedLabel"), heure_depart),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Heure Arrivée", "PaddedLabel"), heure_arrivee),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Jour Location", "PaddedLabel"), jour_loc),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Etat", "PaddedLabel"), etat),
                createLineSeparator()
               
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        
        Button mod = new Button("Modifier");
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
               mod
        ));
        mod.requestFocus();
        mod.addActionListener(e -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier cette demande ?" ,"Oui", "Non");
            if(x)
             {  DemandeBus uDB = db ;

                          
                        int hd= Integer.parseInt(heure_depart.getText())/60;
                        int ha= Integer.parseInt(heure_arrivee.getText())/60;
                        DemandeBus dbus;
                        dbus = new DemandeBus(uDB.getId(),user.getId(),Integer.parseInt(nb_participant.getText()),ville_depart.getText(),ville_arrivee.getText(),heure_depart.getText(),heure_arrivee.getText(),"encours",new Date());
                        if(new DemandeBusServices().updateDemandeBus(dbus)){
                            new DetailsDemandeBusForm(theme, dbus).show();}
                // fs.modifierProfile(user.getId(), email.getText(), num.getText());
                 Dialog.show("Success", "modification a été effectué avec succéee" ,"Ok",null);
             }
            });
        
//        mod.addPointerPressedListener(evt ->{
//               try {           DemandeBus uDB = db ;
//
//                          
//                        int hd= Integer.parseInt(heure_depart.getText())/60;
//                        int ha= Integer.parseInt(heure_arrivee.getText())/60;
//                        DemandeBus dbus;
//                        dbus = new DemandeBus(uDB.getId(),1,Integer.parseInt(nb_participant.getText()),ville_depart.getText(),ville_arrivee.getText(),heure_depart.getText(),heure_arrivee.getText(),"encours",new Date());
//                        if(new DemandeBusServices().updateDemandeBus(dbus)){
//                            new DetailsDemandeBusForm(theme, dbus, previous);
//                              Dialog.show("Succés","Demande Modifié ", new Command("ok"));
//                            
//                        }else{
//                            Dialog.show("error","Server ERROR ", new Command("ok"));
//                             }
//                    } catch (Exception e) {
//                        Dialog.show("error","Nombre de participants doit etre un entier ", new Command("ok"));
//                        System.out.println(e.getMessage());
//                    }
//            
//        });
                
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());
    }
    
    public void addGUIs(  DemandeBus db){
         
        lNb_participants = new TextField("Nombre de participants :"+db.getNbParticipant());              
         lVille_depart = new TextField("Ville Depart :"+db.getVilleDepart());              
         lVille_arrivee = new TextField("Ville Arrivée :"+db.getVilleArrivee());              
         lHeure_depart = new TextField("Heure Depart :"+db.getHeureDepart());    
         
         lEtat = new TextField("Etat :"+db.getEtat());              
         lHeure_arrivee = new TextField("Heure Arrivée :"+db.getHeureArrivee());              
         lJour_location = new TextField("Jour Location : "+db.getJourLocation().toString()); 
         updateBtn = new Button("Modifier");

         this.add(lNb_participants).add(lVille_depart).add(lVille_arrivee).add(lHeure_depart).add(lHeure_arrivee).add(lJour_location).add(updateBtn);
    }
    
    public void addActions(){
        
        
    
    }
    
    public void addComponent(DemandeBus db){
        
        TextField nb_participant = new TextField(""+db.getNbParticipant(),"Nombre de participants");
        nb_participant.setUIID("TextFieldBlack");
       // addStringValue("Nom", nom);
        
        TextField ville_depart = new TextField(""+db.getVilleDepart(),"Ville Depart");
        ville_depart.setUIID("TextFieldBlack");
        //addStringValue("Prenom", prenom);

        TextField ville_arrivee = new TextField(""+db.getVilleArrivee(), "Ville Arrivée", 20, TextField.ANY);
        ville_arrivee.setUIID("TextFieldBlack");
        //addStringValue("Email", email);
        
        TextField heure_depart = new TextField(""+db.getHeureDepart(), "Heure Depart", 20, TextField.ANY);
        heure_depart.setUIID("TextFieldBlack");
        //addStringValue("CIN", cin);
        
        TextField heure_arrivee = new TextField(""+db.getHeureArrivee(), "Heure Arrivée", 20, TextField.ANY);
        heure_arrivee.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        TextField jour_loc = new TextField(""+db.getJourLocation(), "Jour de Location", 20, TextField.ANY);
        jour_loc.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        TextField etat = new TextField(db.getEtat(), "Etat", 20, TextField.ANY);
        etat.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenter(new Label("Nombre de participants", "PaddedLabel"), nb_participant),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Ville Depart", "PaddedLabel"), ville_depart),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Ville Arrivée", "PaddedLabel"), ville_arrivee),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Heure Depart", "PaddedLabel"), heure_depart),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Heure Arrivée", "PaddedLabel"), heure_arrivee),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Jour Location", "PaddedLabel"), jour_loc),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Etat", "PaddedLabel"), etat),
                createLineSeparator()
               
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        
        Button mod = new Button("Modifier");
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                mod
        ));
        mod.requestFocus();
        mod.addActionListener(e -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier cette demande ?" ,"Oui", "Non");
            if(x)
             { 
                // fs.modifierProfile(user.getId(), email.getText(), num.getText());
                 Dialog.show("Success", "modification a été effectué avec succéee" ,"Ok",null);
             }
            });
    }
    
    
}
