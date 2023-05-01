/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Cours;

import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author momo
 */
public class CommentaireService {
    
    private Connection con = MyDB.getInstance().getCnx();

    public void ajouter(Commentaire c) throws SQLException {
        String requete = "INSERT INTO commentaire (contenu, note, id_cours) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, c.getContenu());
        pst.setInt(2, c.getNote());
        pst.setInt(3, c.getId_cours());

        pst.executeUpdate();
        System.out.println("Commentaire ajouté avec succès !");
    }









    public void update(Commentaire c) throws SQLException {
        String requete = "UPDATE commentaire SET contenu=?, note=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, c.getContenu());
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

    public ArrayList<Commentaire> afficherAllC() throws SQLException {
        ArrayList<Commentaire> commentaires = new ArrayList<>();
        String requete = "SELECT commentaire.id, commentaire.contenu, commentaire.note, cours.nom AS Ncours "
                + "FROM commentaire "
                + "INNER JOIN cours ON commentaire.id_cours = cours.id";
        PreparedStatement pst = con.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Commentaire c = new Commentaire();
            Cours m = new Cours();
            c.setId(rs.getInt("id"));
            c.setContenu(rs.getString("contenu"));
            c.setNote(rs.getInt("note"));
            m.setNom(rs.getString("Ncours"));
            c.setCours(m);
            commentaires.add(c);
        }
        return commentaires;
    }






    public ArrayList<Commentaire> rechercherCommentairesParCours(int idCours) throws SQLException {
        ArrayList<Commentaire> commentairesList = new ArrayList<>();

        String query = "SELECT * FROM commentaire WHERE id_cours = ?";

        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, idCours);
        ResultSet resultSet = pst.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String contenu = resultSet.getString("contenu");
            int note = resultSet.getInt("note");
            int id_cours = resultSet.getInt("id_cours");
            Commentaire commentaire = new Commentaire(id, contenu, note, idCours);
            commentairesList.add(commentaire);
        }

        return commentairesList;
    }







}

