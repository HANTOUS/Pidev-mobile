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
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Materiel;
import com.pi.tevent.Services.MaterielService;
import java.util.ArrayList;


/**
 * GUI builder created Form
 *
 * @author al199
 */
public class MaterielAGui extends com.codename1.ui.Form {
    
    private Resources theme;
    private MaterielService ms= new MaterielService();
    ArrayList<Materiel> listMateriel= new ArrayList<>();

    public MaterielAGui(String title, Resources theme) {
        super(title, BoxLayout.y());
        this.theme = theme;
        listMateriel = ms.listMateriel();
        this.addGUIs();
    }
    
    private void addGUIs(){
        Container titleContainer = new Container(BoxLayout.xCenter());
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
            //item.getStyle().setBgColor(0x090031);
            item.getStyle().setBgTransparency(255);
            item.getStyle().setMarginLeft(0);
            item.getStyle().setMarginRight(0);
                    
            TextField id = new TextField(m.getId()+"", "Id");

            // Label
            Label lbLabel = new Label("Label :");
            lbLabel.getStyle().setFgColor(0xFD5056);
            Label label = new Label(m.getLabel()+"");

            item.add(lbLabel).add(label);

            // Label Prix
            Label lbPrix = new Label("Prix :");
            lbPrix.getStyle().setFgColor(0xFD5056);
            Label prix = new Label(m.getPrix()+"");

            item.add(lbPrix).add(prix);


            // Label Stock
            Label lbStock = new Label("Stock :");
            lbStock.getStyle().setFgColor(0xFD5056);
            Label stock = new Label(m.getStock()+"");

            item.add(lbStock).add(stock);


            // Label QteReser
            Label lbqteReser = new Label("Qte reservé :");
            lbqteReser.getStyle().setFgColor(0xFD5056);
            //TextField nbPlace = new TextField(null, "Nbre de place");
            Label qteReser =  new Label(m.getQte_reserve()+"");

            item.add(lbqteReser).add(qteReser);


            // Label Dispo
            Label lbDispo = new Label("Dispo:");
            lbDispo.getStyle().setFgColor(0xFD5056);
            CheckBox dispo = new CheckBox();
            dispo.setSelected(m.getDispo());
            //panne.isSelected(b.getPanne());
            dispo.getStyle().setFgColor(0xFD5056);

            item.add(lbDispo).add(dispo);
            
            Container btns =  new Container(new TableLayout(1, 2));
            Button del =new Button("delete");
            Button up =new Button("update");
            
            btns.add(up).add(del);
            del.addActionListener(event -> {
                ms.deleteMateriel(id.getText());
                Dialog.show("Suppression avec success !", "le Materiel "+id.getText()+" a été supprimer avec success",null, "ok");
                System.out.println(id.getText());
            });
            item.add(btns);
            
            up.addActionListener(event ->{
                Materiel me = new Materiel(Integer.parseInt(id.getText()), label.getText(), Integer.parseInt(stock.getText()), Integer.parseInt(qteReser.getText()), Float.parseFloat(prix.getText()), dispo.isSelected());
                new UpdateMaterielGui("Update Materiel", theme,me).show();
            });
            
            itemsContainer.add(item);
        }
        this.add(itemsContainer);
    }
    
    public MaterielAGui(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MaterielAGui");
        setName("MaterielAGui");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
