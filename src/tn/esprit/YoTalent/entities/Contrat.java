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
public class Contrat {
    private int idC;
    private String nomC,DateDC,DateFC;
    
    public Contrat() {
    }

    public Contrat(String nomC, String DateDC, String DateFC) {
        this.nomC = nomC;
        this.DateDC = DateDC;
        this.DateFC = DateFC;
    }

    public int getIdC() {
        return idC;
    }

    public String getNomC() {
        return nomC;
    }

    public String getDateDC() {
        return DateDC;
    }

    public String getDateFC() {
        return DateFC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public void setDateDC(String DateDC) {
        this.DateDC = DateDC;
    }

    public void setDateFC(String DateFC) {
        this.DateFC = DateFC;
    }

    @Override
    public String toString() {
        return "Contrat{" + "idC=" + idC + ", nomC=" + nomC + ", DateDC=" + DateDC + ", DateFC=" + DateFC + '}';
    }
    

    
    
}