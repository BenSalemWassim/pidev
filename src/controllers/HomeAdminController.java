/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

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

public class HomeAdminController implements Initializable {
    @FXML
    private BorderPane root ;
    @FXML
    private Label nom ;
    @FXML
            StackPane stackpane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void deco() throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
                stackpane.getChildren().removeAll();

        stackpane.getChildren().addAll(root);
        LoginService.getInstance().userLogout();
        
    }
    @FXML
    public void Listf(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminListFreelance.fxml"));
        Parent view = (Parent) loader.load();
        root.setCenter(view);
    }
     @FXML
    public void Listj(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminListJobOwner.fxml"));
        Parent view = (Parent) loader.load();
        root.setCenter(view);
    }
}
