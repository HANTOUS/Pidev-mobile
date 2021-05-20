/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Sponsor;
import com.pi.tevent.Services.SponsorServices;

/**
 *
 * @author skand
 */
public class AddSponsorForm extends BaseForm {
    
          public AddSponsorForm( Resources res) {
      
              
              super(new BorderLayout());
                Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
                 
              
                
           
                
                TextField nomsponsor = new TextField("","nom sponsor") ;
                TextField domaineactivite = new TextField("","domaine d activite") ;
                TextField image = new TextField("","image") ;
                TextField typesubvention = new TextField("","type subvention") ;
               

                
                
                
                
                
                Container content = BoxLayout.encloseY(
                new Label("Sponsor", "LogoLabel"),
                new FloatingHint(nomsponsor),
                createLineSeparator(),
                new FloatingHint(domaineactivite),
                createLineSeparator(),
                new FloatingHint(image),
                createLineSeparator(),
                new FloatingHint(typesubvention),
                createLineSeparator()
              
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        
        
        
        
        
        
        
        
               Button Ajouter = new Button("Ajouter ");
               
               
                add(BorderLayout.SOUTH, BoxLayout.encloseY(
                Ajouter
              //  FlowLayout.encloseCenter(alreadHaveAnAccount, login)
        ));
        Ajouter.requestFocus();
        //Ajouter.addActionListener(e -> new ActiveForm(res).show());
               
               Ajouter.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  Sponsor s = new Sponsor(77,nomsponsor.getText(),domaineactivite.getText(),image.getText(),typesubvention.getText());
               //   if( SponsorServices.getInstance().addsponsor(s))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
              }
          });

              //addAll(nomsponsor,domaineactivite,image,typesubvention,Ajouter);

                
             //   getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e ->previous.showBack());
  
    }

  /*  AddSponsorForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
