/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import Entities.Games;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.GameService;
import Services.categoryService;
import Utils.MyDB;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import javafx.scene.Node;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author achra
 */
public class AjouterController implements Initializable {

    @FXML
    private TextField tfNom;
     @FXML
    private TextArea tfDesc;
    @FXML
    private TextField tfImage;
    @FXML
    private Button imageB;
    @FXML
    private Button btmAjouter;
    @FXML
    private Button Home;
    @FXML
    private Button btmModifier;
    private categoryService categoryService = new categoryService();
    @FXML
    private ComboBox<String> comboBox;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // récupérer la référence du ComboBox et remplir la liste des noms des événements
        try {
            List<String> nomCat = categoryService.getNomsCat();
            comboBox.getItems().addAll(nomCat);
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des noms des categories : " + ex.getMessage());
        }
    }    

    @FXML
    private void addGames(ActionEvent event) throws ParseException, SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String getName = tfNom.getText();
        String getDescription = tfDesc.getText();
        String getImage = tfImage.getText();
        String nomCat = comboBox.getValue();
        if (getName.isEmpty()| getDescription.isEmpty() | getImage.isEmpty()){
            alert.setTitle("Games");
            alert.setContentText("Voun ne pouvez pas envoyer un jeu avec un champ vide!!");
            alert.show();
        }
         if (nomCat == null) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une categorie !");
        alert.showAndWait();
        return;}
         
         if (getDescription.length() < 20) {
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
        int idCat = categoryService.getIdCByNom(nomCat);
        
        
            Games r = new Games(getName, getDescription, getImage,idCat);
            GameService CR = new GameService();
            CR.addGames(r);
            alert.setTitle("game");
            alert.setContentText("Jeux ajoutée Avec succées");
            alert.show();
            tfNom.clear();
            tfDesc.clear();
            tfImage.clear();
            comboBox.setValue(null); // Effacement de la sélection dans le ComboBox

        }
        }

@FXML



private void chooseImageButton(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
    );

    Window window = ((Node) event.getSource()).getScene().getWindow(); // Obtenir la fenêtre parent
    File selectedFile = fileChooser.showOpenDialog(window); // Afficher la boîte de dialogue

    if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath();
        tfImage.setText(imagePath);

        // Copier le fichier dans le dossier de destination
        String destinationFolder = "C:\\xampp\\htdocs\\5image";
        try {
            Path sourcePath = Paths.get(imagePath);
            Path destinationPath = Paths.get(destinationFolder, selectedFile.getName());
            Files.copy(sourcePath, destinationPath);

            // Mettre à jour le champ de la base de données avec le nouveau chemin de l'image
            String newImagePath = destinationPath.toString(); // Chemin absolu de l'image copiée
            updateImagePathInDatabase(newImagePath); // Appeler une fonction pour mettre à jour la base de données
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

private void updateImagePathInDatabase(String newImagePath) {
    // Code pour mettre à jour la base de données avec le nouveau chemin de l'image
    // Utiliser la bibliothèque ou le framework de base de données de votre choix
    // Exemple (hypothétique) avec JDBC :
   
    Statement ste = null;
 Connection cnx = MyDB.getInstance().getCnx();
    try {
        String updateQuery = "UPDATE `game` SET image = '" + newImagePath + "' WHERE id = 1"; // Modifier la requête selon votre schéma de base de données
        ste.executeUpdate(updateQuery);
        ste.close();
        cnx.close();
    } catch (SQLException e) {
        e.printStackTrace();
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
