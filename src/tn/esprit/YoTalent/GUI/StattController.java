/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.YoTalent.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import tn.esprit.YoTalent.services.ServiceVoyage;




/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class StattController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;
@FXML

    private CategoryAxis destination;
    @FXML

    private NumberAxis vol_count;
    /**
     * Initializes the controller class.
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            generateBarChart();
        } catch (SQLException ex) {
            Logger.getLogger(StattController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     private void generateBarChart() throws SQLException {
        ServiceVoyage ss = new ServiceVoyage();
        List<Object[]> CountVolByVoyaga = ss.CountVolByVoyaga();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Object[] row : CountVolByVoyaga) {
            series.getData().add(new XYChart.Data<>(row[0].toString(), (int) row[1]));
        }
        barChart.getData().add(series);
        barChart.setLegendVisible(false);
        barChart.setTitle("STATIC");

}
     @FXML
private void Retour3(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceVoyage.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}   
}