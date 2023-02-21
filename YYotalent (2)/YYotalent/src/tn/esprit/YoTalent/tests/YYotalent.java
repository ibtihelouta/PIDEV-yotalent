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
import tn.esprit.YoTalent.entities.Evenement;
//import tn.esprit.YoTalent.services.ServiceTicket;
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
      
  Contrat c5 = new Contrat ( 53,"aab ", "bh" , "ri" );//upd
  //Contrat c2 = new Contrat ( "l", "f" , "o" ); // creation
  //Contrat c1 = new Contrat (50); //sup
//
     // Voyage v = new Voyage ( "K","t" , "vo" ,1); //cre
      // Voyage v1 = new Voyage ( "bbb","iuuu" , "Voyage1" ,1);
//         
//    
           ServiceVoyage serV = new ServiceVoyage();
                      ServiceContrat serC = new ServiceContrat();

//       
//               
//   
        try {
//          cc.createOne(c2);
//           cc.updateOne(c5);
//           cc.deletOne(c);
 //serV.createOne(v);
     // serC.createOne(c2);
    serC.updateOne(c5);
          // serV.updateOne(v,54);
       // serC.deletOne(c1);
          //  serV.DeleteVoyage(72);
            System.out.println(serC.selectAll());
             System.out.println(serV.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//
//
//    }
  }  }