
package tn.esprit.YoTalent.entities;

import java.util.Objects;
import javafx.scene.image.ImageView;


public class User {
    private int id;
    private String nom;
    private String email;
    private String motpass;
    private String Role;
    private String image;
    private ImageView imageV;

    public User(int id, String nom, String email, String motpass, String Role, String image) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motpass = motpass;
        this.Role = Role;
        this.image = image;
    }

    public User() {
    }
    

    public User(String nom, String email, String motpass, String Role, String image) {
        this.nom = nom;
        this.email = email;
        this.motpass = motpass;
        this.Role = Role;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotpass() {
        return motpass;
    }

    public void setMotpass(String motpass) {
        this.motpass = motpass;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImageV() {
        return imageV;
    }

    public void setImageV(ImageView imageV) {
        this.imageV = imageV;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", motpass=" + motpass + ", Role=" + Role + ", image=" + image + ", imageV=" + imageV + '}';
    }


  

   
}
