/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javax.swing.UIManager.getString;

/**
 *
 * @author USER
 */
public class ServiceEvent 
        //implements IService<Evenement> 
{

     private Connection cnx;

    public ServiceEvent(){
        cnx = MaConnexion.getInstance().getCnx();
        
    }

    
   /* public void createOne(Evenement evenement) throws SQLException {
        String dateDEv,dateFEv= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
      String req =   "INSERT INTO `evenement`(`nomEv`, `dateDEv`, `dateFEv`, `localisation`,`ImageEv`) " + "VALUES ('"+evenement.getNomEv()+"','"+evenement.getDateDEv()+"','"+evenement.getDateFEv()+"','"+evenement.getLocalisation()+"','"+evenement.getImage()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Event ajouté !");
        
    }*/
    public void createOne(Evenement evenement) throws SQLException {
        
    //String dateFEv = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
      
    String req = "INSERT INTO evenement (nomEv, dateDEv, dateFEv, localisation, ImageEv) VALUES (?, ?, ?, ?, ?)";
       
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.setString(1, evenement.getNomEv());
    pst.setString(2, evenement.getDateDEv());
    pst.setString(3, evenement.getDateFEv());
    pst.setString(4, evenement.getLocalisation());
    pst.setString(5, evenement.getImage());
    
    int rowsInserted = pst.executeUpdate();
    
    if (rowsInserted > 0) {
        System.out.println("Event " + evenement.getNomEv() + " has been created successfully.");
    } else {
        System.out.println("Failed to create event " + evenement.getNomEv() + ".");
    }
}


  
    public void updateOne(Evenement evenement) throws SQLException {
        
        String req =  "UPDATE `evenement` SET nomEv=?,dateDEv=?,dateFEv=?,localisation=?,ImageEv=? WHERE idEv=?";
       
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, evenement.getNomEv());
            pst.setString(2, evenement.getDateDEv());
             pst.setString(3, evenement.getDateFEv());
              pst.setString(4, evenement.getLocalisation());
           pst.setString(5, evenement.getImage());
            pst.setInt(6, evenement.getIdEv());
            pst.executeUpdate();
            System.out.println("participants number of event " + evenement.getNomEv() + " is updated successfully");

    }

    

   /* @Override
   public void deletOne(Evenement evenement) throws SQLException {
   
     String req = "DELETE FROM `evenement` WHERE idEv=?";
     
        try {
               PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1, evenement.getIdEv());
            pst.executeUpdate();
            System.out.println("event with idEv="+evenement.getIdEv()+" is deleted successfully");
        } catch (SQLException ex) {
            System.out.println("error in delete event " + ex.getMessage());
        }
     
    }*/
    
    
    public void supprimerEvent(Evenement e)throws SQLException{
    
    String req = "delete from evenement where idEv = ?";
        ServicePlanning sp = new ServicePlanning();
        sp.deletall(e.getIdEv());
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getIdEv());
        ps.executeUpdate();
        System.out.println("event with id= " + e.getIdEv() + "  is deleted successfully");
    }
    
    
    
    
    
  public void deletOne(int idEv)  {
        String req = "delete from `evenement` where idEv=?";
        try {
           PreparedStatement pst =cnx.prepareStatement(req);
            pst.setInt(1, idEv);
            pst.executeUpdate();
            System.out.println("event with id="+idEv+" is deleted successfully");
        } catch (SQLException ex) {
            System.out.println("error in delete event " + ex.getMessage());
        }
    }
      
   

    
   public List<Evenement> selectAll() throws SQLException {
          List<Evenement> temp = new ArrayList<>();
try {
     String req = "SELECT * FROM `Evenement`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Evenement Cat = new Evenement(rs.getInt(1), rs.getString("nomEv"), rs.getString("localisation"),rs.getString("dateDEv") , rs.getString(4), rs.getString("ImageEv"));

            /*  Cat.setIdEv(rs.getInt(1));
            Cat.setNomEv(rs.getString("nomEv"));
            Cat.setDateDEv(rs.getString("dateDEv"));
            Cat.setDateFEv(rs.getString(4));
             Cat.setLocalisation(rs.getString("localisation"));
           Cat.setLocalisation(rs.getString("ImageEv"));*/
           

            temp.add(Cat);

        }

}catch(SQLException ex){
    System.out.println(ex.getMessage());
}
       


        return temp;
    
    
   }
   public ObservableList<Evenement> FetchEvents()throws SQLException{
       ObservableList<Evenement> events = FXCollections.observableArrayList();
        String req = "SELECT * FROM `evenement`";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Evenement Cat = new Evenement();

            Cat.setIdEv(rs.getInt(1));
            Cat.setNomEv(rs.getString("nomEv"));
            Cat.setDateDEv(rs.getString("dateDEv"));
            Cat.setDateFEv(rs.getString(4));
             Cat.setImage(rs.getString("ImageEv"));
               Cat.setLocalisation(rs.getString("localisation"));
           

            events.add(Cat);

        }


        return events;
    
    
    }
   
   
   
    
    
    public Evenement SelectOneEvent(int idEv){
        Evenement event = new Evenement();
        String req = "SELECT * FROM `evenement` where idEv ="+idEv;
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                event = new Evenement(rs.getInt("idEv"), rs.getString("nomEv"), rs.getString("dateDEv"),rs.getString("dateFEv"),rs.getString("localisation"),rs.getString("ImageEv"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent .class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }
    
   public ObservableList<Evenement> searchByEvenement(String nomEv ) throws SQLException{
        String qry="SELECT * FROM evenement where nomEv LIKE '%"+nomEv+"%'" ;
                  System.out.println(qry);
            cnx = MaConnexion.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
        ObservableList<Evenement>  list = FXCollections.observableArrayList()  ; 
        while(rs.next()){
        Evenement a = new Evenement( rs.getString("nomEv"), rs.getString("dateDEv"),rs.getString("dateFEv"),rs.getString("localisation"),rs.getString("ImageEv")); 
        list.add(a) ;
        
        }
         

        return list ;
    } 
    
    public ObservableList<Evenement> getAllTriNom() {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String req = "Select * from evenement  order by nomEv";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
               Evenement a = new Evenement( rs.getString("nomEv"), rs.getString("dateDEv"),rs.getString("dateFEv"),rs.getString("localisation"),rs.getString("ImageEv")); 
        list.add(a) ;
        
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
    
    
      public Evenement Selectid(String nom){
    Evenement event = new Evenement();
    String req = "SELECT * FROM evenement WHERE nomEv=?";
    
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            event = new Evenement(rs.getInt("idEv"), rs.getString("nomEv"), rs.getString("dateDEv"),rs.getString("dateFEv"),rs.getString("localisation"));
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return event;
}

    
}
