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
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.pi.tevent.Entities.Materiel;

/**
 *
 * @author al199
 */
public class MaterielService {
    private ConnectionRequest req = new ConnectionRequest();
    public ArrayList<Materiel> listBus  = new ArrayList<>();
    
    public ArrayList<Materiel> parseCategories(String jsonText) {
        ArrayList<Materiel> materiels = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            ArrayList<Object> list = (ArrayList<Object>) tasksListJson.get("root");
            for (Object obj : list) {
                String objString= new String(""+obj);
                JSONParser JObj = new JSONParser();
                Map<String, Object> MaterielJson;
                MaterielJson = (Map<String, Object>)obj;
                Map<String,Object> ls = (Map<String,Object>) MaterielJson;
                Materiel m = new Materiel();
                int i =0;
                for(Object item :ls.entrySet()){
                    String val = ""+item;
                    val=val.substring(val.indexOf("=")+1,val.length());
                    switch(i){
                        case 0: {
                            val=val.substring(0,val.indexOf("."));
                            m.setId(Integer.parseInt(val));
                            break;
                        }
                        case 1: {
                            m.setLabel((String)val);
                            break;
                        }
                        case 2: {
                            val=val.substring(0,val.indexOf("."));
                            m.setStock(Integer.parseInt(val));
                            break;
                        }
                        case 3: {
                            val=val.substring(0,val.indexOf("."));
                            m.setQte_reserve(Integer.parseInt(val));
                            break;
                        }
                        case 4: {
                            //val=val.substring(0,val.indexOf("."));
                            m.setPrix(Float.parseFloat(val));
                            break;
                        }
                        case 5: {
                            //val=val.substring(0,val.indexOf("."));
                            m.setDispo(Boolean.parseBoolean(val));
                            break;
                        }
                    }
                    i++;
                }
                materiels.add(m);  
            }
        } catch (IOException ex) {
            Dialog.show("ERROR", "ERREURR dans parseArticles", new Command("OK"));
        }
        return materiels;
    }
    
    public ArrayList<Materiel> listMateriel() {
        String url = "http://127.0.0.1:8000/listmaterieljava";
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
}
