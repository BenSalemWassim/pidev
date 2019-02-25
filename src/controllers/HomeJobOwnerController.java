/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import Entity.Freelance;
import Entity.User;
import Services.LoginService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oXCToo
 */
public class HomeJobOwnerController implements Initializable {

    

     @FXML  
     private BorderPane root ;
     @FXML  
     private Label nom ;
   
 
  public void initialize() {  
      
     
      
//         try {
//             // selectedView property of currentSubMenu:
//             
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userSubMenu.fxml"));
//             Parent userSubMenu = (Parent) loader.load();
//             System.out.println("jj");
//             currentSubMenuController.set((SubMenuController)loader.getController());
//             
//             
//             root.setLeft(userSubMenu);
//             ObservableValue<URL> subMenuSelectedView = Bindings.<URL>select(currentSubMenuController, "selectedView");
//             subMenuSelectedView.addListener(new ChangeListener<URL>() {
//                 public void changed(ObservableValue<? extends URL> obs, URL oldView, URL newView) {
//                     if (newView == null) {
//                         root.setCenter(null);
//                     } else {
//                         try {
//                               FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profil.fxml"));
//                             Parent view = (Parent) loader.load();
//                             root.setCenter(view);
//                         } catch (IOException ex) {
//                             Logger.getLogger(HomeJobOwnerController.class.getName()).log(Level.SEVERE, null, ex);
//                         }
//                     }
//                 }
//             });
//             // ...  
//         } catch (IOException ex) {
//             Logger.getLogger(HomeJobOwnerController.class.getName()).log(Level.SEVERE, null, ex);
//         }
     }  
  
  
  
  @FXML
  public void ProfilAction(ActionEvent event) throws IOException{
      User cu = LoginService.getInstance().getLoggedUser();
      
    
     
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profil.fxml"));
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
   

  
    
}
