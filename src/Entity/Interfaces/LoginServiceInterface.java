/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Interfaces;

import Entity.User;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author wassim
 */
public interface LoginServiceInterface {

    String AdminlogIn(String id, String email, String password);

    String MD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    void changeLoggedUser(User user);

    User getLoggedUser();

    String logIn(String id, String email, String password) throws SQLException;

    void userLogout();
    
}
