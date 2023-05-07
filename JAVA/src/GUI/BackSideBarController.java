package GUI;

import GUI.AdminPanelController;
import Utils.Animations;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class BackSideBarController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_PRIMARY = Color.web("#66004d");
    private final Color COLOR_DARK = new Color(1, 1, 1, 0.65);
    private Button[] liens;

    @FXML
    private Button btnCommandes;
    @FXML
    private Button btnLigne_commandes;
    @FXML
    private Button btnProductss;
    @FXML
    private AnchorPane mainComponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
                btnCommandes,
                btnLigne_commandes,
                btnProductss,
        };

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
        }
        btnCommandes.setTextFill(Color.WHITE);
        btnLigne_commandes.setTextFill(Color.WHITE);
        btnProductss.setTextFill(Color.WHITE);

    }

    @FXML
    private void afficherCommandes(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_COMMANDE);

        btnCommandes.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnCommandes, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherLigne_commandes(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_LIGNE_COMMANDE);

        btnLigne_commandes.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnLigne_commandes, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherProductss(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_PRODUCTS);

        btnProductss.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnProductss, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_PRIMARY, 0, false);
        }
        BackMainWindowController.getInstance().loadInterface(link);
    }

    
}
