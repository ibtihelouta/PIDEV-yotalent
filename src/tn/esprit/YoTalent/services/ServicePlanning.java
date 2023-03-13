/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import java.awt.Font;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.utils.MaConnexion;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.text.Document;
import static javax.swing.text.StyleConstants.FontFamily;

/**
 *
 * @author USER
 */
public class ServicePlanning  {
    
     private Connection cnx;
     
 
    public ServicePlanning(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    
    public void createOne(Planning planning) throws SQLException {
        
        ServiceEvent es= new  ServiceEvent();
        Evenement event = new Evenement();
        String datePL= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        
        String req = "INSERT INTO `planning`( `hour`, `nomActivite`, `datePL`, `idEv`)" +"VALUES ('" +planning.getHour()+"','"+planning.getNomActivite()+"','"+planning.getDatePL()+"','"+planning.getIdEv()+"')";
        
            Evenement tempEvent = es.SelectOneEvent(planning.getIdEv());
            System.out.println("before"+tempEvent);
            //es.updateOne(tempEvent);
            int new_id=tempEvent.getIdEv();
            planning.setEvenement(tempEvent);
            System.out.println("after"+tempEvent);
           
        
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
       System.out.println("planning with id event = "+planning.getIdEv()+" is added successfully");
       
    }

   
   
    
    public Planning FetchOnePlan(int idP){
       Planning  p=new Planning();
        String requete = "SELECT * FROM `planning` WHERE idP="+idP;
        
        try {
             Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(requete);
            
            while(rs.next()){           
                 
               
               p = new Planning(rs.getInt("idP"),rs.getString("hour"),rs.getString("nomActivite"),rs.getString("datePL"),rs.getInt("idEv"));
                  

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
  public List<Planning> searchByevent(int id ) throws SQLException{
        String qry="SELECT * FROM planning where idEv="+id+"" ;
                  System.out.println(qry);
            cnx = MaConnexion.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            
            ResultSet rs = stm.executeQuery(qry);
         
        List<Planning>  list = new ArrayList<>();
        while(rs.next()){
        Planning a = new Planning(rs.getString("hour"),rs.getString("nomActivite"),rs.getString("datePL")); 
        list.add(a) ;
        
        }
         

        return list ;
    } 
    
    
    public void updateOne(Planning planning,int id) throws SQLException {
          
        
        String req =  " UPDATE `planning` SET hour=?,nomActivite=?,datePL=? WHERE idP="+id+"";
     
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, planning.getHour());
            pst.setString(2, planning.getNomActivite());
             pst.setString(3, planning.getDatePL());
             // pst.setString(4, planning.getIdEv());
           
           // pst.setInt(5, planning.getIdP());
            pst.executeUpdate();
            System.out.println("nom activite of event " + planning.getNomActivite() + " is updated successfully");

    }
    
    
    
     

    
    
    
    
    public void deletOne( int idP) {
       
      ServiceEvent es= new  ServiceEvent();
        ServicePlanning sp= new ServicePlanning();
        Planning p=sp.FetchOnePlan(idP);
        
        String requete = "DELETE FROM `planning` WHERE idP="+idP;
        
       try {
             Evenement tempEvent = es.SelectOneEvent(p.getIdEv());
            System.out.println("before"+tempEvent);
            
            es.updateOne(tempEvent);
            
         System.out.println("after"+tempEvent);
          PreparedStatement   pst = cnx.prepareStatement(requete); 
          pst.executeUpdate();
            System.out.println("planning with idP="+idP+" is deleted successfully");
        
        
       }catch (SQLException ex) {
            System.out.println("error in delete planning " + ex.getMessage());
        }
        
             
        
    }
        
    public void deletall( int idP) {
       
      
        
        String requete = "DELETE FROM `planning` WHERE idEv="+idP;
        
       try {
            
          PreparedStatement   pst = cnx.prepareStatement(requete); 
          pst.executeUpdate();
            System.out.println("planning with idP="+idP+" is deleted successfully");
        
        
       }catch (SQLException ex) {
            System.out.println("error in delete planning " + ex.getMessage());
        }
        
             
        
    }
    
     public void supprimerEvent(Planning e)throws SQLException{
    
    String req = "delete from planning where idP = ?";
    
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getIdP());
        ps.executeUpdate();
        System.out.println("event with id= " + e.getIdP() + "  is deleted successfully");
    }
    
    
    
    
     public ObservableList<Planning> FetchPlanning()throws SQLException{
       ObservableList<Planning> planning = FXCollections.observableArrayList();
        ServiceEvent es= new  ServiceEvent();
        Evenement event = new Evenement();
        String req = "SELECT * FROM `planning`";
         PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Planning Cat = new Planning();

            Cat.setIdP(rs.getInt(1));
            Cat.setHour(rs.getString("hour"));
            Cat.setNomActivite(rs.getString(3));
            Cat.setDatePL(rs.getString("datePL"));
            Evenement tempEvent= es.SelectOneEvent(rs.getInt("idEv"));
           

            planning.add(Cat);

        }


        return planning;
        
    }
    
    
    
    

    
    public List<Planning> selectAll() throws SQLException {
          
         List<Planning> temp = new ArrayList<>();
         ServiceEvent es= new  ServiceEvent();
        Evenement event = new Evenement();
String req = "SELECT * FROM  `planning`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Planning Cat = new Planning();

            Cat.setIdP(rs.getInt(1));
            Cat.setHour(rs.getString("hour"));
            Cat.setNomActivite(rs.getString(3));
            Cat.setDatePL(rs.getString("datePL"));
            Evenement tempEvent= es.SelectOneEvent(rs.getInt("idEv"));
           

            temp.add(Cat);

        }


        return temp;
        
        
        
    }

   /* public void updateOne(Planning up) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    
    
     public ObservableList<Planning> searchByPlanning(String nomActivite ) throws SQLException{
        String qry="SELECT * FROM planning where nomActivite LIKE '%"+nomActivite+"%'" ;
                  System.out.println(qry);
            cnx = MaConnexion.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
        ObservableList<Planning>  list = FXCollections.observableArrayList()  ; 
        while(rs.next()){
        Planning a = new Planning(rs.getString("hour"),rs.getString("nomActivite"),rs.getString("datePL")); 
        list.add(a) ;
        
        }
         

        return list ;
    } 
     
      public ObservableList<Planning> getAllTriNom() {
        ObservableList<Planning> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String req = "Select * from planning  order by nomActivite";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
                Planning a = new Planning(rs.getString("hour"),rs.getString("nomActivite"),rs.getString("datePL")); 
        list.add(a) ;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
     
     
     
      
      
     
          }
     
     
     
     
     
    
   
        

    
    
        
    
        
        
        
        
        
    

