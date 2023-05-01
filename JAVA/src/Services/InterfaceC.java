/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Game_category;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceC {
  public void addCategory(Game_category p);
  public void updateCategory(Game_category p);
  public void deleteCategory(int id);
  public List<Game_category> showCategory();
  public List<String>getNomsCat()throws SQLException;
  public int getIdCByNom(String nomCat) throws SQLException;
    
}
