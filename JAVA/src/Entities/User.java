/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class User {

    private int id, telephone, cin, is_banned;
    private String fullname, username, adresse, role, email, password;
    private List<String>roles;
    private String Image;
    private String GUserName;
    private int archive;

    public User(int id, int telephone, int cin, int is_banned, String fullname, String username, String adresse, String role, String email, String password, String Image, String GUserName, int archive) {
        this.id = id;
        this.telephone = telephone;
        this.cin = cin;
        this.is_banned = is_banned;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;
        this.Image = Image;
        this.GUserName = GUserName;
        this.archive = archive;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    public User() {
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String fullname, String username, int cin, String role, String email, String password, String adresse, int telephone, String Image, String GUserName) {
        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;
        this.Image = Image;
        this.GUserName = GUserName;
    }

    public User(int id, String fullname, String prenom, int cin, String role, String adresse, String email, String password, int telephone, String Image, String GUserName) {
        this.id = id;

        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;
        this.Image = Image;
        this.GUserName = GUserName;
    }

    public User(int id, String fullname, String username, int cin, String adresse, String email, String password, int telephone, String Image, String GUserName) {
        this.id = id;

        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;
        this.Image = Image;
        this.GUserName = GUserName;
    }

    public User(int id, String fullname, String username, int cin, String adresse, String email, String password, int telephone) {
        this.id = id;

        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;

    }
        public User(String fullname, String username, int cin,String role , String email, String password, String adresse,int telephone) {

        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;

    }
                public User(int id ,String fullname, String username, int cin,String role , String email, String password, String adresse,int telephone) {
        this.id = id;

        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.role = role;
        this.email = email;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getGUserName() {
        return GUserName;
    }

    public void setGUserName(String GUserName) {
        this.GUserName = GUserName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.telephone != other.telephone) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.Image, other.Image)) {
            return false;
        }
        if (!Objects.equals(this.GUserName, other.GUserName)) {
            return false;
        }
        return true;
    }

    public User(  String fullname, String username,int cin, String email,String password,  String adresse,int telephone) {
        this.telephone = telephone;
        this.cin = cin;
        this.fullname = fullname;
        this.username = username;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + ", telephone=" + telephone + ", cin=" + cin + ", fullname=" + fullname + ", username=" + username + ", adresse=" + adresse + ", role=" + role + ", email=" + email + ", password=" + password + ", Image=" + Image + ", GUserName=" + GUserName + '}';
    }

}
