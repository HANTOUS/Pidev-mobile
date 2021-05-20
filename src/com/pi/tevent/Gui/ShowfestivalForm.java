/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.pi.tevent.Gui;

import com.pi.tevent.Entities.Festival;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Services.FestivalServices;


/**
 * GUI builder created Form
 *
 * @author skander
 */
public class ShowfestivalForm extends BaseForm {

    public ShowfestivalForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public ShowfestivalForm(com.codename1.ui.util.Resources resourceObjectInstance) {
         super(new BorderLayout());
         if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("background44");
     add(BorderLayout.NORTH, new Label(resourceObjectInstance.getImage("Logo-Off.png"), "LogoLabel"));
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer1.add(BorderLayout.CENTER, sl);
        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer2.add(BorderLayout.CENTER, sl);
        
       //installSidemenu(resourceObjectInstance);
       // getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
      
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);
        
        gui_Text_Area_2.setRows(2);
        gui_Text_Area_2.setColumns(100);
        gui_Text_Area_2.setGrowByContent(false);
        gui_Text_Area_2.setEditable(false);
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
    ShareButton share = new ShareButton();

Container mainContent;
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
     private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Festivals");
        setName("TrendingForm");
        //System.out.println(Services.ServiceFormations.getInstance().getallformations().toString());
        final String t = new String ("Hello,between "+""+" and "+ ""+"   there will be an event of.\n"+""+
                " you can find us in "+""+" So your are welcome to join us. \n"+ 
                " you can visit our site at http://www.highrises.com\n"
                );
        
       share.setText(t);
        for(Festival f:FestivalServices.getInstance().getAllFestivals())
        {
            int i=0;
        com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
     com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
     com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
     com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
     com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
     com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
     com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();     
        
     
      
      
       
        addComponent(gui_Container_1);
        Container c=new Container();
        c=gui_Container_1;
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage(f.getPicture()));
        gui_Multi_Button_1.setPropertyValue("line1", f.getLieu());
        gui_Multi_Button_1.setPropertyValue("line2", f.getType_fest());
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage(""));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer1.add(BorderLayout.CENTER, sl);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String format = formatter.format(f.getDatefin());
        gui_LA.setPropertyValue("line1", format);
        gui_LA.setPropertyValue("line2", f.getTarif()+"dt");
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
           
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
       
       
        gui_Text_Area_1.setText(f.getNomevent());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
        gui_Button_1.setName("Button_1");
        
        
        gui_Button_1.addActionListener(e->{
            System.out.println(f.getId());
        });
        addAll(createEntry(resourceObjectInstance, true,
                        f.getDatedebut().toString(),
                        f.getDatefin().toString(),
                        f.getNomevent(),
                        f.getLieu(),
                        "test",
                        "contact-a.png",
                        "contact-b.png",
                        "contact-c.png"));
      
        
        
       
       
        
        }
        
        
        
       
    }
     
     
     private Container createEntry(Resources res, boolean selected, String startTime, String endTime, String location, String title, String attendance, String... images) {
        Component time = new Label("", "CalendarHourUnselected");
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        

           
                    //gui_Calendar_1.highlightDate(new Date(), title);


        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
                new Label("", "OrangeLine"),
                new Label("", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
        for(String att : images) {
            cnt.add(res.getImage(att));
        }
        Container mainContent = BoxLayout.encloseY(
               
               
                cnt
        );
        
        Label redLabel = new Label("", "RedLabelRight");
        FontImage.setMaterialIcon(redLabel, FontImage.MATERIAL_LOCATION_ON);
        
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
                ShareButton share = new ShareButton();
                Button stat = new Button("Stat");
                share.setText("Share Event");
                
                
                share.addActionListener(evt->{  
                  
            // signIn();
                       
                 final TextArea t = new TextArea("Hello,between "+startTime+" and "+ endTime+"   there will be an event of.\n"+title+
                " you can find us in "+location +" So your are welcome to join us. \n"+ 
                " you can visit our site at http://www.Southside.com\n"
                );
        t.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                share.setTextToShare(t.getText());
            }
        });
                     share.setTextToShare(t.getText());
                });
        return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
                add(BorderLayout.SOUTH,share);
                
    }
     

//-- DON'T EDIT ABOVE THIS LINE!!!

    protected boolean isCurrentTrending() {
        return true;
    }
}
