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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.entities.Remboursement;
import tn.esprit.YoTalent.entities.Ticket;
import tn.esprit.YoTalent.utils.MaConnexion;

/**
 *
 * @author USER
 */
public class ServiceRemboursement implements IService<Remboursement> {

     private Connection cnx;

    public ServiceRemboursement(){
        cnx = MaConnexion.getInstance().getCnx();
        
    }

    @Override
    public void createOne(Remboursement remboursement) throws SQLException {
         String a ="false";
         int idu=45;
        String req =   "INSERT INTO `remboursement`(`dc`,`idT`,`idu`) VALUES  ('"+a+"','"+remboursement.getIdT()+"','"+idu+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Remboursement ajouté !");
    }
    
       @Override
    public void updateOne(Remboursement remboursement) throws SQLException {
       String sql = " UPDATE `remboursement` SET `idRem`=?,`dc`=?,`idT`=? WHERE idRem=" + remboursement.getIdRem();
      
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            

            ste.setInt(1, remboursement.getIdRem());

            ste.setString(2,String.valueOf(remboursement.getDateRem()) );
            ste.setInt(3, remboursement.getIdT());
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du remboursement :" + remboursement.getIdRem() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
     @Override
    public void deletOne(Remboursement rem) throws SQLException {
         try {
            String req = "DELETE FROM `remboursement` WHERE remboursement.`idRem` = ?";
           
           
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, rem.getIdRem());
            st.executeUpdate();
            System.out.println("remboursement supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public ObservableList<Remboursement> FetchRemboursements()throws SQLException{
       ObservableList<Remboursement> remboursements= FXCollections.observableArrayList();
        String req = "SELECT * FROM Remboursement where dc = ?";
       
        PreparedStatement ps = cnx.prepareStatement(req);
        String s="false";
        ps.setString(1, s);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Remboursement Cat = new Remboursement();

            Cat.setIdRem(rs.getInt(1));
            Cat.setIdT(rs.getInt("idT"));
            Cat.setDateRem(Boolean.valueOf(rs.getString("idu")));
            
          
           
           

            remboursements.add(Cat);

        }


        return remboursements;
    
    
    }
    
    
     @Override
    public List<Remboursement> selectAll() throws SQLException {
          List<Remboursement> temp = new ArrayList<>();

        String req = "SELECT * FROM `remboursement`";
        
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

          Remboursement   Cat = new Remboursement();

            Cat.setIdRem(rs.getInt(1));
            Cat.setDateRem(Boolean.parseBoolean(rs.getString("dc")));
            Cat.setIdT(rs.getInt("idT"));
             Cat.setIdu(rs.getInt("idu"));

            temp.add(Cat);

        }


        return temp;
    }

    public ObservableList<Remboursement> FetchRembous() throws SQLException {
        

       ObservableList<Remboursement> remboursement = FXCollections.observableArrayList();
        ServiceRemboursement es= new  ServiceRemboursement();
        Ticket tick = new Ticket();
        String req = "SELECT * FROM remboursement";
         PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

           
          Remboursement   Cat = new Remboursement();

            Cat.setIdRem(rs.getInt(1));
            Cat.setDateRem(Boolean.parseBoolean(rs.getString("dc")));
            Cat.setIdT(rs.getInt("idT"));
            Cat.setIdu(rs.getInt("idu"));

            remboursement.add(Cat);
        }
        
return remboursement;
}
    
    
    public ObservableList<Remboursement> searchByRemboursement(int id) throws SQLException{
     String qry = "SELECT * FROM remboursement WHERE idT = ? ";
    cnx = MaConnexion.getInstance().getCnx();
    PreparedStatement stm = cnx.prepareStatement(qry);
    stm.setInt(1,  id );
    ResultSet rs = stm.executeQuery();
    ObservableList<Remboursement> remboursement = FXCollections.observableArrayList();
    while(rs.next()) {
        Remboursement cat = new Remboursement(rs.getInt(1),Boolean.parseBoolean(rs.getString("dc")),rs.getInt("idT"));
        /*cat.setIdRem(rs.getInt(1));
        cat.setDateRem(Boolean.parseBoolean(rs.getString("dc")));
        cat.setIdT(rs.getInt("idT"));*/
        remboursement.add(cat);
    }
    return remboursement;
    }

    public Evenement Selectid(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
    

   
    
    
    
    
    
    
    
    
    
    

    
    
 


