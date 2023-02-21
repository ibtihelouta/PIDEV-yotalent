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
import java.util.ArrayList;
import java.util.List;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Participation;


import tn.esprit.YoTalent.entities.Voyage;
import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.YoTalent.entities.Voyage;
/**
 *
 * @author USER
 */
public class ServiceVoyage implements IService<Voyage> {

     private Connection cnx;

    public ServiceVoyage(){
        cnx = MaConnexion.getInstance().getCnx();
        
    }

    @Override
    public void createOne( Voyage voyage) throws SQLException {
        
      String req =   "INSERT INTO `voyage`(`dateDVoy`,`dateRVoy`,`destination`,`idC`)" + "VALUES ('"+voyage.getDateDVoy()+"','"+voyage.getDateRVoy()+"','"+voyage.getDestination()+"','"+voyage.getIdC()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Voyage ajouté !");
    }

    @Override
    public void updateOne(Voyage voyage,int id) throws SQLException {
       String sql = "UPDATE voyage SET `dateDVoy`=?,`dateRVoy`=? , `destination`=? , `idC`=? WHERE idVoy=" + id;
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            
               ste.setString(1, voyage.getDateDVoy());
               ste.setString(2, voyage.getDateRVoy());
               ste.setString(3, voyage.getDestination());
            ste.setInt(4, voyage.getIdC());

           
          

            

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du voyage :" + voyage.getIdVoy() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void deletOne(Voyage V) throws SQLException {
    }

    @Override
    public List<Voyage> selectAll() throws SQLException {
          List<Voyage> temp = new ArrayList<>();

        String req = "SELECT * FROM `Voyage`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Voyage Voy = new Voyage();

            Voy.setIdVoy((rs.getInt(1)));        
            Voy.setDateDVoy(rs.getString("DateDVoy"));
             Voy.setDateRVoy(rs.getString("DateRVoy"));
               Voy.setDestination(rs.getString("Destination"));
               Voy.setIdC(rs.getInt(2));
            
         
            temp.add(Voy);

        }


        return temp;
    }
    

  public void DeleteVoyage ( int id) {

        try {
            String req = "DELETE FROM Voyage WHERE  idVoy=" + id+"";
            PreparedStatement ste = cnx.prepareStatement(req);
//            ste.setInt(1, v.getIdVoy());
            ste.executeUpdate();
            System.out.println("Voyage supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    
    
    
}
