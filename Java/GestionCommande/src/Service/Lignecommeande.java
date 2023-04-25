
package Service;

import entities.Ligne_commande;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author BAZINFO
 */
public class Lignecommeande implements IService<Ligne_commande> {
Connection cnx;

    public Lignecommeande() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Ligne_commande t) throws SQLException {
         String req = "insert into ligne_commande(produit_id,commande_id,quantite,price) values(" + t.getId_produit() + "," + 5 + "," + t.getQuantite() + "," + t.getPrice()+ ")";
         Statement st = cnx.createStatement();
         st.executeUpdate(req); 
    }

    @Override
    public void modifier(Ligne_commande t) throws SQLException {
        System.out.println(t);
        int qte=t.getQuantite();        
         qte++;
         t.setQuantite(qte);
         
        System.out.println(qte);
         String req = "update ligne_commande set produit_id= ? , commande_id =? ,  quantite = ? , price= ? where id = ?";
         
         PreparedStatement ps = cnx.prepareStatement(req);
         System.out.println(qte);
         ps.setInt(1, t.getId_produit());
         ps.setInt(2, t.getCommande_id());          
         ps.setInt(3,t.getQuantite());
         ps.setInt(4, t.getPrice());
         ps.setInt(5, t.getId());
         System.out.println(ps);
         ps.executeUpdate();    
    }

    @Override
    public void supprimer(Ligne_commande t) throws SQLException {
      String sql ="delete from ligne_commande where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Ligne_commande> recuperer() throws SQLException {
    List<Ligne_commande> ligne_commandes = new ArrayList<>();
        String req = "select * from ligne_commande";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Ligne_commande p = new Ligne_commande();
            p.setId(rs.getInt("id"));
            p.setId_produit(rs.getInt("produit_id"));
            p.setCommande_id(rs.getInt("commande_id"));
            p.setQuantite(rs.getInt("quantite"));
            p.setPrice(rs.getInt("price"));

            
            ligne_commandes.add(p);
        }
        return ligne_commandes;    }

 
    public int sommeTotale() throws SQLException {
    //List<Ligne_commande> ligne_commandes = new ArrayList<>();
        String req = "select * from ligne_commande";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        int totale = 0;
        while(rs.next()){
            Ligne_commande p = new Ligne_commande();
            p.setId(rs.getInt("id"));
            p.setId_produit(rs.getInt("produit_id"));
            p.setCommande_id(rs.getInt("commande_id"));
            p.setQuantite(rs.getInt("quantite"));
            p.setPrice(rs.getInt("price"));

            totale+=rs.getInt("quantite")*rs.getInt("price");
            //System.out.println(totale);
        }
        return totale;    }
//       public Integer findQteByproductid(int id) throws SQLException {
//        int qte = 0;
//        try {
//            String req = "select quantite FROM ligne_commande where produit_id=? ";
//        Statement st = cnx.createStatement();
//        ResultSet rs = st.executeQuery(req);
//            st.setInt(1, id);
//            while (rs.next()) {
//               qte = rs.getInt("quantite");
//            }
//        } catch (SQLException a) {
//            System.out.println(a.getMessage());
//        }
//        return qte;
//    }
}
