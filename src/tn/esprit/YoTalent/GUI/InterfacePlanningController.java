/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.services.ServicePlanning;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InterfacePlanningController implements Initializable {

    
    @FXML
    private TextField Hour;
    @FXML
    private TextField NomActivite;
    @FXML
    private DatePicker DatePL;
    @FXML
    private TextField IdEv;
    @FXML
    private Button ModifyPL;
    @FXML
    private TableView<Planning> afficherPL;
    ServicePlanning es=new ServicePlanning();
     Planning e = new Planning();
    @FXML
    private TextField idPD;
    @FXML
    private Button DeletePL;
    @FXML
    private ImageView NextE;
    @FXML
    private Button AddPL;
    
     @FXML
    private TableColumn<Planning, String> colHour;

    @FXML
    private TableColumn<Planning, String> colNomActivite;

    @FXML
    private TableColumn<Planning, String> colDatePL;

    @FXML
    private TableColumn<Planning, Integer> colIDEv;
     ObservableList<Planning> planning ;
      private boolean isLightMode =true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          afficherPL.setFocusTraversable(false);
        try {
            getPlanning();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }   
    
    
   public void getPlanning() throws SQLException {
     
       
    
     colHour.setCellValueFactory(new PropertyValueFactory<Planning,String>("hour"));
   colNomActivite.setCellValueFactory(new PropertyValueFactory<Planning,String>("nomActivite"));
   colDatePL.setCellValueFactory(new PropertyValueFactory<Planning,String>("datePL"));
   colIDEv.setCellValueFactory(new PropertyValueFactory<Planning,Integer>("idEv"));
     
   ;
    
    ServicePlanning es=new ServicePlanning();
       planning= es.FetchPlanning();
        System.out.println(planning);
        afficherPL.setItems(planning); 
    }
   
  
   
   private boolean NoDate(){
     boolean  test=true; 
     
         int a=(int) ChronoUnit.DAYS.between(LocalDate.now(), this.DatePL.getValue());
        System.out.println("aaaaaaaaaa"+a);
          return test;
   
   }
   
    @FXML
    private void btnModifyPL(ActionEvent event) {
    }

    @FXML
    private void btnDeletePL(ActionEvent event) {
    }

    @FXML
    private void btnAddPL(ActionEvent event) {
        
         
       
        if (Hour.getText().isEmpty() 
                || NomActivite.getText().isEmpty()
                || DatePL.getValue().isLeapYear()
                ||IdEv.getText().isEmpty()
                ) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else{
            Planning p = new Planning(Hour.getText()
                    , NomActivite.getText()
                    ,String.valueOf(DatePL.getValue())
                    , Integer.parseInt(IdEv.getText()));
            
            ServicePlanning sp = new ServicePlanning();
            
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

        
        
        
        
        
        
        
    }

    
        
        
        
    
    

