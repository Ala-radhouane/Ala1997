/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.Club;
import edu.gestionClub.entities.ClubCrud;
import edu.gestionClub.entities.User;
import edu.gestionClub.entities.UserServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InscriptionController implements Initializable {

    @FXML
    private TableView<Club> tablei;
    @FXML
    private TableColumn<Club, String> nom_club;
    @FXML
    private TableColumn<Club, String> adresse;
    @FXML
    private TableColumn<Club, Integer> placeDesponible_club;
    @FXML
    private Button Inscription;
    @FXML
    
    private Button Actualise;
    @FXML
   
    private Button aaaaaa;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Actualise.setOnAction((event) -> {

            ClubCrud cc = new ClubCrud();

            ArrayList<Club> nper = (ArrayList<Club>) cc.displayClub();
            ObservableList<Club> nobs = FXCollections.observableArrayList(nper);
            tablei.setItems(nobs);
            nom_club.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_club"));
            placeDesponible_club.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));

        });

        ClubCrud cc = new ClubCrud();

        ArrayList<Club> pers = (ArrayList<Club>) cc.displayClub();
        ObservableList<Club> obs = FXCollections.observableArrayList(pers);
        tablei.setItems(obs);

       nom_club.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_club"));
            placeDesponible_club.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));

    }

    // TODO
    @FXML
    private void Inscription(ActionEvent event) throws SQLException, IOException {
        UserServices us = new UserServices();
        User user = new User();
        Club c = (Club) tablei.getSelectionModel().getSelectedItem();
        if (c != null) {
            int idc = c.getId_club();

            int idu = user.getId_user();
            us.INSCRIT(idc, idu);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("WELCOME");
            alert.setHeaderText("INSCRIPTION DONE !");
            Optional<ButtonType> result1 = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Warnning");
            alert.setHeaderText("INSCRIPTION FAILED  SELECT YOUR CLUB!");
            Optional<ButtonType> result1 = alert.showAndWait();
        }
         String numero="26435806";
             String msg="vous etes inscrit dans le club ";
         //    sendsms(numero,msg);

    }

    @FXML
    private void click1(MouseEvent event) {
    }

  
    /////////////////////////////////////////////

 public void sendsms(String receiver, String msg) {
        try {
            //Construct data
                     

          

             String apiKey = "apikey=LSIEtEkANsY-UdnKiE4rc5vdzmBANmyszFaX4sP24V";
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


