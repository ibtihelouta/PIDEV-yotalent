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
import tn.esprit.YoTalent.entities.EspaceTalent;
import tn.esprit.YoTalent.services.ServiceET;

import tn.esprit.YoTalent.entities.Video;
import tn.esprit.YoTalent.services.ServiceVideo;



import java.sql.SQLException;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.entities.Remboursement;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.services.ServiceUser;

public class YYotalent{

    public static void main(String[] args) {

    // créer un objet EspaceTalent
   
     Ticket t = new Ticket(81,10,62);
        Ticket tt = new Ticket(10,88888,62);
     
          Remboursement rem3 = new Remboursement(800,"1/1/2001",2); 
          
    
        ServiceTicket st = new ServiceTicket();
      ServiceVideo sv = new ServiceVideo();

    // set other fields of espacetalent object here...

      try {
            
                    st.createOne(t);
//                  se.updateOne(etm);
//                  se.deletOne(etd);
     
         // sc.createOne(c);
             
           System.out.println(st.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

}
