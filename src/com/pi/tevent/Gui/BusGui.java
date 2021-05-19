/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import static com.codename1.ui.CN.*;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
//import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import com.pi.tevent.Services.BusService;
import com.pi.tevent.Entities.Bus;

/**
 * GUI builder created Container
 *
 * @author al199
 */
public class BusGui extends BaseForm{
    
    private Resources theme;
    private BusService bs= new BusService();
    ArrayList<Bus> listBus= new ArrayList<>();

    public BusGui(String title, Resources theme){
        super( title,BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Nos Bus");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
        this.add(createDLineSeparator());
        this.add(createDLineSeparator());
        
        super.addSideMenu(theme);
        
        //super(title, BoxLayout.y());
        this.theme = theme;
        listBus = bs.listBus();
        this.addGUIs();
    }
    
    private void addGUIs(){
        Container titleContainer = new Container(BoxLayout.xCenter());
        this.add(titleContainer);
        
        Container itemsContainer = new Container(BoxLayout.y());
        
        for (Bus b : listBus){
            
            Container itemContainer = new Container(BoxLayout.y());
            itemContainer.setUIID("BusListCntainer");
            itemContainer.getStyle().setBgColor(0x090031);
            itemContainer.getStyle().setBgTransparency(255);
            Container item =  new Container(new TableLayout(1, 2));
            item.getStyle().setBgColor(0x090031);
            item.getStyle().setBgTransparency(255);
            item.getStyle().setMarginBottom(10);
            item.getStyle().setMarginLeft(10);
            item.getStyle().setMarginRight(10);
            
            // Label Id
            Label lbId = new Label("N° Bus:");
            lbId.getStyle().setFgColor(0xFD5056);
            Label lbId1 = new Label(""+b.getId());
            lbId1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbId).add(lbId1);
            
            // Label Fabriquant
            Label lbFab = new Label("Fabriquant:");
            lbFab.getStyle().setFgColor(0xFD5056);
            Label lbFab1 = new Label(""+b.getFabriquant());
            lbFab1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbFab).add(lbFab1);
            
            // Label Modele
            Label lbMod = new Label("Modele:");
            lbMod.getStyle().setFgColor(0xFD5056);
            Label lbMod1 = new Label(""+b.getModele());
            lbMod1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbMod).add(lbMod1);
            
            // Label NbrePlace
            Label lbPlace = new Label("Nbre de place:");
            lbPlace.getStyle().setFgColor(0xFD5056);
            Label lbPlace1 = new Label(""+b.getNbPlace());
            lbPlace1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbPlace).add(lbPlace1);
            
            // Label Panne
            Label lbPanne = new Label("Panne:");
            lbPanne.getStyle().setFgColor(0xFD5056);
            Label lbPanne1 = new Label(""+b.getPanne());
            lbPanne1.getStyle().setFgColor(0xFFFFFF);
            
            item.add(lbPanne).add(lbPanne1);
            
            itemsContainer.add(item);
        }
        this.add(itemsContainer);
    }
    
    public BusGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setName("bus");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
