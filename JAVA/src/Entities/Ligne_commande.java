package Entities;


import Utils.RelationObject;

public class Ligne_commande {

    private int id;
    private RelationObject commandeId;
    private int quantite;
    private RelationObject produitId;
    private float price;

    public Ligne_commande(int id, RelationObject commandeId, int quantite, RelationObject produitId, float price) {
        this.id = id;
        this.commandeId = commandeId;
        this.quantite = quantite;
        this.produitId = produitId;
        this.price = price;
    }

    public Ligne_commande(RelationObject commandeId, int quantite, RelationObject produitId, float price) {
        this.commandeId = commandeId;
        this.quantite = quantite;
        this.produitId = produitId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RelationObject getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(RelationObject commandeId) {
        this.commandeId = commandeId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public RelationObject getProduitId() {
        return produitId;
    }

    public void setProduitId(RelationObject produitId) {
        this.produitId = produitId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}