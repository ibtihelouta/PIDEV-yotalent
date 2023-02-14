/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.YoTalent.entities.Categorie;

import tn.esprit.YoTalent.entities.Voyage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author USER
 */
public class ServiceContrat implements IService<Contrat> {

     private Connection cnx;

    public ServiceContrat(){
        cnx = MaConnexion.getInstance().getCnx();
        
    }

    @Override
    public void createOne(Contrat t) throws SQLException {
        String req =   "INSERT INTO contrat(`idC`, `nomC`, `DateDC`, `DateFC`) " + "VALUES ('"+t.getIdC()+"','"+t.getNomC()+"','"+t.getDateDC()+"','"+t.getDateFC()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Contrat ajouté !");
    }

    @Override
    public void updateOne(Contrat t) throws SQLException {
      
        String req =  "UPDATE contrat SET `idC`=?,`nomC`=?,`DateDC`=?,`DateFC`=? WHERE `idC`=?";
       
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setInt(1, t.getIdC());
               pst.setString(2, t.getNomC());
            pst.setString(2, t.getDateDC());
            pst.setString(3, t.getDateFC());
            
           
            pst.setInt(5, t.getIdC());
            pst.executeUpdate();
            System.out.println("le Contrat " + t.getNomC() + " a ete modifier avec successe");
    }

    @Override
    public void deletOne(Contrat t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contrat> selectAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    }