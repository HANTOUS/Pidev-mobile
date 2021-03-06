/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.*;
import java.io.IOException;
import com.codename1.io.FileSystemStorage;
import com.pi.tevent.utils.SessionUser;
import com.pi.tevent.Entities.Utilisateur;
import java.text.SimpleDateFormat;  
import java.util.Date;
import com.codename1.ui.spinner.Picker;
import com.pi.tevent.Services.UtilisateurServices;
/**
 *
 * @author hanto
 */
public class ProfilForm extends BaseForm{
    
    public ProfilForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
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
       /* try{
            Image photo = Image.createImage(user.getImage());
            if(photo.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
                 photo = photo.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
            }}catch(IOException e){
            e.printStackTrace();
        } 
       */
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
            
        //SessionUser su = new SessionUser();
        
        TextField nom = new TextField(user.getNom());
        nom.setUIID("TextFieldBlack");
       // addStringValue("Nom", nom);
        
        TextField prenom = new TextField(user.getPrenom());
        prenom.setUIID("TextFieldBlack");
        //addStringValue("Prenom", prenom);

        TextField email = new TextField(user.getEmail(), "E-Mail", 20, TextField.ANY);
        email.setUIID("TextFieldBlack");
        //addStringValue("Email", email);
        
        TextField cin = new TextField(user.getCin(), "CIN", 20, TextField.EMAILADDR);
        cin.setUIID("TextFieldBlack");
        //addStringValue("CIN", cin);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");  
        Picker dateN = new Picker();
        dateN.setType(Display.PICKER_TYPE_DATE);
        dateN.setDate(user.getDateNaissance());  
        //TextField DateN = new TextField(dateN, "Daten Naissance", 20, TextField.ANY);
        dateN.setUIID("TextFieldBlack");
        //addStringValue("Date Naissance", DateN);
        
        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenter(new Label("Nom", "PaddedLabel"), nom),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Prenom", "PaddedLabel"), prenom),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Email", "PaddedLabel"), email),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("CIN", "PaddedLabel"), cin),
                createLineSeparator(),
                FlowLayout.encloseCenter(new Label("Date Naissance", "PaddedLabel")),
                FlowLayout.encloseCenter(dateN),
                createLineSeparator()

        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        
        Button mod = new Button("Modifier");
        Button changer = new Button("Changer photo de profile");
        changer.addActionListener(e -> changerImage());
        changer.setUIID("LinkChanger");
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                mod,
                FlowLayout.encloseCenter(changer)
        ));
        mod.requestFocus();
        mod.addActionListener(e -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier votre profile" ,"Oui", "Non");
            if(x)
             { 
                UtilisateurServices us = new UtilisateurServices();
                us.getInstance().modifierProfile( user.getId(),nom, prenom, email, cin, dateN,user.getImage(),res);
                user.setNom(nom.getText());
                user.setPrenom(prenom.getText());
                user.setEmail(email.getText());
                user.setCin(cin.getText());
                user.setDateNaissance((Date) dateN.getValue());
                user.setImage(user.getImage());
                SessionUser.setUser(user);
                 //Dialog.show("Success", "modification de votre profile avec succ??ee" ,"Ok",null);
             }
            });
        /*TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);*/

       /* CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));*/
    }

    private void changerImage(){
        Display.getInstance().openGallery(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (ev != null && ev.getSource() != null) {
                    String filePath = (String) ev.getSource();
                    int fileNameIndex = filePath.lastIndexOf("/") + 1;
                    String fileName = filePath.substring(fileNameIndex);

                    Image img = null;
                    try {
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        Dialog.show("Success", "Image selectionn??e" ,"Ok",null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
/*
                    MultiList photoList = findPhotoList();
                    Hashtable tableItem = new Hashtable();
                    tableItem.put("icon", img.scaled(Display.getInstance().getDisplayHeight() / 10, -1));
                    tableItem.put("emblem", fileName);
                    photoList.getModel().addItem(tableItem);*/
                    // Do something, add to List
                }
            }
        }, Display.GALLERY_IMAGE);
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator());
    }
}
