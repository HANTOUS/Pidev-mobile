/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeMateriel;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.Services.DemandeMaterielServices;
import com.pi.tevent.utils.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ListDemandeMaterielForm  extends BaseForm{
    public Resources theme ; 
            ArrayList<DemandeMateriel> list;
                DemandeMaterielServices dms = new DemandeMaterielServices();
                Utilisateur user ;
    public ListDemandeMaterielForm(Resources theme){
        super("List Demande Materiel",BoxLayout.y());
                             user =  SessionUser.getUser();

         Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        this.add(createDLineSeparator());
this.add(createDLineSeparator());
add(createLineSeparator(000000));
getTitleArea().setUIID("Container");
        setTitle("List Demande Matériel");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        this.theme = theme ; 
         System.out.println(user.getId());
                        list = DemandeMaterielServices.getInstance().getDemandeMaterielByUser(user.getId());
                        System.out.println(list);
        addGUIs();
//                        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());
    }
    
    public Container item(DemandeMateriel dm){
//                ImageViewer img = new ImageViewer(theme.getImage("camping.png"));
        Image img = theme.getImage("camping.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 15) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() /15);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       
        Container holderParent1 = new Container(BoxLayout.y());
        Container holderParent = new Container(BoxLayout.x());
        Container holderChild = new Container(BoxLayout.x());
        Container holderChild1 = new Container(BoxLayout.y());
        Container holder = new Container(BoxLayout.y());
        Container holder1 = new Container(BoxLayout.x());
        Container holder2 = new Container(BoxLayout.x());
        Label lMateriel = new Label("Materiel :"+dms.getLabel(dm.getMateriel())) ;
        Label lQuantite= new Label("Quantité :"+dm.getQte()) ;
        Label lDateDebut= new Label("Date Debut : "+dm.getDateDebut()) ;
        Label lDateFin= new Label("Date Fin :"+dm.getDateFin()) ;
                        lMateriel.setUIID("NewsTopLine");
                lQuantite.setUIID("NewsTopLine");
                lDateDebut.setUIID("NewsTopLine");
//img.setSize(new Dimension(1,1));
       holder1.add(lMateriel).add(lQuantite);
       holder2.add(lDateDebut);
       holder.add(holder1).add(holder2);
         holderParent.add(img).add(holder);
       lMateriel.addPointerPressedListener(evt ->{
       new DetailsDemandeMaterielForm(theme,dm).show();
       });
       holderParent.setLeadComponent(lMateriel); // pour tout les composant de holder aient le meme comportement que lNb_particip
       holderParent1.add(holderParent).add(createLineSeparator(000000));
        return holderParent1;
    }
    
    public void addGUIs(){
                Container holderLabel = new Container(BoxLayout.x());
           
//        Label titreID = new Label("ID") ;
//        Label titreNumPermis = new Label("Num Permis") ;
//        Label titreDatePermis= new Label("Date Permis") ;
//        Label titreDateExp= new Label("Date Exp") ;
//         holderLabel.add(titreID).add(titreNumPermis).add(titreDatePermis).add(titreDateExp);
        //this.add(item("14","Tunis","Sousse","14/05/2020","14:00","19:00","encours"));
        //this.add(item("120","Gabes","Sfax","14/05/2025","13:00","19:00","accepter"));
       for (DemandeMateriel db : list) {
            this.add(item(db));
        }
        
       
    }
    
    public void addActions(){
        
    }
}
