/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.services.ServicePlanning;
import tn.esprit.YoTalent.services.ServiceEvent;
import tn.esprit.YoTalent.utils.MaConnexion;
  

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InterfacePlanningController implements Initializable {

    
   
  
    @FXML
    private JFXTimePicker Hour;
    @FXML
    private TextField NomActivite;
    @FXML
    private DatePicker DatePL;
   
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
    private ImageView BackP;
    @FXML
    private Button AddPL;
    String erreur;
    int etatrecaptcha = 0;
    
     private Connection cnx;
    
     
    
     @FXML
    private TableColumn<Planning, String> colHour;

    @FXML
    private TableColumn<Planning, String> colNomActivite;

    @FXML
    private TableColumn<Planning, String> colDatePL;

     ObservableList<Planning> planning ;
      private boolean isLightMode =true;
      java.sql.Timestamp timestamp = null;
@FXML
    private ComboBox<Integer> EventCombo;

@FXML
    private TextField idPM;
    @FXML
    private TextField Recherche;
    @FXML
    private Button Trie;



   
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
            Logger.getLogger(InterfacePlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
              EventCombo.setItems(RecupCombo());
        
    }  
    
   
    public static ObservableList<Integer> RecupCombo(){
             
             
    ObservableList<Integer> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MaConnexion.getInstance().getCnx();
          String sql = "SELECT idEv FROM evenement";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   String r = R.getString(1);
   int n= Integer.valueOf(r);
        System.out.println(n);
    
     
      list.addAll(n);
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
   
    
   
    
   public void getPlanning() throws SQLException {
     
       
    
     colHour.setCellValueFactory(new PropertyValueFactory<Planning,String>("hour"));
   colNomActivite.setCellValueFactory(new PropertyValueFactory<Planning,String>("nomActivite"));
   colDatePL.setCellValueFactory(new PropertyValueFactory<Planning,String>("datePL"));
  ServicePlanning es=new ServicePlanning();
       planning= es.FetchPlanning();
        System.out.println(planning);
        afficherPL.setItems(planning); 
    }
   
  
   
   private boolean NoDate() {

         LocalDate currentDate = LocalDate.now();
         
         LocalDate myDate = DatePL.getValue(); 
         int comparisonResult = myDate.compareTo(currentDate);    
        
         boolean test = true;

        if (comparisonResult < 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult > 0) {
         // myDate est postérieure à currentDate
         test = false;
        }
        return test;
    }
    
    
    
  
   
    @FXML
    private void btnModifyPL(ActionEvent event) {
        
            LocalDate date = DatePL.getValue();
    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    LocalTime time = Hour.getValue();
    String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       
       String NomActiviteV;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((NomActivite.getText().length()==0))
                { Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    alert.showAndWait();}
         
        else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
         }
         else{
             
           
           try{
            NomActiviteV = String.valueOf(NomActivite.getText());
          
        }catch(Exception exc){
            System.out.println("name exception");
            return;
        }  
          
        } 
         
         
            
         
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event update");
            alert.setContentText("Event updated successfully!");
            alert.showAndWait();
            
            
           
            try{
     
         Date DateD = Date.valueOf(this.DatePL.getValue());
         
        
         Time times = Time.valueOf(this.Hour.getValue());
          Planning e1 = afficherPL.getItems().get(afficherPL.getSelectionModel().getSelectedIndex());
        
             Planning up=new Planning(Integer.valueOf(this.idPM.getText()),String.valueOf(times),this.NomActivite.getText(),String.valueOf(DateD),EventCombo.getValue());
        
            es.updateOne(up, Integer.valueOf(this.idPM.getText()));
            getPlanning();
        } catch(Exception ex){
            System.out.println("fama ghalta2");
        }
       NomActivite.clear();
       DatePL.setValue(null);
       Hour.setValue(null);
       //EventCombo.setValue(null);
       EventCombo.getValue();
       
        
       
     
    
       
 
       
           
    } 
        
        
        
        
    


    @FXML
    private void btnDeletePL(ActionEvent event) throws SQLException {
        
          Planning e1 = afficherPL.getItems().get(afficherPL.getSelectionModel().getSelectedIndex());
      
        try {
            es.supprimerEvent(e1);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event delete");
            alert.setContentText("Event deleted successfully!");
            alert.showAndWait();
         getPlanning();
       
        
        
        
        
        
    }

    @FXML
    private void btnAddPL(ActionEvent event) throws SQLException {
       // Evenement Event = EventCombo.getValue();
        
         LocalDate date = DatePL.getValue();
    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    LocalTime time = Hour.getValue();
    String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       
       String NomActiviteV;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((NomActivite.getText().length()==0))
                { Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    alert.showAndWait();}
         
         
         
           if ((NomActivite.getText().length() > 20) ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Please enter a title with a length of maximum 20 characters.");
        alert.showAndWait();
        return;
    }  
         
         
        else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
         }
         else{
             
           
           try{
            NomActiviteV = String.valueOf(NomActivite.getText());
          
        }catch(Exception exc){
            System.out.println("name exception");
            return;
        }  
          
        } 
         
         
         
            Planning Ps=new Planning(String.valueOf(Hour.getValue()),NomActivite.getText(),String.valueOf(DatePL.getValue()),EventCombo.getValue());
         es.createOne(Ps);
         getPlanning();
       
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("planning add");
            alert.setContentText("planning added successfully!");
            alert.showAndWait();
         }
    
        
    
    
    
    
    @FXML
private void handleBackArrowImageClickP(MouseEvent event) throws IOException {
    Parent previousScene = FXMLLoader.load(getClass().getResource("InterfaceEvent.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}  

        
       @FXML
    void choisirPlanning(MouseEvent event) {
        
        
         Planning e = afficherPL.getItems().get(afficherPL.getSelectionModel().getSelectedIndex());
        
        //idLabel.setText(String.valueOf(e.getId_event()));
       idPD.setText(String.valueOf(e.getIdP()));
       
        NomActivite.setText(e.getNomActivite());
       idPD.setText(String.valueOf(e.getIdP()));
         
       //DateDEv.setValue((e.getDateDEv()));
       
    

    } 

    @FXML
    private void Recherche(KeyEvent event) throws SQLException {
        
        
        
         Planning p=new Planning();
ServicePlanning sp = new ServicePlanning();
       afficherPL.setItems(sp.searchByPlanning(Recherche.getText()))  ;
    }

    @FXML
    private void btnTrieparNom(ActionEvent event) throws SQLException {
        
         colHour.setCellValueFactory(new PropertyValueFactory<Planning,String>("hour"));
   colNomActivite.setCellValueFactory(new PropertyValueFactory<Planning,String>("nomActivite"));
   colDatePL.setCellValueFactory(new PropertyValueFactory<Planning,String>("datePL"));
       
        
        
       
         planning=es.getAllTriNom();
             afficherPL.setItems(planning);
    }

}
       



        
        
        
        
 
        
        
    

    
        
        
        
    
    

