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
public class Ligne_commande {
    private int id;
    private Produit prod;
    private int id_produit;
     private Commande cmd;
    private int commande_id;

    private int quantite;
    private int price;

    public Ligne_commande() {
    }

    public Ligne_commande(int id) {
        this.id = id;
    }


    public Ligne_commande( int quantite, int price) {
      
        this.quantite = quantite;
        this.price = price;
    }

   
    public Ligne_commande(int id_produit, int commande_id, int quantite, int price) {
        this.id_produit = id_produit;
        this.commande_id = commande_id;
        this.quantite = quantite;
        this.price = price;
    }

    public Ligne_commande(int id_produit, int quantite, int price) {
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.price = price;
    }
 
  



    public Ligne_commande(int id, int id_produit, int commande_id, int quantite, int price) {
        this.id = id;
        this.id_produit = id_produit;
        this.commande_id = commande_id;
        this.quantite = quantite;
        this.price = price;
    }

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    public Produit getProd() {
        return prod;
    }

    public Ligne_commande(Produit prod, Commande cmd, int quantite, int price) {
        this.prod = prod;
        this.cmd = cmd;
        this.quantite = quantite;
        this.price = price;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }

    public Ligne_commande(Produit prod, int quantite, int price) {
        this.prod = prod;
        this.quantite = quantite;
        this.price = price;
    }

    public Ligne_commande(Commande cmd, int quantite, int price) {
        this.cmd = cmd;
        this.quantite = quantite;
        this.price = price;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
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
        return "Ligne_commande{" + "id=" + id + ", id_produit=" + id_produit + ", commande_id=" + commande_id + ", quantite=" + quantite + ", price=" + price + '}';
    }

    
      public int numArticle(List<Produit> list) {
        int size = list.size();
        return size;
    }

    public float sommePanier(List<Produit> list) {
        float somme = 0;
        for (Produit element : list) {
            somme += element.getPrix()*element.getQuantiteincart();
        }
        return somme;
    }
}

