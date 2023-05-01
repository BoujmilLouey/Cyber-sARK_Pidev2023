package GUI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import Services.CoursService;
import Entities.Cours;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Cours> tableview;

    @FXML
    private TableColumn<Cours, Integer> colid;

    @FXML
    private TableColumn<Cours, String> colnom;

    @FXML
    private TableColumn<Cours, String> coldesc;

    @FXML
    private TableColumn<Cours, String> colpdf;

    @FXML
    private TextField NOM;

    @FXML
    private TextField DESC;

    @FXML
    private TextField PDF;
    @FXML
    private Button addFile;

    @FXML
    private Button addButton;
    @FXML
    private Button addButton1;
    @FXML
    private Button Home;
    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    //private ItemDatabase itemDatabase;
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
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set cell value factories for the table columns
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("des"));
        
        colpdf.setCellValueFactory(new PropertyValueFactory<>("pdf"));
      
    }
    @FXML
    private void addItem(ActionEvent event) {
        CoursService dao = new CoursService();
        String nom = NOM.getText();
        String des = DESC.getText();

        String pdf = "";
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        Path destinationPath = null;
        if (selectedFile != null) {
            pdf = selectedFile.getAbsolutePath();
            // Move the PDF file to the specified directory
            Path sourcePath = selectedFile.toPath();
            destinationPath = Paths.get("C:\\xampp\\htdocs\\pdf", selectedFile.getName());
            try {
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        // Vérifier que les champs ne sont pas vides
        if (nom.isEmpty() || des.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Certains champs sont vides");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Vérifier que le nom et la description ne contiennent pas de chiffres
        if (nom.matches(".*\\d.*") || des.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le nom ou la description contient des chiffres");
            alert.setContentText("Veuillez saisir un nom et une description valides.");
            alert.showAndWait();
            return;
        }

        // Vérifier que le fichier PDF est bien sélectionné
        if (!pdf.toLowerCase().endsWith(".pdf")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le fichier PDF n'a pas été sélectionné");
            alert.setContentText("Veuillez sélectionner un fichier PDF valide.");
            alert.showAndWait();
            return;
        }

        Cours cours = new Cours(nom, des, destinationPath.toString());

        try {
            dao.ajouter(cours);

            // Refresh the TableView
            tableview.getItems().clear();
            try {
                // Get the ArrayList from the service
                ArrayList<Cours> cour = dao.afficherAll();

                // Convert the ArrayList to an ObservableList
                observableList = FXCollections.observableArrayList(cour);

                // Set the ObservableList as the data source for the TableView
                tableview.setItems(observableList);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableview.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontCoach.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Home.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }






    @FXML
private void deleteItem() {
    CoursService dao = new CoursService();
    Cours selectedEvent = tableview.getSelectionModel().getSelectedItem();
    if (selectedEvent != null) {
        try {
            dao.delete(selectedEvent.getId());
            tableview.getItems().remove(selectedEvent);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Get the ArrayList from the service
            ArrayList<Cours> cours = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(cours);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.refresh();
    }
}

       @FXML
private void updateItem() {
    Cours selectedEvent = tableview.getSelectionModel().getSelectedItem();
    if (selectedEvent != null) {
        CoursService dao = new CoursService();
        String nom = NOM.getText();
        
        String des = DESC.getText();
        
        String pdf = PDF.getText();
        

        Cours updatedEvent = new Cours(nom,  des, pdf);
        updatedEvent.setId(selectedEvent.getId());
        try {
            dao.update(updatedEvent);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Get the ArrayList from the service
            ArrayList<Cours> cours = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(cours);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    private void refreshTable() {
    try {
        List<Cours> events = new CoursService().afficherAll();
        ObservableList<Cours> observableEvents = FXCollections.observableArrayList(events);
        tableview.setItems(observableEvents);
    } catch (SQLException ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
}






}
