/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.DemandeChauffeur;
import com.pi.tevent.Entities.DemandeMateriel;
import com.pi.tevent.Services.DemandeChauffeurServices;
import com.pi.tevent.Services.DemandeMaterielServices;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;

/**
 *
 * @author DELL
 */
public class AddDemandeMaterielForm extends BaseForm {

    Container container;
    private TextField quantite;
    Picker Materiel = new Picker();
    Picker dateDebut = new Picker();
    Picker dateFin = new Picker();
    private Button addbtn;
    Label lDateDebut = new Label("Date de debut");
    Label lQuantite = new Label("Quantité");
    Label lMateriel = new Label("Matériel");
    Label lDateFin = new Label("Date de fin");
    Resources theme;
    ComboBox<Map<String, Object>> combo;
    Button b = new Button("GEEEEEEET");
ArrayList<Integer> Stockage ;
    int reste;
        DemandeMaterielServices dms = new DemandeMaterielServices();

    public AddDemandeMaterielForm(Resources theme) {
        super("Demande Matériel", BoxLayout.y());
        this.theme = theme;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        this.add(createDLineSeparator());
        this.add(createDLineSeparator());
        add(createLineSeparator(000000));
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Demande Materiel");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");

        addGUIs();
        addAction();

        combo.setUIID("ComboBox");
        b.addActionListener(e -> {
            System.out.println(combo.getSelectedIndex() + 1);
            System.out.println(combo.getSelectedItem().get("Line1"));
        });

    }

    public void addGUIs() {
        container = new Container(BoxLayout.y());
        quantite = new TextField(null, "Entrer la quantité à reserver");
//                   holder.add(lNb_participants).add(tfNb_participants);
        quantite.setSingleLineTextArea(false);

        Materiel.setType(Display.PICKER_TYPE_STRINGS);
        dateDebut.setType(Display.PICKER_TYPE_DATE);
        dateFin.setType(Display.PICKER_TYPE_DATE);
        dateDebut.setDate(new Date());
        dateFin.setDate(new Date());
        Materiel.setStrings("Tente", "Chaise", "Sac a couchage");
        Materiel.setSelectedString("Tente");
        addbtn = new Button("Ajouter");

        combo = new ComboBox<>(
                createListEntry("Tente"),
                createListEntry("Chaise"),
                createListEntry("Sac à couchage")
        );
        combo.setHint("Choisissez un matériel");

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        container.add(lMateriel).add(combo).add(lQuantite).add(quantite)
                .add(lDateDebut).add(dateDebut).add(lDateFin).add(dateFin).add(addbtn);

        this.add(container);
    }

    public void addAction() {
        addbtn.addActionListener(evt -> {
            if (isValidate()) {
                    //CALCUL DE QUANTITE DISPONIBLE POUR CHAQUE MATERIEL
        Stockage = dms.getStock(combo.getSelectedIndex() + 1);
        reste=Stockage.get(0)-Stockage.get(1);
                try {
                    
                    DemandeMateriel db = new DemandeMateriel(1, combo.getSelectedIndex() + 1, quantite.getText(), "encours", dateDebut.getDate(), dateFin.getDate());
                      int qte=Integer.parseInt(quantite.getText());

            if(qte<reste){
                new DemandeMaterielServices().addDemandeMateriel(db);
                Dialog.show("Succés", "Demande Ajoutée ", new Command("ok"));
                new ListDemandeMaterielForm(theme).show();
                    } else {
                        Dialog.show("Attention", "Il reste que "+""+reste+""+" pour les"+""+dms.getLabel(combo.getSelectedIndex() + 1), new Command("ok"));

                    }
                } catch (Exception e) {
                    Dialog.show("error", "Quantité doit etre un entier ", new Command("ok"));
                }
            }
        });
    }

    private Map<String, Object> createListEntry(String name) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        return entry;
    }

    private void showDialog(String message) {
        Dialog.show("Attention", message, "OK", null);
    }
    //Remplissage des champs obligatoire

    private boolean isValidate() {
        boolean isValid = true;
        String message = "";

        //System.out.println("gui.EventController.ajouter()" + comboType.getValue());
        if (quantite.getText().isEmpty()) {
            isValid = false;
            message = "Veuillez entrer une quantité à reserver";
        } else if (Materiel.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez un materiel";
        } else if (dateDebut.getDate() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date de debut ";

        } else if (dateFin.getDate() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date de fin ";
        }
//        else if (dateFin.getDate().before(dateDebut.getDate())) {
//            isValid = false;
//            message = "La date fin doit etre superieur à la date debut ";
//        }
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
