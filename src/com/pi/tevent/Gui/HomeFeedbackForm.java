/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Salim
 */
public class HomeFeedbackForm extends BaseForm {
    
        public HomeFeedbackForm(Resources res){
        
        setTitle("Home Feedback");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisissez une option s'il vous plait"));
        Button btnAddFee = new Button("Ajouter un Feedback");
        Button btnListFee = new Button("Liste Feedback");
                        super.addSideMenu(res);

        btnAddFee.addActionListener(e-> new AddReclamationForm(res).show());
        btnListFee.addActionListener(e-> new ListFeedbackForm(res).show());
        addAll(btnAddFee, btnListFee);

    }

}
