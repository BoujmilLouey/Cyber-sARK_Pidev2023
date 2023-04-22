/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.FileChooser;
import tn.esprit.entities.categorie_produit;
import tn.esprit.entities.produit;
import tn.esprit.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListProduitController implements Initializable {

    @FXML
    private TextField champNom;
    @FXML
    private TextField ChampRef;
    @FXML
    private TextField ChampPrix;
    @FXML
    private TextField ChampCouleur;
    @FXML
    private TextField ChampPoid;
    @FXML
    private TextField ChampsDesc;
    @FXML
    private TextField ChampImage;
    @FXML
    private Button ButtonImage;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsuprimer;
    @FXML
    private TableView<produit> listproduit;
    @FXML
    private TableColumn<produit,String> tableNom;
    @FXML
    private TableColumn<produit,String> TableRef;
    @FXML
    private TableColumn<produit,String> TablePrix;
    @FXML
    private TableColumn<produit,String> TableCouleur;
    @FXML
    private TableColumn<produit,String> TablePoids;
    @FXML
    private TableColumn<produit,String> TableDesc;
    @FXML
    private TableColumn<produit,String> TableImage;
    @FXML
    private TableColumn<produit, String> TableICt;

 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            ProduitService crud = new ProduitService();
            ObservableList<produit> data = FXCollections.observableArrayList(crud.showProduit());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            tableNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            TableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
            TablePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            TableCouleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
            TablePoids.setCellValueFactory(new PropertyValueFactory<>("poids"));
            TableDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableImage.setCellValueFactory(new PropertyValueFactory<>("image"));
            TableICt.setCellValueFactory(cellData -> {
            categorie_produit category = cellData.getValue().getCategorie();
            
            return new SimpleStringProperty(category != null ? category.getType(): "");
        });
            listproduit.setItems(data);
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println("here");
            Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

 
 
    @FXML
    private void chooseImageButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
          );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath();
        ChampImage.setText(imagePath);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (champNom.getText().isEmpty()| ChampRef.getText().isEmpty() | ChampPrix.getText().isEmpty() | ChampCouleur.getText().isEmpty()
                | ChampPoid.getText().isEmpty() | ChampsDesc.getText().isEmpty()|ChampImage.getText().isEmpty()  ){
            alert.setTitle("Modification produit");
            alert.setContentText("Voun ne pouvez pas modifier un produit avec un champ vide!!");
            alert.show();
        }
        else{
        int idRec=listproduit.getSelectionModel().getSelectedItem().getId();
        produit r = new produit(idRec,champNom.getText(), ChampRef.getText(),ChampPrix.getText(),ChampCouleur.getText(),ChampPoid.getText(),ChampsDesc.getText(),ChampImage.getText());
        ProduitService crud = new ProduitService();
        crud.updateProduit(r);
        champNom.clear();
        ChampRef.clear();
        ChampPrix.clear();
        ChampCouleur.clear();
        ChampPoid.clear();
        ChampsDesc.clear();
        ChampImage.clear();
            ObservableList<produit> data = FXCollections.observableArrayList(crud.showProduit());
           tableNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            TableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
            TablePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            TableCouleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
            TablePoids.setCellValueFactory(new PropertyValueFactory<>("poids"));
            TableDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableImage.setCellValueFactory(new PropertyValueFactory<>("image"));
            listproduit.setItems(data);
            alert.setTitle("Modification produit");
            alert.setContentText("produit Modifiée Avec succées");
            alert.show();
    }
    }

    @FXML
    private void initierJeu(ActionEvent event) {
         int selectedIndex = listproduit.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucun produit selectionnée!");
           alert.setContentText("Veuiller selectionner un produit à modifier");
           Optional<ButtonType> result = alert.showAndWait();
       }
        else{
        int idRec=listproduit.getSelectionModel().getSelectedItem().getId();
        ChampImage.setText(listproduit.getSelectionModel().getSelectedItem().getImage());
        champNom.setText(listproduit.getSelectionModel().getSelectedItem().getNom());
        ChampRef.setText(listproduit.getSelectionModel().getSelectedItem().getReference());
        ChampPrix.setText(listproduit.getSelectionModel().getSelectedItem().getPrix());
        ChampPoid.setText(listproduit.getSelectionModel().getSelectedItem().getPoids());
        ChampsDesc.setText(listproduit.getSelectionModel().getSelectedItem().getDescription());
        ChampCouleur.setText(listproduit.getSelectionModel().getSelectedItem().getCouleur());
        
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
         int selectedIndex = listproduit.getSelectionModel().getSelectedIndex();

       if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucun produit selectionnée!");
           alert.setContentText("Veuiller selectionner un produit à supprimer");
           Optional<ButtonType> result = alert.showAndWait();
       } else{
        try {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ProduitService ser = new ProduitService();
                ser.deleteProduit(listproduit.getSelectionModel().getSelectedItem().getId());
                ProduitService crud = new ProduitService();
                ObservableList<produit> data = FXCollections.observableArrayList(crud.showProduit());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            tableNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            TableRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
            TablePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            TableCouleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
            TablePoids.setCellValueFactory(new PropertyValueFactory<>("poids"));
            TableDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableImage.setCellValueFactory(new PropertyValueFactory<>("image"));
            listproduit.setItems(data);
            alert.setTitle("Suppression du produit");
            alert.setContentText("Le produit a été supprimée Avec succées");
            alert.show();
            } else {

            }
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }

    @FXML
    private void AffCatRoute(ActionEvent event) {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategorie.fxml"));
        try {
           Parent root = loader.load();
            ListCategorieController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            ChampsDesc.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @FXML
    private void AjoutProduitRoute(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
        try {
           Parent root = loader.load();
            AjouterProduitController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            ChampRef.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
 
    }
    

