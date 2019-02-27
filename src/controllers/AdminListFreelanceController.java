/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import Entity.Freelance;
import Services.FreelanceService;
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
public class AdminListFreelanceController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
            StackPane stackpane;
    
    @FXML
    private TableView<Freelance> tableview;
    @FXML
    private TableColumn<Freelance, String> idf;
    @FXML
    private TableColumn<Freelance, String> emailf;
    @FXML
    private TableColumn<Freelance, String> secteurf;
    @FXML
    private Label tel;
    @FXML
    private Label secteur;
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
     @FXML
    private ImageView imagev;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btndel.setVisible(false);
        FreelanceService fs = new FreelanceService();
        ArrayList<Freelance> list = (ArrayList<Freelance>) fs.afficherFreelancers();
        ObservableList<Freelance> frs=FXCollections.observableArrayList(list);
        
        tableview.setItems(frs);
        idf.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailf.setCellValueFactory(new PropertyValueFactory<>("email"));
        secteurf.setCellValueFactory(new PropertyValueFactory<>("secteur"));
        
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
                secteur.setText("Secteur "+tableview.getSelectionModel().getSelectedItem().getSecteur());

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
            FreelanceService fs = new FreelanceService() ;
           String r= fs.supprimerFreelance(tableview.getSelectionModel().getSelectedItem().getId());
           if(r.equalsIgnoreCase("ok")){
               MailingService m = new MailingService();                                                 
            m.envoi(tableview.getSelectionModel().getSelectedItem().getEmail(), "Nous sommes désolé "+ tableview.getSelectionModel().getSelectedItem().getNom() +" votre compte a été supprimé par l'administrateur ");

           ObservableList<Freelance> freelanceSelected,allfreelancers;
           allfreelancers= tableview.getItems();
           freelanceSelected= tableview.getSelectionModel().getSelectedItems();
           freelanceSelected.forEach(allfreelancers::remove);}
            
            
    }
        
    
}
