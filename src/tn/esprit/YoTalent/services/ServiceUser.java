/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;


import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;



/**
 *
 * @author USER
 */
public class ServiceUser implements IService<User> {
    private Connection cnx;

    public ServiceUser(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(User user) throws SQLException {
      String req =   "INSERT INTO `user`(`nom`,`email` , `motpass` ,`role` ,`image` )" + "VALUES ('"+user.getNom()+"','"+user.getEmail()+"','"+user.getMotpass()+"','"+user.getRole()+"','"+user.getImage()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("User ajouté !");
      
    }
    

    @Override
    public void updateOne(User user) throws SQLException {
   String req = "UPDATE user SET  `nom`=? , `email`=? , `motpass`=? , `role`=? , `image`=? WHERE id=" + user.getId();
 
       
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, user.getNom());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getMotpass());
            pst.setString(4, user.getRole());
            pst.setString(5, user.getImage());
            
           
            pst.executeUpdate();
            System.out.println("User " + user.getNom() + " is updated successfully");
    }

    @Override
    public void deletOne(User user) throws SQLException {

        try {
            String req = "DELETE FROM user WHERE user.`id` = ?";
            PreparedStatement su = cnx.prepareStatement(req);
            su.setInt(1, user.getId());
            su.executeUpdate();
            System.out.println("user supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    


     @Override
    public List<User> selectAll() throws SQLException {
              List<User> temp = new ArrayList<>();

        String req = "SELECT * FROM `User`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            User u1 = new User();

            u1.setId(rs.getInt(1));
            u1.setNom(rs.getString("nom"));
            u1.setEmail(rs.getString("email"));
            u1.setRole(rs.getString("role"));
            u1.setImage(rs.getString("image"));
            // u1.setImageV(rs.getString("image"));
           

            temp.add(u1);

        }


        return temp;
    }
    
      public User Login(String username,String Password) {
          User a = null;
         String requete = " select* from user  where email='"+username+"' and  motpass='"+Password+"' " ;
         System.out.println(requete);
        try {
                   PreparedStatement ps = cnx.prepareStatement(requete);
                   ResultSet res = ps.executeQuery() ;

            if (res.next())
            {
                a=new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
    }

      public User GetByEmail(String email) {
          User a = null;
         String requete = " select* from user  where email='"+email+"' " ;
         System.out.println(requete);
        try {
                   PreparedStatement ps = cnx.prepareStatement(requete);
                   ResultSet res = ps.executeQuery() ;

            if (res.next())
            {
                a=new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
    }

    }
    
    
    
