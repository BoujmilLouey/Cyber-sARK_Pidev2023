/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import Entities.Upload;
import Entities.produit;
import Services.CategorieService;
import Services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterProduitController implements Initializable {
    private File file;
    String pic;

    @FXML
    private TextField champNom;
    @FXML
    private ComboBox<String> ChampCategorie;
    @FXML
    private TextField ChampRef;
    @FXML
    private TextField ChampPrix;
    @FXML
    private TextField ChampCouleur;
    @FXML
    private TextField ChampPoid;
    @FXML
    private TextField ChampsDesc;
    @FXML
    private TextField ChampImage;
    @FXML
    private Button ButtonImage;
    @FXML
    private Button BottomAjouter;
    private CategorieService CategorieService = new CategorieService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<String> nomCat = CategorieService.getNomsCat();
            ChampCategorie.getItems().addAll(nomCat);
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des noms des categories : " + ex.getMessage());
        }
    }    

    @FXML
    private void AddProduit(ActionEvent event) throws ParseException, SQLException {
        
         String filePath ="";
               try {
           //insert event info  and user name from  to qrcode 
            String qrCodeData = "Produit name :"+champNom.getText()+"\n"+"Prix :"+ChampCouleur.getText()+"\n"+" Description :"+ChampsDesc.getText();

                    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        // create a random number generator
            Random random = new Random();
        
        // generate a random name
        String name = "";
        for (int i = 0; i < 4; i++) {
            char c = alphabet[random.nextInt(alphabet.length)];
            name += c;
        }



             filePath = "D:\\FINALESS\\INTEGRATION.VOL3.1\\INTEGRATION.VOL3\\LEGENDS-PIDEV\\src\\Images\\QrCode\\"+name+".jpg";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String getNom = champNom.getText();
        String getReference = ChampRef.getText();
        float getPrix = Float.parseFloat(ChampPrix.getText());
        String getCouleur = ChampCouleur.getText();
        float getPoids = Float.parseFloat(ChampPoid.getText());
        String getDescription =ChampsDesc .getText();
        String getImage = ChampImage.getText();
        String nomCat = ChampCategorie.getValue();
        if (getNom.isEmpty()| getDescription.isEmpty() | getImage.isEmpty()| getCouleur.isEmpty()| getReference.isEmpty()  ){
            alert.setTitle("Produit");
            alert.setContentText("Voun ne pouvez pas envoyer un produit avec un champ vide!!");
            alert.show();
        }
         if (nomCat == null) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une categorie !");
        alert.showAndWait();
        return;}
         
         if (getDescription.length() < 10) {
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description doit contenir au moins 20 caractères !");
        alert.showAndWait();
        return;
    }
         
         // Vérifier que l'image est au format jpg ou png
    if (!getImage.toLowerCase().endsWith(".jpg") && !getImage.toLowerCase().endsWith(".png")) {
        Alert alert3 = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout");
        alert.setHeaderText(null);
        alert.setContentText("L'image doit être au format JPG ou PNG !");
        alert.showAndWait();
        return;
    }       
         
         
         
        else {
        
        // Récupération de l'ID de l'événement correspondant au nom sélectionné
        int idCat = CategorieService.getIdCByNom(nomCat);
        
        
            produit r = new produit(getNom,getReference, getPrix,getCouleur,getDescription,getPoids,getImage,idCat);
            ProduitService CR = new ProduitService();
            CR.addProduit(r);
            alert.setTitle("Produit");
            alert.setContentText("Produit ajoutée Avec succées");
            alert.show();
            champNom.clear();
            ChampRef.clear();
            ChampPrix.clear();
            ChampCouleur.clear();
            ChampPoid.clear();
            ChampsDesc.clear();
            ChampImage.clear();
            ChampCategorie.setValue(null); // Effacement de la sélection dans le ComboBox

        }
    }
    @FXML
     private void chooseImageButton(ActionEvent event) throws IOException {
         
      //    FileChooser fileChooser = new FileChooser();
       // fileChooser.setTitle("Choisir une image");
       // fileChooser.getExtensionFilters().addAll(
        //    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
        //  );
       // File selectedFile = fileChooser.showOpenDialog(null);
      //  if (selectedFile != null) {
      //  String imagePath = selectedFile.getAbsolutePath();
       FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //pic=(file.toURI().toString());
        pic = new Upload().upload(file, "\\Images");
        System.out.println(pic);  
      ChampImage.setText(pic);

}
    


    @FXML
    private void Listprodroute(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProduit.fxml"));
        try {
           Parent root = loader.load();
            ListProduitController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            ChampRef.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
}
