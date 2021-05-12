/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Services.DemandeBusServices;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class ListDemandeBusForm extends BaseForm {

    Form current;
    public Resources theme;
    ArrayList<DemandeBus> list;
    
    public ListDemandeBusForm(Resources theme) {
        super( "Liste Demande bus",BoxLayout.y());
                this.theme = theme;
                Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            
this.add(createDLineSeparator());
this.add(createDLineSeparator());
this.add(createLineSeparator(000000));

            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
getTitleArea().setUIID("Container");
        setTitle("List Demande Bus");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

        
        
      Image img = theme.getImage("about1.jpg");

        ScaleImageLabel sl = new ScaleImageLabel(img);
        //sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        list = DemandeBusServices.getInstance().getDemandeBusByUser(1);
        
        current = this;
//        addGUIs();
         for (DemandeBus db : list) {
            this.add(item(db));
        }

//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }

   
        
    public Container item(DemandeBus db) {
        Image img = theme.getImage("bus.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 15) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() /15);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Container holder = new Container(BoxLayout.x());
        Container holder1 = new Container(BoxLayout.y());
        Container holderNB = new Container(BoxLayout.x());
        Container holderVD = new Container(BoxLayout.x());
        Container holderVA = new Container(BoxLayout.x());
        Container holderJL = new Container(BoxLayout.x());
        Label titreNb = new Label("Nombre de participants :");
        Label titreVille_depart = new Label("Ville Depart :");
        Label titreVille_arrivee = new Label("Ville Arrivée :");
        Label titreJour_location = new Label("Jour Location : ");
        
        Label lNb_participants = new Label("" + db.getNbParticipant());
               lNb_participants.setUIID("NewsTopLine");

        Label lVille_depart = new Label(db.getVilleDepart());
               lVille_depart.setUIID("NewsTopLine");

        Label lVille_arrivee = new Label(db.getVilleArrivee());     
        lVille_arrivee.setUIID("NewsTopLine");

        SpanLabel lJour_location = new SpanLabel(db.getJourLocation().toString());   
        lJour_location.setUIID("NewsTopLine");


        holderNB.add(titreNb).add(lNb_participants);
        holderVD.add(titreVille_depart).add(lVille_depart);
        holderVA.add(titreVille_arrivee).add(lVille_arrivee);
        //holderJL.add(titreJour_location).add(lJour_location);
        holder1.add(holderNB).add(holderVD).add(holderVA);
        
        
        
        
//       holder.add(lNb_participants).add(lVille_depart).add(lVille_arrivee).add(lJour_location);
        holder.add(img).add(holder1).add(createLineSeparator(000000));
        lNb_participants.addPointerPressedListener(evt -> {
            new DetailsDemandeBusForm(theme, db).show();
        });
        holder.setLeadComponent(lNb_participants); // pour tout les composant de holder aient le meme comportement que lNb_particip

        return holder;
    }

    public void addGUIs() {
        Container holderLabel = new Container(BoxLayout.x());

        Label titreNb = new Label("Nb");
        Label titreVille_depart = new Label("V.depart");
        Label titreVille_arrivee = new Label("V.arrivée");
        Label titreJour_location = new Label("J.location");
        holderLabel.add(titreNb).add(titreVille_depart).add(titreVille_arrivee).add(titreJour_location);
        //this.add(item("14","Tunis","Sousse","14/05/2020","14:00","19:00","encours"));
        //this.add(item("120","Gabes","Sfax","14/05/2025","13:00","19:00","accepter"));
        //this.add(holderLabel);
        for (DemandeBus db : list) {
            //this.add(item(new DemandeBus(1,50,"Sousse","Tunis","14:00","19:00","encours",new Date())));
            this.add(item(db));
        }
    }

    public void addActions() {

    }
}
