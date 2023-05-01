/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Entities.categorie_produit;
import Entities.produit;
import Utils.MyDB;
/**
 *
 * @author Admin
 */
public class ProduitService implements ProduitInterface {
        Statement ste;
Connection cnx = MyDB.getInstance().getCnx();
    @Override
    public void addProduit(produit p) {
        try {
            String req1="INSERT INTO `produit`(`nom`, `reference`, `prix`, `couleur`,`description`,`poids`,`image`,`categorie_id`) VALUES (?,?,?,?,?,?,?,?)";
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
    
    
    public produit ReadByNVid(int id) {
        produit ee = new produit();
        String condition = (" id ='" + id + "'");
        String requete = "SELECT * FROM produit WHERE " + condition + " LIMIT 1";
        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                ee = new produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("reference"),
                        rs.getString("prix"),
                        rs.getString("couleur"),
                        rs.getString("poids"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("categorie_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n" + e);
        }
        return ee;
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
    
       
    public List<produit> Search(String t) {

        List<produit> list1 = new ArrayList<>();
        List<produit> list2 = showProduit();
        list1 = (list2.stream().filter(c -> c.getNom().startsWith(t)).collect(Collectors.toList()));
        
    return list1;
    }

    public List<produit> triNom() {

        List<produit> list1 = new ArrayList<>();
        List<produit> list2 = showProduit();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNom().compareTo(o2.getNom())).collect(Collectors.toList());
        return list1;

    }
    public List<produit> triReference() {

        List<produit> list1 = new ArrayList<>();
        List<produit> list2 = showProduit();

        list1 = list2.stream().sorted((o1, o2) -> o1.getReference().compareTo(o2.getReference())).collect(Collectors.toList());

        return list1;

    }
    
    }
    

 
   
    

