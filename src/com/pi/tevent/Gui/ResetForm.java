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
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.Image;
import com.pi.tevent.utils.SessionUser;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.Services.UtilisateurServices;
import com.codename1.ui.Dialog;
/**
 *
 * @author hanto
 */
public class ResetForm extends BaseForm{

    public ResetForm(Resources res){
         super(new BorderLayout());
        
          Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        Utilisateur user =  SessionUser.getUser();
        Image img = res.getImage("about1.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

       /* Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);*/
        
        add(BorderLayout.NORTH,LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            new Label(),
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("pdp.jpg"), "PictureWhiteBackground")),
                           new Label()
                    )
                )
        ));

        
;
        
        TextField pwdAct = new TextField("", "Mot de passe Actuel", 20, TextField.PASSWORD);
        TextField pwd = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField pwd1 = new TextField("", "Confirmer Mot de passe", 20, TextField.PASSWORD);
        pwdAct.setSingleLineTextArea(false);
        pwd.setSingleLineTextArea(false);
        pwd1.setSingleLineTextArea(false);

        Button valid = new Button("Enregistrer");

        
        Container content = BoxLayout.encloseY(
                new FloatingHint(pwdAct),
                createLineSeparator(),
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
            us.getInstance().changerPassword( user.getEmail(),pwdAct,pwd,res);}
        });
}

}
