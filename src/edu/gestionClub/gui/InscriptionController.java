/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.Club;
import edu.gestionClub.entities.ClubCrud;
import edu.gestionClub.entities.User;
import edu.gestionClub.entities.UserCrud;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
    UserCrud ucc = new UserCrud();
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
            us.INSCRITD(idc, idu);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("WELCOME");
            alert.setHeaderText("donner vos information ");
            Optional<ButtonType> result1 = alert.showAndWait();
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Ajout user");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            dialog.getDialogPane().setBackground(new Background(new BackgroundFill
        (Color.GREY, new CornerRadii(2), new Insets(2))));
            dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            GridPane grid = new GridPane();
            grid.setHgap(30);
            grid.setVgap(10);   
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField nom = new TextField("");
            nom.setPromptText("nom");
            nom.setPrefSize(100, 50);
            TextField place = new TextField("");
            place.setPromptText("place");
            place.setPrefSize(100, 50);
             TextField dd = new TextField();
                     dd.setPromptText("phone");
            dd.setPrefSize(100, 50);
            grid.add(new Label("Nom User "), 0, 0);
        grid.add(nom, 1, 0);
        grid.add(new Label("phone: "), 0, 1);
        grid.add(dd, 1, 1);
        grid.add(new Label("adresse: "), 0, 2);
        grid.add(place, 1, 2);
        dialog.getDialogPane().setContent(grid);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            user.setNom_user(nom.getText());
            user.setAddress_user(place.getText());
            user.setPhone_user(Integer.parseInt(dd.getText()));
         us.INSCRIT(idc, idu);
         ucc.ajouterUser(user);
        }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Warnning");
            alert.setHeaderText("INSCRIPTION FAILED  SELECT YOUR CLUB!");
            Optional<ButtonType> result1 = alert.showAndWait();
        }
         

    }

    @FXML
    private void click1(MouseEvent event) {
    }

  
    /////////////////////////////////////////////


    


 
    }


