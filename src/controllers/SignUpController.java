/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private StackPane stackpane;
    @FXML
    private AnchorPane anchorpane;
    /// --
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void JobOwnerAction(ActionEvent event) throws IOException{
        
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUpJobOwner.fxml"));
                  stackpane.getChildren().remove(anchorpane);

        stackpane.getChildren().add(root);
 
        //Create new TimeLine animation
        //Animate Y property
      
       
    
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void FreelanceAction(ActionEvent event) throws IOException{
        
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUpFreelancer.fxml"));
                  stackpane.getChildren().remove(anchorpane);

        stackpane.getChildren().add(root);
 
        //Create new TimeLine animation
        //Animate Y property
      
       
    
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void back(ActionEvent event) throws IOException{
     
  Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            
            stackpane.getChildren().add(root);       
            }  
}
