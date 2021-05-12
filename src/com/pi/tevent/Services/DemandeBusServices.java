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
import com.pi.tevent.Entities.DemandeBus;
import com.pi.tevent.Entities.Utilisateur;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class DemandeBusServices {

    ArrayList<DemandeBus> listDemandeBus;
    public boolean resultOK;
    public static DemandeBusServices instance;
    private ConnectionRequest req;

    public DemandeBusServices() {
        req = new ConnectionRequest();
    }

    public static DemandeBusServices getInstance() {
        if (instance == null) {
            instance = new DemandeBusServices();
        }

        return instance;
    }

    public boolean refuserDemandeBus(int id) {

        String url = Statics.BASE_URL + "/demandebusmobile/refuser?id=" + id;
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

    public boolean accepterDemandeBus(int id) {

        String url = Statics.BASE_URL + "/demandebusmobile/accepter?id=" + id;
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

    public boolean addDemandeBus(DemandeBus db) {

        String url = Statics.BASE_URL + "/demandebusmobile/add?utilisateur=" + db.getUtilisateur() + "&nbParticipant=" + db.getNbParticipant() + "&villeDepart=" + db.getVilleDepart() + "&villeArrivee=" + db.getVilleArrivee() + "&heureDepart=" + db.getHeureDepart() + "&heureArrivee=" + db.getHeureArrivee() + "&etat=" + db.getEtat() + "&jourLocation=" + db.getJourLocation();
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

    public boolean updateDemandeBus(DemandeBus db) {

        String url = Statics.BASE_URL + "/demandebusmobile/update?id=" + db.getId() + "&utilisateur=" + db.getUtilisateur() + "&nbParticipant=" + db.getNbParticipant() + "&villeDepart=" + db.getVilleDepart() + "&villeArrivee=" + db.getVilleArrivee() + "&heureDepart=" + db.getHeureDepart() + "&heureArrivee=" + db.getHeureArrivee() + "&etat=" + db.getEtat() + "&jourLocation=" + new Date();
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

    public boolean deleteDemandeBus(DemandeBus db) {

        String url = Statics.BASE_URL + "/demandebusmobile/delete?id=" + db.getId();
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

    public ArrayList<DemandeBus> parseDemandeBus(String json) throws ParseException, NumberFormatException {
        listDemandeBus = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> DemandeBusListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(DemandeBusListJson);

            List<Map<String, Object>> list = (List<Map<String, Object>>) DemandeBusListJson.get("root");

            for (Map<String, Object> obj : list) {
                DemandeBus db = new DemandeBus();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                float nb = Float.parseFloat(obj.get("nbParticipant").toString());
//                float user = Float.parseFloat(obj.get("utilisateur").toString());

                db.setId((int) id);
                db.setNbParticipant((int) nb);
                
                Map <String, Object> utilisateur2 = (Map <String, Object>) obj.get("utilisateur");
                System.out.println(utilisateur2);

                float userId = Float.parseFloat(utilisateur2.get("id").toString());
                
                System.out.println(userId);
                      
                db.setUtilisateur((int) userId);
                db.setVilleDepart((obj.get("villeDepart").toString()));
                db.setVilleArrivee((obj.get("villeArrivee").toString()));
                db.setHeureDepart((obj.get("heureDepart").toString()));
                db.setHeureArrivee((obj.get("heureArrivee").toString()));
                db.setEtat((obj.get("etat").toString()));

                //Date Converter
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(obj.get("jourLocation").toString());

                db.setJourLocation(date);

                //System.out.println(db.getUtilisateur()+"Tarek");
                listDemandeBus.add(db);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listDemandeBus;

    }

    public ArrayList<DemandeBus> getDemandeBusByUser(int userId) {
        listDemandeBus = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandebuslistmobile?utilisateur="+userId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeBusServices dbs = new DemandeBusServices();

                try {
                    listDemandeBus = dbs.parseDemandeBus(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeBus;
    }

    public ArrayList<DemandeBus> getAllDemandeBus() {
        listDemandeBus = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandebuslistmobileA/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeBusServices dbs = new DemandeBusServices();

                try {
                    listDemandeBus = dbs.parseDemandeBus(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeBus;
    }

    public ArrayList<DemandeBus> SearchDemandeBus(String ville) {
        listDemandeBus = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandebusmobileSearch?villeDepart=" + ville;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeBusServices dbs = new DemandeBusServices();

                try {
                    listDemandeBus = dbs.parseDemandeBus(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeBus;
    }

}
