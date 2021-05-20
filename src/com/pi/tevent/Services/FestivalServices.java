/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.pi.tevent.Entities.Festival;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author skand
 */


public class FestivalServices {
    public ArrayList<Festival> Festivals;
    public Festival p = new Festival();
    public static FestivalServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private Form current;

    private FestivalServices() {
         req = new ConnectionRequest();
    }

    public static FestivalServices getInstance() {
        if (instance == null) {
            instance = new FestivalServices();
        }
        return instance;
    }
     public void addfestival(Festival p) {
        
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");  
        String dateD = formatter.format(p.getDatedebut()); 
        String dateF = formatter.format(p.getDatefin()); 
        
        
        String url = Statics.BASE_URL+"/festival/addfestivalJSON?nomevent="+ p.getNomevent()+"&db="+dateD+
                "&df="+dateF+"&hb="+p.getHeuredebut()+"&hf="+p.getHeurefin()+"&lieu="+p.getLieu()+
                "&nbmax="+p.getNbmaxparticipant()+"&typefest="+p.getType_fest()+"&artist="+p.getArtist()+"&pic="+p.getPicture()+
                "&nbinv="+p.getNb_invit()+"&description="+p.getDescription()+"&tarif="+p.getTarif()+"&type="+p.getType(); //création de l'URL
       System.out.println("url: "+url);      
  req.setUrl(url);// Insertion de l'URL de notre demande de connexion
      
       System.out.println("req: "+req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                /*resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                System.out.println("result: "+resultOK);
                req.removeResponseListener(this); //Supprimer cet actionListener*/
                byte[] data = (byte[]) evt.getMetaData();
            String responseData = new String(data);
            System.out.println("data: "+responseData);
            req.removeResponseListener(this);
            
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       // return resultOK;
  }
     
      public ArrayList<Festival> getAllFestivals(){
        String url = Statics.BASE_URL+"/festival/getfestivalJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Festivals = parseFestival(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Festivals;
    }
      
      public ArrayList<Festival> parseFestival(String jsonText){
        try {
            Festivals=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String,Object> FestivalsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
         
            //System.out.println("OUI"+FestivalsListJson.get("root")); 
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)FestivalsListJson.get("root");
           
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Festival t = new Festival();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNomevent(obj.get("nomevent").toString());
                t.setArtist(obj.get("artist").toString());
               
               t.setLieu(obj.get("lieu").toString());
               SimpleDateFormat Festival = new SimpleDateFormat("yyyy-MM-dd");
            Date d;
            try {
                   d = Festival.parse(obj.get("datefin").toString().substring(0, 10));
                    t.setDatefin(d);
                   d = Festival.parse(obj.get("datedebut").toString().substring(0, 10));
                   t.setDatedebut(d);
                        System.out.println(t.getDatefin());
                     
               } catch (ParseException ex) {
            
               }
                t.setTarif(Float.parseFloat(obj.get("tarif").toString()) );
                t.setType_fest(obj.get("typeFest").toString());
                t.setNbmaxparticipant((int)Float.parseFloat(obj.get("nbmaxparticipant").toString()) );
                if (obj.get("picture").toString()!=null)
                 t.setPicture(obj.get("picture").toString());
              
                System.out.println(t.toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Festivals.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return Festivals;
    }

  
     
}
