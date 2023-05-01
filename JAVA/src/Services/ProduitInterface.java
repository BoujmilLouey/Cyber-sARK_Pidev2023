/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import Entities.produit;

/**
 *
 * @author Admin
 */
public interface ProduitInterface {
    public void addProduit(produit p);
  public void updateProduit(produit p);
  public void deleteProduit(int id);
  public List<produit> showProduit();
}
