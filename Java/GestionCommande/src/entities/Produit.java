/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author BAZINFO
 */
public class Produit {
    private int id;
    private int id_categorie_produit_id;
    private String nom;
    private String reference;
    private String image;
    private double prix;
    private String couleur;
    private double poids;
    private String description;
    private int quantiteincart;


    public Produit() {
    }

    public Produit(int id) {
        this.id = id;
    }

    public Produit(int id, int id_categorie_produit_id, String nom, String reference, String image, double prix, String couleur, double poids, String description) {
        this.id = id;
        this.id_categorie_produit_id = id_categorie_produit_id;
        this.nom = nom;
        this.reference = reference;
        this.image = image;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
    }

    public Produit(int id_categorie_produit_id, String nom, String reference, String image, double prix, String couleur, double poids, String description) {
        this.id_categorie_produit_id = id_categorie_produit_id;
        this.nom = nom;
        this.reference = reference;
        this.image = image;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
    }

    public Produit(int id, int id_categorie_produit_id, String nom, String reference, double prix, String couleur, double poids, String description) {
        this.id = id;
        this.id_categorie_produit_id = id_categorie_produit_id;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.couleur = couleur;
        this.poids = poids;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categorie_produit_id() {
        return id_categorie_produit_id;
    }

    public void setId_categorie_produit_id(int id_categorie_produit_id) {
        this.id_categorie_produit_id = id_categorie_produit_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_categorie_produit_id=" + id_categorie_produit_id + ", nom=" + nom + ", reference=" + reference + ", image=" + image + ", prix=" + prix + ", couleur=" + couleur + ", poids=" + poids + ", description=" + description + '}';
    }
   public int getQuantiteincart() {
        return quantiteincart;
    }

    public void setQuantiteincart(int quantiteincart) {
        this.quantiteincart = quantiteincart;
    }
    
    
}
