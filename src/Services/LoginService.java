/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Entity.Interfaces.LoginServiceInterface;
import Entity.Admin;
import Entity.Freelance;
import Entity.JobOwner;
import Entity.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ConnectionUtil;

/**
 *
 * @author wassim
 */
public class LoginService implements LoginServiceInterface {
    public  static User loggedUser  = null;
          private Stage stage;

        
    
    @Override
    public String logIn(String id ,String email ,String password) throws SQLException {
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        con = ConnectionUtil.getInstance();
        
        //query
        String sql = "SELECT * FROM user Where ( (id = ? or email = ? )and (password = ?) )";
        
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, email);
              preparedStatement.setString(3, password);

            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                if(resultSet.getString("type").equals("freelance")){
      LoginService.loggedUser = new Freelance(resultSet.getString("secteur"),resultSet.getString("id"),resultSet.getString("password"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("addresse"), resultSet.getString("telephone"),resultSet.getString("type"),resultSet.getString("photo"));

                }else  if(resultSet.getString("type").equals("jobowner")){
      LoginService.loggedUser = new JobOwner(resultSet.getString("id"),resultSet.getString("password"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("addresse"), resultSet.getString("telephone"),resultSet.getString("type"),resultSet.getString("photo"));
                }
                System.out.println(loggedUser) ;
                return "Success";
            } else {
                
                System.err.println("Wrong Logins --///");
                return "Error";
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
        
        
        
    }
    @Override
    public String AdminlogIn(String id ,String email ,String password) {
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        con = ConnectionUtil.getInstance();
        
        //query
        String sql = "SELECT * FROM user Where ( id = ? or email = ? ) and password = ? and type = 'admin' ";
        
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
                        preparedStatement.setString(2, email);
               preparedStatement.setString(3, password);
         

            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
      LoginService.loggedUser = new Admin(resultSet.getString("id"),resultSet.getString("password"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("addresse"), resultSet.getString("telephone"),resultSet.getString("type"));
                System.out.println(loggedUser) ;
                return "Success";
            } else {
                
                System.err.println("Wrong Logins --///");
                return "Error";
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
        
        
        
    }

    private static LoginService instance;

    public LoginService() {
        instance = this;
    }

    public static LoginService getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    


    @Override
    public User getLoggedUser() {
        return loggedUser;
    }

    
    @Override
 public void changeLoggedUser(User user){
        loggedUser = user;
    }
 
 
    @Override
    public void userLogout(){
        loggedUser = null;
    }


    
    @Override
       public String MD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        byte[] bytesOfMessage = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        BigInteger bigInt = new BigInteger(1,thedigest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    
}
