/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.pi.tevent.Entities.DemandeChauffeur;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.Services.DemandeChauffeurServices;
import com.pi.tevent.utils.SessionUser;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class DetailsDemandeChauffeurForm extends BaseForm {
    Resources theme ;
    Utilisateur user ;
    public DetailsDemandeChauffeurForm(Resources theme, DemandeChauffeur db){
                super("Detaille Du demande",new BorderLayout());
                             user =  SessionUser.getUser();
    this.theme =theme ;
//         addGUIs(dc);
//        addActions();
        
         Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getTitleArea().setUIID("Container");
        setTitle("Detaille Demande Chauffeur");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("destination1.jpg");
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
        //addGUIs(db);
//        addActions();
        
          TextField num_permis = new TextField(""+db.getNumPermis(),"Num??ro de permis",20,TextField.NUMERIC);
        num_permis.setUIID("TextFieldBlack");
       // addStringValue("Nom", nom)     
        TextField date_permis = new TextField(""+db.getDatePermis(), "Date de Permis", 20, TextField.ANY);
        date_permis.setUIID("TextFieldBlack");
        TextField date_expiration = new TextField(""+db.getDatePermis(), "Date d'expiration", 20, TextField.ANY);
        date_expiration.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        TextField etat = new TextField(db.getEtat(), "Etat", 20, TextField.ANY);
        etat.setUIID("TextFieldBlack");
        etat.setEditable(false);
//addStringValue("Date Naissance", DateN);
        
        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenter(new Label("Numero de permis", "PaddedLabel"), num_permis),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Ville Depart", "PaddedLabel"), date_permis),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Ville Arriv??e", "PaddedLabel"), date_expiration),
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
        mod.addActionListener(evt -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier cette demande ?" ,"Oui", "Non");
            if(x)
             {  try {           DemandeChauffeur uDB = db ;

                          
                
                        DemandeChauffeur dChauffeur;
                        dChauffeur = new DemandeChauffeur(uDB.getId(),user.getId(),Integer.parseInt(num_permis.getText()),new Date(),new Date(),"encours");
                        if(new DemandeChauffeurServices().updateDemandeChauffeur(dChauffeur)){
                            new DetailsDemandeChauffeurForm(theme, dChauffeur);
                              Dialog.show("Succ??s","Demande Modifi?? ", new Command("ok"));
                            
                        }else{
                            Dialog.show("error","Server ERROR ", new Command("ok"));
                             }
                    } catch (Exception e) {
                        Dialog.show("error","Nombre de participants doit etre un entier ", new Command("ok"));
                        System.out.println(e.getMessage());
                    }
                // fs.modifierProfile(user.getId(), email.getText(), num.getText());
                 Dialog.show("Success", "modification a ??t?? effectu?? avec succ??ee" ,"Ok",null);
             }
            });
        
//        mod.addPointerPressedListener(evt ->{
//               try {           DemandeChauffeur uDB = db ;
//
//                          
//                
//                        DemandeChauffeur dChauffeur;
//                        dChauffeur = new DemandeChauffeur(uDB.getId(),1,Integer.parseInt(num_permis.getText()),new Date(),new Date(),"encours");
//                        if(new DemandeChauffeurServices().updateDemandeChauffeur(dChauffeur)){
//                            new DetailsDemandeChauffeurForm(theme, dChauffeur, previous);
//                              Dialog.show("Succ??s","Demande Modifi?? ", new Command("ok"));
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
    }
    public void addGUIs(DemandeChauffeur dc){
                Container holderLabel = new Container(BoxLayout.x());
           
        Label titreID = new Label("ID") ;
        Label titreNumPermis = new Label("Num Permis") ;
        Label titreDatePermis= new Label("Date Permis") ;
        Label titreDateExp= new Label("Date Exp") ;
         holderLabel.add(titreID).add(titreNumPermis).add(titreDatePermis).add(titreDateExp);
        Button btnUpdate = new Button("Modifier");
        Label lID = new Label(""+dc.getId()) ;
        Label lEtat = new Label(dc.getEtat());
        Label lNumpermis = new Label(""+dc.getNumPermis()) ;
        Label lDatePermis= new Label(dc.getDatePermis().toString()) ;
        Label lDateExp= new Label(dc.getDateExpiration().toString()) ;
        this.add(lNumpermis).add(lDatePermis).add(lDateExp).add(lEtat).add(btnUpdate);
       
        
    }
    
    public void addActions(){
        
    }
}
