/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Remboursement;
import tn.esprit.YoTalent.entities.Ticket;
import tn.esprit.YoTalent.services.ServiceEvent;
import tn.esprit.YoTalent.services.ServiceRemboursement;
import tn.esprit.YoTalent.services.ServiceTicket;
import tn.esprit.YoTalent.utils.JavaMail;
import tn.esprit.YoTalent.utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class InterfaceRemboursementController implements Initializable {
    @FXML
    private TableColumn<Remboursement, Integer> col2;
    @FXML
    private TableColumn<Remboursement, Integer> col3;
    @FXML
    private TextField delete;
    @FXML
    private Button DeleteR;
    @FXML
    private Button AddTr;
    @FXML
    private ImageView back;
    @FXML
    private TableView<Remboursement> TableR;
    ServiceRemboursement Tt=new ServiceRemboursement();
     Remboursement t = new Remboursement();

    /**
     * Initializes the controller class.
     * 
     * 
     */
     
    
  
    
     ObservableList<Remboursement> remboursement ;
    @FXML
    private ComboBox<String> RemComb;
    @FXML
    private Button accepter;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Table.setFocusTraversable(false);
//       try {
//           getRemb();
//        } catch (SQLException ex) {
//           Logger.getLogger(InterfaceRemboursementController.class.getName()).log(Level.SEVERE, null, ex);
//       }

 
  
        try {
            getRemb();
           
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceRemboursementController.class.getName()).log(Level.SEVERE, null, ex);
        }
         

       
              RemComb.setItems(RecupCombo());
        
//        
    }  
    
    
    
    
    
    
    
    public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MaConnexion.getInstance().getCnx();
          String sql = "SELECT nomEv FROM evenement";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   String r = R.getString(1);
   
        System.out.println(r);
    
     
      list.addAll(r);
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
 
    public int findparnomvev(){
     int r =111;
     ServiceEvent se = new ServiceEvent();
    Evenement e = se.Selectid(RemComb.getValue().toString());
       java.sql.Connection cnx;
     cnx = MaConnexion.getInstance().getCnx();
          String sql = "SELECT idT FROM Ticket where  idEv = ?";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
  st.setInt(1, e.getIdEv());
    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   r = R.getInt(1);
   
        System.out.println(r);
    
     
      
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return r;
    }
    

    public void getRemb() throws SQLException {
     
       
  
    col2.setCellValueFactory(new PropertyValueFactory<Remboursement,Integer>("idu"));
   col3.setCellValueFactory(new PropertyValueFactory<Remboursement,Integer>("idT"));
  ServiceRemboursement es=new ServiceRemboursement();
       //remboursement= es.searchByRemboursement(InterfaceTicketController.currentt.getIdEv());
       remboursement= es.FetchRemboursements();
        System.out.println(remboursement);
        TableR.setItems(remboursement); 
    }
    
    
    private boolean NoDate() {
/*
         LocalDate currentDate = LocalDate.now();
         ServiceEvent st = new ServiceEvent();
         Evenement e = st.SelectOneEvent(InterfaceTicketController.currentt.getIdEv());
         
         LocalDate myDate = LocalDate.parse(e.getDateDEv()) ;
         int comparisonResult = myDate.compareTo(currentDate);    
        
         boolean test = true;

        if (comparisonResult < 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult > 0) {
         // myDate est postérieure à currentDate
         test = false;
        }*/
        return true;
    }
    
    
    
    


    

    @FXML
    private void btnDeleteR(ActionEvent event) throws SQLException ,Exception {
        
        
         Remboursement r = TableR.getItems().get(TableR.getSelectionModel().getSelectedIndex());
      
        try {
            Tt.deletOne(r);
            String mail ="hadir.elayeb@esprit.tn";
            JavaMail.sendMai(mail);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText(" demande de reboursement");
            TableR.refresh();
            alert.setContentText(" demande refusée!");
            alert.showAndWait();
         getRemb();
       
        
    }

    @FXML
    private void btnAddR(ActionEvent event) throws SQLException {
        
        
        
        // LocalDate date = dateR.getValue();
//    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    
       /*  
        if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
         }*/
        
      
         int idu = 55;
        
            Remboursement Ps=new Remboursement(false,55,this.findparnomvev());
         Tt.createOne(Ps);
         
         getRemb();
       
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("remboursement add");
            alert.setContentText("remboursement added successfully!");
            alert.showAndWait();
         }
    
        
        
        
        
        
        
    

    @FXML
    private void handleBackArrowImageClickR(MouseEvent event) throws IOException {
        Parent previousScene = FXMLLoader.load(getClass().getResource("InterfaceTicket.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
    private void Recherche(KeyEvent event) throws SQLException {
        ServiceRemboursement Tt=new ServiceRemboursement();
     Remboursement t = new Remboursement();
       //TableR.setItems(Tt.searchByRemboursement(Recherche.getText()))  ;
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException, Exception {
        ServiceRemboursement sr = new ServiceRemboursement();
        ServiceTicket st = new ServiceTicket();
         Remboursement r = TableR.getItems().get(TableR.getSelectionModel().getSelectedIndex());
         r.setDecision(true);
         sr.updateOne(r);
         String mail ="hadir.elayeb@esprit.tn";
         JavaMail.sendMail(mail);
         Ticket t = new Ticket();
         t.setIdT(r.getIdT());
        // sr.deletOne(r);
         //st.deletOne(t);
         TableR.refresh();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("demande de remboursement ");
            alert.setContentText("rebousement accepté!");
            alert.showAndWait();
         
        
    }
    
}
