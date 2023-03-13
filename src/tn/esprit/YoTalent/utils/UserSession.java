/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.utils;

import tn.esprit.YoTalent.entities.User;


/**
 *
 * @author abirk
 */
public class UserSession {
    
    private static UserSession instance;

    private User user;

    private UserSession(User user) {
        this.user=user;
    }

    public static UserSession getInstace(User user) {
        if(instance == null) {
            instance = new UserSession(user);
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void cleanUserSession() {
        instance=null;
    }
    
    
    
    
}