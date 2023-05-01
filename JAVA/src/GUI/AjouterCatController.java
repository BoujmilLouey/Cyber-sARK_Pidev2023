/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Game_category;
import Entities.Games;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.categoryService;

/**
 * FXML Controller class
 *
 * @author achra
 */
public class AjouterCatController implements Initializable {

    @FXML
    private TextField tfNom;
  
    @FXML
    private Button btmAjouter;
    @FXML
    private Button btmModifier;
    @FXML
    private Button Home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void addCategory(ActionEvent event) throws ParseException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String getName = tfNom.getText();
   
        if (getName.isEmpty()){
            alert.setTitle("Les categories des jeux");
            alert.setContentText("Voun ne pouvez pas envoyer une categorie avec un champ vide!!");
            alert.show();
        }
        else {
            Game_category r = new Game_category(getName);
            categoryService CR = new categoryService();
            CR.addCategory(r);
            alert.setTitle("game category");
            alert.setContentText("categorie ajoutée Avec succées");
            alert.show();
            tfNom.clear();
          
        }
        }


@FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Home.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
    




}

    
    
    
    

   // private void addperson(ActionEvent event) {
      //  String nom=fxnom.getText();
      //  String prenom=fxnom.getText();
        
        
        
 //   }
    
//}
