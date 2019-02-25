/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import Entity.Freelance;
import Services.FreelanceService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
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
                
                        btndel.setVisible(true);

                pseudo.setText(tableview.getSelectionModel().getSelectedItem().getId());
                nom.setText(tableview.getSelectionModel().getSelectedItem().getNom());
                prenom.setText(tableview.getSelectionModel().getSelectedItem().getPrenom());
                email.setText(tableview.getSelectionModel().getSelectedItem().getEmail());
                adresse.setText(tableview.getSelectionModel().getSelectedItem().getAddresse());
                tel.setText(tableview.getSelectionModel().getSelectedItem().getTelephone());
                secteur.setText(tableview.getSelectionModel().getSelectedItem().getSecteur());

               



            }
        });
        
        
       
        
    }
     @FXML
        public void deletef(ActionEvent event){
            FreelanceService fs = new FreelanceService() ;
           String r= fs.supprimerFreelance(tableview.getSelectionModel().getSelectedItem().getId());
           if(r.equalsIgnoreCase("ok")){
           ObservableList<Freelance> freelanceSelected,allfreelancers;
           allfreelancers= tableview.getItems();
           freelanceSelected= tableview.getSelectionModel().getSelectedItems();
           freelanceSelected.forEach(allfreelancers::remove);}
            
            
    }
        
    
}
