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
public class Remboursement {
    private int idRem;
    boolean decision;
    private int idu;
     private int idT;
    public Remboursement(int idRem, boolean decision, int idu, int idT) {
        this.idRem = idRem;
        this.decision = decision;
        this.idu = idu;
        this.idT = idT;
    }

    public Remboursement(boolean decision, int idu, int idT) {
        this.decision = decision;
        this.idu = idu;
        this.idT = idT;
    }
      

    public Remboursement() {
    }
      public Remboursement(boolean decision, int idT) {
        this.decision = decision;
        this.idT = idT;
    }

    public Remboursement(int idRem, boolean decision, int idT) {
        this.idRem = idRem;
        this.decision = decision;
        this.idT = idT;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

  

    public int getIdRem() {
        return idRem;
    }

    public void setIdRem(int idRem) {
        this.idRem = idRem;
    }

    public boolean getDateRem() {
        return decision;
    }

    public void setDateRem(boolean decision) {
        this.decision = decision;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    @Override
    public String toString() {
        return "Remboursement{" + "idRem=" + idRem + ", dateRem=" +  decision + ", idT=" + idT + '}';
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
        final Remboursement other = (Remboursement) obj;
        if (this.idRem != other.idRem) {
            return false;
        }
        if (!Objects.equals(this.decision, other.decision)) {
            return false;
        }
        if (!Objects.equals(this.idT, other.idT)) {
            return false;
        }
        return true;
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

   

   

 
   
    
}
