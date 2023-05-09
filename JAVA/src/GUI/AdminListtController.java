/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import Entities.User;
import Entities.coach;
import Services.ServiceUser;
import Utils.MyDB;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import ressources.Util;

/**
 * FXML Controller class
 *
 * @author donia
 */
public class AdminListtController implements Initializable {

    ObservableList<User> list = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> nomCol;
    @FXML
    private TableColumn<User, String> prenomCol;
    @FXML
    private TableColumn<User, String> cinCol;
    @FXML
    private TableColumn<User, String> mailCol;
    @FXML
    private TableColumn<User, String> mdpCol;
    @FXML
    private TableColumn<User, String> adresseCol;
    @FXML
    private TableColumn<User, String> phoneCol;
    private Connection cnx = MyDB.getInstance().getCnx();
    ServiceUser su = new ServiceUser();
    @FXML
    private TableColumn<User, String> roleCol;
    @FXML
    private TableColumn<User, String> ImageCol;
    
    @FXML
    private JFXTextField Search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mdpCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("Image"));
        

    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

   
    
    private void loadData() {
        list.clear();
        String req = "SELECT * FROM `user`  WHERE `archive`='" + 0 + "' ";

        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                list.add(new User(result.getInt("id"), result.getString("fullname"), result.getString("username"), result.getInt("cin"), result.getString("role"), result.getString("adresse"),  result.getString("email"), result.getString("password"),result.getInt("telephone"), result.getString("Image"), result.getString("Github_UserName")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableView.setItems(list);
             // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		Search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Place -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Place.getFullname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Place.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (Place.getGUserName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (Place.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} 
				else if (String.valueOf(Place.getCin()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
    }

    @FXML
    private void handlePlaceDelete(ActionEvent event) {
        //Fetch the selected row
        User selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            JOptionPane.showMessageDialog(null, "No Place selected ,Please select a Place for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting USER");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getFullname() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            su.supprimer(selectedForDeletion);

            list.remove(selectedForDeletion);

        }
    }
    @FXML
private void handlePlaceBan(ActionEvent event) {
    // Fetch the selected row
    User selectedForBan = tableView.getSelectionModel().getSelectedItem();
    if (selectedForBan == null) {
        JOptionPane.showMessageDialog(null, "No user selected, please select a user to ban.");
        return;
    }

    // Check if the user is already banned
    if (selectedForBan.getIs_banned()== 1) {
        JOptionPane.showMessageDialog(null, "This user is already banned.");
        return;
    }

    // Ask for confirmation before banning the user
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Banning User");
    alert.setContentText("Are you sure you want to ban " + selectedForBan.getFullname() + "?");
    Optional<ButtonType> answer = alert.showAndWait();
    if (answer.get() == ButtonType.OK) {
        // Ban the user
        su.banUser(selectedForBan.getId());

        // Update the list and table view
        selectedForBan.setIs_banned(1);
        tableView.refresh();
    }
}
@FXML
private void handlePlaceUnban(ActionEvent event) {
    // Fetch the selected row
    User selectedForUnban = tableView.getSelectionModel().getSelectedItem();
    if (selectedForUnban == null) {
        JOptionPane.showMessageDialog(null, "No user selected. Please select a user to unban.");
        return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Unbanning User");
    alert.setContentText("Are you sure you want to unban " + selectedForUnban.getFullname() + "?");
    Optional<ButtonType> answer = alert.showAndWait();
    if (answer.get() == ButtonType.OK) {
        // Unban the user
        su.unbanUser(selectedForUnban.getId());

        // Update the user's status in the table
        selectedForUnban.setIs_banned(0);
        tableView.refresh();
    }
}

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }
 
   
    @FXML
    private void handlePlaceEdit(ActionEvent event) {
        //Fetch the selected row
        User selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JOptionPane.showMessageDialog(null, "No Admin selected, Please select a Admin for edit.");
            return;
        } 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAdmin.fxml"));
            Parent parent = loader.load();

            AddAdminController controller = (AddAdminController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(parent));
            stage.show();

            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML

    private void closeStage(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void exportAsPDF(ActionEvent event) {
                        List<List> printData = new ArrayList<>();
        String[] headers = {"   Fullname   ", "  username ", "  CIN  ", "  Email ", "  Mot De Passe  ","   Adresse   ", "  role ","   Telephone   ","   Github Username   " };
        printData.add(Arrays.asList(headers));
        for (User place : list) {
            List<String> row = new ArrayList<>();
            row.add(place.getFullname());
            row.add(place.getUsername());
            row.add(String.valueOf(place.getCin()));
            row.add(place.getEmail());
            row.add(place.getPassword());
             row.add(place.getAdresse());
             row.add(place.getRole());
            row.add(String.valueOf(place.getTelephone()));
             row.add(place.getGUserName());
            printData.add(row);
        }
        Util.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }

    @FXML
    private void retern(ActionEvent event) {
          
         try {
                  final Node source = (Node) event.getSource();

          
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root)); 
            stage.show();
            closeStage(event);
            
    } catch(Exception e) {
        e.printStackTrace();
    } 
                
    }}

