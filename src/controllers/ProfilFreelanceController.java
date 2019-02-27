/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import Entity.Freelance;
import Entity.User;
import Services.FreelanceService;
import Services.JobOwnerService;
import Services.LoginService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * FXML Controller class
 *
 * @author wassim
 */

public class ProfilFreelanceController implements Initializable {
    
    
    @FXML
    private ImageView imagev;
    @FXML
    private Label title ;
    @FXML
    private      JFXButton aa;
    @FXML
    private      JFXButton aa1;
    
    @FXML
    private    JFXTextField txtNom;
    @FXML
    private   JFXTextField txtPrenom;
    @FXML
    private   JFXPasswordField password;
    @FXML
    private   JFXPasswordField password1;
    
    @FXML
    private    JFXTextField txtAddresse;
    
    
    @FXML
    private    TextField txttelephone ;
    
    @FXML
    private StackPane stackpane ;
    @FXML
    private Label labNotif ;
    @FXML
    private Label labNotif1 ;
    @FXML
    private Label sec;
     @FXML
    private Label mail;
     
     
         String firstRemoteFile;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        Freelance cu = (Freelance)LoginService.getInstance().getLoggedUser();
              load(cu.getPhoto());

               sec.setVisible(true);
        System.out.print(cu.getSecteur());
firstRemoteFile= cu.getPhoto();
        sec.setText(cu.getSecteur());
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
                ||txttelephone.getText().isEmpty() ||  txtAddresse.getText().isEmpty() || firstRemoteFile.equals("")
                ) {
            labNotif.setVisible(true);
            
            labNotif.setTextFill(Color.TOMATO);
            labNotif.setText("tous les champs doivent être remplis");
        }
        
        else{
            labNotif.setVisible(false);
            User cu = LoginService.getInstance().getLoggedUser();
            ModifierUserFreelancer();
            
            
        }
    }
    void ModifierUserFreelancer() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        FreelanceService a = new FreelanceService ();
        Freelance cu =(Freelance) LoginService.getInstance().getLoggedUser();
        
        Freelance newf = new Freelance(cu.getSecteur(),cu.getId(),a.MD5(password1.getText()),txtNom.getText(),txtPrenom.getText(),
                cu.getEmail(), txtAddresse.getText(),txttelephone.getText(),"freelance",firstRemoteFile );
        FreelanceService js = new FreelanceService() ;
        a.modifierFreelance(newf, cu.getId());
        
        
    }
    public void ButtonAction1(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        
        if (password.getText().isEmpty() ||  password1.getText().isEmpty()  ) {
            labNotif1.setVisible(true);
            
            labNotif1.setTextFill(Color.TOMATO);
            labNotif1.setText("tous les champs doivent être remplis");
            
        }
        else{
            labNotif.setVisible(false);
            FreelanceService js = new FreelanceService() ;
            Freelance cu =(Freelance) LoginService.getInstance().getLoggedUser();
            
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
