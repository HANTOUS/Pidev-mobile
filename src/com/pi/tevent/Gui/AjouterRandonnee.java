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
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.pi.tevent.Entities.Randonnee;
import com.pi.tevent.Services.RandonneeServices;
import java.util.Date;

/**
 *
 * @author maale
 */
public class AjouterRandonnee extends BaseForm {

    public AjouterRandonnee(Form previous) {
        setTitle("Ajouter randonnee");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "Nom de l'evenement");
        // PickerComponent datedebut = PickerComponent.createDate(new Date()).label("Date début");
        Picker datedebut = new Picker();
        datedebut.setType(Display.PICKER_TYPE_DATE);

        Picker datefin = new Picker();
        datefin.setType(Display.PICKER_TYPE_DATE);

        TextField tfheuredebut = new TextField("", "heure debut");
        TextField tfheurefin = new TextField("", "heure de fin");
        TextField Lieu = new TextField("", "lieu");
        TextField Nbrpart = new TextField("", "nombre de participants");
        TextField description = new TextField("", "description");
        TextField tarif = new TextField("", "Tarif");
        TextField lat = new TextField("", "lattitude");
        TextField lng = new TextField("", "longitude");
        TextField typerand = new TextField("", "Type de randonnee");
        Button Ajouter = new Button("Ajouter ");

        Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((typerand.getText().length() == 0)/*||(tfdate.getText().length()==0)*/) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else 
                
                {

                    Randonnee t = new Randonnee( typerand.getText(),tfnom.getText(), tfheuredebut.getText(), tfheurefin.getText(), Lieu.getText(), 
                            Integer.parseInt(Nbrpart.getText()), description.getText(), Float.parseFloat(tarif.getText()));
                    if( RandonneeServices.getInstance().addRandonnee(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            Message m = new Message("une randonnéé a été ajoutée");
                            Display.getInstance().sendMessage(new String[]{"maalej.zied@gmail.com"}, "mailing", m);}
                    else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    

                }
            }
        });
                addAll(tfnom, datedebut, datefin, tfheuredebut, tfheurefin, Lieu, Nbrpart, description, tarif, lat, lng, typerand, Ajouter);

                   // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}

/*
{
                if ((tfName.getText().length()==0)||(tfStatus.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Task t = new Task(Integer.parseInt(tfStatus.getText()), tfName.getText());
                        if( ServiceTask.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });


                    Randonnee t = new Randonnee(tfnom.getText(), tfheuredebut.getText(), tfheurefin.getText(), Lieu.getText(), Nbrpart.getText(), description.getText(), tarif.getText(), typerand.getText());

*/
