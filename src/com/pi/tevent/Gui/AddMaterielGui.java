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
import com.pi.tevent.Entities.Materiel;
import com.pi.tevent.Services.MaterielService;

/**
 * GUI builder created Form
 *
 * @author al199
 */
public class AddMaterielGui extends com.codename1.ui.Form {
    
    private Resources theme;
    private MaterielService ms= new MaterielService();

    public AddMaterielGui(String title, Resources theme) {
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
        
        // Label
        Label lbLabel = new Label("Label :");
        lbLabel.getStyle().setFgColor(0xFD5056);
        TextField label = new TextField("", "Label");
        
        item.add(lbLabel).add(label);
        
        // Label Prix
        Label lbPrix = new Label("Prix :");
        lbPrix.getStyle().setFgColor(0xFD5056);
        TextField prix = new TextField("1", "Prix", 4,  TextArea.NUMERIC);
        
        item.add(lbPrix).add(prix);
        
        
        // Label Stock
        Label lbStock = new Label("Stock :");
        lbStock.getStyle().setFgColor(0xFD5056);
        TextField stock = new TextField("1", "Stock", 4,  TextArea.NUMERIC);
        
        item.add(lbStock).add(stock);
        
        
        // Label QteReser
        Label lbqteReser = new Label("Qte reservé :");
        lbqteReser.getStyle().setFgColor(0xFD5056);
        //TextField nbPlace = new TextField(null, "Nbre de place");
        TextField qteReser =  new TextField("1", "Qte reservé", 4,  TextArea.NUMERIC);
        
        item.add(lbqteReser).add(qteReser);
        
        
        // Label Dispo
        Label lbDispo = new Label("Dispo:");
        lbDispo.getStyle().setFgColor(0xFD5056);
        CheckBox dispo = new CheckBox();
        //panne.isSelected(b.getPanne());
        dispo.getStyle().setFgColor(0xFD5056);
        
        item.add(lbDispo).add(dispo);
        
            
        itemsContainer.add(item);
        this.add(itemsContainer);
        Button btnSubmit = new Button("Ajouter Materiel");
        btnSubmit.addActionListener(event ->{
            Materiel m = new Materiel(label.getText(), Integer.parseInt(stock.getText()), Integer.parseInt(qteReser.getText()), Float.parseFloat(prix.getText()), dispo.isSelected());
            if(m.getLabel().isEmpty() || m.getPrix()< 1 || m.getStock()< 1 || m.getQte_reserve()< 1)
                Dialog.show("Ajout Invalid", "Vérifiez Vos Champs!", null, "ok");
            else{
                ms.addMateriel(m);
                Dialog.show("Success", "Update avec Success", null, "ok");
                new MaterielAGui("Materiel List",theme);
            }
        });
        this.add(btnSubmit);
    }
    
    public AddMaterielGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("AddMaterielGui");
        setName("AddMaterielGui");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
