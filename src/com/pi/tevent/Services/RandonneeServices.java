package com.pi.tevent.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pi.tevent.Entities.Randonnee;
import com.pi.tevent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class RandonneeServices {

    public ArrayList<Randonnee> tasks;
    
    public static RandonneeServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private RandonneeServices() {
         req = new ConnectionRequest();
    }

    public static RandonneeServices getInstance() {
        if (instance == null) {
            instance = new RandonneeServices();
        }
        return instance;
    }

    public boolean addRandonnee(Randonnee t) {
        
       // "/demandebusmobile/update?id=" + db.getId() + "&utilisateur=" + db.getUtilisateur() + "&nbParticipant=" + db.getNbParticipant() + "&villeDepart=" + db.getVilleDepart() + "&villeArrivee=" + db.getVilleArrivee() + "&heureDepart=" + db.getHeureDepart() + "&heureArrivee=" + db.getHeureArrivee() + "&etat=" + db.getEtat() + "&jourLocation=" + new Date();
        String url = Statics.BASE_URL + "/randJSON/add?nomevent=" + t.getNomevent()
                + "&heuredebut=" + t.getHeuredebut()+ "&heurefin=" + t.getHeurefin()
                + "&lieu=" + t.getLieu()+ "&nbmaxparticipant=" + t.getNbmaxparticipant()
                + "&description=" + t.getDescription()+ "&tarif=" + t.getTarif()
                + "&typerand=" + t.getTyperand() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service Randonnee, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void deleteComm(int id) {
        ConnectionRequest con = new ConnectionRequest();
      //  "http://localhost/pi-dev/web/app_dev.php/forum/supprimerComMobile?idCom=" + id;
        String Url = Statics.BASE_URL+"/deleteRandonnejson?id=" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public ArrayList<Randonnee> parseRandonnees(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Randonnee t = new Randonnee();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 t.setNomevent(obj.get("nomevent").toString());
               t.setHeuredebut(obj.get("heuredebut").toString());
               t.setHeurefin(obj.get("heurefin").toString());
               t.setDescription(obj.get("description").toString());
               t.setLieu(obj.get("lieu").toString());
               float tarif=Float.parseFloat(obj.get("tarif").toString());
               t.setTarif(tarif);
               float nbpart =Float.parseFloat(obj.get("nbmaxparticipant").toString());
               t.setNbmaxparticipant((int)nbpart);
               // t.setTyperand(((int)Float.parseFloat(obj.get("typerand").toString())));
                t.setTyperand(obj.get("typerand").toString());
                              // t.setDescription(obj.get("description").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
      
        return tasks;
    }
    
    public ArrayList<Randonnee> getAllTasks(){
        String url = Statics.BASE_URL+"/listrandA";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseRandonnees(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}

