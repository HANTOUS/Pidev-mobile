/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
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
import com.pi.tevent.Entities.DemandeChauffeur;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.Services.DemandeChauffeurServices;
import com.pi.tevent.utils.SessionUser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class ListDemandeChauffeurForm extends BaseForm {
    public Resources theme ; 
        ArrayList<DemandeChauffeur> list;
Form current;
Utilisateur user;
    public ListDemandeChauffeurForm(Resources theme){
        super("List Demande Chauffeur",BoxLayout.y());
                 user =  SessionUser.getUser();
        Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        this.add(createDLineSeparator());
this.add(createDLineSeparator());
add(createLineSeparator(000000));
getTitleArea().setUIID("Container");
        setTitle("List Demande Chauffeur");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        this.theme = theme ; 
                list = DemandeChauffeurServices.getInstance().getDemandeChauffeurByUser(user.getId());

        addGUIs();
//                        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }
    int id;
    int numpermis ;
    Date dateExp;
    Date datePermis;
    String Etat;
    
            
    public Container item(DemandeChauffeur dc){
        Image img = theme.getImage("chauffeur.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 15) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() /15);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Container holderParent = new Container(BoxLayout.x());
        Container holder = new Container(BoxLayout.y());
        Container holder1 = new Container(BoxLayout.x());
        Container holder2 = new Container(BoxLayout.x());
        Label t1 = new Label("Numéro de permis") ;
        Label t2 = new Label("Date Permis");
        Label lNumpermis = new Label(""+dc.getNumPermis()) ;
                lNumpermis.setUIID("NewsTopLine");

        Label lDatePermis= new Label(dc.getDatePermis().toString()) ;
                lDatePermis.setUIID("NewsTopLine");

        Label lDateExp= new Label(dc.getDateExpiration().toString()) ;

        
         holder1.add(t1).add(lNumpermis);
         holder2.add(t2).add(lDatePermis);
       holder.add(holder1).add(holder2).add(createLineSeparator(000000));
        holderParent.add(img).add(holder);
         lNumpermis.addPointerPressedListener(evt ->{
         new DetailsDemandeChauffeurForm(theme,dc).show();
        });
       holderParent.setLeadComponent(lNumpermis); // pour tout les composant de holder aient le meme comportement que lNb_particip
       
        return holderParent;
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
