/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class HomeController implements Initializable {

    @FXML
    private Label home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Cart(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
             Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void produit(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store.fxml"));
             Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @FXML
    private void panier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
             Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

  
    
}
