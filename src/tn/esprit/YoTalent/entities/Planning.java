/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.entities;
import java.time.LocalDate;
import tn.esprit.YoTalent.entities.Evenement;
import java.util.List;
import java.util.Objects;

/**

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Planning   {
    
    private int idP ;
    public  Evenement evenement;
    private String hour,nomActivite,datePL;
    private int idEv ;

    public Planning() {
    }

    public Planning(int idP, Evenement evenement, String hour, String nomActivite, String datePL, int idEv) {
        this.idP = idP;
        this.evenement = evenement;
        this.hour = hour;
        this.nomActivite = nomActivite;
        this.datePL = datePL;
        this.idEv = idEv;
    }

    public Planning(int idP,Evenement evenement ) {
        this.idP = idP;
        this.evenement = evenement;
    }
    public Planning(  String hour, String nomActivite, String datePL, int idEv) {
        
        this.hour = hour;
        this.nomActivite = nomActivite;
        this.datePL = datePL;
        this.idEv = idEv;
    }
    
    
    
public Planning(int idP, String hour, String nomActivite, String datePL, int idEv) {
        this.idP = idP;
        this.hour = hour;
        this.nomActivite = nomActivite;
        this.datePL = datePL;
        this.idEv = idEv;
    }

    public Planning(int idP, String hour, String nomActivite, String datePL) {
        this.idP = idP;
        this.hour = hour;
        this.nomActivite = nomActivite;
        this.datePL = datePL;
        
    }

    public Planning(String hour, String nomActivite, String datePL) {
        this.hour = hour;
        this.nomActivite = nomActivite;
        this.datePL = datePL;
    }


  

    
    
    
    public int getIdP() {
        return idP;
    }

    public String getHour() {
        return hour;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public String getDatePL() {
        return datePL;
    }

    public int getIdEv() {
        return idEv;
    }

    public Evenement getEvenement() {
        return evenement;
    }
    

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public void setDatePL(String datePL) {
        this.datePL = datePL;
    }

    public void setIdEv(int  idEv) {
        this.idEv = idEv;
        
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planning other = (Planning) obj;
        if (this.idP != other.idP) {
            return false;
        }
        if (this.idEv != other.idEv) {
            return false;
        }
        if (!Objects.equals(this.hour, other.hour)) {
            return false;
        }
        if (!Objects.equals(this.nomActivite, other.nomActivite)) {
            return false;
        }
        if (!Objects.equals(this.datePL, other.datePL)) {
            return false;
        }
        if (!Objects.equals(this.evenement, other.evenement)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Planning{" + "idP=" + idP + ", evenement=" + evenement + ", hour=" + hour + ", nomActivite=" + nomActivite + ", datePL=" + datePL + ", idEv=" + idEv + '}';
    }
    

   
    
    
}
