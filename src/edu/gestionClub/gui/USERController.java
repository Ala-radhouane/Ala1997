/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.User;
import edu.gestionClub.entities.UserCrud;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import javax.swing.JOptionPane;

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
    @FXML
    private TableColumn<?, ?> attente;
    @FXML
    private Button Confirmer;

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
        attente.setCellValueFactory(new PropertyValueFactory<>("attente"));

        ajouter.setOnAction(((ActionEvent event) -> {
            User a = new User(newnom.getText(), newaddress.getText(),
                     Integer.parseInt(newphone_user.getText()),1);
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
            phone_user.setCellValueFactory(new PropertyValueFactory<>("attente"));
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
                    Integer.parseInt(newphone_user.getText()),2);
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
             Confirmer.setOnAction((ActionEvent e) -> {
                            User a = (User) table.getSelectionModel().getSelectedItem();

     
            int b = a.getId_user();
                ps.ConfirmerUser(b);
                 ArrayList<User> nper = (ArrayList<User>) ps.displayUser();
            ObservableList<User> nobs = FXCollections.observableArrayList(nper);
            table.setItems(nobs);

            nom_user.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
            address_user.setCellValueFactory(new PropertyValueFactory<>("address_user"));
            phone_user.setCellValueFactory(new PropertyValueFactory<>("phone_user"));    
            attente.setCellValueFactory(new PropertyValueFactory<>("attente"));  
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("user confirmé!");
                alert.showAndWait();
                String numero="26435806";
             String msg="vous etes inscrit dans le club ";
            sendsms(numero,msg);

            
       });
    }
    
        // TODO
        public void sendsms(String receiver, String msg) {
        try {
            //Construct data
                     

          

             String apiKey = "apikey=raxWOTZAwYA-OpHMkp0lFT1zecbOCrnkHaUXfE2ukt";
            String message = "&message=" + msg;
            String sender = "&sender= ClubXhunter";
            String numbers = "&numbers=+216" + receiver;
            
         

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                //stringBuffer.append(line);
                JOptionPane.showMessageDialog(null, "message" + line);
                
            }
            rd.close();

            //return stringBuffer.toString();
        } catch (Exception e) {
            //System.out.println("Error SMS "+e)
            JOptionPane.showMessageDialog(null, e);
        
    } 
}
    
}
