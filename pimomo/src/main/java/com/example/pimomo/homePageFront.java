package com.example.pimomo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.YearMonth;

public class homePageFront {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button CompteHomeButton;

    @FXML
    private Button deconnexionButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button utilisateurHomeButton;

    @FXML
    private Button produitHomeButton;

    @FXML
    private Button Bcalendrier;

    @FXML
    private Button CoursHomeButton;
    @FXML
    private Button CommentaireHomeButton;

    @FXML
    void handleCompteHomeButton() {

    }

    @FXML
    void handleCoursHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AffCours.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (CoursHomeButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void handleCommentaireHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLcom.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (CommentaireHomeButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void onBcalendrier() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Bcalendrier.getScene().getWindow());
        stage.setScene(scene);
        stage.show();



        /*
        Stage stage = (Stage) (Bcalendrier.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fullCalendar.fxml"));
        stage.setScene(new Scene(loader.load()));
         Get the controller and add the calendar view to it
        Controller controller = loader.getController();
        controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        stage.show();

 */

    }

    @FXML
    void handleDeconnexionButton() {

    }

    @FXML
    void handleProduitHomeButton() {

    }

    @FXML
    void handleUtilisateurHomeButton() {

    }

}
