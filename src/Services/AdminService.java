/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import utils.ConnectionUtil;

/**
 *
 * @author wassim
 */
public class AdminService {
    Connection con = null;
    
    
    ResultSet resultSet = null;
    public AdminService() {
        con = ConnectionUtil.getInstance();
    }
    
    
    
}
