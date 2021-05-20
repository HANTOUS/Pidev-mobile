/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Salim
 */
public class HomeReclamationForm extends BaseForm {
    
    public HomeReclamationForm(Resources res){
        
        
        setTitle("Home Reclamation");
        setLayout(BoxLayout.y());
        //add(BoxLayout.y(), new Label(res.getImage("Logo-Off.png"), "LogoLabel"));
        


        add(new Label("Choisissez une option s'il vous plait"));
        Button btnAddRec = new Button("Ajouter une Reclamation");
        Button btnListRec = new Button("Liste Reclamation");

        btnAddRec.addActionListener(e-> new AddReclamationForm(res).show());
        btnListRec.addActionListener(e-> new ListReclamationForm(res).show());
        addAll(btnAddRec, btnListRec);

    }
    
}
