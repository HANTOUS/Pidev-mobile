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
import com.codename1.ui.events.ActionListener;
import com.pi.tevent.Entities.Camping;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class CampingServices {

   public ArrayList<Camping> Campings;
    
    public static CampingServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private CampingServices() {
         req = new ConnectionRequest();
    }

    public static CampingServices getInstance() {
        if (instance == null) {
            instance = new CampingServices();
        }
        return instance;
    }

    public boolean addCamping(Camping t) {
        String url = Statics.BASE_URL + "/campJSON/add?nomevent=" + t.getNomevent()
                + "&heuredebut=" + t.getHeuredebut()+ "&heurefin=" + t.getHeurefin()
                + "&lieu=" + t.getLieu()+ "&nbmaxparticipant=" + t.getNbmaxparticipant()
                + "&description=" + t.getDescription()+ "&tarif=" + t.getTarif()
                + "&localisation=" + t.getLocalisation();  //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service Camping, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Camping> parseCampings(String jsonText){
        try {
            Campings=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> CampingsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)CampingsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Camping t = new Camping();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               // t.setTyperand(((int)Float.parseFloat(obj.get("typerand").toString())));
               t.setNomevent(obj.get("nomevent").toString());
               t.setHeuredebut(obj.get("heuredebut").toString());
               t.setHeurefin(obj.get("heurefin").toString());
               t.setDescription(obj.get("description").toString());
               t.setLieu(obj.get("lieu").toString());
               float tarif=Float.parseFloat(obj.get("tarif").toString());
               t.setTarif(tarif);
               float nbpart =Float.parseFloat(obj.get("nbmaxparticipant").toString());
               t.setNbmaxparticipant((int)nbpart);
                t.setLocalisation(obj.get("localisation").toString());
               // t.setId((int)id);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setName(obj.get("name").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Campings.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Campings;
    }
     public void deleteComm(int id) {
        ConnectionRequest con = new ConnectionRequest();
      //  "http://localhost/pi-dev/web/app_dev.php/forum/supprimerComMobile?idCom=" + id;
        String Url = Statics.BASE_URL+"/deleteCampingjson&id=" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   /* public ArrayList<Camping> getAllCampings(){
        String url = Statics.BASE_URL+"/apicamping";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Campings = parseCampings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Campings;
    }*/
      public ArrayList<Camping> getAllTasks(){
        String url = Statics.BASE_URL+"/listcampA";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Campings = parseCampings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Campings;
    }

   

}
