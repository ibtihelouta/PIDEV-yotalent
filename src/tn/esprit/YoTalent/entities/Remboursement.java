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
    String dateRem;
    //private Ticket idT;
     private int idT;

    public Remboursement() {
    }
      public Remboursement(String dateRem, int idT) {
        this.dateRem = dateRem;
        this.idT = idT;
    }

    public Remboursement(int idRem, String dateRem, int idT) {
        this.idRem = idRem;
        this.dateRem = dateRem;
        this.idT = idT;
    }

  

    public int getIdRem() {
        return idRem;
    }

    public void setIdRem(int idRem) {
        this.idRem = idRem;
    }

    public String getDateRem() {
        return dateRem;
    }

    public void setDateRem(String dateRem) {
        this.dateRem = dateRem;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    @Override
    public String toString() {
        return "Remboursement{" + "idRem=" + idRem + ", dateRem=" + dateRem + ", idT=" + idT + '}';
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
        if (!Objects.equals(this.dateRem, other.dateRem)) {
            return false;
        }
        if (!Objects.equals(this.idT, other.idT)) {
            return false;
        }
        return true;
    }

    public String getDateR() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

 
   
    
}
