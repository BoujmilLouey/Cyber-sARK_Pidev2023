    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Games;
import java.util.List;


public interface InterfaceService {

       

  public void addGames(Games p);
  public void updateGame(Games p);
  public void deleteGame(int id);
  public List<Games> showGames();

  
    
}
