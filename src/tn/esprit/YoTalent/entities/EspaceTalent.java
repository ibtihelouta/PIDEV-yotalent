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
public class EspaceTalent {
    
private int idEST;
    private String username; //username
      private String image; 
      private int nbVotes;
    private int idU;
     private int idCat;

    public EspaceTalent(int idEST, String username, String image, int nbVotes, int idU, int idCat) {
        this.idEST = idEST;
        this.username = username;
        this.image = image;
        this.nbVotes = nbVotes;
        this.idU = idU;
        this.idCat = idCat;
    }

    public EspaceTalent(String username, String image, int nbVotes, int idU, int idCat) {
        this.username = username;
        this.image = image;
        this.nbVotes = nbVotes;
        this.idU = idU;
        this.idCat = idCat;
    }
    
    
  
    
  

    public EspaceTalent() {
    }

    public EspaceTalent(int idEST) {
        this.idEST = idEST;
    }
    

    public EspaceTalent(String username) {
        this.username = username;
    }

    public EspaceTalent(String username, String image) {
        this.username = username;
        this.image = image;
    }
    

    public EspaceTalent(String username, String image, int idU, int idCat) {
        this.username = username;
        this.image = image;
        this.idU = idU;
        this.idCat = idCat;
    }

    public EspaceTalent(int idEST, String username, String image, int idU) {
        this.idEST = idEST;
        this.username = username;
        this.image = image;
        this.idU = idU;
    }
    

    public EspaceTalent(int idEST, String username, String image, int idU,  int idCat) {
        this.idEST = idEST;
        this.username = username;
        this.image = image;
        this.idU = idU;
        this.idCat = idCat;
    }

    public EspaceTalent(int idEST, int idU) {
        this.idEST = idEST;
        this.idU = idU;
    }

    public int getIdEST() {
        return idEST;
    }

    public int getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(int nbVotes) {
        this.nbVotes = nbVotes;
    }
    

    public void setIdEST(int idEST) {
        this.idEST = idEST;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

 
    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
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
        final EspaceTalent other = (EspaceTalent) obj;
        if (this.idEST != other.idEST) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (this.nbVotes != other.nbVotes) {
            return false;
        }
        if (!Objects.equals(this.idU, other.idU)) {
            return false;
        }
        if (!Objects.equals(this.idCat, other.idCat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EspaceTalent{" + "idEST=" + idEST + ", username=" + username + ", image=" + image + ", nbVotes=" + nbVotes + ", idU=" + idU + ", idCat=" + idCat + '}';
    }

    
    
}



