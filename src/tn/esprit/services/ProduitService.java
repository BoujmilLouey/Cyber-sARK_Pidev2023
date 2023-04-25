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
import tn.esprit.entities.produit;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Admin
 */
public class ProduitService implements ProduitInterface {
        Statement ste;
Connection cnx = MaConnection.getInstance().getCnx();
    @Override
    public void addProduit(produit p) {
        try {
            String req1="INSERT INTO `produit`(`nom`, `reference`, `prix`, `couleur`,`poids`,`description`,`image`,`categorie_id`) VALUES (?,?,?,?,?,?,?,?)";
                        PreparedStatement pr = cnx.prepareStatement(req1);

            pr.setString(1, p.getNom());
            pr.setString(2, p.getReference());
            pr.setString(3, p.getPrix());
            pr.setString(4, p.getCouleur());
            pr.setString(5, p.getPoids());
            pr.setString(6, p.getDescription());
            
            if (p.getImage()!=null)
               pr.setString(7, p.getImage());
            else 
               pr.setString(7, null);
            
            pr.setInt(8,p.getCategorie_id());
            

           
            pr.executeUpdate();
            System.out.println("product added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

            }

    

    @Override
    public void updateProduit(produit p) {
         try {
            String reql = "UPDATE `produit` SET `nom` = '" + p.getNom() + "', `reference` = '" + p.getReference()+ "', `prix` = '" + p.getPrix()+ "', `couleur` = '" + p.getCouleur()+ "',`poids` = '" + p.getPoids()+ "', `description` = '" + p.getDescription()+ "', `image` = '" + p.getImage()+ "' WHERE `produit`.`id` = " + p.getId();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(reql);
            System.out.println("product updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteProduit(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("product deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<produit> showProduit() {
         List<produit> pers = new ArrayList<produit>();
        try {
        String req = "SELECT * FROM `produit`";
        ste = cnx.createStatement();
        ResultSet result = ste.executeQuery(req);
        System.out.println(result);
        while (result.next()) {
            produit resultPerson = new produit(result.getInt("id"), result.getString("nom"), result.getString("reference"),result.getString("prix"),result.getString("couleur"),result.getString("poids"),result.getString("description"),result.getString("image"),result.getInt("categorie_id"));
            pers.add(resultPerson);
        }
        System.out.println(pers);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return pers;
    }
    
    public String getCategoryNameForProduct(produit product) {
        categorie_produit category = product.getCategorie() ;
        if (category == null) {
            return null;
        }
        return category.getType();
    }
    }
    

 
   
    

