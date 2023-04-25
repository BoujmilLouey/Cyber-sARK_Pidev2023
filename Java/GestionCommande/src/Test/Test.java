/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Service.CommandeCRUD;
import Service.Lignecommeande;
import Service.Product;
import entities.Commande;
import entities.Ligne_commande;
import entities.Produit;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author BAZINFO
 */
public class Test {
   
    public static void main(String[] args) throws SQLException  {
        // 'TODO code application logic here
       // Lignecommeande ps = new Lignecommeande();
//        Date d = new Date(14, 1, 2000);
//        Commande c = new Commande(d, 55);
//         Ligne_commande l = new Ligne_commande(8,2,3,55);
//         ps.ajouter(l);
       
        //ps.supprimer(p)
//        try{
//         System.out.println( ps.recuperer());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        Ligne_commande p = new Ligne_commande(12, 5, 2, 20, 6);
        Lignecommeande ps = new Lignecommeande();
        Product psv = new  Product();
         System.out.println( psv.recuperer());
       // ps.ajouter(p);
        // ps.modifier(p);
        //ps.supprimer(p);
        
        System.out.println(ps.sommeTotale());
       System.out.println( ps.recuperer());
        
    }
        
    } 
    

