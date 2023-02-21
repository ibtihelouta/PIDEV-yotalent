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
    private Ticket prixT;
    private Participation idP;

    public Remboursement() {
    }

    public Remboursement(String dateRem, Ticket prixT, Participation idP) {
        this.dateRem = dateRem;
        this.prixT = prixT;
        this.idP = idP;
    }
    
    

    public Remboursement(int idRem, String dateRem, Ticket prixT, Participation idP) {
        this.idRem = idRem;
        this.dateRem = dateRem;
        this.prixT = prixT;
        this.idP = idP;
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

    public Ticket getPrixT() {
        return prixT;
    }

    public void setPrixT(Ticket prixT) {
        this.prixT = prixT;
    }

    public Participation getIdP() {
        return idP;
    }

    public void setIdP(Participation idP) {
        this.idP = idP;
    }

    @Override
    public String toString() {
        return "Remboursement{" + "idRem=" + idRem + ", dateRem=" + dateRem + ", prixT=" + prixT + ", idP=" + idP + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Remboursement other = (Remboursement) obj;
        if (this.idRem != other.idRem) {
            return false;
        }
        if (!Objects.equals(this.dateRem, other.dateRem)) {
            return false;
        }
        if (!Objects.equals(this.prixT, other.prixT)) {
            return false;
        }
        if (!Objects.equals(this.idP, other.idP)) {
            return false;
        }
        return true;
    }
    
    
}
