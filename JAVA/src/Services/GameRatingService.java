/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Utils.MyDB;
import Entities.Game_rating;
import Entities.Games;
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
public class GameRatingService  {
    Statement ste;
 Connection cnx = MyDB.getInstance().getCnx();
    public void addrate(Game_rating gr){
                         try {
            String req1="INSERT INTO `game_rating`(`id`, `rating`, `game_id`) VALUES (?,?,?)";
                        PreparedStatement pr = cnx.prepareStatement(req1);

            pr.setInt(1, gr.getId());
            pr.setInt(2,gr.getRating());
            pr.setObject(3, gr.getGame().getId());
            

           
            pr.executeUpdate();
            System.out.println("game added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Game_rating> showGames() {
        
        List<Game_rating> pers = new ArrayList<Game_rating>();
        try {
        String req = "SELECT * FROM `game_rating`";
        ste = cnx.createStatement();
        ResultSet result = ste.executeQuery(req);
        System.out.println(result);
        while (result.next()) {
            //Game_rating gr=new Game_rating(result.getInt('id'), result.getInt('rating'), result.)
            //pers.add(gr);
        }
        System.out.println(pers);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return pers;
    }
}
