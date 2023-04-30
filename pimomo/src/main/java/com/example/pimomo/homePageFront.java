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
    void handleCommentaireHomeButton() {

    }



    @FXML
    void onBcalendrier() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Bcalendrier.getScene().getWindow());
        stage.setScene(scene);
        stage.show();





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
