/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import com.pi.tevent.Entities.Bus;
/**
 *
 * @author al199
 */
public class BusService {
    
    private ConnectionRequest req = new ConnectionRequest();
    public static BusService instance;
    public ArrayList<Bus> listBus  = new ArrayList<>();
    public boolean ResultOK;
    
    public BusService() {
        req = new ConnectionRequest();
    }

    public static BusService getInstance() {
        if (instance == null) {
            instance = new BusService();
        }

        return instance;
    }
    
    public ArrayList<Bus> parseCategories(String jsonText) {
        ArrayList<Bus> buses = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            System.out.println(jsonText);
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            ArrayList<Object> list = (ArrayList<Object>) tasksListJson.get("root");
            for (Object obj : list) {
                String objString= new String(""+obj);
                JSONParser JObj = new JSONParser();
                Map<String, Object> BusJson;
                BusJson = (Map<String, Object>)obj;
                Map<String,Object> ls = (Map<String,Object>) BusJson;
                Bus b = new Bus();
                int i =0;
                for(Object item :ls.entrySet()){
                    String val = ""+item;
                    val=val.substring(val.indexOf("=")+1,val.length());
                    switch(i){
                        case 0: {
                            val=val.substring(0,val.indexOf("."));
                            b.setId(Integer.parseInt(val));
                            break;
                        }
                        case 1: {
                            b.setFabriquant((String)val);
                            break;
                        }
                        case 2: {
                            b.setModele((String)val);
                            break;
                        }
                        case 3: {
                            val=val.substring(0,val.indexOf("."));
                            b.setNbPlace(Integer.parseInt(val));
                            break;
                        }
                        case 4: {
                            b.setPanne(Boolean.parseBoolean(val));
                            break;
                        }
                    }
                    i++;
                }
                System.out.println(b);
                buses.add(b);  
            }
        } catch (IOException ex) {
            Dialog.show("ERROR", "ERREURR dans parseArticles", new Command("OK"));
        }
        return buses;
    }
    
    public ArrayList<Bus> listBus() {
        String url = "http://127.0.0.1:8000/listbusmobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listBus = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listBus;
    }    
    
    public boolean addBus(Bus b){
            //String url = "http://127.0.0.1:8000/ajoutbusmobile?fabriquant="+b.getFabriquant()+"&modele="+b.getModele()+"&nbPlace="+b.getNbPlace()+"&panne="+b.getNbPlace();
            String url = "http://127.0.0.1:8000/ajoutbusmobile";
        ConnectionRequest req = new ConnectionRequest(url);
        req.addArgument("fabriquant", b.getFabriquant());
        req.addArgument("modele", b.getModele());
        req.addArgument("nbPlace", ""+b.getNbPlace());
        req.addArgument("panne", ""+b.getNbPlace());
        req.addResponseCodeListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK=req.getResponseCode()==200;
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }
    
    public boolean updateBus(Bus b){
        String url = "http://127.0.0.1:8000/updatebusmobile";
        ConnectionRequest req = new ConnectionRequest(url);
        System.out.println(b);
        req.addArgument("id", ""+b.getId());
        req.addArgument("fabriquant", b.getFabriquant());
        req.addArgument("modele", b.getModele());
        req.addArgument("nbPlace", ""+b.getNbPlace());
        req.addArgument("panne", ""+b.getNbPlace());
        req.addResponseCodeListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK=req.getResponseCode()==200;
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }
    
    public boolean deleteBus(String id){
        String url = "http://127.0.0.1:8000/suppbusmobile";
        ConnectionRequest req = new ConnectionRequest(url);
        req.addArgument("id", ""+id);
        req.addResponseCodeListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK=req.getResponseCode()==200;
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }
}
