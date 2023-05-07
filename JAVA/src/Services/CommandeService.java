package Services;

import Entities.Commande;
import Utils.MyDB;
import Utils.RelationObject;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandeService {

    private static CommandeService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public CommandeService() {
        connection = MyDB.getInstance().getCnx();
    }

    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
    }
    
    public List<Commande> getAll() {
        List<Commande> listCommande = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `commande` AS x RIGHT JOIN `user` AS y ON x.user_id = y.id WHERE x.user_id = y.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCommande.add(new Commande(
                        resultSet.getInt("id"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date_commande"))),
                        resultSet.getFloat("montant_commande"),
                        new RelationObject(resultSet.getInt("user_id"), resultSet.getString("y.nom"))
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) commande : " + exception.getMessage());
        }
        return listCommande;
    }
  

    public List<Commande> getByUserId(int userId) {
        List<Commande> listCommande = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `commande` AS x RIGHT JOIN `user` AS y ON x.user_id = y.id WHERE x.user_id = y.id AND user_id = " + userId );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCommande.add(new Commande(
                        resultSet.getInt("id"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date_commande"))),
                        resultSet.getFloat("montant_commande"),
                        new RelationObject(resultSet.getInt("user_id"), resultSet.getString("y.email"))

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) commande : " + exception.getMessage());
        }
        return listCommande;
    }
    
    public List<RelationObject> getAllUsers() {
        List<RelationObject> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsers.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("nom")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) users : " + exception.getMessage());
        }
        return listUsers;
    }
    
      public RelationObject getUserById(int userId) {
    RelationObject user = null;
    try {
        preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE id = ?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new RelationObject(resultSet.getInt("id"), resultSet.getString("nom"));
        }
    } catch (SQLException exception) {
        System.out.println("Error (getUserById) user : " + exception.getMessage());
    }
    return user;
}
    
    
    public boolean checkExist(Commande commande) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `commande` WHERE `date_commande` = ? AND `montant_commande` = ? AND `user_id` = ?");

            preparedStatement.setDate(1, Date.valueOf(commande.getDateCommande()));
            preparedStatement.setFloat(2, commande.getMontantCommande());
            preparedStatement.setInt(3, commande.getUserId().getId());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error (getAll) sdp : " + exception.getMessage());
        }
        return false;
    }
    
    public boolean add(Commande commande) {
        
        if (checkExist(commande)) {
            return false;
        }
    
        String request = "INSERT INTO `commande`(`date_commande`, `montant_commande`, `user_id`) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
            preparedStatement.setDate(1, Date.valueOf(commande.getDateCommande()));
            preparedStatement.setFloat(2, commande.getMontantCommande());
            preparedStatement.setInt(3, commande.getUserId().getId());
            
            preparedStatement.executeUpdate();
            System.out.println("Commande added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) commande : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Commande commande) {
        
        if (checkExist(commande)) {
            return false;
        }
        
        String request = "UPDATE `commande` SET `date_commande` = ?, `montant_commande` = ?, `user_id` = ? WHERE `id`=" + commande.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setDate(1, Date.valueOf(commande.getDateCommande()));
            preparedStatement.setFloat(2, commande.getMontantCommande());
            preparedStatement.setInt(3, commande.getUserId().getId());
            
            preparedStatement.executeUpdate();
            System.out.println("Commande edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) commande : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `commande` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Commande deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) commande : " + exception.getMessage());
        }
        return false;
    }
}
