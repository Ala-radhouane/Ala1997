/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author alara
 */
public class Products {
    
    private int id_product ;
    private String name ;
    private int price;
    

    public Products(int id_product, String name, int price) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        
    }

    public int getId_product() {
        return id_product;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }


    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = (int) price;
    }


    @Override
    public String toString() {
        return "Products{" + "id_product=" + id_product + ", name=" + name + ", price=" + price + '}';
    }
    
    
    
}
