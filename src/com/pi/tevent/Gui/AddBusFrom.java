/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Bus;
import com.pi.tevent.Services.BusService;

/**
 * GUI builder created Form
 *
 * @author al199
 */
public class AddBusFrom extends com.codename1.ui.Form {
    
    private Resources theme;
    private BusService bs= new BusService();

    public AddBusFrom(String title, Resources theme) {
        super(title, BoxLayout.y());
        this.theme = theme;
        this.addGUIs();
    }
    
    private void addGUIs(){
        Container titleContainer = new Container(BoxLayout.xCenter());
        this.add(titleContainer);
        Image imgBus = theme.getImage("booking.jpg");
        this.add(imgBus);
        
        Container itemsContainer = new Container(BoxLayout.y());
        itemsContainer.setUIID("BusAddCntainer");
        //itemsContainer.getStyle().setBgColor(0x090031);
        itemsContainer.getStyle().setBgTransparency(255);
        
        Container item =  new Container(new TableLayout(4, 2));
        //item.getStyle().setBgColor(0x090031);
        item.getStyle().setBgTransparency(255);
        item.getStyle().setMarginLeft(0);
        item.getStyle().setMarginRight(0);
        
        // Label Fabriquant
        Label lbFab = new Label("Fabriquant :");
        lbFab.getStyle().setFgColor(0xFD5056);
        TextField fabriquant = new TextField(null, "Fabriquant");
        
        item.add(lbFab).add(fabriquant);
        
        
        // Label Modele
        Label lbModele = new Label("Modele :");
        lbModele.getStyle().setFgColor(0xFD5056);
        TextField modele = new TextField(null, "Modele");
        
        item.add(lbModele).add(modele);
        
        
        // Label Nbre De Place
        Label lbNbPlace = new Label("Nbre de Place:");
        lbNbPlace.getStyle().setFgColor(0xFD5056);
        //TextField nbPlace = new TextField(null, "Nbre de place");
        TextField nbPlace =  new TextField("1", "Nbre de place", 4,  TextArea.NUMERIC);
        
        item.add(lbNbPlace).add(nbPlace);
        
        
        // Label Panne
        Label lbPanne = new Label("Panne:");
        lbPanne.getStyle().setFgColor(0xFD5056);
        CheckBox panne = new CheckBox();
        panne.getStyle().setFgColor(0xFD5056);
        
        item.add(lbPanne).add(panne);
        
            
        itemsContainer.add(item);
        this.add(itemsContainer);
        Button btnSubmit = new Button("Ajouter Bus");
        btnSubmit.addActionListener(event ->{
            Bus b = new Bus(fabriquant.getText(), modele.getText(), Integer.parseInt(nbPlace.getText()), Boolean.parseBoolean(panne.getText()));
            if(b.getFabriquant().isEmpty() || b.getModele().isEmpty() || b.getNbPlace() < 1)
                Dialog.show("Ajout Invalid", "Vérifiez Vos Champs!", null, "ok");
            else{
                bs.addBus(b);
                Dialog.show("Success", "Ajout avec Success", null, "ok");
            }
        });
        this.add(btnSubmit);
    }
    
    public AddBusFrom(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }


//////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("AddBusFrom");
        setName("AddBusFrom");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
