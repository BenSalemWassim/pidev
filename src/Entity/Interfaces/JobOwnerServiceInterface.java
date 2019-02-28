/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Interfaces;

import Entity.JobOwner;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface JobOwnerServiceInterface {

    String AddJobOwner(JobOwner jo) throws SQLException;

    JobOwner GetJobOwnerByEmail(JobOwner jo) throws SQLException;

    JobOwner GetJobOwnerById(JobOwner jo) throws SQLException;

    String MD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    List<JobOwner> afficherJobOwners();

    void changerMDP(String mdp, String newMdp, String id);

    void modifierJobOwner(JobOwner u, String id);

    String supprimerJobOwner(String id);

    boolean uniqueMail(String mail);

    boolean uniquePseudo(String id);
    
}
