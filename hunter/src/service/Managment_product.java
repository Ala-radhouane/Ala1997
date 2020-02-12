/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Products;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Outils.ConnectionDATAB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alara
 */
public class Managment_product {
    
    private Connection con = ConnectionDATAB.getInstance().getCnx();
    
    public void addproduct(Products p){
        Statement st;
        try {
            st = con.createStatement();
            String req = "insert into products values (" + p.getId_product() + ",'" + p.getName() + "','" + p.getPrice()+"')" ;
            st.executeUpdate(req);            
        } catch (SQLException ex) {
            Logger.getLogger(Managment_product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showproducts(){
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Products [id : " + rs.getInt(1) + ",name:  " + rs.getString(2) + ",Price:  " + rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Managment_product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateproducts(int id_product,String name){
        try {
            PreparedStatement pt = con.prepareStatement("update products set name=? where id_product=?");
            pt.setString(1, name);
            pt.setInt(2, id_product);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Managment_product.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteproduct(Products p){
        try {
            PreparedStatement pt =con.prepareStatement("delete from products where id_product=?");
            pt.setInt(1,p.getId_product());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Managment_product.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public void searchproduct(int id_product){
        try {
            PreparedStatement pt =con.prepareStatement("select * from products where id_product=?");
            pt.setInt(1,id_product);
          ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Products [id : " + rs.getInt(1) + ",name:  " + rs.getString(2) + ",Price:  " + rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Managment_product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
