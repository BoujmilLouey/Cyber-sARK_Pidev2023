/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Safe
 */
public class FrontAdminController {
    
       @FXML
    private AnchorPane anch;
       @FXML
    private JFXButton Event1;

       @FXML
    private JFXButton Produit;

       @FXML
    private JFXButton Categorie;
       @FXML
    private JFXButton User;
       
          @FXML
    private JFXButton AddGcategory;

    @FXML
    private JFXButton AllG;

    @FXML
    private JFXButton CategoryG;

    @FXML
    private JFXButton AddG;
       
       

    @FXML
    private TextField txt_id;
    private void redirectToSousMetier(ActionEvent event) {
          try {
               Parent root=FXMLLoader.load(getClass().getResource("/GUI/Metier.fxml"));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle("Cyber'sARK");
               stage.setScene(new Scene(root));
               stage.show();
               closeStage();
           } catch (IOException ex) {
               Logger.getLogger(FrontAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
      
    private void redirectToMetier(ActionEvent event) {
          try {
               Parent root=FXMLLoader.load(getClass().getResource("/GUI/Metier.fxml"));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle("Cyber'sARK");
               stage.setScene(new Scene(root));
               stage.show();
               closeStage();
           } catch (IOException ex) {
               Logger.getLogger(FrontAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
        
    }

  public void closeStage() {
        ((Stage) anch.getScene().getWindow()).close();
    }
  
    @FXML
    private void handleUser1(ActionEvent event) {
           try {
               Parent root=FXMLLoader.load(getClass().getResource("/GUI/AdminPanel.fxml"));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle("Cyber'sARK");
               stage.setScene(new Scene(root));
               stage.show();
               closeStage();
           } catch (IOException ex) {
               Logger.getLogger(FrontAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }


    @FXML
    private void signout(ActionEvent event) {
                      try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Cyber'sARK");
            stage.setScene(new Scene(root));
            stage.show();
             closeStage();
        } catch (IOException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void redirecttoevents(ActionEvent event) {
         try {
               Parent root=FXMLLoader.load(getClass().getResource("Evenement.fxml"));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle("Cyber'sARK");
               stage.setScene(new Scene(root));
               stage.show();
               closeStage();
           } catch (IOException ex) {
               Logger.getLogger(FrontAdminController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    @FXML
    void handleEventHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Addevent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Event1.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ListProd() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Produit.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ListCat() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListCategorie.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Categorie.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
   //games
    
    
    
    @FXML
    void CateG() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListCategory.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (CategoryG.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @FXML
    void AllG() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListGames.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (AllG.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @FXML
    void AddG() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (AddG.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    void AddGcate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajouterCat.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (AddGcategory.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}



