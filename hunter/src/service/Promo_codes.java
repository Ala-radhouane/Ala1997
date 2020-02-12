/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Outils.ConnectionDATAB;
import entity.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alara
 */
public class Promo_codes {
    
    private Connection con = ConnectionDATAB.getInstance().getCnx();
    
    public void addpromo_code(Promotion po){
        Statement st;
        try {
            st = con.createStatement();
            String req ="insert into promo_codes values (" + po.getId_product() + ","+ po.getPromo_code() +")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Promo_codes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void showpromo_code(){
        PreparedStatement ps ;
        try {
            ps = con.prepareStatement("select * from promo_codes");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Promo_codes [id : " + rs.getInt(1) + ",promo_code:  " + rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Promo_codes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deletepromo_code(Promotion p){
        try {
            PreparedStatement ps = con.prepareStatement("delete from promo_codes where promo_code=?");
            ps.setInt(1, p.getPromo_code());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Promo_codes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updatepromo_code(int id_product,int promo_code){
        try {
            PreparedStatement ps = con.prepareStatement("update promo_codes set id_product=? where promo_code=?");
            ps.setInt(1, id_product);
            ps.setInt(2, promo_code);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Promo_codes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
