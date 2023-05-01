/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Entities.Game_category;
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
public class categoryService implements  InterfaceC {
    
    
    
 Statement ste;
Connection cnx = MyDB.getInstance().getCnx();



    @Override
    public void addCategory(Game_category p) {
try {
            String req1="INSERT INTO `game_category`(`name`) VALUES (?)";
                        PreparedStatement pr = cnx.prepareStatement(req1);

            pr.setString(1, p.getName());
          
          
            
            pr.executeUpdate();
            System.out.println("game category added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateCategory(Game_category p) {
         try {
            String reql = "UPDATE `game_category` SET `name` = '" + p.getName() + "' WHERE `game_category`.`id` = " + p.getId();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(reql);
            System.out.println("game category updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void deleteCategory(int id) {
  try {
            String req = "DELETE FROM `game_category` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("game category deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Game_category> showCategory() {
 
        List<Game_category> pers = new ArrayList<Game_category>();
        try {
        String req = "SELECT * FROM `game_category`";
        ste = cnx.createStatement();
        ResultSet result = ste.executeQuery(req);
        System.out.println(result);
        while (result.next()) {
            Game_category resultPerson = new Game_category(result.getInt("id"), result.getString("name"));
            pers.add(resultPerson);
        }
        System.out.println(pers);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return pers;    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
     public List<String> getNomsCat() throws SQLException {
        String req = "SELECT name FROM game_category";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        List<String> noms = new ArrayList<>();
        while (rs.next()) {
            noms.add(rs.getString("name"));
        }
        return noms;
    
}
     
 @Override
     public int getIdCByNom(String nomCat) throws SQLException {
    int idCat = -1;
    String req = "SELECT id FROM game_category WHERE name=?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, nomCat);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        idCat = rs.getInt("id");
    }
    return idCat;
}     
}
