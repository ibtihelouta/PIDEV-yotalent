/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.entities.Voyage;
import tn.esprit.YoTalent.services.ServiceContrat;
import tn.esprit.YoTalent.services.ServiceVoyage;

/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class InterfaceVoyController implements Initializable {

    @FXML
    private Button ExitTR;
    @FXML
    private Label LocalisationError;
    @FXML
    private TextField IdTR;
    @FXML
    private TextField destinationTR;
    @FXML
    private TextField IDTR;
    @FXML
    private TextField DateDTR;
    
    
    @FXML
    private TextField DateFTR;
    @FXML
    private TextField idC;
   
    @FXML
    private Button ModifyTR;
    @FXML
    private TableView<?> AfficherTr;
    @FXML
    private TableColumn<Voyage, Integer> ColIdTR;
    @FXML
    private TableColumn<Voyage,String> colDateDTR;
    @FXML
    private TableColumn<Voyage, String> colDateRTR;
    @FXML
    private TableColumn<Voyage, String> ColDes;
    @FXML
    private TableColumn<Voyage, Integer> colIdC;
    @FXML
    private TextField idTr;
    @FXML
    private Button DeleteTR;
    @FXML
    private ImageView NextTr;
    @FXML
    private Button AddTR;
    @FXML
    private Label DeletTR;
    @FXML
    private ImageView BackTr;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherTr.setFocusTraversable(false);
        try {
            getVoyage();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceVoyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnModifyEv(ActionEvent voyage) {
        try{
     
        
        Voyage up = new   Voyage (Integer.valueOf(this.IdTR.getText()),this.DateDTR.getText(),this.DateFTR.getText(),this.destinationTR.getText(),Integer.valueOf(this.idC.getText()));
         ServiceVoyage es = new ServiceVoyage();
         es.updateOne(up, 0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Voyage update");
            alert.setContentText("Voyage updated successfully!");
            alert.showAndWait();
            //getContrat();
        } catch(Exception ex){
            System.out.println("fama ghalta2");
        }
       DateDTR.clear();
       DateFTR.clear();
       destinationTR.clear();
     idC.clear();
    }

    @FXML
    private void btnDeleteEv(ActionEvent voyage) {
    }

    @FXML
    private void btnAddEv(ActionEvent voyage) {
         if (DateDTR.getText().isEmpty() 
                ||DateFTR .getText().isEmpty()
                ||destinationTR .getText().isEmpty() 
         || IDTR.getText().isEmpty()){
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else{
           Voyage p = new Voyage(DateDTR.getText()
                    , DateFTR.getText()
                    ,  destinationTR.getText()
                   ,Integer.parseInt(idC.getText()));
                   
            
            ServiceVoyage sp = new ServiceVoyage();
            
            try {
                sp.createOne(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
    }
    public void getVoyage() throws SQLException {
   }    
}
