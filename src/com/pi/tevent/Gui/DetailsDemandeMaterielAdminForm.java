/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.messaging.Message;
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
import com.pi.tevent.Entities.DemandeMateriel;
import com.pi.tevent.Services.DemandeBusServices;
import com.pi.tevent.Services.DemandeMaterielServices;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class DetailsDemandeMaterielAdminForm extends BaseForm {

    Resources theme;
    DemandeMaterielServices dms = new DemandeMaterielServices();
    ArrayList<Integer> Stockage ;
    int reste;
    public DetailsDemandeMaterielAdminForm(Resources theme, DemandeMateriel db) {
        super("Detaille Du demande", new BorderLayout());
        this.theme = theme;
//         addGUIs(dc);
//        addActions();
            //CALCUL DE QUANTITE DISPONIBLE POUR CHAQUE MATERIEL
        Stockage = dms.getStock(db.getMateriel());
        reste=Stockage.get(0)-Stockage.get(1);
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
      getTitleArea().setUIID("Container");
        setTitle("Gérer Demande Matériel");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

        tb.addSearchCommand(e -> {
        });

        Image img = theme.getImage("offer3.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(BorderLayout.NORTH, LayeredLayout.encloseIn(
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

        TextField restefield = new TextField(reste+"", "Matériel", 20, TextField.NUMERIC);
        restefield.setUIID("TextFieldBlack");
        
        TextField materiel = new TextField("" + dms.getLabel(db.getMateriel()), "Matériel", 20, TextField.NUMERIC);
        materiel.setUIID("TextFieldBlack");
        // addStringValue("Nom", nom)     
        TextField qte = new TextField("" + db.getQte(), "Quantité", 20, TextField.ANY);
        qte.setUIID("TextFieldBlack");
        TextField date_debut = new TextField("" + db.getDateDebut(), "Date de Debut", 20, TextField.ANY);
        date_debut.setUIID("TextFieldBlack");
        TextField date_fin = new TextField("" + db.getDateDebut(), "Date de Fin ", 20, TextField.ANY);
        date_fin.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);

        TextField etat = new TextField(db.getEtat(), "Etat", 20, TextField.ANY);
        etat.setUIID("TextFieldBlack");
        etat.setEditable(false);
        restefield.setEditable(false);
//addStringValue("Date Naissance", DateN);

        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenter(new Label("Quantité Restante", "PaddedLabel"), restefield),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Matériel", "PaddedLabel"), materiel),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Quantité", "PaddedLabel"), qte),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Date de debut", "PaddedLabel"), date_debut),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Date de fin", "PaddedLabel"), date_fin),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Etat", "PaddedLabel"), etat),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);

        Button refuser = new Button("Refuser demande");
        Button accepter = new Button("Accepter demande");
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                accepter, refuser
        ));
        if (db.getEtat().equals("encours")) {
            accepter.setEnabled(true);
            refuser.setEnabled(true);
        } else {
            accepter.setEnabled(false);
            refuser.setEnabled(false);
        }
        accepter.requestFocus();
        accepter.addActionListener(evt -> {
                      int quantite = Integer.parseInt(qte.getText());

            if (quantite < reste){                        
           
                new DemandeMaterielServices().accepterDemandeMateriel(db.getId());
            etat.setText("accepter");
            restefield.setText(""+(reste-quantite));
            accepter.setEnabled(false);
            refuser.setEnabled(false);
            }else{
            
                Dialog.show("Attention", "Il reste que"+reste, new Command("ok"));

            }
            String mail=new DemandeBusServices().getemail(db.getUtilisateur());
            Message m = new Message("Votre demande a été accepté");
            Display.getInstance().sendMessage(new String[]{mail}, "Demande Matériel", m);
            
        });
        refuser.addActionListener(evt -> {
            new DemandeMaterielServices().refuserDemandeMateriel(db.getId());
            etat.setText("refuser");
            accepter.setEnabled(false);
            refuser.setEnabled(false);

        });

//        mod.addPointerPressedListener(evt ->{
//               try {           DemandeChauffeur uDB = db ;
//
//                          
//                
//                        DemandeChauffeur dChauffeur;
//                        dChauffeur = new DemandeChauffeur(uDB.getId(),1,Integer.parseInt(materiel.getText()),new Date(),new Date(),"encours");
//                        if(new DemandeChauffeurServices().updateDemandeChauffeur(dChauffeur)){
//                            new DetailsDemandeChauffeurForm(theme, dChauffeur, previous);
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
    }

    public void addGUIs(DemandeChauffeur dc) {
        Container holderLabel = new Container(BoxLayout.x());

        Label titreID = new Label("ID");
        Label titreNumPermis = new Label("Num Permis");
        Label titreDatePermis = new Label("Date Permis");
        Label titreDateExp = new Label("Date Exp");
        holderLabel.add(titreID).add(titreNumPermis).add(titreDatePermis).add(titreDateExp);
        Button btnUpdate = new Button("Modifier");
        Label lID = new Label("" + dc.getId());
        Label lEtat = new Label(dc.getEtat());
        Label lNumpermis = new Label("" + dc.getNumPermis());
        Label lDatePermis = new Label(dc.getDatePermis().toString());
        Label lDateExp = new Label(dc.getDateExpiration().toString());
        this.add(lNumpermis).add(lDatePermis).add(lDateExp).add(lEtat).add(btnUpdate);

    }

    public void addActions() {

    }
}
