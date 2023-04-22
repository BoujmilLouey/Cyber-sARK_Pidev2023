/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.categorie_produit;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Admin
 */
public class CategorieService implements CategorieInterface<categorie_produit> {
  Statement ste;
Connection cnx = MaConnection.getInstance().getCnx();
    @Override
    public void ajouterCat(categorie_produit c) {
        try {
           String sql1="insert into categorie_produit(type,reference) values (?,?)";
                        PreparedStatement pr = cnx.prepareStatement(sql1);

            pr.setString(1, c.getType());
          
          
            
            pr.executeUpdate();
            System.out.println("category added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void modifierCat(categorie_produit c) {
      try {
      
String reql = "UPDATE `categorie_produit` SET `type` = '" + c.getType() + "', `reference` = '" + c.getReference() + "' WHERE `id` = " + c.getId();

            Statement ste = cnx.createStatement();
            ste.executeUpdate(reql);
            System.out.println("category updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<categorie_produit> afficherCat() {
List<categorie_produit> cat = new ArrayList<categorie_produit>();
        try {
        String req = "SELECT * FROM `categorie_produit`";
        ste = cnx.createStatement();
        ResultSet result = ste.executeQuery(req);
        System.out.println(result);
        while (result.next()) {
            categorie_produit categorie = new categorie_produit(result.getInt("id"), result.getString("type"),result.getString("reference"));
            cat.add(categorie);
        }
        System.out.println(cat);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return cat; 
    }

    @Override
    public void supprimerCat(int id) {
        try {
            String req = "DELETE FROM `categorie_produit` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("category deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    @Override
     public List<String> getNomsCat() throws SQLException {
        String req = "SELECT type FROM categorie_produit";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        List<String> noms = new ArrayList<>();
        while (rs.next()) {
            noms.add(rs.getString("type"));
        }
        return noms;
    
}

    @Override
    public int getIdCByNom(String nomCat) throws SQLException {
      int idCat = -1;
    String req = "SELECT id FROM categorie_produit WHERE type=?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, nomCat);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        idCat = rs.getInt("id");
    }
    return idCat;
    }
    
}
