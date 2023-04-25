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
public class CommentaireService {
    
    private Connection con = MyConnection.getInstance().getConnection();

    public void ajouter(Commentaire c) throws SQLException {
        String requete = "INSERT INTO commentaire (description, note, id_cours) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, c.getDescription());
        pst.setInt(2, c.getNote());
        pst.setInt(3, c.getId_cours());

        pst.executeUpdate();
        System.out.println("Commentaire ajouté avec succès !");
    }









    public void update(Commentaire c) throws SQLException {
        String requete = "UPDATE commentaire SET description=?, note=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, c.getDescription());
        pst.setInt(2, c.getNote());
        pst.setInt(3, c.getId());
        pst.executeUpdate();
        System.out.println("Commentaire modifié avec succès !");
    }

    public void delete(int id) throws SQLException {
        String requete = "DELETE FROM commentaire WHERE id=?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Commentaire supprimé avec succès !");
    }

    public ArrayList<Commentaire> afficherAll() throws SQLException {
        ArrayList<Commentaire> commentaires = new ArrayList<>();
        String requete = "SELECT commentaire.id, commentaire.description, commentaire.note, cours.nom AS Ncours "
                + "FROM commentaire "
                + "INNER JOIN cours ON commentaire.id_cours = cours.id";
        PreparedStatement pst = con.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Commentaire c = new Commentaire();
            Cours m = new Cours();
            c.setId(rs.getInt("id"));
            c.setDescription(rs.getString("description"));
            c.setNote(rs.getInt("note"));
            m.setNom(rs.getString("Ncours"));
            c.setCours(m);
            commentaires.add(c);
        }
        return commentaires;
    }



    public Cours getCoursById(int id) throws SQLException {
        String query = "SELECT * FROM cours WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Cours cours = new Cours();
        while (resultSet.next()) {
            cours.setId(resultSet.getInt("id"));
            cours.setNom(resultSet.getString("nom"));
            cours.setDes(resultSet.getString("Des"));
            cours.setpdf(resultSet.getString("pdf"));
        }
        return cours;
    }






}

