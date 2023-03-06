/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;
import java.io.IOException;
import tn.esprit.YoTalent.entities.Ticket;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Remboursement;
import tn.esprit.YoTalent.entities.Ticket;
import tn.esprit.YoTalent.services.ServiceEvent;
import tn.esprit.YoTalent.services.ServiceRemboursement;
import tn.esprit.YoTalent.services.ServiceTicket;
import tn.esprit.YoTalent.utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InterfaceTicketController implements Initializable {


    @FXML
    private TextField PrixT;
    private TextField IdEv;
    @FXML
    private Button ModifyT;
    @FXML
    private Button DeleteT;
    @FXML
    private Button AddT;
   

    @FXML
    private ImageView next;

      
    @FXML
    private ImageView back;
       @FXML
    private TextField Recherche;

   

      
    @FXML
    private TableView<Ticket> Table;
    ServiceTicket Tt=new ServiceTicket();
     Ticket t = new Ticket();
    @FXML
    private TableColumn<Ticket, Integer> Col2;
    @FXML
    private TableColumn<Ticket, String> Col3;
    ObservableList<Ticket> tickets ;
      private boolean isLightMode =true;

      
      private  int idt;
      public static Ticket currentt;

 ServiceTicket st = new ServiceTicket();
  
     Ticket s = new Ticket();
     ObservableList<Ticket> ticket ;
    @FXML
    private ComboBox<String> TickeComb;
    @FXML
    private Button EventMa;
    @FXML
    private Button user;
    @FXML
    private Button Exit;
      

    
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Table.setFocusTraversable(false);
       try {
           getTicket();
        } catch (SQLException ex) {
           Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
       }
         TickeComb.setItems(RecupCombo());
    } 
    public void getTicket() throws SQLException {
     
       
    
    // Col1.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("idT"));
   Col2.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("prixT"));
   Col3.setCellValueFactory(new PropertyValueFactory<Ticket,String>("nomEv"));
  
    
    ServiceTicket Tt=new ServiceTicket();
       tickets= Tt.FetchTickets();
       
        System.out.println(tickets);
        Table.setItems(tickets);
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
   
        
    
     
      list.addAll(r);
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
   
    
    

    
    
@FXML
private void handleArrowImageClickT(MouseEvent event) throws IOException {
    Parent nextScene = FXMLLoader.load(getClass().getResource("InterfaceRemboursement.fxml"));
    Scene scene = new Scene(nextScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

@FXML
private void handleBackArrowImageClickT(MouseEvent event) throws IOException {
    Parent previousScene = FXMLLoader.load(getClass().getResource("InterfaceRemboursement.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}


 @FXML
    void Recherche(KeyEvent event) throws SQLException {
   ServiceTicket Tt=new ServiceTicket();
     Ticket t = new Ticket();
       Table.setItems(Tt.searchByTicket(Integer.parseInt(Recherche.getText())))  ;
       
      
    }
    @FXML
    private void btnModifyT(ActionEvent ticket) throws SQLException
    {
       
            try{
     
       
         new Remboursement();
        
             ServiceEvent se = new ServiceEvent();
             int i = se.Selectid(TickeComb.getValue()).getIdEv();
             // Ticket t = new Ticket(Integer.parseInt(PrixT.getText()), TickeComb.getValue());
                       Ticket t =new Ticket((Integer.valueOf(idt)),(Integer.valueOf(this.PrixT.getText())),i);
         Tt.updateOne(t);
         
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event update");
            alert.setContentText("Event updated successfully!");
            alert.showAndWait();
            
            getTicket();
        } catch(Exception ex){
            System.out.println("fama ghalta2");
        }
       
        PrixT.clear();
       
       //EventCombo.setValue(null);
       TickeComb.getValue();
           
    }
    
      

    @FXML
    private void btDeleteT(ActionEvent ticket) throws SQLException {
        
   
           Ticket t =Table.getItems().get(Table.getSelectionModel().getSelectedIndex());
      
        try {
            Tt.deletOne(t);
            Table.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
            
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Ticket delete");
        alert.setContentText("Ticket deleted successfully!");
        alert.showAndWait();
      getTicket();
            
   }
        
    

    @FXML
    private void btnAddT(ActionEvent ticket) throws SQLException {
        
  
      if (PrixT.getText().isEmpty()) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }
         if (Integer.parseInt(PrixT.getText()) > 50) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Please enter a prix  maximum 50 .");
        alert.showAndWait();
        return;
    }
      
      
      else{
             ServiceEvent se = new ServiceEvent();
             int idev = se.Selectid(TickeComb.getValue().toString()).getIdEv();
             System.out.println(idev);
            Ticket p = new Ticket(Integer.parseInt(PrixT.getText()), idev,TickeComb.getValue().toString());
       
            
             ServiceTicket st = new ServiceTicket();
             getTicket();
            
            try {
                st.createOne(p);
                
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("ghalta kbira");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("ticket add");
            alert.setContentText("ticket added successfully!");
            alert.showAndWait();
            Table.refresh();
      
    }

    @FXML
    private void choisirticket(MouseEvent event) {
        Ticket t1 = Table.getItems().get(Table.getSelectionModel().getSelectedIndex());
        idt =t1.getIdT();
       currentt=t1;
      PrixT.setText(Integer.toString(t1.getPrixT()));
      TickeComb.setValue(t1.getNomEv());
    }

    @FXML
    private void btnEventMa(ActionEvent event) {
        
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceEvent.fxml"));
            Stage stage = (Stage) EventMa.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void btnUser(ActionEvent event) {
        
        
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Admin.fxml"));
            Stage stage = (Stage) user.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
        
        
        
                         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();

    }
}
