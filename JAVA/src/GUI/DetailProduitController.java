/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import Entities.produit;
import Services.ProduitService;
import static GUI.OneProduitController.idProduit;
import Utils.MyDB;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DetailProduitController implements Initializable {
    
    public static int idUsr = 1;

    @FXML
    private ImageView imgpro;
    @FXML
    private Text desctxt;
    @FXML
    private Text couleurtxt;
    @FXML
    private Text prixtxt;
    @FXML
    private Text refertxt;
    @FXML
    private Text nomtxt;
    @FXML
    private Text poidstxt;
    @FXML
    private Button Retour;
    
    
    private boolean etat;
    private int nbrp;
    private int idev;

        Connection cnx = MyDB.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }    
    
     public void loadData() {
        produit e = new produit();
        ProduitService es = new ProduitService();
        
        e = es.ReadByNVid(idProduit);
        nomtxt.setText(e.getNom());
        refertxt.setText(e.getReference());
        prixtxt.setText(e.getPrix());
        couleurtxt.setText(e.getCouleur());
        poidstxt.setText(e.getPoids());
        desctxt.setText(e.getDescription().toString());
        
        
        String path = "file:///D:\\FINALESS\\INTEGRATION.VOL3.1\\INTEGRATION.VOL3\\LEGENDS-PIDEV\\src\\Images\\" + e.getImage();

        Image image = new Image(path);
        System.out.println("detail" + path);
        imgpro.setImage(image);


   }
     @FXML
    void handleEventHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListProduitUsers.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Retour.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    
}
