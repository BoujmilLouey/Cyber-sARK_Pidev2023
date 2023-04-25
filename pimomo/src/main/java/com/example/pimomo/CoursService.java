/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pimomo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author momo
 */
public class CoursService {
    
    private Connection con = MyConnection.getInstance().getConnection();

    public void ajouter(Cours E) throws SQLException {
        String requete = "INSERT INTO cours (nom, des, pdf) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, E.getNom());
        
        pst.setString(2, E.getDes());
        pst.setString(3, E.getpdf());
       
        
        pst.executeUpdate();
        System.out.println("Cours ajouté avec succès !");
    }

    public void update(Cours E) throws SQLException {
        String requete = "UPDATE cours SET nom=?, des=?, pdf=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, E.getNom());
        
        pst.setString(2, E.getDes());
        pst.setString(3, E.getpdf());
       
        pst.setInt(4, E.getId());
        pst.executeUpdate();
        System.out.println("Cours modifié avec succès !");
    }
    
    
  
    

    public void delete(int id) throws SQLException {
        String requete = "DELETE FROM cours WHERE id=?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Cours supprimé avec succès !");
    }

    public ArrayList<Cours> afficherAll() throws SQLException {
        ArrayList<Cours> CoursList = new ArrayList<>();
        String requete = "SELECT * FROM cours";
        PreparedStatement pst = con.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Cours cours = new Cours();
            cours.setId(rs.getInt("id"));
            cours.setNom(rs.getString("nom"));
            
            cours.setDes(rs.getString("Des"));
            
            cours.setpdf(rs.getString("pdf"));
            CoursList.add(cours);
        }
        return CoursList;
    }


    public void setCours(Cours cours) throws SQLException {
        String requete = "UPDATE cours SET nom=?, Des=?, pdf=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, cours.getNom());
        pst.setString(2, cours.getDes());
        pst.setString(3, cours.getpdf());
        pst.setInt(4, cours.getId());
        pst.executeUpdate();
    }

    public Cours getCoursByName(String nom) {
        Cours cours = null;
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM cours WHERE nom='" + nom + "'";
            System.out.println(query);
            ResultSet rs = ((Statement) st).executeQuery(query);
            while (rs.next()) {
                cours = new Cours(rs.getInt("id"), rs.getString("nom"), rs.getString("des"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cours;
    }

   /* public List<Cours> getAllCours() {
        List<Cours> coursList = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM cours";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cours cours = new Cours(rs.getInt("id"), rs.getString("nom"), rs.getString("des"), rs.getString("pdf"));
                coursList.add(cours);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return coursList;
    }

    */




}
