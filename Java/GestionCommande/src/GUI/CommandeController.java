/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.CommandeCRUD;
import Service.Lignecommeande;
import Service.PdfCmd;
import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class CommandeController implements Initializable {

    @FXML
    private AnchorPane liste;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Commande> tableview;
    @FXML
    private TableColumn<Commande, Date> datecol;
    @FXML
    private TableColumn<Commande, Integer> montant;
    CommandeCRUD commandeS =new CommandeCRUD();
    
    Lignecommeande lignecommandeS =new Lignecommeande();
    ObservableList<Commande>obs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            List<Commande> commandes =  commandeS.recuperer();
            obs = FXCollections.observableArrayList(commandes);
            tableview.setItems(obs);
            datecol.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
            montant.setCellValueFactory(new PropertyValueFactory<>("montant_commande"));
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        int selectedIndex = obs.indexOf( tableview.getSelectionModel().getSelectedItem());
        System.out.println(obs.get(0)); 
        commandeS.supprimer(obs.get(selectedIndex));
        obs.remove(selectedIndex);
    }

    @FXML
    private void user(ActionEvent event) {
    }

    @FXML
    private void commande(ActionEvent event) {
    }

    @FXML
    private void cart(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
             Parent root = loader.load();
            liste.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void store(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store.fxml"));
             Parent root = loader.load();
            liste.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
   private void pdf(ActionEvent event) throws SQLException {
            int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
            PdfCmd pdf = new PdfCmd();
            pdf.orderPdf();
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("The pdf file has been exported successfully. ");
            alert0.show();
    }
    @FXML
    private void statistiques(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
             Parent root = loader.load();
            liste.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
}
