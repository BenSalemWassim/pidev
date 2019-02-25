/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import Entity.JobOwner;
import Services.JobOwnerService;
import Services.LoginService;
import Services.MailingService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class SignUpJobOwnerController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
            JFXTextField txtNom;
    @FXML
            JFXTextField txtPrenom;
    @FXML
            JFXPasswordField password;
    @FXML
            JFXPasswordField password1;
    @FXML
            JFXTextField txtUserName;
    @FXML
            JFXTextField txtAddresse;
    @FXML
            JFXTextField txtEmail;
    @FXML
            Label labNotif;
    @FXML
            TextField txttelephone ;
    
    @FXML
    private StackPane stackpane ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(password1.getText());
    }
    public void ButtonAction(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
        
        if (txtEmail.getText().isEmpty() || txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtUserName.getText().isEmpty()
                ||txttelephone.getText().isEmpty() ||  txtAddresse.getText().isEmpty()
                || password1.getText().isEmpty()|| password.getText().isEmpty()) {
            labNotif.setVisible(true);
            
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("tous les champs doivent être remplis");
        }else if (! isValidEmailAddress(txtEmail.getText())){
            
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("Non valide email format");
        }
        else{
            labNotif.setVisible(false);
            if(! (password1.getText().equals(password.getText()))){
                password1.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                labNotif.setTextFill(Color.TOMATO);
                labNotif.setText("Le mot de passe et le mot de passe \n de confirmation ne correspondent pas");
                labNotif.setVisible(true);
                
            }else{
                labNotif.setVisible(false);
                AjouterJobOwner();
            }
        }
        
        
        
        
        
        
        
        
    }
    
    void AjouterJobOwner() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException{
        JobOwnerService a = new JobOwnerService ();
        
        JobOwner newj = new JobOwner( txtUserName.getText(),a.MD5(password1.getText()),txtNom.getText(),txtPrenom.getText(),
                txtEmail.getText(), txtAddresse.getText(),txttelephone.getText(),"jobowner"  );
        JobOwnerService js = new JobOwnerService() ;
        String res = js.AddJobOwner(newj);
        if( res.equals("ok")){
            System.out.println("ok");
            labNotif.setVisible(true);
            labNotif.setText("ajoutée avec succées");
            LoginService s = new LoginService();
            s.getInstance().changeLoggedUser(newj);
            MailingService m = new MailingService();
            m.envoi(newj.getEmail(), newj.getNom());
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeJobOwner.fxml")));
            Stage stage = (Stage) stackpane.getScene().getWindow();
            
            stage.close();
            stage.setScene(scene);
            stage.show();
        }else if(res.equals("idExist")){
            
            labNotif.setVisible(true);
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("Pseudo déja pris");
        }else if(res.equals("idemailExist")){
            
            labNotif.setVisible(true);
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("Pseudo et email déja pris");
        }else if(res.equals("emailExist")){
            
            labNotif.setVisible(true);
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("email déja pris");
        }
        
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    public void back(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
        
        stackpane.getChildren().setAll(root);
    }
}