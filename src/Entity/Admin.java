/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author wassim
 */
public class Admin extends User{

    public Admin(String id, String password, String nom, String prenom, String email, String addresse, String telephone, String type) {
        super(id, password, nom, prenom, email, addresse, telephone, type);
    }

   

    public Admin() {
    }
    

    
    
    
    
}
