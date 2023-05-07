package Entities;



import Utils.RelationObject;

import java.time.LocalDate;

public class Commande {
    
    private int id;
    private LocalDate dateCommande;
    private float montantCommande;
    private RelationObject userId;
    
    public Commande(int id, LocalDate dateCommande, float montantCommande, RelationObject userId) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.montantCommande = montantCommande;
        this.userId = userId;
    }

    public Commande(LocalDate dateCommande, float montantCommande, RelationObject userId) {
        this.dateCommande = dateCommande;
        this.montantCommande = montantCommande;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }
    
    public float getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(float montantCommande) {
        this.montantCommande = montantCommande;
    }
    
    public RelationObject getUserId() {
        return userId;
    }

    public void setUserId(RelationObject userId) {
        this.userId = userId;
    }
    

    
}