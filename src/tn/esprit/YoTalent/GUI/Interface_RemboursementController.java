/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Interface_RemboursementController implements Initializable {

    @FXML
    private TextField IdR;
    @FXML
    private TextField DateR;
    @FXML
    private TextField PrixT;
    @FXML
    private Button ModifyR;
    @FXML
    private TableView<?> Table;
    @FXML
    private TableColumn<?, ?> col1;
    @FXML
    private TableColumn<?, ?> col2;
    @FXML
    private TableColumn<?, ?> col3;
    @FXML
    private TextField delete;
    @FXML
    private Button DeleteR;
    @FXML
    private Button AddTr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnModifyR(ActionEvent event) {
    }

    @FXML
    private void btnDeleteR(ActionEvent event) {
    }

    @FXML
    private void btnAddR(ActionEvent event) {
    }
    
}
