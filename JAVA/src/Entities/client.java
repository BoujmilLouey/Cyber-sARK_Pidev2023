/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author DELL
 */
public class client extends User {
    private String domaine;

    public client(String anom, String aprenom, int acin, String adom, String value, String amail, String doHashing, String adr, int aphone, String pi1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDomaine() {
        return domaine;
    }
 
    
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
                return "Client{" + "id=" + getId() +  ", telephone=" + getTelephone() + ", cin=" + getCin() + ", fullname=" + getFullname() + ", username=" + getUsername() + ", adresse=" + getAdresse() + ", email=" + getEmail() +", domaine="+domaine+ '}';

    }

    public client(String domaine, String fullname, String username, int cin, String role, String email, String password, String adresse, int telephone , String img,String gname) {
        super(fullname, username, cin, role, email, password, adresse, telephone,img , gname);
        this.domaine = domaine;
    }

    public client(String domaine, int id, String fullname, String username, int cin, String email, String password, String adresse, int telephone, String img,String gname) {
        super(id, fullname, username, cin, email, password, adresse, telephone,img , gname);
        this.domaine = domaine;
    }

 public client(String domaine, int id, String fullname, String username, int cin, String email, String password, String adresse, int telephone) {
        super(id, fullname, username, cin, email, password, adresse, telephone);
        this.domaine = domaine;
    }
    

    public client() {
    }

    public client( int id, String fullname, String username, int cin,String domaine, String email, String password, String adresse, int telephone, String img,String gname) {
        super(id, fullname, username, cin, email, password, adresse, telephone,  img, gname);
        this.domaine = domaine;
    }

    public client( int id, String fullname, String username, int cin,String domaine, String role, String email, String password, String adresse, int telephone, String img,String gname) {
        super(id, fullname, username, cin, role, email, password, adresse, telephone,  img, gname);
        this.domaine = domaine;
    }

    public client(int id) {
        super(id);
    }

    
     public client( int id, String fullname, String username, int cin,String domaine, String role, String email, String password, String adresse, int telephone) {
        super(id, fullname, username, cin, role, email, password, adresse, telephone);
        this.domaine = domaine;
    }
    
       public client(  String fullname, String username, int cin,String domaine, String role, String email, String password, String adresse, int telephone) {
        super( fullname, username, cin, role, email, password, adresse, telephone);
        this.domaine = domaine;
    }
    
    public client( String fullname, String username, int cin, String domaine,String role, String email, String password, String adresse, int telephone, String img,String gname) {
        super(fullname, username, cin, role, email, password, adresse, telephone,  img, gname);
        this.domaine = domaine;
    }
    
}
