/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Interfaces.ChatServiceInterface;
import Entity.Chat;
import Entity.Freelance;
import Entity.User;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionUtil;

/**
 *
 * @author wassim
 */
public class ChatService implements ChatServiceInterface {
    
    Connection con = null;
    
    
    ResultSet resultSet = null;
    public ChatService() {
        con = ConnectionUtil.getInstance();
    }
    
    
    @Override
      public String AjouterDemande(Chat c) throws SQLException {
 Connection con = null;
                        ResultSet resultSet = null;
                        con = ConnectionUtil.getInstance();
                        con = ConnectionUtil.getInstance();
                        try {
                            String sql = "INSERT INTO chat (des,ip,sen) VALUES (?, ?,?)";
                            
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1,c.getDes() );
                            
                            statement.setString(2,c.getIp());
                            statement.setString(3,c.getSend());
                            
                            
                            int rowsInserted;
                            
                            rowsInserted = statement.executeUpdate();
                            
                            if (rowsInserted > 0) {
                                System.out.println("A new user was inserted successfully!");
                                        return "ok";

                            }
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(FreelanceService.class.getName()).log(Level.SEVERE, null, ex);
                        }
        return "nok";
        
    }
   
    
    @Override
     public List<Chat> afficherDemandeContact(String de) {
        ArrayList<Chat> js = new ArrayList<>();
        try {
            String req = "select des, ip , sen from chat where des='"+de+"'";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
               Chat c = new Chat();
                c.setDes(result.getString("des"));
                c.setSend(result.getString("sen"));
                                c.setIp(result.getString("ip"));
               
                js.add(c);
            }
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return js;
    }
    @Override
       public String deleteDemandeByClient(String id) {
        try {
            String reqDelete = "delete from chat where des=?";
            
            PreparedStatement ps = con.prepareStatement(reqDelete);
            ps.setString(1,id);
             ps.executeUpdate();
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                                      return("nok");

    }
    @Override
       public String deleteDemandeByServer(String id, String d) {
        try {
            String reqDelete = "delete from chat where sen=? and des = ?";
            
            PreparedStatement ps = con.prepareStatement(reqDelete);
            ps.setString(1,d);
                        ps.setString(2,id);

             ps.executeUpdate();
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                                      return("nok");

    }
       
     
}
