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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author wassim
 */


public class ProfilJobOwnerController implements Initializable {
    
    @FXML
    private ImageView imagev;
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
    @FXML
    private Label mail ;
         String firstRemoteFile;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        User cu = LoginService.getInstance().getLoggedUser();
      load(cu.getPhoto());
      firstRemoteFile= cu.getPhoto();

        txtNom.setText(cu.getNom());
                mail.setText(cu.getEmail());
              txtPrenom.setText(cu.getPrenom());
        txtAddresse.setText(cu.getAddresse());
        txttelephone.setText(cu.getTelephone());
        System.out.print(cu.getType());
      
        
    }
    
      void load(String photo) {
        

		URL url = null;
        URLConnection urlc = null;
        try {
            url = new URL("ftp://root:root@127.0.0.1/"+photo);
            urlc = url.openConnection();
            InputStream is = urlc.getInputStream();
            imagev.setImage(new Image(is));

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                cu.getEmail(), txtAddresse.getText(),txttelephone.getText(),"jobowner",firstRemoteFile  );
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
   
     @FXML
    void changerphoto(ActionEvent event) {

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
            firstRemoteFile = file.getName();
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

