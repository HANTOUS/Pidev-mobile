/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Entities.DemandeChauffeur;
import java.util.Date;
import javafx.scene.control.Alert;
import com.pi.tevent.Gui.AddDemandeBusForm;
import com.pi.tevent.Services.DemandeBusServices;
import com.pi.tevent.Services.DemandeChauffeurServices;

/**
 *
 * @author DELL
 */
public class AddDemandeChauffeurForm extends BaseForm {

    Container container;
    private TextField numPermis;
    Picker datePermis = new Picker();
    Picker dateExpiration = new Picker();
    private Button addbtn;

    Label lDatePermis = new Label("Numéro de permis");
    Label lNumPermis = new Label("Date de permis");
    Label lDateExpiration = new Label("Date d'expiration");
    Resources theme;

    public AddDemandeChauffeurForm(Resources theme) {
        super("Demande Chauffeur", BoxLayout.y());
        this.theme = theme;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        this.add(createDLineSeparator());
        this.add(createDLineSeparator());
        add(createLineSeparator(000000));
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Demande Chauffeur");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

        addGUIs();
        addAction();

    }

    public void addGUIs() {
        container = new Container(BoxLayout.y());
        numPermis = new TextField(null, "Numéro de permis");
//                   holder.add(lNb_participants).add(tfNb_participants);
        numPermis.setSingleLineTextArea(false);

        datePermis.setType(Display.PICKER_TYPE_DATE);
        dateExpiration.setType(Display.PICKER_TYPE_DATE);
        datePermis.setDate(new Date());
        dateExpiration.setDate(new Date());

        addbtn = new Button("Ajouter");

        container.add(lNumPermis).add(numPermis).add(lDatePermis).add(datePermis).add(lDateExpiration).add(dateExpiration).add(addbtn);

        this.add(container);
    }

    public void addAction() {
        addbtn.addActionListener(evt -> {
            if (isValidate()) {
                try {

                    DemandeChauffeur db = new DemandeChauffeur(1, Integer.parseInt(numPermis.getText()), datePermis.getDate(), dateExpiration.getDate(), "encours");
                    if (new DemandeChauffeurServices().addDemandeChauffeur(db)) {
                        Dialog.show("Succés", "Demande Ajoutée ", new Command("ok"));
                        new ListDemandeChauffeurForm(theme).show();

                    } else {
                        Dialog.show("error", "Server ERROR ", new Command("ok"));

                    }
                } catch (Exception e) {
                    Dialog.show("error", "numéro de permis doit etre un entier ", new Command("ok"));
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
        if (numPermis.getText().isEmpty()) {
            isValid = false;
            message = "Veuillez entrer un numero de permis valid";
        } else if (numPermis.getText().length() != 8) {
            isValid = false;
            message = "Le numero de permis doit etre exactement 8 chiffres";
        } else if (datePermis.getDate() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date permis";
        } else if (dateExpiration.getDate() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date d'expiration ";
        }
//        }else if (dateExpiration.getDate().before(datePermis.getDate())) {
//            isValid = false;
//            message = "La date expiration doit être supérieur à la date permis ";
//        }

        if (!isValid) {
            showDialog(message);
        }

        return isValid;

    }
}
