/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Game_category;
import Services.categoryService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListCatController implements Initializable {

    @FXML
    private TableView<Game_category> listCat;
    @FXML
    private TableColumn<Game_category, String> NomColumn;
  
    @FXML
    private Button btnModifier;
    
        @FXML
    private Button Home;
    
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnEnregistrer;
    @FXML
    private TextField textNom;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            categoryService crud = new categoryService();
            ObservableList<Game_category> data = FXCollections.observableArrayList(crud.showCategory());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
           
            listCat.setItems(data);
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println("here");
            Logger.getLogger(ListCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimer(ActionEvent event) throws IOException {
       
        int selectedIndex = listCat.getSelectionModel().getSelectedIndex();

       if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune categorie selectionnée!");
           alert.setContentText("Veuiller selectionner une categorie à supprimer");
           Optional<ButtonType> result = alert.showAndWait();
       } else{
        try {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                categoryService ser = new categoryService();
                ser.deleteCategory(listCat.getSelectionModel().getSelectedItem().getId());
                categoryService crud = new categoryService();
                ObservableList<Game_category> data = FXCollections.observableArrayList(crud.showCategory());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            listCat.setItems(data);
            alert.setTitle("Jeux");
            alert.setContentText("La categorie est supprimée Avec succées");
            alert.show();
            } else {

            }
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }
    public void initierReclamation(ActionEvent event) throws ParseException {
        int selectedIndex = listCat.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune jeu selectionnée!");
           alert.setContentText("Veuiller selectionner une jeu à modifier");
           Optional<ButtonType> result = alert.showAndWait();
       }
        else{
        int idRec=listCat.getSelectionModel().getSelectedItem().getId();
       
        textNom.setText(listCat.getSelectionModel().getSelectedItem().getName());
        
        }
        
    }
    public void modifier(ActionEvent event) throws ParseException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (textNom.getText().isEmpty()){
            alert.setTitle("Category");
            alert.setContentText("Voun ne pouvez pas modifier une reclamation avec un champ vide!!");
            alert.show();
        }
        else{
        int idRec=listCat.getSelectionModel().getSelectedItem().getId();
        Game_category r = new Game_category(idRec,textNom.getText());
        categoryService crud = new categoryService();
        crud.updateCategory(r);
        textNom.clear();
        
            ObservableList<Game_category> data = FXCollections.observableArrayList(crud.showCategory());
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
            listCat.setItems(data);
            alert.setTitle("categorie");
            alert.setContentText("Categorie Modifiée Avec succées");
            alert.show();
    }
    }
    
    
    
    @FXML
    void onhome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FrontAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) (Home.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    

    
    
    }    
    

