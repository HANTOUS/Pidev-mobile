/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pi.tevent.Entities.Sponsor;
import com.pi.tevent.utils.Statics;

/**
 *
 * @author skand
 */
public class SponsorServices {
            
    
    
    public static SponsorServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private SponsorServices() {
         req = new ConnectionRequest();
    }

    public static SponsorServices getInstance() {
        if (instance == null) {
            instance = new SponsorServices();
        }
        return instance;
    }
    public boolean addsponsor(Sponsor S) {
   // String url = Statics.BASE_URL + "/addsponsorJSON/"+ S.getFestival_id()+ "/"+ S.getNom_sponsor()+"/"+ S.getDomaine_acivite()+"/"+ S.getImage()+ "/"+ S.getType_subvention()+"/"; //création de l'URL  
String url = Statics.BASE_URL + "/addsponsorJSON?festival_id="+ S.getFestival_id()+ "&nom_sponsor="+ S.getNom_sponsor() + "&domaine_activite=" + S.getDomaine_acivite()+"&image="+ S.getImage()+ "&type_subvention="+ S.getType_subvention(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
}
