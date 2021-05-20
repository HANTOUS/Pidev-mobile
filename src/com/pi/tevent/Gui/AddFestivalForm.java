/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.p;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;

import java.util.Date;
import java.util.Map;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Festival;
import com.pi.tevent.Entities.Sponsor;
import com.pi.tevent.Services.FestivalServices;
import java.io.IOException;

/**
 *
 * @author skand
 */
public class AddFestivalForm extends BaseForm{
    public AddFestivalForm(Resources res) {
           super(new BorderLayout());
          Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
                
                 
              
                
                TextField tfnom = new TextField("","Nom de l'evenement") ;
               //PickerComponent datedebut = PickerComponent.createDate(new Date()).label("Date début");
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
                TextField artist = new TextField("","artist") ;
                
               ComboBox typefest = new ComboBox("concert","theatre","comedie");
                TextField nbinv = new TextField("","nb_invit") ;
                Button changer = new Button("Choisir une photo pour votre festival");
        changer.addActionListener(e -> changerImage());
                
                
                Container content = BoxLayout.encloseY(
                new Label("Festival", "LogoLabel"),
                new FloatingHint(tfnom),
                createLineSeparator(),
                datedebut,
                createLineSeparator(),
                datefin,
                createLineSeparator(),
                new FloatingHint(tfheuredebut),
                createLineSeparator(),
                new FloatingHint(tfheurefin),
                createLineSeparator(),
                new FloatingHint(Lieu),
                createLineSeparator(),
                new FloatingHint(Nbrpart),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                 new FloatingHint(tarif),
                createLineSeparator(),
                 new FloatingHint(artist),
                createLineSeparator(),
                typefest,
                createLineSeparator(),
                changer,
                createLineSeparator(),
                new FloatingHint(nbinv),
                createLineSeparator()
                        
                        
                 
              
        );
                Button Ajouter = new Button("Ajouter ");
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);


                add(BorderLayout.SOUTH, BoxLayout.encloseY(
                Ajouter
              //  FlowLayout.encloseCenter(alreadHaveAnAccount, login)
        ));
        Ajouter.requestFocus();
        Ajouter.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   Festival f = new Festival(); 
                   f.setNomevent(tfnom.getText());
                   f.setDatedebut(datedebut.getDate());
                   f.setDatefin(datefin.getDate());
                   f.setHeuredebut(tfheuredebut.getText());
                   f.setHeurefin(tfheurefin.getText());
                   f.setDescription(description.getText());
                   f.setLieu(Lieu.getText());
                   f.setNbmaxparticipant(Integer.parseInt(Nbrpart.getText()));
                   f.setArtist(artist.getText());
                   f.setType("festival");
                   f.setTarif(Integer.parseInt(tarif.getText()));
                   f.setType_fest(typefest.getSelectedItem().toString());
                   f.setNb_invit(Integer.parseInt(nbinv.getText()));
                   f.setPicture("unnamed.png");
                   
                   

                  // if (FestivalServices.getInstance().addfestival(f))
                FestivalServices.getInstance().addfestival(f);
                       Dialog.show("success","festival added",new Command("ok"));
                   /* else
                        Dialog.show("echec","festival nope",new Command("ok"));*/
                   
                   
               }
           });
               
            
        
        
        
              

              //addAll(tfnom,datedebut,datefin,tfheuredebut,tfheurefin,Lieu,Nbrpart,description,tarif,typefest,artist,picture,nbinv,Ajouter);

                
               // getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e ->previous.showBack());
  
    }

    /*AddFestivalForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
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
                        Dialog.show("Success", "Image selectionnée" ,"Ok",null);
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
}
