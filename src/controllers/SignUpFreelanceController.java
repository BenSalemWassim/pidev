/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import Entity.Freelance;
import Entity.JobOwner;
import Services.FreelanceService;
import Services.JobOwnerService;
import Services.LoginService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class SignUpFreelanceController implements Initializable {
    
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
            Label nom;
    @FXML
            Label prenom;
    @FXML
            Label pseudo;
    @FXML
            Label email;
    @FXML
            Label addresse;
    @FXML
            Label telephone;
    @FXML
            Label secteurx;
    @FXML
            Label err;
    @FXML
            JFXTextField txttelephone ;
    
    @FXML
            JFXButton d1 ;
    @FXML
            JFXButton d2 ;
    @FXML
            JFXButton d3 ;
    @FXML
            JFXButton d4 ;
    @FXML
            JFXButton d5 ;
    @FXML
            JFXButton d6 ;
    @FXML
            JFXButton d7 ;
    @FXML
            JFXButton d8 ;
    @FXML
            JFXButton d9 ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        translateAnimation(0.5,pane2,1000);
        translateAnimation(0.5,pane3,1000);
        System.out.println(password1.getText());
    }
    
    
    
    
    
    
    @FXML
    private AnchorPane pane1;
    
    @FXML
    private AnchorPane pane2;
    
    @FXML
    private AnchorPane pane3;
    
    @FXML
    private Label countLabel;
    @FXML
    private StackPane dad;
    
    public void translateAnimation(double duration, Node node, double byX){
        
        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(duration) , node);
        translateTransition.setByX(byX);
        translateTransition.play();
        
    }
    
    
    
    
    int showSlide=0;
    
    @FXML
                            void nextAction(ActionEvent event) {
                                
                                if (showSlide==0) {
                                    translateAnimation(0.5, pane2, -1000);
                                    showSlide++; //showSlide=1
                                    countLabel.setText("2/3");
                                }else if (showSlide==1){
                                    
                                    translateAnimation(0.5, pane3, -1000);
                                    showSlide++; //showSlide=2
                                    countLabel.setText("3/3");
                                    nom.setText(txtNom.getText());
                                    prenom.setText(txtPrenom.getText());
                                    addresse.setText(txtAddresse.getText());
                                    pseudo.setText(txtUserName.getText());
                                    email.setText(txtEmail.getText());
                                    telephone.setText(txttelephone.getText());
                                    
                                    
                                }else {
                                    System.out.println("No more slides");
                                }
                                
                            }
                            
                            @FXML
                            void backAction(ActionEvent event) throws IOException {
                                
                                if (showSlide==0){
                                    
                                    
                                }else if(showSlide==1){
                                    translateAnimation(0.5, pane2, 1000);
                                    showSlide--; //showSlide=0
                                    countLabel.setText("1/3");
                                }else if(showSlide==2){
                                    translateAnimation(0.5, pane3, 1000);
                                    showSlide--; //showSlide=1
                                    countLabel.setText("2/3");
                                }
                                
                            }
                            String secteur ="";
                            public void handle(final ActionEvent event) {
                                
                                if (event.getSource() == d1){
                                    secteur="Développeur web";
                                }
                                else if(event.getSource() == d2)
                                    secteur="Développeur mobiles";
                                else if(event.getSource() == d3)
                                    secteur="Les designer et créatifs";
                                else if(event.getSource() == d4)
                                    secteur="Ecrivain";
                                else if(event.getSource() == d5)
                                    secteur="Agent de service à la clientèle";
                                else if(event.getSource() == d6)
                                    secteur="Expert en marketing";
                                else if(event.getSource() == d7)
                                    secteur="Comptable et Consultant";
                                else if(event.getSource() == d8)
                                    secteur="Informatique et réseaux";
                                else if(event.getSource() == d9)
                                    secteur="Science des données et analyses";
                                
                                secteurx.setText(secteur);
                                
                                nextAction(event);
                                
                                
                                
                                
                            }
                            public void ButtonAction(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
                                if (txtEmail.getText().isEmpty() || txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtUserName.getText().isEmpty()
                                        ||txttelephone.getText().isEmpty() ||  txtAddresse.getText().isEmpty()
                                        || password1.getText().isEmpty()|| password.getText().isEmpty()) {
                                    labNotif.setVisible(true);
                                    err.setVisible(true);
                                    
                                    labNotif.setTextFill(Color.TOMATO);
                                    labNotif.setText("tous les champs doivent être remplis");
                                    err.setTextFill(Color.TOMATO);
                                    err.setText("tous les champs doivent être remplis");
                                    
                                    
                                    
                                }else if (! isValidEmailAddress(txtEmail.getText())){
                                    
                                    labNotif.setTextFill(Color.TOMATO);
                                    labNotif.setText("Non valide email format");
                                    err.setTextFill(Color.TOMATO);
                                    err.setText("Non valide email format");
                                    
                                } else if (secteur.equals("")){
                                    
                                    err.setTextFill(Color.TOMATO);
                                    err.setText("Choisit un secteur");
                                }
                                else{
                                    labNotif.setVisible(false);
                                    err.setVisible(false);
                                    
                                    if(! (password1.getText().equals(password.getText()))){
                                        password1.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                                        labNotif.setTextFill(Color.TOMATO);
                                        labNotif.setText("Le mot de passe et le mot de passe \n de confirmation ne correspondent pas");
                                        err.setTextFill(Color.TOMATO);
                                        err.setText("Le mot de passe et le mot de passe de confirmation ne correspondent pas");
                                        labNotif.setVisible(true);
                                        err.setVisible(true);
                                        
                                    }else{
                                        labNotif.setVisible(false);
                                        err.setVisible(false);
                                        
                                        AjouterFreelance();
                                    }
                                }
                                
                                
                                
                                
                                
                                
                                
                                
                            }
                            
                            void AjouterFreelance() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException{
                                FreelanceService a = new FreelanceService ();
                                
                                Freelance newj = new Freelance(secteur, txtUserName.getText(),a.MD5(password1.getText()),txtNom.getText(),txtPrenom.getText(),
                                        txtEmail.getText(), txtAddresse.getText(),txttelephone.getText() ,"freelance" );
                                FreelanceService js = new FreelanceService() ;
                                String res = js.AddFreelance(newj);
                                if( res.equals("ok")){
                                    System.out.println("ok");
                                    labNotif.setVisible(true);
                                    labNotif.setText("ajoutée avec succées");
                                    err.setVisible(true);
                                    err.setText("ajoutée avec succées");
                                    LoginService s = new LoginService();
                                    s.getInstance().changeLoggedUser(newj);
                                    
                                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeFreelance.fxml")));
                                    Stage stage = (Stage) dad.getScene().getWindow();
                                    
                                    stage.close();
                                    stage.setScene(scene);
                                    stage.show();                       
                                }else if(res.equals("idExist")){
                                    
                                    labNotif.setVisible(true);
                                    labNotif.setTextFill(Color.TOMATO);
                                    labNotif.setText("Pseudo déja pris");
                                    err.setVisible(true);
                                    err.setTextFill(Color.TOMATO);
                                    err.setText("Pseudo déja pris");
                                }else if(res.equals("idemailExist")){
                                    
                                    labNotif.setVisible(true);
                                    labNotif.setTextFill(Color.TOMATO);
                                    labNotif.setText("Pseudo et email déja pris");
                                    err.setVisible(true);
                                    err.setTextFill(Color.TOMATO);
                                    err.setText("Pseudo et email déja pris");
                                }else if(res.equals("emailExist")){
                                    
                                    labNotif.setVisible(true);
                                    labNotif.setTextFill(Color.TOMATO);
                                    labNotif.setText("email déja pris");
                                    
                                    err.setVisible(true);
                                    err.setTextFill(Color.TOMATO);
                                    err.setText("email déja pris");
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
                                
                                dad.getChildren().add(root);
                            }
}