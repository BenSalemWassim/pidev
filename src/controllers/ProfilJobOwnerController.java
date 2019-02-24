/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import Entity.Freelance;
import Entity.JobOwner;
import Entity.User;
import Services.FreelanceService;
import Services.JobOwnerService;
import Services.LoginService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author wassim
 */


public class ProfilJobOwnerController implements Initializable {
    @FXML
    private Label title ;
    @FXML
            JFXButton aa;
     @FXML
            JFXButton aa1;
    
    @FXML
            JFXTextField txtNom;
    @FXML
            JFXTextField txtPrenom;
    @FXML
            JFXPasswordField password;
    @FXML
            JFXPasswordField password1;
    
    @FXML
            JFXTextField txtAddresse;
  
    
    @FXML
            TextField txttelephone ;
    
    @FXML
    private StackPane stackpane ;
    @FXML
    private Label labNotif ;
     @FXML
    private Label labNotif1 ;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        User cu = LoginService.getInstance().getLoggedUser();
      
        txtNom.setText(cu.getNom());
        
        txtPrenom.setText(cu.getPrenom());
        txtAddresse.setText(cu.getAddresse());
        txttelephone.setText(cu.getTelephone());
        System.out.print(cu.getType());
      
        
    }
    
    
    
    public void ButtonAction(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        
        if ( txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() 
                ||txttelephone.getText().isEmpty() ||  txtAddresse.getText().isEmpty()
                ) {
            labNotif.setVisible(true);
            
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("tous les champs doivent être remplis");
            
          
        }
        
        else{
            labNotif.setVisible(false);
              User cu = LoginService.getInstance().getLoggedUser();

                                     ModifierUserJobOwner();

             
        }
        }
        
      
    
    void ModifierUserJobOwner() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        JobOwnerService a = new JobOwnerService ();
                User cu = LoginService.getInstance().getLoggedUser();

        JobOwner newj = new JobOwner( cu.getId(),a.MD5(password1.getText()),txtNom.getText(),txtPrenom.getText(),
                cu.getEmail(), txtAddresse.getText(),txttelephone.getText(),"jobowner"  );
        JobOwnerService js = new JobOwnerService() ;
        js.modifierJobOwner(newj, cu.getId());
        
        
    }
    

    public void ButtonAction1(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        
        if (password.getText().isEmpty() ||  password1.getText().isEmpty()  ) {
            labNotif1.setVisible(true);
            
            labNotif1.setTextFill(Color.TOMATO);
            labNotif1.setText("tous les champs doivent être remplis");
        }
        
        else{
            labNotif.setVisible(false);
                    JobOwnerService js = new JobOwnerService() ;
                User cu = LoginService.getInstance().getLoggedUser();

            js.changerMDP(js.MD5(password.getText()),js.MD5(password1.getText()),cu.getId());
        }
        
        
    }
  
}

