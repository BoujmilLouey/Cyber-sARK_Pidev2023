/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.sql.Connection;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Games;
import Services.GameService;
import Services.Metier;
import Utils.MyDB;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListGamesController implements Initializable {
 @FXML
    private Button btnRecherche;

    @FXML
    private TextField recherche;
    @FXML
    private TableView<Games> listGames;
    @FXML
    private TableColumn<Games, String> NomColumn;
    @FXML
    private TableColumn<Games, String> DescriptionColumn;
    @FXML
    private TableColumn<Games, String> ImageColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnEnregistrer;
    @FXML
    private Button imageB;
    @FXML
    private TextField textNom;
    @FXML
    private TextField textImage;
    @FXML
    private TextArea textDescription;
    @FXML
    private ComboBox<String> combo;
        
    @FXML
    private Button Home;
    
    ObservableList<Games> data = FXCollections.observableArrayList();
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
       
       LoadData();
    }
   private void TrieNom() throws IOException {
    GameService ums = new GameService();
    List<Games> games = ums.triNom();

    if (games != null && !games.isEmpty()) {
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ImageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));

        listGames.getItems().setAll(games);
    }
}

    
    @FXML
    void triCoice(ActionEvent event) throws IOException {
        if (combo.getValue().equals("Trier Selon Nom")) {
            TrieNom();
        } 
    }
    
    private void LoadData() {
        System.out.println("************");
        GameService ums = new GameService();
        ums.showGames().stream().forEach((p) -> {
            data.add(p);
        });
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            ImageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));

            combo.getItems().addAll("aucun", "Trier Selon Nom");
            listGames.setItems(data);
            

           
    }
    @FXML
         public void rechercher(ActionEvent event) throws IOException {
        Metier met = new Metier();
        //ServiceUser sca = new ServiceUser();
        System.out.println("/////////////recherche//////////");
        System.out.println(recherche.getText());
        ObservableList<Games> data = FXCollections.observableArrayList(met.SearchByName(recherche.getText()));
        System.out.println(data);
        
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        listGames.setItems(data);

    }
    
    public void supprimer(ActionEvent event) throws IOException {
       
        int selectedIndex = listGames.getSelectionModel().getSelectedIndex();

       if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune jeu selectionnée!");
           alert.setContentText("Veuiller selectionner une jeu à supprimer");
           Optional<ButtonType> result = alert.showAndWait();
       } else{
        try {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                GameService ser = new GameService();
                ser.deleteGame(listGames.getSelectionModel().getSelectedItem().getId());
                GameService crud = new GameService();
                ObservableList<Games> data = FXCollections.observableArrayList(crud.showGames());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            ImageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
            listGames.setItems(data);
            alert.setTitle("Suppression du jeu");
            alert.setContentText("Le jeux est supprimée Avec succées");
            alert.show();
            } else {

            }
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }
    public void initierJeu(ActionEvent event) throws ParseException {
        int selectedIndex = listGames.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune jeu selectionnée!");
           alert.setContentText("Veuiller selectionner une jeu à modifier");
           Optional<ButtonType> result = alert.showAndWait();
       }
        else{
        int idRec=listGames.getSelectionModel().getSelectedItem().getId();
        textImage.setText(listGames.getSelectionModel().getSelectedItem().getImage());
        textNom.setText(listGames.getSelectionModel().getSelectedItem().getName());
        
        textDescription.setText(listGames.getSelectionModel().getSelectedItem().getDescription());
        
        
        }
        
    }
    public void modifier(ActionEvent event) throws ParseException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (textNom.getText().isEmpty()| textDescription.getText().isEmpty() | textImage.getText().isEmpty()){
            alert.setTitle("Modification jeu");
            alert.setContentText("Voun ne pouvez pas modifier une jeu avec un champ vide!!");
            alert.show();
        }
        else{
        int idRec=listGames.getSelectionModel().getSelectedItem().getId();
        Games r = new Games(idRec,textNom.getText(), textDescription.getText(),textImage.getText());
        GameService crud = new GameService();
        crud.updateGame(r);
        textImage.clear();
        textNom.clear();
        textDescription.clear();
        
            ObservableList<Games> data = FXCollections.observableArrayList(crud.showGames());
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            ImageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
            listGames.setItems(data);
            alert.setTitle("Modification jeu");
            alert.setContentText("Jeu Modifiée Avec succées");
            alert.show();
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
        imageB.setText(imagePath);

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
    

