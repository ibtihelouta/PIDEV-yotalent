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
public class Voyage {
private int idVoy;
    private String dateDVoy,dateRVoy;
    private String destination;
   // private Contrat idC;
    private int idC;
     
    public Voyage() {
    }
    

    public Voyage(String dateDVoy, String dateRVoy, String destination, int idC) {
        this.dateDVoy = dateDVoy;
        this.dateRVoy = dateRVoy;
        this.destination = destination;
        this.idC = idC;
    }

    public Voyage(int idVoy, String dateDVoy, String dateRVoy, String destination, int idC) {
        this.idVoy = idVoy;
        this.dateDVoy = dateDVoy;
        this.dateRVoy = dateRVoy;
        this.destination = destination;
        this.idC = idC;
    }

    public int getIdVoy() {
        return idVoy;
    }

    public void setIdVoy(int idVoy) {
        this.idVoy = idVoy;
    }

    public String getDateDVoy() {
        return dateDVoy;
    }

    public void setDateDVoy(String dateDVoy) {
        this.dateDVoy = dateDVoy;
    }

    public String getDateRVoy() {
        return dateRVoy;
    }

    public void setDateRVoy(String dateRVoy) {
        this.dateRVoy = dateRVoy;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    @Override
    public String toString() {
        return "Voyage{" + "idVoy=" + idVoy + ", dateDVoy=" + dateDVoy + ", dateRVoy=" + dateRVoy + ", destination=" + destination + ", idC=" + idC + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Voyage other = (Voyage) obj;
        if (this.idVoy != other.idVoy) {
            return false;
        }
        if (this.idC != other.idC) {
            return false;
        }
        if (!Objects.equals(this.dateDVoy, other.dateDVoy)) {
            return false;
        }
        if (!Objects.equals(this.dateRVoy, other.dateRVoy)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        return true;
    }
    
    

   

    

}
