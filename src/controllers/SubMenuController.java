/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import javafx.beans.property.ReadOnlyObjectProperty;

/**
 *
 * @author wassim
 */
public interface SubMenuController {
       public ReadOnlyObjectProperty<URL> selectedViewProperty();  
     public URL getSelectedView() ;  
    
}
