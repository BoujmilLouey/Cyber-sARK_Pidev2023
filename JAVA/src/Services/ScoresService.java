/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Entities.Scores;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ScoresService implements ScoresInterface  {
     Statement ste;
Connection cnx = MyDB.getInstance().getCnx();





    @Override
    public void ajoutScores(Scores s) {
    try {
        String req = "INSERT INTO `scores`(`score`, `game_id_id`) VALUES (?, ?)";
        PreparedStatement pr = cnx.prepareStatement(req);
        pr.setInt(1, s.getScore());

        // Ajout du score aléatoire entre 1 et 100

        pr.setInt(2, s.getGame_id());


        

        pr.executeUpdate();
        System.out.println("Score ajouté avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public void supprimerScores(int id) throws SQLException {
        String req = "DELETE FROM `score` WHERE `id`=" + id;
        ste = cnx.createStatement();
        int result = ste.executeUpdate(req);
        if (result == 0) {
            System.out.println("Aucun score n'a été supprimé.");
        } else {
            System.out.println("Le score a été supprimé avec succès.");
        }
        }

    @Override
    public List afficherListeScores() throws SQLException {
        String req = "SELECT * FROM `scores`";
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(req);
        List<Scores> scores = new ArrayList<>(); 
        while(rs.next()){
            Scores p = new Scores(rs.getInt("id"),rs.getInt("score"),rs.getInt("game_id_id"),rs.getInt("user_id_id"));
            scores.add(p);
        }
        return scores;
    }    }
 

   

