/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class categorie_produit {
    private int id;
    private String type,reference;
    private Set<produit> produits = new HashSet<>();

    public categorie_produit(int id, String type, String reference) {
        this.id = id;
        this.type = type;
        this.reference = reference;
    }

    public categorie_produit() {
    }

    public categorie_produit(String type, String reference) {
        this.type = type;
        this.reference = reference;
    }

    public categorie_produit(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "categorie_produit{" + "id=" + id + ", type=" + type + ", reference=" + reference + ", produits=" + produits + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<produit> produits) {
        this.produits = produits;
    }
    
}
