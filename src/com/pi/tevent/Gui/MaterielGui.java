/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.pi.tevent.Services.MaterielService;
import com.pi.tevent.Entities.Materiel;
import com.codename1.ui.Toolbar;

/**
 * GUI builder created Form
 *
 * @author al199
 */
    public class MaterielGui extends BaseForm {
    
    private Resources theme;
    private MaterielService ms= new MaterielService();
    ArrayList<Materiel> listMateriel= new ArrayList<>();

    public MaterielGui(String title, Resources theme) {
        super(title, BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Nos Materiels");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        this.add(createDLineSeparator());
        this.add(createDLineSeparator());
        
        super.addSideMenu(theme);
        this.theme = theme;
        listMateriel = ms.listMateriel();
        this.addGUIs();
    }
    
    private void addGUIs(){
        Container titleContainer = new Container(BoxLayout.x());
        this.add(titleContainer);
        
        Container itemsContainer = new Container(BoxLayout.y());
        
        for (Materiel m : listMateriel){
            
            Container itemContainer = new Container(BoxLayout.y());
            itemContainer.setUIID("MaterielListCntainer");
            itemContainer.getStyle().setBgColor(0x090031);
            itemContainer.getStyle().setBgTransparency(255);
            Container item =  new Container(new TableLayout(1, 2));
            item.getStyle().setBgColor(0x090031);
            item.getStyle().setBgTransparency(255);
            item.getStyle().setMarginBottom(10);
            item.getStyle().setMarginLeft(10);
            item.getStyle().setMarginRight(10);
            
            // Label Label
            Label lbId = new Label("Label:");
            lbId.getStyle().setFgColor(0xFD5056);
            Label lbId1 = new Label(""+m.getLabel());
            lbId1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbId).add(lbId1);
            
            // Label Stock
            Label lbFab = new Label("Stock:");
            lbFab.getStyle().setFgColor(0xFD5056);
            Label lbFab1 = new Label(""+m.getStock());
            lbFab1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbFab).add(lbFab1);
            
            // Label qte_reserve
            Label lbMod = new Label("Qte reserve:");
            lbMod.getStyle().setFgColor(0xFD5056);
            Label lbMod1 = new Label(""+m.getQte_reserve());
            lbMod1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbMod).add(lbMod1);
            
            // Label Prix
            Label lbPlace = new Label("Prix:");
            lbPlace.getStyle().setFgColor(0xFD5056);
            Label lbPlace1 = new Label(""+m.getPrix());
            lbPlace1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbPlace).add(lbPlace1);
            
            // Label Disponible
            Label lbPanne = new Label("Disponible:");
            lbPanne.getStyle().setFgColor(0xFD5056);
            Label lbPanne1 = new Label(""+m.getDispo());
            lbPanne1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbPanne).add(lbPanne1);
            
            itemsContainer.add(item);
        }
        this.add(itemsContainer);
    }
    
    public MaterielGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MaterielGui");
        setName("MaterielGui");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
