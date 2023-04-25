/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author BAZINFO
 */
public class Commande {
   private int id;
   private int user_id;
   private Date date_commande;
   private int montant_commande;

    public Commande() {
    }

    public Commande(int id) {
        this.id = id;
    }

    public Commande(Date date_commande, int montant_commande) {
        this.date_commande = date_commande;
        this.montant_commande = montant_commande;
    }

    public Commande(int user_id, Date date_commande, int montant_commande) {
        this.user_id = user_id;
        this.date_commande = date_commande;
        this.montant_commande = montant_commande;
    }

    public Commande(int id,int user_id, Date date_commande, int montant_commande) {
        this.id = id;
        this.user_id = user_id;
        this.date_commande = date_commande;
        this.montant_commande = montant_commande;
    }

    public Commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public int getMontant_commande() {
        return montant_commande;
    }

    public void setMontant_commande(int montant_commande) {
        this.montant_commande = montant_commande;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user_id=" + user_id + ", date_commande=" + date_commande + ", montant_commande=" + montant_commande + '}';
    }
   
}
