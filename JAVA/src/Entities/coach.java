/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author DELL
 */
public class coach extends User  {
    private String metier  ;

    public coach(String text, String text0, int parseInt, String value, String text1, String doHashing, String text2, int parseInt0, String pi1, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public coach(String text, String text0, int parseInt, String value, String text1, String doHashing, String text2, int parseInt0, String pi1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public coach() {
        super();
    }

    
    
  public coach( int id, String fullname, String prenom, int cin , String metier, String email, String password, String adresse, int telephone) {
        super(id ,fullname, prenom, cin,  email, password, adresse, telephone);
        this.metier = metier;
    }
    public coach(  String fullname, String prenom, int cin , String metier,String role, String email, String password, String adresse, int telephone) {
        super(fullname, prenom, cin,role,  email, password, adresse, telephone);
        this.metier = metier;
    }
  
  
  
  
    public coach( int id, String fullname, String prenom, int cin , String metier,String role, String email, String password, String adresse, int telephone) {
        super(id ,fullname, prenom, cin,role,  email, password, adresse, telephone);
        this.metier = metier;
    }
  
    public coach(String metier, String fullname, String prenom, int cin, String role, String email, String password, String adresse, int telephone,String img , String gname) {
        super(fullname, prenom, cin, role, email, password, adresse, telephone,img , gname);
        this.metier = metier;
    }
    
  
  public coach(String metier, String fullname, String prenom, int cin, String role, String email, String password, String adresse, int telephone) {
        super(fullname, prenom, cin, role, email, password, adresse, telephone);
        this.metier = metier;
    }
    
    public coach(String metier, int id, String fullname, String prenom, int cin, String role, String email, String password, String adresse, int telephone,String img , String gname) {
        super(id,fullname, prenom, cin, role, email, password, adresse, telephone,img , gname);
        this.metier = metier;
    }   
    
    
       public coach(String metier, int id, String fullname, String prenom, int cin, String role, String email, String password, String adresse, int telephone) {
        super(id,fullname, prenom, cin, role, email, password, adresse, telephone);
        this.metier = metier;
    }   
    
    
    
    public coach(String metier, int id, String fullname, String prenom, int cin, String email, String password, String adresse, int telephone,String img , String gname) {
        super(id,fullname, prenom, cin, email, password, adresse, telephone,img , gname);
        this.metier = metier;
    }
    
  public coach(String metier, int id, String fullname, String prenom, int cin, String email, String password, String adresse, int telephone) {
        super(id,fullname, prenom, cin, email, password, adresse, telephone);
        this.metier = metier;
    }
  

   
    public coach( int id, String fullname, String prenom, int cin, String metier ,String email, String password, String adresse, int telephone,String img , String gname) {
        super(id,fullname, prenom, cin,  email, password, adresse, telephone,img , gname);
        this.metier = metier;
    }

    @Override
    public String toString() {
        return "freelancer{"+ super.toString() + "metier=" + metier + '}';
    }
    
}
