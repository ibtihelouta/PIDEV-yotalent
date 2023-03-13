/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.entities;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Video {
    private int idVid;
    private String nomVid ;
    private String url ; 
    private Categorie idCat;
    
    public Video() {
    
}
    public Video (int idVid,String nomVid, String url,Categorie idCat){
        this.idCat = idCat ;
        this.idVid = idVid; 
        this.nomVid = nomVid; 
        this.url = url ; 
        
    }
    public Video(Categorie idCat,String nomVid,String url){
        this.idCat = idCat; 
        this.nomVid = nomVid; 
        this.url = url;
    }

    public Video(String nomVid, String url) {
        this.nomVid = nomVid;
        this.url = url;
    }

    public Video(int idVid, String nomVid, String url) {
        this.idVid = idVid;
        this.nomVid = nomVid;
        this.url = url;
    }

    public int getIdVid() {
        return idVid;
    }

    public String getNomVid() {
        return nomVid;
    }

    public String getUrl() {
        return url;
    }

    public Categorie getIdCat() {
        return idCat;
    }

    public void setIdVid(int idVid) {
        this.idVid = idVid;
    }

    public void setNomVid(String nomVid) {
        this.nomVid = nomVid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIdCat(Categorie idCat) {
        this.idCat = idCat;
    }

    @Override
    public String toString() {
        return "Video{" + "nomVid=" + nomVid + ", url=" + url + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (this.idVid != other.idVid) {
            return false;
        }
        if (!Objects.equals(this.nomVid, other.nomVid)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.idCat, other.idCat)) {
            return false;
        }
        return true;
    }
    
    
    
}
