/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;


/**
 *
 * @author alara
 */
public class Bill {
    
    private int id_bill;
    private Date date ;

    public Bill() {
    }

    public Bill(int id_bill) {
        this.id_bill = id_bill;
    }
    
    
    public Bill(int id_bill, Date date) {
        this.id_bill = id_bill;
        this.date = date;
    }

    public int getId_bill() {
        return id_bill;
    }

    public Date getDate() {
        return date;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bill{" + "id_bill=" + id_bill + ", date=" + date + '}';
    }
    
    
    
    
    
}
