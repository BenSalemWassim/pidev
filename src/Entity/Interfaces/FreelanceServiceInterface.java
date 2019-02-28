/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Interfaces;

import Entity.Freelance;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface FreelanceServiceInterface {

    String AddFreelance(Freelance jo) throws SQLException;

    Freelance GetFreelanceByEmail(Freelance jo) throws SQLException;

    Freelance GetFreelanceById(Freelance jo) throws SQLException;

    String MD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    List<Freelance> afficherFreelancers();

    void changerMDP(String mdp, String newMdp, String id);

    void modifierFreelance(Freelance u, String id);

    String supprimerFreelance(String id);

    boolean uniqueMail(String mail);

    boolean uniquePseudo(String id);
    
}
