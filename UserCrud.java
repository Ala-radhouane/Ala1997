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
public class UserCrud {
       MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();
     public void ajouterUser(User c) throws SQLException {

        String requete = "INSERT INTO user(nom_user ,address_user, phone_user)"
                + "VALUES ('" + c.getNom_user() + "','" + c.getAddress_user() + "','" + c.getPhone_user() + "' )";

        try {
            Statement st = cnx.createStatement();

            st.executeUpdate(requete);
            System.out.println("user ajouter");

        } catch (SQLException ex) {
            System.out.println("erreur d'insertion");
        }

    }

     public void modifierUser(User l, int id_user) {
        String requete = "UPDATE user SET nom_user=? ,address_user=?, phone_user=? WHERE id_user=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, id_user);
            pst.setString(1, l.getNom_user());
            pst.setString(2, l.getAddress_user());
            pst.setInt(3, l.getPhone_user());

            

            pst.executeUpdate();
            System.out.println("user modifier");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }

    }
     public boolean suprrimerUser(int id_user) {
        boolean b = false;

        try {
            String requete = "delete from user where id_user=?";
            PreparedStatement std = cnx.prepareStatement(requete);
            std.setInt(1, id_user);
            std.executeUpdate();

            System.out.println("user supprimer ...");
            b = true;

        } catch (Exception e) {
            System.out.println("probleme  ...");
        }
        return b;
    }
      public void affUser() {
        try {
            String sql = "SELECT * FROM user ";

            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                int i = result.getInt(1);
                String nom_user = result.getString(2);
                String address_user = result.getString(3);
                int phone_user = result.getInt(4);

                String output = "user : %d - %s - %s -%d ";
                System.out.println(String.format(output, i, nom_user, address_user, phone_user));
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }

    }
        public List<User> displayUser() {
        List<User> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User p = new User();
                p.setId_user(rs.getInt("id_user"));

                p.setNom_user(rs.getString("nom_user"));
                p.setAddress_user(rs.getString("address_user"));
                p.setPhone_user(rs.getInt("phone_user"));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur d'affichage");
        }
        return myList;
    }
    
}
