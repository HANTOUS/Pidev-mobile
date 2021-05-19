/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.tevent.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.CharArrayReader;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.pi.tevent.utils.Statics;
import com.pi.tevent.utils.SessionUser;
import com.codename1.ui.Dialog;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.PickerComponent;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Container;
import com.pi.tevent.Gui.ProfilForm;
import com.pi.tevent.Gui.EmailForm;
import com.pi.tevent.Gui.ForgotForm;
import com.pi.tevent.Gui.BaseForm;
import com.pi.tevent.Gui.LoginForm;
import com.pi.tevent.Gui.ResetForm;
import com.pi.tevent.Entities.Utilisateur;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.codename1.ui.spinner.Picker;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import java.io.IOException;
import com.codename1.io.Log;
/**
 *
 * @author hanto
 */
public class UtilisateurServices {
    public static UtilisateurServices instance = null;

    public static boolean ResutatOk=true;
    
    private ConnectionRequest req;
    
    public static UtilisateurServices getInstance(){
        if (instance == null)
            instance = new UtilisateurServices();
        return instance;
    }

    public UtilisateurServices(){
        req = new ConnectionRequest();
    }

    
    public void signup(TextField nom,TextField prenom,TextField email,TextField password,TextField cin,Picker dateN,Resources res ){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");  
        String date = formatter.format(dateN.getDate()); 
        String url = Statics.BASE_URL+"/user/signup?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
        "&email="+email.getText().toString()+"&cin="+cin.getText().toString()+"&password="+password.getText().toString()+
        "&dateNaissance="+date;
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            byte[] data = (byte[]) evt.getMetaData();
            String responseData = new String(data);
            System.out.println("data: "+responseData);
            req.removeResponseListener(this);
            
            Message m = new Message("<h1>Activation de Votre Compte</h1>\n" +
            "<p>Vous avez cree un compte sur notre site Tunisia Events, veuillez copier le code ci-dessous pour l'activer</p>\n" +
            "<p>"+"CODE"+"</p>");
            m.setMimeType(Message.MIME_HTML);

            Display.getInstance().sendMessage(new String[] {email.getText().toString()}, "Activation du Compte", m);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        

    }
    
    public void signin(TextField email,TextField password,Resources res){
        String url = Statics.BASE_URL+"/user/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
        ArrayList<Utilisateur> users=new ArrayList<>();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            System.out.println("ICIIII");
            JSONParser j = new JSONParser();
                
            String json =  new String(req.getResponseData())+"";
        try{
            if (json.equals("user does not exist"))
                Dialog.show("Echec d'authentification","Email n'existe pas!","OK",null);
            else if (json.equals("passowrd not correct"))
                Dialog.show("Echec d'authentification","Mot de passe invalid!","OK",null);
            else{
                System.out.println("DATA : "+json);
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                System.out.println("user : "+user);
                List<Map<String,Object>> list = (List<Map<String,Object>>) user.get("root");
                
               // for(Map.Entry obj : user.entrySet()){
                //Création des tâches et récupération de leurs données
                    Utilisateur u = new Utilisateur();
                    
                    u.setId((int)Float.parseFloat(user.get("id").toString()));
                    u.setEmail(user.get("email").toString());
                    u.setPassword(user.get("password").toString());
                    u.setNom(user.get("nom").toString());
                    u.setPrenom(user.get("prenom").toString());
                    u.setCin(String.valueOf((int)Float.parseFloat(user.get("cin").toString())));
                    u.setImage(user.get("image").toString());

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
                    Date date = formatter.parse(user.get("dateNaissance").toString());
                    u.setDateNaissance(date);

                    users.add(u);
                    SessionUser.setUser(u);
                    System.out.println("user : "+u);
                //    }
                if (!users.isEmpty()){
                     try {
                        Media m = MediaManager.createBackgroundMedia("file://C:\\Users\\hanto\\Desktop\\Esprit\\3eme\\PI\\TEvent\\src\\com\\pi\\tevent\\uploads\\welcome.mp3");
                        m.play();
                    } catch (IOException err) {
                        Log.e(err);
                    }
                    new ProfilForm(res).show();}
                req.removeResponseListener(this);
            }
            }
        catch(Exception ex){
            ex.printStackTrace();}
            
            }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    }

    //reset password
    public void envoyerMail(TextField email,Resources res,Button envoyer){
         String url = Statics.BASE_URL+"/api/forgetpass?email="+email.getText().toString();

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            System.out.println("ICIIII");
            JSONParser j = new JSONParser();
                
            String json =  new String(req.getResponseData())+"";
        try{
            if (json.equals("Compte n'existe pas"))
                Dialog.show("Echec ","Email n'existe pas!","OK",null);
            else if (json.equals("fail"))
                Dialog.show("Echec ","Echec de demande réessayer!","OK",null);
            else   if(json.equals("Code mis"))
             {   //api envoi mail reset pass
                

                //validation code
                InteractionDialog nameDialog = new InteractionDialog();
                nameDialog.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));

                // Hint for the user
                SpanLabel hintLabel = new SpanLabel("Indiquer le code");
                hintLabel.setTextUIID("PaddedLabel");

                TextField code = new TextField(
                        //chosenAlarm.name.get() == null ? "Ma destination préférée" : chosenAlarm.name.get()
                );
               // nameTf.setUIID(textFieldStyleName);
               // System.err.println("The textfield colour is " + nameTf.getUnselectedStyle().getFgColor());

                // Validate text button
                Button valid = new Button("Valider");
                Button annuler = new Button("Annuler");
                //validateNameButton.setUIID("ValidateButton");
                Container nameButtons = BoxLayout.encloseX(valid,annuler);

                valid.addActionListener((ex) -> checkToken(email,code,res));

                annuler.addActionListener((ex1) -> new EmailForm(res).show());

                nameDialog.addComponent(BorderLayout.CENTER, code);
                nameDialog.addComponent(BorderLayout.NORTH, BoxLayout.encloseY(new BaseForm().createLineSeparator(),hintLabel));
                // The buttons will be centered
                nameDialog.addComponent(BorderLayout.SOUTH, BorderLayout.centerCenter(nameButtons));

                // Shows the dialog in the center of the screen   
                nameDialog.showPopupDialog(envoyer);
                
                req.removeResponseListener(this);
            }
            }
        catch(Exception e){
            e.printStackTrace();}
            
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void checkToken(TextField email,TextField token,Resources res){
        String url = Statics.BASE_URL+"/api/checkToken?email="+email.getText().toString()+"&token="+token.getText().toString();
        
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

            JSONParser j = new JSONParser();
                
            String json =  new String(req.getResponseData())+"";
        try{
            System.out.println(json);
            if (json.equals("fail")){
                Dialog.show("Echec ","Code Invalid!","OK",null);
             }
            else if (json.equals("success"))  {  
                new ForgotForm(res,token).show();}
            }
        catch(Exception ex){
            ex.printStackTrace();}
            
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    public void resetpass(TextField token,TextField password,Resources res){
        String url = Statics.BASE_URL+"/api/resetPassword?token="+token.getText().toString()+"&password="+password.getText().toString();
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

            JSONParser j = new JSONParser();
                
            String json =  new String(req.getResponseData())+"";
        try{
            System.out.println(json);
            if (json.equals("fail")){
                Dialog.show("Echec ","Réessayer!","OK",null);
             }
            else if (json.equals("mot de passe changé"))  {  
                new LoginForm(res).show();}
            }
        catch(Exception ex){
            ex.printStackTrace();}
            
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

     public void activerCompte(TextField token,Resources res){
        String url = Statics.BASE_URL+"/api/activation?token="+token.getText().toString();
        
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

            JSONParser j = new JSONParser();
                
            String json =  new String(req.getResponseData())+"";
        try{
            System.out.println(json);
            if (json.equals("Code Invalid")){
                Dialog.show("Echec ","Code Invalid!","OK",null);
             }
            else if (json.equals("Compte activé"))  {  
                new LoginForm(res).show();}
            }
        catch(Exception ex){
            ex.printStackTrace();}
            
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }

    public void modifierProfile(int id,TextField nom,TextField prenom,TextField email,TextField cin,Picker dateN,String photo,Resources res ){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");  
        String date = formatter.format(dateN.getDate()); 
        String url = Statics.BASE_URL+"/user/profile?id="+id+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
        "&email="+email.getText().toString()+"&cin="+cin.getText().toString()+"&dateNaissance="+date+"&photo="+photo;
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            byte[] data = (byte[]) evt.getMetaData();
            String responseData = new String(data);
            System.out.println("data: "+responseData);
            req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void changerPassword(String email,TextField password,TextField newPassword,Resources res ){
        
        String url = Statics.BASE_URL+"/api/changerPassword?email="+email+"&password="+password.getText().toString()+"&newPassword="+newPassword.getText().toString();
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();

                String json =  new String(req.getResponseData())+"";
                try{
                    System.out.println(json);
                    if (json.equals("fail")){
                        Dialog.show("Echec ","Réessayer!","OK",null);
                     }
                    else if(json.equals("mot de passe actuel invalid")){
                        Dialog.show("Echec ","Mot de passe Actuel est incorrecte","OK",null);
                     }
                    else if (json.equals("mot de passe changé"))  {  
                        Dialog.show("Information ","Votre mot de passe a été changer!","OK",null);
                        new ResetForm(res).show();}
                    }
                catch(Exception ex){
                    ex.printStackTrace();}
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
