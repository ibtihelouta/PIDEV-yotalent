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
import tn.esprit.YoTalent.utils.MaConnexion;
import tn.esprit.YoTalent.services.ServiceTicket;
import tn.esprit.YoTalent.entities.Ticket;
 import tn.esprit.YoTalent.entities.Participation;
import tn.esprit.YoTalent.services.ServicePartic;




import java.sql.SQLException;
import tn.esprit.YoTalent.entities.Remboursement;
import tn.esprit.YoTalent.services.ServiceRemboursement;

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

       // Categorie p = new Categorie(1,"sarra");
        
     
       //Evenement pt = new Evenement(62,"sarra","tgg","gyhgy","ggg");
      Ticket t = new Ticket(7000,10,62);
     
        // Remboursement rem = new Remboursement("1/20/2042",2);
         //  Remboursement rem1 = new Remboursement(5,"6/6/2001",2);
         // Remboursement rem2 = new Remboursement(30,"1/15/2615",2);
          Remboursement rem3 = new Remboursement(70000,"1/77/2001",2); 
          
    //Remboursement rem1 = new Remboursement("1/21/2022",2);
         
      // ServiceCategorie sp = new ServiceCategorie();
     //    ServicePartic sp = new ServicePartic();
       // ServiceEvent sv = new ServiceEvent();
         // ServiceTicket st = new ServiceTicket();
 ServiceRemboursement st= new ServiceRemboursement();

        try {
           //sp.createOne(p);
         //  st.createOne(t);
  //st.updateOne(t);
  //st.updateOne(t);
 //st.createOne(rem3);
  //st.deletOne (t);
  st.deletOne (rem3);
 // st.updateOne(rem3);
            System.out.println(st.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
