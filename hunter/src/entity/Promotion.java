/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Random;

/**
 *
 * @author alara
 */
public class Promotion  {
    private int id_product ;
    private int promo_code ;

    public Promotion(int id_product, int promo_code) {
        this.id_product = id_product;
        this.promo_code = promo_code;
    }

    public int getId_product() {
        return id_product;
    }

    public int getPromo_code() {
        return promo_code;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setPromo_code(int promo_code) {
        this.promo_code = promo_code;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id_product=" + id_product + ", promo_code=" + promo_code + '}';
    }

    
    

 
    
    
    
    
}
