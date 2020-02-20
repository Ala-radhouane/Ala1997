/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.User;
import edu.gestionClub.entities.UserCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class USERController implements Initializable {

    @FXML
    private TableView<User> table;
    
    @FXML
    private TableColumn<User, String> address_user;
    @FXML
    private TableColumn<User, Integer> phone_user;
    @FXML
    private TextField newnom;
    @FXML
    private TextField newaddress;
    @FXML
    private TextField newphone_user;
    @FXML
    private Button ajouter;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button show;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<User, String> nom_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         UserCrud ps = new UserCrud();
        ArrayList<User> perss = (ArrayList<User>) ps.displayUser();
        ObservableList<User> ob = FXCollections.observableArrayList(perss);
        
          
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<User> per = (ArrayList<User>) ps.displayUser();
        ObservableList<User> datalist = FXCollections.observableArrayList(per);
        ps.displayUser();
        FilteredList<User> filterdData = new FilteredList<>(datalist, p -> true);
            filterdData.setPredicate((Liv) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCaseFilter = newValue.toLowerCase();

                if (Liv.getNom_user().toLowerCase().contains(LowerCaseFilter)) {
                    return true;

                }  
                    return false;
                

            });
            SortedList<User> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
           
        });
        table.setItems(ob);
        
        

        nom_user.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        address_user.setCellValueFactory(new PropertyValueFactory<>("address_user"));
        phone_user.setCellValueFactory(new PropertyValueFactory<>("phone_user"));

        ajouter.setOnAction(((ActionEvent event) -> {
            User a = new User(newnom.getText(), newaddress.getText(),
                     Integer.parseInt(newphone_user.getText()));
            try {
                ps.ajouterUser(a);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ArrayList<User> nper = (ArrayList<User>) ps.displayUser();
            ObservableList<User> nobs = FXCollections.observableArrayList(nper);
            table.setItems(nobs);

            nom_user.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
            address_user.setCellValueFactory(new PropertyValueFactory<>("adresse_user"));
            phone_user.setCellValueFactory(new PropertyValueFactory<>("phone_user"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("User " + a.getNom_user() + " ajouteé");
            alert.showAndWait();

        }));
        
        
        
          delete.setOnAction((ActionEvent e) -> {
            //  TableView<Livres> tbl_elev=new TableView<Livres>();
           User p = (User) table.getSelectionModel().getSelectedItem();
            if (p != null) {
                int a = p.getId_user();

                ps.suprrimerUser(a);
               ArrayList<User> nper = (ArrayList<User>) ps.displayUser();
            ObservableList<User> nobs = FXCollections.observableArrayList(nper);
            table.setItems(nobs);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("supprission terminé avec succé!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("il faut selectinner un club!");
                alert.showAndWait();
            }

        });
         
          show.setOnAction(((event) -> {
            User p = (User) table.getSelectionModel().getSelectedItem();
            if (p != null) {
                newnom.setText(String.valueOf(p.getNom_user()));
                newaddress.setText(String.valueOf(p.getAddress_user()));
                newphone_user.setText(String.valueOf(p.getPhone_user()));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("il faut selectinner un user!");
                alert.showAndWait();
            }

        }));
            update.setOnAction((ActionEvent e) -> {
                            User a = (User) table.getSelectionModel().getSelectedItem();

            User p = new User(newnom.getText(), newaddress.getText(),
                    Integer.parseInt(newphone_user.getText()));
            int b = a.getId_user();
                ps.modifierUser(p, b);
                 ArrayList<User> nper = (ArrayList<User>) ps.displayUser();
            ObservableList<User> nobs = FXCollections.observableArrayList(nper);
            table.setItems(nobs);

            nom_user.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
            address_user.setCellValueFactory(new PropertyValueFactory<>("address_user"));
            phone_user.setCellValueFactory(new PropertyValueFactory<>("phone_user"));    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("user modifier!");
                alert.showAndWait();

            
       });

    
        // TODO
    }    
    
}
