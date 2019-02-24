/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class UserSubMenuController implements SubMenuController {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton haha;
    
     private final ReadOnlyObjectWrapper<URL> selectedView = new ReadOnlyObjectWrapper<>(this, "selectedView", null);  
     @Override  
     public final ReadOnlyObjectProperty<URL> selectedViewProperty() {  
          return selectedView.getReadOnlyProperty();
     }  
     @Override  
     public final URL getSelectedView() {  
          return selectedView.get();  
     }  
     // handler method for selection button:  
     @FXML
     public void ProfilAction() throws MalformedURLException {  
         selectedView.set(new URL("file:///Users/wassim/Documents/NetBeansProjects/Pidev/src/fxml/Profil.fxml")); // url for view you want in the center of the BorderPane  
     }  
     
   
}
