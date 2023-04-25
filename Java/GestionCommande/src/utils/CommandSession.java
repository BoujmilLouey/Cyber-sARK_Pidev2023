/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Ligne_commande;
import entities.Produit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BAZINFO
 */
public class CommandSession {
    private int userId;
    private List<Produit> cart;
   

    private static Map<Integer, CommandSession> instances = new HashMap<>();

    private CommandSession(int userId) {
        this.userId = userId;
        this.cart = new ArrayList<>();
    }

    public static CommandSession getInstance(int userId) {
        CommandSession instance = instances.get(userId);
        if (instance == null) {
            instance = new CommandSession(userId);
            instances.put(userId, instance);
        }
        return instance;
    }

    public void addToCart(Produit product) {
        for (Produit p : cart) {
            if (p.getNom().equals(product.getNom())) {
                p.setQuantiteincart(p.getQuantiteincart() + 1);

                return;
            }
        }
        cart.add(product);
    }

    public void removeFromCart(Produit product) {
        for (Produit p : cart) {
            if (p.getNom().equals(product.getNom())) {
                int newQuantity = p.getQuantiteincart() - 1;
                if (newQuantity == 0) {
                    cart.remove(product);
                } else {
                    p.setQuantiteincart(newQuantity);
                    product.setQuantiteincart(newQuantity);
                }
                return;
            }
        }
    }

    public List<Produit> getCart() {
        return cart;
    }

 

    


    public double getTotal() {
        double total = 0;
        for (Produit product : cart) {
            total += product.getPrix() * product.getQuantiteincart();
        }
        return total;
    }


        public void clearCart() {
        cart.clear();
    }
        
        
        
        
        
        
}
