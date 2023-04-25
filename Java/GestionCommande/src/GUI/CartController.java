/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.CommandeCRUD;
import Service.Lignecommeande;
import Service.Product;
import entities.Ligne_commande;
import entities.Produit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import utils.CommandSession;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class CartController implements Initializable {

    @FXML
    private Label cart;
    @FXML
    private TableColumn<Ligne_commande, Integer> produitcol;
    @FXML
    private TableColumn<Ligne_commande, Integer> qutcol;
    @FXML
    private TableColumn<Ligne_commande, Integer> totalcol;
    private TextField totaltext;
    @FXML
    private TableView<Ligne_commande> textcart;
    Lignecommeande lignecommandeS =new Lignecommeande();
    
    ObservableList<Ligne_commande>obs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            // TODO
            
            List<Ligne_commande> ligne_commandes=  lignecommandeS.recuperer();
             
            obs = FXCollections.observableArrayList(ligne_commandes);
            textcart.setItems(obs);
            produitcol.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            totalcol.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
            qutcol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            totalcol.setCellValueFactory(new PropertyValueFactory<>("price"));
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    


    @FXML
    private void Store(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store.fxml"));
             Parent root = loader.load();
            cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void EffacesProduit(ActionEvent event) throws SQLException {
       int selectedIndex = obs.indexOf( textcart.getSelectionModel().getSelectedItem());
        System.out.println(obs.get(0)); 
        lignecommandeS.supprimer(obs.get(selectedIndex));
        obs.remove(selectedIndex);
    }
  

    private void Vider(ActionEvent event) {
        obs.clear();
    }

    private void Valider(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeinerface.fxml"));
             Parent root = loader.load();
             cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @FXML
    private void cart(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
             Parent root = loader.load();
            cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

private void calculer(ActionEvent event) {
    try {
        // TODO

        int t = lignecommandeS.sommeTotale();
       // totaltext.setInt(t);
        totaltext.setText(Integer.toString(t));
    } catch (SQLException ex) {
        Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @FXML
    private void Pr(MouseEvent event) {
    }

    @FXML
    private void goTopanier(ActionEvent event) {
        
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
             Parent root = loader.load();
            cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }


}
