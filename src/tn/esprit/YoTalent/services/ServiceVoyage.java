/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Participation;

import tn.esprit.YoTalent.entities.Voyage;
import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.entities.EspaceTalent;
import tn.esprit.YoTalent.entities.Voyage;

/**
 *
 * @author USER
 */
public class ServiceVoyage implements IService<Voyage> {

    private Connection cnx;
    ServiceContrat c = new ServiceContrat();

    public ServiceVoyage() {
        cnx = MaConnexion.getInstance().getCnx();

    }

    @Override
    public void createOne(Voyage voyage) throws SQLException {
    String dateDVoy= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    String dateRVoy= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        String req = "INSERT INTO voyage(`dateDVoy`,`dateRVoy`,`destination`,`idC`)" + "VALUES ('" + voyage.getDateDVoy() + "','" + voyage.getDateRVoy() + "','" + voyage.getDestination() + "','"+ 1+ "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Voyage ajouté !");
    }

    public void updateOne(Voyage voyage, int id) throws SQLException {
          String dateDVoy= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
          String dateRVoy= DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        String sql = "UPDATE voyage SET `dateDVoy`=?,`dateRVoy`=? , `destination`=? , `idC`=1 WHERE idVoy= ?;";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, voyage.getDateDVoy());
            ste.setString(2, voyage.getDateRVoy());
            ste.setString(3, voyage.getDestination());
           
            ste.setInt(4, id);

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du voyage :" + voyage.getIdVoy() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//
    @Override
    public List<Voyage> selectAll() throws SQLException {
        List<Voyage> temp = new ArrayList<>();
        ServiceContrat ss = new ServiceContrat();

        String req = "SELECT *"
                + "FROM Voyage"
                + " JOIN Contrat ON Voyage.idC = Contrat.idC";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Voyage Voy = new Voyage();

            Voy.setIdVoy((rs.getInt(1)));
            Voy.setDateDVoy(rs.getString("DateDVoy"));
            Voy.setDateRVoy(rs.getString("DateRVoy"));
            Voy.setDestination(rs.getString("Destination"));

                  Contrat contrat = new Contrat(rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),new EspaceTalent(rs.getInt(10)));
              System.out.println(contrat);
           Voy.setIdC(contrat);
            temp.add(Voy);

        }

        return temp;
    }

    public void DeleteVoyage(int id) {

        try {
            String req = "DELETE FROM Voyage WHERE  idVoy=" + id + "";
            PreparedStatement ste = cnx.prepareStatement(req);
//            ste.setInt(1, v.getIdVoy());
            ste.executeUpdate();
            System.out.println("Voyage supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /////////// fnc ilyom asma/////////
    public ObservableList<Voyage> Fetchv()throws SQLException{
       ObservableList<Voyage> voyage = FXCollections.observableArrayList();
        String req = "SELECT * FROM voyage";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            ServiceContrat pp = new ServiceContrat();
            Voyage vo = new Voyage();
             vo.setIdVoy((rs.getInt(1)));
            vo.setDateDVoy(rs.getString("DateDVoy"));
            vo.setDateRVoy(rs.getString("DateRVoy"));
            vo.setDestination(rs.getString("Destination"));
             vo.setIdC(pp.selectOne(rs.getInt("idC")));
//            Cat.setIdC(rs.getInt(1));
//            Cat.setNomC(rs.getString("nomC"));
//            Cat.setDateDC(rs.getString("dateDC"));
//            Cat.setDateFC(rs.getString("dateFC"));
          //  vo.setIdP(pp.selectOne(rs.getInt("idP")));
           

            voyage.add(vo);

        }


        return voyage;
    
    }
    public List<Object[]> CountVolByAeroArr() throws SQLException {
        List<Object[]> CountVolByAeroArr = new ArrayList<>();
        String req = "SELECT aero_arrivee, COUNT(*) as vol_count FROM vol GROUP BY aero_arrivee";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Object[] row = new Object[2];
            row[0] = rs.getString("aero_arrivee");
            row[1] = rs.getInt("vol_count");
            CountVolByAeroArr.add(row);
        }
        return CountVolByAeroArr;
    }
///////////////// code metier//////////
 public ObservableList<Voyage> getAllTriNom() {
        ObservableList<Voyage> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String req = "Select * from Voyage order by destination";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
                Voyage a = new Voyage(rs.getString("destination"),rs.getString("dateDvoy"),rs.getString("dateRvoy")); 
        list.add(a) ;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
  public ObservableList<Voyage> searchByVoyage(String destination ) throws SQLException{
        String qry="SELECT * FROM Voyage where destination LIKE '%"+destination+"%'" ;
                  System.out.println(qry);
            cnx = MaConnexion.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
        ObservableList<Voyage>  list = FXCollections.observableArrayList()  ; 
        while(rs.next()){
         Voyage a = new Voyage(rs.getString("destination"),rs.getString("dateDvoy"),rs.getString("dateRvoy")); 
        list.add(a) ;
        
        }
         

        return list ;
    } 
 
 public List<Object[]> CountVolByVoyaga() throws SQLException {
        List<Object[]> CountVolByVoyaga = new ArrayList<>();
        String req = "SELECT destination, COUNT(*) as vol_count FROM voyage GROUP BY destination";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Object[] row = new Object[2];
            row[0] = rs.getString("destination");
            row[1] = rs.getInt("vol_count");
            CountVolByVoyaga.add(row);
        }
        return CountVolByVoyaga;
    }
 ////////fasak//////
// public List<Object[]> CountVolByAeroArr() throws SQLException {
//        List<Object[]> CountVolByAeroArr = new ArrayList<>();
//        String req = "SELECT aero_arrivee, COUNT(*) as vol_count FROM vol GROUP BY aero_arrivee";
//        Statement st = cnx.createStatement();
//        ResultSet rs = st.executeQuery(req);
//        while (rs.next()) {
//            Object[] row = new Object[2];
//            row[0] = rs.getString("aero_arrivee");
//            row[1] = rs.getInt("vol_count");
//            CountVolByAeroArr.add(row);
//        }
//        return CountVolByAeroArr;
//    }
 
 //////// fin metier////
    @Override
    public void deletOne(Voyage t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

//    @Override
//    public List<Voyage> selectAll() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void updateOne(Voyage t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
