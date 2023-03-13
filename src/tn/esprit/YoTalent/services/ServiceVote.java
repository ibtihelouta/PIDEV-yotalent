/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.YoTalent.entities.Vote;
import tn.esprit.YoTalent.utils.MaConnexion;

/**
 *
 * @author Hamza
 */
public class ServiceVote {
        private Connection cnx;


 public ServiceVote(){
        cnx = MaConnexion.getInstance().getCnx();
    }
 
 public void createOne(Vote vote) throws SQLException {
      String req =   "INSERT INTO `vote`(`idV`,`nbrV` , `dateV` ,`idU` ,`idEV`,`idEST`,`idVid`,`nomVid` )" + "VALUES ('"+vote.getIdV()+"','"+vote.getNbrV()+"','"+vote.getDateV()+"','"+vote.getIdU()+"','"+vote.getIdEV()+"','"+vote.getIdEST()+"','"+vote.getIdV()+"','"+vote.getNomVid()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("User ajouté !");
      
    }
 
 
  public void updateOne(Vote vote) throws SQLException {
   String req = "UPDATE vote SET  `date`=? , `idU`=? , `idVid`=? , `nomVid`=? WHERE idV=" + vote.getIdV();
 
       
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, vote.getDateV());
            pst.setInt(2, vote.getIdV());
            pst.setString(3, vote.getNomVid());
            
           
            pst.executeUpdate();
            System.out.println("Vote with id " + vote.getIdV() + " is updated successfully");
    }

  public void deletOne(Vote vote) throws SQLException {

        try {
            String req = "DELETE FROM vote WHERE vote.`idv` = ?";
            PreparedStatement su = cnx.prepareStatement(req);
            su.setInt(1, vote.getIdV());
            su.executeUpdate();
            System.out.println("vote supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceVote.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

 }