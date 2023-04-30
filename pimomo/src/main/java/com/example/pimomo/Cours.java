/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pimomo;

/**
 *
 * @author momo
 */
public class Cours {
    
    private int id;
    private String nom;


    private Double NoteMoyenne;

    private String des;
   
    private String pdf;
    

    public Cours() {
    }
    
    
     public Cours(int id, String nom, String des,  String pdf) {
        this.id = id;
        this.nom = nom;
        this.des = des;
        
        this.pdf = pdf;

    }


    public Cours(int id, String nom, String des,  String pdf,Double NoteMoyenne) {
        this.id = id;
        this.nom = nom;
        this.des = des;

        this.pdf = pdf;
        this.NoteMoyenne=NoteMoyenne;
    }

    public Cours(String nom, String des, String pdf) {
       this.nom = nom;
        this.des = des;
        
        this.pdf = pdf;
    }

    public Cours(int id, String nom, String description) {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

   

   

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

   
    
    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nom=" + nom + '}';
    }


    public Double getNoteMoyenne() {
        return NoteMoyenne;
    }

    public void setNoteMoyenne(Double noteMoyenne) {
        NoteMoyenne = noteMoyenne;
    }
}
