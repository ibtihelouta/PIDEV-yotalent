/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.tests;

/**
 *
 * @author USER
 */


 import tn.esprit.YoTalent.entities.Categorie;
import tn.esprit.YoTalent.services.ServiceCategorie;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.services.ServiceEvent;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.services.ServicePlanning;

import tn.esprit.YoTalent.utils.MaConnexion;

import java.sql.SQLException;

public class YYotalent {

    public static void main(String[] args) {
   //  MaConnexion cn1 = MaConnexion.getInstance();
//        MaConnexion cn2 = MaConnexion.getInstance();
//        MaConnexion cn3 = MaConnexion.getInstance();
//        MaConnexion cn4 = MaConnexion.getInstance();
//
//   //   System.out.println(cn1.hashCode());
//        System.out.println(cn2.hashCode());
//        System.out.println(cn3.hashCode());
//        System.out.println(cn4.hashCode());

      //  Categorie p = new Categorie(50, "Fakhreddine");
        
      Evenement pt = new Evenement( "abrra","2 fevrier 2001","3 mars 2001","hje");
         Evenement pt1 = new Evenement( 63,"ibtihel","3 mars 2003","7 mai 2003","hhhje");
          Planning p1 = new Planning("2h", "nisrine", " 14 mars  2001",64);
                   Planning p2 = new Planning( "3h", "nana", " 14 mars  2001");

        //ServiceCategorie sp = new ServiceCategorie();
        ServiceEvent sv = new ServiceEvent();
        ServicePlanning pv = new ServicePlanning();

        try {
             //sv.createOne(pt);
              //sv.createOne(pt1);
             // sv.updateOne(pt1);
             //sv.deletOne(pt);
            //pv.createOne(p1);
           pv.updateOne(p2,11);
             // pv.deletOne( 19);
            System.out.println(sv.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
