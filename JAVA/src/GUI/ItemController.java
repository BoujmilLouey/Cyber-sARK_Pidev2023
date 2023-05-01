package GUI;

import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Services.MyListener;
import Entities.Games;
import Entities.Scores;
import Utils.MyDB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import java.util.logging.Logger;

public class ItemController {
    @FXML
    private Label bestScoreLabel;
    @FXML
    private ImageView imgJeux;
    @FXML
    private Label nameJeux;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Game);
    }

    private Games Game;
    private MyListener myListener;
Statement ste;
 Connection cnx = MyDB.getInstance().getCnx();
    public void setData(Games Game) throws SQLException {
        this.Game = Game;
        nameJeux.setText(Game.getName());
        try {
            imgJeux.setImage(new Image(new FileInputStream(Game.getImage())));
            
            //Image image = new Image(getClass().getResourceAsStream(Game.getImage()));
            //imgJeux.setImage(image);
            
         // Récupérer les scores de la base de données
        String sql = "SELECT score FROM scores WHERE game_id_id = ?";
        
   PreparedStatement pr = cnx.prepareStatement(sql);
        pr.setInt(1, Game.getId());
     ResultSet resultSet = pr.executeQuery();

        // Trouver le meilleur score pour le jeu actuel
        int bestScore = 0;
        while (resultSet.next()) {
            int score = resultSet.getInt("score");
            if (score > bestScore) {
                bestScore = score;
            }
        }

        // Afficher le meilleur score dans l'interface
        bestScoreLabel.setText("Meilleur score : " + bestScore);
        
        
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
