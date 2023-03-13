package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.entities.Voyage;
import tn.esprit.YoTalent.services.ServiceContrat;
import tn.esprit.YoTalent.services.ServiceVoyage;

public class InterfaceVoyageController implements Initializable {
     @FXML
    private TableView<Voyage> AfficherTr;

    @FXML
    private ImageView BackTr;

    @FXML
    private TableColumn<Voyage, String> ColDes;

   // @FXML
   // private TableColumn<Voyage, Integer> ColIdTR;

    @FXML
    private Label DeletTR;

    @FXML
    private Button ExitTR;

    @FXML
    private TextField IdTR;

    @FXML
    private Label LocalisationError;

    

    @FXML
    private TextField RechercheV;

    @FXML
    private Button addVoyage;

    @FXML
    private TableColumn<Voyage, String> colDateDTR;

    @FXML
    private TableColumn<Voyage, String> colDateRTR;

    @FXML
    private DatePicker dateRV;

    @FXML
    private DatePicker dateV;

    @FXML
    private Button deleteVoyage;

    @FXML
    private TextField destinationTR;
    
    


    @FXML
    private Button updateVoyage;
    ServiceVoyage es=new  ServiceVoyage();
     Voyage e = new Voyage();
     ObservableList<Voyage> Voyage ;
      private boolean isLightMode =true;
      java.sql.Timestamp timestamp = null;
      private int idv;
       @FXML
    private TextField Recherche;
       @FXML
    private Button tri;
      
       /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        
           AfficherTr.setFocusTraversable(false);
        try {
            getVoyage();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceContratController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
      }  
     //debut code ibt
    public void getVoyage() throws SQLException {
    
    
     ColDes.setCellValueFactory(new PropertyValueFactory<Voyage,String>("destination"));
   colDateDTR.setCellValueFactory(new PropertyValueFactory<Voyage,String>("dateDVoy"));
   colDateRTR.setCellValueFactory(new PropertyValueFactory<Voyage,String>("dateRVoy"));
  
  ServiceVoyage es=new ServiceVoyage();
      Voyage= es.Fetchv();
        System.out.println( Voyage);
        AfficherTr.setItems( Voyage); 
    }
     private boolean NoDate() {

         LocalDate currentDate = LocalDate.now();
         
         LocalDate myDate = dateV.getValue(); 
         
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
    private void btnModifyC() {
        
            LocalDate date = dateV.getValue();
               //LocalDate date2 = DateFC.getValue();
            
    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

   
    
       
       String Nomdestin;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((destinationTR.getText().length()==0))
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
            Nomdestin = String.valueOf(destinationTR.getText());
          
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
     
         Date DateD = Date.valueOf(this.dateV.getValue());
            Date DateDF = Date.valueOf(this.dateRV.getValue());
         
        
      
          Voyage e1 =  AfficherTr.getItems().get(AfficherTr.getSelectionModel().getSelectedIndex());
//  System.out.println((Integer.valueOf(this.idC.getText())));
    System.out.println(destinationTR.getText());
      System.out.println(String.valueOf(DateD));
        System.out.println(String.valueOf(DateDF));
             Voyage up =new Voyage(e1.getIdVoy(),String.valueOf(DateD),String.valueOf(DateDF),destinationTR.getText());
            System.out.println("blabla");
        System.out.println(up);
           es.updateOne(up,up.getIdVoy());
            getVoyage();
        } catch(Exception ex){
            System.out.println("erreur");
            System.out.println(ex);
            ex.printStackTrace();
        }
       destinationTR.clear();
      dateV.setValue(null);
       dateRV.setValue(null);
       //EventCombo.setValue(null);
       //EventCombo.getValue();
     }  
       
      @FXML
    private void supprimer() throws SQLException {
         Voyage e1 = AfficherTr.getItems().get(AfficherTr.getSelectionModel().getSelectedIndex());
      
         es.DeleteVoyage(e1.getIdVoy());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event delete");
            alert.setContentText("Event deleted successfully!");
            alert.showAndWait();
         getVoyage();
    }
     @FXML
    private void btnAddPL() throws SQLException {
       // Evenement Event = EventCombo.getValue();
        
         LocalDate date = dateV.getValue();
    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

  
    //String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       
       String Nomdestin;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((destinationTR.getText().length()==0))
                { Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    alert.showAndWait();}
         
         
         
           if ((destinationTR.getText().length() > 20) ) {
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
            return;
         }
        else{
             
         
         try{
         Nomdestin = String.valueOf(destinationTR.getText());
        
      }catch(Exception exc){
           System.out.println("name exception");
          return;
      }  
        
      } 
         
           Voyage Ps=new Voyage(destinationTR.getText(),String.valueOf(dateV.getValue()),String.valueOf(dateRV.getValue()));
           
           
            
            
         es.createOne(Ps);
         getVoyage();
       
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Voyage add");
            alert.setContentText("Voyage added successfully!");
            alert.showAndWait();
         }
    
     
       void choisirVoyage() {
        
          
      Voyage v = AfficherTr.getItems().get(AfficherTr.getSelectionModel().getSelectedIndex());
        
        //idLabel.setText(String.valueOf(e.getId_event()));
       IdTR.setText(String.valueOf(v.getIdVoy()));
          dateV.setValue(LocalDate.parse(v.getDateDVoy()));
          dateRV.setValue(LocalDate.parse(v.getDateRVoy()));
        destinationTR.setText(v.getDestination());
      // this.id=c.setIdP(idP);
         this.idv=v.getIdVoy();
     
    } 
    @FXML
    private void btnTrieparNom2()  {
        
          ColDes.setCellValueFactory(new PropertyValueFactory<Voyage,String>("destination"));
   colDateDTR.setCellValueFactory(new PropertyValueFactory<Voyage,String>("dateDVoy"));
   colDateRTR.setCellValueFactory(new PropertyValueFactory<Voyage,String>("dateRVoy"));
       
        
        
       
         Voyage=es.getAllTriNom();
              AfficherTr.setItems(Voyage);
    }

    @FXML
    private void Recherche(KeyEvent event) throws SQLException {
        
     Voyage p=new Voyage();
ServiceVoyage sc = new ServiceVoyage();
//afficherPL.setItems(sp.searchByPlanning(Recherche.getText()))  ;
AfficherTr.setItems(sc.searchByVoyage(Recherche.getText()));
//searchByContrat(Recherche.getText()
//       afficherContrat.setItems(sc.Fetchc())  ;   
        
        
        
    }
    
       
   @FXML
    private void gotovoy(MouseEvent event) throws IOException {
        
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceContrat.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
private void Retour1(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("statt.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}   
}