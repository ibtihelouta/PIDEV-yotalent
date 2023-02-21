/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.services.ServiceContrat;

/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class InterfaceContController implements Initializable {

    @FXML
    private Button ExitC;
    @FXML
    private Label LocalisationError;
    @FXML
    private TextField IdC;
    @FXML
    private TextField NomC;
    @FXML
    private TextField DateDC;
    @FXML
    private TextField DateFC;
    @FXML
    private Button ModifyC;
    @FXML
    private TableView<Contrat> AfficherC;
    @FXML
    private TableColumn<Contrat, Integer> colIdC;
    @FXML
    private TableColumn<Contrat, String> colNomC;
    @FXML
    private TableColumn<Contrat, String> colDateDC;
    @FXML
    private TableColumn<Contrat, String> colDateFC;
    @FXML
    private TextField idC;
    @FXML
    private Button DeleteC;
    @FXML
    private ImageView NextC;
    @FXML
    private Button AddC;
@FXML
    private TableView<Contrat> Display;
 ServiceContrat es=new ServiceContrat();
     Contrat e = new Contrat();
     ObservableList<Contrat> contrat ;
      private boolean isLightMode =true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AfficherC.setFocusTraversable(false);
        try {
            getContrat();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceContController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnModifyC(ActionEvent contrat) {
        try{
     
        
         Contrat up=new   Contrat (Integer.valueOf(this.IdC.getText()),this.NomC.getText(),this.DateDC.getText(),this.DateFC.getText());
         ServiceContrat es = new ServiceContrat();
         es.updateOne(up);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Contrat update");
            alert.setContentText("Contrat updated successfully!");
            alert.showAndWait();
            //getContrat();
        } catch(Exception ex){
            System.out.println("fama ghalta2");
        }
       NomC.clear();
       DateDC.clear();
       
       DateFC.clear();
       
    }

    @FXML
    private void btnDeleteC(ActionEvent contrat) throws SQLException {
  

         
         
         Contrat e1 = AfficherC.getItems().get(AfficherC.getSelectionModel().getSelectedIndex());
      
        try {
            es.deletOne(e1);
        }
        catch (SQLException ex) {
            Logger.getLogger(InterfaceContController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event delete");
            alert.setContentText("Event deleted successfully!");
            alert.showAndWait();
         getContrat();
         
          
          
}
    @FXML
    private void btnAddC(ActionEvent contrat) {
        if (DateDC.getText().isEmpty() 
                || NomC.getText().isEmpty()
                || DateFC.getText().isEmpty()) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else{
            Contrat p = new Contrat(DateDC.getText()
                    ,NomC .getText()
                    ,  DateFC.getText());
            
            ServiceContrat sp = new ServiceContrat();
            
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
    
    
     public void getContrat() throws SQLException {
     
    
   colIdC.setCellValueFactory(new PropertyValueFactory<Contrat,Integer>("idC"));
   colNomC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("nomC"));
  colDateDC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("DateDC"));
   colDateFC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("DateFC"));
   
    
    //ServiceContrat es=new ServiceContrat();
    ServiceContrat serC = new ServiceContrat();
      contrat= serC.Fetchc();
        System.out.println(contrat);
        AfficherC.setItems(contrat); 
 }   
}

