/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.services.ServiceEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import tn.esprit.YoTalent.utils.MaConnexion;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InterfaceEventController implements Initializable {


    @FXML
    private TextField NomEv;

     @FXML
    private DatePicker DateDEv;

    @FXML
    private DatePicker DateFEv;

     @FXML
    private TextField Localisation;
    @FXML
    private Button ModifyEv;

    @FXML
    private TableView<Evenement> AfficherEv;
    ServiceEvent es=new ServiceEvent();
     Evenement e = new Evenement();

   

    @FXML
    private TableColumn<Evenement, String> colNomEv;

    @FXML
    private TableColumn<Evenement, String> colLocalisation;

    @FXML
    private TableColumn<Evenement, String> colDateDEv;

    @FXML
    private TableColumn<Evenement, String> colDateFEv;
    
    ObservableList<Evenement> events ;
      private boolean isLightMode =true;

    @FXML
    private TextField idED;
    @FXML
    private TextField idEvM;

    @FXML
    private Button DeleteEv;

    @FXML
    private ImageView NextP;

    @FXML
    private Button AddEv;

    @FXML
    private Label LocalisationError;


    
       private Connection cnx;
    @FXML
    private Button Planning;

    public InterfaceEventController(){
        cnx = MaConnexion.getInstance().getCnx();
        
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         AfficherEv.setFocusTraversable(false);
        try {
            getEvents();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
    public void getEvents() throws SQLException {
     
       
    
     
   colNomEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nomEv"));
   colLocalisation.setCellValueFactory(new PropertyValueFactory<Evenement,String>("localisation"));
   colDateDEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateDEv"));
   colDateFEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateFEv"));
    
    ServiceEvent es=new ServiceEvent();
       events= es.FetchEvents();
        System.out.println(events);
        AfficherEv.setItems(events); 
    }
    
   

    @FXML
    private void btnModifyEv(ActionEvent event) {
        try{
     
         Date DateDebut = Date.valueOf(this.DateDEv.getValue());
         Date DateFin = Date.valueOf(this.DateFEv.getValue());
         Evenement up=new Evenement(Integer.valueOf(this.idEvM.getText()),this.NomEv.getText(),String.valueOf(DateDebut),String.valueOf(DateFin),this.Localisation.getText());
         es.updateOne(up);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event update");
            alert.setContentText("Event updated successfully!");
            alert.showAndWait();
            getEvents();
        } catch(Exception ex){
            System.out.println("fama ghalta2");
        }
       NomEv.clear();
       Localisation.clear();
       DateDEv.setValue(null);
       DateFEv.setValue(null);
        
        
        
        
    }

    @FXML
    private void btnDeleteEv(ActionEvent event) throws SQLException {
        
        /*   
      Evenement e=AfficherEv.getItems().get(AfficherEv.getSelectionModel().getSelectedIndex());
        es.deletOne(Integer.valueOf(idED.getText()));
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event delete");
            alert.setContentText("Event deleted successfully!");
            alert.showAndWait();
         getEvents();*/

         
         
         Evenement e1 = AfficherEv.getItems().get(AfficherEv.getSelectionModel().getSelectedIndex());
      
        try {
            es.supprimerEvent(e1);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event delete");
            alert.setContentText("Event deleted successfully!");
            alert.showAndWait();
         getEvents();
       
        
    }
    
    
    
    
    
  
   
    
    private boolean NoDate(){
     boolean  test=true;
      System.out.println(ChronoUnit.DAYS.between(this.DateDEv.getValue(), this.DateFEv.getValue()));  
      
         int a=(int) ChronoUnit.DAYS.between(this.DateDEv.getValue(), this.DateFEv.getValue());
         int b=(int) ChronoUnit.DAYS.between(LocalDate.now(), this.DateDEv.getValue());
        System.out.println("aaaaaaaaaa"+b);
    if (a<b) //begin akber
    {
  
        test=false;
        
    }
    return test;
    }

    @FXML
    private void btnAddEv(ActionEvent event) throws SQLException {
        String nomEv,LocalisationE;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((NomEv.getText().length()==0)||(Localisation.getText().length()==0))
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    alert.showAndWait();}
         
         else if (NoDate()== false){
                   Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("End date should be greater than begin date");
                    alert.showAndWait();
         }
         else{
             
           
           try{
            nomEv = String.valueOf(NomEv.getText());
          
        }catch(Exception exc){
            System.out.println("name exception");
            return;
        }  
           try{
            LocalisationE = String.valueOf(Localisation.getText());
           
        }catch(Exception exc){
            System.out.println("name exception");

            return;
        } 
            Evenement ev=new Evenement(NomEv.getText(),Localisation.getText(),String.valueOf(DateDEv.getValue()),String.valueOf(DateFEv.getValue()));
         es.createOne(ev);
         getEvents();
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event add");
            alert.setContentText("Event added successfully!");
            alert.showAndWait();
         }
        
    }

    @FXML
    private void btnPlanning(ActionEvent event) {
       
      /*  try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("InterfacePlanning.fxml"));
            change.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
      
      
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherPersonFXML.fxml"));
            
            Parent root = loader.load();            
            InterfacePlanningController fx = loader.getController();
            
            ServiceEvent sp = new ServiceEvent();
            String msg = sp.selectAll().toString();
            //    fx.setLbAffiche(msg);
            
            Planning.getScene().setRoot(root);
            
//            Stage stage = new Stage();
//            stage.setTitle("Afficher Persons");
//            stage.setScene(scene); 
//            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void choisirEvent(MouseEvent event) {
        
        Evenement e = AfficherEv.getItems().get(AfficherEv.getSelectionModel().getSelectedIndex());
        
        //idLabel.setText(String.valueOf(e.getId_event()));
        idED.setText(String.valueOf(e.getIdEv()));
        NomEv.setText(e.getNomEv());
        Localisation.setText(e.getLocalisation());
      // DateDEv.setValue((e.getDateDEv()));
        
    }

        
        
        
    }
    
    
   
    
  
    
    

