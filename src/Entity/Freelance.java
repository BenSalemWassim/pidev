/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author wassim
 */
public class Freelance extends User {

    
    
    String secteur ;

    public Freelance(String secteur, String id, String password, String nom, String prenom, String email, String addresse, String telephone, String type) {
        super(id, password, nom, prenom, email, addresse, telephone, type);
        this.secteur = secteur;
    }

   


    

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public String toString() {
        return "Freelance{" + "secteur=" + secteur + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.secteur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Freelance other = (Freelance) obj;
        if (!Objects.equals(this.secteur, other.secteur)) {
            return false;
        }
        return true;
    }

    public Freelance() {
    }
    
}
