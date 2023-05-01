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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Entities.categorie_produit;
import Services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField ChampType;
    @FXML
    private TextField ChampRef;
    @FXML
    private Button AjoutBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String getName = ChampType.getText();
         String getRef = ChampRef.getText();
        if (getName.isEmpty()){
            alert.setTitle("Les categories des produits");
            alert.setContentText("Voun ne pouvez pas envoyer une categorie avec un champ vide!!");
            alert.show();
        }
        else if (getRef.isEmpty()){
            alert.setTitle("Les categories des produits");
            alert.setContentText("Voun ne pouvez pas envoyer une categorie avec un champ vide!!");
            alert.show();
        }
        else {
            categorie_produit r = new categorie_produit(getName,getRef);
            CategorieService CR = new CategorieService();
            CR.ajouterCat(r);
            alert.setTitle("product category");
            alert.setContentText("categorie ajoutée Avec succées");
            alert.show();
            ChampType.clear();
            ChampRef.clear();
          
        }
    }

    @FXML
    private void RetourAListCat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategorie.fxml"));
        try {
           Parent root = loader.load();
            ListCategorieController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            ChampType.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
}
