/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.swing.UIManager.getString;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.entities.Video;
import tn.esprit.YoTalent.utils.MaConnexion;
import tn.esprit.YoTalent.utils.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class InterfaceCategController implements Initializable {
    @FXML
    private Button afficherET;
    @FXML
    private Button deconnecter;
    @FXML
    private MediaView Vid;
    @FXML
    private RadioButton voted;
    @FXML
    private MediaView Vid1;
    @FXML
    private RadioButton voted1;
    @FXML
    private MediaView Vid2;
    @FXML
    private RadioButton voted2;
    @FXML
    private MediaView Vid3;
    
    @FXML
    private RadioButton voted3;
    @FXML
    private MediaView Vid4;
    
    @FXML
    private RadioButton voted4;
    @FXML
    private MediaView Vid5;
 
    @FXML
    private RadioButton voted5;
    @FXML
    private Button voter;
    private Connection con = MaConnexion.getInstance().getCnx();
    ResultSet rs; 
    public static ArrayList<Video> videoList = new ArrayList();
    public static int index = 0; 
    public static boolean fromBack = false ; 
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer mediaPlayer4;
    private MediaPlayer mediaPlayer5;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        voted.setToggleGroup(tg);
        voted1.setToggleGroup(tg);
        voted2.setToggleGroup(tg);
        voted3.setToggleGroup(tg);
        voted4.setToggleGroup(tg);
        voted5.setToggleGroup(tg);
    
     try {
            rs = con.createStatement().executeQuery("select * from video WHERE IdCat ='1'");
            if (index == 0) {
                rs.first();
            }
            afficherListe();
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceCategController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
  //  tg.selectedToggleProperty().addListener(( newVal) -> System.out.println(newVal + " was selected"));

    
    }    

    public void afficherListe() throws SQLException {
        for (int i =0 ; i <6 ; i++){
            rs.next();
        Video myvid = new Video(rs.getString("nomVid"),rs.getString("url"));
        videoList.add(myvid);
        }
                afficherListe1(videoList);

     
    }
    public void afficherListe1(ArrayList<Video> videoList) {   
         System.out.println(index);
         String mediaPath = videoList.get(index).getUrl();
         Media media = new Media(mediaPath);
         mediaPlayer = new MediaPlayer(media);
         Vid.setMediaPlayer(mediaPlayer);
         voted.setText(videoList.get(index).getNomVid());
        
         
         //C:/Users/hamza/OneDrive/Bureau/borgH/Tocheck/YYotalentT/YYotalentT/src/tn/esprit/YoTalent/GUI/images/vid1.mp4
         
         
         
         index++;
         String mediaPath1 = videoList.get(index).getUrl();
         Media media1 = new Media(mediaPath1);
         mediaPlayer1 = new MediaPlayer(media1);
         Vid1.setMediaPlayer(mediaPlayer1);
                  voted1.setText(videoList.get(index).getNomVid());


         

         index++;
         mediaPath = videoList.get(index).getUrl();
          media = new Media(mediaPath);
         mediaPlayer2 = new MediaPlayer(media);
         Vid2.setMediaPlayer(mediaPlayer2);
                 voted2.setText(videoList.get(index).getNomVid());

        index++;
         mediaPath = videoList.get(index).getUrl();
          media = new Media(mediaPath);
         mediaPlayer3 = new MediaPlayer(media);
         Vid3.setMediaPlayer(mediaPlayer3);
                  voted3.setText(videoList.get(index).getNomVid());

         
         index++;
         mediaPath = videoList.get(index).getUrl();
          media = new Media(mediaPath);
         mediaPlayer4 = new MediaPlayer(media);
         Vid4.setMediaPlayer(mediaPlayer4);
                 voted4.setText(videoList.get(index).getNomVid());

         
         index++;
         mediaPath = videoList.get(index).getUrl();
          media = new Media(mediaPath);
         mediaPlayer5 = new MediaPlayer(media);
         Vid5.setMediaPlayer(mediaPlayer5);
                 voted5.setText(videoList.get(index).getNomVid());


         
         fromBack = false;
       
         
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
    private void voter(ActionEvent event) throws SQLException {
        
        ToggleGroup tg = new ToggleGroup();
        voted.setToggleGroup(tg);
        voted1.setToggleGroup(tg);
        voted2.setToggleGroup(tg);
        voted3.setToggleGroup(tg);
        voted4.setToggleGroup(tg);
        voted5.setToggleGroup(tg);
        
 tg.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
     if (newVal != null){
     String  data = newVal.getUserData().toString();
 System.out.println(newVal + " was selected");  

    }
 
 
 
 
 
 });
        
                

        
        UserSession userSession = UserSession.getInstace(null);
        User u = userSession.getUser();
        
        String req =   "INSERT INTO `vote`(`idU`, `nomVid`)" + "VALUES ('"+u.getId()+"','"+5+"')";
        Statement st = con.createStatement();
        st.executeUpdate(req);
        
        
       
        System.out.println("Vote ajouté !");
        System.out.println(tg.getSelectedToggle().toString().intern());
        
        
        
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            
            
            tray.setAnimationType(type);
            tray.setTitle("Vote ajouté !");
            tray.setMessage("Nice To have your vote for !!" + tg.getSelectedToggle().toString().intern() );
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.showAndDismiss(Duration.millis(3000));
        
        
    }
    
}
