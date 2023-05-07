package Services;

import Entities.Products;
import Utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsService {

    private static ProductsService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ProductsService() {
        connection = MyDB.getInstance().getCnx();
    }

    public static ProductsService getInstance() {
        if (instance == null) {
            instance = new ProductsService();
        }
        return instance;
    }

    public List<Products> getAll() {
        List<Products> listProducts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `products`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listProducts.add(new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("image"),
                        resultSet.getFloat("price")

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) products : " + exception.getMessage());
        }
        return listProducts;
    }

    public boolean add(Products products) {

        String request = "INSERT INTO `products`(`title`, `image`, `price`) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, products.getTitle());
            preparedStatement.setString(2, products.getImage());
            preparedStatement.setFloat(3, products.getPrice());

            preparedStatement.executeUpdate();
            System.out.println("Products added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) products : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Products products) {

        String request = "UPDATE `products` SET `title` = ?, `image` = ?, `price` = ? WHERE `id`=" + products.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, products.getTitle());
            preparedStatement.setString(2, products.getImage());
            preparedStatement.setFloat(3, products.getPrice());

            preparedStatement.executeUpdate();
            System.out.println("Products edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) products : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `products` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Products deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) products : " + exception.getMessage());
        }
        return false;
    }
}
