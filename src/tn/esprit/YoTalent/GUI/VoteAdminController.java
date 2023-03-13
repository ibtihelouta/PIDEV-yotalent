/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeString.search;
import net.glxn.qrgen.QRCode;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import static tn.esprit.YoTalent.GUI.AdminController.projectPath;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.entities.Vote;
import tn.esprit.YoTalent.services.ServiceVote;
import tn.esprit.YoTalent.utils.MaConnexion;
import tn.esprit.YoTalent.utils.UserSession;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class VoteAdminController implements Initializable {
    @FXML
    private Button GestionEST;
    @FXML
    private Button cont;
    @FXML
    private Button Event;
    @FXML
    private Button Ticket;
    @FXML
    private Button deconnecter;
    @FXML
    private TableView<Vote> tableviewuser;
    @FXML
    private TableColumn<Vote, Integer> idVote;
    @FXML
    private TableColumn<Vote, Integer> idUser;
    @FXML
    private TableColumn<Vote, Integer>dateVote;
    @FXML
    private TableColumn<Vote, Integer>vid;
    
    @FXML
    private Label welcomemsg;
    @FXML
    private ImageView imgprofile;
    @FXML
    private ImageView generateImg;
 @FXML
    private TextField search;
 ServiceVote su = new ServiceVote();
    private Statement ste;
    private Connection con;
    private final ObservableList<Vote> data =FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 UserSession userSession = UserSession.getInstace(null);

        User u = userSession.getUser();
//        welcomemsg.setText("Welcome "+u.getNom());
        
   //    File file = new File("C:\\Users\\hamza\\OneDrive\\Bureau\\borgH\\Tocheck\\YYotalentT\\YYotalent\\src\\tn\\esprit\\YoTalent\\GUI\\images\\"+u.getImage());

    //   Image image = new Image(file.toURI().toString());
     //   imgprofile.setImage(image);
        Aff();    }    

    public void Aff(){
         try {
            con = MaConnexion.getInstance().getCnx();
            ste = con.createStatement();
            data.clear();

            ResultSet rs = ste.executeQuery("select * from vote");
             while(rs.next()){
           Vote f = new Vote(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                         

                data.add(f);
            }
        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
            idVote.setCellValueFactory(new PropertyValueFactory<>("idV"));
            idUser.setCellValueFactory(new PropertyValueFactory<>("idU"));
            dateVote.setCellValueFactory(new PropertyValueFactory<>("dateV"));
            vid.setCellValueFactory(new PropertyValueFactory<>("nomVid"));
            tableviewuser.setItems(data);
               

    
    }
          
        
    
    @FXML
    private void GestionEST(ActionEvent event) {
    }

    @FXML
    private void btnContrat(ActionEvent event) {
    }

    @FXML
    private void btnEvent(ActionEvent event) {
    }

    @FXML
    private void btnTicket(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        
                    UserSession userSession = UserSession.getInstace(null);
                    userSession.cleanUserSession();
                          
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();

    }

    @FXML
    private void loadData(MouseEvent event) {
    }

    
                    public static String projectPath = System.getProperty("user.dir").replace("\\", "/");

     private void QRcode(Vote u) throws FileNotFoundException, IOException {
        String contenue = "idV : " + u.getIdV()+ "\n" + "dateV: " + u.getDateV()+ "\n" + "idV: " + u.getIdV(); 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\tn\\esprit\\YoTalent\\GUI\\images\\" + u.getIdV()+ ".jpg");
       System.out.println(f.getPath());
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final
        Image image = new Image(f.toURI().toString());
        generateImg.setImage(image);


     }
    
    
    
    @FXML
    private void Generate(ActionEvent event) throws IOException {
                 ObservableList<Vote> allUsers,SingleUser ;
             allUsers=tableviewuser.getItems();
             SingleUser=tableviewuser.getSelectionModel().getSelectedItems();
             Vote A = SingleUser.get(0);

            QRcode(A);
    }

    @FXML
    private void deleteData(ActionEvent event) throws SQLException {
        
              tableviewuser.setItems(data);

             ObservableList<Vote> allUsers,SingleUser ;
             allUsers=tableviewuser.getItems();
             SingleUser=tableviewuser.getSelectionModel().getSelectedItems();
             Vote A = SingleUser.get(0);
             su.deletOne(A);
             SingleUser.forEach(allUsers::remove);
             Aff();
            
             TrayNotification tray = new TrayNotification();
             tray.setTitle("Supprimer");
             tray.setMessage("Supprimé avec succès");
             tray.setNotificationType(NotificationType.ERROR);
             tray.showAndWait();
        
    }
    
}
