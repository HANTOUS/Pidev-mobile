/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Feedback;
import com.pi.tevent.Services.FeedbackServices;
import java.util.ArrayList;

/**
 *
 * @author Salim
 */
public class ListFeedbackForm extends BaseForm {
   
    Form current;
    public Resources theme;
    public ArrayList<Feedback> list; 
    
        public ListFeedbackForm(Resources res) {
        super( "Liste Feedback",BoxLayout.y());
        setTitle("Liste Feedback");
        this.theme=theme;    
            
        
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            this.add(createDLineSeparator());
            this.add(createDLineSeparator());
            this.add(createLineSeparator(000000));
            
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        
        setTitle("Liste Feedback");
        getTitleArea().setUIID("Container");
        setTitle("List Feedback");
        getContentPane().setScrollVisible(false);
        setUIID("Profile");
       
        list = FeedbackServices.getInstance().getAllFeedback();
        current = this;
        
        for ( Feedback f : list ){
            this.add(item(f));
        }
    }
    
    public Container item(Feedback f){
        Button supprimer = new Button(FontImage.MATERIAL_DELETE);
        
        Container holderParent = new Container(BoxLayout.y());
        Container holder = new Container(BoxLayout.x());
        Container holder1 = new Container(BoxLayout.y());
        Container holderID = new Container(BoxLayout.x());
        Container holderNO = new Container(BoxLayout.x());
        Container holderRE = new Container(BoxLayout.x());
        
        
        Label TID = new Label("ID :") ;
        Label TNOTE= new Label("Note :") ;
        Label TREMARQUE= new Label("Remarque :") ;
      
        Label ID = new Label (""+f.getId());
        Label NOTE = new Label (""+f.getNote());
        Label REMARQUE = new Label (f.getRemarque());
        
        holderID.add(TID).add(ID);
        holderNO.add(TNOTE).add(NOTE);
        holderRE.add(TREMARQUE).add(REMARQUE);
        
         supprimer.addActionListener(evt ->{
             
            new FeedbackServices().deleteFeedback(f);
            new ListFeedbackForm(theme).show();
             refreshTheme();
         });
       
         holder1.add(holderID).add(holderNO).add(holderRE);
       
       holder.add(holder1).add(supprimer);
       holderParent.add(holder).add(createLineSeparator(000000));
       
        return holderParent;
    }
    
    
}
