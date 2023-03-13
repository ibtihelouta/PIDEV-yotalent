/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;


import tn.esprit.YoTalent.entities.EspaceTalent;

import tn.esprit.YoTalent.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.YoTalent.entities.Categorie;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.entities.Participation;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.entities.Video;



/**
 *
 * @author USER
 */ 
public class ServiceET  {
    private Connection cnx;
  //ServiceVideo sv = new ServiceVideo();
          //  ServiceCategorie sc = new ServiceCategorie();
             //ServiceContrat c = new ServiceContrat();
    public ServiceET(){
        cnx = MaConnexion.getInstance().getCnx();
    }

   
    public void createOne(EspaceTalent e) throws SQLException {
      String req;
       // req = "INSERT INTO espacetalent (username,image,nbVotes,idU, idCat) VALUES ('" + e.getUsername() + "','" + e.getImage() + "', '" + e.getNbVotes() + "', '" + e.getIdU().getIdU() + "', '" + e.getIdCat().getIdCat() + "')";
      //String req =   "INSERT INTO participation (nbRemb, idEV, idU, idT) VALUES ('" + t.getNbRemb() + "', '" + t.getIdEv()+ "', '" + t.getIdEv() + "', '" + t.getIdT() + "')";
       // Statement st = cnx.createStatement();
        //st.executeUpdate(req);
       // System.out.println("espacetalent ajouté !");
      
    }
    
    ////////// ana 
    
    

//    public void updateOne(EspaceTalent espacetalent) throws SQLException {
//   String req = "UPDATE espacetalent SET `idEST`=?,`username`=?, `image`=?,`nbVotes`=? ,`idU`=? , `idCat`=?   WHERE idEST=" + espacetalent.getIdEST();
//
//
//       
//            PreparedStatement pst =cnx.prepareStatement(req);
//
//            pst.setInt(1, espacetalent.getIdEST());
//            pst.setString(2, espacetalent.getUsername());
//             pst.setString(3, espacetalent.getImage());
//              pst.setInt(5, espacetalent.getNbVotes());
//             pst.setInt(5, espacetalent.getIdU().getIdU());
//               pst.setInt(6, espacetalent.getIdCat().getIdCat());
//           
//            pst.executeUpdate();
//            System.out.println("espacetalent " + espacetalent.getUsername() + " is updated successfully");
//    }
//
//    @Override
//    public void deletOne(EspaceTalent espacetalent) throws SQLException {
//try {
//            String req = "DELETE FROM espacetalent WHERE espacetalent.`idEST` = ?";
//            PreparedStatement ste = cnx.prepareStatement(req);
//            ste.setInt(1, espacetalent.getIdEST());
//            ste.executeUpdate();
//            System.out.println("espace talent  supprimé");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceET.class.getName()).log(Level.SEVERE, null, ex);
//        }    }
//
//    
//    private User getUserById(int idU) throws SQLException {
//    String req = "SELECT * FROM user WHERE idU = ?";
//    PreparedStatement ps = cnx.prepareStatement(req);
//    ps.setInt(1, idU);
//    ResultSet rs = ps.executeQuery();
//    if (rs.next()) {
//        int id = rs.getInt("idU");
//        String nomU = rs.getString("nomU");
//       
//        String email = rs.getString("email");
//        // Créer un nouvel objet User avec les données récupérées
//        User user = new User(id,nomU ,email);
//        return user;
//    }
//    return null;
//}
//public List<Integer> affichagecombo()  {
//               List<Integer> arr=new ArrayList<>();
//        try {
//            
//            
//            
//            PreparedStatement pt = cnx.prepareStatement("select idEST from espacetalent");
//            ResultSet rs = pt.executeQuery();
//            while (rs.next()) {
//                int nom=rs.getInt("idEST");
//                
//                arr.add(nom);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceET.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return arr;
//    }
//
//    @Override
//    public List<EspaceTalent> selectAll() throws SQLException {
//          List<EspaceTalent> espacetalents = new ArrayList<>();
//    String req = "SELECT * FROM espacetalent";
//    Statement st = cnx.createStatement();
//    ResultSet rs = st.executeQuery(req);
//    while (rs.next()) {
//        EspaceTalent espacetalent = new EspaceTalent();
//        espacetalent.setIdEST(rs.getInt("idEST"));
//        espacetalent.setUsername(rs.getString("username"));
//        espacetalent.setImage(rs.getString("image"));
//         espacetalent.setIdEST(rs.getInt("nbVotes"));
//       User user = new User();
//      user.setIdU(rs.getInt("idU"));
//      espacetalent.setIdU(user);
//      
//
//      
//      
//       Categorie categorie = new Categorie();
//      categorie.setIdCat(rs.getInt("idCat"));
//      espacetalent.setIdCat(categorie);
//    
//       
//        espacetalents.add(espacetalent);
//    }
//    return espacetalents;
//
//}
//    
//     public List<EspaceTalent> selectAllC(int idCat) throws SQLException {
//          List<EspaceTalent> espacetalents = new ArrayList<>();
//  String req = "SELECT * FROM espacetalent WHERE idCat = " + idCat;    // and sup(nbvote de note);  // max (espacetalents.getNbVotes)
//Statement st = cnx.createStatement();
//    ResultSet rs = st.executeQuery(req);
//    while (rs.next()) {
//        EspaceTalent espacetalent = new EspaceTalent();
//        espacetalent.setIdEST(rs.getInt("idEST"));
//        espacetalent.setUsername(rs.getString("username"));
//        espacetalent.setImage(rs.getString("image"));
//        
//        espacetalent.setIdEST(rs.getInt("nbVotes"));
//       User user = new User();
//      user.setIdU(rs.getInt("idU"));
//      espacetalent.setIdU(user);
//      
//
//      
//      
//       Categorie categorie = new Categorie();
//      categorie.setIdCat(rs.getInt("idCat"));
//      espacetalent.setIdCat(categorie);
//    
//       
//        espacetalents.add(espacetalent);
//    }
//    return espacetalents;
//    
//
//}
//     
//     
//    
    public EspaceTalent SelectOneET(int idEST){
        EspaceTalent espacetalent = new EspaceTalent();
        String req = "SELECT * FROM `espacetalent` where idEST ="+idEST;
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
        espacetalent.setIdEST(rs.getInt("idEST"));
        espacetalent.setUsername(rs.getString("username"));
        espacetalent.setImage(rs.getString("image"));
        
        espacetalent.setIdEST(rs.getInt("nbVotes"));
       User user = new User();
//      user.setIdU(rs.getInt("idU"));
//      espacetalent.setIdU(user);
//      
//
//      
//      
//       Categorie categorie = new Categorie();
//      categorie.setIdCat(rs.getInt("idCat"));
//      espacetalent.setIdCat(categorie);
//    
       
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent .class.getName()).log(Level.SEVERE, null, ex);
        }
        return espacetalent ;
    }
    
     

//
//      public ObservableList<EspaceTalent> FetchEST() throws SQLException {
//    ObservableList<EspaceTalent> espaceTalents = FXCollections.observableArrayList();
//    String req = "SELECT * FROM espacetalent";
//    PreparedStatement pst = cnx.prepareStatement(req);
//    ResultSet rs = pst.executeQuery();
//    while (rs.next()) {
//        EspaceTalent espacetalent = new EspaceTalent();
//        espacetalent.setIdEST(rs.getInt("idEST"));
//       espacetalent.setUsername(rs.getString("username"));
//        espacetalent.setImage(rs.getString("image"));
//        espacetalent.setIdEST(rs.getInt("nbVotes"));
//        espacetalent.setIdU(new User(rs.getInt("idU"))); // on crée un objet User avec l'identifiant correspondant
//        espacetalent.setIdCat(sc.selectOne(rs.getInt("idCat"))); // on récupère l'objet Categorie correspondant
//        espaceTalents.add(espacetalent);
//    }
//    return espaceTalents;
//}
//  
//      
//    public ObservableList<EspaceTalent> FetchESTUser(User idU) throws SQLException {
//    ObservableList<EspaceTalent> espaceTalents = FXCollections.observableArrayList();
//    String req = "SELECT * FROM espacetalent where idU LIKE '%"+idU+"%' ";
//    PreparedStatement pst = cnx.prepareStatement(req);
//    ResultSet rs = pst.executeQuery();
//    while (rs.next()) {
//        EspaceTalent espacetalent = new EspaceTalent();
//        espacetalent.setIdEST(rs.getInt("idEST"));
//       espacetalent.setUsername(rs.getString("username"));
//        espacetalent.setImage(rs.getString("image"));
//        
//        espacetalent.setIdEST(rs.getInt("nbVotes"));
//        espacetalent.setIdU(new User(rs.getInt("idU"))); // on crée un objet User avec l'identifiant correspondant
//        espacetalent.setIdCat(sc.selectOne(rs.getInt("idCat"))); // on récupère l'objet Categorie correspondant
//        espaceTalents.add(espacetalent);
//    }
//    return espaceTalents;
//}
//    
//    
//    public ObservableList<EspaceTalent> searchByEST(String username) throws SQLException{
//        String qry="SELECT * FROM espacetalent where username LIKE '%"+username+"%'" ;
//                  System.out.println(qry);
//           
//            Statement stm = cnx.createStatement();
//            ResultSet rs = stm.executeQuery(qry);
//              
//        ObservableList<EspaceTalent>  list = FXCollections.observableArrayList()  ; 
//        while(rs.next()){
//        EspaceTalent espacetalent = new EspaceTalent(rs.getString(2),rs.getString(3)); 
//                espacetalent.setIdEST(rs.getInt("nbVotes"));
//
//        User user = new User();
//      user.setIdU(rs.getInt("idU"));
//      espacetalent.setIdU(user);
//      
//      
//      
//      
//       Categorie categorie = new Categorie();
//      categorie.setIdCat(rs.getInt("idCat"));
//      espacetalent.setIdCat(categorie);
//      
//     
//      
//   
//        list.add(espacetalent) ;
//        
//        }
//         
//
//        return list ;
//    }
//    
//    public ObservableList<EspaceTalent> getAllTriUser() {
//        ObservableList<EspaceTalent> list = FXCollections.observableArrayList();
//        try {
//         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
//                String req = "Select * from espacetalent  order by username";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//
//            while (rs.next()) {
//            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
//               EspaceTalent espacetalent = new EspaceTalent(rs.getString(2),rs.getString(3)); 
//                       espacetalent.setIdEST(rs.getInt("nbVotes"));
//
//        User user = new User();
//      user.setIdU(rs.getInt("idU"));
//      espacetalent.setIdU(user);
//      
//      
//  
//      
//       Categorie categorie = new Categorie();
//      categorie.setIdCat(rs.getInt("idCat"));
//      espacetalent.setIdCat(categorie);
//      
//    
//   
//        list.add(espacetalent) ;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }
//
//public List<EspaceTalent> fetchESTByUserAndCat(User user, Categorie categorie) throws SQLException {
//    List<EspaceTalent> espaceTalents = new ArrayList<>();
//    String req = "SELECT * FROM espacetalent WHERE idU = ? AND idCat = ?";
//    PreparedStatement pst = cnx.prepareStatement(req);
//    pst.setInt(1, user.getIdU());
//    pst.setInt(2, categorie.getIdCat());
//    ResultSet rs = pst.executeQuery();
//    while (rs.next()) {
//        EspaceTalent espacetalent = new EspaceTalent();
//        espacetalent.setIdEST(rs.getInt("idEST"));
//        espacetalent.setUsername(rs.getString("username"));
//        espacetalent.setImage(rs.getString("image"));
//                espacetalent.setIdEST(rs.getInt("nbVotes"));
//
//        espacetalent.setIdU(user);
//        espacetalent.setIdCat(categorie);
//        espaceTalents.add(espacetalent);
//    }
//    return espaceTalents;
//}
//
//public Video searchByid(int id) throws SQLException {
//    String req = "SELECT * FROM `Video` WHERE idEST = ?";
//    PreparedStatement ps = cnx.prepareStatement(req);
//    ps.setInt(1, id);
//    ResultSet rs = ps.executeQuery();
//    Video vid = null;
//
//    if (rs.next()) {
//        vid = new Video();
//        vid.setIdVid(rs.getInt("idVid"));
//        vid.setNomVid(rs.getString("nomVid"));
//        vid.setUrl(rs.getString("url"));
//        
//        EspaceTalent esp = new EspaceTalent();
//        esp.setIdEST(rs.getInt("idEST"));
//        vid.setIdEST(esp);
//    }
//
//    return vid;
//}
//
//public Video getVideoById(int videoId) throws SQLException {
//   
//    String query = "SELECT * FROM video WHERE idVid = ?";
//    PreparedStatement stmt = cnx.prepareStatement(query);
//    stmt.setInt(1, videoId);
//    ResultSet rs = stmt.executeQuery();
//
//    if (rs.next()) {
//        Video video = new Video();
//        video.setIdVid(rs.getInt("idVid"));
//        video.setNomVid(rs.getString("nomVid"));
//        video.setUrl(rs.getString("url"));
//        // récupérer l'EspaceTalent associé à la vidéo
//        EspaceTalent et = new EspaceTalent();
//        et.setIdEST(rs.getInt("idEST"));
//        video.setIdEST(et);
//        rs.close();
//        stmt.close();
//       
//        return video;
//    } else {
//        rs.close();
//        stmt.close();
//      
//        return null;
//    }
//}
    }
    



    

    
    
    

    
    
    
    
    

    