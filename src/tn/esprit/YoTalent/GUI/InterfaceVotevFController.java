/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class InterfaceVotevFController implements Initializable {
    @FXML 
    private Button retour; 
    @FXML
    private Button afficherET;
    @FXML
    private Button deconnecter;
    @FXML
    private Button Theatre;
    @FXML
    private Button Musique;
    @FXML
    private Button Danse;
    @FXML
    private Button Littéraire;
    @FXML
    private Button Design;
    @FXML
    private Button Peinture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherET(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/LoginFXML.fxml"));
            Stage stage = (Stage) deconnecter.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ToTheatre(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceCateg.fxml"));
            Stage stage = (Stage) Theatre.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void ToMusique(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceCateg1Music.fxml"));
            Stage stage = (Stage)  Musique.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ToDanse(ActionEvent event) {
        
            try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceCateg2Dance.fxml"));
            Stage stage = (Stage)  Danse.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void ToLittéraire(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceCateg3Lite.fxml"));
            Stage stage = (Stage)  Littéraire.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void ToDesign(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceCateg4Design.fxml"));
            Stage stage = (Stage)  Design.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ToPeinture(ActionEvent event) {
                  try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceCateg5Peint.fxml"));
            Stage stage = (Stage)  Peinture.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      @FXML
    private void retour(ActionEvent event) {
        
           try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceParticF.fxml"));
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
