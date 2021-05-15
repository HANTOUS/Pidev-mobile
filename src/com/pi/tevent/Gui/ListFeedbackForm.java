/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Feedback;
import com.pi.tevent.Services.FeedbackServices;

/**
 *
 * @author Salim
 */
public class ListFeedbackForm extends BaseForm {
    
        public ListFeedbackForm(Resources res) {
        setTitle("Liste Feedback");
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        
        SpanLabel sp = new SpanLabel();
        sp.setText(FeedbackServices.getInstance().getAllFeedback().toString());
        add(sp);
    }
    
    public Container item(Feedback f){
        Container holder = new Container(BoxLayout.x());
        
        Label lid = new Label(""+f.getId()) ;
        Label lnote= new Label(""+f.getNote()) ;
        Label lremarque= new Label(f.getRemarque()) ;
      //  Label lVille_arrivee= new Label(db.getVille_arrivee()) ;
        //Label lJour_location = new Label(db.getJour_location().toString());
        
       holder.add(lid).add(lnote).add(lremarque);
       /*lid.addPointerPressedListener(evt ->{
                 new ListReclamationForm(res).show();
       });*/
       holder.setLeadComponent(lid); // pour tout les composant de holder aient le meme comportement que lid
       
        return holder;
    }
}
