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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.services.ServiceEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InterfacehomeController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    private int row = 0;
    private int col = 0;
 public static Evenement currentevent ;   
    @FXML
    private AnchorPane mybutton;
   public static int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gridPane= new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        ServiceEvent se =new ServiceEvent();
        List<Evenement> events = new ArrayList<>();
        try {
            events = se.selectAll(); 
        } catch (SQLException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(events);
        for (Evenement e : events){
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPane.fxml"));
            try {
                Node node = fxmlLoader.load();
                MypaneController controller = fxmlLoader.getController();
                controller.setNom(e.getNomEv());
                controller.setdated(e.getDateDEv());
                controller.setdatef(e.getDateFEv());
                controller.setuser(e.getIdEv());
                //String 
                String imageUrl =e.getImage();
                
                  // l'URL de l'image à charger
                 if(imageUrl.equals("")){
                     imageUrl="C:\\Users\\ASUS\\Pictures\\Screenshots\\a.png";
                 }
                Image image = new Image(new File(imageUrl).toURI().toString());
                controller.setImage(image);
node.setStyle("-fx-background-color:#ffd53d");
                node.setOnMouseClicked(event -> {
                    // Action à effectuer lorsqu'un MyPane est cliqué
                 this.id=e.getIdEv();
                 currentevent=e;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichagePlanning (2).fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        AffichagePlanning controller1 = loader.getController();
                         
                        //controller1.setServiceID(i.getService_id());

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) mybutton.getScene().getWindow();
                        stage.setTitle("ajouter Sous Service");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagePlanning.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
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
    
   
   
}
