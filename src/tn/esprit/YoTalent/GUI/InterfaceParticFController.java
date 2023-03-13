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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.YoTalent.utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class InterfaceParticFController implements Initializable {
    @FXML
    private Button deconnecter;
    @FXML
    private Button ListeVoteParIdU;
    @FXML
    private Button InterV;
    @FXML
    private Button afficherEV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deconnecter(ActionEvent event) throws IOException{
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

    private void retour(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/YoTalent/GUI/*************.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
    


    @FXML
    private void GoToVoteList(ActionEvent event) throws IOException {
  //todo
          try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceVoteF.fxml"));
            Stage stage = (Stage) afficherEV.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToVote(ActionEvent event) throws IOException {
   
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/InterfaceVotevF.fxml"));
            Stage stage = (Stage) afficherEV.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void GoToEvent(ActionEvent event) throws IOException {
       
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageEvent.fxml"));
            Stage stage = (Stage) afficherEV.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfacehomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


 
    }

    
    

