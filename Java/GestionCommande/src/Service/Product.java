/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Commande;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author BAZINFO
 */
public class Product  implements IService<Produit>{
 Connection cnx;

    public Product() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Produit t) throws SQLException {
     String sql = "INSERT INTO `produit`(`id_categorie_produit_id`, `nom`, `reference`,`image`,`prix`,'couleur','poids','description') VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement ste;
      

      try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_categorie_produit_id());
            ste.setString(2, t.getNom());
            ste.setString(3, t.getReference());
            ste.setString(2, t.getImage());
            ste.setDouble(2, t.getPrix());
            ste.setString(2, t.getCouleur());
            ste.setDouble(2, t.getPoids());
            ste.setString(2, t.getDescription());
            ste.executeUpdate();
            System.out.println("produit Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }   

    @Override
    public void modifier(Produit t) throws SQLException {
       try { String sql = "UPDATE `produit` SET  `prix`=?  WHERE id =?";
         PreparedStatement ps = cnx.prepareStatement(sql);
             ps.setDouble(1, t.getPrix());
             ps.executeUpdate();
              System.out.println("produit updated !");
 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void supprimer(Produit t) throws SQLException {
 String sql ="delete from produit where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public List<Produit> recuperer() throws SQLException {
List<Produit> produits = new ArrayList<>();
        String req = "select `id`, `id_categorie_produit_id`, `nom`, `reference`, `image`, `prix`, `couleur`, `poids`, `description` from produit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Produit p = new Produit();
            p.setId(rs.getInt("id"));
              p.setId_categorie_produit_id(rs.getInt("id_categorie_produit_id"));
                p.setNom(rs.getString("nom"));
           p.setReference(rs.getString("reference"));
             p.setImage(rs.getString("image"));
                p.setPrix(rs.getDouble("prix"));
                p.setCouleur(rs.getString("couleur"));
                p.setPoids(rs.getDouble("poids"));
                p.setDescription(rs.getString("description"));


            
            produits.add(p);
        }
        return produits;
    }    
//     public Product findById(int id) {
//        Produit p = null;
//        try {
//            String req = "select * FROM produit where id=? ";
//            Statement st = cnx.createStatement();
//             ResultSet rs = st.executeQuery(req);            
//             st.setInt(1, id);
//            while (rs.next()) {
//            Produit p = new Produit();
//            p.setId(rs.getInt("id"));
//              p.setId_categorie_produit_id(rs.getInt("id_categorie_produit_id"));
//              p.setNom(rs.getString("nom"));
//              p.setReference(rs.getString("reference"));
//              p.setImage(rs.getString("image"));
//                p.setPrix(rs.getDouble("prix"));
//                p.setCouleur(rs.getString("couleur"));
//                p.setPoids(rs.getDouble("poids"));
//                p.setDescription(rs.getString("descripition"));
//            }
//        } catch (SQLException a) {
//            System.out.println(a.getMessage());
//        }
//        return p;
//    }
}