/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import Entities.produit;
import Utils.MyDB;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class ListProduitUsersController implements Initializable {

    @FXML
    private ScrollPane ScrollProduit;
    @FXML
    private GridPane grid;
    @FXML
    private Button Retour;
    private List<produit> Eve = new ArrayList<>();

    private Connection cnx = MyDB.getInstance().getCnx();
    private OneProduitController.MyListener myListener;
    
    
    
       private List<produit> getData() {
        List<produit> produit = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM `produit`";

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                produit a = new produit();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrix(rs.getString(4));
                a.setImage(rs.getString(8));

                produit.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return produit;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        System.out.println(this.getData());

       Eve.addAll(getData());
        int column = 0;
        int row = 1;

         try {
            for (int i = 0; i < Eve.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneProduit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OneProduitController itemController = fxmlLoader.getController();
                    itemController.setData(Eve.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); 
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE); 
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
    }

        // TODO
    } 
    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontClient.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Retour.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
}
