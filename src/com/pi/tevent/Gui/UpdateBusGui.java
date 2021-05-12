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
public class UpdateBusGui extends com.codename1.ui.Form {
    
    private Resources theme;
    private Bus b;
    private BusService bs= new BusService();

    public UpdateBusGui(String title, Resources theme,Bus b) {
        super(title, BoxLayout.y());
        this.theme = theme;
        this.b=b;
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
        
        // Label Id
        Label lbId = new Label("Fabriquant :");
        lbId.getStyle().setFgColor(0xFD5056);
        TextField id = new TextField(b.getId()+"", "Id");
        id.setEditable(false);
        
        item.add(lbId).add(id);
        
        // Label Fabriquant
        Label lbFab = new Label("Fabriquant :");
        lbFab.getStyle().setFgColor(0xFD5056);
        TextField fabriquant = new TextField(b.getFabriquant(), "Fabriquant");
        
        item.add(lbFab).add(fabriquant);
        
        
        // Label Modele
        Label lbModele = new Label("Modele :");
        lbModele.getStyle().setFgColor(0xFD5056);
        TextField modele = new TextField(b.getModele(), "Modele");
        
        item.add(lbModele).add(modele);
        
        
        // Label Nbre De Place
        Label lbNbPlace = new Label("Nbre de Place:");
        lbNbPlace.getStyle().setFgColor(0xFD5056);
        //TextField nbPlace = new TextField(null, "Nbre de place");
        TextField nbPlace =  new TextField(b.getNbPlace()+"", "Nbre de place", 4,  TextArea.NUMERIC);
        
        item.add(lbNbPlace).add(nbPlace);
        
        
        // Label Panne
        Label lbPanne = new Label("Panne:");
        lbPanne.getStyle().setFgColor(0xFD5056);
        CheckBox panne = new CheckBox();
        panne.setSelected(b.getPanne());
        //panne.isSelected(b.getPanne());
        panne.getStyle().setFgColor(0xFD5056);
        
        item.add(lbPanne).add(panne);
        
            
        itemsContainer.add(item);
        this.add(itemsContainer);
        Button btnSubmit = new Button("Update Bus");
        btnSubmit.addActionListener(event ->{
            Bus b = new Bus(Integer.parseInt(id.getText()),fabriquant.getText(), modele.getText(), Integer.parseInt(nbPlace.getText()), panne.isSelected());
            if(b.getFabriquant().isEmpty() || b.getModele().isEmpty() || b.getNbPlace() < 1)
                Dialog.show("Ajout Invalid", "Vérifiez Vos Champs!", null, "ok");
            else{
                bs.updateBus(b);
                Dialog.show("Success", "Ajout avec Success", null, "ok");
                //new BusAGui("Bus List",theme);
            }
        });
        this.add(btnSubmit);
    }
    
    public UpdateBusGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    }
//-- DON'T EDIT ABOVE THIS LINE!!!
}
