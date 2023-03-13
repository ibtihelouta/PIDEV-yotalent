/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.entities;

/**
 *
 * @author USER
 */
public class Vote {
    private int idV; 
    private int nbrV; 
    private String dateV; 
    private int idU;
    private Evenement idEV;
    private EspaceTalent idEST;
    private Video IdVid ;
    private String nomVid; 

    public String getNomVid() {
        return nomVid;
    }

    public void setNomVid(String nomVid) {
        this.nomVid = nomVid;
    }
    
    public Vote(){
        
    }

    public Vote(int idV, int nbrV, String dateV, int idU, Evenement idEV, EspaceTalent idEST) {
        this.idV = idV;
        this.nbrV = nbrV;
        this.dateV = dateV;
        this.idU = idU;
        this.idEV = idEV;
        this.idEST = idEST;
    }

    public Vote(int nbrV, String dateV, int idU, Evenement idEV, EspaceTalent idEST) {
        this.nbrV = nbrV;
        this.dateV = dateV;
        this.idU = idU;
        this.idEV = idEV;
        this.idEST = idEST;
    }

    public Vote(int idV, int nbrV, String dateV, int idU, Video IdVid) {
        this.idV = idV;
        this.nbrV = nbrV;
        this.dateV = dateV;
        this.idU = idU;
        this.IdVid = IdVid;
    }

    public Vote(int idV, String dateV, int idU, String nomVid) {
        this.idV = idV;
        this.dateV = dateV;
        this.idU = idU;
        this.nomVid = nomVid;
    }

    public Vote(int nbrV, String dateV, int idU, Video IdVid) {
        this.nbrV = nbrV;
        this.dateV = dateV;
        this.idU = idU;
        this.IdVid = IdVid;
    }

    public Vote(int idU, Video IdVid) {
        this.idU = idU;
        this.IdVid = IdVid;
    }

    public Vote(String dateV, int idU, Video IdVid) {
        this.dateV = dateV;
        this.idU = idU;
        this.IdVid = IdVid;
    }

    public Vote(String dateV, int idU, String nomVid) {
        this.dateV = dateV;
        this.idU = idU;
        this.nomVid = nomVid;
    }

    public Vote(int idU, String nomVid) {
        this.idU = idU;
        this.nomVid = nomVid;
    }
    

    public int getIdV() {
        return idV;
    }

    public int getNbrV() {
        return nbrV;
    }

    public String getDateV() {
        return dateV;
    }

    public int getIdU() {
        return idU;
    }

    public Evenement getIdEV() {
        return idEV;
    }

    public EspaceTalent getIdEST() {
        return idEST;
    }

    public Video getIdVid() {
        return IdVid;
    }

    public void setIdV(int idV) {
        this.idV = idV;
    }

    public void setNbrV(int nbrV) {
        this.nbrV = nbrV;
    }

    public void setDateV(String dateV) {
        this.dateV = dateV;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public void setIdEV(Evenement idEV) {
        this.idEV = idEV;
    }

    public void setIdEST(EspaceTalent idEST) {
        this.idEST = idEST;
    }

    public void setIdVid(Video IdVid) {
        this.IdVid = IdVid;
    }

    @Override
    public String toString() {
        return "Vote{" + "dateV=" + dateV + ", IdVid=" + IdVid + '}';
    }
    
    
}
