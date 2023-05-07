/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Entities.produit;
/**
 * FXML Controller class
 *
 * @author PC
 */
public class OneProduitController implements Initializable {

    @FXML
    private AnchorPane AnchProduit;
    @FXML
    private ImageView imgProduit;
    @FXML
    private Text nomProduit;
    @FXML
    private Text prixProduit;

    public static int idProduit;
    
    private  produit produit;
    
    
    private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
      public void setData(produit produit,  MyListener myListener) throws MalformedURLException{
        this.myListener = myListener;
        this.produit=produit;

        nomProduit.setText(produit.getNom());
        prixProduit.setText(Float.toString(produit.getPrix()));
                
//get image from root directory and databse
        
        String path = "file:///D:\\FINALESS\\INTEGRATION.VOL3.1\\INTEGRATION.VOL3\\LEGENDS-PIDEV\\src\\Images\\"+produit.getImage();
        Image image = new Image(path);
        System.out.println(path);
                System.out.println(image);

        imgProduit.setImage(image);
        
}


    
     @FXML
    private void EventDetail(MouseEvent event) throws IOException {
        
         idProduit=produit.getId();

       
        Parent root = FXMLLoader.load(getClass().getResource("detailProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    }
    
            public interface MyListener {
    public void onClickListener(produit produit);
}
    
}
