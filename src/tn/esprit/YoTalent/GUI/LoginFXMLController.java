/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.SendMail;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.services.ServiceUser;
import tn.esprit.YoTalent.utils.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    ServiceUser su = new ServiceUser();
    @FXML
    private AnchorPane content;
    @FXML
    private Label msg;
    @FXML
    private TextField usernameF;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
 static String RandGeneratedStr(int l)

 {

 // a list of characters to choose from in form of a string

 String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

 // creating a StringBuffer size of AlphaNumericStr

 StringBuilder s = new StringBuilder(l);

 int i;

 for ( i=0; i<l; i++) {

   //generating a random number using math.random()

   int ch = (int)(AlphaNumericStr.length() * Math.random());

   //adding Random character one by one at the end of s

   s.append(AlphaNumericStr.charAt(ch));

  }

    return s.toString();

 }


    @FXML
    private void connect(ActionEvent event) throws IOException {
        
        if((username.getText().equals("admin")) && (password.getText().equals("admin")))
        {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Admin.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Admin Dashboard");
                    
                    stage.show();

        }
        else{
        User u =  su.Login(username.getText(), password.getText());

        if(u == null){
            msg.setText("mot de passe incorrect");
        }
        else
        {
                        
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene ;
                                
                    UserSession userSession = UserSession.getInstace(u);

            switch(u.getRole())
            {
                case "talentueux" :
                               
                    scene = new Scene(FXMLLoader.load(getClass().getResource("Admin.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("talentueux Dashboard");
                    
                    stage.show();

                    break;
                case "société" :
                                    
                    scene = new Scene(FXMLLoader.load(getClass().getResource("InterfaceVoyage.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("société Dashboard");
                    
                    stage.show();
                    
                    break;
                case "utilisateur" :
                                     
                    scene = new Scene(FXMLLoader.load(getClass().getResource("Admin.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("utilisateur Dashboard");
                    
                    stage.show();
                    break;
                case "participant" :
                                       
                    scene = new Scene(FXMLLoader.load(getClass().getResource("InterfaceParticF.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("participant Dashboard");
                    
                    stage.show();
                    break;
            }
            
                            
            


        }
        }
        
    }

    @FXML
    private void sinscrire(ActionEvent event) throws IOException {

                 Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InscriFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Inscri");
                    
                    stage.show();


    }

    @FXML
    private void envoyer(ActionEvent event) throws SQLException {
        if(!usernameF.getText().isEmpty())
        {
           User u = su.GetByEmail(usernameF.getText());
           
           u.setMotpass(RandGeneratedStr(10));
           su.updateOne(u);
           SendMail.sendMail(u.getEmail(),"Recuperer mot de passe","Votre nouveau mdp : "+u.getMotpass());
        }
    }
    
}
