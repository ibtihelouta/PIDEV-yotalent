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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.esprit.YoTalent.entities.Ticket;
import tn.esprit.YoTalent.services.ServiceTicket;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InterfaceTicketController implements Initializable {

    @FXML
    private ImageView col2;
    @FXML
    private TextField IdT;
    @FXML
    private TextField PrixT;
    @FXML
    private TextField IdEv;
    @FXML
    private Button ModifyT;
    @FXML
    private TextField delete;
    @FXML
    private Button DeleteT;
    @FXML
    private Button AddT;
    @FXML
    private TableView<Ticket> Table;
    ServiceTicket Tt=new ServiceTicket();
     Ticket t = new Ticket();
    @FXML
    private TableColumn<Ticket, Integer> Col1;
    @FXML
    private TableColumn<Ticket, Integer> Col2;
    @FXML
    private TableColumn<Ticket, Integer> Col3;
    ObservableList<Ticket> tickets ;
      private boolean isLightMode =true;

      
      
      @FXML
    private TableView<Ticket> Display;
  //  ServiceEST es=new ServiceEST();
       

 ServiceTicket st = new ServiceTicket();
  
     Ticket s = new Ticket();
     ObservableList<Ticket> ticket ;
      

    
     
     
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
        
    } 
      @FXML
  
    
    public void getTicket() throws SQLException {
     
       ;
    
    // Col1.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("idT"));
   Col2.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("prixT"));
   Col3.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("idEv"));
  
    
    ServiceTicket Tt=new ServiceTicket();
       tickets= Tt.FetchTickets();
       
        System.out.println(tickets);
        Table.setItems(tickets);
    }

    @FXML
    private void btnModifyT(ActionEvent ticket) {
        
        
         try{
     
        
        Ticket up=new   Ticket (Integer.valueOf(this.IdT.getText()),Integer.valueOf(this.PrixT.getText()),Integer.valueOf(this.IdEv.getText()));
          ServiceTicket Tt=new ServiceTicket();
         Tt.updateOne(up);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Ticket update");
            alert.setContentText("Ticket updated successfully!");
            alert.showAndWait();
            //getContrat();
        } catch(Exception Tt){
            System.out.println("fama ghalta2");
        }
       PrixT.clear();
       IdEv.clear();
     
        
       
    }

    @FXML
    private void btDeleteT(ActionEvent ticket) {
        
             
    
       
        
        
        
    }

    @FXML
    private void btnAddT(ActionEvent ticket) throws SQLException {
        
  
      if (PrixT.getText().isEmpty() 
                ||   IdEv.getText().isEmpty() 
             ) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else{
            Ticket p = new Ticket(Integer.parseInt(PrixT.getText())
                    
                    , Integer.parseInt(IdEv.getText())
          );
            
             ServiceTicket st = new ServiceTicket();
             getTicket();
            
            try {
                st.createOne(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
    }}
