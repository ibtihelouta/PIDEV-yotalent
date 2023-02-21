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
        String dateRem= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateRem.now());
        String req =   "INSERT INTO `remboursement`(`idRem`, `dateRem`, `idT`) VALUES  ('"+remboursement.getIdRem()+"','"+remboursement.getDateRem()+"','"+remboursement.getIdT()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Remboursement ajouté !");
    }
    
       @Override
    public void updateOne(Remboursement remboursement) throws SQLException {
       String sql = " UPDATE `remboursement` SET `idRem`=?,`dateRem`=?,`idT`=? WHERE idRem=" + remboursement.getIdRem();
      
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            

            ste.setInt(1, remboursement.getIdRem());

            ste.setString(2, remboursement.getDateRem());
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
    
    
     @Override
    public List<Remboursement> selectAll() throws SQLException {
          List<Remboursement> temp = new ArrayList<>();

        String req = "SELECT * FROM `remboursement`";
        
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

          Remboursement   Cat = new Remboursement();

            Cat.setIdRem(rs.getInt(1));
            Cat.setDateRem(rs.getString("dateRem"));
            Cat.setIdT(rs.getInt("idT"));
           

            temp.add(Cat);

        }


        return temp;
    }


}

   
    
    
    
    
    
    
    
    
    
    

    
    
 


