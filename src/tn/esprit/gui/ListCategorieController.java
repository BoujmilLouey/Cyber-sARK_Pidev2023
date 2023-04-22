/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.categorie_produit;
import tn.esprit.services.CategorieService;


public class ListCategorieController implements Initializable {

    @FXML
    private TableView<categorie_produit> tableCAT;
    @FXML
    private TableColumn<categorie_produit,String> TableTy;
    @FXML
    private TableColumn<categorie_produit, String> TableRef;
    @FXML
    private TextField champType;
    @FXML
    private TextField ChampRef;
    @FXML
    private Button BtnEnregistrer;
    @FXML
    private Button BtnModifier;
    @FXML
    private Button btnSuprimer;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            CategorieService crud = new CategorieService();
            ObservableList<categorie_produit> data = FXCollections.observableArrayList(crud.afficherCat());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            TableTy.setCellValueFactory(new PropertyValueFactory<>("type"));
            TableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
            tableCAT.setItems(data);
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println("here");
            Logger.getLogger(ListCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void modifier(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (champType.getText().isEmpty()){
            alert.setTitle("Category");
            alert.setContentText("Voun ne pouvez pas modifier une reclamation avec un champ vide!!");
            alert.show();
        }
        else if (ChampRef.getText().isEmpty()){
         alert.setTitle("Category");
            alert.setContentText("Voun ne pouvez pas modifier une reclamation avec un champ vide!!");
            alert.show();
        }
        
        
        else{
        int idRec=tableCAT.getSelectionModel().getSelectedItem().getId();
        categorie_produit r = new categorie_produit(idRec,champType.getText(),ChampRef.getText());
        CategorieService crud = new CategorieService();
        crud.modifierCat(r);
        champType.clear();
        ChampRef.clear();
        
            ObservableList<categorie_produit> data = FXCollections.observableArrayList(crud.afficherCat());
            TableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
            TableTy.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableCAT.setItems(data);
            alert.setTitle("categorie");
            alert.setContentText("Categorie Modifiée Avec succées");
            alert.show();
    }
    }

    @FXML
    private void initierReclamation(ActionEvent event) {
         int selectedIndex = tableCAT.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucun produit selectionnée!");
           alert.setContentText("Veuiller selectionner un produit à modifier");
           Optional<ButtonType> result = alert.showAndWait();
       }
        else{
        int idRec=tableCAT.getSelectionModel().getSelectedItem().getId();
       
        champType.setText(tableCAT.getSelectionModel().getSelectedItem().getType());
        ChampRef.setText(tableCAT.getSelectionModel().getSelectedItem().getReference());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
          int selectedIndex = tableCAT.getSelectionModel().getSelectedIndex();

       if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune categorie selectionnée!");
           alert.setContentText("Veuiller selectionner une categorie à supprimer");
           Optional<ButtonType> result = alert.showAndWait();
       } else{
        try {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CategorieService ser = new CategorieService();
                ser.supprimerCat(tableCAT.getSelectionModel().getSelectedItem().getId());
                CategorieService crud = new CategorieService();
                ObservableList<categorie_produit> data = FXCollections.observableArrayList(crud.afficherCat());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            TableTy.setCellValueFactory(new PropertyValueFactory<>("type"));
            TableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
            tableCAT.setItems(data);
            alert.setTitle("Produit");
            alert.setContentText("La categorie est supprimée Avec succées");
            alert.show();
            } else {

            }
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }

    @FXML
    private void AjoutCatRoute(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategorie.fxml"));
        try {
           Parent root = loader.load();
            AjouterCategorieController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            ChampRef.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @FXML
    private void afficherProduitRoute(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduit.fxml"));
        try {
           Parent root = loader.load();
            ListProduitController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            ChampRef.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
   
    
}
