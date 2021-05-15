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

/**
 *
 * @author hanto
 */
public class ActiveForm extends BaseForm {
    
 
    public ActiveForm(Resources res) {
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
                        new Label("Bienvenue", "LogoLabel")
                )
        );
        
        TextField code = new TextField("", "Entrer Code", 30, TextField.ANY);
        code.setSingleLineTextArea(false);
        
        Button login = new Button("Se Connecter");
        //Button resend = new Button("Resend");
        //resend.setUIID("CenterLink");
        Label alreadHaveAnAccount = new Label("J'ai déjà un compte");
       // Button signIn = new Button("Sign In");
       // signIn.addActionListener(e -> previous.showBack());
        //signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(code),
                createLineSeparator(),
                new SpanLabel("Nous avons envoyé le code d'activation à votre e-mail. Veuillez vérifier votre boîte de réception", "CenterLabel"),
               // resend,
                 login
               // FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        login.requestFocus();
        login.addActionListener(e -> new LoginForm(res).show());
    }
    
}
