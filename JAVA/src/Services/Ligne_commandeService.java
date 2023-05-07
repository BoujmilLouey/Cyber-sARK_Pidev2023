package Services;

import Entities.Ligne_commande;
import Utils.MyDB;
import Utils.RelationObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ligne_commandeService {

    private static Ligne_commandeService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public Ligne_commandeService() {
        connection = MyDB.getInstance().getCnx();
    }

    public static Ligne_commandeService getInstance() {
        if (instance == null) {
            instance = new Ligne_commandeService();
        }
        return instance;
    }

    public List<Ligne_commande> getAll() {
        List<Ligne_commande> listLigne_commande = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `ligne_commande`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listLigne_commande.add(new Ligne_commande(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("commande_id"), String.valueOf(resultSet.getInt("commande_id"))),
                        resultSet.getInt("quantite"),
                        new RelationObject(resultSet.getInt("produit_id"), resultSet.getString("produit_id")),
                        resultSet.getFloat("price")
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) ligne_commande : " + exception.getMessage());
        }
        return listLigne_commande;
    }

    public List<Ligne_commande> getByUser(int userId) {
        List<Ligne_commande> listLigne_commande = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `ligne_commande` AS l RIGHT JOIN `products` AS p ON l.produit_id = p.id RIGHT JOIN `commande` AS c ON l.commande_id = c.id WHERE l.produit_id = p.id AND c.user_id = " + userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listLigne_commande.add(new Ligne_commande(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("commande_id"), String.valueOf(resultSet.getInt("c.id"))),
                        resultSet.getInt("quantite"),
                        new RelationObject(resultSet.getInt("produit_id"), resultSet.getString("p.title")),
                        resultSet.getFloat("price")
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getMy) ligne_commande : " + exception.getMessage());
        }
        return listLigne_commande;
    }

    public List<RelationObject> getAllProductss() {
        List<RelationObject> listProductss = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `products`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listProductss.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("title")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) productss : " + exception.getMessage());
        }
        return listProductss;
    }

    public List<RelationObject> getAllCommandes() {
        List<RelationObject> listProductss = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `commande`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listProductss.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("id")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) commande : " + exception.getMessage());
        }
        return listProductss;
    }

    public boolean checkExist(Ligne_commande ligne_commande) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `ligne_commande` WHERE `commande_id` = ? AND `quantite` = ? AND `produit_id` = ? AND `price` = ?");

            preparedStatement.setInt(1, ligne_commande.getCommandeId().getId());
            preparedStatement.setInt(2, ligne_commande.getQuantite());
            preparedStatement.setInt(3, ligne_commande.getProduitId().getId());
            preparedStatement.setFloat(4, ligne_commande.getPrice());

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error (getAll) sdp : " + exception.getMessage());
        }
        return false;
    }

    public boolean add(Ligne_commande ligne_commande) {

        if (checkExist(ligne_commande)) {
            return false;
        }

        String request = "INSERT INTO `ligne_commande`(`commande_id`, `quantite`, `produit_id`, `price`) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, ligne_commande.getCommandeId().getId());
            preparedStatement.setInt(2, ligne_commande.getQuantite());
            preparedStatement.setInt(3, ligne_commande.getProduitId().getId());
            preparedStatement.setFloat(4, ligne_commande.getPrice());

            preparedStatement.executeUpdate();
            System.out.println("Ligne_commande added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) ligne_commande : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Ligne_commande ligne_commande) {

        if (checkExist(ligne_commande)) {
            return false;
        }

        String request = "UPDATE `ligne_commande` SET `commande_id` = ?, `quantite` = ?, `produit_id` = ?, `price` = ? WHERE `id`=" + ligne_commande.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, ligne_commande.getCommandeId().getId());
            preparedStatement.setInt(2, ligne_commande.getQuantite());
            preparedStatement.setInt(3, ligne_commande.getProduitId().getId());
            preparedStatement.setFloat(4, ligne_commande.getPrice());

            preparedStatement.executeUpdate();
            System.out.println("Ligne_commande edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) ligne_commande : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `ligne_commande` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Ligne_commande deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) ligne_commande : " + exception.getMessage());
        }
        return false;
    }
    public int getCommandIdByUserId(int userId) {
    int commandId = -1; // Set a default value to indicate the command ID wasn't found
    try {
        preparedStatement = connection.prepareStatement("SELECT id FROM `commande` WHERE user_id = ?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            commandId = resultSet.getInt("id");
        }
    } catch (SQLException exception) {
        System.out.println("Error (getCommandIdByUserId): " + exception.getMessage());
    }
    return commandId;
    }
}
