/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Entities.Games;
import Entities.Game_category;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author ASUS
 */   
public class GameService implements InterfaceService {

    Statement ste;
 Connection cnx = MyDB.getInstance().getCnx();

  

    @Override
    public void addGames(Games p) {
                 try {
            String req1="INSERT INTO `game`(`name`, `description`, `image`, `game_categorie_id`,`note`) VALUES (?,?,?,?,?)";
                        PreparedStatement pr = cnx.prepareStatement(req1);

            pr.setString(1, p.getName());
            pr.setString(2, p.getDescription());
            
            if (p.getImage()!=null)
               pr.setString(3, p.getImage());
            else 
               pr.setString(3, null);
            
            pr.setInt(4, p.getIdCat());
            pr.setDouble(5, p.getNote());
            

           
            pr.executeUpdate();
            System.out.println("game added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

            }

    @Override
    public void updateGame(Games p) {
         try {
            String reql = "UPDATE `game` SET `name` = '" + p.getName() + "', `description` = '" + p.getDescription() + "',`image` = '" + p.getImage() + "' WHERE `game`.`id` = " + p.getId();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(reql);
            System.out.println("game updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  

    @Override
    public List<Games> showGames() {
        
        List<Games> pers = new ArrayList<Games>();
        try {
        String req = "SELECT * FROM `game`";
        ste = cnx.createStatement();
        ResultSet result = ste.executeQuery(req);
        System.out.println(result);
        while (result.next()) {
            Games resultPerson = new Games(result.getInt("id"), result.getString("name"), result.getString("description"),result.getString("image"),result.getInt("game_categorie_id"),result.getDouble("note"));
            pers.add(resultPerson);
        }
        System.out.println(pers);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return pers;
    }

    @Override
    public void deleteGame(int id) {
         try {
            String req = "DELETE FROM `game` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("game deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public Games findgamebyid(int id) throws SQLException{
       String sql="SELECT * from game where id=?";
       PreparedStatement ste;
       ste=cnx.prepareStatement(sql);
       ste.setInt(1, id);
       ResultSet rs=ste.executeQuery();
       if(rs.next()){
           Games g=new Games(rs.getInt(1), rs.getInt(7));
           return g;
       }
       return null;
   }

   
 public void updaterate(int id,double rate){
        Games pp=null;
        try {
            pp=findgamebyid(id);
        } catch (SQLException ex) {
            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pp.getNote()==0){
            String sql="update game set note=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setDouble(1, rate);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("note modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else{
                String sql="update game set note=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setDouble(1, (rate+pp.getNote())/2);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("note modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                }
        
    
}
  public List<Games> triNom() {

        List<Games> list1 = new ArrayList<>();
        List<Games> list2 = showGames();

        list1 = list2.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        return list1;

    }

}
