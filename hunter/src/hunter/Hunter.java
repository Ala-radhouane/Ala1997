/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunter;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entity.Bill;
import entity.Products;
import entity.Promotion;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;
import service.Managment_bill;
import service.Managment_product;
import service.Promo_codes;

/**
 *
 * @author alara
 */
public class Hunter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Products p1 = new Products(10,"reload",73);
       // Managment_product gestion = new Managment_product();
       // gestion.addproduct(p1);
       // gestion.showproducts();
        //gestion.updateproducts(2, "Footwear_boots");
        // gestion.showproducts();
        // gestion.searchproduct(5);
      //  Random code = new Random();
        //int number = code.nextInt(100000)-666;
        //Promotion po = new Promotion(1,number);
        //Promo_codes promo = new Promo_codes();
        //promo.addpromo_code(po);
        //promo.showpromo_code();
       // promo.deletepromo_code(po);
       // promo.showpromo_code();
      // promo.updatepromo_code(5, number);
       // Bill b =new Bill(7574);
        Managment_bill mng = new Managment_bill();
       // mng.addbill(b);
      //  mng.showbill();
        mng.deletebill(214);
        
    
        
    }
    
}
