package GUI;

import Entities.Ligne_commande;
import GUI.BackMainWindowController;
import Services.Ligne_commandeService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LCommandeShowAllController implements Initializable {

    public static Ligne_commande currentLigne_commande;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;


    List<Ligne_commande> listLigne_commande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listLigne_commande = Ligne_commandeService.getInstance().getAll();

        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listLigne_commande);

        if (!listLigne_commande.isEmpty()) {
            for (Ligne_commande ligne_commande : listLigne_commande) {

                mainVBox.getChildren().add(makeLigne_commandeModel(ligne_commande));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeLigne_commandeModel(
            Ligne_commande ligne_commande
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_LIGNE_COMMANDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#commandeIdText")).setText("Commande : " + ligne_commande.getCommandeId());
            ((Text) innerContainer.lookup("#quantiteText")).setText("Quantite : " + ligne_commande.getQuantite());
            ((Text) innerContainer.lookup("#produitIdText")).setText("Products : " + ligne_commande.getProduitId());
            ((Text) innerContainer.lookup("#priceText")).setText("Price : " + ligne_commande.getPrice());


            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierLigne_commande(ligne_commande));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerLigne_commande(ligne_commande));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterLigne_commande(ActionEvent event) {
        currentLigne_commande = null;
        BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_LIGNE_COMMANDE);
    }

    private void modifierLigne_commande(Ligne_commande ligne_commande) {
        currentLigne_commande = ligne_commande;
        BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_LIGNE_COMMANDE);
    }

    private void supprimerLigne_commande(Ligne_commande ligne_commande) {
        currentLigne_commande = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText(" sûr de supprimer la ligne_commande ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (Ligne_commandeService.getInstance().delete(ligne_commande.getId())) {
                BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_LIGNE_COMMANDE);
            } else {
                AlertUtils.makeError("tu peux pas l'effacer");
            }
        }
    }


}
