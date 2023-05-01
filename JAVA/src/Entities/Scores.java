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
public class Scores {
    private int id;
    private int score;
    private int game_id;
    private int user_id;

    public Scores(int id, int score, int game_id, int user_id) {
        this.id = id;
        this.score = score;
        this.game_id = game_id;
        this.user_id = user_id;
    }

    public Scores(int score, int game_id, int user_id) {
        this.score = score;
        this.game_id = game_id;
        this.user_id = user_id;
    }

    public Scores(int score, int game_id) {
        this.score = score;
        this.game_id = game_id;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getGame_id() {
        return game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.score;
        hash = 71 * hash + this.game_id;
        hash = 71 * hash + this.user_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Scores other = (Scores) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.score != other.score) {
            return false;
        }
        if (this.game_id != other.game_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Scores{" + "id=" + id + ", score=" + score + ", game_id=" + game_id + ", user_id=" + user_id + '}';
    }
    
    
    
}
