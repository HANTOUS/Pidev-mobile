/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.pi.tevent.Entities.Camping;
import com.pi.tevent.Entities.Randonnee;
import com.pi.tevent.Services.CampingServices;

/**
 *
 * @author maale
 */
public class AjouterCamping extends  BaseForm {

    public AjouterCamping(Form previous) {
          setTitle("Ajouter Camping");
                
                 
                setLayout(BoxLayout.y());
                
                TextField tfnom = new TextField("","Nom de l'evenement") ;
               // PickerComponent datedebut = PickerComponent.createDate(new Date()).label("Date début");
               Picker datedebut = new Picker();
               datedebut.setType(Display.PICKER_TYPE_DATE);
               
                Picker datefin = new Picker();
               datefin.setType(Display.PICKER_TYPE_DATE);
                
                TextField tfheuredebut = new TextField("","heure debut") ;
                TextField tfheurefin = new TextField("","heure de fin") ;
                TextField Lieu = new TextField("","lieu") ;
                TextField Nbrpart = new TextField("","nombre de participants") ;
                TextField description = new TextField("","description") ;
                TextField tarif = new TextField("","Tarif") ;
                TextField lat = new TextField("","lattitude") ;
                TextField lng = new TextField("","longitude") ;
                TextField localisation = new TextField("","localisation") ;


               Button Ajouter = new Button("Ajouter ");

               Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((localisation.getText().length() == 0)/*||(tfdate.getText().length()==0)*/) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else 
                
                {

                    Camping t = new Camping( localisation.getText(),tfnom.getText(), tfheuredebut.getText(), tfheurefin.getText(), Lieu.getText(), 
                            Integer.parseInt(Nbrpart.getText()), description.getText(), Float.parseFloat(tarif.getText()));
                    if( CampingServices.getInstance().addCamping(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            Message m = new Message("un camping a été ajoutée");
                            Display.getInstance().sendMessage(new String[]{"maalej.zied@gmail.com"}, "mailing", m);}
                    else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    

                }
            }
        });

              addAll(tfnom,datedebut,datefin,tfheuredebut,tfheurefin,Lieu,Nbrpart,description,tarif,lat,lng,localisation,Ajouter);

                
              //  getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e ->previous.showBack());
  
    }
    
}
