/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entity.User;
import Services.LoginService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author wassim
 */

public class HomeFreelanceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML  
     private BorderPane root ;
     @FXML  
     private Label nom ;
     
    @FXML
  public void ProfilAction(ActionEvent event) throws IOException{
      User cu = LoginService.getInstance().getLoggedUser();
      
 
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfilFreelance.fxml"));
          Parent view = (Parent) loader.load();
          root.setCenter(view);
      
     
      
      
      
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 User cu = LoginService.getInstance().getLoggedUser();
 System.out.print(cu);
      nom.setText(cu.getId());
    
    
    }
  
  @FXML StackPane stackpane;
 public void deco() throws IOException {
  
         
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

        stackpane.getChildren().addAll(root);
      LoginService.getInstance().userLogout();

    }
 
 
    @FXML
    void chat(ActionEvent event) throws IOException {
        
        

     
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chat.fxml"));
          Parent view = (Parent) loader.load();
          root.setCenter(view);
          
      
      
    }
      @FXML

   void msgs (ActionEvent event) throws IOException{
   
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chatClient.fxml"));
          Parent view = (Parent) loader.load();
          root.setCenter(view);
   }
    
}
