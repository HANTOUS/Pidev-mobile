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
import com.codename1.ui.events.ActionListener;
import com.pi.tevent.Entities.DemandeChauffeur;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class DemandeChauffeurServices {

    ArrayList<DemandeChauffeur> listDemandeChauffeur;
    public boolean resultOK;
    public static DemandeChauffeurServices instance;
    private ConnectionRequest req;

    public DemandeChauffeurServices() {
        req = new ConnectionRequest();
    }

    public static DemandeChauffeurServices getInstance() {
        if (instance == null) {
            instance = new DemandeChauffeurServices();
        }

        return instance;
    }
    public boolean refuserDemandeChauffeur(int id) {

        String url = Statics.BASE_URL + "/demandechauffeurmobile/refuser?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code http 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean accepterDemandeChauffeur(DemandeChauffeur dc) {

        String url = Statics.BASE_URL + "/demandechauffeurmobile/accepter?id=" + dc.getId()+"&utilisateur="+dc.getUtilisateur()+"&numPermis="+dc.getNumPermis();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code http 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean addDemandeChauffeur(DemandeChauffeur db) {

        String url = Statics.BASE_URL + "/demandechauffeurmobile/add?utilisateur=" + db.getUtilisateur() + "&numPermis=" + db.getNumPermis() + "&datePermis=" + db.getDatePermis() + "&dateExpiration=" + db.getDateExpiration() + "&etat=" + db.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code http 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateDemandeChauffeur(DemandeChauffeur db) {

        String url = Statics.BASE_URL + "/demandechauffeurmobile/update?id=" + db.getId() + "&utilisateur=" + db.getUtilisateur() + "&numPermis=" + db.getNumPermis() + "&datePermis=" + db.getDatePermis() + "&dateExpiration=" + db.getDateExpiration() + "&etat=" + db.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code http 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteDemandeChauffeur(DemandeChauffeur db) {

        String url = Statics.BASE_URL + "/demandechauffeurmobile/delete?id=" + db.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code http 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<DemandeChauffeur> parseDemandeChauffeur(String json) throws ParseException {
        listDemandeChauffeur = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> DemandeChauffeurListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(DemandeChauffeurListJson);

            List<Map<String, Object>> list = (List<Map<String, Object>>) DemandeChauffeurListJson.get("root");

            for (Map<String, Object> obj : list) {
                DemandeChauffeur db = new DemandeChauffeur();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                float nump = Float.parseFloat(obj.get("numPermis").toString());
                db.setId((int) id);
                db.setNumPermis((int) nump);
                db.setEtat((obj.get("etat").toString()));

                //Date Converter
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date datep = formatter.parse(obj.get("datePermis").toString());
                Date dateex = formatter.parse(obj.get("dateExpiration").toString());

                db.setDatePermis(datep);

                db.setDateExpiration(dateex);

                listDemandeChauffeur.add(db);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listDemandeChauffeur;

    }
    
     public ArrayList<DemandeChauffeur> getAllDemandeChauffeur() {
        listDemandeChauffeur = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandechauffeurlistmobileA/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeChauffeurServices dbs = new DemandeChauffeurServices();

                try {
                    listDemandeChauffeur = dbs.parseDemandeChauffeur(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeChauffeur;
    }


    public ArrayList<DemandeChauffeur> getDemandeChauffeurByUser(int userId) {
        listDemandeChauffeur = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandechauffeurlistmobile?utilisateur="+userId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeChauffeurServices dbs = new DemandeChauffeurServices();

                try {
                    listDemandeChauffeur = dbs.parseDemandeChauffeur(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeChauffeur;
    }

}
