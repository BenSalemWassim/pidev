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
import Services.MailingService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class AdminListJobOwnerController implements Initializable {

    @FXML
    StackPane stackpane;
     @FXML
    private ImageView imagev;
    @FXML
    private TableView<JobOwner> tableview;
    @FXML
    private TableColumn<JobOwner, String> idf;
    @FXML
    private TableColumn<JobOwner, String> emailf;
    
    @FXML
    private Label tel;
    
    @FXML
    private Label adresse;
    @FXML
    private Label pseudo;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private JFXButton btndel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btndel.setVisible(false);
        JobOwnerService fs = new JobOwnerService();
        ArrayList<JobOwner> list = (ArrayList<JobOwner>) fs.afficherJobOwners();
        ObservableList<JobOwner> frs=FXCollections.observableArrayList(list);
        
        tableview.setItems(frs);
        idf.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailf.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        tableview.setOnMousePressed( new EventHandler<MouseEvent>(){
            
            @Override
            public void handle (MouseEvent event){
                      imagev.setImage(null);
                        btndel.setVisible(true);

              
                pseudo.setText("Pseudo "+tableview.getSelectionModel().getSelectedItem().getId());
                nom.setText("Nom "+tableview.getSelectionModel().getSelectedItem().getNom());
                prenom.setText("Prenom "+tableview.getSelectionModel().getSelectedItem().getPrenom());
                email.setText("Email "+tableview.getSelectionModel().getSelectedItem().getEmail());
                adresse.setText("Adresse "+tableview.getSelectionModel().getSelectedItem().getAddresse());
                tel.setText("Telephone "+tableview.getSelectionModel().getSelectedItem().getTelephone());
                     
               if(tableview.getSelectionModel().getSelectedItem().getPhoto() != null)
                   load(tableview.getSelectionModel().getSelectedItem().getPhoto()) ;



            }
        });
        
        
       
        
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
     @FXML
        public void deletef(ActionEvent event){
            JobOwnerService fs = new JobOwnerService() ;
           String r= fs.supprimerJobOwner(tableview.getSelectionModel().getSelectedItem().getId());
           if(r.equalsIgnoreCase("ok")){
               MailingService m = new MailingService();                                                 
            m.envoi(tableview.getSelectionModel().getSelectedItem().getEmail(), "Nous sommes désolé "+ tableview.getSelectionModel().getSelectedItem().getNom() +" votre compte a été supprimé par l'administrateur ");

           ObservableList<JobOwner> JobOwnerSelected,allJobOwner;
           allJobOwner= tableview.getItems();
           JobOwnerSelected= tableview.getSelectionModel().getSelectedItems();
           JobOwnerSelected.forEach(allJobOwner::remove);}
            
            
    }
}
