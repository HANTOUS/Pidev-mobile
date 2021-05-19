/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.tevent.Gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Services.UtilisateurServices;
import com.codename1.ui.Dialog;
/**
 *
 * @author hanto
 */
public class ForgotForm extends BaseForm{

    public ForgotForm(Resources res,TextField token){
         super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("Activate");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo-Off.png"), "LogoLabel"));
        
        TextField pwd = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField pwd1 = new TextField("", "Confirmer Mot de passe", 20, TextField.PASSWORD);
        pwd.setSingleLineTextArea(false);
        pwd1.setSingleLineTextArea(false);

        Button valid = new Button("Valider");

        
        Container content = BoxLayout.encloseY(
                new FloatingHint(pwd),
                createLineSeparator(),
                new FloatingHint(pwd1),
                createLineSeparator(),
                valid
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        valid.requestFocus();
        valid.addActionListener(e -> {
            if (pwd.getText().isEmpty()) {
               Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre mot de passe !!!", null, "ok");
            } else if (! pwd.getText().equals(pwd1.getText())) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer le meme mot de passe !!!", null, "ok");
            }
            else{
            UtilisateurServices us = new UtilisateurServices();
            us.getInstance().resetpass(token,pwd,res);}
        });
}

}
