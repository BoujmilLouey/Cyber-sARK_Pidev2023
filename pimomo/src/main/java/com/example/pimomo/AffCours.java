package com.example.pimomo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
//import jdk.internal.foreign.abi.Binding;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private TableColumn<Cours, Double> noteM;

    @FXML
    private Button home;

    @FXML
    private Button Btele;

    @FXML
    private TextField recherche;



    //pour les commentaire
    @FXML
    private TextField DESC;

    @FXML
    private TextField NOTE;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private ComboBox<Cours> scrollCours;

    @FXML
    private TableColumn<Commentaire, String> Ncours;

    @FXML
    private TableView<Commentaire> tableview;

    @FXML
    private TableColumn<Commentaire, String> descC;

    @FXML
    private TableColumn<Commentaire, Integer> noteC;


    private ObservableList<Commentaire> observableListC;




    private ObservableList<Cours> observableList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load events from database into the tableview
        CoursService dao = new CoursService();
        CommentaireService commentaireService = new CommentaireService();
        ArrayList<Cours> cours = null;
        try {
            cours = dao.afficherAll();
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

        // Calculate and set the average note for each course
        for (Cours c : cours) {
            int id_cours = c.getId();
            ArrayList<Commentaire> commentaires = null;
            try {
                commentaires = commentaireService.rechercherCommentairesParCours(id_cours);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            double sum = 0.0;
            int count = 0;
            for (Commentaire com : commentaires) {
                sum += com.getNote();
                count++;
            }
            double average = (count > 0) ? sum / count : 0.0;
            c.setNoteMoyenne(average);
        }

        noteM.setCellValueFactory(new PropertyValueFactory<>("noteMoyenne"));

        // Load course names into the ComboBox
        ArrayList<Cours> courses = null;
        try {
            courses = dao.afficherAll();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Cours> observableCourses = FXCollections.observableArrayList(courses);
        scrollCours.setItems(observableCourses);

        // Set ComboBox cell factory to display course names
        scrollCours.setCellFactory(new Callback<ListView<Cours>, ListCell<Cours>>() {
            @Override
            public ListCell<Cours> call(ListView<Cours> param) {
                return new ListCell<Cours>() {
                    @Override
                    protected void updateItem(Cours item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getNom());
                        }
                    }
                };
            }
        });

        // Set ComboBox value factory to get selected course
        scrollCours.setConverter(new StringConverter<Cours>() {
            @Override
            public String toString(Cours course) {
                if (course == null) {
                    return null;
                } else {
                    return course.getNom();
                }
            }

            @Override
            public Cours fromString(String string) {
                if (string == null || string.isEmpty()) {
                    return null;
                } else {
                    return dao.getCoursByName(string);
                }
            }
        });

        ArrayList<Commentaire> commentaires = null;

        try {
            commentaires = commentaireService.afficherAllC();
            // Convert the ArrayList to an ObservableList
            observableListC = FXCollections.observableArrayList(commentaires);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableListC);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set cell value factories for the table columns
       // Ncours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId_cours().getNom()));

        descC.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        noteC.setCellValueFactory(new PropertyValueFactory<>("note"));



    }




    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepageFront.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (home.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void ontele() {
        Cours SCours = table.getSelectionModel().getSelectedItem();
        if (SCours != null) {
            String pdfPath = SCours.getPdf();

            if (!pdfPath.isEmpty()) {
                try {
                    String fileName = pdfPath.substring(pdfPath.lastIndexOf("/") + 1);
                    File pdfFile = new File(pdfPath);

                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialFileName(fileName);
                    fileChooser.setTitle("Télécharger le fichier PDF");
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                    fileChooser.getExtensionFilters().add(extFilter);
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

                    File saveFile = fileChooser.showSaveDialog(null);
                    if (saveFile != null) {
                        Files.copy(pdfFile.toPath(), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        // Afficher un message de succès
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Téléchargement réussi");
                        alert.setHeaderText("Le fichier PDF a été téléchargé avec succès.");
                        alert.setContentText("Emplacement du fichier : " + saveFile.getAbsolutePath());
                        alert.showAndWait();
                    }
                } catch (IOException e) {
                    // Gérer les erreurs de téléchargement
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de téléchargement");
                    alert.setHeaderText("Une erreur est survenue lors du téléchargement du fichier PDF.");
                    alert.setContentText("Veuillez réessayer.");
                    alert.showAndWait();
                }
            } else {
                // Le chemin du PDF est vide, afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de sélection");
                alert.setHeaderText("Aucun fichier PDF sélectionné");
                alert.setContentText("Veuillez sélectionner un cours contenant un fichier PDF.");
                alert.showAndWait();
            }
        } else {
            // Aucun cours sélectionné, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setHeaderText("Aucun cours sélectionné");
            alert.setContentText("Veuillez sélectionner un cours dans le tableau.");
            alert.showAndWait();
        }
    }




    @FXML
    void onrecherche() {
        CoursService dao = new CoursService();
        String nom = recherche.getText();

        try {
            // Recherche les cours par nom
            ArrayList<Cours> coursTrouves = dao.rechercherParNom(nom);

            if (coursTrouves.isEmpty()) {
                // Aucun cours trouvé
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aucun cours trouvé");
                alert.setHeaderText(null);
                alert.setContentText("Aucun cours trouvé avec le nom '" + nom + "'.");
                alert.showAndWait();
            } else {
                // Affiche les cours trouvés dans le TableView
                observableList = FXCollections.observableArrayList(coursTrouves);
                table.setItems(observableList);
                noteM.setCellValueFactory(new PropertyValueFactory<>("noteMoyenne"));

            }

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de recherche");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la recherche de cours.");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }

    //ajout de commentaire




    @FXML
    void chooseCours(ActionEvent event) {
        Cours coursChoisi = scrollCours.getValue();
    }


    @FXML
    private void addItem(ActionEvent event) {
        CommentaireService dao = new CommentaireService();

        String contenu = DESC.getText();
        int note = Integer.parseInt(NOTE.getText());

        // Récupérer le cours sélectionné dans le ComboBox
        Cours cours = scrollCours.getValue();


        Commentaire commentaire = new Commentaire(contenu, note, cours.getId());

        try {
            dao.ajouter(commentaire);

            // Refresh the TableView
            tableview.getItems().clear();

            try {
                // Get the ArrayList from the service
                ArrayList<Commentaire> commentaires = dao.afficherAllC();

                // Convert the ArrayList to an ObservableList
                observableListC = FXCollections.observableArrayList(commentaires);

                // Set the ObservableList as the data source for the TableView
                tableview.setItems(observableListC);

            } catch (SQLException ex) {
                Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableview.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void deleteItem() {
        CommentaireService dao = new CommentaireService();
        Commentaire selectedEvent = tableview.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                dao.delete(selectedEvent.getId());
                tableview.getItems().remove(selectedEvent);
            } catch (SQLException ex) {
                Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                // Get the ArrayList from the service
                ArrayList<Commentaire> commentaires = dao.afficherAllC();

                // Convert the ArrayList to an ObservableList
                observableListC = FXCollections.observableArrayList(commentaires);

                // Set the ObservableList as the data source for the TableView
                tableview.setItems(observableListC);

            } catch (SQLException ex) {
                Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableview.refresh();
        }
    }

    @FXML
    private void updateItem() {
        Commentaire selectedEvent = tableview.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            CommentaireService dao = new CommentaireService();


            String contenu = DESC.getText();
            int note = Integer.parseInt(NOTE.getText());


            Commentaire updatedEvent = new Commentaire(contenu, note);
            updatedEvent.setId(selectedEvent.getId());
            try {
                dao.update(updatedEvent);

            } catch (SQLException ex) {
                Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                // Get the ArrayList from the service
                ArrayList<Commentaire> commentaires = dao.afficherAllC();

                // Convert the ArrayList to an ObservableList
                observableListC = FXCollections.observableArrayList(commentaires);

                // Set the ObservableList as the data source for the TableView
                tableview.setItems(observableListC);

            } catch (SQLException ex) {
                Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



}









