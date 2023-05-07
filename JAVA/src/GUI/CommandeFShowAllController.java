package GUI;


import Entities.Commande;
import Entities.CurrentUser;
import Entities.Globals;
import Entities.SingleUser;
import GUI.FrontMainWindowController;
import Services.CommandeService;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CommandeFShowAllController implements Initializable {

    public static Commande currentCommande;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;
    @FXML
    public TextField searchTF;

    List<Commande> listCommande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SingleUser  hold = SingleUser.getInstance();
                  int u = hold.getUserID();
        
        listCommande = CommandeService.getInstance().getByUserId(u);

        displayData("");
    }

    void displayData(String searchText) {
        mainVBox.getChildren().clear();

        Collections.reverse(listCommande);

        if (!listCommande.isEmpty()) {
            for (Commande commande : listCommande) {
                if (commande.getUserId().getName().toLowerCase().startsWith(searchText.toLowerCase())) {
                    mainVBox.getChildren().add(makeCommandeModel(commande));
                }

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeCommandeModel(
            Commande commande
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_COMMANDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#dateCommandeText")).setText("DateCommande : " + commande.getDateCommande());
            ((Text) innerContainer.lookup("#montantCommandeText")).setText("MontantCommande : " + commande.getMontantCommande());
            ((Text) innerContainer.lookup("#userIdText")).setText("User : " + commande.getUserId());


            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierCommande(commande));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerCommande(commande));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterCommande(ActionEvent event) {
        currentCommande = null;
        FrontMainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_COMMANDE);
    }

    private void modifierCommande(Commande commande) {
        currentCommande = commande;
        FrontMainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_MANAGE_COMMANDE);
    }

    private void supprimerCommande(Commande commande) {
        currentCommande = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer commande ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (CommandeService.getInstance().delete(commande.getId())) {
                FrontMainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_COMMANDE);
            } else {
                AlertUtils.makeError("Could not delete commande");
            }
        }
    }


    @FXML
    private void search(KeyEvent event) {
        displayData(searchTF.getText());
    }


}
