package GUI;


import Entities.Ligne_commande;
import GUI.BackMainWindowController;
import Services.Ligne_commandeService;
import Utils.AlertUtils;
import Utils.Constants;
import Utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LCommandeManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> commandeCB;
    @FXML
    public TextField quantiteTF;
    @FXML
    public ComboBox<RelationObject> productsCB;
    @FXML
    public TextField priceTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Ligne_commande currentLigne_commande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (RelationObject products : Ligne_commandeService.getInstance().getAllCommandes()) {
            commandeCB.getItems().add(products);
        }

        for (RelationObject products : Ligne_commandeService.getInstance().getAllProductss()) {
            productsCB.getItems().add(products);
        }

        currentLigne_commande = LCommandeShowAllController.currentLigne_commande;

        if (currentLigne_commande != null) {
            topText.setText("Modifier ligne_commande");
            btnAjout.setText("Modifier");

            try {
                productsCB.setValue(currentLigne_commande.getCommandeId());
                quantiteTF.setText(String.valueOf(currentLigne_commande.getQuantite()));
                productsCB.setValue(currentLigne_commande.getProduitId());
                priceTF.setText(String.valueOf(currentLigne_commande.getPrice()));

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter ligne_commande");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            Ligne_commande ligne_commande = new Ligne_commande(
                    commandeCB.getValue(),
                    Integer.parseInt(quantiteTF.getText()),
                    productsCB.getValue(),
                    Float.parseFloat(priceTF.getText())
            );

            if (currentLigne_commande == null) {
                if (Ligne_commandeService.getInstance().add(ligne_commande)) {
                    AlertUtils.makeSuccessNotification("Ligne_commande ajouté avec succés");
                    BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_LIGNE_COMMANDE);
                } else {
                    AlertUtils.makeError("ligne_commande existe deja");
                }
            } else {
                ligne_commande.setId(currentLigne_commande.getId());
                if (Ligne_commandeService.getInstance().edit(ligne_commande)) {
                    AlertUtils.makeSuccessNotification("Ligne_commande modifié avec succés");
                    LCommandeShowAllController.currentLigne_commande = null;
                    BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_LIGNE_COMMANDE);
                } else {
                    AlertUtils.makeError("ligne_commande existe deja");
                }
            }

        }
    }


    private boolean controleDeSaisie() {


        if (commandeCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir commande");
            return false;
        }


        if (quantiteTF.getText().isEmpty()) {
            AlertUtils.makeInformation("quantite ne doit pas etre vide");
            return false;
        }


        try {
            Integer.parseInt(quantiteTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("quantite doit etre un nombre");
            return false;
        }

        if (productsCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir products");
            return false;
        }


        if (priceTF.getText().isEmpty()) {
            AlertUtils.makeInformation("price ne doit pas etre vide");
            return false;
        }


        try {
            Float.parseFloat(priceTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("price doit etre un réel");
            return false;
        }

        return true;
    }
}