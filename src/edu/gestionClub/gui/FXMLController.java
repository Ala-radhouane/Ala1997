/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.Club;
import edu.gestionClub.entities.ClubCrud;
import java.io.BufferedReader;
import java.io.IOException;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class FXMLController implements Initializable {

    @FXML
    private TableColumn<Club, String> nom;
    @FXML
    private TableColumn<Club, String> adresse;
    @FXML
    private TableColumn<Club, String> materiel;
    @FXML
    private TableColumn<Club, Integer> place_dispo;
    @FXML
    private TableView<Club> table;
    @FXML
    private TextField newnom;
    @FXML
    private TextField newadresse;
    @FXML
    private TextField newmatriel;
    @FXML
    private TextField newplace_dispo;
    @FXML
    private Button ajouter;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button show;
    public static int idclubisc;

    @FXML
    private TextField recherche;
    @FXML
    private Button consulter;
    @FXML
    private Button consultation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClubCrud ps = new ClubCrud();
        ArrayList<Club> perss = (ArrayList<Club>) ps.displayClub();
        ObservableList<Club> ob = FXCollections.observableArrayList(perss);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Club> per = (ArrayList<Club>) ps.displayClub();
            ObservableList<Club> datalist = FXCollections.observableArrayList(per);
            ps.displayClub();
            FilteredList<Club> filterdData = new FilteredList<>(datalist, p -> true);
            filterdData.setPredicate((Liv) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCaseFilter = newValue.toLowerCase();

                if (Liv.getNom_club().toLowerCase().contains(LowerCaseFilter)) {
                    return true;

                }
                return false;

            });
            SortedList<Club> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);

        });
        table.setItems(ob);

        nom.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_club"));
        materiel.setCellValueFactory(new PropertyValueFactory<>("materiel_club"));
        place_dispo.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));

        ajouter.setOnAction(((ActionEvent event) -> {
            Club a = new Club(newnom.getText(), newadresse.getText(),
                    newmatriel.getText(), Integer.parseInt(newplace_dispo.getText()));
            try {
                ps.ajouterClub(a);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ArrayList<Club> nper = (ArrayList<Club>) ps.displayClub();
            ObservableList<Club> nobs = FXCollections.observableArrayList(nper);
            table.setItems(nobs);

            nom.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_club"));
            materiel.setCellValueFactory(new PropertyValueFactory<>("materiel_club"));
            place_dispo.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("club " + a.getNom_club() + " ajouteé");
            alert.showAndWait();

        }));

        delete.setOnAction((ActionEvent e) -> {
            //  TableView<Livres> tbl_elev=new TableView<Livres>();
            Club p = (Club) table.getSelectionModel().getSelectedItem();
            if (p != null) {
                int a = p.getId_club();

                ps.suprrimerClub(a);
                ArrayList<Club> nper = (ArrayList<Club>) ps.displayClub();
                ObservableList<Club> nobs = FXCollections.observableArrayList(nper);
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
            Club p = (Club) table.getSelectionModel().getSelectedItem();
            if (p != null) {
                newnom.setText(String.valueOf(p.getNom_club()));
                newadresse.setText(String.valueOf(p.getAdresse_club()));
                newmatriel.setText(String.valueOf(p.getMateriel_club()));
                newplace_dispo.setText(String.valueOf(p.getPlace_dispo()));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("il faut selectinner un club!");
                alert.showAndWait();
            }

        }));
        update.setOnAction((ActionEvent e) -> {
            Club a = (Club) table.getSelectionModel().getSelectedItem();

            Club p = new Club(newnom.getText(), newadresse.getText(),
                    newmatriel.getText(), Integer.parseInt(newplace_dispo.getText()));
            int b = a.getId_club();
            ps.modifierClub(p, b);
            ArrayList<Club> nper = (ArrayList<Club>) ps.displayClub();
            ObservableList<Club> nobs = FXCollections.observableArrayList(nper);
            table.setItems(nobs);

            nom.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_club"));
            materiel.setCellValueFactory(new PropertyValueFactory<>("materiel_club"));
            place_dispo.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("club modifier!");
            alert.showAndWait();

        });

    }

   

    @FXML
    private void consulter(ActionEvent event) throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("abonné.fxml"));
            consulter.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/////////////////////////////////////////////
    public void sendsms(String receiver, String msg) {
        try {
            //Construct data

            String apiKey = "apikey=LSIEtEkANsY-UdnKiE4rc5vdzmBANmyszFaX4sP24V";
            String message = "&message=" + msg;
            String sender = "&sender= clubXhunter";
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

    @FXML
    private void consultation(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("USER.fxml"));
            consulter.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
