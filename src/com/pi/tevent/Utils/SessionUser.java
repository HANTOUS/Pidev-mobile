/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.tevent.Utils;
import com.pi.tevent.Entities.Utilisateur;
/**
 *
 * @author hanto
 */
public class SessionUser {
    private static SessionUser instance = null;
    private static Utilisateur user = null;
    
    private SessionUser(Utilisateur userConnected) {
        super();
        SessionUser.user = userConnected;
    }
    
    private SessionUser(Utilisateur userConnected, Boolean b) {
        super();
        SessionUser.user = userConnected;
    }
    
    public final static SessionUser getInstance(Utilisateur userConnected) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (SessionUser.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            //synchronized (DataSource.class) {
            //    if (DataSource.instance == null) {
            SessionUser.instance = new SessionUser(userConnected);
            //    }
            //}
        }
        return SessionUser.instance;
    }
    
    public final static SessionUser getFirstInstance(Utilisateur userConnected) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (SessionUser.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            //synchronized (DataSource.class) {
            //    if (DataSource.instance == null) {
            SessionUser.instance = new SessionUser(userConnected,false);
            //    }
            //}
        }
        return SessionUser.instance;
    }



    public static SessionUser getInstance() {
        return instance;
    }

    public static Utilisateur getUser() {
        return user;
    }


    public static void setUser(Utilisateur user) {
        SessionUser.user = user;
    }
}
