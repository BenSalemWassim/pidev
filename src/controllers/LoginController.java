package controllers;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import Entity.User;
import Services.JobOwnerService;
import Services.LoginService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.ConnectionUtil;
/**
 *
 * @author oXCToo
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label lblErrors;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private Button btnSignin;
    
    @FXML
    private StackPane stackpane;
    @FXML
    private AnchorPane anchorpane;
     @FXML
    private CheckBox admin;
     
    /// --
    
    @FXML
    public void handleButtonAction(ActionEvent  event) {
 

            //login here
             if (event.getSource() == btnSignin) {
                if(admin.isSelected()) {
                  if (AdminlogIn().equals("Success")) {
                         try {
                    
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeAdmin.fxml")));
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                  
                  } 
    
    }else{
            
            if (logIn().equals("Success")) {
                try {
                    
                    //add you loading or delays - ;-)
                  
                   
                      
        
                                    User cu = LoginService.getInstance().getLoggedUser();

                    if(cu.getType().equals("freelance")){
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeFreelance.fxml")));
                      Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                     stage.setScene(scene);
                    stage.show();
                    }else if(cu.getType().equals("jobowner")){
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeJobOwner.fxml")));
                                          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                 stage.setScene(scene);
                    stage.show();
                    }
                    
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                
            }
        }
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection con = ConnectionUtil.getInstance();
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error");
            JFXDialogLayout content = new JFXDialogLayout();
            String Text="Server Error";
            String body=" Une maintenance de nos serveurs est en cours! Nous serons bientôt de retour! ";
            content.setHeading(new Text(Text));
            content.setBody(new Text(body));
            
            JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, true);
            JFXButton button = new JFXButton("Okay");
            button.setStyle("-fx-background-color: #0C39FF; -fx-text-fill: white;");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
            button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
            button.setPrefHeight(32);
            content.setActions(button);
            dialog.show();
        } else {
            
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }
    
    public LoginController() {
        
    }
    
    //we gonna use string to check for status
    private String logIn()   {
        
        String id = txtUsername.getText();
        String password = txtPassword.getText();
        //query
        
        try {

           LoginService l =new LoginService();
            String res = l.getInstance().logIn(id,id,l.MD5(password));
            
            if (res.equalsIgnoreCase("Error")) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Email ou mot de passe non valide.");
                System.err.println("Wrong Logins --///");
                return "Error";
                
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Login Successful..Redirecting..");
                System.out.println("Successfull Login");
                return "Success";
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
        
    }
    
     private String AdminlogIn()   {
        
        String id = txtUsername.getText();
        String password = txtPassword.getText();
        //query
        
        try {

           LoginService l =new LoginService();
            String res = l.getInstance().AdminlogIn(id,id,l.MD5(password));
            
            if (res.equalsIgnoreCase("Error")) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Email ou mot de passe non valide.");
                System.err.println("Wrong Logins --///");
                return "Error";
                
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Login Successful..Redirecting..");
                System.out.println("Successfull Login");
                return "Success";
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
        
    }
    @FXML
    public void SignUp(ActionEvent event) throws IOException{
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
            stackpane.getChildren().remove(anchorpane);
            
            stackpane.getChildren().add(root);
            
            //Create new TimeLine animation
            //Animate Y property
            
            
            
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public void mdpo(ActionEvent event) throws IOException{
    
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/MdpOublie.fxml"));
            
            stackpane.getChildren().add(root);
    }
}

