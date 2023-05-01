/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import Entities.Scores;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ScoresInterface<T> {
 public void ajoutScores(Scores s);
 public void supprimerScores(int id) throws SQLException;
 public List<Scores> afficherListeScores() throws SQLException;
  
    
}
