/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.tests;
import edu.gestionClub.entities.User;
import edu.gestionClub.entities.UserCrud;
//import edu.gestionClub.entities.Club;
//import edu.gestionClub.entities.ClubCrud;
import edu.gestionClub.entities.MyConnection;
import edu.gestionClub.entities.UserServices;
import java.sql.SQLException;
import javax.swing.JFrame;




/**
 *
 * @author ASUS
 */
public class GestionClub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
       // ClubCrud lcrud = new ClubCrud();
       // Club L = new Club("dfghr", "bardo", "non_dispo", 91);
       //lcrud.affClub();
       // lcrud.ajouterClub(L);
      // lcrud.affClub();
      // lcrud.modifierClub(L, 3);
      // lcrud.affClub();
     // lcrud.suprrimerClub(6);
     // lcrud.affClub();
      // lcrud.searchclub(5);
      UserCrud lcrud = new UserCrud();
    //    User L = new User("salah","laouina", 91356987);
      // lcrud.modifierUser(L, 3);
      // lcrud.suprrimerUser(3);
       //lcrud.affUser();
        UserServices a = new UserServices();
        a.displayUser();
    }
    
}
