/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.services;

import tn.esprit.YoTalent.entities.Categorie;
import tn.esprit.YoTalent.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author USER
 */
public class ServiceCategorie implements IService<Categorie> {
    private Connection cnx;

    public ServiceCategorie(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(Categorie categorie) throws SQLException {
      String req =   "INSERT INTO `categorie`(`nomCat`)" + "VALUES ('"+categorie.getNomCat()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Categorie ajouté !");
    }

    @Override
    public void updateOne(Categorie t) throws SQLException {
       String req = "UPDATE categorie SET 'nomCat' =  nomCat WHERE 'idCat' = idCat";
             //  "UPDATE `categorie` SET `nomCat`= '"+nomCat+"' WHERE `categorie`.'idCat' ='"+idCat"'";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Person ajouté !");
    }

    @Override
    public void deletOne(Categorie t) throws SQLException {
//"DELETE FROM table_name WHERE some_column = some_value"
    }

    @Override
    public List<Categorie> selectAll() throws SQLException {
          List<Categorie> temp = new ArrayList<>();

        String req = "SELECT * FROM `Categorie`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Categorie Cat = new Categorie();

            Cat.setIdCat(rs.getInt(1));
            Cat.setNomCat(rs.getString("nomCat"));
           

            temp.add(Cat);

        }


        return temp;
    }
}
    

    

