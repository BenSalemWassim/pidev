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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

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
    private ImageView imagev;

    @FXML
    private JFXButton btnphoto;
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
            m.envoi(newj.getEmail(), "Bienvenue "+ newj.getNom() +" sur SmartStart");
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
     @FXML
    void handleButtonAction(ActionEvent event) {

 String img="";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file=fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            img=file.toURI().toString();
            imagev.setImage(image);
        }

        String server = "127.0.0.1";
        int port = 21;
        String user = "root";
        String pass = "root";

        FTPClient ftpClient = new FTPClient();
        try {
            
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            img=img.substring(6);
            File firstLocalFile = new File(img);
            String firstRemoteFile = file.getName();
             ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

 
            InputStream inputStream = new FileInputStream(firstLocalFile);
                      System.out.println("Start uploading first file");

            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            
            inputStream.close();
            
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }    }
}