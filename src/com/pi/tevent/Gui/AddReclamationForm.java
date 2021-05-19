/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Reclamation;
import com.pi.tevent.Services.ReclamationServices;

/**
 *
 * @author Salim
 */
public class AddReclamationForm extends BaseForm {

    
    public AddReclamationForm(Resources res) {
        setTitle("Ajouter Reclamation");
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getContentPane().setScrollVisible(false);
        
        setTitle("Ajouter Reclamation");
        setLayout(BoxLayout.y());
        this.add(createDLineSeparator());
this.add(createDLineSeparator());
this.add(createLineSeparator(000000));
        
        TextField tfuser_id = new TextField("","Id user");
        TextField tfsujet = new TextField("","sujet");
        TextField tfcontenu = new TextField("","conenu");
        Button btnAjouter = new Button("Ajouter Reclamation");
    btnAjouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
            if(tfsujet.getText() =="" || tfcontenu.getText() == "")
            Dialog.show("Alert", "verifiez vos données SVP",new Command ("OK"));
            else {
                try {
                    Reclamation r = new Reclamation(Integer.parseInt(tfuser_id.getText()), tfsujet.getText(),tfcontenu.getText());
                if (new ReclamationServices().addRec(r))
                Dialog.show("Success", "Connection accepté",new Command ("OK"));
                else 
                    Dialog.show("Error", "Erreur de Serveur",new Command ("OK"));
                }catch (NumberFormatException e){
                    Dialog.show("Error", "il faut que user id soit un nombre !",new Command ("OK"));
                }
            }
            }
        
    });
    addAll( tfuser_id, tfsujet, tfcontenu, btnAjouter);
    }
    }