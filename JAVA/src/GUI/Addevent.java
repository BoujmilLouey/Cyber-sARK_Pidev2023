package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Services.EventService;
import Entities.Event;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Addevent {

    @FXML
    private TextField T;

    @FXML
    private DatePicker Tdate;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button Bhome;

    @FXML
    private TableView<Event> table;

    @FXML
    private TableColumn<Event, String> nomEvent;

    @FXML
    private TableColumn<Event, Date> calDate;


    private ObservableList<Event> observableList;
    private void refreshTable() throws SQLException {
        EventService eventService = new EventService();
        ObservableList<Event> eventList = FXCollections.observableArrayList(eventService.getAllEvents());
        table.setItems(eventList);
    }


    @FXML
    public void initialize() {
        nomEvent.setCellValueFactory(new PropertyValueFactory<>("title"));
        calDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(Addevent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @FXML
    private void addItem(ActionEvent event) {
        EventService eventService = new EventService();

        String title = T.getText();
        LocalDate date = Tdate.getValue();

        // Vérifier que les champs ne sont pas vides
        if (title.isEmpty() || date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Certains champs sont vides");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        Event event1 = new Event(title, java.sql.Date.valueOf(date));

        try {
            eventService.ajouter(event1);

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Nouvel événement ajouté avec succès");
            alert.setContentText("Titre : " + title + "\nDate : " + date.toString());
            alert.showAndWait();

            // Réinitialiser les champs après l'ajout
            T.setText("");
            Tdate.setValue(null);
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

            // Afficher un message d'erreur en cas d'échec de l'ajout
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText("Une erreur est survenue lors de l'ajout de l'événement");
            alert.setContentText("Veuillez réessayer.");
            alert.showAndWait();
        }
    }


    @FXML
    private void deleteItem(ActionEvent event) {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        EventService eventService = new EventService();
        if (selectedEvent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Supprimer l'événement ?");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet événement ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    eventService.delete(selectedEvent);

                    // Afficher un message de succès
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suppression réussie");
                    alert.setHeaderText("Événement supprimé avec succès");
                    alert.setContentText("Titre : " + selectedEvent.getTitle());
                    alert.showAndWait();

                    // Actualiser le tableau après la suppression
                    table.getItems().remove(selectedEvent);
                    refreshTable();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                    // Afficher un message d'erreur en cas d'échec de la suppression
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de suppression");
                    alert.setHeaderText("Une erreur est survenue lors de la suppression de l'événement");
                    alert.setContentText("Veuillez réessayer.");
                    alert.showAndWait();
                }
            }
        } else {
            // Aucun événement sélectionné, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setHeaderText("Aucun événement sélectionné");
            alert.setContentText("Veuillez sélectionner un événement dans le tableau.");
            alert.showAndWait();
        }

    }


    @FXML
    private void updateItem(ActionEvent event) {
        Event selectedEvent = table.getSelectionModel().getSelectedItem();
        EventService eventService = new EventService();
        if (selectedEvent != null) {
            String title = T.getText();
            LocalDate date = Tdate.getValue();

            // Vérifier que les champs ne sont pas vides
            if (title.isEmpty() || date == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Certains champs sont vides");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;
            }

            selectedEvent.setTitle(title);
            selectedEvent.setDate(java.sql.Date.valueOf(date));

            try {
                eventService.update(selectedEvent);

                // Afficher un message de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mise à jour réussie");
                alert.setHeaderText("Événement mis à jour avec succès");
                alert.setContentText("Titre : " + title + "\nDate : " + date.toString());
                alert.showAndWait();

                // Réinitialiser les champs après la mise à jour
                T.setText("");
                Tdate.setValue(null);
                refreshTable();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                // Afficher un message d'erreur en cas d'échec de la mise à jour
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de mise à jour");
                alert.setHeaderText("Une erreur est survenue lors de la mise à jour de l'événement");
                alert.setContentText("Veuillez réessayer.");
                alert.showAndWait();
            }
        } else {
            // Aucun événement sélectionné, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setHeaderText("Aucun événement sélectionné");
            alert.setContentText("Veuillez sélectionner un événement dans le tableau.");
            alert.showAndWait();
        }
    }


    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Bhome.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }


}
