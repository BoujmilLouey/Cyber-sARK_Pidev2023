/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.CommandeCRUD;
import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart piechart;
      ObservableList<PieChart.Data> obsp = FXCollections.observableArrayList();
       CommandeCRUD commandeS =new CommandeCRUD();
        ObservableList<Commande>obs;
    @FXML
    private AnchorPane stt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        obsp.clear();

        ResultSet rs = commandeS.Commandecount();
        try {
            while (rs.next()) {
                String date = rs.getString("date_commande");
                int count = rs.getInt("count_commande");
//                System.out.println(category + ": " + count);
                PieChart.Data data = new PieChart.Data(date, count);
                obsp.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setData(obsp);
        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("Votre statistique. ");
            alert0.show();
      
    }

    @FXML
    private void piechart(MouseEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeinerface.fxml"));
             Parent root = loader.load();
            stt.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
            
    }
        
      


