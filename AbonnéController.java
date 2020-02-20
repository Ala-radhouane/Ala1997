/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.Club;
import edu.gestionClub.entities.User;
import edu.gestionClub.entities.UserClub;
import edu.gestionClub.entities.UserServices;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AbonnéController implements Initializable {

    @FXML
    private TableView<UserClub> table2;
    @FXML
    private TableColumn<UserClub, Integer> id;
    @FXML
    private TableColumn<UserClub, Integer> nom;
    @FXML
    private TableColumn<UserClub, Integer> adress;
    @FXML
    private Button bannir;
    @FXML
    private Button close;
    @FXML
    private TableColumn<UserClub, Integer> telephone;
    @FXML
    private Button retour;

    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserServices us = new UserServices();
        ArrayList<UserClub> pers = (ArrayList<UserClub>) us.displayUser();
        ObservableList<UserClub> obs = FXCollections.observableArrayList(pers);
        table2.setItems(obs);

        telephone.setCellValueFactory(new PropertyValueFactory<>("idUc"));
        adress.setCellValueFactory(new PropertyValueFactory<>("idClub"));

        nom.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        // TODO
    }

    @FXML
    private void bannir(ActionEvent event) {
        UserServices us = new UserServices();

        UserClub user = (UserClub) table2.getSelectionModel().getSelectedItem();

        if (user != null) {
            int x = user.getIdUc();
            us.supprimeruser(x);
            ArrayList<UserClub> nper = (ArrayList<UserClub>) us.displayUser();
            ObservableList<UserClub> nobs = FXCollections.observableArrayList(nper);
            table2.setItems(nobs);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CONFIRMATION dELETE");
            alert.setHeaderText("DELETE DONE");
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CONFIRMATION dELETE");
            alert.setHeaderText("Select User");
            Optional<ButtonType> result = alert.showAndWait();

        }
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
