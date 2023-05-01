/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Games;
import Entities.Scores;
import Entities.User;
import Services.GameService;
import Services.Metier;
import Services.MetierMail;
import Services.ScoresService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class frontJeuxController implements Initializable {

    @FXML
    private Button btnParticiper;
    @FXML
    private Button btnRecherche;
     @FXML
    private Button Home;
    @FXML
    private TextField recherche;
    @FXML
    private GridPane gridPaneJeux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (IOException ex) {
            Logger.getLogger(frontJeuxController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frontJeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onParticiperButtonClick(ActionEvent event) {
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }
    public void afficher() throws IOException, SQLException{
        ScoresService SC =new ScoresService();
        int row=0;
        int col=0;
        GameService GS =new GameService();
        List<Games> ListGame =new ArrayList<>();
        ListGame=GS.showGames();
        for (int i=0;i<ListGame.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item.fxml"));
            AnchorPane anchor =fxmlLoader.load();
            ItemController controller =fxmlLoader.getController();
            controller.setData(ListGame.get(i));

            if (col==3){
            col=0;
            row++;
            }
            int id=ListGame.get(i).getId();
            VBox vbox =new VBox();
            Rating rate=new Rating();
            rate.setRating(ListGame.get(i).getNote());
            rate.setOnMouseClicked(e->{
                GameService gs=new GameService();
                gs.updaterate(id, rate.getRating());
                 MetierMail met = new MetierMail();
                 try {
                        met.sendMail("manelzaabi2015@gmail.com");
                    } catch (Exception ex) {
                        Logger.getLogger(frontJeuxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
           // rate.setRating();
            Button btn =new Button("Participer");
            btn.setUserData(ListGame.get(i));
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Node sourceComponent =(Node)event.getSource();
                    Games game =(Games) sourceComponent.getUserData();
                    
                    
                    int randomScore = (int) (Math.random() * 100) + 1;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Score aléatoire généré");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre score aléatoire est: " + randomScore);
                    alert.showAndWait();
                    Scores s =new Scores(randomScore,game.getId());
                   
                    SC.ajoutScores(s);     
                    Metier met = new Metier();
                    met.sendSms("+21653154488", "  Merci pour votre participation au jeu: " +game.getName() + " ,  Votre score est: "+randomScore +" ");

                   alert.setTitle("PARTICIPATION");
                    alert.setContentText("SMS envoyer avec succes");
                    alert.show();
                   
                      

           
           
            
        }                



                
            });
            vbox.getChildren().add(anchor);
            vbox.getChildren().add(rate);
            vbox.getChildren().add(btn);
            vbox.setAlignment(Pos.CENTER);
            gridPaneJeux.add(vbox, col++, row);
            gridPaneJeux.setMaxWidth(Region.USE_COMPUTED_SIZE);
            gridPaneJeux.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridPaneJeux.setMaxWidth(Region.USE_PREF_SIZE);
            
            gridPaneJeux.setMaxHeight(Region.USE_COMPUTED_SIZE);
            gridPaneJeux.setPrefHeight(Region.USE_COMPUTED_SIZE);
            gridPaneJeux.setMaxHeight(Region.USE_PREF_SIZE);
            
            
            gridPaneJeux.setMargin(anchor, new Insets(10));
            
        }
    
    }
    
    
    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontClient.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Home.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
}
