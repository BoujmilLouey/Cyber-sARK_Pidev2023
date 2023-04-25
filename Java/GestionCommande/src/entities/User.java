/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

/**
 *
 * @author BAZINFO
 */
public class User {
      private int id;
    private String email; 
    private String roles; 
    private String password;
    private int is_verified;
    private int is_banned;
    private String username;
    private String fullname;
    private Date naissance;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String email, String roles, String password, int is_verified, int is_banned, String username, String fullname, Date naissance) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.is_banned = is_banned;
        this.username = username;
        this.fullname = fullname;
        this.naissance = naissance;
    }

    public User(String email, String roles, String password, int is_verified, int is_banned, String username, String fullname, Date naissance) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.is_banned = is_banned;
        this.username = username;
        this.fullname = fullname;
        this.naissance = naissance;
    }

    public User(int id, String email, String password, int is_verified, int is_banned, String username, String fullname, Date naissance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.is_verified = is_verified;
        this.is_banned = is_banned;
        this.username = username;
        this.fullname = fullname;
        this.naissance = naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    @Override
    public String toString() {
      return  username ;    }

 public static String hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("SHA-256 algorithm not found");
            return null;
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
