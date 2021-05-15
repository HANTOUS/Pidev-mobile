/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Services.DemandeBusServices;
import java.util.Date;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class AddDemandeBusForm extends BaseForm {

    Resources theme;
    Container holder = new Container(BoxLayout.x());

    Label lNb_participants = new Label("Nombre de participants");
    Label lVille_depart= new Label("Ville de Depart");
    Label lVille_arrivee= new Label("Ville d'arrivée");
    Label lHeure_depart= new Label("Heure de Depart");
    Label lHeure_arrivee= new Label("Heure d'Arrivée");
    Label lJour_location= new Label("Jour de Location");
    private TextField tfNb_participants;

    private Button addbtn;
    Picker jourLocation = new Picker();
    Picker heureDepart = new Picker();
    Picker heureArrivee = new Picker();
    Picker villeDepart = new Picker();
    Picker villeArrivee = new Picker();

    public AddDemandeBusForm(Resources theme) {
        super("Demande Bus", BoxLayout.y());
        this.theme = theme;
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        this.add(createDLineSeparator());
        this.add(createDLineSeparator());
        add(createLineSeparator(000000));
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Demande Bus");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

        addGUIs();
        addActions();

    }

    public void addGUIs() {
        tfNb_participants = new TextField(null, "Nombre de participants");
//                   holder.add(lNb_participants).add(tfNb_participants);
        tfNb_participants.setSingleLineTextArea(false);
        heureDepart.setType(Display.PICKER_TYPE_TIME);
        heureArrivee.setType(Display.PICKER_TYPE_TIME);
        villeDepart.setType(Display.PICKER_TYPE_STRINGS);
        villeArrivee.setType(Display.PICKER_TYPE_STRINGS);
        jourLocation.setType(Display.PICKER_TYPE_DATE);
        villeDepart.setStrings("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Mednine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
        villeArrivee.setStrings("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Mednine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        jourLocation.setFormatter(formatter);
        jourLocation.setDate(new Date());
        heureDepart.setTime(5 * 60); // 10:00AM = Minutes since midnight
        heureArrivee.setTime(18 * 60); // 10:00AM = Minutes since midnight
        villeDepart.setSelectedString("Sousse");
        villeArrivee.setSelectedString("Jendouba");
        /*tfVille_depart = new TextField(null,"Ville Depart");
        tfVille_arrivee = new TextField(null,"Ville Arrivée");
        tfHeure_depart = new TextField(null,"Heure Depart");
        tfHeure_arrivee = new TextField(null,"Heure Arrivée");
        tfJour_location = new TextField(null,"Jour de Location");*/
        addbtn = new Button("Ajouter");
        this    .add(lNb_participants)
                .add(tfNb_participants)
                .add(lVille_depart)
                .add(villeDepart)
                .add(lVille_arrivee)
                .add(villeArrivee)
                .add(lHeure_depart)
                .add(heureDepart)
                .add(lHeure_arrivee)
                .add(heureArrivee)
                .add(lJour_location)
                .add(jourLocation)
                .add(addbtn);
    }

    public void addActions() {
        addbtn.addActionListener(evt -> {
            if (isValidate()) {
                try {
                    int hd = Integer.parseInt(heureDepart.getValue().toString()) / 60;
                    int ha = Integer.parseInt(heureArrivee.getValue().toString()) / 60;
                    DemandeBus db = new DemandeBus(1, Integer.parseInt(tfNb_participants.getText()), villeDepart.getValue().toString(), villeArrivee.getValue().toString(), "" + hd, ha + "", "encours", jourLocation.getDate());
                    if (new DemandeBusServices().addDemandeBus(db)) {
                        Dialog.show("Succés", "Demande Ajoutée ", new Command("ok"));
                        new ListDemandeBusForm(theme).show();

                    } else {
                        Dialog.show("error", "Server ERROR ", new Command("ok"));

                    }
                } catch (Exception e) {
                    Dialog.show("error", "Nombre de participants doit etre un entier ", new Command("ok"));
                }
            }
        });

    }

    private void showDialog(String message) {
        Dialog.show("Attention", message, "OK", null);
    }
    //Remplissage des champs obligatoire

    private boolean isValidate() {
        boolean isValid = true;
        String message = "";

        //System.out.println("gui.EventController.ajouter()" + comboType.getValue());
        if (tfNb_participants.getText().isEmpty()) {
            isValid = false;
            message = "Entrer un nombre de participants";
        } else if (villeDepart.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une ville depart";
        } else if (villeArrivee.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une ville arrivee ";

        } else if (villeArrivee.getValue() == villeDepart.getValue()) {
            isValid = false;
            message = "La ville depart doit être differente de la ville d'arrivée ";

        } else if (heureDepart.getText().isEmpty()) {
            isValid = false;
            message = "Heure Depart réquis";
        } else if (heureArrivee.getText().isEmpty()) {
            isValid = false;
            message = "Heure Arrivee requis";
        } else if (jourLocation.getValue() == null) {
            isValid = false;
            message = "Jour de location requis";
        }
        //else if (comboEtat.getValue() == null) {
//            isValid = false;
//            message = "Veuillez selectionnez type etat";
//        }

        if (!isValid) {
            showDialog(message);
        }

        return isValid;

    }

}
