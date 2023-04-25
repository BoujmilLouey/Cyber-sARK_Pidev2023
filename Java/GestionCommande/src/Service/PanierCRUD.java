/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Panier;
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
public  class PanierCRUD implements IService<Panier> {


   Connection cnx;

    public PanierCRUD() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    
    public void supprimerProdPanier(Panier p) {
        String sql = "delete from ligne_commande where id=?";        
        PreparedStatement ste;

        try {
            
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
            System.out.println("produit supprimé de panier !");

        } catch (SQLException ex) {
            System.out.println("produit non supprimé de panier !");
            System.out.println(ex.getMessage());
        }
    }

//    public void modifierQteProdPanier(Panier p, Produit prod, int quantite) {
//        try {
//            String req = "UPDATE `panier_produit` SET `Quantite` = ? WHERE id_panier  = ? AND ref_produit = ?";
//            PreparedStatement ps = conn.prepareStatement(req);
//            ps.setInt(1, quantite);
//            ps.setInt(2, p.getId_panier());
//            ps.setString(3, prod.getRef_produit());
//            ps.executeUpdate();
//            System.out.println("quantite produit modifie !");
//        } catch (SQLException ex) {
//            Logger.getLogger(panierCRUD.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("quantite produit non modifie !");
//
//        }
//
//    }
//    public boolean verifyExistance(Panier p, Produit p1) throws SQLException{
//           PreparedStatement req = cnx.prepareStatement("SELECT *  FROM ligne_commande WHERE id = ? ");
//           PreparedStatement ste;
//         try {
//            ste = cnx.prepareStatement(req);
//            ste.setInt(1, p.getId());
//            ste.setString(2, p1.getReference());
//            ResultSet rs = req.executeQuery();
//            if (!rs.next()) {
//                System.out.println("produit non existant");
//                return false ; 
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return true;
//        
//    }
    public void ajouterProdPanier(Panier p, Produit p1, int quantite) {
                    String req = "INSERT INTO `panier_produit`(`id_panier`, `ref_produit`,`Quantite`) VALUES (?,?,?)";
           PreparedStatement ste;

        try {
            ste = cnx.prepareStatement(req);

            ste.setInt(1, p.getId());
            ste.setString(2, p1.getReference());
            ste.setInt(3, quantite);
            ste.executeUpdate();
            System.out.println("produit ajouté au panier!!!");

        } catch (SQLException ex) {
            System.out.println("produit non ajouté au panier!!!");
            System.out.println(ex.getMessage());
        }
    }
//m=update nbr elements et total

    
    public void modifierPanier(Panier p) {
        try {
            String req = "UPDATE `ligne_commande` SET  `price` = ? , WHERE id  = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(3, p.getId());
            // list des produits pour calculer le nombre d'article et le somme 
            List<Produit> list_p = new ArrayList<>();
            String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
            PreparedStatement psp = cnx.prepareStatement(produitfilter);
            psp.setInt(1, p.getId());
            ResultSet rp = psp.executeQuery();
            while (rp.next()) {
                int quantite = rp.getInt(3);
                String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                PreparedStatement pss = cnx.prepareStatement(filter);
                pss.setString(1, rp.getString(2));
                ResultSet rr = pss.executeQuery();
                while (rr.next()) {
                    Produit p1 = new Produit();
                    p1.setReference(rr.getString(1));
                    p1.setPrix(rr.getFloat(5));
                    p1.setPoids(quantite);
                    list_p.add(p1);
                }
                p.setList(list_p);
            }
            int numarticle = p.numArticle(list_p);
            float somme = p.sommePanier(list_p);

            ps.setInt(1, numarticle);
            ps.setFloat(2, somme);

            ps.executeUpdate();
            System.out.println("panier updated !");
        } catch (SQLException ex) {
            System.out.println("panier non updated !");
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimerPanier(Panier p) {
        try {
            String req = "DELETE FROM `ligne_commande` WHERE id = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("panier deleted !");
        } catch (SQLException ex) {
            System.out.println("panier non deleted !");
        }
    }

    
    public List<Panier> afficherPanier() {
        List<Panier> list = new ArrayList<>();

        try {
            String req = "Select * from ligne_commande";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                List<Produit> list_p = new ArrayList<>();
                Panier p = new Panier();

                p.setId(RS.getInt(1));
                p.setList(afficherListProduitPanier(p));

//                //utilisateur
//                UtilisateurCRUD uti = new UtilisateurCRUD();
//                int user_id = RS.getInt(2);
//                Utilisateur u = uti.getUserByID(user_id);
//                p.setU1(u);
//                int numarticle = p.numArticle(p.getList());
//                p.setNombre_article(numarticle);
//                float somme = p.sommePanier(p.getList());
//                p.setQuantite(somme);
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    
//    public Panier filtreByuser(Utilisateur u1) {
//        List<panier> list = new ArrayList<>();
//        panier p = new panier();
//        try {
//            String query = "SELECT * FROM panier WHERE id_user = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, u1.getid_user());
//            ResultSet RS = ps.executeQuery();
//            while (RS.next()) {
//                List<produit> list_p = new ArrayList<>();
//
//                p.setId_panier(RS.getInt(1));
//                p.setList(afficherListProduitPanier(p));
//                //utilisateur
//                UtilisateurCRUD uti = new UtilisateurCRUD();
//                int user_id = RS.getInt(2);
//                Utilisateur u = uti.getUserByID(user_id);
//                p.setU1(u);
//                int numarticle = p.numArticle(p.getList());
//                p.setNombre_article(numarticle);
//                float somme = p.sommePanier(p.getList());
//                p.setTotal_panier(somme);
//                list.add(p);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return p;
//    }

//    @Override
//    public panier filtreByidPanier(int pan) {
//        panier p = new panier();
//        try {
//            String query = "SELECT * FROM panier WHERE id_panier = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, pan);
//            ResultSet RS = ps.executeQuery();
//            while (RS.next()) {
//                List<produit> list_p = new ArrayList<>();
//
//                p.setId_panier(RS.getInt(1));
//                p.setList(afficherListProduitPanier(p));
//                //utilisateur
//                UtilisateurCRUD uti = new UtilisateurCRUD();
//                int user_id = RS.getInt(2);
//                Utilisateur u = uti.getUserByID(user_id);
//                p.setU1(u);
//                int numarticle = p.numArticle(p.getList());
//                p.setNombre_article(numarticle);
//                float somme = p.sommePanier(p.getList());
//                p.setTotal_panier(somme);
//
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return p;
//    }

    
    public List<Produit> afficherListProduitPanier(Panier p) {
        List<Produit> list_p = new ArrayList<>();
        try {
            // list des produits

            String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
            PreparedStatement psp = cnx.prepareStatement(produitfilter);
            psp.setInt(1, p.getId());
            ResultSet rp = psp.executeQuery();

            while (rp.next()) {
                int quantite = rp.getInt(3);
                String filter = "SELECT *  FROM produit WHERE `reference`= ?";
                PreparedStatement pss = cnx.prepareStatement(filter);
                pss.setString(1, rp.getString(2));
                ResultSet rr = pss.executeQuery();
                while (rr.next()) {
                    Produit p1 = new Produit();
                    p1.setReference(rr.getString(1));
                    //p1.setLibelle(rr.getString(2));
                    p1.setDescription(rr.getString(3));
                    p1.setImage(rr.getString(4));
                    p1.setPrix(rr.getFloat(5));
                    p1.setPoids(quantite);
                   // categorie cc = new categorie();
                    //cc.setId_categorie(rr.getInt(7));
                    //p1.setCategoriee(cc);
                    list_p.add(p1);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_p;
    }

//    public List<produit> afficherListProduityasmine(panier p) {
//        List<produit> list_p = new ArrayList<>();
//        try {
//            // list des produits
//
//            String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
//            PreparedStatement psp = conn.prepareStatement(produitfilter);
//            psp.setInt(1, p.getId_panier());
//            ResultSet rp = psp.executeQuery();
//
//            while (rp.next()) {
//                String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
//                PreparedStatement pss = conn.prepareStatement(filter);
//                pss.setString(1, rp.getString(2));
//                ResultSet rr = pss.executeQuery();
//                while (rr.next()) {
//                    produit p1 = new produit();
//                    p1.setRef_produit(rr.getString(1));
//                    p1.setLibelle(rr.getString(2));
//                    p1.setDescription(rr.getString(3));
//                    p1.setImage(rr.getString(4));
//                    p1.setPrix_vente(rr.getFloat(5));
//                    p1.setQuantite(rr.getInt(6));
//                    categorie cc = new categorie();
//                    cc.setId_categorie(rr.getInt(7));
//                    p1.setCategoriee(cc);
//                    list_p.add(p1);
//                }
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(panierCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list_p;
//    }

    @Override
    public void ajouter(Panier t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Panier t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Panier t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Panier> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
