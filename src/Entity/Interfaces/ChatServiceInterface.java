/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Interfaces;

import Entity.Chat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface ChatServiceInterface {

    String AjouterDemande(Chat c) throws SQLException;

    List<Chat> afficherDemandeContact(String de);

    String deleteDemandeByClient(String id);

    String deleteDemandeByServer(String id, String d);
    
}
