/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author Admin
 */
public class produit {
   
    private int id;
    private String nom,reference,prix,couleur,poids,description,image;
    private categorie_produit categorie;
    private int categorie_id;

    public produit(int id, String nom, String reference, String prix, String couleur, String poids, String description, String image, categorie_produit categorie, int categorie_id) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
        this.categorie_id = categorie_id;
    }

    public produit(String nom, String reference, String prix, String couleur, String poids, String description, String image, categorie_produit categorie, int categorie_id) {
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
        this.categorie_id = categorie_id;
    }

    public produit(int id, String nom, String reference, String prix, String couleur, String poids, String description, String image, int categorie_id) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
        this.image = image;
        this.categorie_id = categorie_id;
    }

    public produit(String nom, String reference, String prix, String couleur, String poids, String description, String image, int categorie_id) {
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
        this.image = image;
        this.categorie_id = categorie_id;
    }

    public produit(int id, String nom, String reference, String prix, String couleur, String poids, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
        this.image = image;
    }

   

    public produit() {
    }

    public categorie_produit getCategorie() {
        return categorie;
    }

    public void setCategorie(categorie_produit categorie) {
        this.categorie = categorie;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", nom=" + nom + ", reference=" + reference + ", prix=" + prix + ", couleur=" + couleur + ", poids=" + poids + ", description=" + description + ", image=" + image + ", categorie=" + categorie + ", categorie_id=" + categorie_id + '}';
    }

   

   
   
    
    }
