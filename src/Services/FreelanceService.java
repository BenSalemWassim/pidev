/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Entity.Interfaces.FreelanceServiceInterface;
import Entity.Freelance;
import Entity.JobOwner;
import Entity.User;
import static Services.LoginService.loggedUser;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.control.Notifications;
import utils.ConnectionUtil;

/**
 *
 * @author wassim
 */
public class FreelanceService implements FreelanceServiceInterface {
    Connection con = null;
    
    
    ResultSet resultSet = null;
    public FreelanceService() {
        con = ConnectionUtil.getInstance();
    }
    
    
    
    @Override
    public String AddFreelance(Freelance jo) throws SQLException {
        
        Connection con = null;
        
        ResultSet resultSet = null;
        con = ConnectionUtil.getInstance();
        boolean mailTest;
        boolean pseudoTest;
        mailTest= uniqueMail(jo.getEmail());
        pseudoTest = uniquePseudo(jo.getId());
        
        
        if(! pseudoTest){
            System.out.println("idExist");
            if(! mailTest){
                return("idemailExist");
            }
            return("idExist");
        }
        if(! mailTest){
            return("emailExist");
        }
        
        if(mailTest && pseudoTest){
            try {
                String sql = "INSERT INTO user (id, password, nom, prenom,email,addresse,telephone,secteur,type,photo) VALUES (?, ?,?, ?, ?,?,?, ?,?,?)";
                
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, jo.getId());
                statement.setString(2, jo.getPassword());
                statement.setString(3, jo.getNom());
                statement.setString(4, jo.getPrenom());
                statement.setString(5, jo.getEmail());
                statement.setString(6, jo.getAddresse());
                statement.setString(7, jo.getTelephone());
                statement.setString(8, jo.getSecteur());
                statement.setString(9, "freelance");
                statement.setString(10,jo.getPhoto());

                
                int rowsInserted;
                
                rowsInserted = statement.executeUpdate();
                
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                    
                }
                return"ok";
                
            } catch (SQLException ex) {
                Logger.getLogger(FreelanceService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        return "ok";
        
    }
    @Override
    public Freelance GetFreelanceById (Freelance jo) throws SQLException {
        
        String sql = "select * from freelance where id='"+jo.getId()+"'";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet result = statement.executeQuery(sql);
        if (!result.next()){
            return null ;
        }
        Freelance jol = new Freelance();
        
        while (result.next()){
            jol.setId(result.getString(1));
            jol.setPassword(result.getString(2));
            jol.setNom(result.getString(3));
            jol.setPrenom(result.getString(4));
            jol.setEmail(result.getString(5));
            jol.setAddresse(result.getString(6));
            jol.setTelephone(result.getString(7));
            jol.setSecteur(result.getString(8));
                        jol.setType(result.getString(9));

            
            
        }
        return jol ;
    }
    @Override
    public Freelance GetFreelanceByEmail (Freelance jo) throws SQLException {
        
        String sql = "select * from freelance where email='"+jo.getEmail()+"'";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet result = statement.executeQuery(sql);
        if (!result.next()){
            System.out.println("frssssssssssssss");
            return null ;
        }else{
            Freelance jol = new Freelance();
            
            while (result.next()){
                jol.setId(result.getString(1));
                jol.setPassword(result.getString(2));
                jol.setNom(result.getString(3));
                jol.setPrenom(result.getString(4));
                jol.setEmail(result.getString(5));
                jol.setAddresse(result.getString(6));
                jol.setTelephone(result.getString(7));
                jol.setSecteur(result.getString(8));
                                        jol.setType(result.getString(9));

                
            }
            return jol ;}
    }
    
    
    @Override
    public List<Freelance> afficherFreelancers() {
        ArrayList<Freelance> js = new ArrayList<>();
        try {
            String req = "select id,nom,prenom,password,email,telephone,addresse, secteur ,type,photo from user where type='freelance'";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Freelance jol = new Freelance();
                jol.setId(result.getString(1));
                jol.setNom(result.getString(2));
                                jol.setPrenom(result.getString(3));
               jol.setPassword(result.getString(4));
                jol.setEmail(result.getString(5));
                jol.setTelephone(result.getString(6));
                jol.setAddresse(result.getString(7));
                
                jol.setSecteur(result.getString(8));
                jol.setType(result.getString(9));
                jol.setPhoto(result.getString(10));

                js.add(jol);
            }
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return js;
    }
    
    
    
    @Override
    public void modifierFreelance(Freelance u,String id) {
        try {
            String reqUpdate = "update user set"
                    
                    + " nom=?,"
                    + "prenom=?,"
                    +"telephone=? ,addresse=?  , photo= ? where id = ?";
            PreparedStatement ps = con.prepareStatement(reqUpdate);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getTelephone());
            ps.setString(4, u.getAddresse());
            ps.setString(5, u.getPhoto());
            
            ps.setString(6,id);
            
                int col= ps.executeUpdate();
                    if(col >0){
             Notifications.create()
                    .title("Notification")
                    .text("votre compte a été mis à jour").darkStyle()
                    .showInformation();
            System.out.println("envoyé");
                           LoginService.getInstance().changeLoggedUser(u);
                    }
          else{
          Notifications.create()
                    .title("Notification")
                    .text("votre compte n'a pas été mis à jour").darkStyle()
                    .showError();
          }
             
        } catch (SQLException ex) {
             Notifications.create()
                    .title("Notification")
                    .text("Error").darkStyle()
                    .showError();
        }
    }
    
    @Override
    public String supprimerFreelance (String id) {
        try {
            String reqDelete = "delete from user where id=?";
            
            PreparedStatement ps = con.prepareStatement(reqDelete);
            ps.setString(1,id);
            int col = ps.executeUpdate();
            if(col >0){
             Notifications.create()
                    .title("Notification")
                    .text("le compte a été supprimé").darkStyle()
                    .showInformation();
            System.out.println("envoyé");
                    return("ok");
                    
                    }
          else{
          Notifications.create()
                    .title("Notification")
                    .text("le compte n'a pas  supprimé").darkStyle()
                    .showError();
                              return("nok");

          }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                                      return("nok");

    }
    @Override
    public void changerMDP(String mdp ,String newMdp,String id)
    {
        try {
            String reqUpdate = "update user set password=? where id=? and password= ?";
            PreparedStatement ps = con.prepareStatement(reqUpdate);
            ps.setString(1, newMdp);
            ps.setString(2,id);
                        ps.setString(3,mdp);

             int col= ps.executeUpdate();
                    if(col >0){
             Notifications.create()
                    .title("Notification")
                    .text("votre compte a été mis à jour").darkStyle()
                    .showInformation();
            System.out.println("envoyé");
                    
                    
                    }
          else{
          Notifications.create()
                    .title("Notification")
                    .text("mot de passe incorrect").darkStyle()
                    .showError();
          }
             
        } catch (SQLException ex) {
             Notifications.create()
                    .title("Notification")
                    .text("Error").darkStyle()
                    .showError();
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public String MD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        byte[] bytesOfMessage = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        BigInteger bigInt = new BigInteger(1,thedigest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    
  
    @Override
    public boolean uniqueMail(String mail)
    {
        boolean free=true;
        try{
            String reqRec = "select count(*) from user where email=? ";
            PreparedStatement ps = con.prepareStatement(reqRec);
            ps.setString(1,mail);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                if(rs.getInt(1)!=0)
                    free=false;
            }}
        
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return free;
    }
    @Override
    public boolean uniquePseudo(String id)
    {
        boolean free=true;
        try{
            String reqRec = "select count(*) from user where id=? ";
            PreparedStatement ps = con.prepareStatement(reqRec);
            ps.setString(1,id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                if(rs.getInt(1)!=0)
                    free=false;
            }}
        
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return free;
    }
    
}

