/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

   private static Connection INSTANCE = null;
  public ConnectionUtil() {}
  
  
  private static Connection connect()   {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/smartstart", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
        }
        return null;
    }
   public static Connection getInstance()
    {   
        if (INSTANCE == null)
        {   
            synchronized(Connection.class)
            {
                if (INSTANCE == null)
                {   INSTANCE = connect() ;
                }
            }
        }
        return INSTANCE;
    }
}
