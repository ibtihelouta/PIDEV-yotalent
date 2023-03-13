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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.YoTalent.entities.EspaceTalent;
import tn.esprit.YoTalent.entities.Participation;



/**
 *
 * @author USER
 */
public class ServiceContrat implements IService <Contrat> {
private Connection cnx;
  //ServiceVideo sv = new ServiceVideo();
          //  ServiceCategorie sc = new ServiceCategorie();
//             ServiceContrat c = new ServiceContrat();
    public ServiceContrat(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(Contrat t) throws SQLException {
         System.out.println(t);
        String DateDC= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
            String DateFC= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
            //NA9sa
        String req =   "INSERT INTO contrat( `nomC`, `DateDC`, `DateFC`) " + "VALUES ('"+t.getNomC()+"','"+t.getDateDC()+"','" +t.getDateFC()+ "')";
        System.out.println(req);
        Statement st = cnx.createStatement();
        
        st.executeUpdate(req);
        System.out.println("Contrat ajouté !");
    }

      @Override
    public void updateOne(Contrat contrat) throws SQLException {
       String DateDC= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
       String DateFC= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        String sql = "UPDATE contrat SET `nomC`=? , `DateDC`=? , `DateFC`=?,`idEST`=1 WHERE `idC`= ? ";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            
               
            ste.setString(1, contrat.getNomC());
            ste.setString(2, contrat.getDateDC());
            ste.setString(3, contrat.getDateFC());
            ste.setInt(4, contrat.getIdC());
         //  ste.setInt(5, contrat.getIdEST().getIdEST());
            

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceContrat.class.getName()).log(Level.SEVERE, null, ex);
        }
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
          List<Contrat> contrat = new ArrayList<>();
    String req = "SELECT * FROM Contrat JOIN EspaceTalent ON Contrat.idEST = EspaceTalent.idEST";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while (rs.next()) {
       Contrat t = new Contrat();
        t.setIdC(rs.getInt("idC"));
        t.setNomC(rs.getString("nomC"));
        t.setDateDC(rs.getString("DateDC"));
         t.setDateFC(rs.getString("DateFC"));
      
    EspaceTalent  ppp = new EspaceTalent(rs.getInt(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11),rs.getInt(12));
      
       System.out.println(ppp);
            t.setIdEST(ppp);
     
      
       
        contrat.add(t);
    }
    return contrat;
 }

   
    
   public ObservableList<Contrat> Fetchc()throws SQLException{
       ObservableList<Contrat> contrat = FXCollections.observableArrayList();
        String req = "SELECT * FROM contrat";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            ServiceET pp = new ServiceET();
            Contrat Cat = new Contrat();

            Cat.setIdC(rs.getInt(1));
            Cat.setNomC(rs.getString("nomC"));
            Cat.setDateDC(rs.getString("dateDC"));
            Cat.setDateFC(rs.getString("dateFC"));
            Cat.setIdEST(pp.SelectOneET(rs.getInt("idEST")));
           

            contrat.add(Cat);

        }


        return contrat;
    
    }
   public Contrat selectOne(int idC) throws SQLException {
    Contrat contrat = null;
    String req = "SELECT * FROM contrat WHERE idC=?";
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.setInt(1, idC);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
       contrat= new Contrat();
       contrat.setIdC(rs.getInt("idC"));
        contrat.setNomC(rs.getString("nomC"));
        contrat.setDateDC(rs.getString("DateDC"));
        contrat.setDateFC(rs.getString("DateFC"));
         contrat.setIdEST(new EspaceTalent(rs.getInt("idEST")));
        
    }
    return contrat;
}  
   //////////////////// metier/////////
   
     public ObservableList<Contrat> searchBycontrat(String nomC ) throws SQLException{
        String qry="SELECT * FROM Contrat where nomC LIKE '%"+nomC+"%'" ;
                  System.out.println(qry);
            cnx = MaConnexion.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
        ObservableList<Contrat>  list = FXCollections.observableArrayList()  ; 
        while(rs.next()){
        Contrat a = new Contrat(rs.getString("DateDC"),rs.getString("NomC"),rs.getString("DateFC")); 
        list.add(a) ;
        
        }
         

        return list ;
    } 
   public ObservableList<Contrat> getAllTriNom() {
        ObservableList<Contrat> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String req = "Select * from Contrat order by NomC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
                Contrat a = new Contrat(rs.getString("NomC"),rs.getString("DateDC"),rs.getString("DateFC")); 
        list.add(a) ;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }///// lahna yofa////
   
    }


   
    
    