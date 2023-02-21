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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author USER
 */
public class ServiceContrat implements IServicecontrat<Contrat> {

     private Connection cnx;

    public ServiceContrat(){
        cnx = MaConnexion.getInstance().getCnx();
        
    }

    @Override
    public void createOne(Contrat t) throws SQLException {
        String req =   "INSERT INTO contrat( `nomC`, `DateDC`, `DateFC`) " + "VALUES ('"+t.getNomC()+"','"+t.getDateDC()+"','"+t.getDateFC()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Contrat ajouté !");
    }

    
    public void updateOne(Contrat contrat,int id) throws SQLException {
      
       String req =  "UPDATE contrat SET nomC=?,dateDC=?,dateFC=? WHERE idC=?" + id;
       
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setInt(1, contrat.getIdC());
            pst.setString(2, contrat.getNomC());
             pst.setString(3, contrat.getDateDC());
              pst.setString(4, contrat.getDateFC());
           
            
            pst.executeUpdate();
            System.out.println("participants number of event " + contrat.getNomC() + " is updated successfully");
    }

    @Override
    public void deletOne(Contrat contrat) throws SQLException {
try {
            String req = "DELETE FROM contrat WHERE contrat.`idC` = ?";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, contrat.getIdC());
            ste.executeUpdate();
            System.out.println("contrat supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceVideo.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Contrat> selectAll() throws SQLException {
           List<Contrat> temp = new ArrayList<>();

        String req = "SELECT * FROM `Contrat`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Contrat Cat = new Contrat();

            Cat.setIdC(rs.getInt(1));
            Cat.setNomC(rs.getString("nomC"));
            Cat.setDateDC(rs.getString("dateDC"));
           Cat.setDateFC(rs.getString("dateFC"));

            temp.add(Cat);

        }


        return temp;
    }

   
    
    
    public ObservableList<Contrat> Fetchc()throws SQLException{
       ObservableList<Contrat> contrat = FXCollections.observableArrayList();
        String req = "SELECT * FROM contrat";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Contrat Cat = new Contrat();

            Cat.setIdC(rs.getInt(1));
            Cat.setNomC(rs.getString("nomC"));
            Cat.setDateDC(rs.getString("dateDC"));
            Cat.setDateFC(rs.getString("dateFC"));
            
           

            contrat.add(Cat);

        }


        return contrat;
    
    
    }
    }
   
    
    