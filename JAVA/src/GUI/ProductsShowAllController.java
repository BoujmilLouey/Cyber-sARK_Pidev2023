package GUI;

import Entities.Products;
import GUI.BackMainWindowController;
import Services.ProductsService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ProductsShowAllController implements Initializable {

    public static String compareVar = "";
    public static Products currentProducts;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;
    @FXML
    public ComboBox<String> sortCB;

    List<Products> listProducts;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProducts = ProductsService.getInstance().getAll();
        sortCB.getItems().addAll("Title", "Price");
        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listProducts);

        if (!listProducts.isEmpty()) {
            for (Products products : listProducts) {

                mainVBox.getChildren().add(makeProductsModel(products));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeProductsModel(
            Products products
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_PRODUCTS)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#titleText")).setText("Title : " + products.getTitle());

            ((Text) innerContainer.lookup("#priceText")).setText("Price : " + products.getPrice());
            

            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierProducts(products));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerProducts(products));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterProducts(ActionEvent event) {
        currentProducts = null;
        BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_PRODUCTS);
    }

    private void modifierProducts(Products products) {
        currentProducts = products;
        BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_PRODUCTS);
    }

    private void supprimerProducts(Products products) {
        currentProducts = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("sûr de  supprimer produits ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (ProductsService.getInstance().delete(products.getId())) {
                BackMainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_PRODUCTS);
            } else {
                AlertUtils.makeError("tu peuc pas effacer le produit");
            }
        }
    }


    @FXML
    public void sort(ActionEvent actionEvent) {
        Constants.compareVar = sortCB.getValue();
        Collections.sort(listProducts);
        displayData();
    }
    
}
