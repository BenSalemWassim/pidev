/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Entity.User;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

/**
 *
 * @author wassim
 */
public class LoginService {
    public  static User loggedUser  = null;
          private Stage stage;

        
    
    public String logIn(String id ,String email ,String password) {
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        con = ConnectionUtil.getInstance();
        
        //query
        String sql = "SELECT * FROM jobowner , freelance Where ( (freelance.id = ? or freelance.email = ? )or (jobowner.id = ? or jobowner.email = ?))and (jobowner.password = ? or freelance.password = ?) ";
        
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, email);
              preparedStatement.setString(3, id);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
                        preparedStatement.setString(6, password);

            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                LoginService.loggedUser = new User(resultSet.getString("id"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6), resultSet.getString(7));
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
public String AdminlogIn(String id ,String email ,String password) {
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        con = ConnectionUtil.getInstance();
        
        //query
        String sql = "SELECT * FROM admin Where ( id = ? or email = ? ) and password = ?  ";
        
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
                        preparedStatement.setString(2, email);
               preparedStatement.setString(3, password);
         

            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                LoginService.loggedUser = new User(resultSet.getString("id"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6), resultSet.getString(7));
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
    


    public User getLoggedUser() {
        return loggedUser;
    }

    

    public void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    public void gotoProfile() {
        try {
            replaceSceneContent("/fxml/OnBoard.fxml");
        } catch (Exception ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("/fxml/login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(LoginService.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 550);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}
