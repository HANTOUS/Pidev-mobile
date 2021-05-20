/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.pi.tevent.Services.CampingServices;
import com.pi.tevent.Services.RandonneeServices;

/**
 *
 * @author maale
 */
public class SuppressionCamping extends Form{
    public SuppressionCamping() {
        setTitle("Suppression camping");
        setLayout(BoxLayout.y());

        TextField tfid = new TextField("", "ID du camping à supprimer");
                Button Ajouter = new Button("Supprimer ");
                Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfid.getText().length() == 0)/*||(tfdate.getText().length()==0)*/) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else 
                
                {
                   int idrand=Integer.parseInt(tfid.getText());
                   CampingServices.getInstance().deleteComm(idrand);
                   Dialog.show("Success","Connection accepted",new Command("OK"));

                    

                }
            }
        });
                                addAll(tfid,Ajouter );


    }
    
}
