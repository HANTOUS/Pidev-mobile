/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.tevent.Entities.Feedback;
import com.pi.tevent.Services.FeedbackServices;

/**
 *
 * @author Salim
 */
public class AddFeedbackForm extends BaseForm {
    public AddFeedbackForm(Resources res) {
        setTitle("Ajouter Feedback");
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        getContentPane().setScrollVisible(false);
        
        setTitle("Ajouter Feedback");
        
        setLayout(BoxLayout.y());
        TextField tfnote = new TextField("","note");
        TextField tfremarque = new TextField("","remarque");

        Button btnAjouter = new Button("Ajouter Feedback");
    btnAjouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
            if(tfnote.getText() =="" || tfremarque.getText() == "")
            Dialog.show("Alert", "verifiez vos données SVP",new Command ("OK"));
            else {
                try {
                    Feedback f = new Feedback(Integer.parseInt(tfnote.getText()), tfremarque.getText());
                if (new FeedbackServices().addFeedback(f))
                Dialog.show("Success", "Connection accepté",new Command ("OK"));
                else 
                    Dialog.show("Error", "Erreur de Serveur",new Command ("OK"));
                }catch (NumberFormatException e){
                    Dialog.show("Error", "il faut que note soit un nombre !",new Command ("OK"));
                }
            }
            }
        
    });
    addAll(tfnote, tfremarque, btnAjouter);
    }
}
