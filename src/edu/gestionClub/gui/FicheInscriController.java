/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.gui;

import edu.gestionClub.entities.Club;
import edu.gestionClub.entities.ClubCrud;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class FicheInscriController implements Initializable {
    ClubCrud cc = new ClubCrud();
    List<Club> c = new ArrayList<>();
    @FXML
    private VBox clubtopics;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         c = cc.displayClub();
         for(Club t :c){
             System.out.println(t);
            clubtopics.getChildren().add(ClubItem(t));
        }
    }    
    
     private VBox ClubItem(Club c){        
         Font prefFont = new Font("System", 12);
        VBox vbox = new VBox();
        vbox.setPrefSize(708, 169);
        vbox.setStyle("-fx-border-color: black;");
         AnchorPane anchor = new AnchorPane();
        anchor.setPrefSize(351, 100);
        
         Button btn = new Button("View Topic");
        btn.setLayoutX(319);
        btn.setLayoutY(136);
        Integer aaa = c.getId_club();
        String b = aaa.toString();
        btn.setId(b); 
        //btn.setOnMouseClicked(this::ViewTopic);
        
        Label l1 = new Label("Topic's Title :");
        l1.setFont(prefFont);
        l1.setUnderline(true);
        l1.setLayoutX(303);
        l1.setLayoutY(6);
        l1.setUnderline(true);
        l1.setTextFill(Color.RED);
        Label l2 = new Label("Date :");
        l2.setFont(prefFont);
        l2.setUnderline(true);
        l2.setLayoutX(303);
        l2.setLayoutY(68);
        l2.setUnderline(true);
        l2.setTextFill(Color.RED);  
        int k = 0 ;
        
         Label date = new Label(c.getNom_club());
        date.setFont(prefFont);
        date.setLayoutX(301);
        date.setLayoutY(101);
        Label t1 = new Label(c.getAdresse_club());
        t1.setLayoutX(301);
        t1.setLayoutY(36);
        
        vbox.getChildren().addAll(btn,date,l1,l2,t1);       
        return vbox;
    }
    
}
