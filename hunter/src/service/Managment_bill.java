/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Outils.ConnectionDATAB;
import entity.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alara
 */
public class Managment_bill {
    
        private Connection con = ConnectionDATAB.getInstance().getCnx();

        
    public void addbill(Bill b){
        Statement st;
            try {
                String req = "insert into bills (id_bill,date) values (?,?)";
                PreparedStatement pt = con.prepareStatement(req);
                pt.setInt(1, b.getId_bill());
                pt.setDate(2, new java.sql.Date(2020-02-12));
                pt.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(Managment_bill.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void showbill(){
            try {
                PreparedStatement ps = con.prepareStatement("select * from bills");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                System.out.println("Bill [id_bill : " + rs.getInt(1) + ",Date:  " + rs.getDate(2));
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(Managment_bill.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void deletebill(int id_bill){
            try {
                PreparedStatement ps = con.prepareStatement("delete from bills where id_bill=?");
                ps.setInt(1,id_bill);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Managment_bill.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}
