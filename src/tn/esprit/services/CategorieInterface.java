/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategorieInterface<C> {
    public void ajouterCat(C c);
    public void modifierCat(C c);
    public List<C> afficherCat();
    public void supprimerCat(int id);
    public List<String>getNomsCat()throws SQLException;
  public int getIdCByNom(String nomCat) throws SQLException;
}
