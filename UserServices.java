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

/**
 *
 * @author Asus
 */
public class UserServices {

    User u = new User();
    UserClub uc = new UserClub();
    Club c = new Club();

    MyConnection myc = MyConnection.getInstance();
    Connection cnx = myc.getConnection();

    public void INSCRIT(int idc, int idu) {
        String requete = "INSERT INTO inscriptionc(idClub,iduser) VALUES (?,?)";
        String r = "UPDATE club SET place_dispo=place_dispo -1 WHERE id_club=? AND place_dispo -1>0 ";

        {
            try {
                PreparedStatement pst = cnx.prepareStatement(requete);
                PreparedStatement pt = cnx.prepareStatement(r);

                pst.setInt(1, idc);
                pst.setInt(2, idu);
                pt.setInt(1, idc);
                pst.executeUpdate();
                pt.executeUpdate();

                System.out.println("Inscription validé");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
    }

    public List<UserClub> displayUser() {
        String requette = "SELECT * FROM  inscriptionc" ;

        List<UserClub> mylist = new ArrayList<>();
        try {

            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(requette);
             while (rs.next()) {
               UserClub p = new UserClub();
                p.setIdClub(rs.getInt("idClub"));
                p.setIdUser(rs.getInt("idUser"));
                p.setIdUc(rs.getInt("idUc"));

              int a = p.getIdClub();
                 System.out.println(a);
                mylist.add(p);

            }
           

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mylist;
    }
      public void supprimeruser(int x) {
        try {
            String requete = "DELETE  FROM inscriptionc WHERE idUc=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, x);
            pst.executeUpdate();
            System.out.println("validééééé");
        } catch (SQLException ex) {
            System.out.println("erreur");
        }}}


