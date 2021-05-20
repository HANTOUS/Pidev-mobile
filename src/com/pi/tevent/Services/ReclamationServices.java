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
import com.codename1.ui.events.ActionListener;
import com.pi.tevent.Entities.Reclamation;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Salim
 */
public class ReclamationServices {
    public ArrayList<Reclamation>listReclamation;
    public boolean ResultOK;
    public static ReclamationServices instance;
    private ConnectionRequest req;
    

    public ReclamationServices() {
        req = new ConnectionRequest();
        
    }

    public static ReclamationServices getInstance() {
        if (instance == null) {
            instance = new ReclamationServices();
        }

        return instance;
    }
    public void addRec(Reclamation r){
        String url = Statics.BASE_URL+"/ajouterreclamationmobile?user_id="+r.getUser_id()+"&sujet="+r.getSujet()+"&contenu="+r.getContenu()+"&etat="+r.getEtat();
        req.setUrl(url);
        System.out.println(req.getResponseCode());
        req.addResponseCodeListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
            String responseData = new String(data);
            System.out.println("data: "+responseData);
            req.removeResponseListener(this);
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       // return ResultOK;
    }
    
    
    public ArrayList<Reclamation> parseReclamation(String json) throws ParseException, NumberFormatException {
        listReclamation = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
//                float user = Float.parseFloat(obj.get("utilisateur").toString());

                r.setId((int) id);
                
               // Map <String, Object> utilisateur2 = (Map <String, Object>) obj.get("utilisateur");

               // float user_id = Float.parseFloat(utilisateur2.get("user_id").toString());
                
               // System.out.println(userId);
                      
               // r.setUser_id((int) user_id);
                r.setSujet((obj.get("sujet").toString()));
                r.setContenu((obj.get("contenu").toString()));
                r.setEtat((obj.get("etat").toString()));

                listReclamation.add(r);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listReclamation;

    }

    public ArrayList<Reclamation> getReclamationByUser(int userId) {
        listReclamation = new ArrayList<>();

        String url = Statics.BASE_URL + "/Reclamationlistmobile?utilisateur="+userId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReclamationServices rs = new ReclamationServices();

                try {
                    listReclamation = rs.parseReclamation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listReclamation;
    }
    
     public ArrayList<Reclamation> getAllReclamation() {
        listReclamation = new ArrayList<>();

        String url = Statics.BASE_URL + "/reclamationListMobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReclamationServices rs = new ReclamationServices();

                try {
                    listReclamation = rs.parseReclamation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listReclamation;
    }
    
     public void deleteReclamation (Reclamation r) {
        ConnectionRequest con = new ConnectionRequest();
      //  "http://localhost/pi-dev/web/app_dev.php/forum/supprimerComMobile?idCom=" + id;
        String Url = Statics.BASE_URL+"/deleteReclamationMobile?id=" + r.getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
