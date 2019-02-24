/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Services.LoginService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class OnBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton profil ;
     private JFXButton deconnexion ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 private final ObjectProperty<SubMenuController> currentSubMenuController = new SimpleObjectProperty<>();  
     @FXML  
     private BorderPane root ; // root element of home.fxml, injected as usual with fx:id="root"  
     // ...  
     public void handleUser() throws IOException {  
          // Note I fixed you loader code, which is slightly incorrect (doesn't quite do what you think):  
          FXMLLoader loader = new FXMLLoader(getClass().getResource("userSubMenu.fxml"));  
          Parent userSubMenu = (Parent) loader.load();  
          currentSubMenuController.set((SubMenuController)loader.getController());  
          root.setLeft(userSubMenu);  }
    
    
    
}
