/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.esprit.YoTalent.GUI;


import com.itextpdf.text.BaseColor;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfDocument;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.Table;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import tn.esprit.YoTalent.entities.Contrat;
import tn.esprit.YoTalent.services.ServiceContrat;
import javafx.scene.control.Label;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Element;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.entities.Participation;
import tn.esprit.YoTalent.services.ServicePlanning;
import tn.esprit.YoTalent.services.ServiceEvent;
import tn.esprit.YoTalent.utils.MaConnexion;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import javafx.scene.text.TextAlignment;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sun.rmi.transport.Transport;
import tn.esprit.YoTalent.entities.EspaceTalent;



/**
 * FXML Controller class
 *
 * @author ASMA
 */

public class InterfaceContratController implements Initializable {
//@FXML
//    private Pane paneNew;
       
   
  
//    @FXML
//    private JFXTimePicker Hour;
     @FXML
    private TextField idC;
    @FXML
    private TextField NomC;
   
    @FXML
    private DatePicker DateDC;
   @FXML
    private DatePicker DateFC;
    @FXML
    private TableView<Contrat> afficherContrat;
    ServiceContrat es=new ServiceContrat();
     Contrat e = new Contrat();
    @FXML
    private Button deleteContrat;
    @FXML
    
    private ImageView BackP;
    @FXML
    private Button addContrat;
@FXML
    private Button bTri;


    
     //k
//      @FXML
//    private TableColumn<Contrat, Integer> colId;
    @FXML
    private TableColumn<Contrat, String> colNomC;
    @FXML
    private TableColumn<Contrat, String> colDateDC;
     @FXML
    private TableColumn<Contrat, String> colDateFC;
      ObservableList<Contrat> Contrat ;
     
 
    
      private boolean isLightMode =true;
      java.sql.Timestamp timestamp = null;
    //private ComboBox<Integer> EventCombo;

    @FXML
    private TextField Recherche;
//    @FXML
//    private Button Trie;
//    @FXML
//    private Button QRcode;
//    @FXML
//    private Label nom;

private int id;
    @FXML
    private ImageView leftC;
    @FXML
    private Button ExitC;
    @FXML
    private Button UpdateContrat;
    @FXML
    private Button pdf;


//    @FXML
//    private Button ExitC ; 
  
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        
          afficherContrat.setFocusTraversable(false);
        try {
            getContrat();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceContratController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
      }    
    
    
    //debut code ibt
    @FXML
    public void getContrat() throws SQLException {
    
    
     colNomC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("NomC"));
   colDateDC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("DateDC"));
   colDateFC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("DateFC"));
  
  ServiceContrat es=new ServiceContrat();
       Contrat= es.Fetchc();
        System.out.println(Contrat);
        afficherContrat.setItems(Contrat); 
    }
   
  
   
   private boolean NoDate() {

         LocalDate currentDate = LocalDate.now();
         
         LocalDate myDate = DateDC.getValue(); 
         
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
        
            LocalDate date = DateDC.getValue();
               //LocalDate date2 = DateFC.getValue();
            
    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

   
    
       
       String NomContrat;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((NomC.getText().length()==0))
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
            NomContrat = String.valueOf(NomC.getText());
          
        }catch(Exception exc){
            System.out.println("name exception");
            return;
        }  
          
        } 
         
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Contrat update");
            alert.setContentText("Contrat updated successfully!");
            alert.showAndWait();
            
            
           
            try{
     
         Date DateD = Date.valueOf(this.DateDC.getValue());
            Date DateDF = Date.valueOf(this.DateFC.getValue());
         
        
      
          Contrat e1 = afficherContrat.getItems().get(afficherContrat.getSelectionModel().getSelectedIndex());
//  System.out.println((Integer.valueOf(this.idC.getText())));
    System.out.println(NomC.getText());
      System.out.println(String.valueOf(DateD));
        System.out.println(String.valueOf(DateDF));
             Contrat up =new Contrat(e1.getIdC(),NomC.getText(),String.valueOf(DateD),String.valueOf(DateDF));
            System.out.println("blabla");
        System.out.println(up);
            es.updateOne(up);
            getContrat();
        } catch(Exception ex){
            System.out.println("erreur");
            System.out.println(ex);
            ex.printStackTrace();
        }
       NomC.clear();
       DateDC.setValue(null);
       DateFC.setValue(null);
       //EventCombo.setValue(null);
       //EventCombo.getValue();
       
        
       
     
    
       
 
       
           
    } 
        
        
        
        
    


//    @FXML
//    private void btnDeleteDL(ActionEvent event) throws SQLException {
//        
//          Contrat e1 = afficherContrat.getItems().get(afficherContrat.getSelectionModel().getSelectedIndex());
//      
//        try {
//            es.deletOne(e1);
//            idI.setText("");
//        } catch (SQLException ex) {
//            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information ");
//            alert.setHeaderText("Event delete");
//            alert.setContentText("Event deleted successfully!");
//            alert.showAndWait();
//         getPlanning();
//       
//        
//        
//        
//        
//        
//    }
//    
    
      @FXML
    private void supprimer() throws SQLException {
          Contrat e1 = afficherContrat.getItems().get(afficherContrat.getSelectionModel().getSelectedIndex());
      
        try {
            es.deletOne(e1);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceContratController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText(" Contrat delete");
            alert.setContentText(" Contrat deleted successfully!");
            alert.showAndWait();
         getContrat();
    }


    @FXML
    private void btnAddPL() throws SQLException {
       // Evenement Event = EventCombo.getValue();
        
         LocalDate date = DateDC.getValue();
    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

  
    //String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       
       String NomContrat;
        //BeginsAtdate.setValue(LocalDate.now());
         if ((NomC.getText().length()==0))
                { Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    alert.showAndWait();}
         
         
         
           if ((NomC.getText().length() > 20) ) {
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
          NomContrat = String.valueOf(NomC.getText());
        
      }catch(Exception exc){
           System.out.println("name exception");
          return;
      }  
        
      } 
         
            Contrat Ps=new Contrat(NomC.getText(),String.valueOf(DateDC.getValue()),String.valueOf(DateFC.getValue()));
                   es.createOne(Ps);
         getContrat();
       
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Contrat add");
            alert.setContentText("Contrat added successfully!");
            alert.showAndWait();
         }
    
        
    
    
    
    


        
       void choisirPlanning(MouseEvent event) {
        
          
      Contrat c = afficherContrat.getItems().get(afficherContrat.getSelectionModel().getSelectedIndex());
        
        //idLabel.setText(String.valueOf(e.getId_event()));
       idC.setText(String.valueOf(c.getIdC()));
          DateDC.setValue(LocalDate.parse(c.getDateDC()));
           DateFC.setValue(LocalDate.parse(c.getDateFC()));
        NomC.setText(c.getNomC());
      // this.id=c.setIdP(idP);
         this.id=c.getIdC();
      

    } 

     @FXML
    private void Recherche(KeyEvent event) throws SQLException {
        
        
        
         Contrat p=new Contrat();
ServiceContrat sc = new ServiceContrat();
//afficherPL.setItems(sp.searchByPlanning(Recherche.getText()))  ;
afficherContrat.setItems(sc.searchBycontrat(Recherche.getText()));
//searchByContrat(Recherche.getText()
//       afficherContrat.setItems(sc.Fetchc())  ;
    }

     @FXML
    private void btnTrieparNom(ActionEvent event) throws SQLException {
        
         colNomC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("NomC"));
   colDateDC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("DateDC"));
   colDateFC.setCellValueFactory(new PropertyValueFactory<Contrat,String>("DateFC"));
       
        
        
       
         Contrat=es.getAllTriNom();
             afficherContrat.setItems(Contrat);
    }

  
///////////////// pdf/////////////////
//     @FXML
//    public void ActionPerformed(ActionEvent arg0) throws DocumentException, IOException{
//       
//    Document doc =new Document();
//   String requet="Select nomC=?,DateDC=?, DateFC=?  where id=? ";
//////   String sql ="select * from contrat";
//////       
//////try {
//////prepared =cnx.prepareStatement(sql);
//////resultat =prepared.executeQuery();
//////}
//////catch(SQLException) el{
//////el.printStackTrace();
//////}
//        try {
//           PdfWriter.getInstance(doc , new FileOutputStream("C://Users//ASMA//Desktop//My Project//Contrat.pdf"));
//           // PdfWriter.getInstance(doc , new FileOutputStream(""));
//            doc.open();
//            
//            
//            
//                doc.add(new Paragraph(" "));
//                doc.add(new Paragraph("Contrat"));
//                 doc.add(new Paragraph(" "));
//             
//              PdfPTable table =new PdfPTable(3);
//              table.setWidthPercentage(80);
//              PdfPCell cell;
//         
//              
//              cell =new PdfPCell (new Phrase("Nom",FontFactory.getFont("Comic sans MS",10)));
//              //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//              
//              cell.setBackgroundColor(BaseColor.GRAY);
//             
//              table.addCell(cell);
//              
//              
//              cell =new PdfPCell (new Phrase("Date Debut",FontFactory.getFont("Comic sans MS",10)));
//              //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//              cell.setBackgroundColor(BaseColor.GRAY);
//              table.addCell(cell);
//              
//              cell =new PdfPCell (new Phrase("Date Fin",FontFactory.getFont("Comic sans MS",10)));
//              //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//              cell.setBackgroundColor(BaseColor.GRAY);
//              table.addCell(cell);
//              
//              ////////////////////LES AUTRES c
//              
//              cell =new PdfPCell (new Phrase(NomC.getText().toString(),FontFactory.getFont("Comic sans MS",10)));
//              //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//              cell.setBackgroundColor(BaseColor.GRAY);
//              table.addCell(cell);
//              
//              
////////                  cell =new PdfPCell (new Phrase(dateD.getText().toString(),FontFactory.getFont("Comic sans MS",10)));
////////  //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////////              cell.setBackgroundColor(BaseColor.GRAY);
////////              table.addCell(cell);
////////              
////////               
////////                 cell =new PdfPCell (new Phrase(DateFC.getText().toString(),FontFactory.getFont("Comic sans MS",10)));
////////               // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////////              cell.setBackgroundColor(BaseColor.GRAY);
////////              table.addCell(cell);
////////              
//              
//
//              
//               doc.add(table);
//              
//               
//               
//            doc.close();
//               Desktop.getDesktop().open(new File("C://Users//ASMA//Desktop//My Project//Contrat.pdf"));
//            //Desktop.getDesktop().open(new File("C:\\Users\\.pde"));
//            System.out.println("Telechargement terminne!");
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(InterfaceContratController.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        catch (SQLException ex) {
////            Logger.getLogger(PenalitesJoueursController.class.getName()).log(Level.SEVERE, null, ex);
////        }
//    }
//    //PDF
//    
    
    //fin code ibt

    

    @FXML
    private void gotovoy(MouseEvent event) throws IOException {
        
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceVoyage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
private void Retour2(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("mail.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}

    @FXML
    private void ActionPerformed(ActionEvent event) {
    }
    
    
    
    }
    




