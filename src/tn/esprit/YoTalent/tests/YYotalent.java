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
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.entities.Voyage;
import tn.esprit.YoTalent.services.ServiceContrat;
import tn.esprit.YoTalent.services.ServiceVoyage;

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
        
     
      // Evenement pt = new Evenement(62,"sarra","tgg","gyhgy","ggg");
     // Ticket t = new Ticket(2,99,62);
      
   Contrat c = new Contrat ( "kjhg ", "06/01/2022" , "02/06/2023" );

      Voyage v = new Voyage ( "11/11/2023","10/12/2003" , "voyage" ,2);
       Voyage v1 = new Voyage ( "11/11/2000","10/12/2000" , "Voyage1" ,2);
         
    
           ServiceVoyage serV = new ServiceVoyage();
            ServiceContrat cc = new ServiceContrat();
      // ServiceCategorie sp = new ServiceCategorie();
     //    ServicePartic sp = new ServicePartic();
       // ServiceEvent sv = new ServiceEvent();
          //ServiceTicket st = new ServiceTicket();

               
   
        try {
           cc.createOne(c);
          serV.createOne(v);
          serV.createOne(v1);
  serV.updateOne(v);
            System.out.println(serV.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
