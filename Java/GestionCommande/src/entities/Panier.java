/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author BAZINFO
 */
public class Panier {
     private int id;
        List<Produit> list;
 private int commande_id;

    private int quantite;
    private int price;
     
    
    public Panier() {
    }

    public Panier(int id) {
        this.id = id;
    }

    public Panier(List<Produit> list, int commande_id, int quantite, int price) {
        this.list = list;
        this.commande_id = commande_id;
        this.quantite = quantite;
        this.price = price;
    }

    public Panier(int id, List<Produit> list) {
        this.id = id;
        this.list = list;
    }

    public Panier(List<Produit> list) {
        this.list = list;
    }

    public Panier(int commande_id, int quantite, int price) {
        this.commande_id = commande_id;
        this.quantite = quantite;
        this.price = price;
    }

    public Panier(int id, List<Produit> list, int commande_id, int quantite, int price) {
        this.id = id;
        this.list = list;
        this.commande_id = commande_id;
        this.quantite = quantite;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produit> getList() {
        return list;
    }

    public void setList(List<Produit> list) {
        this.list = list;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", list=" + list + ", commande_id=" + commande_id + ", quantite=" + quantite + ", price=" + price + '}';
    }
    
     public int numArticle(List<Produit> list) {
        int size = list.size();
        return size;
    }

    public float sommePanier(List<Produit> list) {
        float somme = 0;
        for (Produit element : list) {
            somme += element.getPrix()*element.getPoids();
        }
        return somme;
    }
    
    
    
    
    
    
    
    
}
