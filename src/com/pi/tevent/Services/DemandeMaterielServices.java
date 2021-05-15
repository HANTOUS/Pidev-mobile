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
import com.pi.tevent.Entities.DemandeMateriel;
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
public class DemandeMaterielServices {
     ArrayList<DemandeMateriel> listDemandeMateriel;
     ArrayList<Integer> Stock;
    public boolean resultOK;
    public static DemandeMaterielServices instance;
    private ConnectionRequest req;
            String label ="";
            int qte_reserve ;
            int stock ;
    public DemandeMaterielServices() {
        req = new ConnectionRequest();
    }

    public static DemandeMaterielServices getInstance() {
        if (instance == null) {
            instance = new DemandeMaterielServices();
        }

        return instance;
    }

    public boolean refuserDemandeMateriel(int id) {

        String url = Statics.BASE_URL + "/demandematerielmobile/refuser?id=" + id;
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

    public boolean accepterDemandeMateriel(int id) {

        String url = Statics.BASE_URL + "/demandematerielmobile/accepter?id=" + id;
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

    public boolean addDemandeMateriel(DemandeMateriel db) {

        String url = Statics.BASE_URL + "/demandematerielmobile/add?utilisateur=" + db.getUtilisateur() + "&qte=" + db.getQte()+ "&Materiel=" + db.getMateriel() + "&dateFin=" + db.getDateFin()+ "&dateDebut=" + db.getDateDebut()+ "&etat="+db.getEtat();
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

    public boolean updateDemandeMateriel(DemandeMateriel db) {

        String url = Statics.BASE_URL + "/demandematerielmobile/update?id=" + db.getId() + "&utilisateur=" + db.getUtilisateur() + "&qte=" + db.getQte()+ "&Materiel=" + db.getMateriel() + "&dateFin=" + db.getDateFin()+ "&dateDebut=" + db.getDateDebut();
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

    public boolean deleteDemandeMateriel(DemandeMateriel db) {

        String url = Statics.BASE_URL + "/demandematerielmobile/delete?id=" + db.getId();
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

    public ArrayList<DemandeMateriel> parseDemandeMateriel(String json) throws ParseException, NumberFormatException {
        listDemandeMateriel = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> DemandeMaterielListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) DemandeMaterielListJson.get("root");

            for (Map<String, Object> obj : list) {
                DemandeMateriel db = new DemandeMateriel();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                float nump = Float.parseFloat(obj.get("qte").toString());
//                float user = Float.parseFloat(obj.get("utilisateur").toString());

                db.setId((int) id);
                db.setQte(obj.get("qte").toString());
                
                Map <String, Object> utilisateur2 = (Map <String, Object>) obj.get("utilisateur");

                float userId = Float.parseFloat(utilisateur2.get("id").toString());
                
                Map <String, Object> materiel = (Map <String, Object>) obj.get("Materiel");

                float materielId = Float.parseFloat(materiel.get("id").toString());
                
                      
                db.setUtilisateur((int) userId);
                db.setMateriel((int) materielId);
                
               
                db.setEtat((obj.get("etat").toString()));

                //Date Converter
                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDeb = formatter.parse(obj.get("dateDebut").toString());
                Date dateFin = formatter.parse(obj.get("dateFin").toString());

                db.setDateDebut(dateDeb);

                db.setDateFin(dateFin);


                listDemandeMateriel.add(db);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listDemandeMateriel;

    }

    
    public ArrayList<DemandeMateriel> getDemandeMaterielByUser(int userId) {
        listDemandeMateriel = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandemateriellistmobile?utilisateur="+userId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeMaterielServices dbs = new DemandeMaterielServices();

                try {
                    listDemandeMateriel = dbs.parseDemandeMateriel(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeMateriel;
    }

    public ArrayList<DemandeMateriel> getAllDemandeMateriel() {
        listDemandeMateriel = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandemateriellistmobileA/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeMaterielServices dbs = new DemandeMaterielServices();

                try {
                    listDemandeMateriel = dbs.parseDemandeMateriel(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeMateriel;
    }

    public ArrayList<DemandeMateriel> SearchDemandeMateriel(String ville) {
        listDemandeMateriel = new ArrayList<>();

        String url = Statics.BASE_URL + "/demandematerielmobileSearch?villeDepart=" + ville;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeMaterielServices dbs = new DemandeMaterielServices();

                try {
                    listDemandeMateriel = dbs.parseDemandeMateriel(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listDemandeMateriel;
    }

    public String parseLabelMateriel(String json) throws ParseException, NumberFormatException {
        label="";
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

           
                
                label=obj.get("label").toString();
               

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return label;

    }

public String getLabel(int id) {
    label="";
        String url = Statics.BASE_URL + "/getmateriel?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeMaterielServices dbs = new DemandeMaterielServices();

                try {
                    label = dbs.parseLabelMateriel(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return label;
    }
      public ArrayList<Integer> parseStockMateriel(String json) throws ParseException, NumberFormatException {
        stock=0;
        qte_reserve=0;
             Stock = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

                            float qte = Float.parseFloat(obj.get("qte_reserve").toString());
                float st = Float.parseFloat(obj.get("stock").toString());
                stock= (int) st;
                qte_reserve= (int) qte;
                
                
               Stock.add(stock);
               Stock.add(qte_reserve);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Stock;

    }
      public ArrayList<Integer> getStock(int id) {
     Stock = new ArrayList<>();
        String url = Statics.BASE_URL + "/getmateriel?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandeMaterielServices dbs = new DemandeMaterielServices();

                try {
                    Stock = dbs.parseStockMateriel(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return Stock;
    }
}
