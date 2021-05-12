/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author DELL
 */
public class LISTESSAYAGE extends BaseForm{
   public LISTESSAYAGE(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        Button b= new Button("GEEEEEEET");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
     ComboBox<Map<String, Object>> combo = new ComboBox<> (
          createListEntry("A Game of Thrones", 1996),
          createListEntry("A Game of Thrones", 1997)
          
     );
  
  combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
  this.add(combo).add(b);
b.addActionListener(e -> {
   System.out.println(combo.getSelectedIndex()+1);
       System.out.println(combo.getSelectedItem().get("Line1")); 
});
  
}

private Map<String, Object> createListEntry(String name, Object date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
   }
    

