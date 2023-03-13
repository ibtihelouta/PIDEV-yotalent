/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class MailController implements Initializable {

    @FXML
    private TextField maail;

    @FXML
    public  void Mail( ) throws MessagingException, SQLException{
    String m=maail.getText();
 
  sendMail(m);
    
    
}
     
     public static void sendMail(String recepient) throws MessagingException{
        System.out.println("Prepared to send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.ssl.trust","*");

        String myEmailAccount="glanzaasma@gmail.com";
        String password="ixyqqtmgdedrlieo";
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myEmailAccount,password);
                
            }
        });
        
        Message message=prepareMessage(session,myEmailAccount,recepient);
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
        private static Message prepareMessage(Session session, String myEmailAccount,String recepient){
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reponse quicklines");
            message.setText("Bonjour cher(e) client(é),\n Votre reponse est le ! \n Bienvenu(e) parmi nous 🙂");
            return message;
                    } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private void gotocon(MouseEvent event) throws IOException {
        
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceContrat.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
