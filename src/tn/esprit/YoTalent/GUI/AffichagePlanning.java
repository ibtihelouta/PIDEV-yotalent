/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.entities.Planning;
import tn.esprit.YoTalent.services.ServiceEvent;
import tn.esprit.YoTalent.services.ServicePlanning;
import tn.esprit.YoTalent.utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichagePlanning implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;

private int row = 0;
    private int col = 0;
    @FXML
    private ImageView BackPA;
    @FXML
    private Label adresse;
    @FXML
    private Button Acheter;
    @FXML
    private Button deconnecter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        adresse.setText(InterfacehomeController.currentevent.getLocalisation());
        gridPane= new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        ServiceEvent se =new ServiceEvent();
        ServicePlanning sp = new ServicePlanning();
        List<Planning> events = new ArrayList<>();
        BackPA.setVisible(true);
        try {
            events = sp.searchByevent(InterfacehomeController.id);
        //events = sp.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(events);
        for (Planning e : events){
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPane.fxml"));
            try {
                Node node = fxmlLoader.load();
                MypaneController controller = fxmlLoader.getController();
                controller.setNom(e.getHour());
                controller.setdated(e.getNomActivite());
                controller.setdatef(e.getDatePL());
                //String 
               /* String imageUrl =e.getImage();
                
                  // l'URL de l'image à charger
                 if(imageUrl.equals("")){
                     imageUrl="C:\\Users\\ASUS\\Pictures\\Screenshots\\a.png";
                 }
                Image image = new Image(new File(imageUrl).toURI().toString());
                controller.setImage(image);*/
node.setStyle("-fx-background-color:#ffd53d");
                /*node.setOnMouseClicked(event -> {
                    // Action à effectuer lorsqu'un MyPane est cliqué
                 
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherSousServiceVitrine.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        AffichagePlanningController controller1 = loader.getController();
                        //controller1.setServiceID(i.getService_id());

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) mybutton.getScene().getWindow();
                        stage.setTitle("ajouter Sous Service");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagePlanningController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });*/
                // Alternance de couleurs de fond
              

                // Ajoutez le nœud au GridPane en spécifiant la position de chaque nœud
                gridPane.add(node, col, row);

                // Incrémente la colonne pour la prochaine instance de MyPane
                col++;

                // Si on atteint la troisième colonne, retourne à la première colonne et incrémente la ligne
                if (col > 2) {
                    col = 0;
                    row++;
                }
            } catch (IOException ex) {
                Logger.getLogger(MypaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     HBox   hbox = new HBox(gridPane);
    hbox.setAlignment(Pos.CENTER);

    // Définir la HBox comme le contenu du ScrollPane
    scrollPane.setContent(hbox);
    }    

    @FXML
    private void handleBackArrowImageClickPA(MouseEvent event) throws IOException {
        
         Parent previousScene = FXMLLoader.load(getClass().getResource("AffichageEvent.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show(); 
        
        
    }

    @FXML
    private void btnAcheter(ActionEvent event) {
        
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/espace.fxml"));
            Stage stage = (Stage) Acheter.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
