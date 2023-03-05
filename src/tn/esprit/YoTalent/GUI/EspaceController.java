/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.YoTalent.entities.Remboursement;
import tn.esprit.YoTalent.entities.Ticket;
import tn.esprit.YoTalent.services.ServiceRemboursement;
import tn.esprit.YoTalent.services.ServiceTicket;
import tn.esprit.YoTalent.utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EspaceController implements Initializable {

    @FXML
    private Label prixtotal;
    @FXML
    private ComboBox<Integer> nbticket;
    @FXML
    private ImageView imagEv;
    @FXML
    private Label nomEv;
    @FXML
    private Label dateDEv;
    @FXML
    private Label localisation;
    @FXML
    private Button payer;
    @FXML
    private Button valider;
    @FXML
    private Button rem;
    @FXML
    private TextField idticket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       idticket.setVisible(false);
        valider.setVisible(false);
        // TODO
        nbticket.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        ServiceTicket st = new ServiceTicket();
       nbticket.valueProperty().addListener((observable, oldValue, newValue) -> {
    // Your code to execute when the ComboBox value changes
    int idev=62; // idev = InterfacehomeController.currentevent.getIdEv();
    int prix = st.prixticket(idev, nbticket.getValue());
    prixtotal.setText(String.valueOf(prix));
    
    
});
       String imagePath ="C:\\Users\\USER\\OneDrive\\Images\\Captures d’écran\\5.png";//imagePath=InterfacehomeController.currentevent.getimageEv
       Image image = new Image(new File(imagePath).toURI().toString());
       imagEv.setImage(image);
       String nomev ="nombaw";//nom=InterfacehomeController.currentevent.getnomEv
       nomEv.setText(nomev);
       String local="localisation"; // local= InterfacehomeController.currentevent.getlocalisation
        localisation.setText(local);
        String date ="17/12/2023";//date = InterfacehomeController.currentevent.getdateEv
        dateDEv.setText(date);
        
        
    }    

    @FXML
    private void payer(ActionEvent event) throws SQLException, Exception {
        int nbrt = nbticket.getValue();
        ServiceTicket st = new ServiceTicket();
        
        int idev = 63;// idev = InterfacehomeController.currentevent.getIdEv();
        int prix = st.getprixticket(idev);
        String mail ="hadir.elayeb@esprit.tn";//mail=sessioncontroller.currentuser.getmail;
        String nomev = "hadir";// nom=InterfacehomeController.currentevent.getnomEv;
        for(int i =0;i<nbrt;i++){
            int idu=45;//idu = sessioncontroller.currentuser.getidu;
            Ticket t = new Ticket(prix,idev,nomev);
            st.payer(t);
             int id =st.recentid();
        JavaMail.sendMail(mail,String.valueOf(id));    
        
        }
    }
       
   
    @FXML
    private void valider(ActionEvent event) throws SQLException,Exception {
         ServiceRemboursement sr = new ServiceRemboursement();
           String mail ="hadir.elayeb@esprit.tn";
            JavaMail.sendMaill(mail); 
        int idu = 1;//idu = sessioncontroller.currentuser.getidu
        Remboursement r = new Remboursement(true, idu, Integer.parseInt(idticket.getText()));
        sr.createOne(r);
          
    }

    @FXML
    private void rembourser(ActionEvent event) {
        idticket.setVisible(true);
        valider.setVisible(true);
    }
    
}
