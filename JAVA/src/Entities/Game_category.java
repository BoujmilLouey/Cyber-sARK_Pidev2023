/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Game_category {
       private int id;
       private String name;
      
      
    public Game_category(int id, String name) {
        this.id = id;
        this.name = name;
        
    }

    public Game_category(String name) {
        this.name = name;
        
    }

   
       
     


   

   
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.name);
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
        final Game_category other = (Game_category) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game_category{" + "id=" + id + ", name=" + name + '}';
    }
       
       
       
}

