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
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.services.ServiceUser;
import tn.esprit.YoTalent.utils.MaConnexion;
import tn.esprit.YoTalent.utils.UserSession;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AdminController implements Initializable {

    @FXML
    private Button GestionEST;
    @FXML
    private Button deconnecter;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField role;
    @FXML
    private TextField image;
    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TableColumn<User, Integer> iduserT;
    @FXML
    private TableColumn<User, String> nomT;
    @FXML
    private TableColumn<User, String> emailT;
    @FXML
    private TableColumn<User, String> roleT;
    @FXML
    private TableColumn<User, ImageView> imageT;
    @FXML
    private TextField search;

    
    private Statement ste;
    private Connection con;
    private final ObservableList<User> data = FXCollections.observableArrayList();
    @FXML
    private ImageView imgtmp;
    @FXML
    private Label filepath;
    @FXML
    private Button uploadbutton;

    ServiceUser su = new ServiceUser();
    @FXML
    private Label welcomemsg;
    @FXML
    private ImageView imgprofile;
    @FXML
    private ImageView generateImg;
    @FXML
    private Button Event;
    @FXML
    private Button Ticket;
    @FXML Button vt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserSession userSession = UserSession.getInstace(null);

        User u = userSession.getUser();
     //   welcomemsg.setText("Welcome "+u.getNom());
        
     //  File file = new File("C:\\Users\\hamza\\OneDrive\\Bureau\\borgH\\Tocheck\\YYotalentT\\YYotalent\\src\\tn\\esprit\\YoTalent\\GUI\\images\\"+u.getImage());

     //  Image image = new Image(file.toURI().toString());
  //      imgprofile.setImage(image);
        Aff();
    }    

    @FXML
    private void GestionEST(ActionEvent event) {
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
    private void modifyData(ActionEvent event) {
    }

    @FXML
    private void deleteData(ActionEvent event) throws SQLException {
                     tableviewuser.setItems(data);

             ObservableList<User> allUsers,SingleUser ;
             allUsers=tableviewuser.getItems();
             SingleUser=tableviewuser.getSelectionModel().getSelectedItems();
             User A = SingleUser.get(0);
             su.deletOne(A);
             SingleUser.forEach(allUsers::remove);
             Aff();
             RechercheAV();
             TrayNotification tray = new TrayNotification();
             tray.setTitle("Supprimer");
             tray.setMessage("Supprimé avec succès");
             tray.setNotificationType(NotificationType.ERROR);
             tray.showAndWait();
    }

    @FXML
    private void loadData(MouseEvent event) {
        
             ObservableList<User> allUsers,SingleUser ;
             allUsers=tableviewuser.getItems();
             SingleUser=tableviewuser.getSelectionModel().getSelectedItems();
             User A = SingleUser.get(0);
             
             nom.setText(A.getNom());
             email.setText(A.getEmail());
             role.setText(A.getRole());
             image.setText(A.getImage());
             File file = new File("C:\\Users\\Windows\\Desktop\\YYotalent\\YYotalent\\src\\tn\\esprit\\YoTalent\\GUI\\images\\"+A.getImage());
                Image image = new Image(file.toURI().toString());
                
                imgtmp.setImage(image);
                //f.setImageV(imageView);                

    }

    @FXML
    private void uploadIMG(ActionEvent event) {    
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(uploadbutton.getScene().getWindow());

        image.setText(file.getName());
        filepath.setText(file.getPath());
        File filee = new File(file.getPath());

        Image image = new Image(filee.toURI().toString());
        imgtmp.setImage(image);

    }
    
    public void Aff(){
                                try {
            con = MaConnexion.getInstance().getCnx();
            ste = con.createStatement();
            data.clear();

            ResultSet rs = ste.executeQuery("select * from user");
            while(rs.next()){
                User f = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                                
                File file = new File("C:\\Users\\Windows\\Desktop\\YYotalent\\YYotalent\\src\\tn\\esprit\\YoTalent\\GUI\\images\\"+rs.getString(6));
                System.out.println(file.toURI().toString());
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                f.setImageV(imageView);                

                data.add(f);
            }
        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
            iduserT.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<>("nom"));
            emailT.setCellValueFactory(new PropertyValueFactory<>("email"));
            roleT.setCellValueFactory(new PropertyValueFactory<>("Role"));
            imageT.setCellValueFactory(new PropertyValueFactory<>("imageV"));
            tableviewuser.setItems(data);
            RechercheAV();

    }
        public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (user.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                                        return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableviewuser.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableviewuser.setItems(sortedData);
    }
        
        
                public static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private void QRcode(User u) throws FileNotFoundException, IOException {
        String contenue = "Nom : " + u.getNom()+ "\n" + "Email: " + u.getEmail()+ "\n" + "Role: " + u.getRole(); 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\tn\\esprit\\YoTalent\\GUI\\images\\" + u.getId()+ ".jpg");
        System.out.println(f.getPath());
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final
        Image image = new Image(f.toURI().toString());
        generateImg.setImage(image);


     }

    @FXML
    private void Generate(ActionEvent event) throws IOException {
                     ObservableList<User> allUsers,SingleUser ;
             allUsers=tableviewuser.getItems();
             SingleUser=tableviewuser.getSelectionModel().getSelectedItems();
             User A = SingleUser.get(0);

            QRcode(A);
    }

    @FXML
    private void btnEvent(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceEvent.fxml"));
            Stage stage = (Stage) Event.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void btnTicket(ActionEvent event) {
        
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceTicket.fxml"));
            Stage stage = (Stage) Ticket.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    @FXML
    void btnContrat(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceContrat.fxml"));
            Stage stage = (Stage) Ticket.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }

         
         
         
    }

 @FXML
    void vt(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/VoteAdmin.fxml"));
            Stage stage = (Stage) Ticket.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
