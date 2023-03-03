/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.services.ServiceEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private Button DeleteEv;

    @FXML
    private ImageView NextP;
@FXML
    private ImageView  Back;
    @FXML
    private Button AddEv;

    @FXML
    private Label LocalisationError;
@FXML
    private Button triNom;

    
       private Connection cnx;
  ;
    @FXML
    private TextField Recherche;
    public static Evenement currentevent;
    private int i;

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
 colDateDEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateDEv"));
   colDateFEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateFEv"));
     colLocalisation.setCellValueFactory(new PropertyValueFactory<Evenement,String>("localisation"));
    
    ServiceEvent es=new ServiceEvent();
       events= es.FetchEvents();
        System.out.println(events);
        AfficherEv.setItems(events); 
    }
    
   

    @FXML
    private void btnModifyEv(ActionEvent event) throws SQLException {
         String nomEv,LocalisationE;
         
       
         if ((NomEv.getText().length()==0)||(Localisation.getText().length()==0))
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    
                    alert.showAndWait();}
         
          if ((NomEv.getText().length() > 20)||(Localisation.getText().length()>20) ) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Please enter a title with a length of maximum 20 characters.");
        alert.showAndWait();
        return;
    }  
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
            System.out.println("localisation exception");
            
            
            
            
            

            return;
        } 
         
          
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event update");
            alert.setContentText("Event updated successfully!");
            alert.showAndWait();
            
            
             Evenement e1 = AfficherEv.getItems().get(AfficherEv.getSelectionModel().getSelectedIndex());
            try{
     
         Date DateDebut = Date.valueOf(this.DateDEv.getValue());
         Date DateFin = Date.valueOf(this.DateFEv.getValue());
        
          FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(null);
                String imagePath = selectedFile.getAbsolutePath();
                System.out.println(Integer.toString(i));
                
             Evenement up=new Evenement(i,this.NomEv.getText(),this.Localisation.getText(),String.valueOf(DateDebut),String.valueOf(DateFin),imagePath);
            // (int idEv, String nomEv, String localisation, String dateDEv, String dateFEv, String image) 
         es.updateOne(up);
            
            getEvents();
        } catch(Exception ex){
            System.out.println("fama ghalta2");
        }
       NomEv.clear();
       DateDEv.setValue(null);
       DateFEv.setValue(null);
         Localisation.clear();
        
       
     
    
       
 
       
           
    }
     
     
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
    private void btnAddEv(ActionEvent event) throws SQLException, IOException {
        String nomEv,LocalisationE;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((NomEv.getText().length()==0)||(Localisation.getText().length()==0))
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    
                    alert.showAndWait();}
         
         if ((NomEv.getText().length() > 20)||(Localisation.getText().length()>20) ) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Please enter a title with a length of maximum 20 characters.");
        alert.showAndWait();
        return;
    }  
         
         
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
            System.out.println("localisation exception");
            
            
            
            
            

            return;
        } 
           FileChooser fileChooser1 = new FileChooser();
                File selectedFile1 = fileChooser1.showOpenDialog(null);
                String imagePath = selectedFile1.getAbsolutePath();



           
            Evenement ev=new Evenement(NomEv.getText(),Localisation.getText(),String.valueOf(DateDEv.getValue()),String.valueOf(DateFEv.getValue()),imagePath);
    
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
   
      private void choisirEvent(MouseEvent event) {
        
        Evenement e = AfficherEv.getItems().get(AfficherEv.getSelectionModel().getSelectedIndex());
        
        //idLabel.setText(String.valueOf(e.getId_event()));
       idED.setText(String.valueOf(e.getIdEv()));
        NomEv.setText(e.getNomEv());
        currentevent=e;
         
       this.i=e.getIdEv();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(e.getDateDEv());
     LocalDate localDate1 = LocalDate.parse(e.getDateFEv());  
       DateDEv.setValue(localDate);
       DateFEv.setValue(localDate1);
       Localisation.setText(e.getLocalisation());
        
    }

        
      @FXML
private void handleArrowImageClickE(MouseEvent event) throws IOException {
    Parent nextScene = FXMLLoader.load(getClass().getResource("InterfacePlanning.fxml"));
    Scene scene = new Scene(nextScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

@FXML
private void handleBackArrowImageClickE(MouseEvent event) throws IOException {
    Parent previousScene = FXMLLoader.load(getClass().getResource("path/to/login.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}  

    @FXML
    private void Recherche(KeyEvent event)throws SQLException {
        
        
         Evenement p=new Evenement();
ServiceEvent sp = new ServiceEvent();
       AfficherEv.setItems(sp.searchByEvenement(Recherche.getText()))  ;
    }
    
    @FXML
    void triNom(ActionEvent event) {
          
   colNomEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nomEv"));
 colDateDEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateDEv"));
   colDateFEv.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dateFEv"));
     colLocalisation.setCellValueFactory(new PropertyValueFactory<Evenement,String>("localisation"));
        events=es.getAllTriNom();
             AfficherEv.setItems(events);
    }
        
    }
        
    
    
    
   
    
  
    
    

