package com.example.pimomo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.commons.io.FileUtils;


public class AffCours implements Initializable {

    @FXML
    private TableView<Cours> table;

    @FXML
    private TableColumn<Cours, String> colnom;

    @FXML
    private TableColumn<Cours, String> coldesc;

    @FXML
    private TableColumn<Commentaire, Integer> noteM;

    @FXML
    private Button home;

    @FXML
    private Button Btele;



    private ObservableList<Cours> observableList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load events from database into the tableview
        CoursService dao = new CoursService();
        try {
            // Get the ArrayList from the service
            ArrayList<Cours> cours = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(cours);

            // Set the ObservableList as the data source for the TableView
            table.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set cell value factories for the table columns

        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("des"));


    }

    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepageFront.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (home.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }





/*
    @FXML
    void ontele() {
        // Récupérer le cours sélectionné dans la tableview
        Cours cours = table.getSelectionModel().getSelectedItem();

        if (cours == null) {
            // Aucun cours sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un cours à télécharger.");
            alert.showAndWait();
            return;
        }

        // Ouvrir un FileChooser pour sélectionner l'emplacement de téléchargement
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le cours");
        fileChooser.setInitialFileName(cours.getNom() + ".pdf");

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                // Télécharger le fichier en utilisant Apache Commons IO
                FileUtils.copyURLToFile(new URL(cours.getpdf()), file);

                // Afficher une alerte pour confirmer le téléchargement
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Téléchargement réussi");
                alert.setHeaderText(null);
                alert.setContentText("Le fichier a été téléchargé avec succès.");
                alert.showAndWait();
            } catch (IOException ex) {
                // Une erreur s'est produite, afficher une alerte et imprimer la pile d'erreurs
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de téléchargement");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors du téléchargement du fichier PDF.");
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


 */







}
