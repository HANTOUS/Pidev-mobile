/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Services.DemandeBusServices;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ListDemandeBusAdminForm extends BaseForm {
    Form current;
    public Resources theme;
    ArrayList<DemandeBus> list;


    public ListDemandeBusAdminForm(Resources theme) {
        super( "Liste Demande Bus",BoxLayout.y());
                this.theme = theme;
                  Toolbar tb = new Toolbar(true);
            setToolbar(tb);
this.add(createDLineSeparator());
this.add(createDLineSeparator());
add(createLineSeparator(000000));
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
getTitleArea().setUIID("Container");
        setTitle("List Demandes Bus");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

//        
      Image img = theme.getImage("about1.jpg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        //sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        list = DemandeBusServices.getInstance().getAllDemandeBus();
        
        current = this;
//        addGUIs();
         for (DemandeBus db : list) {
            this.add(item(db));
        }

//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }

   
        
    public Container item(DemandeBus db) {
                    Button supprimer = new Button("Supprimer");

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
                        titreNb.setUIID("NewsTopLine");
                        titreVille_depart.setUIID("NewsTopLine");
                        titreVille_arrivee.setUIID("NewsTopLine");
                        titreJour_location.setUIID("NewsTopLine");

        Label lNb_participants = new Label("" + db.getNbParticipant());
        Label lVille_depart = new Label(db.getVilleDepart());
        Label lVille_arrivee = new Label(db.getVilleArrivee());
        Label lUser = new Label(db.getUtilisateur()+"");
        SpanLabel lJour_location = new SpanLabel(db.getJourLocation().toString());
        
        holderNB.add(titreNb).add(lNb_participants);
        holderVD.add(titreVille_depart).add(lVille_depart);
        holderVA.add(titreVille_arrivee).add(lVille_arrivee);
        //holderJL.add(titreJour_location).add(lJour_location);
         supprimer.addActionListener(evt ->{
            new DemandeBusServices().deleteDemandeBus(db);
            //new ListDemandeBusAdminForm(theme).show();
             refreshTheme();
         });

        holder1.add(holderNB).add(holderVD).add(holderVA).add(lUser).add(createLineSeparator(000000));
        
        
        
//       holder.add(lNb_participants).add(lVille_depart).add(lVille_arrivee).add(lJour_location);
        holder.add(holder1).add(supprimer);
        lNb_participants.addPointerPressedListener(evt -> {
            new DetailsDemandeBusAdminForm(theme, db).show();
        });
        holder1.setLeadComponent(lNb_participants); // pour tout les composant de holder aient le meme comportement que lNb_particip

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
