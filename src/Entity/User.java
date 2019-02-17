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
public class User {
    private String id ; 
        private String password ; 
        private String nom ; 
        private String prenom ; 
        private String email ; 
        private String addresse ; 
        private String telephone ; 

    public User(String id, String password, String nom, String prenom, String email, String addresse, String telephone) {
        this.id = id;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.addresse = addresse;
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", addresse=" + addresse + ", telephone=" + telephone + '}';
    }

    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getTelephone() {
        return telephone;
    }

}
