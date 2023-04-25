/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.CommandeCRUD;
import Service.Lignecommeande;
import Service.Product;
import Service.SMSNotifier;
import entities.Commande;
import entities.Ligne_commande;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import javafx.scene.control.Alert;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class PanierController implements Initializable {

    @FXML
    private Label cart;
    @FXML
    private TableView<Produit> textcart;
    @FXML
    private TableColumn<Produit, String> nomcol;
    @FXML
    private TableColumn<Produit, String> refcol;
    @FXML
    private TableColumn<Produit, Double> prixcol;
    @FXML
    private TableColumn<Produit, String> coulcol;
    @FXML
    private TableColumn<Produit, Double> poidscol;
    @FXML
    private TableColumn<Produit, String> desccol;
    @FXML
    private TextField totaltext;

    /**
     * Initializes the controller class.
     */
     CommandeCRUD commandeS =new CommandeCRUD();
    
    Lignecommeande lignecommandeS =new Lignecommeande();
    ObservableList<Produit> obs;
    ObservableList<Produit> obsp;
    Product produitS = new Product();
        List<Produit> listp = new ArrayList<>();
    @FXML
    private TableColumn<Produit, Integer> prodid;
    @FXML
    private TableColumn<Produit, Integer> qp;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private Label cart1;
    @FXML
    private TableView<Produit> panier;
   List<Produit> paniers = new ArrayList<>();
    List<Produit>  ptovalide
         = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            List<Produit> produits = produitS.recuperer();

            obs = FXCollections.observableArrayList(produits);
            textcart.setItems(obs);
            nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            refcol.setCellValueFactory(new PropertyValueFactory<>("reference"));
            prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
            coulcol.setCellValueFactory(new PropertyValueFactory<>("couleur"));
            poidscol.setCellValueFactory(new PropertyValueFactory<>("poids"));
            desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Parent root = loader.load();
            cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
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
       int selectedIndex = obsp.indexOf( panier.getSelectionModel().getSelectedItem());
      
        obsp.remove(selectedIndex);
          Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("Produit Effacer!. ");
            alert0.show();
    }
  

    @FXML
    private void Vider(ActionEvent event) {
        obsp.clear();
        paniers.clear();
        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("Panier est vide. ");
            alert0.show();
        
    }

 

   @FXML
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
    private void addtoPanier(ActionEvent event) {

        int selectedIndex = obs.indexOf(textcart.getSelectionModel().getSelectedItem());
     
        paniers.add(obs.get(selectedIndex));

        // TODO
        //  List<Produit> produits=  produitS.recuperer();
        obsp = FXCollections.observableArrayList(paniers);
        panier.setItems(obsp);
        prodid.setCellValueFactory(new PropertyValueFactory<>("id"));
        qp.setCellValueFactory(new PropertyValueFactory<>("poids"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        //System.out.println(listp);
            List<Produit> listp = FXCollections.observableList(obsp);
        
             Produit c = new Produit();
                c.setId(listp.get(0).getId());
               c.setPrix((int) listp.get(0).getPrix());
            
           ptovalide.add(c);
           Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("Votre produit est ajouter. ");
            alert0.show();
   

//
//             obsp = FXCollections.observableArrayList(paniers);
//        panier.setItems(obsp);
//       prodid.setCellValueFactory(new PropertyValueFactory<>("id"));
//        qp.setCellValueFactory(new PropertyValueFactory<>("poids"));
//        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
    }
   @FXML
    private void Valider(ActionEvent event) {
        System.out.println("commmmm  ");
             AjouterCommd(ptovalide) ;  
         try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeinerface.fxml"));
             Parent root = loader.load();
             cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            
        }
           SMSNotifier sms = new SMSNotifier();
                
                    String message ="Votre Commande est valider ";
                    
                  sms.sendSms( message);
                
    }
 private void AjouterCommd( List<Produit> ptovalide) {
     System.out.println("ptovalide :  "+ptovalide);
         Lignecommeande lc = new Lignecommeande();
             for (Iterator<Produit> iterator = ptovalide.iterator(); iterator.hasNext();) {
               try {
                Produit next = iterator.next();
                Ligne_commande c = new Ligne_commande(next.getId(), 5, (int) next.getPrix());
                System.out.println("Ligne_commande : "+c);
                lc.ajouter(c);
                
                //Validation cmd
                Commande cv = new Commande();
                cv.setDate_commande(Date.valueOf(LocalDate.now()));
                cv.setMontant_commande(lignecommandeS.sommeTotale());
                commandeS.ajouter(cv);
      
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
 }

    @FXML
    private void panier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
             Parent root = loader.load();
            cart.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    
}
