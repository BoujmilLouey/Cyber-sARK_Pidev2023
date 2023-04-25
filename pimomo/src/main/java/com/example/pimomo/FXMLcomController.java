package com.example.pimomo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class FXMLcomController implements Initializable {

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
    private TableColumn<Cours, String> Ncours;

    @FXML
    private TableView<Commentaire> tableview;

    @FXML
    private TableColumn<Commentaire, String> coldesc;

    @FXML
    private TableColumn<Commentaire, Integer> colnote;
    @FXML
    private Button home;

  private ObservableList<Commentaire> observableList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load events from database into the tableview
        CommentaireService dao = new CommentaireService();
        try {
            // Get the ArrayList from the service
            ArrayList<Commentaire> commentaires = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(commentaires);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

            // Set cell value factories for the table columns
            coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            colnote.setCellValueFactory(new PropertyValueFactory<>("note"));
            Ncours.setCellValueFactory(new PropertyValueFactory<>("nom")); // Nouvelle colonne

            // Load course names into the ComboBox
            CoursService coursService = new CoursService();
            ArrayList<Cours> courses = coursService.afficherAll();
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


                        return coursService.getCoursByName(string);
                    }
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void addItem(ActionEvent event) {
        CommentaireService dao = new CommentaireService();

        String desc = DESC.getText();
        int note = Integer.parseInt(NOTE.getText());

        // Récupérer le cours sélectionné dans le ComboBox
        Cours cours = scrollCours.getValue();


            Commentaire commentaire = new Commentaire(desc, note, cours.getId());

            try {
                dao.ajouter(commentaire);

                // Refresh the TableView
                tableview.getItems().clear();

                try {
                    // Get the ArrayList from the service
                    ArrayList<Commentaire> commentaires = dao.afficherAll();

                    // Convert the ArrayList to an ObservableList
                    observableList = FXCollections.observableArrayList(commentaires);

                    // Set the ObservableList as the data source for the TableView
                    tableview.setItems(observableList);

                } catch (SQLException ex) {
                    Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableview.refresh();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Get the ArrayList from the service
            ArrayList<Commentaire> commentaires = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(commentaires);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.refresh();
    }
}

       @FXML
private void updateItem() {
    Commentaire selectedEvent = tableview.getSelectionModel().getSelectedItem();
    if (selectedEvent != null) {
        CommentaireService dao = new CommentaireService();
       
        
        String description = DESC.getText();
 int note = Integer.parseInt(NOTE.getText());
        

        Commentaire updatedEvent = new Commentaire(  description, note);
        updatedEvent.setId(selectedEvent.getId());
        try {
            dao.update(updatedEvent);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Get the ArrayList from the service
            ArrayList<Commentaire> commentaires = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(commentaires);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    private void refreshTable() {
    try {
        List<Commentaire> events = new CommentaireService().afficherAll();
        ObservableList<Commentaire> observableEvents = FXCollections.observableArrayList(events);
        tableview.setItems(observableEvents);
    } catch (SQLException ex) {
        Logger.getLogger(FXMLcomController.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    void chooseCours(ActionEvent event) {
        Cours coursChoisi = scrollCours.getValue();
    }

   
}
