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
import com.pi.tevent.Entities.Feedback;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Salim
 */
public class FeedbackServices {
 public ArrayList<Feedback>listFeedback;
    public boolean ResultOK;
    public static FeedbackServices instance;
    private ConnectionRequest req;

    public FeedbackServices() {
        req = new ConnectionRequest();
    }
    
    public static FeedbackServices getInstance() {
        if (instance == null) {
            instance = new FeedbackServices();
        }

        return instance;
    }
    
    public boolean addFeedback(Feedback f){
        String url = Statics.BASE_URL+"/ajouterFeedbackMobile?note="+f.getNote()+"&remarque="+f.getRemarque();
        ConnectionRequest req = new ConnectionRequest(url);
        req.addResponseCodeListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK=req.getResponseCode()==200;
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }
    
    public ArrayList<Feedback> parseFeedback(String json) throws ParseException, NumberFormatException {
        listFeedback = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> FeedbackListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbackListJson.get("root");

            for (Map<String, Object> obj : list) {
                Feedback f = new Feedback();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
//                float user = Float.parseFloat(obj.get("utilisateur").toString());

                f.setId((int) id);
                
               // Map <String, Object> utilisateur2 = (Map <String, Object>) obj.get("utilisateur");

               // float user_id = Float.parseFloat(utilisateur2.get("user_id").toString());
                
               // System.out.println(id);
                      
               // r.setUser_id((int) user_id);
               float note = Float.parseFloat(obj.get("note").toString());
                f.setNote((int) note);
                f.setRemarque((obj.get("remarque").toString()));
            

                listFeedback.add(f);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listFeedback;

    }

    public ArrayList<Feedback> getFeedbackByUser(int id) {
        listFeedback = new ArrayList<>();

        String url = Statics.BASE_URL + "/Feedbacklistmobile?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FeedbackServices f = new FeedbackServices();

                try {
                    listFeedback = f.parseFeedback(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listFeedback;
    }
    
     public ArrayList<Feedback> getAllFeedback() {
        listFeedback = new ArrayList<>();

        String url = Statics.BASE_URL + "/FeedbackListMobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FeedbackServices fs = new FeedbackServices();

                try {
                    listFeedback = fs.parseFeedback(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listFeedback;
    }
    
     public void deleteFeedback (int id) {
        ConnectionRequest con = new ConnectionRequest();
      //  "http://localhost/pi-dev/web/app_dev.php/forum/supprimerComMobile?idCom=" + id;
        String Url = Statics.BASE_URL+"/deleteFeedback?id=" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
