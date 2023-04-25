/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyDB;
import entities.Commande;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utils.MyDB.getInstance;

/**
 *
 * @author BAZINFO
 */
public class CommandeCRUD  implements IService<Commande>{
    
       Connection cnx;

    public CommandeCRUD() {
        cnx = MyDB.getInstance().getCnx();
    }

 

    @Override
    public void ajouter(Commande t) throws SQLException {
    String sql="insert into commande(date_commande,montant_commande) values (?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setDate(1, t.getDate_commande());
            ste.setInt(2, t.getMontant_commande());
            
            ste.executeUpdate();
            System.out.println("Commande Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Commande c) throws SQLException {
try {
            String req = "UPDATE `commande` SET `date_commande` = ?, `montant_commande` = ?  WHERE id  = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, c.getDate_commande());
            ps.setInt(2, c.getMontant_commande());
            
            ps.setInt(3, c.getId());
            ps.executeUpdate();
            System.out.println("commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Commande t) throws SQLException {
         String sql ="delete from commande where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    

    }

    @Override
    public List<Commande> recuperer() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Commande c = new Commande();
            c.setId(rs.getInt("id"));
              c.setDate_commande(rs.getDate("date_commande"));
                c.setMontant_commande(rs.getInt("montant_commande"));
            
            commandes.add(c);
        }
        return commandes;
    }

   
     public  ResultSet Commandecount(){
      try {
            String req = "SELECT commande.date_commande, COUNT(*) as count_commande FROM commande ";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            return RS ;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    
    
    return null;
    
}
}
