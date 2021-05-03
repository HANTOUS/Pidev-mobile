/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.tevent.Gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
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
            InteractionDialog nameDialog = new InteractionDialog();
            nameDialog.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));

            // Hint for the user
            SpanLabel hintLabel = new SpanLabel("Indiquer le code");
            hintLabel.setTextUIID("PaddedLabel");

            TextField code = new TextField(
                    //chosenAlarm.name.get() == null ? "Ma destination préférée" : chosenAlarm.name.get()
            );
           // nameTf.setUIID(textFieldStyleName);
           // System.err.println("The textfield colour is " + nameTf.getUnselectedStyle().getFgColor());

            // Validate text button
            Button valid = new Button("Valider");
            Button annuler = new Button("Annuler");
            //validateNameButton.setUIID("ValidateButton");
            Container nameButtons = BoxLayout.encloseX(valid,annuler);

            valid.addActionListener((ex) -> new ForgotForm(res).show());
            
            annuler.addActionListener((ex1) -> new EmailForm(res).show());
          
            nameDialog.addComponent(BorderLayout.CENTER, code);
            nameDialog.addComponent(BorderLayout.NORTH, BoxLayout.encloseY(createLineSeparator(),hintLabel));
            // The buttons will be centered
            nameDialog.addComponent(BorderLayout.SOUTH, BorderLayout.centerCenter(nameButtons));

            // Shows the dialog in the center of the screen   
            nameDialog.showPopupDialog(envoyer);
            });
    }
    
}
