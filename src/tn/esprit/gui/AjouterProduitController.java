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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.entities.produit;
import tn.esprit.services.CategorieService;
import tn.esprit.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField champNom;
    @FXML
    private ComboBox<String> ChampCategorie;
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
    private Button BottomAjouter;
    private CategorieService CategorieService = new CategorieService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<String> nomCat = CategorieService.getNomsCat();
            ChampCategorie.getItems().addAll(nomCat);
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des noms des categories : " + ex.getMessage());
        }
    }    

    @FXML
    private void AddProduit(ActionEvent event) throws ParseException, SQLException {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String getNom = champNom.getText();
        String getReference = ChampRef.getText();
        String getPrix = ChampPrix.getText();
        String getCouleur = ChampCouleur.getText();
        String getDescription =ChampsDesc .getText();
        String getPoids = ChampPoid.getText();
        String getImage = ChampImage.getText();
        String nomCat = ChampCategorie.getValue();
        if (getNom.isEmpty()| getDescription.isEmpty() | getImage.isEmpty()| getCouleur.isEmpty()| getReference.isEmpty()  ){
            alert.setTitle("Produit");
            alert.setContentText("Voun ne pouvez pas envoyer un produit avec un champ vide!!");
            alert.show();
        }
         if (nomCat == null) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une categorie !");
        alert.showAndWait();
        return;}
         
         if (getDescription.length() < 10) {
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description doit contenir au moins 20 caractères !");
        alert.showAndWait();
        return;
    }
         
         // Vérifier que l'image est au format jpg ou png
    if (!getImage.toLowerCase().endsWith(".jpg") && !getImage.toLowerCase().endsWith(".png")) {
        Alert alert3 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout");
        alert.setHeaderText(null);
        alert.setContentText("L'image doit être au format JPG ou PNG !");
        alert.showAndWait();
        return;
    }       
         
         
         
        else {
        
        // Récupération de l'ID de l'événement correspondant au nom sélectionné
        int idCat = CategorieService.getIdCByNom(nomCat);
        
        
            produit r = new produit(getNom,getReference, getPrix,getCouleur,getDescription,getPoids,getImage,idCat);
            ProduitService CR = new ProduitService();
            CR.addProduit(r);
            alert.setTitle("Produit");
            alert.setContentText("Produit ajoutée Avec succées");
            alert.show();
            champNom.clear();
            ChampRef.clear();
            ChampPrix.clear();
            ChampCouleur.clear();
            ChampsDesc.clear();
            ChampPoid.clear();
            ChampImage.clear();
            ChampCategorie.setValue(null); // Effacement de la sélection dans le ComboBox

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
    private void Listprodroute(ActionEvent event) {
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
