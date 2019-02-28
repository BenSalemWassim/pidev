/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fxml;

import Entity.User;
import Services.ChatService;
import Services.FreelanceService;
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
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ChatController implements Initializable {
    @FXML
    private JFXTextField idd;
    @FXML
    private JFXButton send;
     
     
     
    ServerSocket ss;
    Socket s;
    @FXML
            JFXButton sends ;
    @FXML
            JFXTextArea messages ;
    @FXML
            JFXTextField text ;
    PrintWriter pout;
    BufferedReader br;
    String ipstring;
    boolean ready2send=false;
     @FXML
    private Button ter;
    
    
    @FXML
            void actionhandler(ActionEvent event) throws UnknownHostException {
                Connection con = null;
                User cu = LoginService.getInstance().getLoggedUser();
                ResultSet resultSet = null;
                con = ConnectionUtil.getInstance();
                con = ConnectionUtil.getInstance();
                try {
                    String sql = "INSERT INTO chat (des,ip,sen) VALUES (?, ?,?)";
                    
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1,idd.getText() );
                    
                    statement.setString(2,InetAddress.getLocalHost().getHostAddress());
                    statement.setString(3,cu.getId());
                    
                    
                    int rowsInserted;
                    
                    rowsInserted = statement.executeUpdate();
                    
                    if (rowsInserted > 0) {
                        System.out.println("A new user was inserted successfully!");
                        
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FreelanceService.class.getName()).log(Level.SEVERE, null, ex);
                }
                Messenger.start();
                
            }
            /**
             * Initializes the controller class.
             */
            @Override
            public void initialize(URL url, ResourceBundle rb) {
                // TODO
                
            }
            
            Thread Messenger=new Thread(){
                public void run()
                {
                    try{
                        
                        
                        messages.setText("Waiting for an incoming connection.\nEnter my ip at client side.\nMy ip: "+InetAddress.getLocalHost().getHostAddress());
                        ss=new ServerSocket(9999);
                        s= ss.accept();
                        s.setKeepAlive(true);
                        
                        
                        pout = new PrintWriter(s.getOutputStream(),true);
                        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                        messages.setText(messages.getText()+"\nConnected to:"+s.getInetAddress().getHostAddress()+":"+s.getPort());
                        while(terminer)
                        {
                            if(ready2send==true)
                            {
                                pout.println(text.getText());
                                messages.setText(messages.getText()+"\nMe: "+text.getText());
                                text.setText("");
                                ready2send=false;
                            }
                            if(br.ready())
                            {
                                messages.setText(messages.getText()+ "\n user2"+br.readLine());
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
                terminer= false ;
                ChatService c = new ChatService();
                c.deleteDemandeByServer(idd.getText(),cu.getId());
            }
           
}
