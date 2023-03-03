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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.Evenement;
import tn.esprit.YoTalent.services.ServiceEvent;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MypaneController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private TextField date_d;
    @FXML
    private Label nom;
    @FXML
    private TextField date_fin;
    @FXML
    private AnchorPane plannig_button;
    public static Evenement currentevent ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setNom(String n){
        this.nom.setText(n);
    }
    public void setdated(String dd){
        this.date_d.setText(dd);
    }
    public void setdatef(String df){
        this.date_fin.setText(df);
    }
    public void setImage(Image image){
        this.img.setImage(image);
        img.setFitWidth(200);
        img.setFitHeight(300);
        //img.setRotate(90);
    }
    public void setuser(int id){
        ServiceEvent se = new ServiceEvent();
        currentevent = se.SelectOneEvent(id);
    }

    @FXML
    private void showplanning(MouseEvent event) throws IOException {
       Parent nextScene = FXMLLoader.load(getClass().getResource("AffichagePlanning (2).fxml"));
    Scene scene = new Scene(nextScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    
}
}
