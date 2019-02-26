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
        private String type;
        private String photo;
                
        private int completion_rate ;

   

    public User(String id, String password, String nom, String prenom, String email, String addresse, String telephone, String type, String photo, int completion_rate) {
        this.id = id;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.addresse = addresse;
        this.telephone = telephone;
        this.type = type;
        this.photo = photo;
        this.completion_rate = completion_rate;
    }

    public User(String id, String password, String nom, String prenom, String email, String addresse, String telephone, String type, int completion_rate) {
        this.id = id;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.addresse = addresse;
        this.telephone = telephone;
        this.type = type;
        this.completion_rate = completion_rate;
    }
        
    public User(String id, String password, String nom, String prenom, String email, String addresse, String telephone, String type) {
        this.id = id;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.addresse = addresse;
        this.telephone = telephone;
        this.type = type;
    }

    public void setCompletion_rate(int completion_rate) {
        this.completion_rate = completion_rate;
    }

    public int getCompletion_rate() {
        return completion_rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "User{" + "id=" + id + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", addresse=" + addresse + ", telephone=" + telephone + ", type=" + type + '}';
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
 public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
