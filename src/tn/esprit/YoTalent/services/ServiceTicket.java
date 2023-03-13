/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Participation;

import tn.esprit.YoTalent.entities.Ticket;
import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.YoTalent.GUI.InterfaceRemboursementController;
import tn.esprit.YoTalent.entities.Remboursement;

/**
 *
 * @author USER
 */
public class ServiceTicket implements IService<Ticket> {

    private Connection cnx;

    public ServiceTicket() {
        cnx = MaConnexion.getInstance().getCnx();

    }

    @Override
    public void createOne(Ticket ticket) throws SQLException {
        String req = "INSERT INTO `ticket`(`prixT`,`idEv`,`nomEv`, `etat`)" + "VALUES ('" + ticket.getPrixT() + "','" + ticket.getIdEv() + "','" + ticket.getNomEv() + "',0)";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

        System.out.println("Ticket ajouté !");

    }
    public void payer(Ticket ticket) throws SQLException {
        String req = "INSERT INTO `ticket`(`prixT`, `idEv`, `nomEv`, `etat`) " +"VALUES ('" + ticket.getPrixT() + "', '" + ticket.getIdEv() + "', '" + ticket.getNomEv() + "', 1)";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

        System.out.println("Ticket ajouté !");

    }

    @Override
    public void updateOne(Ticket ticket) throws SQLException {
        String sql = "UPDATE ticket SET `idT`=?,`PrixT`=?,`IdEv`=? WHERE idT=" + ticket.getIdT();
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);

            ste.setInt(1, ticket.getIdT());

            ste.setInt(2, ticket.getPrixT());
            ste.setInt(3, ticket.getIdEv());
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du ticket :" + ticket.getIdT() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deletOne(Ticket t) throws SQLException {
        try {
            String req = "DELETE FROM `ticket` WHERE ticket.`idT` = ?";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, t.getIdT());
            st.executeUpdate();
            System.out.println("reservation ticket supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Ticket> selectAll() throws SQLException {
        List<Ticket> temp = new ArrayList<>();

        String req = "SELECT * FROM `Ticket`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Ticket Cat = new Ticket();

            Cat.setIdT(rs.getInt(1));
            Cat.setPrixT(rs.getInt("prixT"));
            Cat.setIdT(rs.getInt("idEv"));
            Cat.setNomEv(rs.getString("nomEv"));
            temp.add(Cat);

        }

        return temp;
    }

    public ObservableList<Ticket> searchByTicket(Integer prixT) throws SQLException {
        String qry = "SELECT * FROM ticket where prixT LIKE '%" + prixT + "%'";
        System.out.println(qry);
        cnx = MaConnexion.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(qry);
        ObservableList<Ticket> tickets = FXCollections.observableArrayList();
        while (rs.next()) {
            Ticket Cat = new Ticket();

            Cat.setIdT(rs.getInt(1));
            Cat.setPrixT(rs.getInt("prixT"));
            Cat.setIdEv(rs.getInt("idEv"));
             Cat.setNomEv(rs.getString("nomEv"));
            tickets.add(Cat);

        }

        return tickets;
    }

    public ObservableList<Ticket> FetchTickets() throws SQLException {
        ObservableList<Ticket> tickets = FXCollections.observableArrayList();
        String req = "SELECT * FROM Ticket";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Ticket Cat = new Ticket();

            Cat.setIdT(rs.getInt(1));
            Cat.setPrixT(rs.getInt("prixT"));
            Cat.setIdEv(rs.getInt("idEv"));
             Cat.setNomEv(rs.getString("nomEv"));
            tickets.add(Cat);

        }

        return tickets;

    }
public int prixticket (int idev,int nbT){
     int r =111;
     ServiceEvent se = new ServiceEvent();
   
       java.sql.Connection cnx;
     cnx = MaConnexion.getInstance().getCnx();
          String sql = "SELECT prixT FROM Ticket where  idEv = ?";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
  st.setInt(1, idev);
    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   r = R.getInt(1)*nbT;
   
        System.out.println(r);
    
     
      
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return r;
    
    
}
public int getprixticket (int idev){
     int r =111;
     ServiceEvent se = new ServiceEvent();
   
       java.sql.Connection cnx;
     cnx = MaConnexion.getInstance().getCnx();
          String sql = "SELECT prixT FROM Ticket where  idEv = ?";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
  st.setInt(1, idev);
    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   r = R.getInt(1);
   
        System.out.println(r);
    
     
      
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return r;
    
    
}
public int recentid() throws SQLException{
    
    String sql = "SELECT MAX(idT) FROM ticket";
PreparedStatement statement = cnx.prepareStatement(sql);
ResultSet result = statement.executeQuery();
int maxId = 1;
if (result.next()) {
     maxId = result.getInt(1);
    // Use maxId as the ID for the new ticket for the user
}
    return maxId;
}
    public void modifierTicket(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
