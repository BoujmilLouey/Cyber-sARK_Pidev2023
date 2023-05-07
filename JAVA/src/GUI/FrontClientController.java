/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.SingleUser;
import Entities.User;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Safe
 */
public class FrontClientController implements Initializable{

    @FXML
    private AnchorPane anch;
    @FXML
    private JFXButton ButtonCompte;
    @FXML
    private JFXButton ButtonProjet;
    @FXML
    private JFXButton ButtonMetier;
    @FXML
    private JFXButton ButtonAnnonce;
    @FXML
    private JFXButton ButtonEvenement;
    @FXML
    private JFXButton ButtonSignOut;
        @FXML
    private ImageView image_view;
    @FXML
    private JFXButton ButtonStore;
    
   @FXML
    private JFXButton Bgames;
    

    @FXML
    private Label label_name;
    @FXML
    private TextField txt_id;
    @FXML
    private void redirecttocompte(ActionEvent event) {
              try {
                  final Node source = (Node) event.getSource();

         
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientPanel.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));
             
            stage.show();
           
    } 
          catch(Exception e) {
        e.printStackTrace();
    }
    }

    private void redirecttoProjet(ActionEvent event) {
                      try {
                  final Node source = (Node) event.getSource();

         
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjetClient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));
             
            stage.show();
           
    } 
          catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void redirecttometier(ActionEvent event) {
         try {
                  final Node source = (Node) event.getSource();

         
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MetierClientChart.fxml"));
       
             System.out.println("ZZZ");
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));
             
            stage.show();
           
    } 
          catch(Exception e) {
        e.printStackTrace();
    }
        
    }

    @FXML
    private void redirectToAnnonce(ActionEvent event) {
         try {
                  final Node source = (Node) event.getSource();

         
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Annonce.fxml"));
       
             System.out.println("ZZZ");
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));
             
            stage.show();
           
    } 
          catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void redirecttoevenement(ActionEvent event) {
        try {
                  final Node source = (Node) event.getSource();

         
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EvenementClient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));
             
            stage.show();
           
    } 
          catch(Exception e) {
        e.printStackTrace();
    }
    }
  private Stage getStage() {
        return (Stage) image_view.getScene().getWindow();
    }




    private void closeStage(ActionEvent event) {
        getStage().close();
    }
    
    @FXML
    private void handleCoursHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AffCours.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (ButtonProjet.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void signout(ActionEvent event) {
          try {
                  final Node source = (Node) event.getSource();

          
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root)); 
            stage.show();
             closeStage( event);
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 SingleUser hold = SingleUser.getInstance();
                  User u = hold.getUser(); 
                  label_name.setText(u.getPrenom()+" "+u.getNom());
                  String picture = "file:"+u.getImage();
                    Image image = new Image(picture, 110, 110, false, true);
            
            image_view.setImage(image);    }
    
    @FXML
    void onBcalendrier() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (ButtonMetier.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
        
    }
    @FXML
    void Produit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListProduitUsers.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (ButtonStore.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    @FXML
    void onGames() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AFFICHE.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Bgames.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
        
    }
     @FXML
    void Cart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontMainWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Bgames.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    
    
}
