/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Game_rating {
    private int id;
    private int rating;
    private Games game;

    public Game_rating(int id, int rating, Games game) {
        this.id = id;
        this.rating = rating;
        this.game = game;
    }

    public Game_rating(int rating, Games game) {
        this.rating = rating;
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Game_rating{" + "id=" + id + ", rating=" + rating + ", game=" + game + '}';
    }
    
}
