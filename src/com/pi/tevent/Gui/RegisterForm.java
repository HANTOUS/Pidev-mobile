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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.PickerComponent;
import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;
import com.pi.tevent.Services.UtilisateurServices;
import com.codename1.ui.Dialog;
/**
 *
 * @author hanto
 */
public class RegisterForm extends BaseForm{

     public RegisterForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Login");
                
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prénom", 20, TextField.ANY);
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirmer mot de passe", 20, TextField.PASSWORD);
        TextField cin = new TextField("", "Cin", 9, TextField.NUMERIC);
        Picker dateN = new Picker();
        dateN.setType(Display.PICKER_TYPE_DATE);
       // dateN.setFormatter(mySimpleDateFormat);*/
       // PickerComponent dateN = PickerComponent.createDate(new Date()).label("Date Naissance");
       /* SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        mySimpleDateFormat.format(dateN);*/
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        cin.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button login = new Button("Se connecter");
        login.addActionListener(e -> previous.showBack());
        login.setUIID("Link");
        Label alreadHaveAnAccount = new Label("J'ai déjà un compte");
        
        Container content = BoxLayout.encloseY(
                new Label("S'inscrire", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                new FloatingHint(cin),
                createDLineSeparator(),
              
                //new FloatingHint(),
                dateN,
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, BoxLayout.encloseY(
                createDLineSeparator(),
                content
        ));
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, login)
        ));
        next.requestFocus();
        next.addActionListener(e ->
        {
            if (nom.getText().isEmpty()) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre nom !!!", null, "ok");

            } else if (prenom.getText().isEmpty()) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre prenom !!!", null, "ok");
            } else if (password.getText().isEmpty()) {
               Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre mot de passe !!!", null, "ok");
            } else if (! password.getText().equals(confirmPassword.getText())) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer le meme mot de passe !!!", null, "ok");
            }else if (email.getText().isEmpty()) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre email !!!", null, "ok");
            } else if (cin.getText().isEmpty()) {
                Dialog.show("Vérifiez Vos Champs", "!!!  Vous devez entrer votre numero de carte d'identité !!!", null, "ok");
            } else {
                UtilisateurServices us = new UtilisateurServices();
                us.getInstance().signup( nom, prenom, email, password, cin, dateN,res);
                new ActiveForm(res).show();
            }
        });
    }
}
