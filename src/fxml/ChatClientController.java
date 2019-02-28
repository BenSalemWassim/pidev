/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entity.Chat;
import Entity.User;
import Services.ChatService;
import Services.LoginService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ChatClientController implements Initializable {

   
    String ipstring;
        @FXML
    private TableView<Chat> tableview;
@FXML
    private Button chat;
    @FXML
    private TableColumn<Chat, String> nom;
    
    
    String DestName="";
    String DestIp="";
    @FXML
    private Button ter;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
          messages.setVisible(false);
                     sends.setVisible(false);
                     text.setVisible(false);
        tableview.setVisible(true);
          ChatService fs = new ChatService();
        User cu = LoginService.getInstance().getLoggedUser();
        ArrayList<Chat> list = (ArrayList<Chat>) fs.afficherDemandeContact(cu.getId());
        ObservableList<Chat> frs=FXCollections.observableArrayList(list);
        
        tableview.setItems(frs);
        nom.setCellValueFactory(new PropertyValueFactory<>("send"));
        
        tableview.setOnMousePressed( new EventHandler<MouseEvent>(){
            
            @Override
            public void handle (MouseEvent event){
                System.out.println(DestIp);
                                System.out.println(DestName);

                                    DestIp=  tableview.getSelectionModel().getSelectedItem().getIp();
                                    DestName=tableview.getSelectionModel().getSelectedItem().getSend();
               



            }
        });
        

    }
    
    public void chater( ActionEvent event){
       
                     if(!DestIp.equals(""))
                     {  Messenger.start();
                      tableview.setVisible(false);
        chat.setVisible(false);
                     messages.setVisible(true);
                     sends.setVisible(true);
                     text.setVisible(true);
                     }

    }
    
    
    
        @FXML
    private JFXTextArea messages;

    @FXML
    private JFXTextField text;

    @FXML
    private JFXButton sends;
    PrintWriter pout;
    BufferedReader br;
    boolean ready2send=false;
     ServerSocket ss;
    Socket s;
    @FXML
    void send(ActionEvent event) {
      ready2send= true ;

    }
    
    Thread Messenger=new Thread(){
 public void run()
 {
  try{

  
       messages.setText("Connecting to:"+DestName+":9999"); 
        s= new Socket(   InetAddress.getByName(DestIp),9999);
        
  
pout = new PrintWriter(s.getOutputStream(),true);
  br=new BufferedReader(new InputStreamReader(s.getInputStream()));
  messages.setText(messages.getText()+"\nConnected to:"+s.getInetAddress().getHostAddress()+":"+s.getPort());
   while(terminer)
  {
   if(ready2send==true)
        {
        pout.println(text.getText());
        messages.setText(messages.getText()+"\nMe: "+ text.getText());
        text.setText("");
        ready2send=false;
        }
    if(br.ready())
        {
        messages.setText(messages.getText()+ "\n "+DestName+": "+br.readLine());
        }
    Thread.sleep(80);
  }
 }   catch (IOException ex) {
     } catch (InterruptedException ex) {
             System.exit(0);

     }

}
 
 
 
    
    } ;
                 public void send() throws InterruptedException, IOException{
     
  
 
   
      ready2send= true ;
      
  
 
  }
                  boolean terminer = true;
            public void terminer(ActionEvent event){
                User cu = LoginService.getInstance().getLoggedUser();
                terminer = false ;
                ChatService c = new ChatService();
                c.deleteDemandeByServer(cu.getId(),DestName);
            }
            
            
            
}
