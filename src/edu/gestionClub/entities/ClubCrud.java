/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21626
 */
public class ClubCrud {

    MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();

    public void ajouterClub(Club c) throws SQLException {

        String requete = "INSERT INTO club(nom_club ,adresse_club, materiel_club, place_dispo)"
                + "VALUES ('" + c.getNom_club() + "','" + c.getAdresse_club() + "','" + c.getMateriel_club() + "','" + c.getPlace_dispo() + "' )";

        try {
            Statement st = cnx.createStatement();

            st.executeUpdate(requete);
            System.out.println("club ajouter");

        } catch (SQLException ex) {
            System.out.println("erreur d'insertion");
        }

    }

    public void modifierClub(Club l, int id_club) {
        String requete = "UPDATE club SET nom_club=? ,adresse_club=?, materiel_club=?, place_dispo=? WHERE id_club=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, l.getNom_club());
            pst.setString(2, l.getAdresse_club());
            pst.setString(3, l.getMateriel_club());
            pst.setInt(4, l.getPlace_dispo());

            pst.setInt(5, id_club);

            pst.executeUpdate();
            System.out.println("club modifier");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }

    }

    public boolean suprrimerClub(int id_club) {
        boolean b = false;

        try {
            String requete = "delete from club where id_club=?";
            PreparedStatement std = cnx.prepareStatement(requete);
            std.setInt(1, id_club);
            std.executeUpdate();

            System.out.println("club supprimer ...");
            b = true;

        } catch (Exception e) {
            System.out.println("probleme  ...");
        }
        return b;
    }

    public void affClub() {
        try {
            String sql = "SELECT * FROM club ";

            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                int i = result.getInt(1);
                String nom_club = result.getString(2);
                String adresse_club = result.getString(3);
                String materiel_club = result.getString(4);
                int place_dispo = result.getInt(5);

                String output = "club : %d - %s - %s - %s -  %d ";
                System.out.println(String.format(output, i, nom_club, adresse_club, materiel_club, place_dispo));
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }

    }

    public void searchclub(int id_club) {

        try {
            PreparedStatement pt = cnx.prepareStatement("select * from club where id_club=?");
            pt.setInt(1, id_club);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Clubs [id : " + rs.getInt(1) + ",nom_club:  " + rs.getString(2) + ",adresse_club:  " + rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Club> displayClub() {
        List<Club> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM club";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Club p = new Club();
                p.setId_club(rs.getInt("id_club"));

                p.setNom_club(rs.getString("nom_club"));
                p.setAdresse_club(rs.getString("adresse_club"));
                p.setMateriel_club(rs.getString("materiel_club"));
                p.setPlace_dispo(rs.getInt("place_dispo"));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur d'affichage");
        }
        return myList;
    }
}
