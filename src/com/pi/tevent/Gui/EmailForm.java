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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.*;
import java.io.IOException;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.pi.tevent.Services.UtilisateurServices;
import com.codename1.ui.Dialog;

/**
 *
 * @author hanto
 */
public class EmailForm extends BaseForm{
    

    public EmailForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Mot de passe oublié", "LogoLabel")
                )
        );
        
        TextField mail = new TextField("", "Entrer Votre Email", 30, TextField.ANY);
        mail.setSingleLineTextArea(false);
        
        Button envoyer = new Button("Envoyer");
        //Button resend = new Button("Resend");
        //resend.setUIID("CenterLink");
        Label alreadHaveAnAccount = new Label("J'ai déjà un compte");
        Button signIn = new Button("Se Connecter");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(mail),
                createLineSeparator(),
                new SpanLabel("Nous allons envoyer le code de confirmation à votre e-mail. Veuillez vérifier votre boîte de réception", "CenterLabel"),
               // resend,
                 envoyer,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        envoyer.requestFocus();
        envoyer.addActionListener(e -> {
            if (mail.getText().isEmpty()) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre email !!!", null, "ok");
            }
            else{
                System.out.println("emailform");
                UtilisateurServices us = new UtilisateurServices();
                us.getInstance().envoyerMail( mail,res,envoyer);
            }
        });
    }
    
}
