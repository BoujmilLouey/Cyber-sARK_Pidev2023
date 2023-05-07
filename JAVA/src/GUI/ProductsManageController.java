package GUI;


import Entities.Products;
import GUI.BackMainWindowController;
import Services.ProductsService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class ProductsManageController implements Initializable {

    @FXML
    public TextField titleTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public TextField priceTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Products currentProducts;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentProducts = ProductsShowAllController.currentProducts;

        if (currentProducts != null) {
            topText.setText("Modifier products");
            btnAjout.setText("Modifier");

            try {
                titleTF.setText(currentProducts.getTitle());
                selectedImagePath = FileSystems.getDefault().getPath(currentProducts.getImage());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                priceTF.setText(String.valueOf(currentProducts.getPrice()));

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter products");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            

            Products products = new Products(
                    titleTF.getText(),
                    
                    Float.parseFloat(priceTF.getText())
            );

            if (currentProducts == null) {
                if (ProductsService.getInstance().add(products)) {
                    AlertUtils.makeSuccessNotification("Products ajouté avec succés");
                    BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_PRODUCTS);
                } else {
                    AlertUtils.makeError("products existe deja");
                }
            } else {
                products.setId(currentProducts.getId());
                if (ProductsService.getInstance().edit(products)) {
                    AlertUtils.makeSuccessNotification("Products modifié avec succés");
                    ProductsShowAllController.currentProducts = null;
                    BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_PRODUCTS);
                } else {
                    AlertUtils.makeError("products existe deja");
                }
            }

            if (selectedImagePath != null) {
                createImageFile();
            }
        }
    }

    

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("Images/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {


        if (titleTF.getText().isEmpty()) {
            AlertUtils.makeInformation("title ne doit pas etre vide");
            return false;
        }


        


        if (priceTF.getText().isEmpty()) {
            AlertUtils.makeInformation("price ne doit pas etre vide");
            return false;
        }


        try {
            Float.parseFloat(priceTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("price doit etre un réel");
            return false;
        }
        return true;
    }
}