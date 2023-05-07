package GUI;


import Entities.Commande;
import Entities.Ligne_commande;
import Entities.SingleUser;
import Services.CommandeService;
import Services.SMSNotifier;
import Services.Ligne_commandeService;
import Utils.AlertUtils;
import Utils.Constants;
import Utils.RelationObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PanierShowAllController implements Initializable {

    public static Ligne_commande currentLigne_commande;
    @FXML
    public TableView<Ligne_commande> tableView;
    @FXML
    public TableColumn<Ligne_commande, RelationObject> produitTC;
    @FXML
    public TableColumn<Ligne_commande, Integer> quantiteTC;
    @FXML
    public TableColumn<Ligne_commande, Float> priceTC;
    @FXML
    public Text topText;
    @FXML
    public Button commanderBTN;
    @FXML
    public Text totalTF;
    @FXML
    public Button plusBTN;
    @FXML
    public Button minusButton;

    float prixTotal;
    List<Ligne_commande> listLigne_commande;
    private int currentIndex;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTotal();

        setDisableButtons(false);

        produitTC.setCellValueFactory(new PropertyValueFactory<>("produitId"));
        quantiteTC.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));

        displayData();
    }

    void displayData() {
        Collections.reverse(listLigne_commande);

        ObservableList<Ligne_commande> listTab = FXCollections.observableArrayList();
        if (!listLigne_commande.isEmpty()) {
            listTab.addAll(listLigne_commande);
            tableView.setItems(listTab);
        } else {
            commanderBTN.setDisable(true);
        }
    }

    @FXML
    public void setSelected(MouseEvent mouseEvent) {
        currentIndex = tableView.getSelectionModel().getSelectedIndex();
        currentLigne_commande = tableView.getSelectionModel().getSelectedItem();
        setDisableButtons(false);
    }

    private void setDisableButtons(boolean state) {
        plusBTN.setDisable(state);
        minusButton.setDisable(state);
    }

    @FXML
    public void commander(ActionEvent actionEvent) throws Exception {
        SingleUser  hold = SingleUser.getInstance();
                  int u = hold.getUserID();
                  String a = hold.getUserEmail();
        CommandeService.getInstance().add(
                new Commande(
                        LocalDate.now(),
                        prixTotal,
                        new RelationObject(u,a)
                ));
        
        //SMSNotifier sms = new SMSNotifier();
                
                    //String message ="Votre Commande est valider ";
                    
                  //sms.sendSms(message);
        Mailer.sendMail(a, "Commande", "<h1>Validation Commande</h1> <br/> <h2><b>Votre commande a été ajouté</b></h2>");
        FrontMainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_DISPLAY_ALL_COMMANDE);
    }

    @FXML
    public void plusCommande(ActionEvent actionEvent) {
        float prixUnitaire = currentLigne_commande.getPrice() / currentLigne_commande.getQuantite();
        currentLigne_commande.setQuantite(currentLigne_commande.getQuantite() + 1);
        currentLigne_commande.setPrice(currentLigne_commande.getPrice() + prixUnitaire);

        tableView.getItems().set(currentIndex, currentLigne_commande);
        Ligne_commandeService.getInstance().edit(currentLigne_commande);

        updateTotal();
    }

    @FXML
    public void moinCommande(ActionEvent actionEvent) {
        if (currentLigne_commande.getQuantite() <= 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("tu vas eliminer votre commande , vous etes sur !!");
            alert.setHeaderText(null);
            alert.setContentText("sûr de vouloir supprimer ce produit ?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                if (Ligne_commandeService.getInstance().delete(currentLigne_commande.getId())) {
                    FrontMainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_PANIER);
                } else {
                    AlertUtils.makeError("non!");
                }
            }
            setDisableButtons(true);
            currentLigne_commande = null;
        } else {
            float prixUnitaire = currentLigne_commande.getPrice() / currentLigne_commande.getQuantite();
            currentLigne_commande.setQuantite(currentLigne_commande.getQuantite() - 1);
            currentLigne_commande.setPrice(currentLigne_commande.getPrice() - prixUnitaire);

            tableView.getItems().set(currentIndex, currentLigne_commande);
            Ligne_commandeService.getInstance().edit(currentLigne_commande);
        }
        updateTotal();
    }

    void updateTotal() {
        SingleUser  hold = SingleUser.getInstance();
                  int u = hold.getUserID();
        listLigne_commande = Ligne_commandeService.getInstance().getByUser(u);
        prixTotal = 0;
        for (Ligne_commande ligne_commande : listLigne_commande) {
            prixTotal += ligne_commande.getPrice();
            totalTF.setText("Total : " + prixTotal);
        }
    }
}
