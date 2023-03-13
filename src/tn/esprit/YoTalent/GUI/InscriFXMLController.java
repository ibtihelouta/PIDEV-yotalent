/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.YoTalent.entities.User;
import tn.esprit.YoTalent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InscriFXMLController implements Initializable {

    @FXML
    private TextField image;
    @FXML
    private Button uploadbutton;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private ComboBox<String> comborole;
    @FXML
    private ImageView img;
    ObservableList<String> listRole = FXCollections.observableArrayList("talentueux","société","utilisateur","participant");
    @FXML
    private Label filepath;

    ServiceUser su = new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comborole.setItems(listRole);
    }    

    @FXML
    private void uploadIMG(ActionEvent event) {
                FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(uploadbutton.getScene().getWindow());

        image.setText(file.getName());
        filepath.setText(file.getPath());
        File filee = new File(file.getPath());

        Image image = new Image(filee.toURI().toString());
        img.setImage(image);

    }

    @FXML
    private void sinsicire(ActionEvent event) throws SQLException, IOException {
        
        if(filepath.getText()!="")
        {
         File f = new File(filepath.getText());
        User d = new User(nom.getText(),email.getText(),mdp.getText(),comborole.getSelectionModel().getSelectedItem().toString(),image.getText());
        su.createOne(d); 

         Files.copy(Paths.get(filepath.getText()),Paths.get("C:\\Users\\Windows\\Desktop\\YYotalent\\YYotalent\\src\\tn\\esprit\\YoTalent\\GUI\\images\\"+image.getText()),REPLACE_EXISTING);
        } 
        else
        {
        User d = new User(nom.getText(),email.getText(),mdp.getText(),comborole.getSelectionModel().getSelectedItem().toString(),"user.png");
        su.createOne(d); 
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
                         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();


    }
    
}
